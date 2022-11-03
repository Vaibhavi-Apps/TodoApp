package com.vppanchalofficial.reminder.TaskModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vppanchalofficial.reminder.TaskModule.TaskModel
import com.vppanchalofficial.reminder.TaskModule.TaskRepository

class mainViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun getTask(): LiveData<List<TaskModel>> {
        return taskRepository.getTask()
    }

    suspend fun insertTask(taskModel: TaskModel){
        return taskRepository.insertTask(taskModel)
    }
    suspend fun updateTask(taskModel: TaskModel){
        return taskRepository.updateTask(taskModel)
    }
    suspend fun deleteTask(taskModel: TaskModel){
        return taskRepository.deleteTask(taskModel)
    }
}