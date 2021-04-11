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

class UserAdapter(val userList: ArrayList<User>, val itemClickHandler: (Int) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.idUserTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameUserTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailUserTextView)

        fun bind(id: String, name: String, email: String) {
            idTextView.text = id
            nameTextView.text = name
            emailTextView.text = email
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val headerView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
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
        holder.bind(userList[position].id.toString(), userList[position].name, userList[position].email )
    }
}