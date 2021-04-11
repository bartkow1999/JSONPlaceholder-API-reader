package com.example.recyclersample

data class User(val id: Int, val name: String, val email: String) {
    override fun toString(): String {
        return id.toString() + name + email
    }
}
