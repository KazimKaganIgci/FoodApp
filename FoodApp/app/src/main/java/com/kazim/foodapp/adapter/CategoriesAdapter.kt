package com.kazim.foodapp.adapter

import com.kazim.foodapp.data.Category
import com.kazim.foodapp.databinding.CategoriesRowBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.foodapp.fragments.HomeFragmentDirections


class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private var diffUtil =object :DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
           return oldItem.idCategory==newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem==newItem
        }

    }
    val differ =AsyncListDiffer(this,diffUtil)


    class ViewHolder(val binding:CategoriesRowBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(CategoriesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data =differ.currentList[position]
        Glide.with(holder.itemView).load(data.strCategoryThumb).into(holder.binding.imageView)
        holder.binding.text.text =data.strCategory.toString()

        holder.binding.imageView.setOnClickListener {
         it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeedFragment(data.strCategory.toString()))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}