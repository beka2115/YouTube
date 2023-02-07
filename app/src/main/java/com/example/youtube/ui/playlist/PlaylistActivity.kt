package com.example.youtube.ui.playlist

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.checks.ConnectionLiveData
import com.example.youtube.adapters.PlaylistsAdapter
import com.example.youtube.ui.video.VideoActivity
import com.example.youtube.base.BaseActivity
import com.example.youtube.checks.CheckConnection
import com.example.youtube.databinding.ActivityPlaylistsBinding

class PlaylistActivity : BaseActivity<ActivityPlaylistsBinding, PlayListsViewModel>() {
    private var inet = true
    private lateinit var cld: ConnectionLiveData
    private val adapter = PlaylistsAdapter(this::onClick)
    private lateinit var connection: CheckConnection
    private lateinit var intent: Intent

    companion object {
        const val KEY_FOR_PLAYLISTS = "key.playlists"
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
            viewModel = ViewModelProvider(this)[PlayListsViewModel::class.java]
            viewModel.playlists().observe(this) {
                Log.e("ololo", it.items.toString())
                adapter.addPlaylists(it.items)
                //inet=false
            }

    }


    override fun initViews() {
        super.initViews()
        binding.recyclerPlaylists.layoutManager = LinearLayoutManager(this)
        binding.recyclerPlaylists.adapter = adapter
    }

    override fun isConnection() {
        super.isConnection()
        connection = CheckConnection(applicationContext)
        connection.observe(this) {
            if (it) {
                binding.noConnection.visibility = View.GONE
                if (inet == false) {
                    initViewModel()
                    inet=true
                }
            } else {
                inet = false
                binding.noConnection.visibility = View.VISIBLE
            }
        }

//        cld = ConnectionLiveData(application)
//        cld.observe(this) {
//            if (it) {
//                binding.noConnection.visibility = View.GONE
//                binding.recyclerPlaylists.visibility = View.VISIBLE
//                if (inet == false) {
//                    initViewModel()
//                    inet=true
//                }
//            } else {
//                binding.noConnection.visibility = View.VISIBLE
//                binding.recyclerPlaylists.visibility = View.GONE
//                inet= false
//            }
//        }
    }

    private fun onClick(id: String) {
        intent = Intent(this, VideoActivity::class.java)
        intent.putExtra(KEY_FOR_PLAYLISTS, id)
        startActivity(intent)
    }
}