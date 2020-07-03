package com.mindvalley.personalgrowth.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryConverter {

    @TypeConverter
    fun fromCategory(category: Category): String {
        val gson = Gson()
        val type = object : TypeToken<Category>() {

        }.type
        return gson.toJson(category, type)
    }

    @TypeConverter
    fun toCategory(categoryString: String): Category {
        val gson = Gson()
        val type = object : TypeToken<Category>() {

        }.type
        return gson.fromJson<Category>(categoryString, type)
    }

}