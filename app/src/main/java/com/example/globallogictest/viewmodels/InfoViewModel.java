package com.example.globallogictest.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.globallogictest.data.Info;
import com.example.globallogictest.data.InfoRepository;

import java.util.List;

public class InfoViewModel extends AndroidViewModel {

    private InfoRepository mRepository;

    private LiveData<List<Info>> mAllInfo;

    public InfoViewModel (Application application) {
        super(application);
        mRepository = new InfoRepository(application);
        mAllInfo = mRepository.getAllInfo();
    }


    public LiveData<List<Info>> getAllInfo() { return mAllInfo; }

    public void insert(Info info) { mRepository.insert(info); }

    public void deleteInfo(){mRepository.delete();}
}
