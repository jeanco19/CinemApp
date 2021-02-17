package com.jean.cinemapp.presentation.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

import com.jean.cinemapp.databinding.FragmentFrontBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FrontFragment: Fragment() {

    private var _mBinding: FragmentFrontBinding? = null
    private val mBinding get() = _mBinding!!
    @Inject lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentFrontBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        checkUserSession()
    }

    private fun setUpListeners() {
        mBinding.btnSignIn.setOnClickListener {
            findNavController().navigate(FrontFragmentDirections.actionFrontFragmentToSignInFragment())
        }
        mBinding.btnSignUp.setOnClickListener {
            findNavController().navigate(FrontFragmentDirections.actionFrontFragmentToSignUpFragment())
        }
    }

    private fun checkUserSession() {
        val currentUser = mFirebaseAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(FrontFragmentDirections.actionFrontFragmentToNavigation())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}