package com.example.navigation.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MbtiRepository {
    val database = Firebase.database
    val userRef = database.getReference("user")

    fun observeMbti(mbti: MutableLiveData<String>){
        userRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mbti.postValue(snapshot.value.toString() )
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun postMbti(newValue: String){
        userRef.setValue(newValue)
    }
}