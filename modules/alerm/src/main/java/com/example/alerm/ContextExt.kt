package com.example.alerm

import android.content.Context
import android.widget.Toast

fun Context?.showToast(text: CharSequence) {
    val context = this ?: return
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}