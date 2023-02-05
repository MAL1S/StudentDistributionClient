package ui.preview.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation.NavController
import ui.preview.viewmodel.PreviewViewModel
import ui.preview.widget.StudentTable
import ui.preview.widget.TabHome
import ui.preview.widget.TabPage

@Composable
fun PreviewScreen(
    navController: NavController,
    previewViewModel: PreviewViewModel,
) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(WhiteDark)
//    ) {
//
    var tabPage by remember { mutableStateOf(TabPage.Students) }

    Scaffold(
        topBar = {
            TabHome(
                selectedTabIndex = tabPage.ordinal,
                onSelectedTab = {
                    tabPage = it
                    println(tabPage.ordinal)
                }
            )
        }
    ) {
        val students = previewViewModel.students.collectAsState()
        StudentTable(
            modifier = Modifier.padding(24.dp),
            students = students.value
        )
    }

//        val students = previewViewModel.students.collectAsState()
//
//        TabLayout(
//            listOf(
//                TabItem.Students(students.value),
//                TabItem.Projects(listOf()),
//            )
//        )
//
//        StudentTable(
//            modifier = Modifier.padding(24.dp),
//            students = students.value
//        )
}
