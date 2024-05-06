package com.example.cleanarchitecturemovieapp.ui.screens.home

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchitecturemovieapp.data.Resource
import com.example.cleanarchitecturemovieapp.data.model.Movie
import com.example.cleanarchitecturemovieapp.ui.components.CircularLoading
import com.example.cleanarchitecturemovieapp.ui.components.MovieListItem
import com.example.cleanarchitecturemovieapp.ui.components.upcoming.AnimationScrollItem
import com.example.cleanarchitecturemovieapp.ui.components.upcoming.CarouselMovieItem

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val upcomingMoviesState by homeViewModel.upcomingMoviesState.collectAsState()
    val popularMoviesState by homeViewModel.popularMoviesState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            HandleSectionMoviesState(
                upcomingMoviesState = upcomingMoviesState,
                navController = navController
            )
        }

        item {
            HandlePopularMoviesState(
                popularMoviesState = popularMoviesState,
                navController = navController
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HandleSectionMoviesState(
    upcomingMoviesState: Resource<List<Movie>>,
    navController: NavController
) {
    val pageCount = Int.MAX_VALUE
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = pageCount / 2,
    )

    when (upcomingMoviesState) {
        is Resource.Loading -> {}

        is Resource.Success -> {
            val movies = upcomingMoviesState.data
            Text(
                text = "Upcoming Movies",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp)
            )
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                HorizontalPager(
                    modifier = Modifier.height(160.dp),
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 60.dp),
                ) { page ->
                    val sizeScale by animateFloatAsState(
                        if (pagerState.currentPage == page) 1f else .85f,
                        label = "",
                        animationSpec = tween(
                            durationMillis = 250,
                        )
                    )

                    CarouselMovieItem(
                        movie = movies[page % movies.size],
                        sizeScale = sizeScale,
                        navController = navController
                    )
                }
            }
            AnimationScrollItem(pagerState)
        }

        is Resource.Error -> {}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HandlePopularMoviesState(
    popularMoviesState: Resource<List<Movie>>,
    navController: NavController
) {
    when (popularMoviesState) {
        is Resource.Loading -> {
            CircularLoading()
        }

        is Resource.Success -> {
            val movies = popularMoviesState.data
            Text(
                text = "Popular Movies",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp, top = 20.dp)
            )
            Column {
                movies.forEach{
                    MovieListItem(movie = it, navController = navController)
                }
            }

        }

        is Resource.Error -> {
            Text(
                text = "Unable to fetch data from server",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 18.dp, bottom = 15.dp, top = 20.dp)
                    .fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}
