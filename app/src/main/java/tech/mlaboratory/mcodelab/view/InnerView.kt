package tech.mlaboratory.mcodelab.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import tech.mlaboratory.mcodelab.view.touch.translateMotionEvent
import tech.mlaboratory.mcodelab.view.viewgroup.translateMeasureSpec

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
        Log.i(TOUCH_LOG_TAG, "dispatchTouchEvent   " + translateMotionEvent(event))
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TOUCH_LOG_TAG, "onTouchEvent         " + translateMotionEvent(event))
        return comsume
    }

    companion object {
        val TOUCH_LOG_TAG = "View     :"
        const val VIEW_GROUP_LOG_TAG = "InnerView     :"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.i(VIEW_GROUP_LOG_TAG, "onMeasure: width[${translateMeasureSpec(widthMeasureSpec)}], height[${translateMeasureSpec(heightMeasureSpec)}]")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.i(VIEW_GROUP_LOG_TAG, "layout : $l, $t, $r, $b")
        super.layout(l, t, r, b)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.i(VIEW_GROUP_LOG_TAG, "onLayout : $left, $top, $right, $bottom")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        Log.i(VIEW_GROUP_LOG_TAG, "dispatchDraw")
        super.dispatchDraw(canvas)
    }

    override fun draw(canvas: Canvas?) {
        Log.i(VIEW_GROUP_LOG_TAG, "draw")
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.i(VIEW_GROUP_LOG_TAG, "onDraw")
        super.onDraw(canvas)
    }
}