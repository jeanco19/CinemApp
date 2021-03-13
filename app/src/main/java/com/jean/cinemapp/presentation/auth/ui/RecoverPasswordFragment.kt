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
import com.google.android.material.snackbar.Snackbar
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentRecoverPasswordBinding
import com.jean.cinemapp.presentation.auth.viewmodel.RecoverPasswordViewModel
import com.jean.cinemapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverPasswordFragment: Fragment() {

    private var _mBinding: FragmentRecoverPasswordBinding? = null
    private val mBinding get() = _mBinding!!
    private val mRecoverPasswordViewModel by viewModels<RecoverPasswordViewModel>()
    private val mProgressDialog = BaseProgressDialog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentRecoverPasswordBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        mBinding.ivArrowBack.setOnClickListener {
            findNavController().navigate(RecoverPasswordFragmentDirections.actionRecoverPasswordFragmentToSignInFragment())
        }
        mBinding.btnSend.setOnClickListener {
            validateRecoverInput()
        }
    }

    private fun validateRecoverInput() {
        if (isInputComplete()) {
            val email = mBinding.tietEmail.text.toString().trim()
            doRecoverPassword(email)
        }
    }

    private fun isInputComplete(): Boolean {
        return mBinding.tietEmail.validateEmptyInput(requireContext(), mBinding.tilEmail) &&
                mBinding.tietEmail.validateEmailInput(requireContext(), mBinding.tilEmail)
    }

    private fun doRecoverPassword(email: String) {
        mRecoverPasswordViewModel.doRecoverPassword(email).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
            override fun onChanged(result: Resource<Boolean>) {
                when (result) {
                    is Resource.Loading -> {
                        mProgressDialog.show(requireContext(), getString(R.string.progress_dialog_sending_email_message))
                    }
                    is Resource.Success -> {
                        mProgressDialog.mDialog.dismiss()
                        mRecoverPasswordViewModel.mRecoverPassword.removeObserver(this)
                        Snackbar.make(mBinding.root, result.message!!, Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(RecoverPasswordFragmentDirections.actionRecoverPasswordFragmentToSignInFragment())
                    }
                    is Resource.Error -> {
                        mProgressDialog.mDialog.dismiss()
                        mRecoverPasswordViewModel.mRecoverPassword.removeObserver(this)
                        manageErrors(result)
                    }
                }
            }
        })
    }

    private fun manageErrors(result: Resource.Error<Boolean>) {
        when (result.errorType) {
            ErrorTypes.FAILED_RESPONSE -> {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
            }
            ErrorTypes.WITHOUT_CONNECTION -> {
                Snackbar.make(mBinding.root, getString(R.string.error_internet_connection), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}