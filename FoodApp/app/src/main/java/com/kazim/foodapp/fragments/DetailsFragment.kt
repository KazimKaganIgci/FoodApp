package com.kazim.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.databinding.FragmentDetailsBinding
import com.kazim.foodapp.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mealId:String
    private lateinit var mealTitle:String
    private lateinit var mealUrl:String
    private lateinit var meal:Meal
    private val viewModel:DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealId=DetailsFragmentArgs.fromBundle(it).mealid
            mealUrl=DetailsFragmentArgs.fromBundle(it).mealurl
            mealTitle=DetailsFragmentArgs.fromBundle(it).mealname


        }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMeal(mealId)
        detailsMeals()

        binding.fabbutton.setOnClickListener {
            viewModel.upsertMeal(meal)
        }

       /* lifecycleScope.launchWhenStarted {
            viewModel.getAllMeals().collect{

            }
        }*/

    }

    private fun detailsMeals() {
       Glide.with(this).load(mealUrl).into(binding.imageView)
        viewModel.getDetailMealLivedata.observe(viewLifecycleOwner, Observer {data->
            binding.infoText.text=data.strInstructions
            binding.categoriesText.text="Categories:"+data.strCategory
            binding.titleText.text="Name:"+data.strMeal
            meal=data
        })
    }


}