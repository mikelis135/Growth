package com.mindvalley.personalgrowth.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.local.entity.Media

class MediaConverter {

    val gson = Gson()

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
        return gson.fromJson<Media>(mediaString, type)
    }

}