package com.dicoding.picodiploma.nurtur.ui.navigation

sealed class Screen(val route: String) {
    object Beranda : Screen("beranda")
    object Konsultasi : Screen("konsultasi")
    object Riwayat : Screen("aktivitas")
    object Profil : Screen("profil")
    object Article : Screen("article")
    object RiwayatMood : Screen("riwayat_mood")
    object DetailArticle : Screen("article/{articleId}") {
        fun createRoute(articleId: Int) = "article/$articleId"
    }
}