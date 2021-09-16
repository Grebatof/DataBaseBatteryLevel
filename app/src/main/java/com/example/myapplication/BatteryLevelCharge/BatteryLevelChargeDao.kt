package com.example.myapplication.BatteryLevelCharge

import androidx.room.*


@Dao
interface BatteryLevelChargeDao{
    @Query("SELECT * FROM BatteryLevelCharge")
    fun getAll(): List<BatteryLevelCharge>

    @Query("SELECT * FROM BatteryLevelCharge WHERE date = :date")
    fun getById(date: String): BatteryLevelCharge

    @Insert
    fun insert(batteryLevelCharge: BatteryLevelCharge)

    @Update
    fun update(batteryLevelCharge: BatteryLevelCharge)

    @Delete
    fun delete(batteryLevelCharge: BatteryLevelCharge)
}

