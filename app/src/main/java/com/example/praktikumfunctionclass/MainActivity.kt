package com.example.praktikumfunctionclass

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtJurusan = findViewById<EditText>(R.id.edtJurusan)
        val edtNilai = findViewById<EditText>(R.id.edtNilai)
        val btnProses = findViewById<Button>(R.id.btnProses)
        val txtHasil = findViewById<TextView>(R.id.txtHasil)

        btnProses.setOnClickListener {
            val nama = edtNama.text.toString()
            val nim = edtNim.text.toString()
            val jurusan = edtJurusan.text.toString()
            val nilaiText = edtNilai.text.toString()

            if (nama.isEmpty() || nim.isEmpty() || jurusan.isEmpty() || nilaiText.isEmpty()) {
                txtHasil.text = "Semua data harus diisi"
            } else {
                val nilai = nilaiText.toIntOrNull() ?: 0
                val mahasiswa = Mahasiswa(nama, nim, jurusan, nilai)
                val grade = hitungGrade(mahasiswa.nilai)
                val status = tentukanStatus(mahasiswa.nilai)
                val pesan = tentukanPesan(mahasiswa.nilai)
                txtHasil.text = tampilkanDataMahasiswa(mahasiswa, grade, status, pesan)
            }
        }
    }

    fun hitungGrade(nilai: Int): String {
        return when {
            nilai >= 90 -> "A"
            nilai >= 80 -> "B"
            nilai >= 70 -> "C"
            nilai >= 60 -> "D"
            else -> "E"
        }
    }

    fun tentukanStatus(nilai: Int): String {
        return if (nilai >= 75) "LULUS" else "TIDAK LULUS"
    }

    fun tentukanPesan(nilai: Int): String {
        return if (nilai >= 75) "Selamat, Anda berhasil" else "Tetap semangat belajar"
    }

    fun tampilkanDataMahasiswa(mahasiswa: Mahasiswa, grade: String, status: String, pesan: String): String {
        return """
            Nama : ${mahasiswa.nama}
            NIM : ${mahasiswa.nim}
            Jurusan : ${mahasiswa.jurusan}
            Nilai : ${mahasiswa.nilai}
            Grade : $grade
            Status : $status
            Pesan : $pesan
        """.trimIndent()
    }
}

class Mahasiswa(val nama: String, val nim: String, val jurusan: String, val nilai: Int)