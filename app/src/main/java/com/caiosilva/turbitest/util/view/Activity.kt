package com.caiosilva.turbitest.util.view

import android.app.Activity
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun Activity.getContentView(): ViewGroup = findViewById(android.R.id.content)

fun Activity.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(getContentView(), message, duration).show()
}

fun Activity.showSnackbar(@StringRes messageRes: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    showSnackbar(getString(messageRes), duration)
}

fun AppCompatActivity.showProgress() {
    ProgressDialogCustom.instance.show(this.supportFragmentManager, this.javaClass.name)
}

fun AppCompatActivity.hideProgress() = ProgressDialogCustom.instance.dismiss()


fun Activity.getMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()