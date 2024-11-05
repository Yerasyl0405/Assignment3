@file:Suppress("NAME_SHADOWING")

package com.example.assignment3.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.assignment3.model.Movie


import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController

@Composable
fun MoviesByCollectionScreen(viewModel: MovieViewModel, collectionType: String, navController: NavController) {
    LaunchedEffect(collectionType) {
        viewModel.fetchMoviesByCollection(collectionType)
    }

    val movies = viewModel.moviesByCollectionMap[collectionType] ?: emptyList()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie) {

            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .width(111.dp), // Установка фиксированной ширины для колонки
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth() // Используем fillMaxWidth для карточки
                .height(156.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = movie.posterUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = movie.nameRu ?: movie.nameEn ?: "Unknown",
            style = TextStyle(fontSize = 14.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 4.dp) // Добавляем горизонтальные отступы
                .fillMaxWidth() // Устанавливаем ширину текста на всю доступную ширину


        )
        Text(text = movie.genres[0].genre, fontSize = 12.sp)
    }
}

@Composable
fun MovieCollectionRow(
    title: String,
    collectionType: String,
    viewModel: MovieViewModel,
    onSeeAllClick: () -> Unit
) {
    val movies = viewModel.moviesByCollectionMap[collectionType] ?: emptyList()

    LaunchedEffect(collectionType) {
        viewModel.fetchMoviesByCollection(collectionType)
    }

    Column (modifier = Modifier.height(238.dp)){
        Row (modifier = Modifier.padding(start= 26.dp).height(20.dp).width(308.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween){
            Text(text = title, style = TextStyle(fontSize = 18.sp,  fontWeight = FontWeight.W600), modifier = Modifier.clickable {      })


            Text(text = "Все", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W500, color = Color.Blue ), modifier = Modifier.clickable { onSeeAllClick() })

        }

        LazyRow(
            modifier = Modifier.padding(start= 26.dp).padding(top= 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie) {  }
            }
        }
    }
}
