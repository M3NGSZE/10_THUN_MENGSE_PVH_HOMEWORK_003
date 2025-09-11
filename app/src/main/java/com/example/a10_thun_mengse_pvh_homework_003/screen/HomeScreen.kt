package com.example.a10_thun_mengse_pvh_homework_003.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.BookMark
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.NoteList
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.PopUpDel
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.PopUpDialog
import com.example.a10_thun_mengse_pvh_homework_003.ui.components.Topbar
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel

@Composable
fun HomeScreen(noteViewModel: NoteViewModel){

    var title by remember { mutableStateOf("") }
    var show by remember {mutableStateOf(false)}
    var showDel by remember {mutableStateOf(false)}

    Scaffold (
        topBar = {
            Topbar(
                isShow = { show = true },
                another = { showDel = true }
            )
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

            if (show){
                PopUpDialog(noteViewModel){ show = it}
            }

            if (showDel){
                PopUpDel{showDel = it}
            }

            BookMark(noteViewModel)

            NoteList(noteViewModel)


        }
    }
}