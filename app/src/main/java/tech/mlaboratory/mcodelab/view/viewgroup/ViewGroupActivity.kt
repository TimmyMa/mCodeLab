package tech.mlaboratory.mcodelab.view.viewgroup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_view_group.*
import tech.mlaboratory.mcodelab.R

class ViewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_group)
    }

    fun invalidate(view: View) {
        innerView.invalidate()
    }

    fun requestLayout(view: View) {
        innerView.requestLayout()
    }
}


fun translateMeasureSpec(measureSpec: Int): String {
    val size = View.MeasureSpec.getSize(measureSpec)
    val mode = when (View.MeasureSpec.getMode(measureSpec)) {
        View.MeasureSpec.UNSPECIFIED -> "UNSPECIFIED"
        View.MeasureSpec.EXACTLY -> "EXACTLY"
        View.MeasureSpec.AT_MOST -> "AT_MOST"
        else -> "UNKNOWN"
    }

    return "size: $size, mode: $mode"
}