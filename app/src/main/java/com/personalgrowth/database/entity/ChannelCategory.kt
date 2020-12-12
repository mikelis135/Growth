package com.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.personalgrowth.database.AppConstants
import com.personalgrowth.model.Category

@Entity(tableName = AppConstants.CHANNEL_CATAGORY_TABLE)
class ChannelCategory(
    @PrimaryKey
    var id: Int = 0,
    val data: Category?
)

