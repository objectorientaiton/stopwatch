package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentResultBinding
import com.example.navigation.viewmodel.MBTIViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ResultFragment : Fragment() {
    //val viewModel : MBTIViewModel by viewModels()
    val viewModel : MBTIViewModel by activityViewModels()
    var binding : FragmentResultBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)

        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mbti.observe(viewLifecycleOwner/*this쓰지마쇼*/){
            binding?.txtResult?.text = viewModel.mbti.value
        }

        binding?.btnReexamine?.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_examineFragment)
        }
    }


}