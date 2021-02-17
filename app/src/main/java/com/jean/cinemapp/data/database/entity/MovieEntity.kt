package com.jean.cinemapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.cinemapp.utils.Constants.DB_DESCRIPTION
import com.jean.cinemapp.utils.Constants.DB_IMAGE
import com.jean.cinemapp.utils.Constants.DB_RELEASE_DATE
import com.jean.cinemapp.utils.Constants.DB_VOTE_AVERAGE
import com.jean.cinemapp.utils.Constants.TABLE_MOVIE

@Entity(tableName = TABLE_MOVIE)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = DB_DESCRIPTION)
    val overview: String,
    @ColumnInfo(name = DB_IMAGE)
    val posterPath: String,
    @ColumnInfo(name = DB_RELEASE_DATE)
    val releaseDate: String,
    val title: String,
    @ColumnInfo(name = DB_VOTE_AVERAGE)
    val voteAverage: Float,
    val popularity: Float,
    val type: String
)