package net.nuvem.contador

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.INITIAL)
    val state: LiveData<State>
        get() = _state

    private val _contador: MutableLiveData<Int> = MutableLiveData(0)
    val contador: LiveData<Int>
        get() = _contador

    fun start() {
        _state.value = State.STARTED

        viewModelScope.launch {

            delay(1000L)

            while (_state.value == State.STARTED) {
                val atual = _contador.value ?: 0
                _contador.postValue(atual + 1)
                delay(1000L)
            }

        }
    }

    fun pause() {
        _state.value = State.PAUSED
    }

    fun reset() {
        _state.value = State.INITIAL
        _contador.value = 0
    }

    enum class State {
        INITIAL, STARTED, PAUSED
    }

}