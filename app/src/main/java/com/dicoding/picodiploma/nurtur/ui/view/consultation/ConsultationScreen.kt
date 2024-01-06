package com.dicoding.picodiploma.nurtur.ui.view.consultation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.MenuConsultationData
import com.dicoding.picodiploma.nurtur.ui.theme.YellowArrow
import com.dicoding.picodiploma.nurtur.widget.TabLayout
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConsultationScreen(konsultasiViewModel : KonsultasiViewModel) {
    Column {
        SectionText("Manajemen Waktu")
        CategoryRow()
        SectionText("Konsultasiku")
        TabLayout(konsultasiViewModel)
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(MenuConsultationData.consultations, key = { it.id }) { consultation ->
            CategoryItem(
                modifier = Modifier.padding(horizontal = 8.dp),
                painterResource(consultation.icon),
                consultation.cardColor,
                consultation.name
            )
        }
    }
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = modifier
//    ) {
//        CategoryItem(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            painterResource(R.drawable.ic_baseline_clock),
//            "Manajemen Waktu"
//        )
//        CategoryItem(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            painterResource(R.drawable.ic_baseline_clock),
//            "Manajemen Waktu"
//        )
//        CategoryItem(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            painterResource(R.drawable.ic_baseline_clock),
//            "Manajemen Waktu"
//        )
//        CategoryItem(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            painterResource(R.drawable.ic_baseline_clock),
//            "Manajemen Waktu"
//        )
//    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    cardColor: Color,
    category: String
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.clickable { context.startActivity(
            Intent(context,ConsultationReservationPsikologActivity::class.java )
        ) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = modifier
                .padding(top = 20.dp)
                .width(60.dp).height(60.dp),
            shape = RoundedCornerShape(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = cardColor
            ),
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(40.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center
            )
        }

        Text(
            text = category,
            fontSize = 10.sp,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryItemPreview() {
    CategoryItem(
        modifier = Modifier.padding(horizontal = 8.dp),
        painterResource(R.drawable.emotion_consultation_menu),
        YellowArrow,
        "Manajemen Waktu"
    )
}

@Composable
@Preview(showBackground = true)
fun ConsultationScreenPreview() {
    lateinit var konsultasiViewModel: KonsultasiViewModel
    ConsultationScreen(konsultasiViewModel)
}