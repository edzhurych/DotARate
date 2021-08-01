//package com.ez.data.repository
//
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.ez.data.constants.BASE_URL_OPENDOTA
//import com.ez.data.network.ServerApi
//import org.junit.Test
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.runner.RunWith
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@RunWith(AndroidJUnit4::class)
//class OpenDotaRepositoryImplTest {
//
//    val serverApi = Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(getClient())
//        .build().create(ServerApi::class.java)
//
//    val repository = OpenDotaRepositoryImpl(serverApi, )
//
//    @Test
//    fun getGamesDataSourceFactory() {
//
//    }
//
//    @Before
//    fun setUp() {
//
//    }
//}