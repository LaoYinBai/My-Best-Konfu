package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.common.mvp.BaseView;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface g {

    public interface a extends b<c> {
    }

    public interface b<T extends c> extends IPresenter<T> {
        void b();
    }

    public interface c extends BaseView {
        void a(int i, String str, LGAccountConstant.LoginType loginType);

        void a(User user, LGAccountConstant.LoginType loginType);
    }
}
