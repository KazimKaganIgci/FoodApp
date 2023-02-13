package com.kazim.foodapp.db

import androidx.room.*
import com.kazim.foodapp.data.Meal
import com.kazim.foodapp.data.MealList
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal:Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM meal")
    fun getMeals(): Flow<List<Meal>>
    }