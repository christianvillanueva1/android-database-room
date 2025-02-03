package com.christian.crudandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.christian.crudandroid.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    companion object{
        const val NAME = "Album_DB"
    }

    abstract fun getAlbumDao() : AlbumDao
}