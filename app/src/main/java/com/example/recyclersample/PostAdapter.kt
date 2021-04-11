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

class PostAdapter(val postList: ArrayList<Post>, val itemClickHandler: (Int) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.idPostTextView)
        private val userIdTextView: TextView = itemView.findViewById(R.id.userIdPostTextView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titlePostTextView)
        private val bodyTextView: TextView = itemView.findViewById(R.id.bodyPostTextView)

        fun bind(id: Int, userId: Int, title: String, body: String) {
            idTextView.text = id.toString()
            userIdTextView.text = userId.toString()
            titleTextView.text = title
            bodyTextView.text = body
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val headerView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        val headerViewHolder = PostViewHolder(headerView)
        headerView.setOnClickListener {
            itemClickHandler.invoke(headerViewHolder.adapterPosition)
        }
        return headerViewHolder
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return postList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position].id, postList[position].userId, postList[position].title, postList[position].body )
    }
}