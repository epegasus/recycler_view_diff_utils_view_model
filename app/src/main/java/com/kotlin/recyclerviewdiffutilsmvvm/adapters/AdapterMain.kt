package com.kotlin.recyclerviewdiffutilsmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.recyclerviewdiffutilsmvvm.R
import com.kotlin.recyclerviewdiffutilsmvvm.data_classes.Student
import com.kotlin.recyclerviewdiffutilsmvvm.databinding.ListItemStudentBinding
import com.kotlin.recyclerviewdiffutilsmvvm.interfaces.OnItemClickListener

class AdapterMain(private val onItemClickListener: OnItemClickListener) : ListAdapter<Student, AdapterMain.ViewHolderMain>(DiffUtilsStudent) {

    object DiffUtilsStudent : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_student, parent, false)
        return ViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        val student = getItem(position)
        holder.binding.tvIdListItemStudent.text = student.id.toString()
        holder.binding.tvNameListItemStudent.text = student.name
        holder.binding.root.setOnClickListener { onItemClickListener.onItemClick(holder.adapterPosition) }
    }

    class ViewHolderMain(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListItemStudentBinding = ListItemStudentBinding.bind(itemView)
    }
}