package com.kazim.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.foodapp.data.MealX
import com.kazim.foodapp.databinding.FeedRowBinding
import com.kazim.foodapp.fragments.FeedFragmentDirections
import com.kazim.foodapp.fragments.HomeFragmentDirections

class FeedAdapter: RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private var diffUtil =object : DiffUtil.ItemCallback<MealX>(){
        override fun areItemsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem.idMeal ==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealX, newItem: MealX): Boolean {
            return oldItem ==newItem
        }
    }
    val differ = AsyncListDiffer(this,diffUtil)

    class ViewHolder(val binding:FeedRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FeedRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =differ.currentList[position]
        Glide.with(holder.itemView).load(data.strMealThumb).into(holder.binding.imageView)
        holder.binding.text.text=data.strMeal
        holder.binding.imageView.setOnClickListener {view->

            Navigation.findNavController(view).navigate(FeedFragmentDirections.actionFeedFragmentToDetailsFragment(data.idMeal.toString(),data.strMeal.toString(),data.strMealThumb.toString()))


        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}