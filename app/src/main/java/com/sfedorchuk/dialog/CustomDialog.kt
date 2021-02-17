package com.sfedorchuk.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import com.google.android.material.internal.ContextUtils.getActivity
import com.sfedorchuk.R
import kotlin.system.exitProcess


class CustomDialog(context: Context) : Dialog(context) {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        findViewById<Button>(R.id.yesButton).setOnClickListener {
            getActivity(context)?.finish();
            exitProcess(0);

        }

        findViewById<Button>(R.id.noButton).setOnClickListener {
            dismiss()
        }

    }
}