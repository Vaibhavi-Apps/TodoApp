package com.vppanchalofficial.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskModel(@PrimaryKey(autoGenerate = true)
                       val id: Int,
                     val taskName: String,
                     val taskDesc: String,
                     var taskStatus: String = Constant.STATUS_PENDING)

