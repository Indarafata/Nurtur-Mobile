package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.picodiploma.nurtur.data.DailyMoodQuestion
import com.dicoding.picodiploma.nurtur.data.DailyMoodQuestionData
import com.dicoding.picodiploma.nurtur.ui.theme.PrimaryColor
import com.dicoding.picodiploma.nurtur.utils.FACE_DETECTION
import com.dicoding.picodiploma.nurtur.utils.WEIGHT_RESULT
import com.dicoding.picodiploma.nurtur.widget.CustomButton


val totalBobot = mutableListOf<Int>()
val answeredList = mutableListOf<CheckAnswer>()
var hitung: Int = 0
var isOpenDialog = false

@Composable
fun DailyMoodQuizScreen(faceDetectionResult: String, name: String) {
    var selectedOption1 by remember { mutableStateOf(Option.None) }
    var selectedOption2 by remember { mutableStateOf(Option.None) }
    var selectedOption3 by remember { mutableStateOf(Option.None) }
    var selectedOption4 by remember { mutableStateOf(Option.None) }
    var selectedOption5 by remember { mutableStateOf(Option.None) }
    var selectedOption6 by remember { mutableStateOf(Option.None) }
    var selectedOption7 by remember { mutableStateOf(Option.None) }
    var selectedOption8 by remember { mutableStateOf(Option.None) }
    var selectedOption9 by remember { mutableStateOf(Option.None) }
    var selectedOption10 by remember { mutableStateOf(Option.None) }
    var selectedOption11 by remember { mutableStateOf(Option.None) }

    var answeredQuestions by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(15.dp)) {
        Row {
            Text(
                text = "Halo ",
                fontSize = 14.sp,
            )
            Text(
                text = name ?: "-",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Text("Pertanyaan ${answeredQuestions} dari 11")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(DailyMoodQuestionData.questions, key = { it.id }) { quiz ->

                if (quiz.id < 12) {
                    totalBobot.add(0)
                }
                answeredQuestions = hitung
                when (quiz.id) {
                    1 -> {

                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption1,
                            onOptionSelected = { option ->
                                selectedOption1 = option
                            }
                        )
                    }

                    2 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption2,
                            onOptionSelected = { option ->
                                selectedOption2 = option
                            }
                        )
                    }

                    3 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption3,
                            onOptionSelected = { option ->
                                selectedOption3 = option
                            }
                        )
                    }

                    4 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption4,
                            onOptionSelected = { option ->
                                selectedOption4 = option
                            }
                        )
                    }

                    5 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption5,
                            onOptionSelected = { option ->
                                selectedOption5 = option
                            }
                        )
                    }

                    6 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption6,
                            onOptionSelected = { option ->
                                selectedOption6 = option
                            }
                        )
                    }

                    7 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption7,
                            onOptionSelected = { option ->
                                selectedOption7 = option
                            }
                        )
                    }

                    8 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption8,
                            onOptionSelected = { option ->
                                selectedOption8 = option
                            }
                        )
                    }

                    9 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption9,
                            onOptionSelected = { option ->
                                selectedOption9 = option
                            }
                        )
                    }

                    10 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption10,
                            onOptionSelected = { option ->
                                selectedOption10 = option
                            }
                        )
                    }

                    11 -> {
                        RadioGroup(
                            quiz,
                            selectedOption = selectedOption11,
                            onOptionSelected = { option ->
                                selectedOption11 = option
                            }
                        )
                        AlertDialogSample(faceDetectionResult)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun RadioGroup(
    quiz: DailyMoodQuestion,
    selectedOption: Option,
    onOptionSelected: (Option) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(5.dp)
            .height(271.dp)
            .width(340.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = quiz.pertanyaan,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier.padding(8.dp)
            )
            RadioButtonOption(
                quiz = quiz,
                option = Option.SangatSetuju,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
            )
            RadioButtonOption(
                quiz = quiz,
                option = Option.Setuju,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
            RadioButtonOption(
                quiz = quiz,
                option = Option.TidakSetuju,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
            RadioButtonOption(
                quiz = quiz,
                option = Option.SangatTidakSetuju,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )

        }
    }
}

@Composable
fun RadioButtonOption(
    quiz: DailyMoodQuestion,
    option: Option,
    selectedOption: Option,
    onOptionSelected: (Option) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onOptionSelected(option);
                saveWeightCountQuestion(quiz, option)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = {
                onOptionSelected(option);
                saveWeightCountQuestion(quiz, option)
            },
        )
        Text(text = option.label)
    }
}

fun saveWeightCountQuestion(
    quiz: DailyMoodQuestion,
    option: Option
) {
    var check: Boolean = false;

    if (answeredList.isEmpty()) {
        hitung++
        answeredList.add(CheckAnswer(quiz.id, true))
    }

    for (dataAnswered in answeredList) {
        if (dataAnswered.quizId == quiz.id) {
            check = true
        }
    }

    if (!check) {
        hitung++
        answeredList.add(CheckAnswer(quiz.id, true))
    }

    when (option) {
        Option.SangatSetuju -> totalBobot[quiz.id - 1] = quiz.boboSangatSetuju
        Option.Setuju -> totalBobot[quiz.id - 1] = quiz.bobotSetuju
        Option.TidakSetuju -> totalBobot[quiz.id - 1] = quiz.bobotTidakSeuju
        Option.SangatTidakSetuju -> totalBobot[quiz.id - 1] = quiz.bobotSangatTidakSeuju
        else -> ""
    }
}

@Composable
fun AlertDialogSample(faceDetectionResult: String) {
    MaterialTheme {
        val context = LocalContext.current
        Column {
            val openDialog = remember { mutableStateOf(false) }

            CustomButton(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        var countWeight = 0;
                        for (data in totalBobot) {
                            countWeight += data
                        }
                        if (countWeight < 11) {
                            openDialog.value = true
                        }else {
                            val intent = Intent(context, ResultDailyMoodActivity::class.java)
                            intent.putExtra(FACE_DETECTION, faceDetectionResult)
                            intent.putExtra(WEIGHT_RESULT, countWeight)
                            context.startActivity(intent)
                        }
                    },
                "Selanjutnya",
                PrimaryColor
            )

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                        isOpenDialog = false
                    },
                    title = {
                        Text(text = "Dialog Title Will Show Here")
                    },
                    text = {
                        Text("Here is a description text of the dialog")
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Dismiss Button")
                        }
                    }
                )
            }
        }

    }
}

enum class Option(val label: String) {
    SangatSetuju("Sangat Setuju"),
    Setuju("Setuju"),
    TidakSetuju("Tidak Setuju"),
    SangatTidakSetuju("Sangat Tidak Setuju"),
    None("")
}

@Preview(showBackground = true)
@Composable
fun PreviewDailyMoodQuizScreen() {
    DailyMoodQuizScreen("", "")
}

data class CheckAnswer(
    var quizId: Int,
    var isChecked: Boolean
)