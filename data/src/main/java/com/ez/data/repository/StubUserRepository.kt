package com.ez.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ez.domain.model.*
import com.ez.domain.repository.UserRepository

class StubUserRepository : UserRepository {
    override suspend fun saveUserId(userId: UserId) {}

    override suspend fun getUserId(): UserId? = null

    override suspend fun logout() {}

    override suspend fun saveUser(user: User) {}

    override fun getUser(): LiveData<User?> = liveData {}

    override fun getUserName(): LiveData<UserName?> = liveData {}

    override suspend fun getUserResponse(id: Int): UserResponse? = null

    override suspend fun getWinsAndLosses(id: Int): WinsAndLosses? = null
}