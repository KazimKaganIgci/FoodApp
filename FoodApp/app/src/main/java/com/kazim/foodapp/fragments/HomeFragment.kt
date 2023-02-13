package com.kazim.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.foodapp.adapter.CategoriesAdapter
import com.kazim.foodapp.adapter.OverAdapter
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.databinding.FragmentHomeBinding
import com.kazim.foodapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding :FragmentHomeBinding
    private lateinit var overAdapter: OverAdapter
    private lateinit var categoryAdapter:CategoriesAdapter
    private lateinit var meal:Meal

    private val viewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding =FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        overAdapter= OverAdapter()
        categoryAdapter= CategoriesAdapter()
        getRandomMeals()
        getOverMeals()
        setupRecyclerview()
        setupCategoriesRecyclerview()
        getCategories()
        binding.imageView.setOnClickListener {view->
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment
                (meal.idMeal.toString(),meal.strMeal.toString(),meal.strMealThumb.toString()))
        }

    }

    private fun setupCategoriesRecyclerview(){
        binding.categoryRecyclerView.apply {
            layoutManager=GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
            adapter=categoryAdapter
        }

    }

    private fun getCategories() {
       lifecycleScope.launchWhenStarted {
           viewModel.getCategoriesLivedata.collect{
               categoryAdapter.differ.submitList(it)


           }
       }
    }

    private fun setupRecyclerview() {
        binding.overRecyclerView.apply {
            layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter =overAdapter
        }
    }

    private fun getOverMeals() {
        viewModel.getOverMealLivedata.observe(viewLifecycleOwner, Observer {
            overAdapter.differ.submitList(it)
        })
    }

    private fun getRandomMeals() {
        viewModel.getRandomMealLivedata.observe(viewLifecycleOwner, Observer {data->
            if (data !=null){
            Glide.with(this).load(data.strMealThumb).into(binding.imageView)
                meal=data


            }


        })
    }


}