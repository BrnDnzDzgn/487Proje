package com.example.a487_project.Activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMakeUpRoomBinding

class MakeUpRoomActivity : FragmentActivity(), EyeFragment.EyeFragmentListener { //Ahmet
    lateinit var binding: ActivityMakeUpRoomBinding
    lateinit var bottomFragment : FaceSavedFragment
    var eyenumber: Int = 1
    var lipnumber: Int = 1
    var makeupnumber: Int = 1
    var maxnumber: Int = 3


    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeUpRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val selectedTheme = intent.getStringExtra("theme")

        if(selectedTheme == "office"){
            binding.backgroundformk.setImageResource(R.drawable.office)
        }
        else if(selectedTheme== "fantasy"){
            binding.backgroundformk.setImageResource(R.drawable.fantasy2)
        }
        else if(selectedTheme == "gothic"){
            binding.backgroundformk.setImageResource(R.drawable.gothic2)
        }

        bottomFragment = FaceSavedFragment()
        loadFrag(bottomFragment)

        binding.eyesleftbtn.setOnClickListener{
            if(eyenumber <= 1){
                eyenumber = maxnumber
            } else { eyenumber++ }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.eyesrightbtn.setOnClickListener{
            if(eyenumber >= maxnumber){
                eyenumber = 1
            } else { eyenumber-- }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.lipsleftbtn.setOnClickListener{
            if(lipnumber <= 1){
                lipnumber = lipnumber
            } else { lipnumber++ }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.lipsrightbtn.setOnClickListener{
            if(lipnumber >= maxnumber){
                lipnumber = 1
            } else { lipnumber-- }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }

        binding.gobackbtn.setOnClickListener {
            val intent = Intent(this, FashionRoomActivity::class.java)
            intent.putExtra("eye", eyenumber)
            intent.putExtra("lip", lipnumber)
            intent.putExtra("makeup", makeupnumber)
            startActivity(intent)
        }

    }


    fun loadFrag(dynamicFragment: Fragment) {
        val bundle=Bundle()
        bundle.putInt("num1", 10)
        bundle.putString("num2", "20")
        dynamicFragment.arguments = bundle
        fm = supportFragmentManager
        ft = fm.beginTransaction()
        ft.replace(R.id.facesavedtvfrag, dynamicFragment)
        ft.commit()
    }

    override fun onButtonClick(text: String) {
        bottomFragment.updateText(text)
    }
    fun setChangedFace(eye: Int, lip: Int, makeup: Int) {
        when(eye){
            1 -> {
                binding.eyelayer.setImageResource(R.drawable.eye1)
                binding.eyedetaillayer.setImageResource(R.drawable.eyeball1)
            }
            2 -> {
                binding.eyelayer.setImageResource(R.drawable.eye2)
                binding.eyedetaillayer.setImageResource(R.drawable.eyeball2)
            }
            3 -> {
                binding.eyelayer.setImageResource(R.drawable.eye3)
                binding.eyedetaillayer.setImageResource(R.drawable.eyeball3)
            }
        }
        when(lip){
            1 -> {
                binding.liplayer.setImageResource(R.drawable.lip1)
                binding.lipdetaillayer.setImageResource(R.drawable.lipdetail1)
            }
            2 -> {
                binding.liplayer.setImageResource(R.drawable.lip2)
                binding.lipdetaillayer.setImageResource(R.drawable.lipdetail2)
            }
            3 -> {
                binding.liplayer.setImageResource(R.drawable.lip3)
                binding.lipdetaillayer.setImageResource(R.drawable.lipdetail3)
            }
        }

    }

}