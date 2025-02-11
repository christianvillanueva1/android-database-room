package com.christian.crudandroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.christian.crudandroid.R
import com.christian.crudandroid.models.Album
import com.christian.crudandroid.viewmodel.AlbumViewModel

class EditAlbumFragment  : Fragment() {

    private val albumViewModel: AlbumViewModel by viewModels()
    private val args: EditAlbumFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_album, container, false)

        // Referencias a los campos del diseño
        val editTextName = view.findViewById<EditText>(R.id.editTextAlbumName)
        val editTextSinger = view.findViewById<EditText>(R.id.editTextAlbumSinger)
        val editTextSongs = view.findViewById<EditText>(R.id.editTextAlbumSongs)
        val buttonSaveChanges = view.findViewById<Button>(R.id.buttonSaveChanges)

        // Cargar datos existentes del juego
        val album = args.album
        editTextName.setText(album.name)
        editTextSinger.setText(album.singer)
        editTextSongs.setText(album.songs.toString())

        // Guardar cambios
        buttonSaveChanges.setOnClickListener {
            val updatedName = editTextName.text.toString().trim()
            val updatedSinger = editTextSinger.text.toString().trim()
            val updatedSongsString = editTextSongs.text.toString().trim()

            try {
                if (updatedName.isNotEmpty() && updatedSinger.isNotEmpty() && updatedSongsString.isNotEmpty()) {
                    val updatedSongs = updatedSongsString.toInt()

                    val updatedAlbum = Album(
                        id = album.id,
                        name = updatedName,
                        singer = updatedSinger,
                        songs = updatedSongs
                    )

                    albumViewModel.updatealbum(updatedAlbum)
                    Toast.makeText(requireContext(), "Album updated", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_editAlbumFragment_to_albumListFragment)
                } else {
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(requireContext(), "The number of songs must be a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para volver a la vista principal
        val btnBackToMain = view.findViewById<Button>(R.id.btnBackToMain)
        btnBackToMain.setOnClickListener {
            findNavController().navigate(R.id.action_editAlbumFragment_to_albumListFragment)
        }

        return view
    }
}
