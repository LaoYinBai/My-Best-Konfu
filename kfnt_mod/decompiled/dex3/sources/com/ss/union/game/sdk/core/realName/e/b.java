package com.ss.union.game.sdk.core.realName.e;

import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.core.realName.b.a;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.e.a;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends BasePresenter<a.b> implements a.InterfaceC0088a {
    @Override // com.ss.union.game.sdk.core.realName.e.a.InterfaceC0088a
    public void a(String str, String str2) {
        showLoading();
        com.ss.union.game.sdk.core.realName.d.a.a(str, str2, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.core.realName.e.b.1
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                a.C0087a.b(z2);
                a.C0087a.a(z);
                if (b.this.mView != null) {
                    b.this.hideLoading();
                    ((a.b) b.this.mView).onDeviceRealNameSuccess(z, z2, lGRealNameRewardInfo);
                }
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i, String str3) {
                if (b.this.mView != null) {
                    b.this.hideLoading();
                    ((a.b) b.this.mView).onDeviceRealNameFailure(i, str3);
                }
            }
        });
    }

    @Override // com.ss.union.game.sdk.core.realName.e.a.InterfaceC0088a
    public void b(String str, String str2) {
        showLoading();
        com.ss.union.game.sdk.core.realName.d.a.b(str, str2, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.core.realName.e.b.2
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                if (b.this.mView != null) {
                    b.this.hideLoading();
                    ((a.b) b.this.mView).onAccountRealNameSuccess(z, z2, lGRealNameRewardInfo);
                }
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i, String str3) {
                if (b.this.mView != null) {
                    b.this.hideLoading();
                    ((a.b) b.this.mView).onAccountRealNameFailure(i, str3);
                }
            }
        });
    }
}
