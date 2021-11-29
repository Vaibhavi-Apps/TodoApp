package com.vppanchaofficial.reminder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.vppanchaofficial.reminder.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    private lateinit var database: TaskAppDataBase
    lateinit var binding: ActivityHomeBinding
    private var adapter = myTaskListAdapter(this, ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        /*val mainViewModel: mainViewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        mainViewModel.addTask()*/

//        var model = TaskModel("task_name1","task_desc1")
//        binding.taskModel = model

        //here we initialize our database
        database = Room.databaseBuilder(this, TaskAppDataBase::class.java, "taskDb").build()
        GlobalScope.launch {
            getTask(binding)
        }

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private suspend fun getTask(binding: ActivityHomeBinding) {
        val arrayList = database.taskDao().getTask()
        adapter = myTaskListAdapter(this, arrayList)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.taskList.setLayoutManager(staggeredGridLayoutManager)
        binding.taskList.adapter = adapter

    }

    fun openActivty(view: android.view.View) {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        GlobalScope.launch {
            val arrayList = database.taskDao().getTask()
            adapter.updateList(arrayList)
        }
    }

}