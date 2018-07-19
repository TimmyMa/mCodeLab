// IOnNewBookArrivedListener.aidl
package tech.mlaboratory.mcodelab.ipc;

import tech.mlaboratory.mcodelab.ipc.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
