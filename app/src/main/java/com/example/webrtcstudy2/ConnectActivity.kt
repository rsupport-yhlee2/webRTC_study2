package com.example.webrtcstudy2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.webrtcstudy2.databinding.ActivityConnectBinding
import java.util.jar.Manifest

class ConnectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConnectBinding

    private lateinit var roomID: String
    private var isJoin: Boolean = false

    private lateinit var rtcClient: RtcClient
    private lateinit var signalingClient: SignalingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        checkPermissions()
    }

    private fun getIntentData() {
        roomID = intent.getStringExtra("roomID")!!
        isJoin = intent.getBooleanExtra("isJoin", false)
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraAndAudioPermisson()
        } else {
            initializeClients()
        }
    }

    private fun initializeClients() {
        rtcClient = RtcClient()
        signalingClient = SignalingClient(roomID, object : SignalListenr {})
    }

    private fun requestCameraAndAudioPermisson() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO),
            1
        )
    }
}