package navigation

import androidx.compose.runtime.Composable

class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().renderContents()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    ) {

        @Composable
        fun renderContents() {
            contents(this)
        }
    }
}

@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: ScreenRoute,
    bundle: Bundle? = null,
    content: @Composable () -> Unit
) {
    if (navController.currentScreen.value.sharedScreen.screenRoute == route) {
        content()
    }
}