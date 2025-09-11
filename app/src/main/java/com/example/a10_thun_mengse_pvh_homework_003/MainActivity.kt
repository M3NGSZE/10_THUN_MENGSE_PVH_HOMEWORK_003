package com.example.a10_thun_mengse_pvh_homework_003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.a10_thun_mengse_pvh_homework_003.repository.NoteRepository
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.database.NoteDatabase
import com.example.a10_thun_mengse_pvh_homework_003.screen.HomeScreen
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModel
import com.example.a10_thun_mengse_pvh_homework_003.viewModel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = NoteDatabase.getDatabase(context = applicationContext)
        val noteDao = database.noteDao()
        val noteRepository = NoteRepository(noteDao)
        val noteViewModelFactory = NoteViewModelFactory(noteRepository)
        val noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
        setContent {
            HomeScreen(noteViewModel = noteViewModel)
        }
    }
}