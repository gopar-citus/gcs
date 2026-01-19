package com.example.gcs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gcs.databinding.ActivityMainBinding
import com.example.gcs.ui.demo.DemoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openDemoButton.setOnClickListener {
            startActivity(Intent(this, DemoActivity::class.java))
        }
    }
}
