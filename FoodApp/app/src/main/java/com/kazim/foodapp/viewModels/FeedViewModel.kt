package com.kazim.foodapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazim.foodapp.data.MealX
import com.kazim.foodapp.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(val repository: MealRepository):ViewModel() {

    private val _getCategoryDetailMealLivedata= MutableLiveData<List<MealX>>()
    val getCategoryMealLivedata: LiveData<List<MealX>> =_getCategoryDetailMealLivedata
    fun getCategoryDetailsList(category:String){

        viewModelScope.launch {
            val response =repository.getFeedList(category)
            if (response.isSuccessful){
                response.body()?.meals?.let {
                    _getCategoryDetailMealLivedata.value =it as List<MealX>
                }
            }

        }




    }
}