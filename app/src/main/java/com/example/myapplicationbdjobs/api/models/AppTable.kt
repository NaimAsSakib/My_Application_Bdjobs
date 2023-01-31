package com.example.myapplicationbdjobs.api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appTable")
data class AppTable(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "originalTitle")
    val originalTitle: String? = null,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: String? = "",

    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,


    )
