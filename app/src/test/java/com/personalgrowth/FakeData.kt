package com.personalgrowth

import com.personalgrowth.model.*

object FakeData {

    val channelCategoryData =
        ChannelCategoryResponse(Category(listOf(CategoryNames("taiwo"), CategoryNames("kehinde"))))

    val newEpisodesData = NewEpisodesResponse(
        Media(
            listOf(
                Course(
                    "type1",
                    "title1",
                    CoverAsset("url1"),
                    Channel("title")
                ), Course("type2", "title1", CoverAsset("url1"), Channel("title"))
            )
        )
    )

    val channelData = ChannelsResponse(
        AllChannels(
            listOf(
                ChannelItem(
                    "title1",
                    listOf(
                        Series("seriesTitle1", CoverAsset("coverAssetUrl1")),
                        Series("seriesTitle2", CoverAsset("coverAssetUrl2"))
                    ),
                    0,
                    listOf(ChannelMedia("type", "title", CoverAsset("channelMediaCoverAsset"))),
                    "id",
                    IconAsset("iconAssetThumbNail"),
                    CoverAsset("coverAsset")
                )
            )
        )
    )

    val errorMessage = "error fetching data"

}