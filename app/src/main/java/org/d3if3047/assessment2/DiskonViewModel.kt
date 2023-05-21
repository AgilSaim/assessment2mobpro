package org.d3if3047.assessment2

import androidx.lifecycle.ViewModel
import org.d3if3047.assessment2.model.HasilDiskon

class DiskonViewModel : ViewModel() {

    fun hitungDiskon(txtHarga: Int, txtDiskon: Int): HasilDiskon {
        val potongan = txtHarga * txtDiskon / 100
        val hasil = txtHarga - potongan
        return HasilDiskon(hasil)
    }
}