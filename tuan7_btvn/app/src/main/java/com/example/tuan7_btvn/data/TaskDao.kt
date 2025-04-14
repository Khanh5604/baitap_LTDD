package com.example.tuan7_btvn.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tuan7_btvn.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)
}