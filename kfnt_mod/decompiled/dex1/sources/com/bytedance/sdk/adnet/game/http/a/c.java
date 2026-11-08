package com.bytedance.sdk.adnet.game.http.a;

import android.text.TextUtils;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements Interceptor {
    private int a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable unused) {
            return i;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String[] strArrSplit;
        Request request = chain.request();
        String strHeader = request.header("h_out_time_rwc");
        if (!TextUtils.isEmpty(strHeader) && (strArrSplit = strHeader.split("_")) != null && strArrSplit.length == 3) {
            int iA = a(strArrSplit[0], -1);
            int iA2 = a(strArrSplit[1], -1);
            int iA3 = a(strArrSplit[2], -1);
            if (iA != -1) {
                chain = chain.withReadTimeout(iA, TimeUnit.SECONDS);
            }
            if (iA2 != -1) {
                chain = chain.withWriteTimeout(iA2, TimeUnit.SECONDS);
            }
            if (iA3 != -1) {
                chain = chain.withConnectTimeout(iA3, TimeUnit.SECONDS);
            }
        }
        return chain.proceed(request);
    }
}
