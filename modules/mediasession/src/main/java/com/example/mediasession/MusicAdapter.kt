package com.example.mediasession

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private val layoutInflater: LayoutInflater,
    private val musicClick: (Music) -> Unit
) : ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffCallback()) {

    inner class MusicViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)

        fun bind(music: Music) {
            title.text = music.title

            view.setOnClickListener {
                musicClick(music)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(layoutInflater.inflate(R.layout.music_item, parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }
}