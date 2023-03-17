package common.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import common.theme.BlueMainDark
import common.theme.BlueMainLight
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.TimesCircle

fun String.toShortName(): String {
    val name = this.split(" ")
    var shortName = "${name[0]} "
    (1..name.lastIndex).forEach {
        shortName += "${name[it][0].uppercase()}."
    }

    return shortName
}

@Composable
fun ExposedDropdownMenuWithChips(
    title: String,
    isTitleChangeable: Boolean,
    stateHolder: ExposedDropdownMenuStateHolder,
    itemsState: SnapshotStateList<String>,
    dropdownItems: List<String>,
) {
    BorderedTitledComposable(title = "Преподаватели") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ChipsVerticalGrid(itemsState)
            Spacer(Modifier.size(8.dp))
            ExposedDropdownMenu(
                title = title,
                isTitleChangeable = isTitleChangeable,
                stateHolder = stateHolder,
                items = dropdownItems
            ) { _, clickedItem ->
                if (!itemsState.contains(clickedItem)) {
                    itemsState.add(clickedItem)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
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
                        onClick = {},
                        enabled = false,
                        colors = ChipDefaults.chipColors(
                            backgroundColor = BlueMainLight,
                            contentColor = Color.White,
                            disabledBackgroundColor = BlueMainLight,
                            disabledContentColor = Color.White
                        ),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(item.toShortName())
                            Spacer(Modifier.size(8.dp, 1.dp))
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.TimesCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .onClick {
                                        itemsState.removeIf { str -> str == item }
                                    }
                            )
                        }
                    }
                    Spacer(Modifier.size(16.dp, 1.dp))
                }
            }
        }
    }
}

@Composable
fun ExposedDropdownMenu(
    modifier: Modifier = Modifier,
    title: String,
    isTitleChangeable: Boolean,
    stateHolder: ExposedDropdownMenuStateHolder,
    items: List<String>,
    onItemClicked: (Int, String) -> Unit,
) {
    var changeableTitle by remember { mutableStateOf(title) }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                stateHolder.onSize(it.size.toSize())
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
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
                text = AnnotatedString(changeableTitle.toShortName()),
                modifier = Modifier.fillMaxWidth(0.8f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = stateHolder.icon,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
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
                        onItemClicked(index, item)
                        if (isTitleChangeable) changeableTitle = item
                    }
                ) {
                    Text(text = item.toShortName())
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