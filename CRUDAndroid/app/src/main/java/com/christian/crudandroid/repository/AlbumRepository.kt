package com.christian.crudandroid.repository
import androidx.lifecycle.LiveData
import com.christian.crudandroid.db.AlbumDao
import com.christian.crudandroid.models.Album

class AlbumRepository(private val albumDao: AlbumDao) {
    val allAlbums: LiveData<List<Album>> = albumDao.getAllAlbums()
    suspend fun insertAlbum(album: Album) = albumDao.addAlbum(album)
    suspend fun updateAlbum(album: Album) = albumDao.updateAlbum(album)
    suspend fun deleteAlbum(album: Album) = albumDao.deleteAlbum(album)
}