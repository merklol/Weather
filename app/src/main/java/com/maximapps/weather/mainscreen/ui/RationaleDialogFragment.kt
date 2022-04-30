package com.maximapps.weather.mainscreen.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.maximapps.weather.R

class RationaleDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.rationale_dialog_title)
            .setMessage(R.string.rationale_dialog_message)
            .setPositiveButton(R.string.rationale_dialog_positive_btn_text) { _, _ -> }
            .create()
}
