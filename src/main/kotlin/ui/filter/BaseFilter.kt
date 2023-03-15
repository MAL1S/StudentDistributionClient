package ui.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.compose.VisibleDialog
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Filter
import domain.Department
import domain.model.Institute
import domain.model.Participation
import domain.model.Project
import domain.model.Student
import kotlin.reflect.KClass

enum class FilterType(type: KClass<out Any>) {
    STUDENTS(Student::class),
    PROJECTS(Project::class),
    PARTICIPATIONS(Participation::class),
    INSTITUTES(Institute::class),
    DEPARTMENTS(Department::class)
}

interface FilterConfiguration {
    val filters: Map<FilterType, List<Any>>
}

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    filterConfiguration: FilterConfiguration,
    onClicked: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            imageVector = FontAwesomeIcons.Solid.Filter,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onClicked()
                }
        )
    }
}

@Composable
fun FilterDialog(
    visible: Boolean,
    filterContent: @Composable () -> Unit,
    onApplyClicked: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    VisibleDialog(
        visible = visible,
        textPart = {
            filterContent()
        },
        buttonsPart = {
            Row {
                Button(
                    onClick = {
                        onApplyClicked()
                    }
                ) {
                    Text("Применить")
                }
                Button(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Закрыть")
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        }
    )
}