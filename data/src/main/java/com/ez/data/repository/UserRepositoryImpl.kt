package com.ez.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ez.data.constants.STEAM_API_KEY
import com.ez.data.db.GameDao
import com.ez.data.db.UserDao
import com.ez.data.db.UserIdDao
import com.ez.data.model.toUserDb
import com.ez.data.model.toUserIdDb
import com.ez.data.network.ServerApi
import com.ez.domain.model.*
import com.ez.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Named


class UserRepositoryImpl
@Inject constructor(
    @Named("OpendotaApi") private val api: ServerApi,
    private val userIdDao: UserIdDao,
    private val userDao: UserDao,
    private val gameDao: GameDao,
) : UserRepository {

    /**
     * This is a "regular" suspending function, which means the caller must
     * be in a coroutine. The repository is not responsible for starting or
     * stopping coroutines since it doesn't have a natural lifecycle to cancel
     * unnecessary work.
     *
     * This *may* be called from Dispatchers.Main and is main-safe because
     * Room will take care of main-safety for us.
     */
    override suspend fun getUserId() = userIdDao.getId()?.toUserId()

    /**
     * Room
     */
    override suspend fun saveUserId(userId: UserId) = userIdDao.saveId(userId.toUserIdDb())

    /**
     * Room
     */
    override suspend fun logout() {
        userIdDao.clearUserId()
        gameDao.deleteGames()
    }

    /**
     * Room
     */
    override suspend fun saveUser(user: User) {
        Log.d("MyLogs", "СОХРАНЯЕМ ПОЛЬЗОВАТЕЛЯ В БД")
        val id = userDao.saveUser(user.toUserDb())
        Log.d("MyLogs", "СОХРАНЯЕМ ПОЛЬЗОВАТЕЛЯ В БД. ID = $id")
    }

    /**
     * Room
     */
    override suspend fun getUser(): User {
        Log.d("MyLogs", "ПОЛУЧАЕМ ПОЛЬЗОВАТЕЛЯ ИЗ БД")
        return userDao.getUser().toUser()
    }

    /**
     * Room
     */
    override fun getUserName(): LiveData<UserName?> {
        Log.d("MyLogs", "ПОЛУЧАЕМ ИМЯ ПОЛЬЗОВАТЕЛЯ ИЗ БД")
        return userDao.getUserName()
    }

    /**
     * GET request.
     * Receive User Data
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<UserResponse> object.
     */
    override suspend fun getUserResponse(id: Int): UserResponse? {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС ПОЛЬЗОВАТЕЛЯ. ID = $id")
        val response = api.getUser(id)

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    /**
     * GET request.
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<WinsAndLosses> object.
     */
    override suspend fun getWinsAndLosses(id: Int): WinsAndLosses? {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС ВИНОВ И ЛУЗОВ. ID = $id")
        val response = api.getWinsAndLosses(id)

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    /**
     * Decode Steam Api Key.
     */
    private fun getK(): String {
        val key = STEAM_API_KEY.split(" ")

        val result = StringBuilder()
        for (i in key) {
            val int = i.toInt() shr 2
            result.append(int.toChar())
        }
        return result.toString()
    }
}