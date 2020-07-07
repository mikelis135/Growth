package com.mindvalley.personalgrowth.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindvalley.personalgrowth.database.DBConstants
import com.mindvalley.personalgrowth.model.Media

@Entity(tableName = DBConstants.NEW_EPISODES_TABLE)
class NewEpisodes(
    @PrimaryKey
    val id: Int = 0,
    val data: Media?
)

