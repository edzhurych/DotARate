package com.ez.domain.repository

import androidx.lifecycle.LiveData
import com.ez.domain.model.*

interface UserRepository {
    // Database
    suspend fun saveUserId(userId: UserId)
    suspend fun getUserId(): UserId?
    suspend fun logout()
    suspend fun saveUser(user: User)
    suspend fun getUser(): User?
    fun getUserName(): LiveData<UserName?>

    // Network
    suspend fun getUserResponse(id: Int): UserResponse?
    suspend fun getWinsAndLosses(id: Int): WinsAndLosses?
}