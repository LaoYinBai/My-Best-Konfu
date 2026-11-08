package com.ss.union.game.sdk.common.net.tnc;

import android.content.Context;
import com.bytedance.sdk.adtnc.a;
import com.bytedance.sdk.adtnc.sdk.a.b;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VTncUtils {
    public static final String TAG = "VTncUtils";
    private static volatile boolean a;

    public static synchronized void init(final Context context, final String str) {
        a = true;
        LogUtils.log(TAG, "start init");
        a.a().a(new com.bytedance.sdk.adtnc.sdk.b.a() { // from class: com.ss.union.game.sdk.common.net.tnc.VTncUtils.1
            public boolean b() {
                return true;
            }

            public int d() {
                return 0;
            }

            public Context a() {
                return context;
            }

            public String c() {
                return str;
            }

            public String[] g() {
                return new String[]{"dm.toutiao.com", "dm-hl.toutiao.com", "dm-lq.toutiao.com"};
            }
        });
    }

    public static synchronized String filterUrl(String str, String str2) {
        if (a) {
            LogUtils.log(TAG, "filterUrl has Init true");
            return a.a().a(str, str2);
        }
        LogUtils.log(TAG, "filterUrl has Init false");
        return str2;
    }

    public static synchronized void onResponse(String str, com.bytedance.sdk.adtnc.sdk.a.a aVar, b bVar) {
        if (a) {
            LogUtils.log(TAG, "onResponse Init true");
            a.a().a(str, aVar, bVar);
        } else {
            LogUtils.log(TAG, "onResponse Init false");
        }
    }

    public static synchronized void onError(String str, com.bytedance.sdk.adtnc.sdk.a.a aVar, Throwable th) {
        if (a) {
            LogUtils.log(TAG, "onError Init true");
            try {
                a.a().a(str, aVar, th);
            } catch (Throwable unused) {
            }
        } else {
            LogUtils.log(TAG, "onError Init false");
        }
    }
}
