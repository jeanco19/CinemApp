package com.jean.cinemapp.utils

import android.content.Context
import android.net.Uri
import android.util.Patterns
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jean.cinemapp.R
import com.jean.cinemapp.utils.Constants.IMAGE_BASE_URL

fun <T: RecyclerView.ViewHolder> RecyclerView.setupVerticalRecycler(context: Context, adapterClass: RecyclerView.Adapter<T>, divider: Boolean): RecyclerView {
    return this.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        if (divider) addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = adapterClass
    }
}

fun <T: RecyclerView.ViewHolder> RecyclerView.setupHorizontalRecycler(context: Context, adapterClass: RecyclerView.Adapter<T>): RecyclerView {
    return this.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = adapterClass
    }
}

fun ImageView.loadMovieImage(image: String?) {
    this.load(IMAGE_BASE_URL + image) {
        crossfade(true)
        placeholder(R.drawable.placeholder_movie)
        error(R.drawable.placeholder_movie)
        fallback(R.drawable.placeholder_movie)
    }
}

fun ImageView.loadCastImage(image: String?) {
    this.load(IMAGE_BASE_URL + image) {
        crossfade(true)
        placeholder(R.drawable.placeholder_person)
        error(R.drawable.placeholder_person)
        fallback(R.drawable.placeholder_person)
    }
}

fun ImageView.loadFirebaseImage(image: Uri?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.placeholder_person)
        error(R.drawable.placeholder_person)
        fallback(R.drawable.placeholder_person)
    }
}

fun TextInputEditText.validateInput(context: Context, textInputLayout: TextInputLayout): Boolean {
    if (this.text.toString().isEmpty()) {
        textInputLayout.error = context.getString(R.string.error_must_complete_this_field)
        textInputLayout.isFocusable = true
        return false
    } else {
        textInputLayout.error = null
    }
    return true
}

fun TextInputEditText.validateEmailInput(context: Context, textInputLayout: TextInputLayout): Boolean {
    if (!Patterns.EMAIL_ADDRESS.matcher(this.text.toString().trim()).matches()) {
        textInputLayout.error = context.getString(R.string.error_invalid_email)
        textInputLayout.isFocusable = true
        return false
    } else {
        textInputLayout.error = null
    }
    return true
}

fun TextInputEditText.validatePasswordLengthInput(context: Context, textInputLayout: TextInputLayout): Boolean {
    if (this.text.toString().length < 8) {
        textInputLayout.error = context.getString(R.string.error_weak_password)
        textInputLayout.isFocusable = true
        return false
    } else {
        textInputLayout.error = null
    }
    return true
}