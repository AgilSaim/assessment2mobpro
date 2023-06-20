package org.d3if3047.assessment2.ui.barang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3047.assessment2.databinding.FragmentBarangBinding
import org.d3if3047.assessment2.network.BarangApi

class BarangFragment : Fragment() {

    private val viewModel: BarangViewModel by lazy {
        ViewModelProvider(this)[BarangViewModel::class.java]
    }

    private lateinit var binding: FragmentBarangBinding
    private lateinit var myAdapter: BarangAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentBarangBinding.inflate(layoutInflater, container, false)
        myAdapter = BarangAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun updateProgress(status: BarangApi.ApiStatus) {
        when (status) {
            BarangApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            BarangApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            BarangApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}