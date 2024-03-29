package com.caiosilva.turbitest.util.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.caiosilva.turbitest.R

class ProgressDialogCustom : DialogFragment() {

    companion object {
        private var progressDialogCustom: ProgressDialogCustom? = null

        val instance: ProgressDialogCustom
            @Synchronized get() {
                return progressDialogCustom ?: ProgressDialogCustom().apply {
                    progressDialogCustom = this
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        val customView = requireActivity()
            .layoutInflater
            .inflate(
                R.layout.custom_progress_dialog,
                requireActivity().contentScene?.sceneRoot)
        val colorBackground = ContextCompat.getColor(requireContext(), android.R.color.transparent)

        customView.setBackgroundColor(colorBackground)

        return AlertDialog.Builder(requireActivity())
                .setView(customView)
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        (manager.findFragmentByTag(tag) as? ProgressDialogCustom)?.let { instance ->
            if (instance.isResumed) return
        }
        super.show(manager, tag)
    }

    override fun dismiss() {
        try {
            dialog?.apply {
                if (isShowing && isResumed) {
                    super.dismiss()
                }
            }
        } catch (exception: IllegalStateException) {
            exception.printStackTrace()
        }
    }
}