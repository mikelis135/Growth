package com.mindvalley.personalgrowth.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindvalley.personalgrowth.local.entity.ChannelItem

class ChannelItemConverter {

    @TypeConverter
    fun fromChannelItem(channelItem: ChannelItem): String {
        val gson = Gson()
        val type = object : TypeToken<ChannelItem>() {

        }.type
        return gson.toJson(channelItem, type)
    }

    @TypeConverter
    fun toChannelItem(channelItemString: String): ChannelItem {
        val gson = Gson()
        val type = object : TypeToken<ChannelItem>() {

        }.type
        return gson.fromJson<ChannelItem>(channelItemString, type)
    }

}