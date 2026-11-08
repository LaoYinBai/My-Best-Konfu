package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.baidu.mobads.container.util.BuildUtils;
import com.baidu.mobads.sdk.internal.ae;
import dalvik.system.DexClassLoader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile d f3224a;

    private d() {
    }

    public static d a() {
        if (f3224a == null) {
            synchronized (d.class) {
                if (f3224a == null) {
                    f3224a = new d();
                }
            }
        }
        return f3224a;
    }

    public void a(double d, ae.b bVar) {
        by byVarA = by.a();
        String strC = byVarA.c(r.f3246a);
        HashMap<String, String> map = new HashMap<>();
        map.put(DispatchConstants.VERSION, "" + d);
        map.put("os", DispatchConstants.ANDROID);
        map.put("tp", a(BuildUtils.getInstance((Context) null).getModel()));
        map.put("bdr", a(BuildUtils.getInstance((Context) null).getSDK()));
        ae aeVar = new ae(byVarA.a(strC, map));
        aeVar.a(bVar);
        aeVar.b();
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException | NullPointerException unused) {
            return str;
        }
    }

    public DexClassLoader a(String str, String str2, String str3, ClassLoader classLoader) {
        return new DexClassLoader(str, str2, str3, classLoader);
    }
}
