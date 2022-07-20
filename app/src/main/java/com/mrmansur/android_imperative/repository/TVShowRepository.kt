package com.mrmansur.android_imperative.repository

import com.mrmansur.android_imperative.networking.TVShowService
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val tvShowService: TVShowService) {
    /**
     * Retrofit Related
     */
    suspend fun apiTVShowPopular(page : Int) = tvShowService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q : Int) = tvShowService.apiTVShowDetails(q)

    /**
     * Room Related
     */
}