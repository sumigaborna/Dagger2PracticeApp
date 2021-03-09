package com.example.dagger2practiceapp.models

class PostsApiResponse : ArrayList<Post>()

data class Post(
    val body: String? = null,
    var id: Int = 0,
    val title: String? = null,
    val userId: Int = 0
)
