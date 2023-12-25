package com.example.a487_project.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMakeUpRoomBinding

class MakeUpRoomActivity : FragmentActivity(), EyeFragment.EyeFragmentListener { //Ahmet
    lateinit var binding: ActivityMakeUpRoomBinding
    //lateinit var bottomFragment : BottomFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_up_room)
    }

    override fun onButtonClick(text: String) {
        TODO("Not yet implemented")
    }
}