package tech.mlaboratory.mcodelab

import android.content.Intent
import android.os.Bundle
import android.view.View
import tech.mlaboratory.mcodelab.bitmapcompress.BitmapCompressActivity
import tech.mlaboratory.mcodelab.databinding.DataBindingActivity
import tech.mlaboratory.mcodelab.ipc.IPCActivity
import tech.mlaboratory.mcodelab.nativelib.NativeLibActivity
import tech.mlaboratory.mcodelab.opengl.OpenGLES20Activity
import tech.mlaboratory.mcodelab.saveinstancestate.SaveInstanceStateActivity
import tech.mlaboratory.mcodelab.statusbar.StatusBarActivity
import tech.mlaboratory.mcodelab.touch.TouchActivity

class MainActivity : BaseActivity() {

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

    fun openGLActivity(view: View) {
        startActivity(Intent(view.context, OpenGLES20Activity::class.java))
    }

    fun statusBarActivity(view: View) {
        startActivity(Intent(view.context, StatusBarActivity::class.java))
    }

    fun dataBindingActivity(view: View) {
        startActivity(Intent(view.context, DataBindingActivity::class.java))
    }

    fun ipcActivity(view: View) {
        startActivity(Intent(view.context, IPCActivity::class.java))
    }

    fun touchActivity(view: View) {
        startActivity(Intent(view.context, TouchActivity::class.java))
    }
}
