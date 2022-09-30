package com.example.aplicacion_4b_g7.ui.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.aplicacion_4b_g7.data.AppDatabase
import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.viewmodels.LoginViewModel
import com.example.aplicacion_4b_g7.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val db: AppDatabase by inject<AppDatabase>()
    private val memoryDataSource: MemoryDataSource by inject()
    private val scope = CoroutineScope(newSingleThreadContext("splash"))
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart(){
        super.onStart()
        binding.splashAnimation.playAnimation()
        binding.splashAnimation.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                loginViewModel.currentUser()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
        observeViewModels()
    }

    private fun observeViewModels(){
        loginViewModel.user.observe(this, Observer {
            var intent = Intent(applicationContext, LoginActivity::class.java)
            if(it != null){
                intent = Intent(applicationContext, HomeActivity::class.java)
            }
            startActivity(intent)
            finish()
        })
    }
}