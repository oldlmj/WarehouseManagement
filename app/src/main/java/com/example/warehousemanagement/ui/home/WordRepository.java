package com.example.warehousemanagement.ui.home;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.warehousemanagement.RoomDataBase.DataBase;
import com.example.warehousemanagement.RoomDataBase.DataUao;
import com.example.warehousemanagement.RoomDataBase.MyData;

import java.util.List;

class WordRepository {

    private DataUao mWordDao;
    private LiveData<List<MyData>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        DataBase db = DataBase.getDatabase(application);
        mWordDao = db.getDataUao();
        mAllWords = mWordDao.displayAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<MyData>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(MyData mydata) {
        DataBase.databaseWriteExecutor.execute(() -> {
            mWordDao.insertData(mydata);
        });
    }
}

