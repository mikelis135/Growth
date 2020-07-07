package com.mindvalley.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindvalley.personalgrowth.database.DBConstants
import com.mindvalley.personalgrowth.model.Category

@Entity(tableName = DBConstants.CHANNEL_CATAGORY_TABLE)
class ChannelCategory(
    @PrimaryKey
    var id: Int = 0,
    val data: Category?
)

