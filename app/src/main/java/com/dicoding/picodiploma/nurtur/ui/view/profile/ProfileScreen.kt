package com.dicoding.picodiploma.nurtur.ui.view.profile

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.ui.view.changePassword.ChangePasswordActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel

@Composable
fun ProfileScreen(navigateToHistoryTransaction : (String) -> Unit, navigateToHistoryMood: (String) -> Unit, mainViewModel: MainViewModel) {
    val currentUser = mainViewModel.currentUser.observeAsState()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BannerProfile(mainViewModel)

        MenuProfile(
            painterResource(R.drawable.ic_baseline_lock_24),
            "Ganti Password",
            Modifier.clickable {
                context.startActivity(
                    Intent(context, ChangePasswordActivity::class.java)
                )
            })
        MenuProfile(painterResource(R.drawable.ic_baseline_history_24), "Riwayat Transaksi", Modifier.clickable { navigateToHistoryTransaction(currentUser.value?.message?.id ?: "")})
        MenuProfile(painterResource(R.drawable.ic_baseline_person_24), "Riwayat Daily Mood", Modifier.clickable { navigateToHistoryMood(currentUser.value?.message?.id ?: "") })
        Spacer(modifier = Modifier.height(100.dp))
        MenuProfileLogout(
            mainViewModel,
            painterResource(R.drawable.ic_baseline_logout_24),
            "Keluar"
        )
    }
}

@Composable
fun BannerProfile(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
    val currentUser = mainViewModel.currentUser.observeAsState()
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.image_banner_profile),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.empty_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 10.dp)
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = currentUser.value?.message?.name ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )
            Text(
                text = currentUser.value?.message?.email ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )
        }
//        Profile()
    }
}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.image_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Ahmed",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
        )
        Text(
            text = "0987654321",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
        )

    }
}

@Composable
fun MenuProfile(icon: Painter, name: String, modifier: Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(1.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .height(52.dp)
                .width(300.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = icon,
                contentDescription = "Banner Image",
                colorFilter = ColorFilter.tint(color = Color.Gray),
            )
            Spacer(modifier = Modifier.width(22.dp))
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Image(
                painter = painterResource(R.drawable.ic_baseline_arrow_forward_24),
                contentDescription = "Banner Image",
                colorFilter = ColorFilter.tint(color = Color.Gray),
            )
        }
    }
}

@Composable
fun MenuProfileLogout(
    mainViewModel: MainViewModel,
    icon: Painter,
    name: String,
    modifier: Modifier = Modifier
) {
    val currentUser = mainViewModel.currentUser.observeAsState()
    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(1.dp),
    ) {
        Row(modifier = Modifier
            .height(52.dp)
            .width(300.dp)
            .clickable {
                mainViewModel.logout(
                    currentUser.value?.message?.id ?: "",
                    currentUser.value?.message?.token ?: ""
                ); context.startActivity(
                Intent(context, MainActivity::class.java)
            )
            }, verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(58.dp))
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(155.dp))
            Image(
                painter = icon,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = Color.Gray),
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    lateinit var mainViewModel: MainViewModel
    ProfileScreen(navigateToHistoryTransaction = {
    }, navigateToHistoryMood = {
    }, mainViewModel)
}