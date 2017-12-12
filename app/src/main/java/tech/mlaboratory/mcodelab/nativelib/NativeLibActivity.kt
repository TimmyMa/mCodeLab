package tech.mlaboratory.mcodelab.nativelib

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.ziroom.commonlibrary.util.SignKeyUtil
import tech.mlaboratory.mcodelab.R

class NativeLibActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)

        findViewById<TextView>(R.id.text).apply {
            append("Key :")
            append((SignKeyUtil.getKey() ?: "") + "\n")

            append("HouseKey :")
            append((SignKeyUtil.getHouseKey() ?: "") + "\n")

            append("SignKey :")
            append(SignKeyUtil.getSignKey() ?: "")
        }
    }
}
