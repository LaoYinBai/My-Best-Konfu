package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.common.mvp.BaseView;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.core.base.account.model.User;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface e {

    public interface a<T extends c> extends IPresenter<T> {
        void a(String str, String str2);

        void a(String str, String str2, String str3);
    }

    public interface b extends a<c> {
    }

    public interface c extends BaseView {
        void a(int i, String str);

        void a(User user);

        void a(String str, String str2, int i);

        void b(int i, String str);
    }
}
