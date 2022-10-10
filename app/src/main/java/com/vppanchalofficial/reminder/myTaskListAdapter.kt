package com.vppanchalofficial.reminder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.vppanchalofficial.reminder.databinding.CreateTaskListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class myTaskListAdapter(private var context: Context) :
    RecyclerView.Adapter<myTaskListAdapter.ViewHolder>() {

    private var itemViewModels: List<TaskModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CreateTaskListBinding.inflate(
            LayoutInflater.from(parent.context),
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
        holder.binding.imageDelete.setOnClickListener {
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

    fun setList(it: List<TaskModel>) {
        this.itemViewModels = it;
        notifyDataSetChanged()
    }

/*
    fun updateList(arrayList: LiveData<List<TaskModel>>) {
        itemViewModels = arrayList
        //notifyDataSetChanged()
    }
*/


    class ViewHolder(var binding: CreateTaskListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.setVariable(BR.taskModel, taskModel)
            binding.executePendingBindings()
        }
    }

}

