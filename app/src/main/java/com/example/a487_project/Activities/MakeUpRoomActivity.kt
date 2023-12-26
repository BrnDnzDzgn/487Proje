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
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.Classes.DataHolder
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMakeUpRoomBinding

class MakeUpRoomActivity : FragmentActivity(), EyeFragment.EyeFragmentListener { //Ahmet
    lateinit var binding: ActivityMakeUpRoomBinding
    lateinit var bottomFragment : FaceSavedFragment
    //lateinit var fashionRoomActivity: FashionRoomActivity
    var eyenumber: Int = 1
    var lipnumber: Int = 1
    var makeupnumber: Int = 1
    var maxnumber: Int = 3


    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        //fashionRoomActivity = FashionRoomActivity()

        super.onCreate(savedInstanceState)
        binding = ActivityMakeUpRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setChangedFace(eyenumber, lipnumber, makeupnumber)

        val intent = intent
        val selectedTheme = intent.getStringExtra("theme")

        preparedata(selectedTheme.toString(), eyenumber, lipnumber, makeupnumber)

        bottomFragment = FaceSavedFragment()
        loadFrag(bottomFragment)

        binding.eyesleftbtn.setOnClickListener{
            if(eyenumber <= 1){
                eyenumber = maxnumber
            } else { eyenumber-- }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.eyesrightbtn.setOnClickListener{
            if(eyenumber >= maxnumber){
                eyenumber = 1
            } else { eyenumber++ }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.lipsleftbtn.setOnClickListener{
            if(lipnumber <= 1){
                lipnumber = maxnumber
            } else { lipnumber-- }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }
        binding.lipsrightbtn.setOnClickListener{
            if(lipnumber >= maxnumber){
                lipnumber = 1
            } else { lipnumber++ }
            setChangedFace(eyenumber, lipnumber, makeupnumber)
        }

        // go back to dressing

        binding.gobackbtn.setOnClickListener {
            val intent = Intent(this, FashionRoomActivity::class.java)


            intent.putExtra("eye", eyenumber)
            intent.putExtra("lip", lipnumber)
            intent.putExtra("makeup", makeupnumber)
            intent.putExtra("theme",selectedTheme)
            finish()
        }

    }


    fun loadFrag(dynamicFragment: Fragment) {
        fm = supportFragmentManager
        ft = fm.beginTransaction()
        ft.replace(R.id.facesavedtvfrag, dynamicFragment)
        ft.commit()
    }

    override fun onButtonClick(text: String) {
        DataHolder.eye = eyenumber
        DataHolder.lip = lipnumber
        DataHolder.makeup = makeupnumber
        //fashionRoomActivity.setChangedFace(DataHolder.eye, DataHolder.lip, DataHolder.makeup)
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

    fun preparedata(selectedTheme: String, eyenumber: Int, lipnumber: Int, makeupnumber: Int){

        if(selectedTheme == "office"){
            binding.backgroundformk.setImageResource(R.drawable.office)
        }
        else if(selectedTheme== "fantasy"){
            binding.backgroundformk.setImageResource(R.drawable.fantasy2)
        }
        else if(selectedTheme == "gothic"){
            binding.backgroundformk.setImageResource(R.drawable.gothic2)
        } else { binding.backgroundformk.setImageResource(R.drawable.gothic2) }

        setChangedFace(eyenumber, lipnumber, makeupnumber)
        binding.bodylayer.setImageResource(R.drawable.body)

    }

}