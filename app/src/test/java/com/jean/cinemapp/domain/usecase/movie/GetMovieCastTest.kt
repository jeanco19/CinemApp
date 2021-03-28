package com.jean.cinemapp.domain.usecase.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import com.jean.cinemapp.utils.TestCoroutineRules
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetMovieCastTest {

    private val castList = listOf<Cast>()
    private val movieId = "1"

    // RULES

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRules()

    // MOCKS

    private val movieRepository = Mockito.mock(MovieRepository::class.java)
    private val getMovieCast = GetMovieCastImpl(movieRepository)

    // MOCK STATES
    private val successResult = Resource.Success<List<Cast>>(castList)
    private val errorResultEmptyList = Resource.Error<List<Cast>>(ErrorTypes.EMPTY_LIST)
    private val errorResultFailedResponse = Resource.Error<List<Cast>>(ErrorTypes.FAILED_RESPONSE)
    private val errorResultFailedConnection = Resource.Error<List<Cast>>(ErrorTypes.FAILED_CONNECTION)
    private val errorResultWithoutConnection = Resource.Error<List<Cast>>(ErrorTypes.WITHOUT_CONNECTION)

    @Test
    fun `get success result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(successResult)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Success::class.java)

            val response = (result as Resource.Success).data
            Truth.assertThat(response).isInstanceOf(List::class.java)
        }
    }

    @Test
    fun `get error result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(errorResultEmptyList)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)

            val response = (result as Resource.Error).errorType
            Truth.assertThat(response).isInstanceOf(ErrorTypes::class.java)
        }
    }

    @Test
    fun `get error empty list result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(errorResultEmptyList)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)

            val response = (result as Resource.Error).errorType
            Truth.assertThat(response).isEqualTo(ErrorTypes.EMPTY_LIST)
        }
    }

    @Test
    fun `get error failed response result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(errorResultFailedResponse)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)

            val response = (result as Resource.Error).errorType
            Truth.assertThat(response).isEqualTo(ErrorTypes.FAILED_RESPONSE)
        }
    }

    @Test
    fun `get error failed connection result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(errorResultFailedConnection)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)

            val response = (result as Resource.Error).errorType
            Truth.assertThat(response).isEqualTo(ErrorTypes.FAILED_CONNECTION)
        }
    }

    @Test
    fun `get error without connection result from repository`() {
        runBlockingTest {
            given(getMovieCast.invoke(movieId)).willReturn(errorResultWithoutConnection)

            val result = getMovieCast.invoke(movieId)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)

            val response = (result as Resource.Error).errorType
            Truth.assertThat(response).isEqualTo(ErrorTypes.WITHOUT_CONNECTION)
        }
    }
}