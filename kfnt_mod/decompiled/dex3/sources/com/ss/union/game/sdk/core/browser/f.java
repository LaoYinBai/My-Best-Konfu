package com.ss.union.game.sdk.core.browser;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.ss.union.game.sdk.core.vapp.VBindAccountActivity;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements a {
    private static final String a = "JSVHandlerAdapter";

    @Override // com.ss.union.game.sdk.core.browser.a
    public void a(b bVar) {
    }

    @Override // com.ss.union.game.sdk.core.browser.a
    public void b(b bVar) {
    }

    @Override // com.ss.union.game.sdk.core.browser.a
    public void a(Activity activity, int i, int i2, Intent intent) {
        LogDevKitUtils.logBrowser("onActivityResult：" + i + Constants.COLON_SEPARATOR + i2 + ",data:" + intent);
        if (i != 10232 || intent == null) {
            return;
        }
        if (i2 == 0) {
            String stringExtra = intent.getStringExtra("v_bind_token");
            String stringExtra2 = intent.getStringExtra("v_nick_name");
            String stringExtra3 = intent.getStringExtra("v_bind_msg");
            if (!TextUtils.isEmpty(stringExtra3)) {
                ToastUtils.getInstance().toast(stringExtra3);
                return;
            }
            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
                ToastUtils.getInstance().toast("参数错误");
                return;
            }
            try {
                VBindAccountActivity.a(activity, stringExtra, stringExtra2, false, false);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        String stringExtra4 = intent.getStringExtra("v_bind_msg");
        if (TextUtils.isEmpty(stringExtra4)) {
            return;
        }
        ToastUtils.getInstance().toast(stringExtra4);
    }
}
