package com.example.assignment3.network
import androidx.compose.runtime.Composable
import com.example.assignment3.model.Movie
import com.example.assignment3.model.MovieCollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Headers
import retrofit2.http.Query

interface KinopoiskApi {
    @Headers("X-API-KEY: 91ace429-0bb3-4bcc-978a-27dd9e4e4b3d")
    @GET("v2.2/films/collections")
    suspend fun getMoviesByCollection(
        @Query("type") type: String,
    ): Response<MovieCollectionResponse>
}


