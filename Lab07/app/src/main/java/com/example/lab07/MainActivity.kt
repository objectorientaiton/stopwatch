package com.example.lab07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import com.example.lab07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var number1: Int = 0
    private var number2: Int = 0
    private var number3: Int = 0

    fun replaceFragment(fragment: Fragment){ // 외워야 하는 식
        supportFragmentManager.beginTransaction().run {
            replace(binding.frmFragment.id, fragment)  //replace 수행 해!
            commit()
        }
    }

    fun replaceInputFragment() {
        val inputFragment = InputFragment.newInstance(number1, number2, number3)

        replaceFragment(inputFragment)

        inputFragment.setFragmentResultListener("input") { _, bundle ->
            number1 = bundle.getInt("number1", 0)
            number2 = bundle.getInt("number2", 0)
            number3 = bundle.getInt("number3", 0)
        }
    }

    fun Max_(num1:Int, num2:Int, num3:Int): Int{
        var max:Int = 1
        if(num1 > num2){
            if(num2 > num3) max = num1
            else if(num1 > num3) max = num1
            else max = num3
        }
        else if(num2 > num1){
            if(num1 > num3) max = num2
            else if(num2 > num3) max = num2
            else max = num3
        }
        return max
    }
    fun Sum_(num1:Int, num2:Int, num3:Int): Int{
        var sum:Int = 0
        sum = num1 + num2 + num3
        return sum
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       replaceInputFragment()

        binding.btnInput.setOnClickListener {
            replaceInputFragment()
        }

        binding.btnMax.setOnClickListener {
            val max = Max_(number1, number2, number3)
            replaceFragment(ResultFragment.newInstance(max))
        }

        binding.btnSum.setOnClickListener {
            val sum =Sum_(number1, number2, number3)
            replaceFragment(ResultFragment.newInstance(sum))
        }
    }
}