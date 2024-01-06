package com.dicoding.picodiploma.nurtur.ui.view.article

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nurtur.data.ArticleData

class ArticleViewModel() : ViewModel() {

    val articles = ArticleData.articles
}