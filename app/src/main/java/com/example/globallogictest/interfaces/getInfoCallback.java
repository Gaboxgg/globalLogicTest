package com.example.globallogictest.interfaces;

import androidx.annotation.Nullable;

import com.example.globallogictest.data.Info;

import java.util.List;

public interface getInfoCallback {
    void onGetFinish(@Nullable List<Info> response);
}
