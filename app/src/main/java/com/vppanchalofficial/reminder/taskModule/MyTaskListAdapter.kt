package com.vppanchalofficial.reminder.taskModule

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vppanchalofficial.reminder.BR
import com.vppanchalofficial.reminder.Singletons.Constant
import com.vppanchalofficial.reminder.TaskAppDataBase
import com.vppanchalofficial.reminder.databinding.CreateTaskListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class myTaskListAdapter(private var context: HomeActivity) :
    RecyclerView.Adapter<myTaskListAdapter.ViewHolder>() {

    private var itemViewModels: List<TaskModel> = listOf()
    private lateinit var mainViewModels: MainViewModel
    private lateinit var dao: TaskDao
    private lateinit var repository: TaskRepository

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

        dao = TaskAppDataBase.getDatabase(context.applicationContext).taskDao()
        repository = TaskRepository(dao)
        mainViewModels = ViewModelProvider(context, MainViewModelFectory(repository)).get(
            MainViewModel::class.java)

        var taskModel: TaskModel = itemViewModels.get(position)
        holder.bind(taskModel)
        val status = taskModel.taskStatus
        if(status.equals(Constant.STATUS_DONE)){
            holder.binding.textView2.setPaintFlags(holder.binding.textView2.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            holder.binding.imageDelete.isChecked = true
        }else{
            holder.binding.textView2.setPaintFlags(holder.binding.textView2.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
            holder.binding.imageDelete.isChecked = false
        }

        holder.binding.imageDelete.setOnClickListener {
            if(status.equals(Constant.STATUS_PENDING)){
                holder.binding.imageDelete.isChecked = true
                holder.binding.textView2.setPaintFlags(holder.binding.textView2.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                GlobalScope.launch {
                    mainViewModels.updateTask(TaskModel(taskModel.id, taskModel.taskName,  taskModel.taskDesc, taskStatus = Constant.STATUS_DONE))
                }
                notifyItemChanged(position)

            }else{
                holder.binding.imageDelete.isChecked = false
                holder.binding.textView2.setPaintFlags(holder.binding.textView2.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                GlobalScope.launch {
                    mainViewModels.updateTask(TaskModel(taskModel.id, taskModel.taskName,  taskModel.taskDesc, taskStatus = Constant.STATUS_PENDING))
                }
                notifyItemChanged(position)

            }
        }
    }

    fun setList(it: List<TaskModel>) {
        this.itemViewModels = it;
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        var taskModel: TaskModel = itemViewModels.get(position)
        GlobalScope.launch {
            mainViewModels.deleteTask(taskModel)
        }
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemViewModels.size)


    }


    class ViewHolder(var binding: CreateTaskListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.setVariable(BR.taskModel, taskModel)
            binding.executePendingBindings()
        }
    }

}
