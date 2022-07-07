package com.example.github_mvp

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class FragmentWithInfo: Fragment() {
    fun showErrorDialog(msg: String) {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setMessage(msg)
            .setOnCancelListener { dialog ->
                dialog.dismiss()
            }.show()
    }

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}