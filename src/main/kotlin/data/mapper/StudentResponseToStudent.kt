package data.mapper

import data.dto.StudentResponse
import domain.model.Student

fun studentResponseToStudent(student: StudentResponse): Student {
    return Student(
        id = student.numz.toInt(),
        name = student.fio,
        group = student.trainingGroup,
        shouldDistribute = true,
        specialtyId = 0
    )
    //TODO: set specialtyId
}