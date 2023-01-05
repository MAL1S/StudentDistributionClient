package navigation

import androidx.compose.runtime.Composable
import di.AppComponent
import uploaddata.di.UploadDataComponent

@Composable
fun CustomNavigationHost(
    navController: NavController,
    appComponent: AppComponent
) {

    NavigationHost(navController) {
        composable(Screen.UploadDataScreen.name) {
            UploadDataComponent(navController, appComponent).render()
        }

        composable(Screen.InfoScreen.name) {
            UploadDataComponent(navController, appComponent).render()
        }
    }.build()
}