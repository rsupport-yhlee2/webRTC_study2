package com.example.webrtcstudy2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.webrtcstudy2.databinding.ActivityConnectBinding

class ConnectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConnectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}