package com.example.youtube.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.ItemsData
import com.example.youtube.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(val onClick: (id: String, itemCount: Int, title: String, desc: String) -> Unit) :
    RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    private var playlists = arrayListOf<ItemsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(playlists[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPlaylists(playlist: List<ItemsData>) {
        playlists.clear()
        playlists.addAll(playlist)
        playlists.removeAt(0)
        notifyDataSetChanged()
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(playlist: ItemsData) {
            with(binding) {
                initClickers(playlist)
                textTitle.text = playlist.snippet.title
                Glide.with(imgVideo).load(playlist?.snippet?.thumbnails?.maxres?.url)
                    .into(imgVideo)
                videoCount.text = "${playlist.contentDetails.itemCount} video series"
            }
        }

        private fun initClickers(playlist: ItemsData) {
            itemView.setOnClickListener {
                onClick(
                    playlist.id,
                    playlist.contentDetails.itemCount,
                    playlist.snippet.title,
                    playlist.snippet.description
                )
            }
        }
    }
}