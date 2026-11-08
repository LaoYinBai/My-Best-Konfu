package com.bytedance.b.a.c;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.b.a.c.k;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentHashMap<String, k> f3728a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static ConcurrentHashMap<String, List<String>> f3729b = new ConcurrentHashMap<>();

    /* renamed from: c, reason: collision with root package name */
    private static ConcurrentHashMap<String, List<String>> f3730c = new ConcurrentHashMap<>();

    public static String a() {
        return com.bytedance.frameworks.core.a.a.f;
    }

    public static synchronized void a(@NonNull String str, @NonNull List<String> list) {
        if (!TextUtils.isEmpty(str) && !g.a(list)) {
            LinkedList linkedList = new LinkedList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                try {
                    linkedList.add(("https://" + new URL(it.next()).getHost()) + h.f3688c);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            f3730c.put(str, linkedList);
        }
    }

    @Deprecated
    public static synchronized void b(@NonNull String str, @NonNull List<String> list) {
        if (!TextUtils.isEmpty(str) && !g.a(list)) {
            f3729b.put(str, a(list));
        }
    }

    public static synchronized void c(@NonNull String str, @NonNull List<String> list) {
        if (!TextUtils.isEmpty(str) && !g.a(list)) {
            f3729b.put(str, a(list));
        }
    }

    public static List<String> a(List<String> list) {
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                linkedList.add(("https://" + new URL(it.next()).getHost()) + h.d);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return linkedList;
    }

    @Deprecated
    public static synchronized void a(@NonNull Context context, @NonNull String str, @NonNull JSONObject jSONObject, k.b bVar) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (jSONObject == null) {
            throw new IllegalArgumentException("param 'headerInfo' is not allowed to be null");
        }
        if (f3728a.get(str) == null || !f3728a.get(str).j()) {
            k kVar = f3728a.get(str);
            if (kVar == null) {
                kVar = new k(str);
                f3728a.put(str, kVar);
            }
            kVar.a(f3730c.remove(str));
            kVar.b(f3729b.remove(str));
            kVar.a(context, jSONObject, bVar);
        }
    }

    public static synchronized void a(@NonNull Context context, @NonNull String str, @NonNull JSONObject jSONObject, k.c cVar) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (jSONObject == null) {
            throw new IllegalArgumentException("param 'headerInfo' is not allowed to be null");
        }
        if (f3728a.get(str) == null || !f3728a.get(str).j()) {
            k kVar = f3728a.get(str);
            if (kVar == null) {
                kVar = new k(str);
                f3728a.put(str, kVar);
            }
            kVar.a(f3730c.remove(str));
            kVar.b(f3729b.remove(str));
            kVar.a(context, jSONObject, cVar);
        }
    }

    public static k a(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("param 'aid' is not allowed to assigned empty string");
        }
        if (f3728a.get(str) == null) {
            synchronized (l.class) {
                if (f3728a.get(str) == null) {
                    f3728a.put(str, new k(str));
                }
            }
        }
        return f3728a.get(str);
    }
}
