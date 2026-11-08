package com.yxcorp.kuaishou.addfp.a.a;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements a {
    private /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public static String a(String str, String str2) throws ClassNotFoundException {
        String str3;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            str3 = null;
        }
        return str3 == null ? "" : str3;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.a.a
    public final void a(boolean z, com.yxcorp.kuaishou.addfp.a.b.a aVar) {
        String strB;
        if (z || aVar != null) {
            try {
                try {
                    strB = aVar.b();
                    if (!TextUtils.isEmpty(strB)) {
                        b.a = strB;
                    }
                } finally {
                    try {
                        if (!TextUtils.isEmpty(b.a) && !b.a.startsWith("KWE")) {
                            this.a.g.b(b.a);
                        }
                        if (aVar != null) {
                            aVar.d();
                        }
                    } catch (Throwable unused) {
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                try {
                    if (!TextUtils.isEmpty(b.a) && !b.a.startsWith("KWE")) {
                        this.a.g.b(b.a);
                    }
                    if (aVar != null) {
                        aVar.d();
                        return;
                    }
                    return;
                } catch (Throwable unused2) {
                    return;
                }
            }
        } else {
            strB = null;
        }
        new StringBuilder("get OAID ").append(strB);
        b.a().b();
    }
}
