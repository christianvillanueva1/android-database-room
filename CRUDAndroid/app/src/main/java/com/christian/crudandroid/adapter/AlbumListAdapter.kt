package com.christian.crudandroid.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.christian.crudandroid.R
import com.christian.crudandroid.models.Album

class AlbumListAdapter(
    private val onEditClick: (Album) -> Unit,
    private val onDeleteClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {

    private var albums = emptyList<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val currentalbum = albums[position]
        holder.bind(currentalbum)
    }

    override fun getItemCount() = albums.size

    fun submitList(albumList: List<Album>) {
        albums = albumList
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val singerTextView: TextView = itemView.findViewById(R.id.textViewSinger)
        private val songsTextView: TextView = itemView.findViewById(R.id.textViewSongs)
        private val editButton: Button = itemView.findViewById(R.id.buttonEdit)
        private val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(album: Album) {
            nameTextView.text = album.name
            singerTextView.text = album.singer
            songsTextView.text = album.songs.toString()

            editButton.setOnClickListener {
                onEditClick(album)
            }

            deleteButton.setOnClickListener {
                showDeleteConfirmationDialog(album)
            }
        }

        private fun showDeleteConfirmationDialog(album: Album) {
            AlertDialog.Builder(itemView.context)
                .setTitle("Confirm delete")
                .setMessage("Are you sure you want to delete the album \"${album.name}\"?")
                .setPositiveButton("Yes") { _, _ ->
                    onDeleteClick(album)
                    showDeleteSuccessDialog(album)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        private fun showDeleteSuccessDialog(album: Album) {
            AlertDialog.Builder(itemView.context)
                .setTitle("album deleted")
                .setMessage("The album \"${album.name}\" has been successfully deleted.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}