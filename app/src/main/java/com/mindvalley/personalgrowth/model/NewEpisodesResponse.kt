package com.mindvalley.personalgrowth.model

data class NewEpisodesResponse(
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