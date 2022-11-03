package com.vppanchalofficial.reminder.TaskModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFectory(private val taskRepository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return mainViewModel(taskRepository) as T
    }
}