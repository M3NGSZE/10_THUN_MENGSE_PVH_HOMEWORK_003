package com.example.a10_thun_mengse_pvh_homework_003.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10_thun_mengse_pvh_homework_003.repository.NoteRepository
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository): ViewModel(){
    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }
}