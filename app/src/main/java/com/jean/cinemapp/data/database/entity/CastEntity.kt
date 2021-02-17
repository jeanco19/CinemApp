package com.jean.cinemapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.cinemapp.utils.Constants.DB_IMAGE
import com.jean.cinemapp.utils.Constants.DB_MOVIE_ID
import com.jean.cinemapp.utils.Constants.TABLE_CAST

@Entity(tableName = TABLE_CAST)
data class CastEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = DB_IMAGE)
    val profilePath: String?,
    @ColumnInfo(name = DB_MOVIE_ID)
    val movieId: Int
)