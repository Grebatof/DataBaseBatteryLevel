package com.example.myapplication.BatteryLevelCharge

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BatteryLevelCharge::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun batteryLevelChargeDao(): BatteryLevelChargeDao
}