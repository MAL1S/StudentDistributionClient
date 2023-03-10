package navigation

import androidx.compose.runtime.Composable
import di.AppComponent
import ui.details.project.di.ProjectDetailsComponent
import ui.distribution_algorithm.di.AlgorithmComponent
import ui.preview.di.PreviewComponent
import ui.uploaddata.di.UploadDataComponent

@Composable
fun CustomNavigationHost(
    navController: NavController,
    appComponent: AppComponent
) {

    NavigationHost(navController) {
        composable(SharedScreen.UploadScreen.screenRoute) {
            UploadDataComponent(navController = navController, appComponent = appComponent).render()
        }

        composable(SharedScreen.AlgorithmScreen.screenRoute) {
            AlgorithmComponent(navController = navController, appComponent = appComponent).render()
        }

        composable(SharedScreen.PreviewScreen.screenRoute) {
            PreviewComponent(navController = navController, appComponent = appComponent).render()
        }

        composable(SharedScreen.ProjectDetailsScreen.screenRoute) {
            ProjectDetailsComponent(
                navController = navController,
                appComponent = appComponent
            ).render()
        }
    }.build()
}