package org.d3if3047.assessment2.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diskon")
data class DiskonEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var harga: Int,
    var diskon: Int,
    var total: Int
)