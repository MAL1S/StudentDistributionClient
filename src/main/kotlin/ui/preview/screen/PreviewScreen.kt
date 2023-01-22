package ui.preview.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Student
import navigation.NavController
import ui.preview.widget.StudentTable

@Composable
fun PreviewScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(WhiteDark)
    ) {
        val groups = mapOf("ИСТб" to "ИСТб-1", "АСУб" to "АСУб-1")
        val students = mutableListOf<Student>()
        (0..35).forEach {
            val groupName = if (it < 15) "АСУб" else "ИСТб"
            val groupNumber = groups[groupName]!!
            students.add(
                Student(
                    id = it,
                    name = "Name $it",
                    group = groupNumber,
                    shouldDistribute = true,
                    specialtyId = 0
                )
            )
        }
        StudentTable(
            modifier = Modifier.padding(24.dp),
            students = students
        )
    }
}