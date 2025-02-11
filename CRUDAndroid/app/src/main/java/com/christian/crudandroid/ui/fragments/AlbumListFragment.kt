package com.christian.crudandroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christian.crudandroid.R
import com.christian.crudandroid.adapter.AlbumListAdapter
import com.christian.crudandroid.models.Album
import com.christian.crudandroid.viewmodel.AlbumViewModel

class AlbumListFragment: Fragment() {

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var adapter: AlbumListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_album_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewAlbums)
        adapter = AlbumListAdapter(
            onEditClick = { album ->
                val action = AlbumListFragmentDirections.actionAlbumListFragmentToEditAlbumFragment(album)
                findNavController().navigate(action)
            },
            onDeleteClick = { album ->
                albumViewModel.deletealbum(album)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        albumViewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        albumViewModel.allalbums.observe(viewLifecycleOwner) { games: List<Album> ->
            adapter.submitList(games)
        }

        view.findViewById<Button>(R.id.buttonAddAlbum).setOnClickListener {
            findNavController().navigate(R.id.action_albumListFragment_to_addAlbumFragment)
        }

        return view
    }
}