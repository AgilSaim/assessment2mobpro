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
import org.d3if3047.assessment2.model.hitungDiskon

class HitungViewModel(private val db: DiskonDao) : ViewModel() {

    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

    fun hitungDiskon(harga: Int, diskon: Int, total: Int) {
        val dataDiskon = DiskonEntity(
            harga = harga,
            diskon = diskon,
            total = total
        )
        hasilDiskon.value = dataDiskon.hitungDiskon()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataDiskon)
            }
        }
    }

    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}