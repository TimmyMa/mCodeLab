package tech.mlaboratory.mcodelab.view.touch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_touch.*
import tech.mlaboratory.mcodelab.R

class TouchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)
    }

    fun ignorantViewActivity(view: View) {
        LogTouchEventActivity.start(view.context, checkBox.isChecked, checkBox2.isChecked, checkBox3.isChecked)
    }
}
