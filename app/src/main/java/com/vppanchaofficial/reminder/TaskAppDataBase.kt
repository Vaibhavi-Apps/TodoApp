package com.vppanchaofficial.reminder

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =  [TaskModel::class], version = 1)
abstract class TaskAppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}