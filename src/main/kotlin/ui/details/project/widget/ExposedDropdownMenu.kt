package ui.details.project.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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

@Composable
fun ExposedDropdownMenuWithChips(
    stateHolder: ExposedDropdownMenuStateHolder,
    itemsState: SnapshotStateList<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ChipsVerticalGrid(itemsState)
        ExposedDropdownMenu(stateHolder, itemsState) { item ->
            if (!itemsState.contains(item)) {
                itemsState.add(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipsVerticalGrid(
    itemsState: SnapshotStateList<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsState.chunked(3).forEach { threeRow ->
            Row {
                threeRow.forEach { item ->
                    Chip(
                        onClick = {

                        }
                    ) {
                        Text(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ExposedDropdownMenu(
    stateHolder: ExposedDropdownMenuStateHolder,
    items: List<String>,
    onItemClicked: (String) -> Unit,
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
            stateHolder.setItems(items)
            stateHolder.items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        stateHolder.onEnabled(false)
                        onItemClicked(item)
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

class ExposedDropdownMenuStateHolder {
    var enabled by mutableStateOf(false)
        private set
    var size by mutableStateOf(Size.Zero)
        private set
    val icon: ImageVector
        @Composable get() = if (enabled) Icons.Default.KeyboardArrowUp
        else Icons.Default.KeyboardArrowDown
    var items: List<String> = emptyList()
        private set

    fun setItems(newItems: List<String>) {
        items = newItems
    }

    fun onEnabled(newValue: Boolean) {
        enabled = newValue
    }

    fun onSize(newValue: Size) {
        size = newValue
    }
}

@Composable
fun rememberExposedMenuStateHolder() = remember {
    ExposedDropdownMenuStateHolder()
}