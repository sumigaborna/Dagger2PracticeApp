package com.example.dagger2practiceapp.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger2practiceapp.R
import com.example.dagger2practiceapp.ui.main.Resource
import com.example.dagger2practiceapp.utils.VerticalSpacingItemDecoration
import com.example.dagger2practiceapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, { resource ->
            when(resource.status){
                Resource.Status.LOADING -> {
                    Log.d("PostsFragment","Loading data")
                }
                Resource.Status.ERROR -> {
                    Log.e("PostsFragment","Error occured on posts: ${resource.message}")
                }
                Resource.Status.SUCCESS -> {
                    resource.data?.let { adapter.setPosts(it) }
                }
            }
        })
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager  = LinearLayoutManager(requireContext())
        val verticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)
        recyclerView.addItemDecoration(verticalSpacingItemDecoration)
        recyclerView.adapter = adapter
    }
}