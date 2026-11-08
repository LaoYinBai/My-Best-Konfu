package com.meizu.cloud.pushsdk.base;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends i<g> implements g {
    private static h a;
    private boolean b;

    private h(g gVar) {
        super(gVar);
        this.b = false;
    }

    public static h b() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h(new b());
                }
            }
        }
        return a;
    }

    public void a(Context context) {
        a(context, (String) null);
    }

    public void a(Context context, String str) {
        StringBuilder sb;
        String str2;
        if (this.b) {
            return;
        }
        this.b = true;
        b((context.getApplicationInfo().flags & 2) != 0);
        if (str == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                sb = new StringBuilder();
                sb.append(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath());
                str2 = "/pushSdk/";
            } else {
                sb = new StringBuilder();
                sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
                str2 = "/Android/data/pushSdk/";
            }
            sb.append(str2);
            sb.append(context.getPackageName());
            str = sb.toString();
        }
        a(str);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void a(String str) {
        c().a(str);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void a(String str, String str2) {
        c().a(str, str2);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void a(String str, String str2, Throwable th) {
        c().a(str, str2, th);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void a(boolean z) {
        c().a(z);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public boolean a() {
        return c().a();
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void b(String str, String str2) {
        c().b(str, str2);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void b(boolean z) {
        c().b(z);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void c(String str, String str2) {
        c().c(str, str2);
    }

    @Override // com.meizu.cloud.pushsdk.base.g
    public void d(String str, String str2) {
        c().d(str, str2);
    }
}
