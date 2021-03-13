package com.jean.cinemapp.presentation.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.BottomSheetReAuthenticationBinding
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.presentation.profile.viewmodel.ChangePasswordViewModel
import com.jean.cinemapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReAuthenticationBottomSheetFragment: BottomSheetDialogFragment() {

    private var _mBinding: BottomSheetReAuthenticationBinding? = null
    private val mBinding get() = _mBinding!!
    private val mChangePasswordViewModel by viewModels<ChangePasswordViewModel>()
    private var mUser: User? = null
    private val mProgressDialog = BaseProgressDialog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = BottomSheetReAuthenticationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserData()
        setupListeners()
    }

    private fun getUserData() {
        mUser = mChangePasswordViewModel.getUserData()
    }

    private fun setupListeners() {
        mBinding.btnReAuthenticate.setOnClickListener {
            validateReAuthenticationInputs()
        }
    }

    private fun validateReAuthenticationInputs() {
        if (isInputComplete() && mUser != null) {
            val email = mUser!!.email.trim()
            val password = mBinding.tietPassword.text.toString().trim()
            doReAuthentication(email, password)
        }
    }

    private fun isInputComplete(): Boolean {
        return mBinding.tietPassword.validateEmptyInput(requireContext(), mBinding.tilPassword) &&
               mBinding.tietPassword.validatePasswordLengthInput(requireContext(), mBinding.tilPassword)
    }

    private fun doReAuthentication(email: String, password: String) {
        mChangePasswordViewModel.doReAuthenticate(email, password).observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
            override fun onChanged(result: Resource<Boolean>?) {
                when (result) {
                    is Resource.Loading -> {
                        mProgressDialog.show(requireContext(), getString(R.string.progress_dialog_authenticating_message))
                    }
                    is Resource.Success -> {
                        mProgressDialog.mDialog.dismiss()
                        mChangePasswordViewModel.mReAuthenticate.removeObserver(this)
                        findNavController().navigate(ReAuthenticationBottomSheetFragmentDirections
                            .actionReAuthenticationBottomSheetFragmentToChangePassword())
                        Toast.makeText(requireContext(), getString(R.string.re_authentication_successfully_message), Toast.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        mProgressDialog.mDialog.dismiss()
                        mChangePasswordViewModel.mReAuthenticate.removeObserver(this)
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