package org.d3if3047.assessment2.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3047.assessment2.R
import org.d3if3047.assessment2.databinding.FragmentHitungBinding
import org.d3if3047.assessment2.db.DiskonDb
import org.d3if3047.assessment2.model.HasilDiskon

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = DiskonDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungDiskon() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilDiskon().observe(requireActivity(), { showResult(it) })
    }

    private fun hitungDiskon() {
        val harga = binding.hargaEditText.text.toString()
        val diskon = binding.diskonEditText.text.toString()
        val total = binding.diskonEditText.text.toString()

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
            diskon.toInt(),
            total.toInt()
        )
    }

    private fun showResult(result: HasilDiskon?) {
        if (result == null) return
        binding.totalTextView.text = getString(R.string.total, result.total.toString())
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.hargaEditText.text,
            binding.diskonEditText.text,
            binding.totalTextView.text,
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}