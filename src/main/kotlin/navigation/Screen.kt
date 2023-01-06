package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

//enum class Screen(
//    val label: String,
//    val icon: ImageVector
//) {
//    UploadDataScreen(
//        label = "Upload data",
//        icon = Icons.Filled.Add
//    ),
//    InfoScreen(
//        label = "Info",
//        icon = Icons.Filled.Info
//    )
//}

interface ParametrizedScreen {
    fun withArgs(bundle: Bundle)
}

data class Screen(
    val sharedScreen: SharedScreen,
    val bundle: Bundle? = null,
)

enum class ScreenRoute {
    UPLOAD,
    INFO,
    DETAILS
}

sealed class SharedScreen(
    val screenRoute: ScreenRoute,
    val parentScreenRoute: ScreenRoute,
    val title: String,
    val icon: ImageVector,
) {
    object UploadScreen : SharedScreen(
        screenRoute = ScreenRoute.UPLOAD,
        parentScreenRoute = ScreenRoute.UPLOAD,
        title = "Загрузить данные",
        icon = Icons.Filled.Add
    )

    object InfoScreen : SharedScreen(
        screenRoute = ScreenRoute.INFO,
        parentScreenRoute = ScreenRoute.INFO,
        title = "Инфо",
        icon = Icons.Filled.Info
    )

    object DetailsScreen : SharedScreen(
        screenRoute = ScreenRoute.DETAILS,
        parentScreenRoute = ScreenRoute.UPLOAD,
        title = "Детали",
        icon = Icons.Filled.Edit
    )

    companion object {
        fun findByRoute(route: ScreenRoute): SharedScreen {
            return when (route) {
                ScreenRoute.UPLOAD -> UploadScreen
                ScreenRoute.INFO -> InfoScreen
                ScreenRoute.DETAILS -> DetailsScreen
            }
        }
    }
}

//sealed class SharedScreen(val name: String, open val bundle: Bundle? = null) : ParametrizedScreen {
//
//    object Upload : SharedScreen("upload") {
//        //        override fun withArgs(vararg args: Any): Bundle = withArgs(args) { id, filter ->
////            val bundle = Bundle()
////            bundle.put("id", id)
////            bundle.put("filter", filter)
////            bundle
////        }
////
////        private fun Upload.withArgs(vararg args: Any, block: (id: Int, filter: String) -> Bundle): Bundle {
////            val id = args[0] as Int
////            val filter = args[1] as String
////            return block(id, filter)
////        }
//
//        override var bundle: Bundle? = null
//
//        override fun withArgs(bundle: Bundle) {
//            this@Upload.bundle = bundle
//        }
//    }
//
//    object Info : SharedScreen("info") {
//        //        override fun withArgs(vararg args: Any): Bundle = withArgs(args) { id, filter ->
////            val bundle = Bundle()
////            bundle.put("id", id)
////            bundle.put("filter", filter)
////            bundle
////        }
////
////        private fun Info.withArgs(vararg args: Any, block: (id: Int, filter: String?) -> Bundle): Bundle {
////            val id = args[0] as Int
////            val filter = args[1] as String?
////            return block(id, filter)
////        }
////        override fun withArgs(filter: String): Bundle {
////            return Bundle().apply {
////                 put("filter", filter)
////            }
////        }
//        override var bundle: Bundle? = null
//
//        override fun withArgs(bundle: Bundle) {
//            this@Info.bundle = bundle
//        }
//    }
//}