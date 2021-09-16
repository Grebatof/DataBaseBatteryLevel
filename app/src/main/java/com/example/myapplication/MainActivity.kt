package com.example.myapplication

import android.content.Context
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.BatteryLevelCharge.AppDatabase
import com.example.myapplication.BatteryLevelCharge.BatteryLevelCharge
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeInDB()
    }

    private fun writeInDB() {
        val instance = this
        val db = Room.databaseBuilder(instance, AppDatabase::class.java, "database-batterycharge").allowMainThreadQueries().build()
        val batteryLevelChargeDao = db.batteryLevelChargeDao()

        MainScope().launch {
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

                Log.d("!!!", "${batteryLevelCharge.date} : ${batteryLevelCharge.batteryLevel}")
            }
        }
    }
}
