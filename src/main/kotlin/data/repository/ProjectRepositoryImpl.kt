package data.repository

import data.local.dao.ProjectDao
import data.mapper.projectResponseToResponse
import data.remote.api.AdminProjectFairApi
import domain.model.Project
import domain.model.Student
import domain.repository.ProjectRepository
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val projectDao: ProjectDao,
    private val adminProjectFairApi: AdminProjectFairApi
): ProjectRepository {

    override suspend fun uploadProjects() {
        withContext(ioDispatcher) {
            val projects = adminProjectFairApi.getProjects()

            projects.forEach {
                println(it.specialities)
                insertProject(
                    projectResponseToResponse(it)
                )
            }
        }
    }

    override fun getProjects(): Flow<ResultsChange<Project>> {
        return projectDao.getAll()
    }

    override suspend fun updateProject(project: Project) {
        projectDao.update(project)
    }

    override suspend fun insertProject(project: Project) {
        withContext(ioDispatcher) {
            projectDao.insert(project)
        }
    }

    override suspend fun insertProject(projects: List<Project>) {
        withContext(ioDispatcher) {
            projectDao.insert(projects)
        }
    }

    override suspend fun deleteProject(project: Project) {
        projectDao.delete<Student>(project)
    }

    override suspend fun deleteAllProjects() {
        projectDao.deleteAll<Project>()
    }
}