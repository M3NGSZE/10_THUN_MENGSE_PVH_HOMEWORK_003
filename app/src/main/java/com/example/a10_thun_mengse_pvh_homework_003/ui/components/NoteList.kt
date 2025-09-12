package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel

@Composable
fun NoteList(noteViewModel: NoteViewModel, ){

    val noted by noteViewModel.getAllNotes().collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        items(noted, key = {it -> it.id}){
            it -> ColumnNote(it, noteViewModel)
        }

//        item {
//            MyBottomSheet(noteViewModel)
//        }
    }
}

@Composable
fun ColumnNote(item: Note, noteViewModel: NoteViewModel){

    val markTint = if (item.mark) Color(0xFFFD5722) else Color(0xFF1D1B20)
    var isShow by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    var isDelete by remember { mutableStateOf(false) }

    if (isDeleting) {
        return
    }

    Card (
        modifier = Modifier
            .padding(10.dp)
            .clickable{
                isShow = !isShow
            }
    ){
        Column (
            modifier = Modifier
                .padding(16.dp)
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )

                IconButton(
                    onClick = {
                        if (item.mark){
                            noteViewModel.removeBookMark(item.id)
                        }else{
                            noteViewModel.addBookMark(item.id)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Bookmarks,
                        contentDescription = "Bookmark",
                        tint = markTint
                        )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.content,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.date,
            )
        }
    }

//    var isDelete by remember {mutableStateOf(false)}
//    var delNoteId by remember {mutableStateOf(0L)}

//    MyBottomSheet(noteViewModel, isShow, item.id, {isDeleting = it}){isShow = it}

    MyBottomSheet(
        noteViewModel = noteViewModel,
        isShow = isShow,
        noteId = item.id,
//        getIsDelete = { shouldDelete ->
//            if (shouldDelete) {
//                isDeleting = true
//                noteViewModel.deleteById(item.id)
//            }
        getIsDelete = {isDeleting = it
        },
        getIsShow = { showState ->
            isShow = showState
        }
    )

//    Log.d("delNoteId1","$delNoteId")
//
    if(isDelete){
        Log.d("jinhot","if it true this one is open")
        noteViewModel.deleteById(item.id)
    }

    isDelete = false
//    LaunchedEffect (isDelete) {
//        if (isDelete) {
//            noteViewModel.deleteById(item.id)
//            // Don't reset isDelete here - let the recomposition handle it
//        }
//    }

}