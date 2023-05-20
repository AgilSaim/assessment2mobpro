package org.d3if3047.assessment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if3047.assessment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungDiskon() }
    }

    private fun hitungDiskon() {
        val harga = binding.hargaEditText.text.toString()
        val diskon = binding.diskonEditText.text.toString()

        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(this, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(diskon)) {
            Toast.makeText(this, R.string.diskon_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val txtHarga = harga.toInt()
        val txtDiskon = diskon.toInt()

        val potongan = txtHarga * txtDiskon / 100
        val hasil = txtHarga - potongan

        binding.totalTextView.text = getString(R.string.total, hasil.toString())
    }
}