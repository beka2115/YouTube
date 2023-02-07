package com.example.youtube.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.PlayList
import com.example.youtube.base.BaseViewModel
import com.example.youtube.`object`.Constant
import com.example.youtube.remote.ApiService
import com.example.youtube.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListsViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists():LiveData<PlayList>{
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()

        apiService.getPlaylists(BuildConfig.API_KEY,Constant.part,Constant.channelId)
            .enqueue(object: Callback<PlayList>{
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful){
                        data.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }

}