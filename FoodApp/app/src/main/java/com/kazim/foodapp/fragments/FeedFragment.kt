package com.kazim.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kazim.foodapp.adapter.FavAdapter
import com.kazim.foodapp.adapter.FeedAdapter
import com.kazim.foodapp.databinding.FragmentFeedBinding
import com.kazim.foodapp.viewModels.FeedViewModel
import com.kazim.foodapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedFragment : Fragment() {
   private lateinit var binding:FragmentFeedBinding
   private lateinit var data:String
    private lateinit var feedAdapter: FeedAdapter

    private val viewModel:FeedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedAdapter= FeedAdapter()
        arguments?.let {
            data =FeedFragmentArgs.fromBundle(it).categoryname.toString()
            println(data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCategoryDetailsList(data)
        setupFavRecycler()
        viewModel.getCategoryMealLivedata.observe(viewLifecycleOwner, Observer {
            feedAdapter.differ.submitList(it)
        })

    }

    private fun setupFavRecycler() {
        binding.favRecylerView.apply {
            layoutManager = GridLayoutManager(context,2, RecyclerView.VERTICAL,false)
            adapter =feedAdapter


        }
    }




}