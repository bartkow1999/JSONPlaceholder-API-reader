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

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var usersList: ArrayList<User>

    private lateinit var tasks_counter: ArrayList<Int>
    private lateinit var non_completed_counter: ArrayList<Int>

    private lateinit var posts_counter: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val threadGetUsers = Thread() {
            run {
                Thread.sleep(1000)
                val url = "https://jsonplaceholder.typicode.com/users"
                val body = URL(url).readText()
                usersList = ArrayList(Klaxon().parseArray<User>(body))

                val url_tasks = "https://jsonplaceholder.typicode.com/todos"
                val body_tasks = URL(url_tasks).readText()
                val tasksList = ArrayList(Klaxon().parseArray<Task>(body_tasks))

                tasks_counter = ArrayList<Int>()
                non_completed_counter = ArrayList<Int>()
                for (i in 0..9) {
                    tasks_counter.add(0)
                    non_completed_counter.add(0)
                }
                for (task: Task in tasksList) {
                    if (!task.completed) {
                        non_completed_counter[task.userId - 1]++
                    }
                    tasks_counter[task.userId - 1]++
                }

                val url_posts = "https://jsonplaceholder.typicode.com/posts"
                val body_posts = URL(url_posts).readText()
                val postsList = ArrayList(Klaxon().parseArray<Post>(body_posts))

                posts_counter = ArrayList<Int>()
                for (i in 0..9) {
                    posts_counter.add(0)
                }
                for (post: Post in postsList) {
                    posts_counter[post.userId - 1]++
                }

            }
            runOnUiThread() {
                val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
                recyclerView.adapter = UserAdapter(
                    usersList,
                    tasks_counter,
                    non_completed_counter,
                    posts_counter,
                    this::onItemClickHandler
                )
            }
        }
        threadGetUsers.start()
    }

    private fun onItemClickHandler(position: Int) {
        Log.d("***", "$position");

        val intent = Intent(this, TaskPostActivity()::class.java)
        intent.putExtra("rodzic_id", (position + 1).toString())
        startActivity(intent)
    }

}
