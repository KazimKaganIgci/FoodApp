package com.kazim.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kazim.foodapp.adapter.FavAdapter
import com.kazim.foodapp.databinding.FragmentFavoriteBinding
import com.kazim.foodapp.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
   private lateinit var binding:FragmentFavoriteBinding
   private lateinit var favAdapter: FavAdapter
   private val viewModel:DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favAdapter=FavAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavRecycler()
        getAllData()
        getDeleteAdapter()
    }

    private fun getDeleteAdapter() {
        lifecycleScope.launchWhenStarted {
            favAdapter.setOnActionEditListener{
                viewModel.deleteMeal(meal =it)
                favAdapter.notifyDataSetChanged()

            }
        }
    }

    private fun getAllData() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllMeals().collect{
                favAdapter.differ.submitList(it)
            }
        }
    }

    private fun setupFavRecycler() {
        binding.favRecylerView.apply {
            layoutManager =GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
            adapter =favAdapter


        }
    }


}