package com.vppanchalofficial.reminder.TaskModule

import androidx.lifecycle.LiveData


class TaskRepository(private val taskDao: TaskDao){

    fun getTask(): LiveData<List<TaskModel>> {
        return taskDao.getTask()
    }

    suspend fun insertTask(task: TaskModel){
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: TaskModel){
        taskDao.updateTask(task)
    }
 suspend fun deleteTask(task: TaskModel){
        taskDao.deleteTask(task)
    }

}
