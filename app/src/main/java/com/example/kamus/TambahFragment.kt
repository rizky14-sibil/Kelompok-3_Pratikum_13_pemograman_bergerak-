package com.example.kamus

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class TambahFragment : Fragment(R.layout.fragment_tambah) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = KamusDatabase.getInstance(requireContext()).kataDao()
        val etIndonesia = view.findViewById<EditText>(R.id.etIndonesia)
        val etInggris = view.findViewById<EditText>(R.id.etInggris)

        view.findViewById<Button>(R.id.btnSimpan).setOnClickListener {
            val indo = etIndonesia.text.toString().trim()
            val ing = etInggris.text.toString().trim()
            if (indo.isEmpty() || ing.isEmpty()) {
                Toast.makeText(requireContext(), "Isi kedua kolom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewLifecycleOwner.lifecycleScope.launch {
                dao.tambah(Kata(indonesia = indo, inggris = ing))
                etIndonesia.text.clear()
                etInggris.text.clear()
                Toast.makeText(requireContext(), "Tersimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
