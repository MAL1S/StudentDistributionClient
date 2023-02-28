package ui.details.project.screen

import androidx.compose.runtime.Composable
import domain.model.Project
import navigation.NavController
import ui.details.project.widget.EditableDescriptionField

@Composable
fun ProjectDetailsScreen(
    navController: NavController,
    project: Project
) {
    EditableDescriptionField(title = "Title", content = "${project.goal}")
}