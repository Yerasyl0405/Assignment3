package com.example.assignment3.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val kinopoiskId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val posterUrl: String,
    val genres: List<Genre> // Add this line to include genres
)

data class MovieCollectionResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<Movie>
)
data class Genre(
    val genre: String
)