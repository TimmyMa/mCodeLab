package tech.mlaboratory.mcodelab

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import tech.mlaboratory.mcodelab.bitmapcompress.BitmapCompressActivity
import tech.mlaboratory.mcodelab.nativelib.NativeLibActivity
import tech.mlaboratory.mcodelab.saveinstancestate.SaveInstanceStateActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveInstanceStateActivity(view: View) {
        startActivity(Intent(view.context, SaveInstanceStateActivity::class.java))
    }

    fun bitmapCompressActivity(view: View) {
        startActivity(Intent(view.context, BitmapCompressActivity::class.java))
    }

    fun nativeLibActivity(view: View) {
        startActivity(Intent(view.context, NativeLibActivity::class.java))
    }
}
