package com.example.kamus

import androidx.room.Entity
import androidx.room.PrimaryKey

// Satu baris di tabel "kata" berisi satu pasangan kata.
@Entity(tableName = "kata")
data class Kata(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val indonesia: String,
    val inggris: String
)
