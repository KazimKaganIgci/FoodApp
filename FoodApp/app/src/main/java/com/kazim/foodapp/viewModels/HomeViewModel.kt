package com.kazim.foodapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazim.foodapp.data.*
import com.kazim.foodapp.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MealRepository
):ViewModel() {


    init {
        getRandomMeal()
        getOverList()
        getCategories()
    }
    private val _getRandomMealLivedata=MutableLiveData<Meal>()
    val getRandomMealLivedata:LiveData<Meal> =_getRandomMealLivedata

    fun getRandomMeal(){
        try {
            viewModelScope.launch {
                val response = repository.getRandomMeal()

                response.body()!!.meals.let {
                    if (it != null) {
                        _getRandomMealLivedata.postValue(it[0])
                    }
                }

            }
        }catch (e:Exception){
            Log.d("Hata", "getRandomMeal: Hata"+e.localizedMessage.toString())
        }
      
    }


    private val _getOverMealLivedata=MutableLiveData<List<MealX>>()
    val getOverMealLivedata:LiveData<List<MealX>> =_getOverMealLivedata
    fun getOverList(){
        try {
            viewModelScope.launch {
                val response =repository.getOverList("Seafood")
                if (response.isSuccessful){
                    response.body()?.meals?.let {
                        _getOverMealLivedata.postValue(it)
                    }
                }

            }

        }catch (e:Exception){
            Log.d("Hata", "getRandomMeal: Hata"+e.localizedMessage.toString())
        }

    }
    private val _getCategoriesLivedata=MutableStateFlow<List<Category>>(emptyList())
    var getCategoriesLivedata: MutableStateFlow<List<Category>> =_getCategoriesLivedata

    fun getCategories(){

        try {
            viewModelScope.launch {
                val response =repository.getCategory()
                if (response.isSuccessful){
                    response.body()!!.categories.let {
                        if (it != null) {
                            _getCategoriesLivedata.emit(it as List<Category>)
                        }
                    }
                }
            }

        }catch (e:Exception){
            Log.d("Hata", "getRandomMeal: Hata"+e.localizedMessage.toString())
        }

    }



}