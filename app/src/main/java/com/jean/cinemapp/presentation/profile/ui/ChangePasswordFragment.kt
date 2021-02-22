package com.jean.cinemapp.presentation.profile.ui

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
import com.jean.cinemapp.databinding.FragmentChangePasswordBinding
import com.jean.cinemapp.presentation.profile.viewmodel.ChangePasswordViewModel
import com.jean.cinemapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment: Fragment() {

    private var _mBinding: FragmentChangePasswordBinding? = null
    private val mBinding get() = _mBinding!!
    private val mChangePasswordViewModel by viewModels<ChangePasswordViewModel>()
    private val mProgressDialog = BaseProgressDialog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        mBinding.ivArrowBack.setOnClickListener {
            findNavController().navigate(ChangePasswordFragmentDirections.actionChangePasswordToNavigationProfile())
        }
        mBinding.btnChangePassword.setOnClickListener {
            validatePasswordInputs()
        }
    }

    private fun validatePasswordInputs() {
        if (isInputsComplete()) {
            val newPassword = mBinding.tietConfirmPassword.text.toString().trim()
            doChangePassword(newPassword)
        }
    }

    private fun isInputsComplete(): Boolean {
        return mBinding.tietNewPassword.validateEmptyInput(requireContext(), mBinding.tilNewPassword) &&
               mBinding.tietNewPassword.validatePasswordLengthInput(requireContext(), mBinding.tilNewPassword) &&
               mBinding.tietConfirmPassword.validateEmptyInput(requireContext(), mBinding.tilConfirmPassword) &&
               mBinding.tietConfirmPassword.validateMatchPasswordInputs(requireContext(), mBinding.tilConfirmPassword, mBinding.tietNewPassword.text.toString())
    }

    private fun doChangePassword(newPassword: String) {
        mChangePasswordViewModel.doChangePassword(newPassword).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
            override fun onChanged(result: Resource<Boolean>?) {
                when (result) {
                    is Resource.Loading -> {
                        mProgressDialog.show(requireContext(), getString(R.string.dialog_changing_password))
                    }
                    is Resource.Success -> {
                        mProgressDialog.mDialog.dismiss()
                        mChangePasswordViewModel.mChangePassword.removeObserver(this)
                        clearInputs()
                        Snackbar.make(mBinding.root, result.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        // TODO implementar dialogo para re-autenticacion cuando el usuario realiza esta operacion tiempo despues de hacer un login
                        mProgressDialog.mDialog.dismiss()
                        mChangePasswordViewModel.mChangePassword.removeObserver(this)
                        manageErrors(result)
                    }
                }
            }
        })
    }

    private fun clearInputs() {
        mBinding.tietNewPassword.text?.clear()
        mBinding.tietConfirmPassword.text?.clear()
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