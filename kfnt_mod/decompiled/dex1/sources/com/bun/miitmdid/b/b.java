package com.bun.miitmdid.b;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    @Keep
    private static boolean h;

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private String f3271a = null;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private InterfaceC0091b f3272b = null;

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private Object f3273c = null;

    @Keep
    private Map<String, String> d = new HashMap();

    @Keep
    private Map<String, String> e = new HashMap();

    @Keep
    private Map<String, String> f = new HashMap();

    @Keep
    private String g = "GET";

    @Keep
    class a extends AsyncTask<Void, Void, c> {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        b f3274a;

        @Keep
        a() {
            this.f3274a = b.this;
        }

        @Keep
        protected native c a(Void... voidArr);

        @Keep
        protected native void a(c cVar);

        @Override // android.os.AsyncTask
        @Keep
        protected native /* bridge */ /* synthetic */ c doInBackground(Void[] voidArr);

        @Override // android.os.AsyncTask
        @Keep
        protected native /* bridge */ /* synthetic */ void onPostExecute(c cVar);
    }

    @Keep
    /* renamed from: com.bun.miitmdid.b.b$b, reason: collision with other inner class name */
    public interface InterfaceC0091b {
        @Keep
        void a(Exception exc, int i, String str);
    }

    @Keep
    private class c {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        private String f3276a;

        /* renamed from: b, reason: collision with root package name */
        @Keep
        private int f3277b;

        /* renamed from: c, reason: collision with root package name */
        @Keep
        private Exception f3278c;

        @Keep
        public c(b bVar, String str, Exception exc, int i) {
            this.f3276a = str;
            this.f3278c = exc;
            this.f3277b = i;
        }

        @Keep
        static native /* synthetic */ Exception a(c cVar);

        @Keep
        static native /* synthetic */ int b(c cVar);

        @Keep
        static native /* synthetic */ String c(c cVar);
    }

    @Keep
    private b(Context context) {
    }

    @Keep
    public static native b a(@NonNull Context context);

    @Keep
    static native /* synthetic */ String a(b bVar);

    @Keep
    private native void a(HttpURLConnection httpURLConnection);

    @Keep
    private native c b();

    @Keep
    static native /* synthetic */ c b(b bVar);

    @Keep
    private static native void b(@NonNull String str);

    @Keep
    private native c c();

    @Keep
    static native /* synthetic */ c c(b bVar);

    @Keep
    static native /* synthetic */ InterfaceC0091b d(b bVar);

    @Keep
    private native String d();

    @Keep
    public native b a();

    @Keep
    public native b a(InterfaceC0091b interfaceC0091b);

    @Keep
    public native b a(Object obj);

    @Keep
    public native b a(@NonNull String str);

    @Keep
    public native b a(@NonNull String str, @NonNull String str2);

    @Keep
    public native b a(Map<String, String> map);
}
