package com.dicoding.picodiploma.nurtur.ui.view.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.dicoding.picodiploma.nurtur.data.Article

@Composable
fun ArticleScreen(navigateToDetail: (Int) -> Unit, modifier: Modifier = Modifier) {
    val articleViewModel: ArticleViewModel = ArticleViewModel()


    Column(
        modifier = modifier
            .padding()
    ) {
        BannerHome()
        LazyColumn {
            items(articleViewModel.articles, key = { it.id }) { article ->
                ArticleCard(article, navigateToDetail)
            }
        }
    }
}

@Composable
fun BannerHome(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.image_banner_home_article),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Search()
            Row {
                CardButton("Waktu")
                CardButton("Emosi")
                CardButton("Keluarga")
//                CardButton()

            }
        }
    }
}

@Composable
fun ArticleCard(article: Article, navigateToDetail: (Int) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(3.dp)
    ) {
        Row(
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth().clickable { navigateToDetail(article.id) }, verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = article.thumbnail,
                contentDescription = "Banner Image",
                modifier = Modifier.size(75.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = article.title,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun CardButton(title: String, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(25.dp)
            .width(91.dp)
    ) {
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(modifier: Modifier = Modifier) {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Cari artikel")
        },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
        Search()
}

@Preview(showBackground = true)
@Composable
fun CardButtonPreview() {
    CardButton("waktu")
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
//    ArticleScreen()
}