package com.blogspot.cahyanatea.lat0alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import org.intellij.lang.annotations.Language

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val pesan = intent.getStringExtra(Intent.EXTRA_TEXT)
        showNotif(context, pesan)
        Log.d("MYLOG", pesan)
    }

    private fun showNotif(context: Context, pesan: String) {
        val notif = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm diterima")
                .setContentText(pesan)

        val manager = NotificationManagerCompat.from(context)
        manager.notify(0, notif.build())
    }
}
