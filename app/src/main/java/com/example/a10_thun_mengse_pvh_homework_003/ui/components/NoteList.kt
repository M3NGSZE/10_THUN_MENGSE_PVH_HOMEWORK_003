package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.helper.dateConverter
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel
import java.time.LocalDate

@Composable
fun NoteList(noteViewModel: NoteViewModel){

    val noted by noteViewModel.getAllNotes().collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        items(noted, key = {it -> it.id}){
            it -> ColumnNote(it)
        }
    }
}

@Composable
fun ColumnNote(item: Note){
    Card (
        modifier = Modifier
            .padding(10.dp)
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
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Bookmarks,
                        contentDescription = "Bookmark",

                        )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = item.content,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${item.date}",
            )
        }
    }

}