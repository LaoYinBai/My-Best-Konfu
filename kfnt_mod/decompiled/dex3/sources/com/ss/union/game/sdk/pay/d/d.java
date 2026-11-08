package com.ss.union.game.sdk.pay.d;

import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.d.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends BasePresenter<b.InterfaceC0099b> implements b.a {
    @Override // com.ss.union.game.sdk.pay.d.b.a
    public void a(String str) {
        showLoading();
        com.ss.union.game.sdk.pay.c.a.b(str, new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.d.d.1
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i, String str2) {
                d.this.hideLoading();
                if (d.this.mView != null) {
                    ((b.InterfaceC0099b) d.this.mView).a(i, str2);
                }
            }
        });
    }
}
