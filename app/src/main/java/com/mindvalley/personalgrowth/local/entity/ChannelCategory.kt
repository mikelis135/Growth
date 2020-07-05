package com.mindvalley.personalgrowth.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
class ChannelCategory(
    @PrimaryKey
    val id: Int = 0,
    val data: Category?
)

data class Category(
    val categories: List<CategoryNames>?
)

data class CategoryNames(
    val name: String = ""
)