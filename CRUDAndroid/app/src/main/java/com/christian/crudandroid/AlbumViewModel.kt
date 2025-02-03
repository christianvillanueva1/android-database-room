package com.christian.crudandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {

    val albumDao = MainApplication.albumDatabase.getAlbumDao()

    val albumList : LiveData<List<Album>> = albumDao.getAllAlbums()

    fun addAlbum(name: String, singer: String, songs: Int ){
        viewModelScope.launch(Dispatchers.IO) {
            albumDao.addAlbum(Album(name = name, singer = singer, songs = songs))
        }
    }

    fun deleteAlbum(album: Album){
        viewModelScope.launch(Dispatchers.IO) {
            albumDao.deleteAlbum(album)
        }
    }

    fun updateAlbum(album: Album){
        viewModelScope.launch(Dispatchers.IO) {
            albumDao.updateAlbum(album)
        }
    }
}