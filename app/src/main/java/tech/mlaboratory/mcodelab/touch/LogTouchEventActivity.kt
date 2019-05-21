package tech.mlaboratory.mcodelab.touch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_ignorant_view.*
import tech.mlaboratory.mcodelab.R

class LogTouchEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ignorant_view)

        viewGroup.intercept = intent.getBooleanExtra("viewGroupIntercept", false)
        viewGroup.comsume = intent.getBooleanExtra("viewGroupConsume", false)
        view.comsume = intent.getBooleanExtra("viewConsume", false)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(LOG_TAG, "dispatchTouchEvent   " + translateMotionEvent(ev))
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(LOG_TAG, "onTouchEvent         " + translateMotionEvent(event))
        return super.onTouchEvent(event)
    }

    companion object {
        val LOG_TAG = "Activity :"

        fun start(context: Context, viewGroupIntercept: Boolean, viewGroupConsume: Boolean, viewConsume: Boolean) {
            context.startActivity(Intent(context, LogTouchEventActivity::class.java).apply {
                putExtra("viewGroupIntercept", viewGroupIntercept)
                putExtra("viewGroupConsume", viewGroupConsume)
                putExtra("viewConsume", viewConsume)
            })
        }
    }
}


fun translateMotionEvent(ev: MotionEvent?): String {
    if (ev == null) {
        return "NULL"
    }

    val actionName = when(ev.actionMasked) {
        MotionEvent.ACTION_DOWN -> "DOWN        "
        MotionEvent.ACTION_POINTER_DOWN -> "POINTER_DOWN"
        MotionEvent.ACTION_MOVE -> "MOVE        "
        MotionEvent.ACTION_UP -> "UP          "
        MotionEvent.ACTION_POINTER_UP -> "POINTER_UP  "
        MotionEvent.ACTION_CANCEL -> "CANCEL      "
        else -> "UNKNOWN     "
    }

    val pointerInfoStringBuffer = StringBuffer()
    pointerInfoStringBuffer.append("pointer count: ${ev.pointerCount}, ")
    for(pointerIndex in 0 until ev.pointerCount) {
        pointerInfoStringBuffer.append("pointer $pointerIndex at ${ev.getX(pointerIndex)}, ${ev.getY(pointerIndex)}, ")
    }

    return " Action: $actionName, ${pointerInfoStringBuffer.dropLast(2)}"
}