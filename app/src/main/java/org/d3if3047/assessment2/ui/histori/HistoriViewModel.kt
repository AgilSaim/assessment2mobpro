package org.d3if3047.assessment2.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if3047.assessment2.db.DiskonDao

class HistoriViewModel(db: DiskonDao) : ViewModel() {
    val data = db.getLastDiskon()
}