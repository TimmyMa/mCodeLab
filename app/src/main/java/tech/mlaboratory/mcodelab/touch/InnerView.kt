package tech.mlaboratory.mcodelab.touch

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * Created by xiaoming on 2019-05-21.
 * Don't contact before modify.
 */
class InnerView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var comsume = false

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i(LOG_TAG, "dispatchTouchEvent   " + translateMotionEvent(event))
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(LOG_TAG, "onTouchEvent         " + translateMotionEvent(event))
        return comsume
    }

    companion object {
        val LOG_TAG = "View     :"
    }

}