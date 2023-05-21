package org.d3if3047.assessment2.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3047.assessment2.R
import org.d3if3047.assessment2.databinding.FragmentHitungBinding
import org.d3if3047.assessment2.model.HasilDiskon3

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: DiskonViewModel by lazy {
        ViewModelProvider(requireActivity())[DiskonViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungDiskon() }
        viewModel.getHasilDiskon().observe(requireActivity(), { showResult(it) })
    }

    private fun hitungDiskon() {
        val harga = binding.hargaEditText.text.toString()
        val diskon = binding.diskonEditText.text.toString()

        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(diskon)) {
            Toast.makeText(context, R.string.diskon_invalid, Toast.LENGTH_LONG).show()
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