package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import com.bytedance.sdk.account.execute.ApiRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bs {

    /* renamed from: b, reason: collision with root package name */
    public static final String f3181b = "404";

    /* renamed from: c, reason: collision with root package name */
    protected final bb f3182c = bb.a();
    private Context g;

    /* renamed from: a, reason: collision with root package name */
    public static final String f3180a = r.f3247b;
    private static bs f = new bs();
    public static volatile String d = "";
    public static volatile String e = "";
    private static AtomicBoolean h = new AtomicBoolean(false);
    private static String i = "";
    private static AtomicBoolean j = new AtomicBoolean(false);
    private static String k = "";

    public static bs a() {
        return f;
    }

    private bs() {
    }

    public void a(Context context) {
        if (this.g == null) {
            this.g = context;
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("ad", str3);
            map.put("stacktrace", str2);
            a(str, f3181b, map);
        } catch (Exception e2) {
            this.f3182c.a(e2);
        }
    }

    private void a(String str, String str2, HashMap<String, String> map) {
        Uri.Builder builder = new Uri.Builder();
        try {
            builder.appendQueryParameter("type", str2).appendQueryParameter("p_ver", "9.171").appendQueryParameter("appsid", a("appsid", new Object[0])).appendQueryParameter(DispatchConstants.VERSION, "android_" + d() + "_" + bt.f).appendQueryParameter("pack", this.g != null ? this.g.getPackageName() : "").appendQueryParameter("sn", a("encodedSn", this.g)).appendQueryParameter("cuid", a("encodedCUID", this.g)).appendQueryParameter("os", DispatchConstants.ANDROID).appendQueryParameter("osv", av.a(this.g).d()).appendQueryParameter("romn", b()).appendQueryParameter("romv", c()).appendQueryParameter("bdr", "" + av.a(this.g).a()).appendQueryParameter("brd", "" + a(av.a(this.g).f()));
            if (str != null && str.length() > 128) {
                int iIndexOf = str.indexOf(10);
                if (iIndexOf <= 0) {
                    iIndexOf = 127;
                }
                str = str.substring(0, iIndexOf);
            }
            builder.appendQueryParameter("reason", str);
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.appendQueryParameter(entry.getKey(), entry.getValue());
                }
            }
        } catch (Throwable th) {
            this.f3182c.a(th);
        }
        ae aeVar = new ae(f3180a, "POST");
        aeVar.a(builder);
        aeVar.b();
    }

    private String a(String str, Object... objArr) {
        IXAdContainerFactory iXAdContainerFactoryC;
        u uVarA = u.a();
        if (uVarA == null || (iXAdContainerFactoryC = uVarA.c()) == null) {
            return "";
        }
        Object remoteParam = iXAdContainerFactoryC.getRemoteParam(str, objArr);
        return remoteParam instanceof String ? (String) remoteParam : "";
    }

    private String d() {
        String str = bt.e;
        if (!bt.d.equals(str)) {
            return str;
        }
        try {
            double dB = bh.b(bh.a(this.g));
            return dB > 0.0d ? String.valueOf(dB) : str;
        } catch (Throwable th) {
            this.f3182c.a(th);
            return str;
        }
    }

    private String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public String b() {
        try {
            if (h.compareAndSet(false, true)) {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                String str = (String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]);
                if (!TextUtils.isEmpty(str)) {
                    i = str;
                }
            }
            return i;
        } catch (Throwable th) {
            this.f3182c.a(th);
            return i;
        }
    }

    public String c() {
        try {
            if (j.get()) {
                return k;
            }
            if (!h.get()) {
                b();
            }
            if (i.equalsIgnoreCase("")) {
                j.set(true);
                return "";
            }
            if (j.compareAndSet(false, true)) {
                String strA = a("hw_sc.build.platform.version", "");
                if (!TextUtils.isEmpty(strA)) {
                    k = strA;
                }
            }
            return k;
        } catch (Throwable th) {
            this.f3182c.a(th);
            return k;
        }
    }

    private String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str3 = (String) cls.getDeclaredMethod(ApiRequest.METHOD_GET, String.class).invoke(cls, str);
            return TextUtils.isEmpty(str3) ? str2 : str3;
        } catch (Throwable th) {
            this.f3182c.a(th);
            return str2;
        }
    }
}
