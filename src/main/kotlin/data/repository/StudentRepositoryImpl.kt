package data.repository

import common.date.getCurrentDateTime
import common.student.canParticipate
import data.local.dao.StudentDao
import data.mapper.studentResponseToStudent
import data.remote.api.OrdinaryProjectFairApi
import di.Preview
import domain.model.Log
import domain.model.LogSource
import domain.model.LogType
import domain.model.Student
import domain.repository.LoggingRepository
import domain.repository.StudentRepository
import io.realm.kotlin.ext.copyFromRealm
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.types.RealmAny
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val studentDao: StudentDao,
    private val projectFairApi: OrdinaryProjectFairApi,
    private val loggingRepository: LoggingRepository
) : StudentRepository {

    override val downloadFlow = MutableStateFlow(0f)

    override fun getStudents(): Flow<ResultsChange<Student>> {
        return studentDao.getAll()
    }

    override suspend fun updateStudent(student: Student) {
        //studentDao.update(student)
    }

    override suspend fun insertStudent(student: Student, byRebase: Boolean) {
        withContext(ioDispatcher) {
            studentDao.insert(student)
            if (!byRebase) {
                loggingRepository.saveLog(
                    log = Log(
                        id = UUID.randomUUID().toString(),
                        dateTime = getCurrentDateTime(),
                        student = student
                    ),
                    logType = LogType.SAVE,
                    logSource = LogSource.SERVER
                )
            }
        }
    }

    override suspend fun insertStudent(students: List<Student>) {
        withContext(ioDispatcher) {
            studentDao.insert(students)
        }
    }

    override suspend fun deleteStudent(student: Student) {
        studentDao.delete<Student>(student)
    }

    override suspend fun deleteAllStudents() {
        studentDao.deleteAll<Student>()
    }

    override suspend fun rebaseStudents() {
        withContext(ioDispatcher) {
            val students = projectFairApi.getCandidates().filter {
                it.specialty != null && it.canParticipate()
            }

            deleteAllStudents()
            val newStudents = students.map { studentResponseToStudent(it) }
            insertStudent(newStudents)
            downloadFlow.value = 1f
        }
    }

    override suspend fun syncStudents() {

        data class StudentAlive(
            var isAlive: Boolean,
            val student: Student
        )

        withContext(ioDispatcher) {
            val students = projectFairApi.getCandidates().filter {
                it.specialty != null
            }
            val oldStudents = studentDao.getAll<Student>().first().list
            val oldMap = mutableMapOf<Int, StudentAlive>()
            oldStudents.forEach {
                oldMap[it.numz] = StudentAlive(false, it)
            }
            var current = 0f
            val overall = students.size

            students.forEach { studentResponse ->
                if (studentResponse.specialty == null) return@forEach
                val newStudent = studentResponseToStudent(studentResponse)
                val oldStudent = oldMap[newStudent.numz]
                if (oldStudent == null) {
                    insertStudent(newStudent, false)
                } else {
                    oldMap[newStudent.numz]!!.isAlive = true
                }
                downloadFlow.value = ++current / overall
            }

            oldMap.filter { !it.value.isAlive }.forEach {
                deleteStudent(it.value.student)
            }
        }
    }
}