package com.dicoding.picodiploma.nurtur.ui.view.consultation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nurtur.R
import com.dicoding.picodiploma.nurtur.data.Psikolog
import com.dicoding.picodiploma.nurtur.data.PsikologData
import com.dicoding.picodiploma.nurtur.databinding.ItemPsikologBinding

class PsikologAdapter(
    private val psikologClickListener: PsikologClickListener
) : RecyclerView.Adapter<PsikologAdapter.ListViewHolder>() {

    interface PsikologClickListener {
        fun onPsikologClick(psikolog: Psikolog)
    }

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPsikologBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (position < PsikologData.piskolog.size) {
            val psikol = PsikologData.piskolog[position]
            holder.bind(psikol)

            holder.itemView.setOnClickListener {
                // Update selected position and notify data set changed to refresh the views
                selectedPosition = holder.adapterPosition
                notifyDataSetChanged()

                psikologClickListener.onPsikologClick(psikol)
            }

            // Change background drawable based on the selected position
            if (selectedPosition == position) {
                holder.itemView.setBackgroundResource(R.drawable.item_border)
            } else {
                holder.itemView.setBackgroundResource(R.drawable.item_border_default)
            }
        }
    }

    override fun getItemCount(): Int = PsikologData.piskolog.size

    inner class ListViewHolder(val binding: ItemPsikologBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(psiko: Psikolog) {
            Glide.with(binding.root.context)
                .load(psiko.imaga_url) // URL Gambar
                .into(binding.imageView2)
            binding.dokterName.text = psiko.nama
            binding.spesialis.text = psiko.spesialis
            binding.pengalaman.text = psiko.exp
            binding.dokterDesc.text = psiko.Detail
        }
    }
}