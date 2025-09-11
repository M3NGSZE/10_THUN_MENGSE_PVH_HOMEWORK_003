package com.example.a10_thun_mengse_pvh_homework_003.roomDB.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val note: String,
    val date: LocalDate,
    val mark: Boolean = false
)
