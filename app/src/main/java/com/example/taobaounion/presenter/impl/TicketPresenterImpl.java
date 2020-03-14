package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.model.Api;
import com.example.taobaounion.model.domain.TicketParams;
import com.example.taobaounion.model.domain.TicketResult;
import com.example.taobaounion.presenter.ITicketPresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitManager;
import com.example.taobaounion.view.ITicketPagerCallback;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketPresenterImpl implements ITicketPresenter {
    @Override
    public void getTicket(String title, String url, String cover) {
        //TODO： 去获取淘口令
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        TicketParams ticketParams = new TicketParams(title, url);
        Call<TicketResult> task = api.getTicket(ticketParams);
        task.enqueue(new Callback<TicketResult>() {
            @Override
            public void onResponse(Call<TicketResult> call, Response<TicketResult> response) {
                int code = response.code();
                LogUtils.d(TicketPresenterImpl.this, "result code == >" + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    TicketResult ticketResult = response.body();
                    LogUtils.d(TicketPresenterImpl.this, "result " + ticketResult.toString());
                } else {
                    //请求失败
                    LogUtils.d(TicketPresenterImpl.this, "请求失败");
                }
            }

            @Override
            public void onFailure(Call<TicketResult> call, Throwable t) {
                LogUtils.d(TicketPresenterImpl.this, "请求失败 ===> "  + t.toString());
            }
        });
    }

    @Override
    public void registerViewCallback(ITicketPagerCallback callback) {

    }

    @Override
    public void unregisterViewCallback(ITicketPagerCallback callback) {

    }
}
