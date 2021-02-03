package com.ez.dotarate.model.repository

import androidx.lifecycle.LiveData
import com.ez.dotarate.database.User
import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserName
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import retrofit2.Response

interface UserRepository {
    // Database
    suspend fun saveUserId(userId: UserId)
    suspend fun getUserId(): UserId?
    suspend fun logout()
    suspend fun saveUser(user: User)
    fun getUser(): LiveData<User>
    fun getUserName(): LiveData<UserName?>

    // Network
    suspend fun getUserResponse(id: Int): UserResponse?
    suspend fun getWinsAndLosses(id: Int): WinsAndLosses?
}