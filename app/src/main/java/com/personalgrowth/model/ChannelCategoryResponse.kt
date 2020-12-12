package com.personalgrowth.model

data class ChannelCategoryResponse(
    val data: Category?
)

data class Category(
    val categories: List<CategoryNames>?
)

data class CategoryNames(
    val name: String = ""
)