package com.dicoding.picodiploma.nurtur.ui.view.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun DetailArticleScreen(
    modifier: Modifier = Modifier,
    articleId: Int,
    navigateBack: () -> Unit,
) {
    val articleViewModel: ArticleViewModel = ArticleViewModel()
    val articles = articleViewModel.articles
    val article = articles.filter { it.id == articleId }.first()


    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ){
            AsyncImage(
                model = article.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp).fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 25.dp)
            )
            Text(
                text = article.description,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListItemPreview() {
//    MyCharacterListTheme {
        DetailArticleScreen(
            articleId = 10,
            navigateBack = {
            },
        )
//    }
}