package ru.skillbranch.devintensive

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity :
    AppCompatActivity(),
    TextView.OnEditorActionListener {

    companion object {
        const val KEY_BENDER_STATUS = "STATUS"
        const val KEY_BENDER_QUESTION = "QUESTION"
    }

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = savedInstanceState?.getString(KEY_BENDER_STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(KEY_BENDER_QUESTION) ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        tv_text.text = benderObj.askQuestion()

        et_message.setOnEditorActionListener(this)
        iv_send.setOnClickListener { answer() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(KEY_BENDER_STATUS, benderObj.status.name)
        outState?.putString(KEY_BENDER_QUESTION, benderObj.question.name)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
            actionId == EditorInfo.IME_ACTION_DONE ||
            event != null &&
            event.action == KeyEvent.ACTION_DOWN &&
            event.keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event == null || !event.isShiftPressed) {
                Log.e("TAG", "hi")
                answer()
                hideKeyboard()
                return true
            }
        }
        return false
    }

    @SuppressLint("DefaultLocale")
    fun answer() {
        val (phrase, color) = benderObj.listenAnswer(et_message.text.toString())
        et_message.setText("")
        val (r, g, b) = color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        tv_text.text = phrase
    }
}
