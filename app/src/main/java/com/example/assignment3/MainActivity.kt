package com.example.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
// MovieScreen.kt
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.network.RetrofitInstance
import com.example.assignment3.view.MovieCollectionRow
import com.example.assignment3.view.MovieViewModel
import com.example.assignment3.view.MoviesByCollectionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {            val navController = rememberNavController()
            val viewModel: MovieViewModel = remember { MovieViewModel(RetrofitInstance.api) }


            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(navController, startDestination = "MovieScreen") {
                    composable("MovieScreen") { MovieScreen(viewModel, navController) }
                    composable("MoviesByCollectionScreen/{collectionType}") { backStackEntry ->
                        val collectionType = backStackEntry.arguments?.getString("collectionType") ?: ""
                        MoviesByCollectionScreen(viewModel, collectionType, navController)
                    }
                }
            }
        }
    }
}


@Composable
fun MovieScreen(viewModel: MovieViewModel, navController: NavController) {
    val collectionTypes = listOf("TOP_POPULAR_ALL", "TOP_POPULAR_MOVIES", "TOP_250_MOVIES","TOP_250_TV_SHOWS", "VAMPIRE_THEME", "COMICS_THEME")

    LazyColumn(verticalArrangement = Arrangement.spacedBy(36.12.dp) , modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(top = 97.dp)) {
        item {
            Text(text = "Skillcinema", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        }


        collectionTypes.forEach { collectionType ->
            item {

                MovieCollectionRow(
                    title = collectionType,collectionType=collectionType, viewModel=viewModel,
                    onSeeAllClick = { navController.navigate("MoviesByCollectionScreen/$collectionType") }
                )
            }
        }
    }
}

