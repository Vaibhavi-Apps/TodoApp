package com.vppanchalofficial.reminder

import androidx.lifecycle.LiveData


class TaskRepository(private val taskDao: TaskDao){

    fun getTask(): LiveData<List<TaskModel>> {
        return taskDao.getTask()
    }

    suspend fun insertTask(task: TaskModel){
        taskDao.insertTask(task)
    }

}
