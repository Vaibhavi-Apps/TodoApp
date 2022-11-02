package com.vppanchalofficial.reminder

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.vppanchalofficial.reminder.databinding.CreateTaskListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class myTaskListAdapter(private var context: HomeActivity) :
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
        val dao = TaskAppDataBase.getDatabase(context.applicationContext).taskDao()
        val repository = TaskRepository(dao)
        val mainViewModel = ViewModelProvider(context, MainViewModelFectory(repository)).get(mainViewModel::class.java)


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
                    mainViewModel.updateTask(TaskModel(taskModel.id, taskModel.taskName,  taskModel.taskDesc, taskStatus = Constant.STATUS_DONE))
                }
                notifyItemChanged(position)

            }else{
                holder.binding.imageDelete.isChecked = false
                holder.binding.textView2.setPaintFlags(holder.binding.textView2.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                GlobalScope.launch {
                    mainViewModel.updateTask(TaskModel(taskModel.id, taskModel.taskName,  taskModel.taskDesc, taskStatus = Constant.STATUS_PENDING))
                }
                notifyItemChanged(position)

            }
        }
        /*holder.binding.imageDelete.setOnClickListener {
            */
        /*(itemViewModels as MutableList).removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemViewModels.size)

            GlobalScope.launch {
                database.taskDao().deleteTask(taskModel)
            }
            val expandIn: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            holder.itemView.startAnimation(expandIn)*/
        /*


        }*/
    }

    fun setList(it: List<TaskModel>) {
        this.itemViewModels = it;
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        var taskModel: TaskModel = itemViewModels.get(position)
        val database = Room.databaseBuilder(context, TaskAppDataBase::class.java, "taskDb").build()

        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemViewModels.size)

        GlobalScope.launch {
            database.taskDao().deleteTask(taskModel)
        }
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

