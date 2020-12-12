package com.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.personalgrowth.database.AppConstants
import com.personalgrowth.model.AllChannels

@Entity(tableName = AppConstants.CHANNELS_TABLE)
class Channels(
    @PrimaryKey
    val id: Int = 0,
    val data: AllChannels?
)

