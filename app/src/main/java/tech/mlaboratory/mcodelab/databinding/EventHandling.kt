package tech.mlaboratory.mcodelab.databinding

import android.util.Log
import android.view.View

/**
 * Created by xiaoming on 2018/7/5.
 * Don't contact before modify.
 */
class MyHandlers {
    fun onClickFriend(view: View) {
        Log.i(this.javaClass.simpleName, view.toString())
    }
}

class Presenter {
    fun onSaveClick(user: User) {
        user.firstName = "First name changed."
        Log.i(this.javaClass.simpleName, user.toString())
    }
    fun onSaveClick(view: View, user: User) {
        user.lastName = "Last name changed."
        Log.i(this.javaClass.simpleName, view.toString() + user.toString())
    }
    fun completeChanged(user: User, checked: Boolean) {
        Log.i(this.javaClass.simpleName, user.toString() + checked.toString())
    }
}