package ui.details.project.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.theme.BlueMainDark
import common.theme.BlueMainLight

@Composable
fun EditableSearchField(
    modifier: Modifier = Modifier,
    text: String,
    content: String,
    onDataChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier.border(
            border = BorderStroke(2.dp, BlueMainDark),
            shape = RoundedCornerShape(14.dp)
        ),
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        value = text,
        onValueChange = {
            onDataChanged(it)
        },
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = BlueMainLight,
        ),
       placeholder = {
           Text(text = content)
       },
        colors = TextFieldDefaults.textFieldColors(
            textColor = BlueMainLight,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "",
                tint = BlueMainDark
            )
        }
    )
}