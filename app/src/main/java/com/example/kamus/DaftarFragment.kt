package com.example.kamus

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class DaftarFragment : Fragment(R.layout.fragment_daftar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = KamusDatabase.getInstance(requireContext()).kataDao()
        val txtJumlah = view.findViewById<TextView>(R.id.txtJumlah)
        val rvDaftar = view.findViewById<RecyclerView>(R.id.rvDaftar)
        val adapter = KataAdapter()
        rvDaftar.layoutManager = LinearLayoutManager(requireContext())
        rvDaftar.adapter = adapter

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val posisi = viewHolder.bindingAdapterPosition
                if (posisi != RecyclerView.NO_POSITION) {
                    val kata = adapter.currentList[posisi]
                    viewLifecycleOwner.lifecycleScope.launch {
                        dao.hapus(kata)
                        Snackbar.make(view, "Kata '${kata.indonesia}' dihapus", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(rvDaftar)

        viewLifecycleOwner.lifecycleScope.launch {
            dao.ambilSemua().collect { daftar ->
                adapter.submitList(daftar)
                txtJumlah.text = "Jumlah kata: ${daftar.size}"
            }
        }
    }
}
