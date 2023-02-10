package com.example.youtube.ui.video

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.BuildConfig
import com.example.youtube.PlayList
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.utils.Constant

class VideoViewModel: BaseViewModel() {

    fun getPlaylistItems(playlistId:String, itemCount:Int):LiveData<Resource<PlayList>>{
        return App().repository.getPlaylistItems(playlistId,itemCount)
    }
}