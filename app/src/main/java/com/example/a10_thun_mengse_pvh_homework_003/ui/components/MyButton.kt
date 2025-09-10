package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    title: String,
    onOff: Boolean = true,
    textColor: Long,
    isShow: (Boolean) -> Unit,
    saveDel: () -> Unit
){
    Button(
        modifier = Modifier
            .width(95.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(textColor),
            disabledContainerColor = Color.Transparent
        ),
        enabled = onOff,
        onClick = {
            if (title == "Cancel"){
                isShow(false)
            }else {
                isShow(false)
                saveDel()
            }
        }
    ) {
        Text(text = title)
    }
}