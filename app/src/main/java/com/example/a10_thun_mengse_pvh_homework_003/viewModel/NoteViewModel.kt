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

    fun getAllNotes() = noteRepository.getAllNotes()

    fun getAllBookMark() = noteRepository.getAllBookMark()

    fun addBookMark(id: Long) = viewModelScope.launch{
        noteRepository.addBookMark(id)
    }

    fun removeBookMark(id: Long) = viewModelScope.launch {
        noteRepository.removeBookMark(id)
    }

    fun getNoteBId(id: Long) = noteRepository.getNoteById(id)

    fun updateById(id: Long, title: String, content: String, date: String) = viewModelScope.launch {
        noteRepository.updateById(id, title, content, date)
    }

    fun updateNoe(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun deleteById(id: Long) = viewModelScope.launch {
        noteRepository.deleteById(id)
    }
}