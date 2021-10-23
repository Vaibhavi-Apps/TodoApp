package com.vppanchaofficial.reminder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vppanchaofficial.reminder.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        binding.lifecycleOwner = this

        /*val mainViewModel: mainViewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        mainViewModel.addTask()*/

//        var model = TaskModel("task_name1","task_desc1")
//        binding.taskModel = model

        var arraylist = ArrayList<TaskModel>()
        /*arraylist.add(TaskModel("task_name1","task_desc1"))
        arraylist.add(TaskModel("task_name2","task_desc1"))
        arraylist.add(TaskModel("task_name3","task_desc1"))
        arraylist.add(TaskModel("task_name4","task_desc1"))
        arraylist.add(TaskModel("task_name5","task_desc1"))
        arraylist.add(TaskModel("task_name6","task_desc1"))
        arraylist.add(TaskModel("task_name7","task_desc1"))
        arraylist.add(TaskModel("task_name8","task_desc1"))*/

       /* var model = TaskModel("task_name1","task_desc1")
        binding.taskModel = model
        */
        val adapter = myTaskListAdapter(arraylist)
        binding.taskList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.taskList.adapter = adapter

    }

    fun openActivty(view: android.view.View) {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }



}