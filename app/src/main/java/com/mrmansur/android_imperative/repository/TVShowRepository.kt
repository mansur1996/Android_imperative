package com.mrmansur.android_imperative.repository

import com.mrmansur.android_imperative.db.TVShowDao
import com.mrmansur.android_imperative.model.TVShow
import com.mrmansur.android_imperative.networking.TVShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val tvShowService: TVShowService, private val tvShowDao: TVShowDao) {
    /**
     * Retrofit Related
     */
    suspend fun apiTVShowPopular(page : Int) = tvShowService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q : Int) = tvShowService.apiTVShowDetails(q)

    /**
     * Room Related
     */

    suspend fun getTVShowsFromDB() = tvShowDao.getTVShowsFromDB()
    suspend fun insertTVShowToDB(tvShow: TVShow) = tvShowDao.insertTVShowToDB(tvShow)
    suspend fun deleteTvShowsFromDB() = tvShowDao.deleteTvShowsFromDB()

}