package com.christian.crudandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.christian.crudandroid.models.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ALBUM")
    fun getAllAlbums() : LiveData<List<Album>>

    @Insert
    suspend fun addAlbum(album: Album)

    @Delete
    suspend fun deleteAlbum(album: Album)

    @Update
    suspend fun updateAlbum(album: Album)
}

