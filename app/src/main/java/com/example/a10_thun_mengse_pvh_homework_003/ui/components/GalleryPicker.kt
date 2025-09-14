package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun GalleryPicker() {
    val context = LocalContext.current

    // Launcher for picking image from gallery
    val galleryLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Handle the selected image URI
            Toast.makeText(context, "Image selected: $uri", Toast.LENGTH_SHORT).show()
        }
    }

    // Button to open gallery
    Button (onClick = {
        galleryLauncher.launch("image/*") // mime type: only images
    }) {
        Text(text = "Pick Image from Gallery")
    }
}
