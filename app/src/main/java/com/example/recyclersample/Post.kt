package com.example.recyclersample

data class Post(val id: Int, val userId: Int, val title: String, val body: String) {
    override fun toString(): String {
        return id.toString() + userId.toString() + title + body
    }
}
