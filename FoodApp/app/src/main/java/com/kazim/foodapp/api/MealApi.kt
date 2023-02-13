package com.kazim.foodapp.api

import com.kazim.foodapp.data.CategoriesList
import com.kazim.foodapp.data.MealList
import com.kazim.foodapp.data.OverList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal():Response<MealList>

    @GET("filter.php")
    suspend fun getOverList(@Query("c") categoryName:String):Response<OverList>

    @GET("categories.php")
    suspend fun getCategories():Response<CategoriesList>


    @GET("lookup.php")
    suspend fun getDetailList(@Query("i") categoryId:String):Response<MealList>

    @GET("filter.php")
    suspend fun getFeedList(@Query("c") categoryName:String):Response<OverList>
}