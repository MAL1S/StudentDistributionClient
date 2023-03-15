package ui.preview.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import domain.Department
import domain.model.Institute
import ui.filter.FilterConfiguration
import ui.filter.FilterDialog
import ui.filter.FilterType

@Composable
fun ProjectFilterDialog(
    visible: Boolean,
    projectFilterConfiguration: ProjectFilterConfiguration,
    onApplyClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    FilterDialog(
        visible = visible,
        filterContent = {
            Column {
                projectFilterConfiguration.filters.keys.forEach {
                    Text(it.name)
                }
            }
        },
        onApplyClicked = {

        },
        onDismissRequest = {
            onDismissRequest()
        }
    )
}

class ProjectFilterConfiguration(
    institutes: List<Institute>,
    departments: List<Department>
): FilterConfiguration {
    override val filters: Map<FilterType, List<Any>> = mapOf(
        FilterType.INSTITUTES to institutes,
        FilterType.DEPARTMENTS to departments
    )
}