package ui.uploaddata.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.compose.VisibleDialog
import ui.uploaddata.viewmodel.DownloadType

@Composable
fun DownloadProgressDialog(
    visible: Boolean,
    prgs: Float,
    progressBars: Map<DownloadType, Float>,
    onDismissRequest: () -> Unit,
) {
    VisibleDialog(
        visible = visible,
        textPart = {
            Column {
                progressBars.forEach { progressItem ->
                    Row {
                        Text(progressItem.key.name)
                        Spacer(Modifier.size(width = 8.dp, height = 1.dp))
                        DownloadProgressBar(progressItem.value)
                        Spacer(Modifier.size(width = 8.dp, height = 1.dp))
                        Text("${progressItem.value*100}%")
                    }
                }
            }
        },
        buttonsPart = {

        },
        onDismissRequest = {
            onDismissRequest()
        }
    )
}

@Composable
private fun DownloadProgressBar(
    progress: Float,
) {
    LinearProgressIndicator(progress = progress)
}

data class ProgressItem(
    val title: String,
    var progress: Float,
)