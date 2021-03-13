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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentProfileBinding
import com.jean.cinemapp.presentation.profile.adapter.OptionAdapter
import com.jean.cinemapp.presentation.profile.viewmodel.ProfileViewModel
import com.jean.cinemapp.utils.*
import com.jean.cinemapp.utils.Constants.OPTION_CHANGE_LANGUAGE
import com.jean.cinemapp.utils.Constants.OPTION_CHANGE_PASSWORD
import com.jean.cinemapp.utils.Constants.OPTION_EDIT_PROFILE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment(), OptionAdapter.Interaction {

    private var _mBinding: FragmentProfileBinding? = null
    private val mBinding get() = _mBinding!!
    private val mProfileViewModel by viewModels<ProfileViewModel>()
    private val mProgressDialog = BaseProgressDialog()
    private lateinit var mOptionAdapter: OptionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupGeneralOptions()
        setUserData()
    }

    private fun setupListeners() {
        mBinding.tvSignOut.setOnClickListener {
            showSignOutDialog()
        }
        mBinding.swDarkMode.setOnCheckedChangeListener { buttonView, isChecked ->
            // TODO activar o desactivar el modo oscuro según el estado del Switch
        }
    }

    private fun showSignOutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_sign_out_title))
            .setMessage(getString(R.string.dialog_sign_out_message))
            .setPositiveButton(getString(R.string.dialog_close_button)) { dialog, which -> doSignOut() }
            .setNegativeButton(getString(R.string.dialog_cancel_button), null)
            .show()
    }

    private fun doSignOut() {
        mProfileViewModel.doSignOut().observe(viewLifecycleOwner, object: Observer<Resource<Boolean>> {
            override fun onChanged(result: Resource<Boolean>?) {
                when (result) {
                    is Resource.Loading -> {
                        mProgressDialog.show(requireContext(), getString(R.string.progress_dialog_sign_out_message))
                    }
                    is Resource.Success -> {
                        mProgressDialog.mDialog.dismiss()
                        mProfileViewModel.mSignOut.removeObserver(this)
                        findNavController().navigate(R.id.action_main_to_front_fragment)
                    }
                    is Resource.Error -> {
                        mProgressDialog.mDialog.dismiss()
                        mProfileViewModel.mSignOut.removeObserver(this)
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setupGeneralOptions() {
        mOptionAdapter = OptionAdapter(Helper.getGeneralSettingOptions(requireContext()), this)
        mBinding.rvGeneralOptions.setupVerticalRecycler(requireContext(), mOptionAdapter, true)
    }

    override fun onItemSelected(option: String) {
        when (option) {
            OPTION_EDIT_PROFILE -> {
                // TODO implementar navegación a Fragment de editar perfil
            }
            OPTION_CHANGE_PASSWORD -> {
                findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToChangePassword())
            }
            OPTION_CHANGE_LANGUAGE -> {
                // TODO implementar navegación a Fragment de cambiar idioma
            }
        }
    }

    private fun setUserData() {
        val user = mProfileViewModel.getUserData()
        user?.let { data ->
            mBinding.tvName.text = data.name
            mBinding.tvEmail.text = data.email
            mBinding.ivAvatar.loadFirebaseImage(data.avatar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}