package com.ss.union.game.sdk.common.mvp;

import com.ss.union.game.sdk.common.mvp.BaseView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BasePresenter<T extends BaseView> implements IPresenter<T> {
    protected T mView;

    @Override // com.ss.union.game.sdk.common.mvp.IPresenter
    public void attach(T t) {
        this.mView = t;
    }

    @Override // com.ss.union.game.sdk.common.mvp.IPresenter
    public void detach() {
        this.mView = null;
    }

    protected void showLoading() {
        if (this.mView != null) {
            this.mView.showLoading();
        }
    }

    protected void hideLoading() {
        if (this.mView != null) {
            this.mView.hideLoading();
        }
    }

    protected void toast(String str) {
        if (this.mView != null) {
            this.mView.toast(str);
        }
    }
}
