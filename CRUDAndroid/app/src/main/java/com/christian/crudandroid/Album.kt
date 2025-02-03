package com.christian.crudandroid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name:String,
    var singer: String,
    var songs: Int
)