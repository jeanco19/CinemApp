package com.jean.cinemapp.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.data.database.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class CinemaDaoTest {

    // RULES

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    // INIT

    private lateinit var database: CinemaDatabase
    private lateinit var dao: CinemaDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), CinemaDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cinemaDao()
    }

    @Test
    fun saveAndGetMovie() = runBlockingTest {
        val movie = MovieEntity(1, "Overview", "Poster", "Date", "Title", 123.45f, 345.675f, "Popular")
        dao.saveMovie(movie)
        val movies = dao.getAllMovies()
        assertThat(movies).contains(movie)
    }

    @Test
    fun saveAndGetCast() = runBlockingTest {
        val cast = CastEntity(1, "Name", "Image", 1)
        dao.saveCast(cast)
        val casts = dao.getMovieCast(1)
        assertThat(casts).contains(cast)
    }

    @Test
    fun saveAndGetFavoriteMovie() = runBlockingTest {
        val favorite = FavoriteEntity(1, "Overview", "Poster", "Date", "Title", 123.45f, 345.675f)
        dao.saveFavorite(favorite)
         val job = launch(Dispatchers.IO) {
             dao.getFavoriteMovies().collect { favorites ->
                 assertThat(favorites).contains(favorite)
             }
         }
        job.cancel()
    }

    @Test
    fun deleteAllFavoritesMovies() = runBlockingTest {
        val favorite = FavoriteEntity(1, "Overview", "Poster", "Date", "Title", 123.45f, 345.675f)
        val favorite1 = FavoriteEntity(2, "Overview1", "Poster1", "Date1", "Title1", 123.46f, 345.676f)
        dao.saveFavorite(favorite)
        dao.saveFavorite(favorite1)
        dao.deleteAllFavorites()
        val job = launch(Dispatchers.IO) {
            dao.getFavoriteMovies().collect { favorites ->
                assertThat(favorites).isEmpty()
            }
        }
        job.cancel()
    }

    @Test
    fun deleteFavoriteMovie() = runBlockingTest {
        val favorite = FavoriteEntity(1, "Overview", "Poster", "Date", "Title", 123.45f, 345.675f)
        dao.saveFavorite(favorite)
        dao.deleteFavoriteMovie(favorite)
        val job = launch(Dispatchers.IO) {
            dao.getFavoriteMovies().collect { favorites ->
                assertThat(favorites).doesNotContain(favorite)
            }
        }
        job.cancel()
    }

    @After
    fun tearDown() {
        database.close()
    }
}