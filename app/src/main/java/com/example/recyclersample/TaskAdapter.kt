/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(val taskList: ArrayList<Task>, val itemClickHandler: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.idTaskTextView)
        private val userIdTextView: TextView = itemView.findViewById(R.id.userIdTaskTextView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTaskTextView)
        private val completedTextView: TextView = itemView.findViewById(R.id.completedTaskTextView)

        fun bind(id: Int, userId: Int, title: String, completed: Boolean) {
            idTextView.text = id.toString()
            userIdTextView.text = userId.toString()
            titleTextView.text = title
            completedTextView.text = completed.toString()
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val headerView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        val headerViewHolder = TaskViewHolder(headerView)
        headerView.setOnClickListener {
            itemClickHandler.invoke(headerViewHolder.adapterPosition)
        }
        return headerViewHolder
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return taskList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position].id, taskList[position].userId, taskList[position].title, taskList[position].completed )
    }
}