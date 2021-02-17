package com.jean.cinemapp.presentation.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentSignInBinding
import com.jean.cinemapp.presentation.auth.viewmodel.SignInViewModel
import com.jean.cinemapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: Fragment() {

    private var _mBinding: FragmentSignInBinding? = null
    private val mBinding get() = _mBinding!!
    private val mSignInViewModel by viewModels<SignInViewModel>()
    private val mProgressDialog = BaseProgressDialog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        mBinding.ivArrowBack.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToFrontFragment())
        }
        mBinding.btnSignIn.setOnClickListener {
            validateSignInInputs()
        }
        mBinding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToRecoverPasswordFragment())
        }
    }

    private fun validateSignInInputs() {
        if (isFormComplete()) {
            val email = mBinding.tietEmail.text.toString().trim()
            val password = mBinding.tietPassword.text.toString().trim()
            mSignInViewModel.doSignIn(email, password).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
                override fun onChanged(result: Resource<Boolean>) {
                    when (result) {
                        is Resource.Loading -> {
                            mProgressDialog.show(requireContext(), getString(R.string.dialog_sign_in_message))
                        }
                        is Resource.Success -> {
                            mProgressDialog.mDialog.dismiss()
                            mSignInViewModel.mSignIn.removeObserver(this)
                            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMobileNavigation())
                        }
                        is Resource.Error -> {
                            mProgressDialog.mDialog.dismiss()
                            mSignInViewModel.mSignIn.removeObserver(this)
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }

    private fun isFormComplete(): Boolean {
        return mBinding.tietEmail.validateInput(requireContext(), mBinding.tilEmail) &&
               mBinding.tietEmail.validateEmailInput(requireContext(), mBinding.tilEmail) &&
               mBinding.tietPassword.validateInput(requireContext(), mBinding.tilPassword) &&
               mBinding.tietPassword.validatePasswordLengthInput(requireContext(), mBinding.tilPassword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}