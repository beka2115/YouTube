package com.example.youtube.ui.player

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.youtube.core.comon.connectionChek.ConnectionLiveData
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityPlayerBinding
import com.example.youtube.ui.video.VideoActivity

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {

    private var videoId: String? = null
    private lateinit var cld: ConnectionLiveData


    override fun inflateViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }


    override fun isConnection() {
        super.isConnection()
        cld = ConnectionLiveData(application)
        cld.observe(this) { answer ->
            if (answer) {
                binding.isConnection.btnTryAgain.setOnClickListener {
                    binding.isConnection.noConnection.visibility = View.GONE
                    initViewModel()
                }
            } else {
                binding.isConnection.noConnection.visibility = View.VISIBLE
            }

        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[PlayerViewModel::class.java]
        getData()
    }

    override fun initViews() {
        super.initViews()
        receiveData()
    }

    override fun initListener() {
        super.initListener()
        with(binding){
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun getData() {
        viewModel.getVideos(videoId.toString()).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        Glide.with(binding.imgPlayer)
                            .load(it.data.items[0].snippet.thumbnails.maxres.url)
                            .into(binding.imgPlayer)
                        binding.textTitle.text = it.data.items[0].snippet.title
                        binding.textDesc.text = it.data.items[0].snippet.description
                        binding.progressBar.progress.visibility = View.GONE
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.isConnection.noConnection.visibility = View.VISIBLE
                    binding.progressBar.progress.visibility = View.GONE
                }
            }
        }
    }

    private fun receiveData() {
        val intent = intent
        videoId = intent.getStringExtra(VideoActivity.KEY_FOR_VIDEO)
    }
}