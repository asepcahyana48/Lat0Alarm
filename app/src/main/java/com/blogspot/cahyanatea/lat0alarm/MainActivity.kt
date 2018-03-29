package com.blogspot.cahyanatea.lat0alarm

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener(this)
        btn_stop.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, "ini pesan saya")
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        when(view!!.id) {
            R.id.btn_start -> {
                val alarm = Calendar.getInstance()
                alarm.timeInMillis = System.currentTimeMillis()
                alarm.set(Calendar.HOUR_OF_DAY, 7)
                alarm.set(Calendar.MINUTE, 5)
                alarm.set(Calendar.SECOND, 0)
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, 1000*60*3, pendingIntent)

                val componentName = ComponentName(this, BootReceiver::class.java)
                packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
            }
            R.id.btn_stop -> {
                alarmManager.cancel(pendingIntent)
                val componentName = ComponentName(this, BootReceiver::class.java)
                packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
            }
        }
    }
}
