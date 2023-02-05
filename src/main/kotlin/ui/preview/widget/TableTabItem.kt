package ui.preview.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import common.theme.BlueMainLight

@Composable
fun TabItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: ImageVector,
    title: String,
    colorSelected: Color,
    colorUnselected: Color,
    onClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .selectable(
                selected = selected,
            ) {
                println("clicked")
            }
            .background(
                color = if (selected) colorUnselected else colorSelected
            )
            .border(
                BorderStroke(2.dp, BlueMainLight),
                RoundedCornerShape(10.dp)
            )
            .clickable {
                onClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.size(4.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) colorSelected else colorUnselected,
            modifier = Modifier.size(60.dp, 60.dp)
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = title,
            color = if (selected) colorSelected else colorUnselected,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.size(4.dp))
    }
}


@Composable
fun TabLayout(
    tabs: List<TabItem>,
    //onPageSelected: ((tabItem: TabItem) -> Unit)
) {
    var selected = remember { 0 }

    Row {
        tabs.forEachIndexed { index, tabItem ->
            TabItem(
                selected = index == selected,
                modifier = Modifier.size(16.dp),
                icon = tabItem.icon,
                title = tabItem.title,
                colorSelected = Color.Blue,
                colorUnselected = Color.Red,
                onClicked = {
                    selected = index
                }
            )
        }
    }
}

@Composable
fun TabHome(
    selectedTabIndex: Int,
    onSelectedTab: (TabPage) -> Unit,
) {
    TabRow(
        modifier = Modifier.padding(horizontal = 100.dp),
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        divider = {},
        indicator = {}
    ) {
        TabPage.values().forEachIndexed { index, tabPage ->
            TabItem(
                selected = index == selectedTabIndex,
                icon = tabPage.icon,
                title = "AAAA",
                colorSelected = Color.White,
                colorUnselected = BlueMainLight,
                onClicked = {
                    onSelectedTab(tabPage)
                }
            )
        }
    }
}

enum class TabPage(val icon: ImageVector) {
    Students(Icons.Default.Email),
    Projects(Icons.Default.Info),
}

sealed class TabItem(
    val index: Int,
    val icon: ImageVector,
    val title: String,
    val screenToLoad: @Composable () -> Unit,
) {
//    class Students(students: List<Student>) : TabItem(0, Icons.Default.Email, "Студенты", {
//        StudentTable(students = students)
//    })
//
//    class Projects(projects: List<Project>) : TabItem(0, Icons.Default.Email, "Студенты", {
//        ProjectTable(projects = projects)
//    })

    object Students : TabItem(0, Icons.Default.Email, "Студенты", {
        StudentTable(students = listOf())
    })

    object Projects : TabItem(0, Icons.Default.Email, "Студенты", {
        ProjectTable(projects = listOf())
    })
}