package com.kazim.foodapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.data.MealX
import com.kazim.foodapp.repo.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MealRepository
) :ViewModel() {


    private val _getDetailMealLivedata= MutableLiveData<Meal>()
    val getDetailMealLivedata: LiveData<Meal> =_getDetailMealLivedata

    fun getDetailMeal(id:String){
        viewModelScope.launch {
           var response= repository.getDetailMeal(id)

            response.body()!!.meals.let {
                if (it != null) {
                    _getDetailMealLivedata.postValue(it[0])
                }
            }
        }
    }


    fun upsertMeal(meal: Meal)=viewModelScope.launch {
        repository.upsertMeal(meal)
    }

    fun deleteMeal(meal: Meal)=viewModelScope.launch {
        repository.deleteMeal(meal)
    }
    fun getAllMeals()=repository.getAllMeal
}