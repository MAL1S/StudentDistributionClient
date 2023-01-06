
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import di.AppComponent
import di.DaggerAppComponent
import navigation.CustomNavigationHost
import navigation.Screen
import navigation.SharedScreen
import navigation.rememberNavController

private val appComponent: AppComponent by lazy {
    DaggerAppComponent
        .factory()
        .create()
}

fun main(args: Array<String>) = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 800.dp, height = 600.dp),
        title = "Medium"
    ) {
//        rememberCoroutineScope().launch {
//            withContext(Dispatchers.IO) {
//                val response = ProjectFairClient.getClient().getProjects()
//                //println(response)
//            }
//        }

//        UploadDataScreen(
//            uploadDataViewModel = UploadDataViewModel(
//                syncDataUseCase = SyncDataUseCase(
//                    uploadDataRepository = UploadDataRepositoryImpl(
//                        ioDispatcher = Dispatchers.IO
//                    )
//                )
//            )
//        )

        //UploadDataComponent(appComponent).render()
        App()

//        UploadFileCard(
//            fileTypeName = "Students",
//            onPickFileClicked = {
//                openFileDialog(
//                    window = window,
//                    title = "SHIT",
//                    allowedExtensions = listOf(".xlsx", ".xls"),
//                    allowMultiSelection = false
//                )
//            }
//        )
    }
}

@Composable
fun App() {
    val navBarScreens = listOf(
        Screen(SharedScreen.UploadScreen),
        Screen(SharedScreen.InfoScreen)
    )
    val navController by rememberNavController(navBarScreens[0])
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                NavigationRail(
                    modifier = Modifier.align(Alignment.CenterVertically).fillMaxHeight()
                ) {
                    navBarScreens.forEach {
                        NavigationRailItem(
                            selected = currentScreen.sharedScreen.parentScreenRoute == it.sharedScreen.parentScreenRoute,
                            icon = {
                                Icon(
                                    imageVector = it.sharedScreen.icon,
                                    contentDescription = it.sharedScreen.title
                                )
                            },
                            label = {
                                Text(it.sharedScreen.title)
                            },
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.sharedScreen.screenRoute)
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    // This is how you can use
                    CustomNavigationHost(navController = navController, appComponent = appComponent)
                }
            }
        }
    }
}