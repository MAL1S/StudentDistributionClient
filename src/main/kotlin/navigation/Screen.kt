package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    UploadDataScreen(
        label = "Upload data",
        icon = Icons.Filled.Add
    ),
    InfoScreen(
        label = "Info",
        icon = Icons.Filled.Info
    )
}