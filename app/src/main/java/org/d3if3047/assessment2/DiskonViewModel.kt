package org.d3if3047.assessment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3047.assessment2.model.HasilDiskon

class DiskonViewModel : ViewModel() {

    private val hasilDiskon = MutableLiveData<HasilDiskon?>()
    fun hitungDiskon(txtHarga: Int, txtDiskon: Int) {
        val potongan = txtHarga * txtDiskon / 100
        val hasil = txtHarga - potongan
        hasilDiskon.value = HasilDiskon(hasil)
    }

    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}