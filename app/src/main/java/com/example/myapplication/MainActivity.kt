package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.BatteryLevelCharge.BatteryLevelChargeService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceClass = BatteryLevelChargeService::class.java
        val intent = Intent(this, serviceClass)

        startService(intent)
    }
}
