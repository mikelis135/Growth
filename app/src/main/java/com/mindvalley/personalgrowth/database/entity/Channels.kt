package com.mindvalley.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindvalley.personalgrowth.database.AppConstants
import com.mindvalley.personalgrowth.model.AllChannels

@Entity(tableName = AppConstants.CHANNELS_TABLE)
class Channels(
    @PrimaryKey
    val id: Int = 0,
    val data: AllChannels?
)

