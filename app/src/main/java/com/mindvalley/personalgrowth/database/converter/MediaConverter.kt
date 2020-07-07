package com.mindvalley.personalgrowth.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.model.Media

class MediaConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromMedia(media: Media): String {
        val type = object : TypeToken<Media>() {

        }.type
        return gson.toJson(media, type)
    }

    @TypeConverter
    fun toMedia(mediaString: String): Media {
        val type = object : TypeToken<Media>() {

        }.type
        return gson.fromJson(mediaString, type)
    }

}