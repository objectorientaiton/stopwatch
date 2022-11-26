package com.example.team_16.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.team_16.StopwatchFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class StopwatchRepository {

    val today = SimpleDateFormat("yyyy-MM-dd")
        .format(Date(System.currentTimeMillis()))
    val email = StopwatchFragment().email
    val db = FirebaseFirestore.getInstance()
    val timeRef = db.collection("Users").document("$email")
        .collection("studytime").document("$today")
    var _binding = StopwatchFragment().binding
    fun observeStopwatch(
        hour: MutableLiveData<String>,
        minute: MutableLiveData<String>,
        second: MutableLiveData<String>,
        millisecond: MutableLiveData<String>,
        time: MutableLiveData<String>
    ) {}

    fun uploadTime(timerAction: () -> Unit) {
        timeRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val H = document.getString("hour")
                val M = document.getString("minute")
                val S = document.getString("second")
                val MS = document.getString("millisecond")
                val TB = document.getString("timebuff")
                if (H != null && M != null && S != null && MS != null && TB != null) {
                    StopwatchFragment().hour = H.toLong()
                    StopwatchFragment().minute = M.toLong()
                    StopwatchFragment().second = S.toLong()
                    StopwatchFragment().millisecond = MS.toLong()
                    StopwatchFragment().time = TB.toLong()
                    _binding.root.post {
                        timerAction()
                        Log.v("알림", "셋팅되었습니다")
                    }
                }
            }
        }
    }
}
