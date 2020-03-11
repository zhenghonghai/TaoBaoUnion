package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.model.Api;
import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.presenter.IHomePresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitManager;
import com.example.taobaounion.view.IHomeCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

public class HomePresenterImpl implements IHomePresenter {

    @Override
    public void getCategories() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this, "result code is " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    Categories categories = response.body();
                    LogUtils.d(this, categories.toString());
                } else {
                    // 请求失败
                    LogUtils.i(this, "请求失败...");
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                LogUtils.e(this, "请求错误..." + t);
            }
        });


    }

    @Override
    public void registerCallback(IHomeCallback callback) {

    }

    @Override
    public void unregisterCallback(IHomeCallback callback) {

    }
}
