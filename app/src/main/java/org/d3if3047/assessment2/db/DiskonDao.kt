package org.d3if3047.assessment2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiskonDao {
    @Insert
    fun insert(diskon: DiskonEntity)
    @Query("SELECT * FROM diskon ORDER BY id DESC LIMIT 1")
    fun getLastDiskon(): LiveData<DiskonEntity?>
}