package tech.mlaboratory.mcodelab.databinding

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import tech.mlaboratory.mcodelab.BR
import tech.mlaboratory.mcodelab.R

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        val binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)
        val user = User("Test", "User")
        binding.user = user
        binding.handlers = MyHandlers()
        binding.presenter = Presenter()


    }
}
