package com.example.navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigation.repository.MbtiRepository

const val UNCHECKED_MBTI = "ISTP"

class MBTIViewModel: ViewModel() {
    private val _mbti = MutableLiveData<String>(UNCHECKED_MBTI)
    val mbti : LiveData<String> get() = _mbti

    private val repository = MbtiRepository()
    init{
        repository.observeMbti(_mbti)
    }


    private fun modifyMbti(index: Int, newValue: Char){
        _mbti.value = _mbti.value?.let{
            val chArr = it.toCharArray()
            chArr[index] = newValue
            String(chArr)
        } ?: UNCHECKED_MBTI

        repository.postMbti(_mbti.value ?: UNCHECKED_MBTI)
    }

    val isE get() = _mbti.value?.get(0) == 'E'
    val isN get() = _mbti.value?.get(1) == 'N'
    val isF get() = _mbti.value?.get(2) == 'F'
    val isJ get() = _mbti.value?.get(3) == 'J'

    fun setE(newValue: Boolean){
        modifyMbti(0, if(newValue) 'E' else 'I')
    }
    fun setN(newValue: Boolean){
        modifyMbti(1, if(newValue) 'N' else 'S')
    }
    fun setF(newValue: Boolean){
        modifyMbti(2, if(newValue) 'F' else 'N')
    }
    fun setJ(newValue: Boolean){
        modifyMbti(3, if(newValue) 'J' else 'P')
    }
}