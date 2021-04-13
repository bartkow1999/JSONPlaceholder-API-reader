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

class CommentAdapter(val commentList: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postIdTextView: TextView = itemView.findViewById(R.id.postIdCommentTextView)
        private val idTextView: TextView = itemView.findViewById(R.id.idCommentTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameCommentTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailCommentTextView)
        private val bodyTextView: TextView = itemView.findViewById(R.id.bodyCommentTextView)

        fun bind(postId: Int, id: Int, name: String, email: String, body: String) {
            postIdTextView.text = postId.toString()
            idTextView.text = id.toString()
            nameTextView.text = name
            emailTextView.text = email
            bodyTextView.text = body
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val headerView =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(headerView)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return commentList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(
            commentList[position].postId,
            commentList[position].id,
            commentList[position].name,
            commentList[position].email,
            commentList[position].body
        )
    }
}