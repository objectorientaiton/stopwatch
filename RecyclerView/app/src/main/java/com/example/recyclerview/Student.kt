package com.example.recyclerview
//집합들을 정의하기 위해 만들어진 클래스
enum class EGender{
    MALE,
    FEMALE,
    LGBT
}
data class Student(val name: String,
                   val id: String,
                   val gender: EGender,
                   val attend: Int,
                   val absence: Int )