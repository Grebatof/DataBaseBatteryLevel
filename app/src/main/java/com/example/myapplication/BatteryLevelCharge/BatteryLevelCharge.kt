package com.example.myapplication.BatteryLevelCharge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BatteryLevelCharge (
    @PrimaryKey val date: String,
    @ColumnInfo val batteryLevel: Int
)