package org.d3if3047.assessment2.ui.histori

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3047.assessment2.R
import org.d3if3047.assessment2.databinding.ItemHistoriBinding
import org.d3if3047.assessment2.db.DiskonEntity
import org.d3if3047.assessment2.model.hitungDiskon
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<DiskonEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DiskonEntity>() {
                override fun areItemsTheSame(
                    oldData: DiskonEntity, newData: DiskonEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: DiskonEntity, newData: DiskonEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        @SuppressLint("StringFormatMatches")
        fun bind(item: DiskonEntity) = with(binding) {
            val hasilDiskon = item.hitungDiskon()
            kategoriTextView.text = hasilDiskon.total.toString().substring(0, 1)
            val colorRes = when(hasilDiskon.total) {
                else -> R.color.hasil
            }

            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            diskonTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilDiskon.total.toString())
            dataTextView.text = root.context.getString(R.string.data_x, item.harga, item.diskon)
        }
    }
}