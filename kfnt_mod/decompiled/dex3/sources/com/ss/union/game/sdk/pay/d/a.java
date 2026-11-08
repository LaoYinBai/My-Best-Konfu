package com.ss.union.game.sdk.pay.d;

import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.d.b;
import com.ss.union.game.sdk.pay.d.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends BasePresenter<b.InterfaceC0099b> implements c.a {
    @Override // com.ss.union.game.sdk.pay.d.c.a
    public void a(String str, String str2) {
        showLoading();
        com.ss.union.game.sdk.pay.c.b.a(str, str2, new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.d.a.1
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i, String str3) {
                a.this.hideLoading();
                if (a.this.mView != null) {
                    ((b.InterfaceC0099b) a.this.mView).a(i, str3);
                }
            }
        });
    }
}
