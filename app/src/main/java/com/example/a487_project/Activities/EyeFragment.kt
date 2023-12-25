package com.example.a487_project.Activities

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a487_project.R
import com.example.a487_project.databinding.FragmentEyeBinding

class EyeFragment : Fragment() {

    private lateinit var binding: FragmentEyeBinding

    interface EyeFragmentListener {
        fun onButtonClick(text: String)
    }

    lateinit var activityCallback: EyeFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallback = context as EyeFragmentListener
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEyeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savefacebtn.setOnClickListener {
            Toast.makeText(context, "Save Face Button is clicked", Toast.LENGTH_LONG).show()
            activityCallback.onButtonClick("Button Ok is clicked")
        }
    }
}