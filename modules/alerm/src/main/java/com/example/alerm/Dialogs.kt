package com.example.alerm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class SimpleAlertDialog : DialogFragment() {

    interface OnClickListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    private lateinit var listener: OnClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SimpleAlertDialog.OnClickListener) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context ?: return super.onCreateDialog(savedInstanceState)

        val builder = AlertDialog.Builder(context)
            .setMessage("時間になりました")
            .setPositiveButton("起きる") { dialog, which ->
                listener.onPositiveClick()
            }
            .setNegativeButton("あと５分") { dialog, which ->
                listener.onNegativeClick()
            }

        return builder.create()
    }
}

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    interface OnDateSelectedListener {
        fun onSelected(year: Int, month: Int, date: Int)
    }

    private lateinit var listener: OnDateSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDateSelectedListener) {
            listener = context
        } else {
            throw IllegalArgumentException("implemented OnDateSelectedListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context ?: return super.onCreateDialog(savedInstanceState)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(context, this, year, month, date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener.onSelected(year, month, dayOfMonth)
    }
}

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    interface OnTimeSelectedListener {
        fun onSelected(hourOfDay: Int, minute: Int)
    }

    private lateinit var listener: OnTimeSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TimePickerFragment.OnTimeSelectedListener) {
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context ?: return super.onCreateDialog(savedInstanceState)

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(context, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener.onSelected(hourOfDay, minute)
    }

}