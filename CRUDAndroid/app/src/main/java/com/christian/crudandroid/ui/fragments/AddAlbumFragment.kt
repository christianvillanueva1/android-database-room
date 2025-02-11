package com.christian.crudandroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.christian.crudandroid.R
import com.christian.crudandroid.models.Album
import com.christian.crudandroid.viewmodel.AlbumViewModel

class AddAlbumFragment: Fragment() {

    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_album, container, false)

        albumViewModel = ViewModelProvider(this)[AlbumViewModel::class.java]

        val titleInput = view.findViewById<EditText>(R.id.etAlbumName)
        val genreInput = view.findViewById<EditText>(R.id.etAlbumSinger)
        val releaseYearInput = view.findViewById<EditText>(R.id.etAlbumSongs)

        view.findViewById<Button>(R.id.btnSaveAlbum).setOnClickListener {
            val name = titleInput.text.toString().trim()
            val singer = genreInput.text.toString().trim()
            val songs = releaseYearInput.text.toString().trim()

            if (name.isNotEmpty() && singer.isNotEmpty() && songs.isNotEmpty()) {
                try {
                    val album = Album(
                        name = name,
                        singer = singer,
                        songs = songs.toInt()
                    )
                    albumViewModel.insertalbum(album)
                    Toast.makeText(requireContext(), "Album succsessfully added", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addAlbumFragment_to_albumListFragment)
                } catch (e: NumberFormatException) {
                    Toast.makeText(requireContext(), "The number of songs must be a valid number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        val btnBackToMain = view.findViewById<Button>(R.id.btnBackToMain)
        btnBackToMain.setOnClickListener {
            findNavController().navigate(R.id.action_addAlbumFragment_to_albumListFragment)
        }

        return view
    }
}
