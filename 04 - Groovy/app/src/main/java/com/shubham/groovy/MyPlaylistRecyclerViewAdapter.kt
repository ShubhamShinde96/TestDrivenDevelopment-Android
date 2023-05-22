package com.shubham.groovy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubham.groovy.databinding.PlaylistItemBinding


class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.playlist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.playlistNameTv.text = item.name
        holder.playlistCategoryTv.text = item.category

        holder.playlistImage.setImageResource(item.image)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val playlistNameTv: TextView = view.findViewById(R.id.playlist_name)
        val playlistCategoryTv: TextView = view.findViewById(R.id.playlist_category)

        val playlistImage: ImageView = view.findViewById(R.id.playlist_image)
    }

}