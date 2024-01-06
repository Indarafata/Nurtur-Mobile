package com.dicoding.picodiploma.nurtur.ui.view.dailyMood

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.AdapterQuizData
import com.dicoding.picodiploma.nurtur.data.DailyMoodQuestion
import com.dicoding.picodiploma.nurtur.databinding.ItemQuizDailyBinding

class QuizAdapter(
    private val questions: List<DailyMoodQuestion>,
    private val onAnswerSelected: (DailyMoodQuestion, Int) -> Unit
) : RecyclerView.Adapter<QuizAdapter.ListViewHolder>() {

    var totalBobot: Int = 0
    var bobot: Int = 0
    var numberQuestion: Int = 0
    val quizDataList = mutableListOf<AdapterQuizData>()
    lateinit var copyQuizDataList: List<AdapterQuizData>
    private val radioStatusMap: HashMap<Int, Int> = HashMap()

//    init {
//        setHasStableIds(true)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemQuizDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = questions.size

    inner class ListViewHolder(val binding: ItemQuizDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: DailyMoodQuestion) {
            binding.question.text = quiz.pertanyaan
            // Simpan referensi RadioButton dalam list
            val radioButtons = mutableListOf<RadioButton>()
            var copyRadioButtons = mutableListOf<RadioButton>()

            // pengecekan ischecked


            // Mendapatkan referensi ke semua RadioButton di dalam RadioGroup
            if(quizDataList.isEmpty()) {
                for (i in 0 until binding.radioGroup.childCount) {
                    val radioButton = binding.radioGroup.getChildAt(i) as? RadioButton
                    radioButton?.let {
                        radioButtons.add(it)
                    }
                }
            }


            copyRadioButtons = radioButtons.toMutableList()
            println("ini list awal ${quizDataList}")

//            if (quizDataList.size != 0 && quiz.id > quizDataList.size) {
            if (numberQuestion < numberQuestion - 1) {
                for (i in 0..3) {
                    // Lakukan sesuatu dengan nilai i di sini
                    println("Nilai i if: $i ini id ${quiz.id} dan ini size ${quizDataList.size}")
                    copyRadioButtons[i].isChecked = false
                }
            }
            if (quizDataList.size != 0) {
                println("ini di else")
                println("curiga copy ${copyQuizDataList}")
                for (data in copyQuizDataList) {
                    println("ini else ${data.idQuestion}")
                    println("memastikan ${quizDataList.size} sama ${numberQuestion}")
//                    if (data.idQuestion == quiz.id) {
                    if (quiz.radioId != 0) {
                        println("cek ${data.idQuestion} dan ${quiz.id} dan ${quiz.pertanyaan}")
//                        when (data.checkedOption) {
                        when (quiz.radioId) {
                            R.id.quizRadioButtonOption1 -> {
//                                bobot = question.boboSangatSetuju
                                Log.d("when else", "yang 1")
//                                if (data.idQuestion == quiz.id)
                                    copyRadioButtons[0].isChecked = true
                            }

                            R.id.quizRadioButtonOption2 -> {
//                                bobot = question.bobotSetuju
                                Log.d("when else", "yang 2")
//                                if (data.idQuestion == quiz.id)
                                    copyRadioButtons[1].isChecked = true
                            }

                            R.id.quizRadioButtonOption3 -> {
//                                bobot = question.bobotTidakSeuju
                                Log.d("when else", "yang 3")
//                                if (data.idQuestion == quiz.id)
                                    copyRadioButtons[2].isChecked = true
                            }

                            R.id.quizRadioButtonOption4 -> {
//                                bobot = question.bobotSangatTidakSeuju
                                Log.d("when else", "yang 4")
//                                if (data.idQuestion == quiz.id)
                                    copyRadioButtons[3].isChecked = true
                            }

                            else
                            -> ""

                        }
                    }
                }
            }

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                onAnswerSelected(quiz, checkedId)
                if (quiz.id > quizDataList.size - 1) {
                    numberQuestion++
                    radioStatusMap[quiz.id] = quiz.id
                    quizDataList.add(AdapterQuizData(quiz.id, checkedId))
                    copyQuizDataList = quizDataList.toList()
                    quiz.radioId = checkedId
                    println("panjang list ${quizDataList.size}")
                    println("ini list setelah add ${quizDataList}")
                }

                when (checkedId) {
                    R.id.quizRadioButtonOption1 -> {
                        bobot = quiz.boboSangatSetuju
                        Log.d("Masuk", "yang 1")
                    }

                    R.id.quizRadioButtonOption2 -> {
                        bobot = quiz.bobotSetuju
                        Log.d("Masuk", "yang 2")
                    }

                    R.id.quizRadioButtonOption3 -> {
                        bobot = quiz.bobotTidakSeuju
                        Log.d("Masuk", "yang 3")
                    }

                    R.id.quizRadioButtonOption4 -> {
                        bobot = quiz.bobotSangatTidakSeuju
                        Log.d("Masuk", "yang 4")
                    }
                }
            }
            totalBobot += bobot
            Log.d("ini bobot total", totalBobot.toString())
        }
    }
}
//class QuizAdapter(
//    private val questions: List<DailyMoodQuestion>,
//    private val onAnswerSelected: (DailyMoodQuestion, Int) -> Unit
//) : BaseAdapter() {
//
//    var totalBobot: Int = 0
//    var bobot: Int = 0
//    val quizDataList = mutableListOf<AdapterQuizData>()
//    lateinit var copyQuizDataList: List<AdapterQuizData>
//    var checkIsLoop: Boolean = false
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view = convertView ?: ItemQuizDailyBinding.inflate(
//            LayoutInflater.from(parent?.context), parent, false
//        ).root
//        val binding = ItemQuizDailyBinding.bind(view)
//
//        val question = questions[position]
//        binding.question.text = question.pertanyaan
//
//        // Simpan referensi RadioButton dalam list
//        val radioButtons = mutableListOf<RadioButton>()
//        var copyRadioButtons = mutableListOf<RadioButton>()
//        copyQuizDataList = quizDataList.toList()
//
//        // pengecekan ischecked
//
//
//// Mendapatkan referensi ke semua RadioButton di dalam RadioGroup
//        for (i in 0 until binding.radioGroup.childCount) {
//            val radioButton = binding.radioGroup.getChildAt(i) as? RadioButton
//            radioButton?.let {
//                radioButtons.add(it)
//            }
//        }
//
//        copyRadioButtons = radioButtons.toMutableList()
//
//        if (quizDataList.size != 0 && question.id > quizDataList.size - 1) {
////            binding.radioGroup.check(question.answers[quizDataList.])
//            Log.d("haa", "kok masuk sini")
//            for (i in 0..3) {
//                // Lakukan sesuatu dengan nilai i di sini
//                println("Nilai i: $i")
////                val radioButton = binding.radioGroup.getChildAt(i) as RadioButton
////                radioButton.isChecked = false
//                copyRadioButtons[i].isChecked = false
//            }
//            println("ini di if")
//        } else if (quizDataList.size != 0) {
//            checkIsLoop = true
//            println("ini di else")
////            for (i in 0..4) {
////                // Lakukan sesuatu dengan nilai i di sini
////                println("Nilai i else: $i")
////                val radioButton =  binding.radioGroup.getChildAt(i) as RadioButton
//            for (data in copyQuizDataList) {
//                checkIsLoop = true
//                println("ini else ${data.idQuestion}")
//                if (data.idQuestion == question.id) {
//                    // Lakukan aksi
////                        val radiobutton = findViewById<RadioButton>(data.checkedOption)
////
////                        // Lakukan checklis
////                        radiobutton.isChecked = true
////                        binding.radioGroup.check(data.checkeOption)
//                    when (data.checkedOption) {
//                        R.id.quizRadioButtonOption1 -> {
////                                bobot = question.boboSangatSetuju
//                            Log.d("when else", "yang 1")
//                            copyRadioButtons[0].isChecked = true
//                        }
//
//                        R.id.quizRadioButtonOption2 -> {
////                                bobot = question.bobotSetuju
//                            Log.d("when else", "yang 2")
//                            copyRadioButtons[1].isChecked = true
//                        }
//
//                        R.id.quizRadioButtonOption3 -> {
////                                bobot = question.bobotTidakSeuju
//                            Log.d("when else", "yang 3")
//                            copyRadioButtons[2].isChecked = true
//                        }
//
//                        R.id.quizRadioButtonOption4 -> {
////                                bobot = question.bobotSangatTidakSeuju
//                            Log.d("when else", "yang 4")
//                            copyRadioButtons[3].isChecked = true
//                        }
//                    }
//                }
//            }
////            }
//            checkIsLoop = false
//        }
//
//        val id: Int = binding.radioGroup.checkedRadioButtonId
//        if (!checkIsLoop) {
//            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
//                onAnswerSelected(question, checkedId)
//                Log.d(
//                    "ini bisa centang",
//                    "${question.pertanyaan} dan ${checkedId} ini id rill ${R.id.quizRadioButtonOption1}"
//                )
//
//                println("ini id question ${question.id}")
//
////                for (data in quizDataList) {
////                    println("ini else ${data.idQuestion}")
////                    if (data.idQuestion == question.id) {
////
////                    }
////                }
//                if(question.id > quizDataList.size - 1) {
//                    quizDataList.add(AdapterQuizData(question.id, checkedId))
//                }
//
//
//                when (checkedId) {
//                    R.id.quizRadioButtonOption1 -> {
//                        bobot = question.boboSangatSetuju
//                        Log.d("Masuk", "yang 1")
//                    }
//
//                    R.id.quizRadioButtonOption2 -> {
//                        bobot = question.bobotSetuju
//                        Log.d("Masuk", "yang 2")
//                    }
//
//                    R.id.quizRadioButtonOption3 -> {
//                        bobot = question.bobotTidakSeuju
//                        Log.d("Masuk", "yang 3")
//                    }
//
//                    R.id.quizRadioButtonOption4 -> {
//                        bobot = question.bobotSangatTidakSeuju
//                        Log.d("Masuk", "yang 4")
//                    }
//                }
//                totalBobot += bobot
//                Log.d("ini bobot total", totalBobot.toString())
//            }
//        }
//
//        return view
//    }
//
//    override fun getCount(): Int = questions.size
//    override fun getItem(p0: Int): Any {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemId(p0: Int): Long {
//        TODO("Not yet implemented")
//    }
//
//}