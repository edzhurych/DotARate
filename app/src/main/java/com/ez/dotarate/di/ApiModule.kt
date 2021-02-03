package com.ez.dotarate.di

import android.util.Log
import com.ez.dotarate.BuildConfig
import com.ez.dotarate.constants.BASE_URL_OPENDOTA
import com.ez.dotarate.constants.BASE_URL_PANDASCORE
import com.ez.dotarate.constants.BASE_URL_STEAM
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import com.ez.dotarate.model.repository.PandaScoreRepository
import com.ez.dotarate.model.repository.PandaScoreRepositoryImpl
import com.ez.dotarate.model.repository.UserRepositoryImpl
import com.ez.dotarate.network.ServerApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    internal fun provideUserRepository(@Named("OpenDotaApi") api: ServerApi, db: AppDatabase) =
        UserRepositoryImpl(api, db)

    @Provides
    internal fun provideOpenDotaRepository(@Named("OpenDotaApi") api: ServerApi, db: AppDatabase) =
        OpenDotaRepositoryImpl(api, db)

    @Provides
    internal fun providePandaScoreRepository(@Named("PandaScoreApi") api: ServerApi): PandaScoreRepository =
        PandaScoreRepositoryImpl(api)

    @Named("PandaScoreApi")
    @Provides
    @Singleton
    internal fun providePandaScoreApi(client: OkHttpClient): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_PANDASCORE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServerApi::class.java)

    @Named("OpenDotaApi")
    @Provides
    @Singleton
    internal fun provideOpendotaApi(client: OkHttpClient): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServerApi::class.java)

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        }).apply {
            level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}