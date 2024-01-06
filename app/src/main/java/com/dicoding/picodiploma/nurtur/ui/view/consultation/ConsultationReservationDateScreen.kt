package com.dicoding.picodiploma.nurtur.ui.view.consultation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.DateAppointment
import com.dicoding.picodiploma.nurtur.data.DateAppointmentData
import com.dicoding.picodiploma.nurtur.data.TimeAppointment
import com.dicoding.picodiploma.nurtur.data.TimeAppointmentData
import com.dicoding.picodiploma.nurtur.ui.theme.PrimaryColor
import com.dicoding.picodiploma.nurtur.utils.DATE_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.DOKTER_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.LABEL_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.PHOTO_APPOINMENT
import com.dicoding.picodiploma.nurtur.utils.TIME_APPOINMENT
import com.dicoding.picodiploma.nurtur.widget.CustomButton

var tanggal: String = ""
var waktu: String = ""

@Composable
fun ConsultationReservationDateScreen(photoUrl: String, dokter: String, label: String) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopSection()
        SectionText("Pilih Tanggal", modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp))
        DateColumn()
        SectionText(
            "Pilih Waktu (durasi 1 jam)",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )
        TimeColumn()
        SectionText("Metode", modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp))
        MethodColumn(dokter, photoUrl, label)
    }
}

@Composable
fun DateColumn() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        DateRow()
//        DateRow()
//        DateRow()
//        DateRow()
        val chunkedDates = DateAppointmentData.appointment.chunked(2)
        chunkedDates.forEach { dateRow ->
            DateRow(dateRow)
        }
    }
}

@Composable
fun TimeColumn() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        TimeRow()
//        TimeRow()
//        TimeRow()
        val chunkedTimes = TimeAppointmentData.appointment.chunked(3)
        chunkedTimes.forEach { timeRow ->
            TimeRow(timeRow)
        }
    }
}

@Composable
fun MethodColumn(nama: String, photo: String, label: String) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MethodRow()
        CustomButton(
            Modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    val intent =
                        Intent(context, ConsultationReservationQuisionerActivity::class.java)
                    intent.putExtra(DOKTER_APPOINMENT, nama)
                    intent.putExtra(PHOTO_APPOINMENT, photo)
                    intent.putExtra(LABEL_APPOINMENT, label)
                    intent.putExtra(DATE_APPOINMENT, tanggal)
                    intent.putExtra(TIME_APPOINMENT, waktu)
                    context.startActivity(intent)
                },
            "Selanjutnya",
            PrimaryColor
        )
    }
}

@Composable
fun TopSection(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .requiredSize(24.dp)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
//        Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Buat Appointment",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}

@Composable
fun SectionText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
//fun DateRow(
//    modifier: Modifier = Modifier,
//) {
fun DateRow(dates: List<DateAppointment>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
//        DateCard()
//        DateCard()
        dates.forEach { date ->
            DateCard(date)
        }
    }
}

@Composable
//fun DateCard(modifier: Modifier = Modifier) {
fun DateCard(appointment: DateAppointment, modifier: Modifier = Modifier) {
    var isClicked by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isClicked) 8.dp else 1.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, end = 10.dp, start = 10.dp)
            .height(52.dp)
            .width(167.dp)
            .clickable {
                // Mengubah status klik saat tombol diklik
                isClicked = !isClicked;
                if (isClicked) {
                    tanggal = appointment.tanggal
                }
            }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(if (isClicked) Color.Magenta else Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = appointment.tanggal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = if (isClicked) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
//fun TimeCard() {
fun TimeCard(appointment: TimeAppointment) {
    var isClicked by remember { mutableStateOf(false) }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isClicked) 8.dp else 1.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, end = 10.dp, start = 10.dp)
            .height(52.dp)
            .width(90.dp)
            .clickable {
                isClicked = !isClicked;
                if (isClicked) {
                    tanggal = appointment.waktu
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isClicked) Color.Magenta else Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = appointment.waktu,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
//fun TimeRow(
//    modifier: Modifier = Modifier
//) {
fun TimeRow(times: List<TimeAppointment>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
//        TimeCard()
//        TimeCard()
//        TimeCard()
        times.forEach { time ->
            TimeCard(time)
        }
    }
}

@Composable
//fun MethodCard(title: String) {
fun MethodCard(painter: Painter, title: String) {
    var isClicked by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isClicked) 8.dp else 1.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(52.dp)
            .width(167.dp)
            .clickable {
                // Mengubah status klik saat kartu diklik
                isClicked = !isClicked
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isClicked) Color.Magenta else Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .requiredSize(30.dp)
                    .clip(CircleShape)
            )
            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = if (isClicked) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun MethodRow(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
//        MethodCard("Chat")
//        MethodCard("Telepon")
        MethodCard(painterResource(R.drawable.baseline_chat_24), "Chat")
        MethodCard(painterResource(R.drawable.baseline_phone_enabled_24), "Telepon")
    }
}

@Composable
@Preview(showBackground = true)
fun DateCardPreview() {
    DateCard(appointment = DateAppointment(1, ""))
}

@Composable
@Preview(showBackground = true)
fun MethodCardPreview() {
    MethodCard(painterResource(R.drawable.ic_baseline_clock), "Chat")
}

@Composable
@Preview(showBackground = true)
fun ConsultationReservationScreenPreview() {
    ConsultationReservationDateScreen("", "", "")
}