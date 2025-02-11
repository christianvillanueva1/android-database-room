package com.christian.crudandroid.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.christian.crudandroid.models.Album
import com.christian.crudandroid.db.AlbumDatabase
import com.christian.crudandroid.repository.AlbumRepository

import kotlinx.coroutines.launch


class AlbumViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository: AlbumRepository
    val allalbums: LiveData<List<Album>>
    init {
        val albumDao = AlbumDatabase.getDatabase(application).albumDao()
        repository = AlbumRepository(albumDao)
        allalbums = repository.allAlbums
    }
    fun insertalbum(album: Album) = viewModelScope.launch {
        repository.insertAlbum(album) }
    fun updatealbum(album: Album) = viewModelScope.launch {
        repository.updateAlbum(album) }
    fun deletealbum(album: Album) = viewModelScope.launch {
        repository.deleteAlbum(album) }
}