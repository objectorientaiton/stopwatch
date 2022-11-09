package com.example.project_stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.project_stopwatch.R
import kotlin.concurrent.timer
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener{

    var isRunning = false
    var timer:Timer?=null
    var time = 0

    private lateinit var btn_start: Button
    private lateinit var txt_mil : TextView
    private lateinit var txt_min : TextView
    private lateinit var txt_sec : TextView
    private lateinit var txt_hour : TextView
    private lateinit var btn_calendar : Button
    private lateinit var btn_statistic : Button
    private lateinit var btn_ranking : Button
    private lateinit var btn_account : Button
    private lateinit var btn_stopwatch : Button


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        btn_start = findViewById(R.id.btn_start)
        txt_mil=findViewById(R.id.txt_mil)
        txt_sec=findViewById(R.id.txt_sec)
        txt_min=findViewById(R.id.txt_min)
        txt_hour=findViewById(R.id.txt_hour)
        btn_calendar = findViewById(R.id.btn_calendar)
        btn_account = findViewById(R.id.btn_account)
        btn_ranking = findViewById(R.id.btn_ranking)
        btn_statistic = findViewById(R.id.btn_statistic)
        btn_stopwatch = findViewById(R.id.stopwatch)

        btn_start.setOnClickListener(this)
        btn_calendar.setOnClickListener(this)
        btn_account.setOnClickListener(this)
        btn_ranking.setOnClickListener(this)
        btn_stopwatch.setOnClickListener(this)
        btn_statistic.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start ->
                if (isRunning) pause() else start()
        }
    }

    fun start(){
        btn_start.text = "Stop"
        isRunning = true
        timer = timer(period = 10){
            time++
            val hour = time / 360000
            val min = (time % 360000)/6000
            val sec = ((time % 360000)%6000) / 100
            val mil = time % 100

            runOnUiThread{
                if(isRunning){
                    txt_hour.text =
                        if(hour<10) "0${hour}" else "$hour"
                    txt_min.text =
                        if(min<10) "0${min}" else "$min"
                    txt_sec.text =
                        if(sec<10) "0${sec}" else "$sec"
                    txt_mil.text =
                        if(mil<10) "0${mil}" else "$mil"
                }
            }
        }
    }

    fun pause(){
        btn_start.text = "Start"
        isRunning = false
        timer?.cancel()
    }

    fun reset(){
        timer?.cancel()

        time = 0
        isRunning = false
        txt_hour.text = "00"
        txt_min.text = "00"
        txt_sec.text = "00"
        txt_mil.text = "00"
    }
}