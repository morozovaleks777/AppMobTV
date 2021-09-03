package com.example.myapplicationmobtv
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class Feed(
    @Json(name = "language")
    val language: String,
    @Json(name = "lastUpdated")
    val lastUpdated: String,
    @Json(name = "movies")
    val movies: List<Movie>,
    @Json(name = "providerName")
    val providerName: String,
    @Json(name = "series")
    val series: List<Serie>
):SomeData {
    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "content")
        val content: Content,
        @Json(name = "genres")
        val genres: List<String>,
        @Json(name = "id")
        val id: String,
        @Json(name = "releaseDate")
        val releaseDate: String,
        @Json(name = "shortDescription")
        val shortDescription: String,
        @Json(name = "tags")
        val tags: List<String>,
        @Json(name = "thumbnail")
        val thumbnail: String,
        @Json(name = "title")
        val title: String
    ):SomeData {
        @JsonClass(generateAdapter = true)
        data class Content(
            @Json(name = "captions")
            val captions: List<Any>,
            @Json(name = "dateAdded")
            val dateAdded: String,
            @Json(name = "duration")
            val duration: Int,
            @Json(name = "videos")
            val videos: List<Video>
        ) {
            @JsonClass(generateAdapter = true)
            data class Video(
                @Json(name = "quality")
                val quality: String,
                @Json(name = "url")
                val url: String,
                @Json(name = "videoType")
                val videoType: String
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class Serie(
        @Json(name = "genres")
        val genres: List<String>,
        @Json(name = "id")
        val id: String,
        @Json(name = "releaseDate")
        val releaseDate: String,
        @Json(name = "seasons")
        val seasons: List<Season>,
        @Json(name = "shortDescription")
        val shortDescription: String,
        @Json(name = "tags")
        val tags: List<String>,
        @Json(name = "thumbnail")
        val thumbnail: String,
        @Json(name = "title")
        val title: String
    ):SomeData {
        @JsonClass(generateAdapter = true)
        data class Season(
            @Json(name = "episodes")
            val episodes: List<Episode>,
            @Json(name = "seasonNumber")
            val seasonNumber: Int
        ) {
            @JsonClass(generateAdapter = true)
            data class Episode(
                @Json(name = "content")
                val content: Content,
                @Json(name = "episodeNumber")
                val episodeNumber: Int,
                @Json(name = "id")
                val id: String,
                @Json(name = "releaseDate")
                val releaseDate: String,
                @Json(name = "shortDescription")
                val shortDescription: String,
                @Json(name = "thumbnail")
                val thumbnail: String,
                @Json(name = "title")
                val title: String
            ) {
                @JsonClass(generateAdapter = true)
                data class Content(
                    @Json(name = "captions")
                    val captions: List<Any>,
                    @Json(name = "dateAdded")
                    val dateAdded: String,
                    @Json(name = "duration")
                    val duration: Int,
                    @Json(name = "videos")
                    val videos: List<Video>
                ) {
                    @JsonClass(generateAdapter = true)
                    data class Video(
                        @Json(name = "quality")
                        val quality: String,
                        @Json(name = "url")
                        val url: String,
                        @Json(name = "videoType")
                        val videoType: String
                    )
                }
            }
        }
    }
}
