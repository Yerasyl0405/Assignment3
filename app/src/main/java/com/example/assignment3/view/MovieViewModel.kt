package com.example.assignment3.view
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment3.model.Movie
import com.example.assignment3.model.MovieCollectionResponse
import com.example.assignment3.network.KinopoiskApi
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(private val api: KinopoiskApi) : ViewModel() {

    private val _moviesByCollectionMap = mutableStateMapOf<String, List<Movie>>()
    val moviesByCollectionMap: Map<String, List<Movie>> get() = _moviesByCollectionMap


    fun fetchMoviesByCollection(collectionType: String) {
        if (_moviesByCollectionMap.containsKey(collectionType)) return

        viewModelScope.launch {
            try {
                val response: Response<MovieCollectionResponse> = api.getMoviesByCollection(collectionType)
                if (response.isSuccessful) {
                    response.body()?.let { movieCollectionResponse ->
                        _moviesByCollectionMap[collectionType] = movieCollectionResponse.items
                    } ?: run {
                        _moviesByCollectionMap[collectionType] = emptyList()
                    }
                } else {
                    _moviesByCollectionMap[collectionType] = emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _moviesByCollectionMap[collectionType] = emptyList()
            }
        }
    }
}

