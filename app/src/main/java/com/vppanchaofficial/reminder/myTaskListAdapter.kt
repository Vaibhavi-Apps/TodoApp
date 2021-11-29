package com.vppanchaofficial.reminder

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class myTaskListAdapter(private var context: Context, private var itemViewModels: List<TaskModel>) :
    RecyclerView.Adapter<myTaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.create_task_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val database = Room.databaseBuilder(context, TaskAppDataBase::class.java, "taskDb").build()

        val taskModel: TaskModel = itemViewModels.get(position)
        holder.bind(taskModel)

        holder.itemView.setOnClickListener {
            (itemViewModels as MutableList).removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemViewModels.size)

            GlobalScope.launch {
                database.taskDao().deleteTask(taskModel)
            }
            val expandIn: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            holder.itemView.startAnimation(expandIn)
        }
    }

    fun updateList(arrayList: List<TaskModel>) {
        itemViewModels = arrayList
        notifyDataSetChanged()
    }


    class ViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {

            binding.setVariable(BR.taskModel, taskModel)
            binding.executePendingBindings()
        }
    }

}

