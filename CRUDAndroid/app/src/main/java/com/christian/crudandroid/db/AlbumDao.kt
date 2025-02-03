package com.christian.crudandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.christian.crudandroid.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ALBUM")
    fun getAllAlbums() : LiveData<List<Album>>

    @Insert
    fun addAlbum(album: Album)

    @Delete
    fun deleteAlbum(album: Album)

    @Update
    fun updateAlbum(album: Album)
}

