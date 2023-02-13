package com.kazim.foodapp.repo

import android.util.Log
import com.kazim.foodapp.api.MealApi
import com.kazim.foodapp.data.CategoriesList
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.data.MealList
import com.kazim.foodapp.data.OverList
import com.kazim.foodapp.db.MealDatabase
import retrofit2.Response
import javax.inject.Inject

class MealRepository@Inject constructor(private val mealApi: MealApi,
                        private val db:MealDatabase) {

    val database =db.mealDao()

    suspend fun getRandomMeal():Response<MealList>{
       return mealApi.getRandomMeal()
    }

    suspend fun getOverList(categoryName:String): Response<OverList>{
        return mealApi.getOverList(categoryName)
    }
    suspend fun getFeedList(categoryName:String): Response<OverList>{
        return mealApi.getFeedList(categoryName)
    }

    suspend fun getCategory(): Response<CategoriesList>{
        return mealApi.getCategories()
    }
    suspend fun getDetailMeal(id:String): Response<MealList>{
        return mealApi.getDetailList(id)
    }

     suspend fun upsertMeal(meal: Meal){
         database.insert(meal)
     }
    suspend fun deleteMeal(meal: Meal){
        database.delete(meal)
    }
    val getAllMeal =database.getMeals()



}