package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ListStudentsBinding

class StudentsAdapter(val students: Array<Student>)
    : RecyclerView.Adapter<StudentsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListStudentsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size

    class Holder(private val binding: ListStudentsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student){
            binding.imageView.setImageResource(when(student.gender){
                EGender.MALE -> R.drawable.male
                EGender.FEMALE -> R.drawable.female
                EGender.LGBT -> R.drawable.lgbt
            })
            binding.txtName.text = student.name
            binding.txtId.text = student.id
            binding.txtAttendNum.text = student.attend.toString()
            binding.txtAbsenceNum.text = student.absence.toString()

            binding.root.setOnClickListener{
                Toast.makeText(binding.root.context, "이름: ${student.name} 학번: ${student.id}\n출석: ${student.attend} 결석: ${student.absence}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}