package com.mrmansur.android_imperative.di

import com.mrmansur.android_imperative.networking.Server.IS_TESTER
import com.mrmansur.android_imperative.networking.Server.SERVER_DEVELOPMENT
import com.mrmansur.android_imperative.networking.Server.SERVER_PRODUCTION
import com.mrmansur.android_imperative.networking.TVShowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /**
     * Retrofit Related
     */
    @Provides
    fun server() : String {
        if(IS_TESTER)  return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient() : Retrofit{
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService() : TVShowService{
        return retrofitClient().create(TVShowService::class.java)
    }

    /**
     * Room Related
     */

}