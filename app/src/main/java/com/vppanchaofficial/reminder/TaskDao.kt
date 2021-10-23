package com.vppanchaofficial.reminder

import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(taskModel: TaskModel)

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

    @Query("SELECT * FROM task")
    suspend fun getTask(): ArrayList<TaskModel>
}