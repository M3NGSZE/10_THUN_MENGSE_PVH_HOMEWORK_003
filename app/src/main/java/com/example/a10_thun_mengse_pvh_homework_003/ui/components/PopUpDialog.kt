package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PopUpDialog(){
    var title by remember { mutableStateOf("") }

//    AlertDialog(
//        onDismissRequest = {},
//        title = {},
//        modifier = TODO(),
//
//    )
//        Column (
//            modifier = Modifier
//                .clip(RoundedCornerShape(16.dp))
//                .height(250.dp)
//                .padding(16.dp)
//        ){
//            Text(
//                text = "Create New Note",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Medium
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            TextField(
//                value = title,
//                onValueChange = {it -> title = it},
//                label = {Text("Title")},
//            )
//        }
//    }

    AlertDialog(
        title = {
            Text(
                text = "Create New Note",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        text =  {
            TextField(
                value = title,
                onValueChange = {it -> title = it},
                label = {Text("Title")},
            )
        },
        onDismissRequest ={},
        confirmButton = {
            Text(
                text = "Save",
                modifier = Modifier.clickable{

                }
            )
        },

        dismissButton =  {Text("kl")},


    )
}