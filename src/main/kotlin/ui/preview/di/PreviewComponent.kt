package ui.preview.di

import androidx.compose.runtime.Composable
import di.AppComponent
import navigation.NavController
import ru.student.distribution.di.BaseComponent
import ui.preview.screen.PreviewScreen

class PreviewComponent(
    appComponent: AppComponent,
    private val navController: NavController
): BaseComponent {

    @Composable
    override fun render() {
        PreviewScreen(navController)
    }
}