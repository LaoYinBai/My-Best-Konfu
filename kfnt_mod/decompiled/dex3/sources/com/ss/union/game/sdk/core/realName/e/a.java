package com.ss.union.game.sdk.core.realName.e;

import com.ss.union.game.sdk.common.mvp.BaseView;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface a {

    /* renamed from: com.ss.union.game.sdk.core.realName.e.a$a, reason: collision with other inner class name */
    public interface InterfaceC0088a extends IPresenter<b> {
        void a(String str, String str2);

        void b(String str, String str2);
    }

    public interface b extends BaseView {
        void onAccountRealNameFailure(int i, String str);

        void onAccountRealNameSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo);

        void onDeviceRealNameFailure(int i, String str);

        void onDeviceRealNameSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo);
    }
}
