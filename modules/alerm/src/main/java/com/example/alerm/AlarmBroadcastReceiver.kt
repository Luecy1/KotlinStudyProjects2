package com.example.alerm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        context.showToast("時間になりました")

        val intent = Intent(context, MainActivity::class.java)
            .putExtra("onReceive", true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(intent)
    }
}