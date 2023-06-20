package org.d3if3047.assessment2.ui.barang

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3047.assessment2.model.Barang
import org.d3if3047.assessment2.network.BarangApi
import org.d3if3047.assessment2.network.UpdateWorker
import java.util.concurrent.TimeUnit

class BarangViewModel : ViewModel() {
    private val data = MutableLiveData<List<Barang>>()
    private val status = MutableLiveData<BarangApi.ApiStatus>()
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(BarangApi.ApiStatus.LOADING)
            try {
                data.postValue(BarangApi.service.getBarang())
                status.postValue(BarangApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("BarangViewModel", "Failure: ${e.message}")
                status.postValue(BarangApi.ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<Barang>> = data

    fun getStatus(): LiveData<BarangApi.ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
