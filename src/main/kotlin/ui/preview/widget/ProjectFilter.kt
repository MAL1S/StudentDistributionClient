package ui.preview.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.compose.ExposedDropdownMenu
import common.compose.rememberExposedMenuStateHolder
import domain.Department
import domain.model.Institute
import ui.filter.*

@Composable
fun ProjectFilterDialog(
    visible: Boolean,
    projectFilterConfiguration: ProjectFilterConfiguration,
    onApplyClicked: (ProjectFilterConfiguration) -> Unit,
    onDismissRequest: () -> Unit,
) {
    FilterDialog(
        visible = visible,
        filterContent = {
            Column {
                projectFilterConfiguration.filters.forEach { (filterType, filterValue) ->
                    val stateHolder = rememberExposedMenuStateHolder()
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = filterType.title,
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.size(16.dp))
                        ExposedDropdownMenu(
                            modifier = Modifier
                                .size(width = 200.dp, height = Dp.Unspecified),
                            title = filterValue.selectedValue.name,
                            isTitleChangeable = true,
                            stateHolder = stateHolder,
                            items = filterValue.values
                        ) { index, itemClicked ->
                            projectFilterConfiguration
                                .filters[filterType]!!
                                .selectedValue =
                                if (index == 0) FilterSelectedType.All
                                else FilterSelectedType.Selected(itemClicked)
                        }
                    }
                    Spacer(Modifier.size(32.dp))
                }
            }
        },
        onApplyClicked = {
            onApplyClicked(projectFilterConfiguration)
            onDismissRequest()
        },
        onDismissRequest = {
            onDismissRequest()
        }
    )
}

class ProjectFilterConfiguration(
    institutes: List<Institute>,
    departments: List<Department>,
) : FilterConfiguration {
    override val filters: MutableMap<FilterType, FilterValue> = mutableMapOf(
        FilterType.INSTITUTE to FilterValue(
            values = listOf(FILTER_ALL) + institutes.map { it.name },
            selectedValue = FilterSelectedType.All
        ),
        FilterType.DEPARTMENT to FilterValue(
            values = listOf(FILTER_ALL) + departments.map { it.name },
            selectedValue = FilterSelectedType.All
        ),
    )
}