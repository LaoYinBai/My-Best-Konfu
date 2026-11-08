package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ae {
    private static volatile ae a;

    /* renamed from: a, reason: collision with other field name */
    private Context f59a;

    /* renamed from: a, reason: collision with other field name */
    private List<w> f60a = new ArrayList();

    private ae(Context context) {
        this.f59a = context.getApplicationContext();
        if (this.f59a == null) {
            this.f59a = context;
        }
    }

    public static ae a(Context context) {
        if (a == null) {
            synchronized (ae.class) {
                if (a == null) {
                    a = new ae(context);
                }
            }
        }
        return a;
    }

    public int a(String str) {
        synchronized (this.f60a) {
            w wVar = new w();
            wVar.f98a = str;
            if (this.f60a.contains(wVar)) {
                for (w wVar2 : this.f60a) {
                    if (wVar2.equals(wVar)) {
                        return wVar2.a;
                    }
                }
            }
            return 0;
        }
    }

    public synchronized String a(at atVar) {
        return this.f59a.getSharedPreferences("mipush_extra", 0).getString(atVar.name(), "");
    }

    public synchronized void a(at atVar, String str) {
        SharedPreferences sharedPreferences = this.f59a.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putString(atVar.name(), str).commit();
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m42a(String str) {
        synchronized (this.f60a) {
            w wVar = new w();
            wVar.a = 0;
            wVar.f98a = str;
            if (this.f60a.contains(wVar)) {
                this.f60a.remove(wVar);
            }
            this.f60a.add(wVar);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m43a(String str) {
        synchronized (this.f60a) {
            w wVar = new w();
            wVar.f98a = str;
            return this.f60a.contains(wVar);
        }
    }

    public void b(String str) {
        synchronized (this.f60a) {
            w wVar = new w();
            wVar.f98a = str;
            if (this.f60a.contains(wVar)) {
                Iterator<w> it = this.f60a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    w next = it.next();
                    if (wVar.equals(next)) {
                        wVar = next;
                        break;
                    }
                }
            }
            wVar.a++;
            this.f60a.remove(wVar);
            this.f60a.add(wVar);
        }
    }

    public void c(String str) {
        synchronized (this.f60a) {
            w wVar = new w();
            wVar.f98a = str;
            if (this.f60a.contains(wVar)) {
                this.f60a.remove(wVar);
            }
        }
    }
}
