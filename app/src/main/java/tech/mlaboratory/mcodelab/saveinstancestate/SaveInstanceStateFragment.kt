package tech.mlaboratory.mcodelab.saveinstancestate

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import tech.mlaboratory.mcodelab.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SaveInstanceStateFragment : Fragment() {

    private var firstCreateTime: String? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_save_instance_state, container, false)

        if (savedInstanceState == null) {
            val sdf = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault())
            val date = Date(System.currentTimeMillis())
            firstCreateTime = "Fragment created " + sdf.format(date)
        } else {
            firstCreateTime = savedInstanceState.getString("TimeString")
        }

        view.findViewById<TextView>(R.id.text).apply {
            text = firstCreateTime
        }.setOnClickListener {
            startActivity(Intent(context, ScrollingActivity::class.java))
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("TimeString", firstCreateTime)
    }
}
