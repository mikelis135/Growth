package com.personalgrowth.database.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.personalgrowth.model.Category
import com.personalgrowth.model.CategoryNames
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

internal class CategoryConverterTest {

    private val gson = Gson()
    private val type = object : TypeToken<Category>() {}.type

    @Test
    fun fromCategory_ReturnsCorrectData() {

        //Arrange
        val categoryNames = mutableListOf<CategoryNames>()
        categoryNames.add(CategoryNames("Taiwo"))
        val category = Category(categoryNames)

        //Act
        val categoryString = gson.toJson(category, type)

        //Assert
        assertThat(categoryString, CoreMatchers.containsString("Taiwo"))

    }

    @Test
    fun fromCategory_ReturnsWrongData() {

        //Arrange
        val categoryNames = mutableListOf<CategoryNames>()
        categoryNames.add(CategoryNames("Taiwo"))
        val category = Category(categoryNames)

        //Act
        val categoryString = gson.toJson(category, type)

        //Assert
        assertFalse(categoryString, categoryString.isNullOrBlank())

    }

    @Test
    fun toCategory_ReturnsCorrectData() {

        //Arrange
        val categoryString = "{\"categories\":[{\"name\":\"Taiwo\"}]}"

        //Act
        val allChannels = gson.fromJson<Category>(categoryString, type)

        //Assert
        assertEquals(allChannels?.categories?.get(0)?.name, "Taiwo")

    }

    @Test
    fun toCategory_ReturnsWrongData() {

        //Arrange
        val categoryString = "{\"categories\":[{\"name\":\"Taiwo\"}]}"

        //Act
        val category = gson.fromJson<Category>(categoryString, type)

        //Assert
        assertFalse(
            category?.categories?.get(0)?.name,
            category?.categories?.get(0)?.name.isNullOrBlank()
        )

    }

}