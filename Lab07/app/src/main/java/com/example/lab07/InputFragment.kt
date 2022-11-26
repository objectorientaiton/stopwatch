package com.example.lab07

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.example.lab07.databinding.FragmentInputBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "number1"
private const val ARG_PARAM2 = "number2"
private const val ARG_PARAM3 = "number3"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {
    //lateinit var binding: FragmentInputBinding //lateinit이 null이 될 수 있어서 lateinit 안씀
    var binding: FragmentInputBinding ?= null

    // TODO: Rename and change types of parameters
    private var number1: Int = 0
    private var number2: Int = 0
    private var number3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) { //bundle로 가지고 있던걸 argument로 update
        super.onCreate(savedInstanceState)
        arguments?.let { //argument가 있다면 다음을 수행해라 // it:Bundle
            number1 = it.getInt("number1",0)
            number2 = it.getInt("number2",0)
            number3 = it.getInt("number3",0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding?.editNumber1?.setText(number1.toString())
        binding?.editNumber2?.setText(number2.toString())
        binding?.editNumber3?.setText(number3.toString())

        binding?.editNumber1?.setOnKeyListener{ view, i, keyEvent->
            setResult()
            false
        }
        binding?.editNumber2?.setOnKeyListener{ view, i, keyEvent->
            setResult()
            false
        }
        binding?.editNumber3?.setOnKeyListener{ view, i, keyEvent->
            setResult()
            false
        }
        return binding?.root
    }


    override fun onDestroyView(){ // 메모리가 안새게 하는 함수
        super.onDestroyView()
        binding = null
    }

    fun setResult(){
        Bundle().let{ bundle->
            binding?.editNumber1?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number1", number)
            }
            binding?.editNumber2?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number2", number)
            }
            binding?.editNumber3?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number3", number)
            }
            setFragmentResult("input", bundle)
        }
    }

    override fun onPause() {
        super.onPause()

        Bundle().let{ bundle ->
            binding?.editNumber1?.text.toString().toIntOrNull() ?.let { number ->
                bundle.putInt("number1", number)
            }
            binding?.editNumber2?.text.toString().toIntOrNull() ?.let { number ->
                bundle.putInt("number2", number)
            }
            binding?.editNumber3?.text.toString().toIntOrNull() ?.let { number ->
                bundle.putInt("number3", number)
            }
            setFragmentResult("input", bundle)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic

        fun newInstance(number1: Int, number2: Int, number3: Int) =
            InputFragment().apply {
                arguments = Bundle().apply {
                    putInt("number1", number1)
                    putInt("number2", number2)
                    putInt("number3", number3)
                }//inputFragment 놓고 argument apply 해라
            }
    }
}