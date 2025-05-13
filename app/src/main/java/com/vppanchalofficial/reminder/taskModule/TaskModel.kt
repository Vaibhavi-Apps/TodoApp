package com.vppanchalofficial.reminder.taskModule

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vppanchalofficial.reminder.Singletons.Constant

@Entity(tableName = "task")
data class TaskModel(@PrimaryKey(autoGenerate = true)
                       val id: Int,
                     val taskName: String,
                     val taskDesc: String,
                     var taskStatus: String = Constant.STATUS_PENDING
)

