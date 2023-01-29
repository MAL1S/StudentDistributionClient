package ui.preview.viewmodel

import domain.model.Student
import domain.usecase.student.GetStudentsUseCase
import domain.usecase.student.InsertStudentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PreviewViewModel @Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase,
    private val insertStudentUseCase: InsertStudentUseCase,
) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val students = MutableStateFlow<List<Student>>(emptyList())

    init {
        getStudents()
    }

    private fun getStudents() {
        coroutineScope.launch {
            println("LAUNCHED")
            getStudentsUseCase().collect {
                println("COLLECTED ${it.list}")
                students.value = it.list
            }
        }

        coroutineScope.launch {
            (1..10).forEach {
                delay(1000)
                println("added 1")
                insertStudentUseCase.invoke(
                    Student(
                        id = it*5,
                        name = "${it*5}",
                        group = "group",
                        shouldDistribute = true,
                        specialtyId = 123
                    )
                )
            }
        }
    }
}