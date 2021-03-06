package com.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.personalgrowth.database.AppConstants
import com.personalgrowth.model.Media

@Entity(tableName = AppConstants.NEW_EPISODES_TABLE)
class NewEpisodes(
    @PrimaryKey
    val id: Int = 0,
    val data: Media?
)

