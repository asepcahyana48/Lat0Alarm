package com.blogspot.cahyanatea.lat0alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import java.util.*

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val intent2 = Intent(context, AlarmReceiver::class.java)
            intent2.putExtra(Intent.EXTRA_TEXT, "ini pesan saya")
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarm = Calendar.getInstance()
            alarm.timeInMillis = System.currentTimeMillis()
            alarm.set(Calendar.HOUR_OF_DAY, 6)
            alarm.set(Calendar.MINUTE, 50)
            alarm.set(Calendar.SECOND, 0)
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarm.timeInMillis, 1000*60*3, pendingIntent)

            val componentName = ComponentName(context, BootReceiver::class.java)
            context.packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
            Log.d("MYLOG", "ALARM RESCHEDULER")
        }
    }
}
