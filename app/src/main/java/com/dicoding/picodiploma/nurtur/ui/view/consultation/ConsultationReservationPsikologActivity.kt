package com.dicoding.picodiploma.nurtur.ui.view.consultation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.Psikolog
import com.dicoding.picodiploma.nurtur.databinding.ActivityConsultationReservationPsikologBinding

class ConsultationReservationPsikologActivity : AppCompatActivity(), PsikologAdapter.PsikologClickListener {

    private lateinit var adapter: PsikologAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityConsultationReservationPsikologBinding
    private val psikologAdapter = PsikologAdapter(this)
    private lateinit var photoUrl : String
    private lateinit var dokter : String
    private lateinit var label : String

    override fun onPsikologClick(psikolog: Psikolog) {
        photoUrl = psikolog.imaga_url
        dokter = psikolog.nama
        label = psikolog.spesialis

        Toast.makeText(this, "Anda memilih ${psikolog.nama}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationReservationPsikologBinding.inflate(layoutInflater)
        setContentView(binding.root)





        recyclerView = findViewById(R.id.rvKonsul)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = psikologAdapter

        binding.buttonReservationPsikolog.setOnClickListener {
            setContent { ConsultationReservationDateScreen(photoUrl, dokter, label) }
        }
    }
}