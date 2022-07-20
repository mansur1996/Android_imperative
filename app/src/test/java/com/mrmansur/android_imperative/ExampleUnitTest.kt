package com.mrmansur.android_imperative

import com.mrmansur.android_imperative.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okhttp3.Dispatcher
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkStatusCode() = runTest {
        val response = AppModule().tvShowService().apiTVShowPopular(1)
        assertEquals(response.code(), 200)
    }

    @Test
    fun responseIsSuccessful() = runTest {
        val response = AppModule().tvShowService().apiTVShowPopular(1)
        assertTrue(response.isSuccessful)
    }

    @Test
    fun checkTVShowListNotNull() = runTest {
        val response = AppModule().tvShowService().apiTVShowPopular(1)
        assertNotNull(response.body())
        assertNotNull(response.body()!!.tv_shows)
    }
    @Test
    fun checkTVShowListSize()  = runTest {
        val response = AppModule().tvShowService().apiTVShowPopular(1)
        val tvShowPopular = response.body()
        assertEquals(tvShowPopular!!.tv_shows.size, 20)
    }
    @Test
    fun checkFirstTVShowStatus() = runTest {
        val response = AppModule().tvShowService().apiTVShowPopular(1)
        val tvShowPopular = response.body()
        val tvShoes = tvShowPopular!!.tv_shows
        val tvShow = tvShoes[0]
        assertEquals(tvShow.status, "Running")
    }
}