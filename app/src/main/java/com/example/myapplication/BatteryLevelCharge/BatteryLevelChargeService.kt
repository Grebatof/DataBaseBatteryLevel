package com.example.myapplication.BatteryLevelCharge

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.IBinder
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class BatteryLevelChargeService: Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("!!!","Service started.")

        writeInDB()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("!!!","Service destroyed.")
    }

    private fun writeInDB() {
        val instance = this
        val db = Room.databaseBuilder(instance, AppDatabase::class.java, "database-batterycharge").allowMainThreadQueries().build()
        val batteryLevelChargeDao = db.batteryLevelChargeDao()

        GlobalScope.launch {
            while (true) {
                var percentage: Int = 1000
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    val bm = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
                    percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
                }

                val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())

                val batteryLevelCharge = BatteryLevelCharge(currentDate, percentage)
                batteryLevelChargeDao.insert(batteryLevelCharge)

                delay(10000L)
            }
        }
    }
}