package com.jean.cinemapp.data.database

import androidx.room.*
import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCast(castEntity: CastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM table_movie")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM table_cast WHERE movie_id LIKE :movieId")
    suspend fun getMovieCast(movieId: Int): List<CastEntity>

    @Query("SELECT * FROM table_favorite")
    fun getFavoriteMovies(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM table_favorite")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavoriteMovie(favoriteEntity: FavoriteEntity)
}