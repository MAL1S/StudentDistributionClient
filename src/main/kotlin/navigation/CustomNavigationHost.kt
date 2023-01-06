package navigation

import androidx.compose.runtime.Composable
import di.AppComponent
import uploaddata.DetailsScreen
import uploaddata.di.UploadDataComponent

@Composable
fun CustomNavigationHost(
    navController: NavController,
    appComponent: AppComponent
) {

    NavigationHost(navController) {
//        composable(Screen.UploadDataScreen.name) {
//            UploadDataComponent(navController, appComponent).render()
//        }
//
//        composable(Screen.InfoScreen.name) {
//            UploadDataComponent(navController, appComponent).render()
//        }
        composable(SharedScreen.UploadScreen.screenRoute) {
            UploadDataComponent(navController = navController, appComponent = appComponent).render()
        }

        composable(SharedScreen.InfoScreen.screenRoute) {
            UploadDataComponent(navController = navController, appComponent = appComponent).render()
        }

        composable(SharedScreen.DetailsScreen.screenRoute) {
            DetailsScreen(navController)
        }
    }.build()
}