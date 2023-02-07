package com.example.youtube.ui.video

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube.databinding.ActivityVideoBinding
import com.example.youtube.ui.playlist.PlaylistActivity

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val id = intent.getStringExtra(PlaylistActivity.KEY_FOR_PLAYLISTS)
        binding.textId.text = id
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
}