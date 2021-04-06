package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.settime).setOnClickListener(View.OnClickListener {
        view ->
        openTimePickerDialog()
    })


    }









/*
    fun showTimerPickerFragment(view: View) {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.show(supportFragmentManager, "time_picker")
    }
*/

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun startAlarm(calendar: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    /**
     * On Click Button for Cancel the previously set alarm
     */
    fun cancelAlarm(view: View) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.cancel(pendingIntent)
    }



    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun openTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this,
            onTimeSetListener,
            calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE],
            true)
        timePickerDialog.setTitle("Set Alarm Time")
        timePickerDialog.show()
    }





   @RequiresApi(Build.VERSION_CODES.KITKAT)
   private var onTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
       val calendar = Calendar.getInstance()
       calendar.set(Calendar.HOUR_OF_DAY, hour)
       calendar.set(Calendar.MINUTE, minute)
       calendar.set(Calendar.SECOND, 0)
       startAlarm(calendar)

    }

}