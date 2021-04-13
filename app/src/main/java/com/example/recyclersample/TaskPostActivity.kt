package com.example.recyclersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import java.net.URL

class TaskPostActivity : AppCompatActivity() {

    private var tasksList: ArrayList<Task> = ArrayList<Task>()
    private var postsList: ArrayList<Post> = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_post)

        val taskRecyclerView: RecyclerView = findViewById(R.id.taskRecyclerView)
//        taskRecyclerView.adapter = TaskAdapter(ArrayList<Task>(), this::onTaskItemClickHandler)
        val postRecyclerView: RecyclerView = findViewById(R.id.postRecyclerView)
//        postRecyclerView.adapter = PostAdapter(ArrayList<Post>(), this::onPostItemClickHandler)

        val id_parent = intent.getStringExtra("rodzic_id")

        val threadGetTasks = Thread() {
            run {
                Thread.sleep(1000)
                val url = "https://jsonplaceholder.typicode.com/todos"
                val body = URL(url).readText()
                val tempTasksList = ArrayList(Klaxon().parseArray<Task>(body))

                for (task: Task in tempTasksList) {
                    if (task.userId.toString() == id_parent) {
                        tasksList.add(task)
                    }
                }
            }
            runOnUiThread() {
                taskRecyclerView.adapter = TaskAdapter(tasksList, this::onTaskItemClickHandler)
            }
        }
        threadGetTasks.start()

        val threadGetPosts = Thread() {
            run {
                Thread.sleep(1000)
                val url = "https://jsonplaceholder.typicode.com/posts"
                val body = URL(url).readText()
                val tempPostsList = ArrayList(Klaxon().parseArray<Post>(body))

                for (post: Post in tempPostsList) {
                    if (post.userId.toString() == id_parent) {
                        postsList.add(post)
                    }
                }
            }
            runOnUiThread() {
                postRecyclerView.adapter = PostAdapter(postsList, this::onPostItemClickHandler)
            }
        }
        threadGetPosts.start()

        val backButton: Button = findViewById(R.id.backPostButton)
        backButton.setOnClickListener() {
            this.finish()
        }

    }

    private fun onTaskItemClickHandler(position:Int){
        Log.d("in task ***","${position}");
        //here you can start a new intent to open a new activity on click of item
    }

    private fun onPostItemClickHandler(position:Int){
        Log.d("in post ***","${position}");

        val intent = Intent(this, CommentActivity()::class.java)
        intent.putExtra("post_id", (postsList[0].id+position).toString())
        startActivity(intent)
    }
}