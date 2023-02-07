package com.example.youtube.ui.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtube.R
import com.example.youtube.ui.playlist.PlaylistActivity

class VideoActivity : AppCompatActivity() {

    private lateinit var intentVideo: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        intentVideo = intent
        val id = intentVideo.getStringExtra(PlaylistActivity.KEY_FOR_PLAYLISTS)!!
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
}