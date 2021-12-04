package com.vppanchalofficial.reminder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vppanchalofficial.reminder.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private var adapter = myTaskListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        adapter = myTaskListAdapter(this)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.taskList.setLayoutManager(staggeredGridLayoutManager)
        binding.taskList.adapter = adapter

        val dao = TaskAppDataBase.getDatabase(applicationContext).taskDao()
        val repository = TaskRepository(dao)
        val mainViewModel = ViewModelProvider(this, MainViewModelFectory(repository)).get(mainViewModel::class.java)

        mainViewModel.getTask().observe(this, {
            adapter.setList(it)
        })

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    fun openActivty(view: android.view.View) {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        GlobalScope.launch {

        }
    }
}