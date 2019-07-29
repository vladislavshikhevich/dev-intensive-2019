package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    val view = currentFocus ?: View(this)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    return !this.isKeyboardOpen()
}

fun Activity.isKeyboardClosed(): Boolean {
    val rootView = this.findViewById<View>(android.R.id.content)
    val visibleBounds = Rect()

    rootView.getWindowVisibleDisplayFrame(visibleBounds)

    return rootView.height > visibleBounds.height()
}