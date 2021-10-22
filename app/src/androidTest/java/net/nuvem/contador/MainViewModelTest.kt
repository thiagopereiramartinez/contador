package net.nuvem.contador

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()

        runBlocking(Dispatchers.Main) {
            viewModel.state.observeForever { }
            viewModel.contador.observeForever { }
        }
    }

    @Test
    fun testInitialState() {
        assertEquals(MainViewModel.State.INITIAL, viewModel.state.value)
        assertEquals(0, viewModel.contador.value)
    }

    @Test
    fun testStartContador() = runBlocking(Dispatchers.Main) {
        viewModel.start()
        delay(2500L)

        assertEquals(MainViewModel.State.STARTED, viewModel.state.value)
        assertEquals(2, viewModel.contador.value)
    }

    @Test
    fun testPauseContador() = runBlocking(Dispatchers.Main) {
        viewModel.start()
        delay(2500L)
        viewModel.pause()
        delay(1500L)

        assertEquals(MainViewModel.State.PAUSED, viewModel.state.value)
        assertEquals(2, viewModel.contador.value)
    }

    @Test
    fun testResetContador() = runBlocking(Dispatchers.Main) {
        viewModel.start()
        delay(2500L)
        viewModel.reset()
        assertEquals(MainViewModel.State.INITIAL, viewModel.state.value)
        assertEquals(0, viewModel.contador.value)
    }
}