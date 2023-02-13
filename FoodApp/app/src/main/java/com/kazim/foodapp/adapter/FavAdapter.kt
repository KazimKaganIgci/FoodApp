package com.kazim.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.databinding.FavRowBinding
import com.kazim.foodapp.fragments.FavoriteFragmentDirections
import com.kazim.foodapp.fragments.HomeFragmentDirections

class FavAdapter: RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    private var delete : ((Meal) -> Unit)? =null

    private val diffUtil =object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem==newItem
        }
    }
    val differ =AsyncListDiffer(this,diffUtil)

    class ViewHolder(val binding:FavRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(FavRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data =differ.currentList[position]
        Glide.with(holder.itemView).load(data.strMealThumb).into(holder.binding.imageView)
        holder.binding.text.text =data.strMeal

        holder.binding.imageView.setOnClickListener {view->

            Navigation.findNavController(view).navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(data.idMeal,data.strMeal.toString(),data.strMealThumb.toString()))
        }
        holder.binding.deleteBtn.setOnClickListener{
            delete?.invoke(data)
        }
    }

    override fun getItemCount()=differ.currentList.size

    fun setOnActionEditListener(callback:(Meal)->Unit){
        this.delete =callback

    }
}