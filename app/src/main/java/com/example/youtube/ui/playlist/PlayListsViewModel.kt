package com.example.youtube.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.PlayList
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource

class PlayListsViewModel: BaseViewModel() {


    fun getPlaylists():LiveData<Resource<PlayList>>{
        return App().repository.getPlaylists()
    }


}