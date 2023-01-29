package domain.usecase.student

import data.repository.StudentRepositoryImpl
import domain.model.Student
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentsUseCase @Inject constructor(
    private val studentRepositoryImpl: StudentRepositoryImpl
) {

    operator fun invoke(): Flow<ResultsChange<Student>> = studentRepositoryImpl.getStudents()
}

class InsertStudentUseCase @Inject constructor(
    private val studentRepositoryImpl: StudentRepositoryImpl
) {

    suspend operator fun invoke(student: Student) {
        studentRepositoryImpl.insertStudent(student)
    }
}