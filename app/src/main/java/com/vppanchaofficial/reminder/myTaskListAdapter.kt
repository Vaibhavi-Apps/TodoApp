package com.vppanchaofficial.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class myTaskListAdapter(var itemViewModels: ArrayList<TaskModel>) : RecyclerView.Adapter<myTaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.crate_task_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

/*    override fun getItemViewType(position: Int): Int {
        return R.layout.crate_task_list
    }*/

    override fun getItemCount() = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskModel: TaskModel = itemViewModels.get(position)
        holder.bind(taskModel);
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.setVariable(BR.taskModel, taskModel);
            binding.executePendingBindings();
        }
    }

}

