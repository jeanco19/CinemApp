package com.jean.cinemapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class, CastEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class CinemaDatabase: RoomDatabase() {

    abstract fun cinemaDao(): CinemaDao
}