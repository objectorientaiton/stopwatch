package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentExamineBinding
import com.example.navigation.viewmodel.MBTIViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ExamineFragment : Fragment() {
    val viewModel: MBTIViewModel by activityViewModels()
    var binding : FragmentExamineBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamineBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    fun examineMBTI() : String{
        binding?.let {
            val ieStr = if ( it.chkE.isChecked ) "E" else "I"
            val snStr = if( it.chkN.isChecked ) "N" else "S"
            val tfStr = if( it.chkF.isChecked ) "F" else "T"
            val jpStr = if( it.chkJ.isChecked ) "J" else "P"

            return ieStr + snStr + tfStr + jpStr
        }
        return ""
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mbti.observe(viewLifecycleOwner){
            binding?.chkE?.isChecked = viewModel.isE
            binding?.chkN?.isChecked = viewModel.isN
            binding?.chkF?.isChecked = viewModel.isF
            binding?.chkJ?.isChecked = viewModel.isJ
        }
        binding?.chkE?.setOnClickListener{
            viewModel.setE(binding?.chkE?.isChecked ?: false)
        }
        binding?.chkN?.setOnClickListener{
            viewModel.setN(binding?.chkN?.isChecked ?: false)
        }
        binding?.chkF?.setOnClickListener{
            viewModel.setF(binding?.chkF?.isChecked ?: false)
        }
        binding?.chkJ?.setOnClickListener{
            viewModel.setJ(binding?.chkJ?.isChecked ?: false)
        }



        binding?.btnResult?.setOnClickListener {
            val result = examineMBTI()
            val bundle = bundleOf("MBTI" to result)
            findNavController().navigate(R.id.action_examineFragment_to_resultFragment, bundle)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}