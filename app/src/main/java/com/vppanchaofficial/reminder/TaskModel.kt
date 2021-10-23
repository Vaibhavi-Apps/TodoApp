package com.vppanchaofficial.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskModel(@PrimaryKey(autoGenerate = true)
                       val id: Int,
                       val taskName: String,
                       val taskDesc: String){


}

