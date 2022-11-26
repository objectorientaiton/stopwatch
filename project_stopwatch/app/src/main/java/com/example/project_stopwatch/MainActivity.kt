
package com.example.project_stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var start: Button? = null
    var pause: Button? = null
    var MillisecondTime: Long = 0
    var StartTime: Long = 0
    var TimeBuff: Long = 0
    var UpdateTime = 0L
    var handler: Handler? = null
    var Seconds = 0
    var Minutes = 0
    var MilliSeconds = 0
    var Hours = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.txt_time) as TextView?
        start = findViewById(R.id.btn_start) as Button?
        pause = findViewById(R.id.btn_stop) as Button?
        handler = Handler()


        start?.setOnClickListener(View.OnClickListener {
            StartTime = SystemClock.uptimeMillis()
            handler?.postDelayed(runnable, 0)
        })
        pause?.setOnClickListener(View.OnClickListener {
            TimeBuff += MillisecondTime
            handler?.removeCallbacks(runnable)
        })
    }

    var runnable: Runnable = object : Runnable {
        override fun run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime
            UpdateTime = TimeBuff + MillisecondTime

            Seconds = (UpdateTime / 1000).toInt()
            Hours = Minutes / 60
            Minutes = Seconds / 60
            Seconds = Seconds % 60
            MilliSeconds = (UpdateTime % 100).toInt()
            textView?.setText(
                "" + String.format("%02d",Hours) + ":" +
                String.format("%02d",Minutes) + ":" +
                String.format("%02d", Seconds) + "." +
                String.format("%02d", MilliSeconds))
            handler?.postDelayed(this, 0)
        }
    }
}

    /*fun midnight(){
        val highNoon = Calendar.getInstance()
        highNoon.set(Calendar.HOUR_OF_DAY, 12)
        highNoon.set(Calendar.MINUTE, 0)
        highNoon.set(Calendar.SECOND, 0)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run(){
                //특정시간에 동작
            }
        }, highNoon.time)
    }*/

