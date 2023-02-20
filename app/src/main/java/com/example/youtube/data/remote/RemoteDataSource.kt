package com.example.youtube.data.remote

import com.example.youtube.BuildConfig
import com.example.youtube.PlayList
import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.utils.Constant

class RemoteDataSource : BaseDataSource() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists(): Resource<PlayList> {
        return getResult {
            apiService.getPlaylists(
                BuildConfig.API_KEY,
                Constant.part,
                Constant.channelId
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String, itemCount: Int): Resource<PlayList> {
        return getResult {
            apiService.getPlaylistItems(BuildConfig.API_KEY, Constant.part, playlistId, itemCount)
        }
    }

    suspend fun getVideo(videoId: String): Resource<PlayList>{
        return getResult {
            apiService.getVideos(BuildConfig.API_KEY,Constant.part,videoId)
        }
    }

}