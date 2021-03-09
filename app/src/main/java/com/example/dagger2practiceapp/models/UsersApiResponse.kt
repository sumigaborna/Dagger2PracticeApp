package com.example.dagger2practiceapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UsersApiResponse : ArrayList<UserItem>()


class UserItem {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

    constructor(id: Int, username: String?, email: String?, website: String?) {
        this.id = id
        this.username = username
        this.email = email
        this.website = website
    }

    constructor() {}
}