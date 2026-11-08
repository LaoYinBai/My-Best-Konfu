package com.kwad.sdk.core.i;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static Map<String, WeakReference<C0039a>> a = new ConcurrentHashMap();

    /* renamed from: com.kwad.sdk.core.i.a$a, reason: collision with other inner class name */
    public static class C0039a {
        private HandlerThread a;
        private Handler b;

        public C0039a(String str) {
            String str2;
            if (TextUtils.isEmpty(str)) {
                str2 = "ksad-HandlerThread";
            } else {
                str2 = "ksad-" + str;
            }
            this.a = new HandlerThread(str2);
            this.a.start();
            this.b = new Handler(this.a.getLooper());
        }

        public Handler a() {
            return this.b;
        }
    }

    public static synchronized Handler a() {
        return a("commonHandlerThread").a();
    }

    @NonNull
    private static C0039a a(String str) {
        if (str == null) {
            return new C0039a(null);
        }
        WeakReference<C0039a> weakReference = a.get(str);
        if (weakReference != null && weakReference.get() != null) {
            return weakReference.get();
        }
        C0039a c0039a = new C0039a(str);
        a.put(str, new WeakReference<>(c0039a));
        return c0039a;
    }

    public static synchronized Handler b() {
        return a("reportHandlerThread").a();
    }
}
