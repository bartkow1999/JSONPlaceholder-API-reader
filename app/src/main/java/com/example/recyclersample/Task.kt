package com.example.recyclersample

data class Task(val id: Int, val userId: Int, val title: String, val completed: Boolean) {
    override fun toString(): String {
        return id.toString() + userId.toString() + title + completed.toString()
    }
}
