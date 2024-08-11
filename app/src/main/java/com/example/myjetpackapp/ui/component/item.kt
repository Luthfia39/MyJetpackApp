package com.example.myjetpackapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myjetpackapp.R
import com.example.myjetpackapp.ui.theme.MyJetpackAppTheme

@Composable
fun Item(
    id: Int,
    name: String,
    rating: Double,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(bottom = 5.dp),

        ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = stringResource(R.string.image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .size(170.dp)
                .clip(RectangleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            fontSize = 18.sp
        )

        Row(modifier = modifier.padding(start = 14.dp, top = 18.dp, bottom = 8.dp)) {
            Icon(Icons.Filled.Star, "Star for rating")
            Text(
                text = rating.toString(),
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                textAlign = Start,
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun JetpackAppPreview() {
    MyJetpackAppTheme {
        Item(
            id = 1,
            name = "Ini nama",
            rating = 5.0,
            photoUrl = "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/3.jpg"
        )
    }
}