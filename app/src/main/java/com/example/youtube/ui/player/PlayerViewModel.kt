package com.example.youtube.ui.player

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.PlayList
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel

class PlayerViewModel : BaseViewModel() {

    fun getVideos(videoId: String): LiveData<Resource<PlayList>> {
        return App().repository.getVideos(videoId)
    }

}