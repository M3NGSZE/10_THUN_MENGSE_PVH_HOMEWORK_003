package com.example.a10_thun_mengse_pvh_homework_003.roomDB.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.dao.NoteDao
import com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity.Note


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract  fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            return instance ?: synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}