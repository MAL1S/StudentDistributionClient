package domain.usecase.student

import di.Review
import domain.model.Student
import domain.repository.StudentRepository
import javax.inject.Inject

class InsertStudentUseCase @Inject constructor(
    @Review private val studentRepository: StudentRepository
) {

    suspend operator fun invoke(student: List<Student>) {
        studentRepository.insertStudent(student)
    }
}