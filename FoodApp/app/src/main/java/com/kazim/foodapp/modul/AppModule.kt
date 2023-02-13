package com.kazim.foodapp.modul

import android.app.Application
import androidx.room.Room
import com.kazim.foodapp.api.MealApi
import com.kazim.foodapp.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApi():MealApi=Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app:Application):MealDatabase= Room.databaseBuilder(app,MealDatabase::class.java,"meal_db").build()
}