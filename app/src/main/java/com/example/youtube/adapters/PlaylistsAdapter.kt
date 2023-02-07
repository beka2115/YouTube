package com.example.youtube.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.ItemsData
import com.example.youtube.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(val onClick: (id: String) -> Unit) :
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
        notifyDataSetChanged()
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playlist: ItemsData) {
            with(binding) {
                itemView.setOnClickListener {
                    onClick(playlist.id)
                }
                textTitle.text = playlist.snippet.title
                Glide.with(imgVideo).load(playlist.snippet.thumbnails.standard.url).into(imgVideo)
            }
        }
    }

}