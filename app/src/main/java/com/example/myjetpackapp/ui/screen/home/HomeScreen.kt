package com.example.myjetpackapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myjetpackapp.di.Injection
import com.example.myjetpackapp.model.Destinations
import com.example.myjetpackapp.ui.ViewModelFactory
import com.example.myjetpackapp.ui.common.UiState
import com.example.myjetpackapp.ui.component.Item

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(
        initial = UiState.Loading
    ).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getAllDestinations()
            }

            is UiState.Success -> {
                HomeContent(
                    listDestinations = it.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }

            is UiState.Error -> {
                // do nothing
            }
        }
    }
}

@Composable
fun HomeContent(
    listDestinations: List<Destinations>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listDestinations) {
            Item(
                id = it.destinations.id,
                name = it.destinations.name,
                rating = it.destinations.rating,
                photoUrl = it.destinations.photoUrl,
                modifier = Modifier.clickable {
                    navigateToDetail(it.destinations.id)
                }
            )
        }
    }
}