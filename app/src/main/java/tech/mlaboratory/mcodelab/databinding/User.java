package tech.mlaboratory.mcodelab.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import tech.mlaboratory.mcodelab.BR;

/**
 * Created by xiaoming on 2018/7/5.
 * Don't contact before modify.
 */
public class User extends BaseObservable {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
