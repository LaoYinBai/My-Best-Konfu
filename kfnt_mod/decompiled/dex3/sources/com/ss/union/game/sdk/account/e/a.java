package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.common.mvp.BaseView;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.core.base.account.model.User;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface a {

    /* renamed from: com.ss.union.game.sdk.account.e.a$a, reason: collision with other inner class name */
    public interface InterfaceC0049a<T extends c> extends IPresenter<T> {
        void a();
    }

    public interface b extends InterfaceC0049a<c> {
    }

    public interface c extends BaseView {
        void b(User user);

        void c(int i, String str);

        void f();
    }
}
