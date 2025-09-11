package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.R
import com.example.a10_thun_mengse_pvh_homework_003.helper.dateConverter
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookMark(){

    val currentDate = dateConverter()

    val bookMarkNote = remember {

        mutableStateListOf(
            Note(
                id = 1L,
                title = "Sentry Project",
                content = "The Sentry and The Void are respectively a superhero and supervillain appearing in American comic books published by Marvel Comics. Created by Paul Jenkins and Jae Lee, with uncredited conceptual contributions by Rick Veitch, the characters first appeared in The Sentry #1.",
                date = currentDate
            ),
            Note(
                id = 2L,
                title = "Man of tomorrow",
                content = "Superman is a superhero created by writer Jerry Siegel and artist Joe Shuster, first appearing in issue #1 of Action Comics, published in the United States on April 18, 1938.",
                date = currentDate
            ),
        )
    }

    LazyRow {
        items(bookMarkNote, key = {it -> it.id}){
            item -> CardBookMark(item)
        }
    }
}

@Composable
fun CardBookMark(bookMark: Note){
    Card (
        modifier = Modifier
            .padding(16.dp)
            .width(400.dp)
            .height(150.dp)



    ){
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp)
        ){
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.sentry),
                    contentDescription = bookMark.title,
                    modifier = Modifier.size(90.dp)
                )
            }
            Column (

            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = bookMark.title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Bookmarks,
                            contentDescription = "bookmark",
                            tint = Color(0xFFFF5722),
                            )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))



                Text(
                    text = "${bookMark.content}...",
                    maxLines = 3
                )
            }
        }
    }
}