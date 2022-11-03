package com.vppanchalofficial.reminder.TaskModule

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(taskModel: TaskModel)

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

    @Query("SELECT * FROM task ORDER BY taskStatus DESC")
    fun getTask(): LiveData<List<TaskModel>>
}