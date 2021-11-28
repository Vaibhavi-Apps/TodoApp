package com.vppanchaofficial.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.vppanchaofficial.reminder.databinding.ActivityCreateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {

    lateinit var database: TaskAppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityCreateBinding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //here we initialize our database
        database = Room.databaseBuilder(this, TaskAppDataBase::class.java, "taskDb").build()

        binding.saveTask.setOnClickListener{
            GlobalScope.launch {
                database.taskDao().insertTask(TaskModel(0,binding.task.text.toString(),binding.text2.text.toString()))
            }
            // TaskModel("task_name9","task_desc1")
            finish()
        }
    }
}