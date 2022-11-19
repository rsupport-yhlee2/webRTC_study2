package com.example.webrtcstudy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.webrtcstudy2.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val database = Firebase.database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        startCall.setOnClickListener {
            val roomID = roomID.text.toString()
            database.child("calls").child(roomID).get().addOnSuccessListener {
                if(it.value == null){
                    val intent = Intent(this@MainActivity,ConnectActivity::class.java)
                    intent.putExtra("roomID",roomID)
                    intent.putExtra("isJoin",false)
                    startActivity(intent)
                }
            }
        }
        joinCall.setOnClickListener {
            val roomID = roomID.text.toString()
            val intent = Intent(this@MainActivity,ConnectActivity::class.java)
            intent.putExtra("roomID",roomID)
            intent.putExtra("isJoin",true)
            startActivity(intent)
        }
    }
}