package com.example.a10_thun_mengse_pvh_homework_003.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a10_thun_mengse_pvh_homework_003.helper.dateConverter
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel

@Composable
fun PopUpDialog(noteViewModel: NoteViewModel, isShow: (Boolean) -> Unit){

    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val isSave = !(title.isBlank() || note.isBlank())
    val date = dateConverter()

    val newNote = Note(title = title, content = note, date = date)

    AlertDialog(
        onDismissRequest = {
            isShow(false)
        },
        title = {
            Text(
                text = "Create New Note",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        text =  {
            Column {
                Column {
                    MyTextField("Title"){ title = it}

                    Spacer(modifier = Modifier.height(15.dp))

                    MyTextField("Content"){ note = it}
                }
            }
        },
        confirmButton = {
            MyButton(
                title ="Save",
                onOff = isSave,
                textColor = 0xFF3F51B5,
                isShow = {
                    isShow(it)
                    noteViewModel.insertNote(newNote)
                }
                ){

            }
        },

        dismissButton =  {
            MyButton(
                title ="Cancel",
                textColor = 0xFF3F51B5,
                isShow = { isShow(it) }
            ){  }
        },
    )
}


@Composable
fun MyTextField(
    title: String,
    setValue: (String) -> Unit
){
    var value by remember { mutableStateOf("") }
    setValue(value.trim())
    OutlinedTextField(
        value = value,
        onValueChange = {it -> value = it},
        label = { Text(text = title) },
    )
}