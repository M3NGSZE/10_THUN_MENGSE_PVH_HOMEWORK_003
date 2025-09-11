package com.example.a10_thun_mengse_pvh_homework_003.repository

import com.example.a10_thun_mengse_pvh_homework_003.roomDB.dao.NoteDao
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }
    fun getAllNotes(): Flow<MutableList<Note>> = noteDao.selectAllNotes()
    fun getAllBookMark(): Flow<MutableList<Note>> = noteDao.selectAllBookMark()
}