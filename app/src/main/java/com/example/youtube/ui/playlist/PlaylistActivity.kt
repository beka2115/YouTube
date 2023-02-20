package com.example.youtube.ui.playlist

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.core.comon.connectionChek.ConnectionLiveData
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.ui.video.VideoActivity

class PlaylistActivity : BaseActivity<ActivityPlaylistsBinding, PlayListsViewModel>() {

    private val adapter = PlaylistsAdapter(this::onClick)
    private lateinit var cld: ConnectionLiveData
    private var intentList = arrayListOf<String>()

    companion object {
        const val KEY_FOR_PLAYLISTS = "key.playlists"
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[PlayListsViewModel::class.java]
        getData()
    }

    override fun initViews() {
        super.initViews()
        binding.recyclerPlaylists.layoutManager = LinearLayoutManager(this)
        binding.recyclerPlaylists.adapter = adapter
    }


    override fun isConnection() {
        super.isConnection()
        cld = ConnectionLiveData(application)
        cld.observe(this) {answer ->
            if (answer) {
                binding.noConnection.btnTryAgain.setOnClickListener {
                        binding.noConnection.noConnection.visibility = View.GONE
                        initViewModel()
                }
            } else {
                binding.noConnection.noConnection.visibility = View.VISIBLE
            }

        }
    }

    private fun getData() {
        viewModel.getPlaylists().observe(this) {
            when (it.status) {

                Status.SUCCESS -> {
                    it.data?.let { it1 -> adapter.addPlaylists(it1.items) }
                    binding.progress.progress.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.progress.progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.noConnection.noConnection.visibility = View.VISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progress.progress.visibility = View.GONE
                }
            }
        }
    }

    private fun onClick(id: String, itemCount: Int, title: String, desc: String) {
        val intent = Intent(this@PlaylistActivity, VideoActivity::class.java)
        intentList.clear()
        intentList.add(id)
        intentList.add(itemCount.toString())
        intentList.add(title)
        intentList.add(desc)
        intent.putStringArrayListExtra(KEY_FOR_PLAYLISTS, intentList)
        startActivity(intent)
    }
}