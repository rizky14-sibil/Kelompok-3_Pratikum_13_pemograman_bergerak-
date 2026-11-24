package com.example.kamus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class KataAdapter : ListAdapter<Kata, KataAdapter.KataViewHolder>(DIFF) {

    class KataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtIndonesia: TextView = view.findViewById(R.id.txtIndonesia)
        val txtInggris: TextView = view.findViewById(R.id.txtInggris)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kata, parent, false)
        return KataViewHolder(view)
    }

    override fun onBindViewHolder(holder: KataViewHolder, position: Int) {
        val kata = getItem(position)
        holder.txtIndonesia.text = kata.indonesia
        holder.txtInggris.text = kata.inggris
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Kata>() {
            override fun areItemsTheSame(oldItem: Kata, newItem: Kata) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Kata, newItem: Kata) =
                oldItem == newItem
        }
    }
}
