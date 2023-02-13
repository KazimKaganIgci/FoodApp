package com.kazim.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.foodapp.data.MealX
import com.kazim.foodapp.databinding.OverRowBinding
import com.kazim.foodapp.fragments.HomeFragmentDirections

class OverAdapter: RecyclerView.Adapter<OverAdapter.ViewHolder>() {
    private var diffUtil =object :DiffUtil.ItemCallback<MealX>(){
        override fun areItemsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem.idMeal ==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem ==newItem
        }
    }
    val differ =AsyncListDiffer(this,diffUtil)


    class ViewHolder(val binding:OverRowBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(OverRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =differ.currentList[position]
        Glide.with(holder.itemView).load(data.strMealThumb).into(holder.binding.overimg)

        holder.binding.overimg.setOnClickListener {view->

                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment
                    (data.idMeal.toString(),data.strMeal.toString(),data.strMealThumb.toString()))


        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}