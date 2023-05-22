package org.d3if3047.assessment2.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3047.assessment2.db.DiskonDao
import org.d3if3047.assessment2.db.DiskonEntity
import org.d3if3047.assessment2.model.HasilDiskon

class HitungViewModel(private val db: DiskonDao) : ViewModel() {

    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

    fun hitungDiskon(harga: Int, diskon: Int, total: Int) {
        val potongan = harga * diskon / 100
        val total = harga - potongan
        hasilDiskon.value = HasilDiskon(total)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataDiskon = DiskonEntity(
                    harga = harga,
                    diskon = diskon,
                    total = total
                )
                db.insert(dataDiskon)
            }
        }
    }

    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}