package tech.mlaboratory.mcodelab.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tech.mlaboratory.mcodelab.R;
import tech.mlaboratory.mcodelab.databinding.ActivityIpcBinding;

public class IPCActivity extends AppCompatActivity {

    private static final String TAG = "IPCActivity";
    private IBookManager mManager;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mManager = IBookManager.Stub.asInterface(service);
            mBinding.textViewLog.append("Remote service connected\n");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindService(new Intent(IPCActivity.this, BookManagerService.class), mConnection, BIND_AUTO_CREATE);
        }
    };
    private ActivityIpcBinding mBinding;
    private List<IOnNewBookArrivedListener> mListenerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc);

        bindService(new Intent(this, BookManagerService.class), mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    public void add(View view) {
        try {
            Book book = new Book(0, "Thinking in Java");
            mManager.addBook(book);
            mBinding.textViewLog.append("Add book " + book.toString() + "\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void query(View view) {
        try {
            List<Book> list = mManager.getBookList();
            mBinding.textViewLog.append("Query book " + list.toString() + "\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addListener(View view) {
        IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
            @Override
            public void onNewBookArrived(Book book) throws RemoteException {
                mBinding.textViewLog.append("New book arrived " + book.toString() + "\n");
            }
        };
        try {
            mManager.registerListener(listener);
            mListenerList.add(listener);
            mBinding.textViewLog.append("Add listener " + listener.toString() + "\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void removeListener(View view) {
        if (mListenerList.size() > 0) {
            IOnNewBookArrivedListener listener = mListenerList.get(0);
            try {
                mManager.unregisterListener(listener);
                mListenerList.remove(listener);
                mBinding.textViewLog.append("Remove listener " + listener.toString() + "\n");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
