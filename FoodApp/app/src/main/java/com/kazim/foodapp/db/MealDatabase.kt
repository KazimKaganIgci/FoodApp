package com.kazim.foodapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kazim.foodapp.data.Meal

@Database(entities =[Meal::class], version = 1)
@TypeConverters(Converter::class)
abstract class MealDatabase :RoomDatabase(){
    abstract fun mealDao():MealDao
}