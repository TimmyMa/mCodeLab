package tech.mlaboratory.mcodelab.saveinstancestate

import android.os.Bundle
import tech.mlaboratory.mcodelab.BaseActivity
import tech.mlaboratory.mcodelab.R
import java.text.SimpleDateFormat
import java.util.*

class SaveInstanceStateActivity : BaseActivity() {

    private var firstCreateTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_instance_state)

        if (savedInstanceState == null) {
            val sdf = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault())
            val date = Date(System.currentTimeMillis())
            firstCreateTime = "Activity created " + sdf.format(date)
        } else {
            firstCreateTime = savedInstanceState.getString("TimeString")
        }

        supportActionBar?.title = firstCreateTime

        var fragment:SaveInstanceStateFragment? = supportFragmentManager
                .findFragmentByTag("SaveInstanceStateFragment") as SaveInstanceStateFragment?
        if (fragment == null) {
            fragment = SaveInstanceStateFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("TimeString", firstCreateTime)
    }
}
