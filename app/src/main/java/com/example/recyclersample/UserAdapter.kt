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

class UserAdapter(
    val userList: ArrayList<User>,
    val tasks_counter: ArrayList<Int>,
    val non_completed_counter: ArrayList<Int>,
    val posts_counter: ArrayList<Int>,
    val itemClickHandler: (Int) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.idUserTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameUserTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailUserTextView)
        private val wykonanoTextView: TextView = itemView.findViewById(R.id.wykonanoUserTextView)
        private val postyTextView: TextView = itemView.findViewById(R.id.postyUserTextView)


        fun bind(id: String, name: String, email: String, wykonano: String, postow: String) {
            idTextView.text = id
            nameTextView.text = name
            emailTextView.text = email
            wykonanoTextView.text = wykonano
            postyTextView.text = postow
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val headerView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        val headerViewHolder = UserViewHolder(headerView)
        headerView.setOnClickListener {
            itemClickHandler.invoke(headerViewHolder.adapterPosition)
        }
        return headerViewHolder
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return userList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(
            userList[position].id.toString(),
            userList[position].name,
            userList[position].email,
            "To complete: " + non_completed_counter[position].toString() + "/" + tasks_counter[position].toString() + " tasks",
            "The user has made " + posts_counter[position].toString() + " posts"
        )
    }
}