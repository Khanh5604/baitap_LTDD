package com.example.tuan7_btvn

import android.app.Application
import androidx.room.Room
import com.example.tuan7_btvn.data.AppDatabase

class MyApplication : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task_database"
        ).build()
    }
}