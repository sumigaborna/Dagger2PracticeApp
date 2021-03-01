package com.example.dagger2practiceapp.models

class UsersApiResponse : ArrayList<UserItem>()

data class UserItem(
    val id: Int,
    val email: String,
)