package ui.details.project.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.theme.BlueMainDark
import common.theme.BlueMainLight

@Composable
fun EditableDescriptionField(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    var contentText by rememberSaveable(TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(content))
    }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            color = BlueMainDark,
        )
        Spacer(Modifier.size(8.dp))
        Box(
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, BlueMainDark),
                    RoundedCornerShape(10.dp)
                )
        ) {
            BasicTextField(
                value = contentText,
                onValueChange = {
                    contentText = it
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = BlueMainLight,
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.padding(12.dp)) {
                        innerTextField()
                    }
                }
            )
        }
    }
}