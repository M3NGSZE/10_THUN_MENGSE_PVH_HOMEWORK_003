package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(isShow: () -> Unit){
    TopAppBar(
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.sze),
                    contentDescription = "sze",
                    modifier = Modifier.clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Text("Robert Reynolds")
                    Text("Szentry", fontSize = 15.sp)
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    isShow()
                } ,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0))
            ) {
                Icon(
                    imageVector = Icons.Rounded.AddCircleOutline,
                    contentDescription = "Add",
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            IconButton(
                onClick = {} ,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0))
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFFF5722),
                    modifier = Modifier.size(35.dp)
                )
            }
        }

    )
}