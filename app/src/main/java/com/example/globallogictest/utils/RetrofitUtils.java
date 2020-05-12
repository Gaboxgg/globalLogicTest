package com.example.globallogictest.utils;

import androidx.annotation.NonNull;
import android.util.Log;


import com.example.globallogictest.R;
import com.example.globallogictest.data.Info;
import com.example.globallogictest.interfaces.Constants;
import com.example.globallogictest.interfaces.getInfoCallback;
import com.example.globallogictest.interfaces.globalLogicClient;
import com.example.globallogictest.utils.GeneralUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static com.example.globallogictest.interfaces.globalLogicClient globalLogicClient;

    private static globalLogicClient getRetrofitCsInstance(){
        if(globalLogicClient == null){
            globalLogicClient = new Retrofit.Builder()
                    .baseUrl(Constants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(globalLogicClient.class);
        }

        return globalLogicClient;
    }

    public static ResponseBody getInfo(final getInfoCallback callback){
        Call<List<Info>> call = getRetrofitCsInstance().getInfo();

        call.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(@NonNull Call<List<Info>> call, @NonNull Response<List<Info>> response) {
                if(callback != null)
                    callback.onGetFinish(response.isSuccessful() ? response.body() : null);
            }

            @Override
            public void onFailure(@NonNull Call<List<Info>> call, @NonNull Throwable t) {
                Log.e("error", t.toString());
                GeneralUtils.showToast(R.string.api_bad_response);
                if(callback != null)
                    callback.onGetFinish(null);
            }
        });
        return null;
    }
}
