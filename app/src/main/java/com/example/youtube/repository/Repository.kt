package com.example.youtube.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.PlayList
import com.example.youtube.data.remote.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Resource<PlayList>> {
        val data = MutableLiveData<Resource<PlayList>>()

        data.value = Resource.loading()

        apiService.getPlaylists(
            BuildConfig.API_KEY,
            Constant.part,
            Constant.channelId
        )
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful) {
                        Log.e("ololo", "hello data")
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {

                    print(t.stackTrace)
                    data.value = Resource.error(t.stackTrace.toString(), null, null)
                }
            })
        return data
    }


    fun getPlaylistItems(playlistId: String,itemCount: Int):LiveData<Resource<PlayList>>{
        val data = MutableLiveData<Resource<PlayList>>()

        data.value = Resource.loading()
        apiService.getPlaylistItems(BuildConfig.API_KEY,Constant.part,playlistId,itemCount).enqueue(object : Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful){
                    data.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                print(t.stackTrace)
                data.value = Resource.error(t.stackTrace.toString(), null, null)
            }

        })
        return data
    }

}