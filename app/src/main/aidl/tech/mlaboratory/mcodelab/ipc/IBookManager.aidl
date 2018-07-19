// IBiikManager.aidl
package tech.mlaboratory.mcodelab.ipc;

import tech.mlaboratory.mcodelab.ipc.Book;
import tech.mlaboratory.mcodelab.ipc.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
