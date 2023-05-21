package org.d3if3047.assessment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if3047.assessment2.databinding.ActivityMainBinding
import org.d3if3047.assessment2.model.HasilDiskon

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DiskonViewModel by lazy {
        ViewModelProvider(this)[DiskonViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungDiskon() }
        viewModel.getHasilDiskon().observe(this, { showResult(it) })
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
        viewModel.hitungDiskon(
            harga.toInt(),
            diskon.toInt()
        )
    }
    private fun showResult(result: HasilDiskon?) {
        if (result == null) return
        binding.totalTextView.text = getString(R.string.total, result.total.toString())
    }
}