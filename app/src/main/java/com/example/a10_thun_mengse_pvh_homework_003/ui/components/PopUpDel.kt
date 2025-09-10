package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PopUpDel(isShow: (Boolean) -> Unit){

    AlertDialog(
        modifier = Modifier.width(330.dp),
        onDismissRequest = { isShow(false) },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = "",
                tint = Color(0xFFFF5722),
            )
        },
        title = {
            Text(
                text = "Delete All Notes?",
            )
        },
        text = {
            Text(
                text = "This will permanently delete all of your notes. " +
                        "This action can not be undo.",
                textAlign = TextAlign.Center,
            )
        },
        confirmButton = {
            MyButton(
                title ="Save",
                textColor = 0xFFFF5722,
                isShow = {  isShow(it) }
            ){

            }
        },
        dismissButton = {
            MyButton(
                title ="Cancel",
                textColor = 0xFF3F51B5,
                isShow = { isShow(it) }
            ){  }
        }

    )
}