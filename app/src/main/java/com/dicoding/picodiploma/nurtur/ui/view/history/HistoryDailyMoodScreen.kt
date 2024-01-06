package com.dicoding.picodiploma.nurtur.ui.view.history

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.UploadFaceMoodActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel

@Composable
fun HistoryDailyMoodScreen(mainViewModel: MainViewModel, dailyMoodViewModel: DailyMoodViewModel, modifier: Modifier = Modifier) {
    val currentUser = mainViewModel.currentUser.observeAsState()
    val userId =currentUser.value?.message?.id ?: ""

    dailyMoodViewModel.userId = userId

    val data = dailyMoodViewModel.getDailyMood.observeAsState()


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        BannerHistory()
        Spacer(modifier = Modifier.height(10.dp))
//        for (historyDaily in data?.value ?: dailyMoodViewModel.history) {
//            HistoryDailyCard(historyDaily.hasil, historyDaily.day)
//        }
        if(data.value?.isEmpty() == true){
            Text(
                text = "Belum ada data riwayat",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        else {
            for (historyDaily in data?.value ?: dailyMoodViewModel.history) {
                HistoryDailyCard(historyDaily.hasil, historyDaily.day)
            }
        }
    }
}

@Composable
fun BannerHistory(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.image_banner_history),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Riwayat Daily Mood",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HistoryDailyCard(keteranganMood: String, tanggal: String) {
    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                context.startActivity(
                    Intent(context, UploadFaceMoodActivity::class.java)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            if(keteranganMood == "Mood Baik"){
                Image(
                    painter = painterResource(R.drawable.happy_reaction),
                    contentDescription = "Banner Image",
                    modifier = Modifier.size(45.dp)
                )
            }
            else if(keteranganMood == "Mood Sedang"){
                Image(
                    painter = painterResource(R.drawable.flat_reaction),
                    contentDescription = "Banner Image",
                    modifier = Modifier.size(45.dp)
                )
            }
            else{
                Image(
                    painter = painterResource(R.drawable.angry_reaction),
                    contentDescription = "Banner Image",
                    modifier = Modifier.size(45.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = keteranganMood,
                    fontSize = 14.sp,
                )
                Text(
                    text = tanggal,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HistoryDailyCardPreview() {
    HistoryDailyCard("Mood Bagus", "15 Des 2023")
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HistoryDailyMoodScreenPreview() {
    lateinit var mainViewModel: MainViewModel
    lateinit var dailyMoodViewModel: DailyMoodViewModel
    HistoryDailyMoodScreen(mainViewModel, dailyMoodViewModel)
}