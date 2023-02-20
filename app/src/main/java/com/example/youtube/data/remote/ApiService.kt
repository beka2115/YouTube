package com.example.youtube.data.remote

import com.example.youtube.PlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int = 25
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int
    ): Response<PlayList>

    @GET("videos")
    suspend fun getVideos(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") id: String
    ): Response<PlayList>

}