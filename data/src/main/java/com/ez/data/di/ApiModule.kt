package com.ez.data.di

import android.util.Log
import com.ez.data.BuildConfig
import com.ez.data.constants.BASE_URL_OPENDOTA
import com.ez.data.constants.BASE_URL_PANDASCORE
import com.ez.data.db.AppDatabase
import com.ez.data.db.GameDao
import com.ez.data.db.UserDao
import com.ez.data.db.UserIdDao
import com.ez.data.network.ServerApi
import com.ez.data.repository.OpenDotaRepositoryImpl
import com.ez.data.repository.PandaScoreRepositoryImpl
import com.ez.data.repository.UserRepositoryImpl
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.repository.PandaScoreRepository
import com.ez.domain.repository.UserRepository
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
    fun provideUserRepository(
        @Named("OpenDotaApi") api: ServerApi,
        userIdDao: UserIdDao,
        userDao: UserDao,
        gameDao: GameDao
    ): UserRepository = UserRepositoryImpl(
        api,
        userIdDao,
        userDao,
        gameDao,
    )

    @Provides
    fun provideOpenDotaRepository(
        @Named("OpenDotaApi") api: ServerApi,
        db: AppDatabase
    ): OpenDotaRepository = OpenDotaRepositoryImpl(api, db)

    @Provides
    fun providePandaScoreRepository(@Named("PandaScoreApi") api: ServerApi): PandaScoreRepository =
        PandaScoreRepositoryImpl(api)

    @Named("PandaScoreApi")
    @Provides
    @Singleton
    fun providePandaScoreApi(client: OkHttpClient): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_PANDASCORE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServerApi::class.java)

    @Named("OpenDotaApi")
    @Provides
    @Singleton
    fun provideOpendotaApi(client: OkHttpClient): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServerApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
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