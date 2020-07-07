package com.mindvalley.personalgrowth.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.model.ChannelItem

class ChannelItemConverter {

    val gson = Gson()

    @TypeConverter
    fun fromChannelItem(channelItem: ChannelItem): String {
        val type = object : TypeToken<ChannelItem>() {

        }.type
        return gson.toJson(channelItem, type)
    }

    @TypeConverter
    fun toChannelItem(channelItemString: String): ChannelItem {
        val type = object : TypeToken<ChannelItem>() {

        }.type
        return gson.fromJson<ChannelItem>(channelItemString, type)
    }

}