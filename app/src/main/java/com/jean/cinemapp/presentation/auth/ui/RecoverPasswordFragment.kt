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

import com.jean.cinemapp.databinding.FragmentRecoverPasswordBinding
import com.jean.cinemapp.presentation.auth.viewmodel.RecoverPasswordViewModel
import com.jean.cinemapp.utils.BaseProgressDialog
import com.jean.cinemapp.utils.Resource
import com.jean.cinemapp.utils.validateEmailInput
import com.jean.cinemapp.utils.validateInput
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
        if (isFormComplete()) {
            val email = mBinding.tietEmail.text.toString().trim()
            mRecoverPasswordViewModel.doRecoverPassword(email).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
                override fun onChanged(result: Resource<Boolean>) {
                    when (result) {
                        is Resource.Loading -> {
                            mProgressDialog.show(requireContext(), getString(R.string.dialog_sending_email_message))
                        }
                        is Resource.Success -> {
                            mProgressDialog.mDialog.dismiss()
                            mRecoverPasswordViewModel.mRecoverPassword.removeObserver(this)
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                            findNavController().navigate(RecoverPasswordFragmentDirections.actionRecoverPasswordFragmentToSignInFragment())
                        }
                        is Resource.Error -> {
                            mProgressDialog.mDialog.dismiss()
                            mRecoverPasswordViewModel.mRecoverPassword.removeObserver(this)
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }

    private fun isFormComplete(): Boolean {
        return mBinding.tietEmail.validateInput(requireContext(), mBinding.tilEmail) &&
               mBinding.tietEmail.validateEmailInput(requireContext(), mBinding.tilEmail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}