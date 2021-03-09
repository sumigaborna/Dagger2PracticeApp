package com.example.dagger2practiceapp.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger2practiceapp.SessionManager
import com.example.dagger2practiceapp.models.Post
import com.example.dagger2practiceapp.models.PostsApiResponse
import com.example.dagger2practiceapp.network.main.MainApi
import com.example.dagger2practiceapp.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    private val posts = MediatorLiveData<Resource<PostsApiResponse?>>()

    fun observePosts(): LiveData<Resource<PostsApiResponse?>> {
        posts.value = Resource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPosts(sessionManager.getAuthUser.value!!.data!!.id)
                .onErrorReturn {
                    Log.e("PostsViewModel", "source error getting posts: ${it.localizedMessage}")
                    val post = Post()
                    post.id = -1
                    return@onErrorReturn PostsApiResponse()
                }
                .map {
                    if(it.size < 0){
                        return@map Resource.error("Something went wrong",null)
                    }
                    else{
                        return@map Resource.success(it)
                    }
                }.subscribeOn(Schedulers.io())
        )

        posts.addSource(source) {
            posts.value = it as Resource<PostsApiResponse?>?
            posts.removeSource(source)
        }

        return posts
    }
}