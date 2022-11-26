package com.example.team_16.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team_16.MainActivity
import com.example.team_16.repository.StopwatchRepository
import java.util.*



class StopwatchViewModel: ViewModel() {
    private var _hour = MutableLiveData<String>("0")
    private var _minute = MutableLiveData<String>("0")
    private var _second = MutableLiveData<String>("0")
    private var _millisecond = MutableLiveData<String>("0")
    private var _time = MutableLiveData<String>("0")

    val hour: LiveData<String> get() = _hour
    val minute: LiveData<String> get() = _minute
    val second: LiveData<String> get() = _second
    val millisecond: LiveData<String> get() = _millisecond
    val time: LiveData<String> get() = _time

    var isRunning: Boolean = false
    private var timerTask: Timer? = null

    private val repository = StopwatchRepository()

    init {
        repository.observeStopwatch(_hour, _minute, _second, _millisecond, _time)
    }

    fun start(timerAction: () -> Unit, timerset: () -> Unit){ // 변해야하는 정보를 파라미터로 받음
        if(isRunning) return // job은 lateinit으로 정의해 놨는데, 초기화됐으면 job을 cancel해라! 두 번 일하지 않게!
        isRunning = true

        timerTask = kotlin.concurrent.timer(period = 10) {
            timerset()
            val handler = Handler(Looper.getMainLooper())
            handler.post(Runnable{
                MainActivity().runOnUiThread {
                    timerAction()

                }
            })

        }
/*        job = viewModelScope.launch {
            while(true) {
                delay(10)
                fnCallback()
            }
        }*/
    }

    fun stop(){
        isRunning = false
        timerTask?.cancel()
    }

}



