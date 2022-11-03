package com.vppanchalofficial.reminder.TaskModule

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vppanchalofficial.reminder.*
import com.vppanchalofficial.reminder.Singletons.SwipeToDeleteCallback
import com.vppanchalofficial.reminder.databinding.ActivityHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    private lateinit var dao: TaskDao
    lateinit var binding: ActivityHomeBinding
    private var adapter = myTaskListAdapter(this)

    private lateinit var repository: TaskRepository
    private lateinit var mainViewModelV: mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        adapter = myTaskListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.taskList.setLayoutManager(linearLayoutManager)

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.taskList)


        binding.taskList.adapter = adapter

        dao = TaskAppDataBase.getDatabase(applicationContext).taskDao()
        repository = TaskRepository(dao)
        mainViewModelV = ViewModelProvider(this, MainViewModelFectory(repository)).get(mainViewModel::class.java)

        mainViewModelV.getTask().observe(this, {
            adapter.setList(it)
        })

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    fun openActivty(view: android.view.View) {
        /*val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)*/

        val bottomsheet = BottomSheetDialog(this)
        bottomsheet.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val views = layoutInflater.inflate(R.layout.activity_create, null)
        bottomsheet.setContentView(views)

        val saveButton = views.findViewById<Button>(R.id.save_task)
        val taskName = views.findViewById<EditText>(R.id.task)
        val taskDescription = views.findViewById<EditText>(R.id.text2)
        bottomsheet.show()

        saveButton.setOnClickListener{


            if(taskName.text ==  null || taskName.text.toString().equals("")){
                taskName.error = "Please Enter Task"
            }else {
                bottomsheet.dismiss()
                GlobalScope.launch {
                    mainViewModelV.insertTask(
                        TaskModel(
                        0,
                        taskName.text.toString(),
                        taskDescription.text.toString()
                    )
                    )

                    /*   database.taskDao().insertTask(
                           TaskModel(
                               0,
                               taskName.text.toString(),
                               binding.text2.text.toString()
                           )
                       )*/
                }
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        GlobalScope.launch {

        }
    }
}