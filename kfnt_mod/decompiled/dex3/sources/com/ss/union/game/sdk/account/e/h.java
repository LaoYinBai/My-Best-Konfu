package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.account.e.g;
import com.ss.union.game.sdk.account.third.a;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h extends BasePresenter<g.c> implements g.a {
    private int a;

    public h(int i) {
        this.a = i;
    }

    @Override // com.ss.union.game.sdk.account.e.g.b
    public void b() {
        showLoading();
        com.ss.union.game.sdk.account.third.a.a().a(ActivityUtils.getTopActivity(), new a.InterfaceC0052a() { // from class: com.ss.union.game.sdk.account.e.h.1
            @Override // com.ss.union.game.sdk.account.third.a.InterfaceC0052a
            public void a(String str) {
                com.ss.union.game.sdk.c.a.a("OauthManager.init().dyOauthLogin success ");
                com.ss.union.game.sdk.account.d.b.a(str, h.this.a, new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.e.h.1.1
                    @Override // com.ss.union.game.sdk.account.d.c
                    public void a(int i, String str2) {
                        com.ss.union.game.sdk.c.a.a("LGAccountNetManager.tikTokLogin fail code " + i + " msg " + str2);
                        h.this.hideLoading();
                        if (h.this.mView != null) {
                            ((g.c) h.this.mView).a(i, str2, LGAccountConstant.LoginType.LOGIN_TYPE_DY);
                        }
                    }

                    @Override // com.ss.union.game.sdk.account.d.c
                    public void a(User user) {
                        com.ss.union.game.sdk.c.a.a("LGAccountNetManager.tikTokLogin success ");
                        h.this.hideLoading();
                        if (h.this.mView != null) {
                            ((g.c) h.this.mView).a(user, LGAccountConstant.LoginType.LOGIN_TYPE_DY);
                        }
                    }
                });
            }

            @Override // com.ss.union.game.sdk.account.third.a.InterfaceC0052a
            public void a(int i, String str) {
                com.ss.union.game.sdk.c.a.a("OauthManager.init().dyOauthLogin fail code " + i + " msg " + str);
                h.this.hideLoading();
                if (h.this.mView != null) {
                    ((g.c) h.this.mView).a(i, str, LGAccountConstant.LoginType.LOGIN_TYPE_DY);
                }
            }
        });
    }
}
