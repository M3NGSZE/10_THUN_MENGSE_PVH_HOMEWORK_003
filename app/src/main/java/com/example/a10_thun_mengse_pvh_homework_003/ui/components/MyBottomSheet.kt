package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(noteViewModel: NoteViewModel, isShow: Boolean){
    var showSheet = isShow
    val sheetState = rememberModalBottomSheetState()

    val noteById by noteViewModel.getNoteBId(3L).collectAsState(initial = null)

    /*
    or we can use this
    noteState?.let { note ->
        Text(text = note.title)
        Text(text = note.note)
        Text(text = note.date.toString())
    }
     */

    if (showSheet){
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
            },
            sheetState = sheetState
        ) {
            if (noteById != null){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0x583F51B5)),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = "edit",
                            tint = Color.Blue
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = noteById?.title?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = noteById!!.date,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = noteById!!.content,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }


}