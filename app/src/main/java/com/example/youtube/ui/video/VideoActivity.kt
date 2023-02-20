package com.example.youtube.ui.video

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.core.comon.connectionChek.ConnectionLiveData
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.databinding.ActivityVideoBinding
import com.example.youtube.ui.player.PlayerActivity
import com.example.youtube.ui.playlist.PlaylistActivity

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    private lateinit var cld: ConnectionLiveData
    private val adapter = VideoAdapter(this::onClick)
    private var playlistId: String? = null
    private var itemCount: Int = 1
    private var title: String? = null
    private var description: String? = null
    private var intentList = arrayListOf<String>()
    companion object{
       const val KEY_FOR_VIDEO = "key_video"
    }
    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun isConnection() {
        super.isConnection()
        cld = ConnectionLiveData(application)
        cld.observe(this) { answer ->
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

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        getData()
    }

    @SuppressLint("AppCompatMethod", "SetTextI18n")
    override fun initViews() {
        super.initViews()
        binding.recyclerVideo.layoutManager = LinearLayoutManager(this)
        binding.recyclerVideo.adapter = adapter
        getDataFromPlaylist()
        binding.title.text = title
        binding.desc.text = description
        binding.videoCount.text = "$itemCount video series"
    }

    override fun initListener() {
        super.initListener()
        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }


    private fun getData() {
        playlistId?.let {
            viewModel.getPlaylistItems(it, itemCount).observe(this) {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 -> adapter.addVideo(it1.items) }
                        binding.progress.progress.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progress.progress.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.noConnection.noConnection.visibility = View.VISIBLE
                        binding.progress.progress.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getDataFromPlaylist() {
        intentList.clear()
        val intent = intent
        if (intent != null) {
            intentList =
                intent.getStringArrayListExtra(PlaylistActivity.KEY_FOR_PLAYLISTS) as ArrayList<String>
        }
        playlistId = intentList[0]
        itemCount = intentList[1].toInt()
        title = intentList[2]
        description = intentList[3]
    }

    private fun onClick(id:String){
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(KEY_FOR_VIDEO,id)
        startActivity(intent)
    }

}