package com.example.terrestrial.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.terrestrial.data.response.DataItem
import com.example.terrestrial.databinding.ItemCourseBinding

class CourseAdapter(private val onItemClick: (String) -> Unit) :
    ListAdapter<DataItem, CourseAdapter.CourseViewHolder>(DiffCallback) {

    class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: DataItem) {
            Glide.with(itemView).load(course.thumbnail).into(binding.ivItemPhoto)
            binding.tvTitleKursus.text = course.courseName
            binding.desc.text = course.courseType
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding =
            ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = getItem(position)
        holder.bind(currentCourse)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentCourse.id.toString())
        }
    }
}
