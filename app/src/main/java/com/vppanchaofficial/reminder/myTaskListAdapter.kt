package com.vppanchaofficial.reminder

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class myTaskListAdapter(var context: Context, var itemViewModels: List<TaskModel>) :
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

    /* override fun getItemViewType(position: Int): Int {
        return R.layout.crate_task_list
    }*/

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var database = Room.databaseBuilder(context, TaskAppDataBase::class.java, "taskDb").build()

        var taskModel: TaskModel = itemViewModels.get(position)
        holder.bind(taskModel)

        holder.itemView.setOnClickListener {

            Log.e("vaibhavi==>", "Item Click" + position)
            (itemViewModels as MutableList).removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemViewModels.size)

            GlobalScope.launch {
                database.taskDao().deleteTask(taskModel)
            }
            val expandIn: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            holder.itemView.startAnimation(expandIn)
        }
        /*val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.itemView.setBackgroundColor(color)*/
    }

    fun changeColor(view : TextView){
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        view.setBackgroundColor(color)
    }
    class ViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.setVariable(BR.taskModel, taskModel)
            binding.executePendingBindings()
        }
    }

}

