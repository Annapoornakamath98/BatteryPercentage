package com.yml.batterypercentage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textView:TextView
    lateinit var broadcastReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.textview)
        broadcastReceiver= object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val percentage = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                textView.setText("Battery percentage: " + percentage)
                val statusOfBattery = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, 0)
                if (statusOfBattery == BatteryManager.BATTERY_STATUS_CHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Charger is connected", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_FULL)
                {
                    Toast.makeText(getApplicationContext(), "Battery is fully charged", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_DISCHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Battery is discharging", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_NOT_CHARGING)
                {
                    Toast.makeText(getApplicationContext(), "Charger is not connected", Toast.LENGTH_SHORT).show()
                }
                else if (statusOfBattery == BatteryManager.BATTERY_STATUS_UNKNOWN)
                {
                    Toast.makeText(getApplicationContext(), "Battery status is unknown", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
     override fun onStart() {
        registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        super.onStart()
    }
     override fun onStop() {
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }
}