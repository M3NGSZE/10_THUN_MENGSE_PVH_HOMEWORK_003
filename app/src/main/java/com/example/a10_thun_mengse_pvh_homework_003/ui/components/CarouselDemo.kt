package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import com.example.a10_thun_mengse_pvh_homework_003.R

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
data class CarouselItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int // Replace with your image resources
)

val imageSlide = listOf(
    CarouselItem(
        id = 1,
        title = "sentry",
        description = "sentry",
        imageRes = R.drawable.sentry
    ),
    CarouselItem(
        id = 2,
        title = "toji",
        description = "toji",
        imageRes = R.drawable.toji
    ),
    CarouselItem(
        id = 3,
        title = "sentry",
        description = "sentry",
        imageRes = R.drawable.aang
    )
)

@Composable
fun ImageCarousel(
    items: List<CarouselItem>,
    modifier: Modifier = Modifier,
    autoScroll: Boolean = true,
    autoScrollDuration: Long = 3000L
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val scope = rememberCoroutineScope()

    // Auto-scroll effect
    LaunchedEffect(autoScroll) {
        if (autoScroll) {
            while (true) {
                delay(autoScrollDuration)
                val nextPage = (pagerState.currentPage + 1) % items.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carousel pager
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
            )

            CarouselCard(
                item = items[page],
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                        )
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Page indicators
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(items.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}

@Composable
fun CarouselCard(
    item: CarouselItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image (replace with your image loading logic)
            // For demonstration, using a colored background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(
                            red = (item.id * 50) % 255,
                            green = (item.id * 80) % 255,
                            blue = (item.id * 120) % 255
                        ).copy(alpha = 0.7f)
                    )
            )

            // Content overlay
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
