package com.ss.union.game.sdk.account.third.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.bytedance.sdk.open.aweme.TikTokOpenApiFactory;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.api.TiktokOpenApi;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.ss.union.game.sdk.account.third.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TikTokLoginEntryActivity extends Activity implements TikTokApiEventHandler {
    TiktokOpenApi a;

    public void onReq(BaseReq baseReq) {
    }

    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.a = TikTokOpenApiFactory.create(this, 1);
        this.a.handleIntent(getIntent(), this);
    }

    public void onResp(BaseResp baseResp) {
        a("dyOauth onResp() type:" + baseResp.getType());
        if (baseResp.getType() == 2) {
            Authorization.Response response = (Authorization.Response) baseResp;
            if (baseResp.isSuccess()) {
                b(response.authCode);
            } else {
                a(response.errorCode, response.errorMsg);
            }
        }
    }

    public void onErrorIntent(@Nullable Intent intent) {
        a(-202, "系统错误，请稍后再试");
    }

    private void b(String str) {
        a("dyOauth onSuc() authCode:" + str);
        a.InterfaceC0052a interfaceC0052aB = a.a().b();
        if (interfaceC0052aB == null) {
            return;
        }
        interfaceC0052aB.a(str);
        a();
    }

    private void a(int i, String str) {
        a("dyOauth onFail() code:" + i + ",msg:" + str);
        a.InterfaceC0052a interfaceC0052aB = a.a().b();
        if (interfaceC0052aB == null) {
            return;
        }
        interfaceC0052aB.a(i, str);
        a();
    }

    private void a() {
        a.a().c();
        finish();
    }

    public void a(String str) {
        com.ss.union.game.sdk.c.a.a(str);
    }
}
