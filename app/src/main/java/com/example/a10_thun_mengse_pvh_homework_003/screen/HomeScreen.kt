package com.example.a10_thun_mengse_pvh_homework_003.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Popup
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.PopUpDialog
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.PopUpPop
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.Topbar

@Composable
fun HomeScreen(){

    var title by remember { mutableStateOf("") }
    var show = true


    Scaffold (
        topBar = {
            Topbar()
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
//                .background(Color.Red)
        ){

            if (show){
                PopUpDialog()
            }

//            TextField(
//                value = title,
//                onValueChange = {it -> title = it},
//                label = {Text("Title")},
//            )

        }
    }
}