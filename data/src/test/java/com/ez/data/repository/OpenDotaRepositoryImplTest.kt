package com.ez.data.repository

import com.ez.data.db.AppDatabase
import com.ez.data.network.ServerApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock

class OpenDotaRepositoryImplTest {

    val db = mock(AppDatabase::class.java)
    val repository = OpenDotaRepositoryImpl(mock(ServerApi::class.java), db)

    @Test
    fun getGamesDataSourceFactory() {

    }

    @Before
    fun setUp() {
        when(db.gameDao())
    }
}