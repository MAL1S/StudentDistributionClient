package ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.compose.VisibleDialog
import common.theme.BlueMainLight
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Filter
import domain.Department
import domain.model.Institute
import kotlin.reflect.KClass

enum class FilterType(
    name: String,
    type: KClass<out Any>,
) {
    INSTITUTES(name = "Институты", type = Institute::class),
    DEPARTMENTS(name = "Кафедры", type = Department::class)
}

interface FilterConfiguration {
    val filters: Map<FilterType, List<Any>>
}

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(BlueMainLight),
    ) {
        Icon(
            imageVector = FontAwesomeIcons.Solid.Filter,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .clickable {
                    onClicked()
                }
                .padding(8.dp)
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
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
                        onApplyClicked()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = BlueMainLight, contentColor = Color.White)
                ) {
                    Text("Применить")
                }
                Spacer(Modifier.size(16.dp))
                Button(
                    onClick = {
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = BlueMainLight, contentColor = Color.White)
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