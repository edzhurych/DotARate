package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GameDetailViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
    ) : AndroidViewModel(application) {

    val liveGame = MutableLiveData<GameDetail>()
    val errorLiveData = MutableLiveData<String>()

    val isLoaded = ObservableBoolean(false)

    fun getGameDetail(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val gameDetails = repository.getGameDetail(id)

                //Log.d("MyLogs", "УСПЕШЕЫЙ ЗАПРОС = ${response.body()}")
                if (gameDetails != null) {
                    liveGame.postValue(gameDetails)
                } else {
                    errorLiveData.postValue("Проблемы при попытке получить данные")
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue("Нет интернета")
            } catch (e: SocketTimeoutException) {
                // Возникает при плохом соединении с интернетом.
                // Когда заканчивается время (timeout) для запроса к серверу.
                // Timeout устанавливается при настройке запроса
                errorLiveData.postValue("Плохое качество соединения с интернетом. Попробуйте позже")
            }
        }
    }
}