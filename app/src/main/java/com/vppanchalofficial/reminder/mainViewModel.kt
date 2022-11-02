package com.vppanchalofficial.reminder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

/*
    fun addTask(){

        var arraylist = ArrayList<TaskModel>()
        arraylist.add(TaskModel("task_name1","task_desc1"))
        arraylist.add(TaskModel("task_name2","task_desc1"))
        arraylist.add(TaskModel("task_name3","task_desc1"))
        arraylist.add(TaskModel("task_name4","task_desc1"))
        arraylist.add(TaskModel("task_name5","task_desc1"))
        arraylist.add(TaskModel("task_name6","task_desc1"))
        arraylist.add(TaskModel("task_name7","task_desc1"))
        arraylist.add(TaskModel("task_name8","task_desc1"))

    }
*/
}