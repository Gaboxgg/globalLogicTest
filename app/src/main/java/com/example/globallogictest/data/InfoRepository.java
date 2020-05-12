package com.example.globallogictest.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.globallogictest.data.Info;
import com.example.globallogictest.data.InfoDatabase;
import com.example.globallogictest.interfaces.InfoDao;

import java.util.List;

public class InfoRepository {

    private InfoDao mInfoDao;
    private LiveData<List<Info>> mAllInfo;

    public InfoRepository(Application application) {
        InfoDatabase db = InfoDatabase.getDatabase(application);
        mInfoDao = db.infoDao();
        mAllInfo = mInfoDao.getAllInfo();
    }

    public LiveData<List<Info>> getAllInfo() {
        return mAllInfo;
    }

    public void insert (Info info) {
        new insertAsyncTask(mInfoDao).execute(info);
    }

    public void delete () {
        new deleteAsyncTask(mInfoDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Info, Void, Void> {

        private InfoDao mAsyncTaskDao;

        insertAsyncTask(InfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Info... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Info, Void, Void> {

        private InfoDao mAsyncTaskDao;

        deleteAsyncTask(InfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Info... params) {
            mAsyncTaskDao.delete();
            return null;
        }
    }
}