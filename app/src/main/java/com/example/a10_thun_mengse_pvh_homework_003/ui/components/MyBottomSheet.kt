package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.helper.dateConverter
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    noteViewModel: NoteViewModel,
    isShow: Boolean,
    noteId: Long,
    getIsDelete:(Boolean) -> Unit,
    getIsShow: (Boolean) -> Unit
){
    var showSheet = isShow
    val sheetState = rememberModalBottomSheetState()

    val noteById by noteViewModel.getNoteBId(noteId).collectAsState(initial = null)

    /*
    or we can use this
    noteState?.let { note ->
        Text(text = note.title)
        Text(text = note.note)
        Text(text = note.date.toString())
    }
     */

    var edit by remember { mutableStateOf(false) }

    var isDelete by remember {mutableStateOf(false)}
//    var delNoteId by remember {mutableStateOf(0L)}

    if (showSheet){
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
                getIsShow(false)
            },
            sheetState = sheetState
        ) {
            if (noteById != null){

                var editTitle by remember { mutableStateOf(noteById?.title ?: "") }
                var editContent by remember { mutableStateOf(noteById?.content ?: "") }
                val passNoted = noteById?.let { current ->
                    Note(noteId, editTitle, editContent, dateConverter(), current.mark)
                }


                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    if (!edit){
                        IconButton(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0x583F51B5)),
                            onClick = {
                                edit = !edit
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
                            text = noteById?.title ?: "",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Medium
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = noteById?.date ?: "",
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = noteById?.content ?: "",
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                        )
                    }else{

                        var deleteAlert by remember { mutableStateOf(false) }

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IconButton(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFFD5722)),
                                onClick = {
                                    deleteAlert = !deleteAlert
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = "delete",
                                    tint = Color.White
                                )
                            }

                            if (deleteAlert){
                                AskDelete(
                                    getDeleteAlert = {deleteAlert = it},
                                    getIsDelete = { it -> getIsDelete(it)}
                                )
                            }

                            IconButton(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0x583F51B5)),
                                onClick = {
                                    edit = !edit
//                                    noteViewModel.updateById(noteId, editTitle, editContent,
//                                        dateConverter()
//                                    )
//                                    noteViewModel.updateNoe(Note(noteId, editTitle, editContent, dateConverter(), noteById!!.mark))
                                    passNoted?.let { safeNote ->
                                        noteViewModel.updateNoe(safeNote)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Edit,
                                    contentDescription = "edit",
                                    tint = Color.Blue
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = editTitle,
                            onValueChange = {editTitle = it},
                            label = {Text("Tittle")},
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = editContent,
                            onValueChange = {editContent = it},
                            label = {Text("Content")},
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }
    }

}

@Composable
fun AskDelete(
    getDeleteAlert: (Boolean) -> Unit,
    getIsDelete: (Boolean) -> Unit
){
    AlertDialog(
        onDismissRequest = {},
        icon = {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "",
                tint = Color(0xFFFD5722),
                modifier = Modifier.size(60.dp)
            )
        },
        text = {
            Text(
                text = "Are you sure, you want to delete this",
                fontSize = 16.sp
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    getIsDelete(true)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFD5722)
                )
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    getDeleteAlert(false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F51B5)
                )
            ) {
                Text(text = "Cancel")
            }
        }
    )
}