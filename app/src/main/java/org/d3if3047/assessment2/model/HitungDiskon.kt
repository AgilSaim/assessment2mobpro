package org.d3if3047.assessment2.model

import org.d3if3047.assessment2.db.DiskonEntity

fun DiskonEntity.hitungDiskon(): HasilDiskon {
    val potongan = harga * diskon / 100
    val total = harga - potongan
    return HasilDiskon(total)
}