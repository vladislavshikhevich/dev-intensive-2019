package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R
import kotlin.math.roundToLong

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    val view = currentFocus ?: View(this)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

//fun Activity.isKeyboardOpen(): Boolean {
//    return !this.isKeyboardOpen()
//}
//
//fun Activity.isKeyboardClosed(): Boolean {
//    val rootView = this.findViewById<View>(android.R.id.content)
//    val visibleBounds = Rect()
//
//    rootView.getWindowVisibleDisplayFrame(visibleBounds)
//
//    return rootView.height > visibleBounds.height()
//}

fun Activity.convertDpToPx(dp: Float): Long {
    val r = this.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics).roundToLong()
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()
    val rootView = findViewById<View>(android.R.id.content)
    rootView.getWindowVisibleDisplayFrame(r)
    val heightDiff = rootView.height - r.height()
    val marginOfError = this.convertDpToPx(50F)

    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}