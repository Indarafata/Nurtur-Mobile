package com.dicoding.picodiploma.nurtur.data.pref

import androidx.compose.ui.graphics.vector.ImageVector
import com.dicoding.picodiploma.nurtur.ui.navigation.Screen

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
