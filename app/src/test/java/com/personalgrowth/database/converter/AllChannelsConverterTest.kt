package com.personalgrowth.database.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.personalgrowth.model.AllChannels
import com.personalgrowth.model.ChannelItem
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class AllChannelsConverterTest {

    private val gson = Gson()
    private val type = object : TypeToken<AllChannels>() {}.type

    @Test
    fun fromAllChannels_returns_CorrectData() {

        //Arrange
        val channelItem = mutableListOf<ChannelItem>()
        channelItem.add(ChannelItem("Mindvalley", null, 10, null, "", null, null))
        val channelsSent = AllChannels(channelItem)

        //Act
        val channelsString = gson.toJson(channelsSent, type)

        //Assert
        assertThat(channelsString, CoreMatchers.containsString("Mindvalley"))
        assertThat(channelsString, CoreMatchers.containsString("10"))

    }


    @Test
    fun fromAllChannels_returns_WrongData() {

        //Arrange
        val channelItem = mutableListOf<ChannelItem>()
        channelItem.add(ChannelItem("Mindvalley", null, 10, null, "", null, null))
        val channelsSent = AllChannels(channelItem)

        //Act
        val channelsString = gson.toJson(channelsSent, type)

        //Assert
        assertFalse(channelsString, channelsString.isNullOrBlank())

    }

    @Test
    fun toAllChannels_returns_CorrectData() {

        //Arrange
        val channelString =
            "{\"channels\":[{\"title\":\"Mindvalley\",\"mediaCount\":10,\"id\":\"\"}]}"

        //Act
        val allChannels = gson.fromJson<AllChannels>(channelString, type)

        //Assert
        assertEquals(allChannels?.channels?.get(0)?.title, "Mindvalley")
        assertEquals(allChannels?.channels?.get(0)?.mediaCount, 10)

    }


    @Test
    fun toAllChannels_returns_WrongData() {

        //Arrange
        val channelString =
            "{\"channels\":[{\"title\":\"Mindvalley\",\"mediaCount\":10,\"id\":\"\"}]}"

        //Act
        val allChannels = gson.fromJson<AllChannels>(channelString, type)

        //Assert
        assertFalse(
            allChannels?.channels?.get(0)?.title,
            allChannels?.channels?.get(0)?.title.isNullOrBlank()
        )

    }

}