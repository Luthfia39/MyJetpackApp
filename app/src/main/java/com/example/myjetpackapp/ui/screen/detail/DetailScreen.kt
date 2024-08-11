package com.example.myjetpackapp.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myjetpackapp.R
import com.example.myjetpackapp.di.Injection
import com.example.myjetpackapp.ui.ViewModelFactory
import com.example.myjetpackapp.ui.common.UiState

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getDetail(id)
            }

            is UiState.Success -> {
                val destination = it.data.destinations
                DetailContent(
                    destination.id,
                    destination.name,
                    destination.rating,
                    destination.price,
                    destination.desc,
                    destination.photoUrl,
                    onBackClick = navigateBack,
                )
            }

            is UiState.Error -> {
                // do nothing
            }
        }
    }
}

@Composable
fun DetailContent(
    id: Int,
    name: String,
    rating: Double,
    price: Double,
    desc: String,
    image: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = stringResource(R.string.image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .size(300.dp)
                        .clip(RectangleShape)
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = "Rating : ${rating.toString()}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = modifier.padding(top = 15.dp)
                )
                Text(
                    text = "Harga tiket : Rp${price.toString()}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = modifier.padding(top = 15.dp)
                )
            }
        }
    }
}