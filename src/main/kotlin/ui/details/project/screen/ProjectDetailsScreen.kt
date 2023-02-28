package ui.details.project.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Project
import navigation.NavController
import ui.details.project.widget.BackButton
import ui.details.project.widget.EditableDescriptionField
import ui.details.project.widget.TitleField

@Composable
fun ProjectDetailsScreen(
    navController: NavController,
    project: Project,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .verticalScroll(ScrollState(0))
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            BackButton(navController = navController)
            Spacer(modifier = Modifier.size(16.dp))
            TitleField(title = project.title)
        }
        EditableDescriptionField(title = "Title", content = project.goal ?: "")
        EditableDescriptionField(title = "Customer", content = project.customer ?: "")
        EditableDescriptionField(title = "Product result", content = project.productResult)
        EditableDescriptionField(title = "Study result", content = project.studyResult)
        Spacer(modifier = Modifier.size(16.dp))
    }
}