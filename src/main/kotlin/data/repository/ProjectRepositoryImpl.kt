package data.repository

//import com.grapecity.documents.excel.drawing.b.it
import common.date.getCurrentDateTime
import data.local.dao.ProjectDao
import data.mapper.projectResponseToProject
import data.remote.api.OrdinaryProjectFairApi
import domain.model.*
import domain.repository.LoggingRepository
import domain.repository.ProjectRepository
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.types.RealmAny
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val projectDao: ProjectDao,
    private val projectFairApi: OrdinaryProjectFairApi,
    private val loggingRepository: LoggingRepository,
) : ProjectRepository {

    override val downloadFlow = MutableStateFlow<Float>(0f)

    override fun getProjects(): Flow<ResultsChange<Project>> {
        return projectDao.getAll()
    }

    override suspend fun updateProject(project: Project) {
        projectDao.update(project)
        loggingRepository.saveLog(
            log = Log(
                id = UUID.randomUUID().toString(),
                dateTime = getCurrentDateTime(),
                subject = project,
            ),
            logType = LogType.CHANGE,
            logSource = LogSource.USER
        )
    }

    override suspend fun syncProjectById(id: Int): Boolean {
        return try {
            val project = projectFairApi.getProjectById(id)
            updateProject(projectResponseToProject(project))
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun insertProject(project: Project) {
        withContext(ioDispatcher) {
            projectDao.insert(project)
            loggingRepository.saveLog(
                log = Log(
                    id = UUID.randomUUID().toString(),
                    dateTime = getCurrentDateTime(),
                    subject = project,
                ),
                logType = LogType.SAVE,
                logSource = LogSource.SERVER
            )
        }
    }

    override suspend fun insertProject(projects: List<Project>) {
        withContext(ioDispatcher) {
            projectDao.insert(projects)
        }
    }

    override suspend fun deleteProject(project: Project) {
        projectDao.delete<Student>(project)
        loggingRepository.saveLog(
            log = Log(
                id = UUID.randomUUID().toString(),
                dateTime = getCurrentDateTime(),
                subject = project,
            ),
            logType = LogType.REMOVE,
            logSource = LogSource.SERVER
        )
    }

    override suspend fun deleteAllProjects() {
        projectDao.deleteAll<Project>()
    }

    override suspend fun syncProjects() {

        data class ProjectAlive(
            var isAlive: Boolean,
            val project: Project,
        )

        withContext(ioDispatcher) {
            deleteAllProjects()
            val projects = projectFairApi.getProjects().data
            val oldProjects = projectDao.getAll<Project>().first().list
            val oldMap = mutableMapOf<Int, ProjectAlive>()
            oldProjects.forEach {
                oldMap[it.id] = ProjectAlive(false, it)
            }
            var current = 0f
            val overall = projects.size

            projects.forEach {
                val newProject = projectResponseToProject(it)
                val oldProject = oldMap[newProject.id]
                if (oldProject == null) {
                    insertProject(newProject)
                    downloadFlow.value = ++current / overall
                } else {
                    oldMap[newProject.id]!!.isAlive = true
                }
            }

            oldMap.filter { !it.value.isAlive }.forEach {
                deleteProject(it.value.project)
                downloadFlow.value = ++current / overall
            }
        }
    }

    override suspend fun rebaseProjects() {
        withContext(ioDispatcher) {
            val projects = projectFairApi.getProjects().data
            var current = 0f
            val overall = projects.size

            deleteAllProjects()
            projects.forEach {
                val newProject = projectResponseToProject(it)
                insertProject(newProject)
                downloadFlow.value = ++current / overall
            }
        }
    }
}