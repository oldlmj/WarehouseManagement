package com.example.warehousemanagement.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.warehousemanagement.RoomDataBase.MyData;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<MyData>> mAllWords;

    public HomeViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<MyData>> getAllWords() {
        return mAllWords;
    }

    void insert(MyData mydata) {
        mRepository.insert(mydata);
    }
}