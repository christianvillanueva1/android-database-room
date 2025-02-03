package com.christian.crudandroid

import android.app.Application
import androidx.room.Room
import com.christian.crudandroid.db.AlbumDatabase

class MainApplication : Application() {

    companion object {
        lateinit var albumDatabase: AlbumDatabase
    }

    override fun onCreate() {
        super.onCreate()
        albumDatabase = Room.databaseBuilder(
            applicationContext,
            AlbumDatabase::class.java,
            AlbumDatabase.NAME
        ).build()
    }
}

