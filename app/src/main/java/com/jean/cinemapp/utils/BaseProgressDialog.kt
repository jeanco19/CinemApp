package com.jean.cinemapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.CustomProgressDialogBinding

class BaseProgressDialog {

    private lateinit var mBinding: CustomProgressDialogBinding
    lateinit var mDialog: CustomDialog

    fun show(context: Context): Dialog = show(context, null)

    fun show(context: Context, title: CharSequence?): Dialog {
        mBinding = CustomProgressDialogBinding.inflate(LayoutInflater.from(context))
        if (title != null) mBinding.tvTitle.text = title
        mBinding.cardView.setCardBackgroundColor(Color.parseColor("#70000000"))
        setColorFilter(mBinding.progressBar.indeterminateDrawable, ResourcesCompat.getColor(context.resources, R.color.color_primary, null))
        mBinding.tvTitle.setTextColor(Color.WHITE)
        mDialog = CustomDialog(context)
        mDialog.setContentView(mBinding.root)
        mDialog.show()
        return mDialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context): Dialog(context, R.style.CustomDialogTheme) {

        init {
            window?.decorView?.rootView?.setBackgroundResource(R.color.color_dialog_background)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}