package com.example.taobaounion.utils;

import com.example.taobaounion.presenter.ICategoryPagerPresenter;
import com.example.taobaounion.presenter.IHomePresenter;
import com.example.taobaounion.presenter.ITicketPresenter;
import com.example.taobaounion.presenter.impl.CategoryPagerPresenterImpl;
import com.example.taobaounion.presenter.impl.HomePresenterImpl;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;

public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final ICategoryPagerPresenter mCategoryPagerPresenter;
    private final IHomePresenter mHomePresenter;
    private final ITicketPresenter mTicketPresenter;

    public static PresenterManager getInstance() {
        return ourInstance;
    }

    private PresenterManager() {

        mCategoryPagerPresenter = new CategoryPagerPresenterImpl();
        mHomePresenter = new HomePresenterImpl();
        mTicketPresenter = new TicketPresenterImpl();

    }

    public ICategoryPagerPresenter getmCategoryPagerPresenter() {
        return mCategoryPagerPresenter;
    }

    public IHomePresenter getmHomePresenter() {
        return mHomePresenter;
    }

    public ITicketPresenter getmTicketPresenter() {
        return mTicketPresenter;
    }
}
