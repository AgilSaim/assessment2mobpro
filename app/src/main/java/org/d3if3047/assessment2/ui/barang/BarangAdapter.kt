package org.d3if3047.assessment2.ui.barang

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3047.assessment2.R
import org.d3if3047.assessment2.databinding.ItemBarangBinding
import org.d3if3047.assessment2.model.Barang
import org.d3if3047.assessment2.network.BarangApi

class BarangAdapter : RecyclerView.Adapter<BarangAdapter.ViewHolder>() {

    private val data = mutableListOf<Barang>()
    fun updateData(newData: List<Barang>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemBarangBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(barang: Barang) = with(binding) {
            tvNamaBarang.text = barang.namabarang
            tvJumlahBarang.text = barang.hargabarang.toString()
            tvDiskon.text = barang.diskon.toString()
            Glide.with(imageView.context)
            Glide.with(root.context)
                .load(barang.img)
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, barang.namabarang)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBarangBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
