package com.example.a10_thun_mengse_pvh_homework_003.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note): Long

    @Query("SELECT * FROM notes")
    fun selectAllNotes(): Flow<MutableList<Note>>
}