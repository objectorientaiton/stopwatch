package com.example.lamdaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lamdaexample.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    fun makeRandomArray(): IntArray{ //randomarray가 되도록 함수 완성하기
        val result = intArrayOf(0,0,0,0,0)
        for(i in 0 until result.size) {
            result[i] = Random.nextInt(0, 100)
        }
        return  result
    }
    fun makeArrayString(): String{
        var result = ""
        for (elem in arr){
            result += "$elem "
        }
        return result
    }
    fun sum_(arr:IntArray): String{
        var result = 0
        for(elem in arr){
            result += elem
        }
        return result.toString()
    }
    fun max_(arr:IntArray): String{
        var max = arr[0]
        for(i in 1 until arr.size){
            if(arr[i] > max) max = arr[i]
        }
        return max.toString()
    }
    fun product_(arr:IntArray): String{
        var sum = 1
        for(elem in arr){
            sum *= elem
        }
        return sum.toString()
    }

    private var arr = makeRandomArray()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.txtNumber.text = makeArrayString()

        binding.btnRand.setOnClickListener {
            arr = makeRandomArray()
            binding.txtNumber.text = makeArrayString()
        }
        binding.btnSum.setOnClickListener {
            binding.textView4.text = sum_(arr)
        }
        binding.btnProduct.setOnClickListener {
            binding.textView4.text = product_(arr)
        }
        binding.btnMax.setOnClickListener {
            binding.textView4.text = max_(arr)
        }

    }
}