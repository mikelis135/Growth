package com.mindvalley.personalgrowth.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.model.Category

class CategoryConverter {

    val gson = Gson()

    @TypeConverter
    fun fromCategory(category: Category): String {
        val type = object : TypeToken<Category>() {

        }.type
        return gson.toJson(category, type)
    }

    @TypeConverter
    fun toCategory(categoryString: String): Category {
        val type = object : TypeToken<Category>() {

        }.type
        return gson.fromJson<Category>(categoryString, type)
    }

}