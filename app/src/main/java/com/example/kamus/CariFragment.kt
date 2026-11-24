package com.example.kamus

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CariFragment : Fragment(R.layout.fragment_cari) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = KamusDatabase.getInstance(requireContext()).kataDao()
        val etCari = view.findViewById<EditText>(R.id.etCari)
        val txtKosong = view.findViewById<TextView>(R.id.txtKosong)
        val rvHasil = view.findViewById<RecyclerView>(R.id.rvHasil)
        val adapter = KataAdapter()
        rvHasil.layoutManager = LinearLayoutManager(requireContext())
        rvHasil.adapter = adapter

        var pencarianJob: Job? = null
        fun cariKata(teks: String) {
            pencarianJob?.cancel()
            pencarianJob = viewLifecycleOwner.lifecycleScope.launch {
                val hasil = dao.cari("%${teks.trim()}%")
                adapter.submitList(hasil)
                txtKosong.visibility = if (hasil.isEmpty()) View.VISIBLE else View.GONE
            }
        }
        cariKata("")
        etCari.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cariKata(s?.toString().orEmpty())
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
    }
}
