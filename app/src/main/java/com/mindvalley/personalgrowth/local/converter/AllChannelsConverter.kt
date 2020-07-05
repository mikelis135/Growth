package com.mindvalley.personalgrowth.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.local.entity.AllChannels

class AllChannelsConverter {

    val gson = Gson()

    @TypeConverter
    fun fromAllChannels(allChannels: AllChannels): String {


        val type = object : TypeToken<AllChannels>() {

        }.type
        return gson.toJson(allChannels, type)
    }

    @TypeConverter
    fun toAllChannels(allChannelsString: String): AllChannels {
        val type = object : TypeToken<AllChannels>() {

        }.type
        return gson.fromJson<AllChannels>(allChannelsString, type)
    }

}