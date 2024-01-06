package com.dicoding.picodiploma.nurtur.widget

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.ui.theme.LightYellow

@Composable
fun ConsultationCard(modifier: Modifier = Modifier, color: Color, name: String, avatar: Painter, date: String, time: String, category: String, status: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Column(modifier = modifier.padding(10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.empty_expression),
                    contentDescription = "Banner Image",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row(modifier = modifier.padding(top = 5.dp, end = 10.dp)) {
                        Image(
                            painter = avatar,
                            contentDescription = "Banner Image",
                        )
                        Text(
                            text = date,
                            fontSize = 10.sp,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_baseline_clock),
                            contentDescription = "Banner Image",
                        )
                        Text(
                            text = time,
                            fontSize = 10.sp,
                        )
                    }
                    TagLine(modifier, category)
                }
            }
            CustomButton(modifier, status, color)
        }
    }
}

@Composable
fun TagLine(modifier: Modifier = Modifier, tagLine: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = LightYellow
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .height(20.dp)
            .width(95.dp)
            .padding(top = 5.dp)
    ) {
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Text(
                text = tagLine,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

//@Composable
//fun ButtonHistory(modifier: Modifier = Modifier, status: String, color: Color) {
//    ElevatedCard(
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 0.dp
//        ),
//        colors = CardDefaults.cardColors(
//            containerColor = color
//        ),
//        shape = RoundedCornerShape(5.dp),
//        modifier = Modifier
//            .height(50.dp)
//    ) {
//        Box(modifier = modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center) {
//            Text(
//                text = status,
//                fontSize = 14.sp,
//                textAlign = TextAlign.Center,
//            )
//        }
//    }
//}