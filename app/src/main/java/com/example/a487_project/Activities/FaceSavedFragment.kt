package com.example.a487_project.Activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a487_project.R
import com.example.a487_project.databinding.FragmentFaceSavedBinding

class FaceSavedFragment : Fragment() {private lateinit var binding: FragmentFaceSavedBinding
    var sum:Int=0

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFaceSavedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args=arguments
        var num1:Int=args!!.getInt("num1")
        var num2:Int=Integer.parseInt(args!!.getString("num2"))
        sum = num1 + num2

        binding.savedfacetv.text="Press Save Face Button"
    }

    fun updateText(s: String) {
        binding.savedfacetv.text=s
        Log.i("FRAGMENT","updateText() in BottomFragment")
    }
}