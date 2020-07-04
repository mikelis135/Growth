package com.mindvalley.personalgrowth.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_episodes_table")
class NewEpisodes(
    @PrimaryKey
    val id: Int = 0,
    val data: Media?
)

data class Media(
    val media: List<Course>?
)

data class Course(
    val type: String = "",
    val title: String = "",
    val coverAsset: CoverAsset?,
    val channel: Channel?
)

data class CoverAsset(
    val url: String = ""
)

data class Channel(
    val title: String = ""
)