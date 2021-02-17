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

import com.jean.cinemapp.databinding.FragmentSignUpBinding
import com.jean.cinemapp.presentation.auth.viewmodel.SignUpViewModel
import com.jean.cinemapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: Fragment() {

    private var _mBinding: FragmentSignUpBinding? = null
    private val mBinding get() = _mBinding!!
    private val mProgressDialog = BaseProgressDialog()
    private val mSignUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        mBinding.ivArrowBack.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToFrontFragment())
        }
        mBinding.btnSignUp.setOnClickListener {
            validateSignUpInputs()
        }
    }

    private fun validateSignUpInputs() {
        if (isFormComplete()) {
            val fullName = "${mBinding.tietName.text?.trim()} ${mBinding.tietLastName.text?.trim()}"
            val email = mBinding.tietEmail.text.toString().trim()
            val password = mBinding.tietPassword.text.toString().trim()
            mSignUpViewModel.doSignUp(email, password, fullName).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
                override fun onChanged(result: Resource<Boolean>) {
                    when (result) {
                        is Resource.Loading -> {
                            mProgressDialog.show(requireContext(), getString(R.string.dialog_sign_up_message))
                        }
                        is Resource.Success -> {
                            mProgressDialog.mDialog.dismiss()
                            mSignUpViewModel.mSignUp.removeObserver(this)
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToNavigation())
                        }
                        is Resource.Error -> {
                            mProgressDialog.mDialog.dismiss()
                            mSignUpViewModel.mSignUp.removeObserver(this)
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }

    private fun isFormComplete(): Boolean {
        return  mBinding.tietName.validateInput(requireContext(), mBinding.tilName) &&
                mBinding.tietLastName.validateInput(requireContext(), mBinding.tilLastName) &&
                mBinding.tietEmail.validateInput(requireContext(), mBinding.tilEmail) &&
                mBinding.tietEmail.validateEmailInput(requireContext(), mBinding.tilEmail) &&
                mBinding.tietPassword.validateInput(requireContext(), mBinding.tilPassword) &&
                mBinding.tietPassword.validatePasswordLengthInput(requireContext(), mBinding.tilPassword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}