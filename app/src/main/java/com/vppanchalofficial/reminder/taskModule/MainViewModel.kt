package com.vppanchalofficial.reminder.taskModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun getTask(): LiveData<List<TaskModel>> {
        return taskRepository.getTask()
    }

    suspend fun insertTask(taskModel: TaskModel){
        taskRepository.insertTask(taskModel)
    }

    suspend fun updateTask(taskModel: TaskModel){
        taskRepository.updateTask(taskModel)
    }

    suspend fun deleteTask(taskModel: TaskModel){
        taskRepository.deleteTask(taskModel)
    }
}
