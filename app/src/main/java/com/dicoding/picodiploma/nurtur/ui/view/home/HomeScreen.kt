package com.dicoding.picodiploma.nurtur.ui.view.home

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import coil.compose.AsyncImage
import com.dicoding.picodiploma.nurtur.data.Article
import com.dicoding.picodiploma.nurtur.data.MenuConsultationData
import com.dicoding.picodiploma.nurtur.data.databaseDailyMood.HistoryDailyMood
import com.dicoding.picodiploma.nurtur.ui.theme.LightYellow
import com.dicoding.picodiploma.nurtur.ui.theme.PurpleimeManagement
import com.dicoding.picodiploma.nurtur.ui.theme.PrimaryColor
import com.dicoding.picodiploma.nurtur.ui.view.article.ArticleViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.DailyMoodViewModel
import com.dicoding.picodiploma.nurtur.ui.view.dailyMood.UploadFaceMoodActivity
import com.dicoding.picodiploma.nurtur.ui.view.main.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    resultDailyMoodViewModel: DailyMoodViewModel,
    navigateToConsultation: (Int) -> Unit,
    navigateToArticle: (Int) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val data = resultDailyMoodViewModel.getDailyToday.observeAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding()
    ) {
        BannerHome(mainViewModel, navigateToConsultation, navigateToArticle, navigateToDetail, data?.value)
    }

}

@Composable
fun BannerHome(
    mainViewModel: MainViewModel,
    navigateToConsultation: (Int) -> Unit,
    navigateToArticle: (Int) -> Unit,
    navigateToDetail: (Int) -> Unit,
    dataToday: HistoryDailyMood?,
    modifier: Modifier = Modifier
) {
    val articleViewModel: ArticleViewModel = ArticleViewModel()
    val currentUser = mainViewModel.currentUser.observeAsState()

    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.image_banner_home_article),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
//        Search()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            TopSection(currentUser.value?.message?.name ?: "-")
            DailyMoodSection(dataToday)
            ConsultationSection(Modifier.clickable { navigateToConsultation(0) })
            TitleSection("Konsultasi", "Lihat Semua", navigateToConsultation)

            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                items(MenuConsultationData.consultations, key = { it.id }) { consultation ->
                    MenuConsultation(
                        modifier,
                        consultation.cardColor,
                        consultation.arrowColor,
                        painterResource(consultation.icon),
                        consultation.name
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            TitleSection("Artikel terbaru", "Lihat Semua", navigateToArticle)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                items(articleViewModel.articles, key = { it.id }) { article ->
                    MenuArticle(article, navigateToDetail)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}

@Composable
fun TopSection(name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Hai ibu hebat,"
            )
            Text(
                text = name,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_baseline_notifications_24),
            contentDescription = "Banner Image",
        )
//        AsyncImage(
//            model = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKoAAACUCAMAAAA02EJtAAAAMFBMVEXk5ueutLfn6eqqsLTM0NLq7O26v8KnrrHf4eLHy82xt7rS1dfY29zU2Nnb3t/DyMryEXPkAAAD7klEQVR4nO2c2XLkIAwAQdz4+v+/XRg7GydxPICwoCr0U3bz0iULcYkwNhgMBoPBYDAYDAaDwaArlAJm54B9/dgtwGanPT/wWlro0RbYOmlh+Jnwz21hndmCmjcv+E+E0M6q1nonwOoLzf+4bvIA7GbuTLngUx9pAMvlp/8qq20Hrmp75/nCz81drX4b0h0jm5umiUZX1zSukCwaEC1drX8veI7r1s41/esfrrKRq9oSR9SJNnUApvvCf4lfW5iu+aJxLmgR1txE3TELuSvI/ETdseSqhaJcUFes8qDSj6xS0RhWUlFEUIMrqSpLXU9dQTtnzeWiAU+oCg4RVE46vULeiuoHjm4Pawtm/zOazBQ1/iN+plJVZdP/J4JsIaCQplyQbQktWpVswsJV1QjVuIIJreqpVCValVOpOrSpIFpfQ9op1R1mqP5tVbJcxVcAqo0AerXCyYoVW9CmZKtA/BqATBWwCSAclapC7le4mKjWq+iZlW4XwFbs3orwiA0ZVcITNig4Wj9Deca64FTpRLNvgb4iNOWtO+okiGpZdagW3Vkc0J2tvECcWoiFVpWx4tJKHFRMvaK/ECwsArTDf6f04KLFbWBZCpCPqZ0C01bdC9nFtc1lcAQyN1mC6lTtyjWvJcCT3wSfUBlloHVbGCzJ5VW3brYD+77NLmJoL4F/IekIi2yPeot637/YRfdiBKwTN7LGN5qiLoHfs0D4qbXdNxST2n+Lbfin35ZuWoI/iY3hmxbGiJelMVy7pc8edsZehdMuUjonp9ke/9EpEFA78ccuTeF4ZjFNMaYBKeW0zOv+i24I0YtZ6i+nWK83OXeQCADWLk7zfSj9Wq7Cr8MIW9sNMVA2xvKu+H8pXCG+UxNbYFPJqUV8gkOqCXbSt9/8Jrom2FLlLrDl+mlNKsZvJA9FACbspUXEy6dlwcrEYfQOIdyTWRuWJB55YXGW9c/JKlnj05/x8pniNet6Ef1APHCDcbN2RspulbMgYftUjJcVjzFDSOt/+09MvU0irJh25QSEr5SxKv0ApZwqSQDuyY//QZVmBuR1aoYrMgng4TQ9uyKng+dq1E88qhCQxTSCOYCt0KaW6Vqqim1QKKCwDoCkqFLfKLosAnxHdQFFbU2JB+fVXfNvYRTp4D+Rna6AbKRBkPsaC9VHgyQvBbAPqjDkdgw0E+WxYygjBUoeflcka9JqUPzPpG8KVMNMjeQ8HGo4/HdSi0CFlz9I0l+6th1UEZ9Yr1qW/wOTGNS5eVC5SMyACo8p0KTNWOinnzVI6x1Sjev/i7S/dwBdqCZNWNim/zokjavJiPak/ZmmVfZA2tMR6IIk1cEf4h/bxDWtKov23AAAAABJRU5ErkJggg==",
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(30.dp)
//                .clip(CircleShape)
//        )
        Image(
            painter = painterResource(R.drawable.empty_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun DailyMoodSection(dataToday: HistoryDailyMood?) {
    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .clickable {
                context.startActivity(
                    Intent(context, UploadFaceMoodActivity::class.java)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            if(dataToday != null){
                if(dataToday.hasil == "Mood Baik"){
                    Image(
                        painter = painterResource(R.drawable.happy_reaction),
                        contentDescription = "Banner Image",
                        modifier = Modifier.size(45.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Anda telah mengisi",
                            fontSize = 14.sp,
                        )
                        Text(
                            text = "Mood Baik",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                else if(dataToday.hasil == "Mood Sedang"){
                    Image(
                        painter = painterResource(R.drawable.flat_reaction),
                        contentDescription = "Banner Image",
                        modifier = Modifier.size(45.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Anda telah mengisi",
                            fontSize = 14.sp,
                        )
                        Text(
                            text = "Mood Sedang",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                else{
                    Image(
                        painter = painterResource(R.drawable.angry_reaction),
                        contentDescription = "Banner Image",
                        modifier = Modifier.size(45.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "Anda telah mengisi",
                            fontSize = 14.sp,
                        )
                        Text(
                            text = "Mood Buruk",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

            }
            else{
                Image(
                    painter = painterResource(R.drawable.empty_expression),
                    contentDescription = "Banner Image",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Anda belum mengisi",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Ayo isi daily mood anda",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Column(Modifier.weight(1f)) {
//                Text(
//                    text = "Anda telah mengisi",
//                    fontSize = 14.sp,
//                )
//                Text(
//                    text = "Mood Buruk",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                )
//            }
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
fun TitleSection(name: String, action: String, navigate: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 40.dp)
    ) {
        Text(
            text = name,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = action,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor,
            modifier = Modifier.clickable { navigate(0) }
        )
    }
}

@Composable
fun ConsultationSection(modifier: Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = LightYellow
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.image_consultation_home_section),
                contentDescription = "Banner Image",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Butuh teman cerita?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Tenang, kamu gak sendirian kok. Ada kami yang siap mendengar ceritamu.",
                    fontSize = 14.sp,
                    maxLines = 3,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.ic_baseline_arrow_forward_24),
                contentDescription = "Banner Image",
                colorFilter = ColorFilter.tint(color = Color.Gray),
            )
        }
    }
}

@Composable
fun MenuConsultation(
    modifier: Modifier = Modifier,
    cardColor: Color,
    arrowColor: Color,
    icon: Painter,
    name: String?
) {
    ElevatedCard(
        modifier = modifier
            .padding(top = 20.dp)
            .width(160.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(10.dp)) {

            Column(Modifier.weight(1f)) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = name ?: "-",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
            }
            Image(
                painter = painterResource(R.drawable.arrow_consultation_menu),
                contentDescription = "Banner Image",
                colorFilter = ColorFilter.tint(color = arrowColor),
                modifier = modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun MenuArticle(
    article: Article,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .width(260.dp)
            .clickable { navigateToDetail(article.id) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = article.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun MenuArticlePreview() {
    MaterialTheme {
//        MenuArticle(
//            modifier = Modifier.padding(8.dp)
//        )
    }
}

@Composable
@Preview(showBackground = true)
fun MenuConsultationPreview() {
    MaterialTheme {
        MenuConsultation(
            modifier = Modifier.padding(8.dp),
            PurpleimeManagement,
            PrimaryColor,
            painterResource(R.drawable.clock_consultation_menu),
            "Manajemen Waktu"
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DailyMoodSectionPreview() {
//    DailyMoodSection()
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ConsultationSectionPreview() {
    ConsultationSection(Modifier)
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
//    HomeScreen()
}