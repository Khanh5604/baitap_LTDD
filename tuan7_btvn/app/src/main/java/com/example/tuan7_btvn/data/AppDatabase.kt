package com.example.tuan7_btvn.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tuan7_btvn.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}