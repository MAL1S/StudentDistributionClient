package ui.details.participation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import common.compose.rememberForeverLazyListState
import common.theme.BlueMainLight
import common.theme.GrayLight
import domain.model.Participation
import domain.model.Student
import kotlinx.coroutines.launch
import ui.preview.viewmodel.PreviewViewModel

private const val KEY = "PROJECT_PARTICIPATION"

@Composable
fun ParticipationTableItem(
    modifier: Modifier = Modifier,
    participation: Participation,
    student: Student?,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = student?.name ?: "Не найдено",
            modifier = Modifier
                .fillMaxWidth(0.6f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = participation.priority.toString(),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = student?.group ?: "Не найдено",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ParticipationTableHead(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(GrayLight)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "ФИО",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .wrapContentWidth()
        )
        Text(
            text = "Приоритет",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentWidth()
        )
        Text(
            text = "Группа",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
        )
    }
    Divider(thickness = 2.dp)
}

@Composable
fun ParticipationTable(
    modifier: Modifier = Modifier,
    participations: List<Participation>,
    previewViewModel: PreviewViewModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, BlueMainLight),
                RoundedCornerShape(10.dp)
            )
    ) {
        ParticipationTableHead(
            modifier = Modifier.fillMaxWidth()
        )

        val scrollState = rememberForeverLazyListState(KEY)
        val coroutineScope = rememberCoroutineScope()

        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            scrollState.scrollBy(-delta)
                        }
                    }
                ),

            ) {
            items(participations) { participation ->
                ParticipationTableItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    participation = participation,
                    student = previewViewModel.getStudentById(participation.studentId)
                )
                Divider()
            }
        }
    }
}
