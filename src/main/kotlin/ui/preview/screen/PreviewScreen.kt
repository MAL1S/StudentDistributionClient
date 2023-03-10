package ui.preview.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import navigation.NavController
import common.compose.RadioButtonGroupRow
import common.compose.Title
import ui.preview.viewmodel.PreviewViewModel
import ui.preview.widget.*
import ui.preview.widget.PreviewTabPage.Projects
import ui.preview.widget.PreviewTabPage.Students
import ui.preview.widget.StudentsTabPage.Enrolled
import ui.preview.widget.StudentsTabPage.Uncounted

@Composable
fun PreviewScreen(
    navController: NavController,
    previewViewModel: PreviewViewModel,
) {
    var previewTabPage by remember { mutableStateOf(Students) }
    var studentsTabPage by remember { mutableStateOf(Enrolled) }

    fun studentTabPageToIndex(): Int {
        return when (studentsTabPage) {
            Enrolled -> 0
            Uncounted -> 1
        }
    }

    val studentsWithParticipations = previewViewModel.studentsWithParticipations.collectAsState()
    val studentsWithoutParticipations = previewViewModel.studentsWithoutParticipations.collectAsState()
    val students = previewViewModel.students.collectAsState()
    val projects = previewViewModel.projects.collectAsState()

    var studentsToDisplay by remember { mutableStateOf(students) }

    Scaffold(
        topBar = {
            Row {
                TabHome(
                    modifier = Modifier.size(width = 400.dp, height = Dp.Unspecified),
                    selectedTabIndex = previewTabPage.ordinal,
                    values = PreviewTabPage.values().toList(),
                    onSelectedTab = {
                        previewTabPage = it as PreviewTabPage
                    }
                )

                Text(text = "${students.value.size}")

                if (previewTabPage == Students) {
                    RadioButtonGroupRow(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        titles = listOf(
                            Title(Enrolled.title, Enrolled.name),
                            Title(Uncounted.title, Uncounted.name)
                        ),
                        selected = studentTabPageToIndex()
                    ) {
                        studentsTabPage = StudentsTabPage.fromString(it.name!!)

                        studentsToDisplay = when (studentsTabPage) {
                            Enrolled -> {
                                studentsWithParticipations
                            }
                            Uncounted -> {
                                studentsWithoutParticipations
                            }
                        }
                    }
                }
            }
        },
    ) {
        when (previewTabPage) {
            Students -> {
                Spacer(Modifier.size(16.dp))
                StudentTable(
                    modifier = Modifier.padding(24.dp),
                    students = studentsToDisplay.value,
                    previewViewModel,
                    navController
                )
            }
            Projects -> {
                ProjectTable(
                    modifier = Modifier.padding(24.dp),
                    projects = projects.value,
                    previewViewModel,
                    navController
                )
            }
        }
    }
}
