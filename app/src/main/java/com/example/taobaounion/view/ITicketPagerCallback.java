package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallback;
import com.example.taobaounion.model.domain.TicketResult;

public interface ITicketPagerCallback extends IBaseCallback {

    /**
     * 淘宝令加载结果
     *
     * @param cover
     * @param result
     */
    void onTicketLoaded(String cover, TicketResult result);
}
