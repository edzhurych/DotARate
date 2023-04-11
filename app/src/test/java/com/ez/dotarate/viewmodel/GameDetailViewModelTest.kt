package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ez.domain.model.GameDetail
import com.ez.domain.repository.OpenDotaRepository
import com.ez.dotarate.view.gamedetail.GameDetailViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
class GameDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private var id: Long = 1
    private val mApplication = mock(Application::class.java)
    private val mOpenDotaRepository = mock(OpenDotaRepository::class.java)
    var viewModel: GameDetailViewModel = GameDetailViewModel(mApplication, mOpenDotaRepository)

    var gameDetail: GameDetail = GameDetail(
        0, 0, 0, 0,
        emptyList(), 0, true, 0, 9
    )
    private val testDispatcher = TestCoroutineDispatcher()
    private val errorMessage = "Проблемы при попытке получить данные"

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getGameDetail_isValid() {
        println("getGameDetail. Thread - ${Thread.currentThread().name}")
        runBlocking(testDispatcher) {
            println("setUp. Thread - ${Thread.currentThread().name}")
            `when`(mOpenDotaRepository.fetchGameDetail(id)).thenReturn(gameDetail)
        }

        viewModel.getGameDetail(id)
        Thread.sleep(1000)
        assertThat(viewModel.liveGame.value, `is`(gameDetail))
    }

    @Test
    fun getGameDetail_inNotValid() {
        println("getGameDetail. Thread - ${Thread.currentThread().name}")

        runBlocking(testDispatcher) {
            println("setUp. Thread - ${Thread.currentThread().name}")
            `when`(mOpenDotaRepository.fetchGameDetail(id)).thenReturn(null)
        }

        viewModel.getGameDetail(id)
        Thread.sleep(1000)
        assertEquals(errorMessage, viewModel.errorLiveData.value)
    }
}