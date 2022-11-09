package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val students = arrayOf(
        Student("이준희", "2021125051", EGender.FEMALE, 10, 0),
        Student("홍길동", "2021125035", EGender.MALE, 9, 1),
        Student("성춘향", "2021125074", EGender.FEMALE, 10, 0),
        Student("이몽룡", "2021125026", EGender.MALE, 10, 0),
        Student("이순신", "2021125084", EGender.MALE, 8, 2),
        Student("이효리", "2021125044", EGender.FEMALE, 7, 3),
        Student("성유리", "2021125093", EGender.FEMALE, 10, 0),
        Student("아이유", "2021125024", EGender.FEMALE, 5, 5),
        Student("조정석", "2021125056", EGender.MALE, 6, 4),
        Student("한가인", "2021125058", EGender.FEMALE, 10, 0),
        Student("이승철", "2021125025", EGender.MALE, 10, 0),

        )

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // UI와 코드 연결
        setContentView(binding.root)

        binding.recStudents.layoutManager = LinearLayoutManager(this) // 한줄씩 student출력
        binding.recStudents.adapter = StudentsAdapter(students)
    }
}