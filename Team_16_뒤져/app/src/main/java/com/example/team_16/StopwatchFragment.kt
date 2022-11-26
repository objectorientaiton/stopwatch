package com.example.team_16

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.team_16.MainActivity
import com.example.team_16.databinding.FragmentStopwatchBinding
import com.example.team_16.repository.StopwatchRepository
import com.example.team_16.viewmodel.StopwatchViewModel
import java.util.*

@Suppress("DEPRECATION")
class StopwatchFragment : Fragment() {
    val viewModel: StopwatchViewModel by activityViewModels()
    lateinit var binding: FragmentStopwatchBinding
    var time: Long = 0
    private var timerTask: Timer? = null
    var email: String? = "None"


/*    val today = SimpleDateFormat("yyyy-MM-dd")
        .format(Date(System.currentTimeMillis())) //오늘 날짜
    val yesterday = SimpleDateFormat("yyyy-MM-dd")
        .format(Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24))) // 어제 날짜*/


    var hour: Long = 0
    var minute: Long = 0
    var second: Long = 0
    var millisecond: Long = 0
    val timerset: () -> Unit = {
        time++
        second = time / 100
        millisecond = time % 100
        minute = second / 60
        hour = minute / 60
    }
    val timerAction: () -> Unit = {
        binding.txtHour.setText(String.format("%02d", hour))
        binding.txtMin.setText(String.format("%02d", minute))
        binding.txtSec.setText(String.format("%02d", second))
        binding.txtMil.setText(String.format("%02d", millisecond))
    } // 나름 묶어두려는거임..

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = arguments?.getString("email")


        setFragmentResult("email", bundleOf("email" to email))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStopwatchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            start()
        }
        binding.btnStop.setOnClickListener {
            stop()
        }

    }
   private fun start() {
        timerTask = kotlin.concurrent.timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100
            val min = sec / 60
            val hour = min / 60
            val handler = Handler(Looper.getMainLooper())
            handler.post(Runnable{
                MainActivity().runOnUiThread {
                    binding.txtHour.setText(String.format("%02d", hour))
                    binding.txtMin.setText(String.format("%02d", min))
                    binding.txtSec.setText(String.format("%02d", sec))
                    binding.txtMil.setText(String.format("%02d", milli))
                }
            })
        }
    }
    private fun stop() {
        timerTask?.cancel()
    }
}
