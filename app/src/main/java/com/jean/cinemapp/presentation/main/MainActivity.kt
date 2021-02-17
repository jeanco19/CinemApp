package com.jean.cinemapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private var mIsCloseAppDialogEnable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpBottomNavigation()
        verifyNavDestinations()
    }

    private fun setUpBottomNavigation() {
        mNavController = (supportFragmentManager.findFragmentById(mBinding.navHostFragment.id) as NavHostFragment).navController
        AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile, R.id.navigation_favorite))
        mBinding.navView.setupWithNavController(mNavController)
    }

    private fun verifyNavDestinations() {
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile, R.id.navigation_favorite -> {
                    mIsCloseAppDialogEnable = true
                    showBottomNavigation()
                }
                R.id.front_fragment -> {
                    mIsCloseAppDialogEnable = true
                    hideBottomNavigation()
                }
                R.id.sign_in_fragment, R.id.sign_up_fragment, R.id.recover_password_fragment, R.id.movie_detail -> {
                    mIsCloseAppDialogEnable = false
                    hideBottomNavigation()
                }
            }
        }
    }

    fun showBottomNavigation() {
        mBinding.navView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        mBinding.navView.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {
        if (mIsCloseAppDialogEnable) showCloseAppDialog() else super.onBackPressed()
    }

    private fun showCloseAppDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.dialog_close_app_title))
            .setMessage(getString(R.string.dialog_close_app_message))
            .setPositiveButton(getString(R.string.dialog_close_button)) { dialog, which -> finish() }
            .setNegativeButton(getString(R.string.dialog_cancel_button), null)
            .show()
    }
}