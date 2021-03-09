package com.example.dagger2practiceapp.models

data class PostsApiResponse(val posts:ArrayList<Post>)

data class Post (
    var userId : Int = 0,
    var id : Int = 0,
    var title: String? = null,
    var body: String? = null,
)