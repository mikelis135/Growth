package com.mindvalley.personalgrowth.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels_table")
class Channels(
    @PrimaryKey
    val id: Int = 0,
    val data: AllChannels?
)

data class AllChannels(
    val channels: List<ChannelItem>?
)

data class ChannelItem(
    val title: String = "",
    val series: List<Series>?,
    val mediaCount: Int = 0,
    val latestMedia: List<ChannelMedia>?,
    val id: String = "",
    val iconAsset: IconAsset?,
    val coverAsset: CoverAsset?
)

data class Series(
    val title: String = "",
    val coverAsset: CoverAsset?
)

data class ChannelMedia(
    val type: String = "",
    val title: String = "",
    val coverAsset: CoverAsset?
)

data class IconAsset(
    val thumbnailUrl: String = ""
)