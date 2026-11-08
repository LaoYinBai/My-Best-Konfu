package com.huawei;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.bytedance.push.utils.e;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.utils.Util;
import com.hw.HWPushAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements Runnable {
    private final Context a;
    private final int b;
    private String c;

    public a(Context context) {
        this.a = context;
        this.b = HWPushAdapter.getHwPush();
    }

    a(Context context, String str) {
        this(context);
        this.c = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String strA = this.c;
            if (TextUtils.isEmpty(strA)) {
                strA = a();
            }
            if (TextUtils.isEmpty(strA)) {
                e.b(HWPushAdapter.TAG, "get huawei token error!!");
                return;
            }
            e.a(HWPushAdapter.TAG, "get token success : " + strA);
            a(strA);
        } catch (ApiException e) {
            int statusCode = e.getStatusCode();
            i.l().a(HWPushAdapter.TAG, "getToken error，errCode = " + statusCode + " please visit : https://developer.huawei.com/consumer/cn/doc/development/HMS-References/status");
            b(String.valueOf(statusCode));
        } catch (Exception e2) {
            e2.printStackTrace();
            b("");
        }
    }

    private String a() throws ApiException {
        int iB = b.b(this.a);
        if (iB != 0) {
            e.a(HWPushAdapter.TAG, "hms not available:" + iB);
            i.o().a(this.b, 109, String.valueOf(iB), "hms not available");
            return null;
        }
        String token = HmsInstanceId.getInstance(this.a).getToken(Util.getAppId(this.a), "HCM");
        if (!TextUtils.isEmpty(token)) {
            return token;
        }
        b("");
        return null;
    }

    private void a(String str) {
        i.m().a(this.a, this.b, str);
    }

    private void b(String str) {
        i.o().a(this.b, 102, str, "get token error");
    }
}
