package com.vppanchalofficial.reminder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vppanchalofficial.reminder.TaskModule.TaskDao
import com.vppanchalofficial.reminder.TaskModule.TaskModel

@Database(entities =  [TaskModel::class], version = 1)
abstract class TaskAppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object{
        private var Instance: TaskAppDataBase? = null

        fun getDatabase(context: Context): TaskAppDataBase {
            if(Instance == null){
                synchronized(this) {
                    Instance =
                        Room.databaseBuilder(context, TaskAppDataBase::class.java, "taskDb")
                            .build()
                }
            }
            return Instance!!
        }
    }
}