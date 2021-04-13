package com.example.recyclersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_comment.*
import java.net.URL

class CommentActivity : AppCompatActivity() {

    private var commentList: ArrayList<Comment> = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val commentRecyclerView: RecyclerView = findViewById(R.id.commentRecyclerView)

        val id_post = intent.getStringExtra("post_id")

        val threadGetComments = Thread() {
            run {
                Thread.sleep(1000)
                val url = "https://jsonplaceholder.typicode.com/comments"
                val body = URL(url).readText()
                val tempCommentsList = ArrayList(Klaxon().parseArray<Comment>(body))

                for (comment: Comment in tempCommentsList) {
                    if (comment.postId.toString() == id_post) {
                        commentList.add(comment)
                    }
                }
            }
            runOnUiThread() {
                commentRecyclerView.adapter = CommentAdapter(commentList)
            }
        }
        threadGetComments.start()

        val backButton: Button = findViewById(R.id.backCommentButton)
        backButton.setOnClickListener() {
            this.finish()
        }
    }
}