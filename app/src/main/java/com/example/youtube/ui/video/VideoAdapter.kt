package com.example.youtube.ui.video

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.ItemsData
import com.example.youtube.databinding.ItemVideoBinding

class VideoAdapter(val onCLick:(id: String) -> Unit ) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videoList = arrayListOf<ItemsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addVideo(videoLists: List<ItemsData>) {
        videoList.addAll(videoLists)
        notifyDataSetChanged()
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(videoList: ItemsData) {
            with(binding) {
                itemView.setOnClickListener {
                    onCLick(videoList.contentDetails.videoId)
                }
                videoTitle.text = videoList.snippet.title
                Glide.with(imgVideo).load(videoList?.snippet?.thumbnails?.maxres?.url.toString())
                    .into(imgVideo)
            }
        }
    }

}