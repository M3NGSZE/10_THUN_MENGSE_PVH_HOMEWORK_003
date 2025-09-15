package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.a10_thun_mengse_pvh_homework_003.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    isShow: () -> Unit,
    another: () -> Unit,
){
    TopAppBar(
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                verticalAlignment = Alignment.CenterVertically
            ){

                var profile by remember { mutableStateOf<Uri?>(null) }
                val context = LocalContext.current

                val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

                // Save profile data
                fun saveProfile(uri: Uri?) {
                    prefs.edit()
                        .putBoolean("has_profile", uri != null)
                        .putString("profile_image_uri", uri?.toString())
                        .apply()
                }

                // Load profile data
                fun loadProfile(): Uri? {
                    val uriString = prefs.getString("profile_image_uri", null)
                    return uriString?.let { Uri.parse(it) }
                }

                // Load flag
                fun hasProfile(): Boolean {
                    return prefs.getBoolean("has_profile", false) // default false
                }


                val hasProfile = hasProfile()
                val imagePicker = rememberLauncherForActivityResult (
                    contract = ActivityResultContracts.GetContent()
                ) {
                    uri: Uri? ->
                    profile = uri
                    saveProfile(uri)
                }

                // On first load (restore profile)
                if (profile == null && prefs.getBoolean("has_profile", false)) {
                    profile = loadProfile()
                }

                Image(
                    painter = if (profile == null) painterResource(id = R.drawable.sentry) else rememberAsyncImagePainter(profile),
                    contentDescription = "sze",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable{
                            imagePicker.launch("image/*")
                        }
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
                onClick = {
                    another()
                    Log.d("trash","$isShow")
                } ,
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