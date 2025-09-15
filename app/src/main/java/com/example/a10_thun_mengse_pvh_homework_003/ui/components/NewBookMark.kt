package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.a10_thun_mengse_pvh_homework_003.R
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@Composable
fun NewBookMark(noteViewModel: NoteViewModel){

    val bookMarkNote by noteViewModel.getAllBookMark().collectAsState(initial = emptyList())
    val pagerState = rememberPagerState(pageCount = {bookMarkNote.size})

    LaunchedEffect(true) {
        if (true) {
            while (true) {
                delay(3000L)
                val nextPage = (pagerState.currentPage + 1) % bookMarkNote.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carousel pager
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 52.dp),
//            pageSpacing = 16.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
            )

            CardBookMark1(
                bookMark = bookMarkNote[page],
                noteViewModel = noteViewModel
            )

        }
    }

}

@Composable
fun CardBookMark1(bookMark: Note, noteViewModel: NoteViewModel) {

    val images = listOf(
        R.drawable.sentry,
        R.drawable.toji,
        R.drawable.aang,
        R.drawable.ic_launcher_foreground
    )

    val randomImage = remember(bookMark.id) { images.random() }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .width(400.dp)
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = randomImage),
                    contentDescription = bookMark.title,
                    modifier = Modifier.size(90.dp)
                )
            }
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = bookMark.title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    IconButton(
                        onClick = {
                            noteViewModel.removeBookMark(bookMark.id)
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
                    text = bookMark.content,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}