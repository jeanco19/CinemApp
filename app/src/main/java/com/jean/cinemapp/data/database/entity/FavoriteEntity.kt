package com.jean.cinemapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.cinemapp.utils.Constants
import com.jean.cinemapp.utils.Constants.TABLE_FAVORITE

@Entity(tableName = TABLE_FAVORITE)
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = Constants.DB_DESCRIPTION)
    val overview: String,
    @ColumnInfo(name = Constants.DB_IMAGE)
    val posterPath: String,
    @ColumnInfo(name = Constants.DB_RELEASE_DATE)
    val releaseDate: String,
    val title: String,
    @ColumnInfo(name = Constants.DB_VOTE_AVERAGE)
    val voteAverage: Float,
    val popularity: Float
)