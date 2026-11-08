package com.ss.union.game.sdk.common.mvp;

import com.ss.union.game.sdk.common.mvp.BaseView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IPresenter<T extends BaseView> {
    void attach(T t);

    void detach();
}
