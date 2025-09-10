package com.example.a10_thun_mengse_pvh_homework_003.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.Topbar

@Composable
fun HomeScreen(){
    Scaffold (
        topBar = {
            Topbar()
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            Popup {

            }
        }
    }
}