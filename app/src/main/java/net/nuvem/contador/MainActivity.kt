package net.nuvem.contador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import net.nuvem.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            btnIniciar.setOnClickListener {
                viewModel.start()
            }
            btnPausar.setOnClickListener {
                viewModel.pause()
            }
            btnResetar.setOnClickListener {
                viewModel.reset()
            }
        }

        viewModel.state.observe(this) { state ->
            when (state) {
                MainViewModel.State.STARTED -> {
                    with(binding) {
                        btnIniciar.isEnabled = false
                        btnPausar.isEnabled = true
                        btnResetar.isEnabled = true
                    }
                }
                MainViewModel.State.PAUSED -> {
                    with(binding) {

                        btnIniciar.isEnabled = true
                        btnIniciar.text = "Retomar"
                        btnPausar.isEnabled = false
                        btnResetar.isEnabled = true
                    }
                }
                MainViewModel.State.INITIAL -> {
                    with(binding) {
                        btnIniciar.isEnabled = true
                        btnPausar.isEnabled = false
                        btnResetar.isEnabled = false
                    }
                }
            }
        }

        viewModel.contador.observe(this) {
            binding.tvContador.text = it.toString()
        }

    }
}