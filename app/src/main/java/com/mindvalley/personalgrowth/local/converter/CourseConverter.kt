package com.mindvalley.personalgrowth.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.local.entity.Course

class CourseConverter {

    val gson = Gson()

    @TypeConverter
    fun fromCourse(course: Course): String {
        val type = object : TypeToken<Course>() {

        }.type
        return gson.toJson(course, type)
    }

    @TypeConverter
    fun toCourse(courseString: String): Course {
        val type = object : TypeToken<Course>() {

        }.type
        return gson.fromJson<Course>(courseString, type)
    }

}