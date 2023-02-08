package com.example.youtube.ui.playlist

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.core.comon.connectionChek.ConnectionLiveData
import com.example.youtube.core.network.result.Status
import com.example.youtube.ui.video.VideoActivity
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding

class PlaylistActivity : BaseActivity<ActivityPlaylistsBinding, PlayListsViewModel>() {

    private val adapter = PlaylistsAdapter(this::onClick)
    private lateinit var cld: ConnectionLiveData

    companion object {
        const val KEY_FOR_PLAYLISTS = "key.playlists"
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[PlayListsViewModel::class.java]
        viewModel.getPlaylists().observe(this) {

            when(it.status){

                Status.SUCCESS -> {
                    it.data?.let { it1 -> adapter.addPlaylists(it1.items) }
                }
                Status.LOADING -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

            }


        }
    }

    override fun initViews() {
        super.initViews()
        binding.recyclerPlaylists.layoutManager = LinearLayoutManager(this)
        binding.recyclerPlaylists.adapter = adapter
    }

    override fun isConnection() {
        super.isConnection()
        cld = ConnectionLiveData(application)
        cld.observe(this) {
            if (it) {
                binding.noConnection.visibility = View.GONE
                initViewModel()

            } else {
                binding.noConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun onClick(id: String) {
        val intent = Intent(this@PlaylistActivity, VideoActivity::class.java)
        intent.putExtra(KEY_FOR_PLAYLISTS, id)
        startActivity(intent)
    }
}