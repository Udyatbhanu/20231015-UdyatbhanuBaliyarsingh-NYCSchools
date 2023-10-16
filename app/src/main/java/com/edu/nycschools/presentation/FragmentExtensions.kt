package com.edu.nycschools.presentation

import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.edu.nycschools.R

fun Fragment.showError(view: View) {
    val dialog = context?.let { Dialog(it) }
    dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE) // before

    dialog?.setContentView(R.layout.error_dialog)
    dialog?.setCancelable(false)

    val lp = WindowManager.LayoutParams()
    lp.copyFrom(dialog?.window?.attributes)
    lp.width = WindowManager.LayoutParams.WRAP_CONTENT
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT

    (dialog?.findViewById<View>(R.id.bt_close) as AppCompatButton).setOnClickListener {
        dialog.dismiss()
        Navigation.findNavController(view).popBackStack()
    }
    dialog.show()
    dialog.window?.attributes = lp
}
