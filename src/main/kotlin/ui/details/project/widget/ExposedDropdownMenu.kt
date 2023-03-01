package ui.details.project.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import common.theme.BlueMainDark
import java.awt.FlowLayout

@Composable
fun ExposedDropdownMenu(
    stateHolder: ExposedDropdownMenuStateHolder,
) {
    Box {
        Row(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .border(
                    BorderStroke(2.dp, BlueMainDark),
                    RoundedCornerShape(10.dp)
                )
                .clickable {
                    stateHolder.onEnabled(!stateHolder.enabled)
                }
                .padding(12.dp)
        ) {
            Text(
                text = AnnotatedString("Выбрать преподавателя"),
                modifier = Modifier.onGloballyPositioned {
                    stateHolder.onSize(it.size.toSize())
                }
            )
            Icon(
                imageVector = stateHolder.icon,
                null
            )
        }
        DropdownMenu(
            expanded = stateHolder.enabled,
            onDismissRequest = {
                stateHolder.onEnabled(false)
            },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    stateHolder.size.width.toDp()
                }),
        ) {
            stateHolder.items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        stateHolder.onSelectedIndex(index)
                        stateHolder.onEnabled(false)
                    }
                ) {
                    Text(text = s)
                }
            }
        }
    }
}

class ExposedDropdownMenuStateHolder {
    var enabled by mutableStateOf(false)
    var value by mutableStateOf("")
    var selectedIndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)
    val icon: ImageVector
        @Composable get() = if (enabled) Icons.Default.KeyboardArrowUp
        else Icons.Default.KeyboardArrowDown
    val items = (1..5).map {
        "option $it"
    }

    fun onEnabled(newValue: Boolean) {
        enabled = newValue
    }

    fun onSelectedIndex(newValue: Int) {
        selectedIndex = newValue
        value = items[selectedIndex]
    }

    fun onSize(newValue: Size) {
        size = newValue
    }
}

@Composable
fun rememberExposedMenuStateHolder() = remember {
    ExposedDropdownMenuStateHolder()
}