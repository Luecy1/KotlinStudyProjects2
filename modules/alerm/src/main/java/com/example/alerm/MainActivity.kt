package com.example.alerm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),
    SimpleAlertDialog.OnClickListener,
    DatePickerFragment.OnDateSelectedListener,
    TimePickerFragment.OnTimeSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getBooleanExtra("onReceive", false)) {

            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    window.addFlags(FLAG_TURN_SCREEN_ON or FLAG_SHOW_WHEN_LOCKED)
                }
                else -> {
                    window.addFlags(FLAG_TURN_SCREEN_ON or FLAG_SHOW_WHEN_LOCKED or FLAG_DISMISS_KEYGUARD)
                }
            }

            val dialog = SimpleAlertDialog()
            dialog.show(supportFragmentManager, "alert_dialog")
        }

        setContentView(R.layout.activity_main)

        setAlarm.setOnClickListener {
            val date = "${dateText.text} ${timeText.text}".toDate()

            if (date != null) {
                val calender = Calendar.getInstance()
                calender.time = date
                setAlarm(calender)
                showToast("アラームをセットしました")
                finish()
            } else {
                showToast("日付の形式が正しくありません")
            }
        }

        cancelAlarm.setOnClickListener {
            cancelAlarmManager()
        }

        dateText.setOnClickListener {
            val dialog = DatePickerFragment()
            dialog.show(supportFragmentManager, "date_dialog")
        }

        timeText.setOnClickListener {
            val dialog = TimePickerFragment()
            dialog.show(supportFragmentManager, "time_dialog")
        }
    }

    private fun setAlarm(calendar: Calendar) {
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pending = PendingIntent.getBroadcast(this, 0, intent, 0)

        val info = AlarmManager.AlarmClockInfo(calendar.timeInMillis, null)
        am.setAlarmClock(info, pending)
    }

    private fun cancelAlarmManager() {
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pending = PendingIntent.getBroadcast(this, 0, intent, 0)
        am.cancel(pending)
    }

    override fun onPositiveClick() {
        finish()
    }

    override fun onNegativeClick() {
        showToast("あと５分")
    }

    override fun onSelected(year: Int, month: Int, date: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, date)
        dateText.text = DateFormat.format("yyyy/MM/dd", calendar)
    }

    override fun onSelected(hourOfDay: Int, minute: Int) {
        timeText.text = "%1$02d:%2$02d".format(hourOfDay, minute)
    }
}

private fun String.toDate(): Date? {
    return try {
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm")
        format.parse(this)
    } catch (e: ParseException) {
        null
    }
}