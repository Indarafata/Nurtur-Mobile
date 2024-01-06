package com.dicoding.picodiploma.nurtur.ui.view.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.pref.BottomBarItem
import com.dicoding.picodiploma.nurtur.ui.navigation.Screen
import com.dicoding.picodiploma.nurtur.ui.view.article.ArticleScreen
import com.dicoding.picodiploma.nurtur.ui.view.article.DetailArticleScreen
import com.dicoding.picodiploma.nurtur.ui.view.consultation.ConsultationScreen
import com.dicoding.picodiploma.nurtur.ui.view.consultation.KonsultasiViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModel
import com.dicoding.picodiploma.nurtur.ui.view.history.HistoryDailyMoodScreen
import com.dicoding.picodiploma.nurtur.ui.view.history.HistoryConsultationScreen
import com.dicoding.picodiploma.nurtur.ui.view.home.HomeScreen
import com.dicoding.picodiploma.nurtur.ui.view.profile.ProfileScreen

//class JetNurturApp {
//}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetNurturApp(
    page : Int,
    mainViewModel: MainViewModel,
    resultDailyMoodViewModel: DailyMoodViewModel,
    konsultasiViewModel: KonsultasiViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Article.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if(page == 1)Screen.Beranda.route else Screen.Konsultasi.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Beranda.route) {
                HomeScreen(mainViewModel, resultDailyMoodViewModel, navigateToConsultation = { listId ->
                    navController.navigate(Screen.Konsultasi.route)
                }, navigateToArticle = { listId ->
                    navController.navigate(Screen.Article.route)
                }, navigateToDetail = { articleId ->
                    navController.navigate(Screen.DetailArticle.createRoute(articleId))
                })
            }
            composable(Screen.Konsultasi.route) {
                ConsultationScreen(konsultasiViewModel)
            }
            composable(Screen.Riwayat.route) {
                HistoryConsultationScreen(konsultasiViewModel)
            }
            composable(Screen.Profil.route) {
                ProfileScreen(navigateToHistoryTransaction = { listId ->
                    navController.navigate(Screen.Riwayat.route)
                }, navigateToHistoryMood = { listId ->
                    navController.navigate(Screen.RiwayatMood.route)
                }, mainViewModel)
            }
            composable(Screen.RiwayatMood.route) {
                HistoryDailyMoodScreen(mainViewModel, resultDailyMoodViewModel)
            }
            composable(Screen.Article.route) {
                ArticleScreen(navigateToDetail = { articleId ->
                    navController.navigate(Screen.DetailArticle.createRoute(articleId))
                })
            }
            composable(
                route = Screen.DetailArticle.route,
                arguments = listOf(navArgument("articleId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("articleId") ?: -1L
                DetailArticleScreen(
                    articleId = id.toInt(),
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_beranda),
                icon = Icons.Default.Home,
                screen = Screen.Beranda
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_konsultasi),
                icon = ImageVector.vectorResource(R.drawable.ic_baseline_consultation),
                screen = Screen.Konsultasi
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_riwayat),
                icon = ImageVector.vectorResource(R.drawable.ic_baseline_activity),
                screen = Screen.Riwayat
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profil),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profil
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = currentRoute == it.screen.route,
                onClick = {
                    navController.navigate(it.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetNurturAppPreview() {
    lateinit var mainViewMode: MainViewModel
    lateinit var dailyMoodViewModel: DailyMoodViewModel
    lateinit var konsultasiViewModel: KonsultasiViewModel
    JetNurturApp(0, mainViewMode, dailyMoodViewModel, konsultasiViewModel)
}