package com.example.recyclersample

data class Comment(val postId: Int, val id: Int, val name: String, val email: String, val body: String) {
    override fun toString(): String {
        return postId.toString() + id.toString() + name + email + body
    }
}
