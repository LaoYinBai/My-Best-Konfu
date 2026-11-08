package com.kwad.sdk.core.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.g.a;
import com.kwad.sdk.g.b;
import com.kwad.sdk.utils.ao;
import com.kwad.sdk.utils.as;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.i;
import com.kwad.v8.Platform;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static String a = "";
    private static InterfaceC0037a b = null;
    private static boolean c = false;
    private static boolean d = false;

    /* renamed from: com.kwad.sdk.core.f.a$a, reason: collision with other inner class name */
    public interface InterfaceC0037a {
        @WorkerThread
        void a(String str);
    }

    public static String a() {
        Context context = KsAdSDKImpl.get().getContext();
        String strA = a(context);
        return (TextUtils.isEmpty(strA) && TextUtils.isEmpty(av.e(context))) ? b.ac() : strA;
    }

    public static String a(Context context) {
        if (ao.i() && !TextUtils.isEmpty(ao.j())) {
            return ao.j();
        }
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = as.e(context);
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        if (ao.i() || b.a(2048L)) {
            return a;
        }
        b(context);
        return a;
    }

    @SuppressLint({"PrivateApi"})
    private static String b(String str) throws ClassNotFoundException {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, Platform.UNKNOWN);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return null;
        }
    }

    private static void b(final Context context) {
        if (!TextUtils.isEmpty(a) || context == null) {
            return;
        }
        c(context);
        if (com.kwad.sdk.g.a.a()) {
            com.kwad.sdk.g.a.a(context, new a.InterfaceC0063a() { // from class: com.kwad.sdk.core.f.a.1
            });
        } else if (com.kwad.sdk.g.b.a()) {
            com.kwad.sdk.g.b.a(context, new b.a() { // from class: com.kwad.sdk.core.f.a.2
            });
        }
    }

    private static void c(Context context) {
        if (!TextUtils.isEmpty(a) || context == null || c) {
            return;
        }
        c = true;
        if (context == null || d) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        i.a(new Runnable() { // from class: com.kwad.sdk.core.f.a.3
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:50:0x00b3  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    Method dump skipped, instructions count: 488
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.f.a.AnonymousClass3.run():void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f() throws ClassNotFoundException {
        String strB = b("ro.build.freeme.label");
        return !TextUtils.isEmpty(strB) && strB.equalsIgnoreCase("FREEMEOS");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean g() throws ClassNotFoundException {
        String strB = b("ro.ssui.product");
        return (TextUtils.isEmpty(strB) || strB.equalsIgnoreCase(Platform.UNKNOWN)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h() {
        if (b != null) {
            b.a(a);
        }
    }
}
