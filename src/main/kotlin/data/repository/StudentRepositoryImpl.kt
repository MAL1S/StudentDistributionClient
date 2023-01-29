package data.repository

import data.local.dao.StudentDao
import domain.model.Student
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val studentDao: StudentDao
) {

    fun getStudents(): Flow<ResultsChange<Student>> {
        return studentDao.getAll()
    }

    suspend fun insertStudent(student: Student) {
        withContext(ioDispatcher) {
            studentDao.insert(student)
        }
    }
}