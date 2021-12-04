package com.vppanchalofficial.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vppanchalofficial.reminder.databinding.ActivityCreateBinding
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
        val dao = TaskAppDataBase.getDatabase(applicationContext).taskDao()
        val repository = TaskRepository(dao)
        val mainViewModel = ViewModelProvider(this, MainViewModelFectory(repository)).get(mainViewModel::class.java)


        binding.saveTask.setOnClickListener{
            if(binding.task.text ==  null || binding.task.text.toString().equals("")){
                binding.task.error = "Please Enter Task"
            }else {
                GlobalScope.launch {
                    mainViewModel.insertTask(TaskModel(
                        0,
                        binding.task.text.toString(),
                        binding.text2.text.toString()
                    ))

                 /*   database.taskDao().insertTask(
                        TaskModel(
                            0,
                            binding.task.text.toString(),
                            binding.text2.text.toString()
                        )
                    )*/
                }
                finish()
            }
        }
    }
}