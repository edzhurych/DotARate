package com.ez.infrastructure.di

import android.util.Log
import com.ez.data.BuildConfig
import com.ez.data.constants.BASE_URL_OPENDOTA
import com.ez.data.constants.BASE_URL_PANDASCORE
import com.ez.data.network.ServerApi
import com.ez.data.repository.OpenDotaRepositoryImpl
import com.ez.data.repository.PandaScoreRepositoryImpl
import com.ez.data.repository.UserRepositoryImpl
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.repository.PandaScoreRepository
import com.ez.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OpenDotaApi
class PandaScoreApi

val apiModule = module {

    single(named<OpenDotaApi>()) {
        Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build().create(ServerApi::class.java)
    }

    single(named<PandaScoreApi>()) {
        Retrofit.Builder().baseUrl(BASE_URL_PANDASCORE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build().create(ServerApi::class.java)
    }

    factory<UserRepository> {
        UserRepositoryImpl(
            get(named<OpenDotaApi>()),
            get(),
            get(),
            get(),
        )
    }

    factory<OpenDotaRepository> { OpenDotaRepositoryImpl(get(named<OpenDotaApi>()), get()) }

    factory<PandaScoreRepository> { PandaScoreRepositoryImpl(get(named<PandaScoreApi>())) }

    factory<OkHttpClient> {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        }).apply {
            level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE)
        }

        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    }
}