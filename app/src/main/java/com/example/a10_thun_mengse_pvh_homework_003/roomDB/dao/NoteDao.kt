package com.example.a10_thun_mengse_pvh_homework_003.roomDB.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note): Long

    @Query("SELECT * FROM notes")
    fun selectAllNotes(): Flow<MutableList<Note>>

    @Query("SELECT * FROM notes WHERE mark = 1")
    fun selectAllBookMark(): Flow<MutableList<Note>>

    @Query("UPDATE notes SET mark = 1 WHERE id = :id")
    suspend fun updateBookMark(id: Long): Int

    @Query("UPDATE notes SET mark = 0 WHERE id = :id")
    suspend fun removeBookMark(id: Long): Int

    @Query("SELECT * FROM notes Where id = :id")
    fun selectById(id: Long): Flow<Note>


    // using this if we want to update specific field
    @Query("UPDATE NOTES SET title = :title, content = :content, date = :date Where id = :id")
    suspend fun updateById(id: Long, title: String, content: String, date: String): Int

    // using this if we pass the whole object
    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteById(notes: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Long)

}