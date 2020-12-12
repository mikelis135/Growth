package com.personalgrowth.database.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.personalgrowth.model.Course
import com.personalgrowth.model.Media
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class MediaConverterTest {

    private val gson = Gson()
    private val type = object : TypeToken<Media>() {}.type

    @Test
    fun fromMedia_ReturnsCorrectData() {

        //Arrange
        val courseList = mutableListOf<Course>()
        courseList.add(Course("Series", "Mentoring", null, null))
        val media = Media(courseList)

        //Act
        val mediaString = gson.toJson(media, type)

        //Assert
        assertThat(mediaString, CoreMatchers.containsString("Series"))
        assertThat(mediaString, CoreMatchers.containsString("Mentoring"))

    }

    @Test
    fun fromMedia_ReturnsWrongData() {

        //Arrange
        val courseList = mutableListOf<Course>()
        courseList.add(Course("Series", "Mentoring", null, null))
        val media = Media(courseList)

        //Act
        val mediaString = gson.toJson(media, type)

        //Assert
        assertFalse(mediaString, mediaString.isNullOrBlank())

    }

    @Test
    fun toMedia_ReturnsCorrectData() {

        //Arrange
        val mediaString = "{\"media\":[{\"type\":\"Series\",\"title\":\"Mentoring\"}]}"

        //Act
        val media = gson.fromJson<Media>(mediaString, type)

        //Assert
        assertEquals(media?.media?.get(0)?.type, "Series")
        assertEquals(media?.media?.get(0)?.title, "Mentoring")

    }


    @Test
    fun toMedia_ReturnsWrongData() {

        //Arrange
        val mediaString = "{\"media\":[{\"type\":\"Series\",\"title\":\"Mentoring\"}]}"

        //Act
        val media = gson.fromJson<Media>(mediaString, type)

        //Assert
        assertFalse(media?.media?.get(0)?.type, media?.media?.get(0)?.type.isNullOrBlank())

    }

}