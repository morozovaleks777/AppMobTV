package com.example.myapplicationmobtv

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val shortDescription: String = "",
    val thumbnail: String?=""
) : SomeData

data class Serie(
    val seasons: List<Feed.Serie.Season>,
    val id: Int = 0,
    val title: String = "",
    val shortDescription: String = "",
    val thumbnail: String?=""
) : SomeData
data class Season(
    val episodes: List<Episode>,
    val seasonNumber: Int=0,

):SomeData
data class Episode(
    val content: Feed.Serie.Season.Episode.Content,
    val episodeNumber: Int=0,
    val id: String="",
    val releaseDate: String="",
    val shortDescription: String="",
    val thumbnail: String="",
    val title: String=""
):SomeData