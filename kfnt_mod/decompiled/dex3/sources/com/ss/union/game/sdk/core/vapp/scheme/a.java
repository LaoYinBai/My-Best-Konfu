package com.ss.union.game.sdk.core.vapp.scheme;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final int a = 1000;
    private static ThreadLocal<String> b = new ThreadLocal<>();
    private static ThreadLocal<Long> c = new ThreadLocal<>();
    private List<com.ss.union.game.sdk.core.vapp.scheme.a.b> d = new ArrayList();

    public a(Intent intent) {
        this.d.add(new com.ss.union.game.sdk.core.vapp.scheme.a.a.a(intent));
    }

    public static boolean a(String str) {
        return TextUtils.equals(b.get(), str) && System.currentTimeMillis() - c.get().longValue() < 1000;
    }

    public void a(com.ss.union.game.sdk.core.vapp.scheme.a.b bVar) {
        this.d.add(bVar);
    }

    public boolean a(Context context) {
        Iterator<com.ss.union.game.sdk.core.vapp.scheme.a.b> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().b(context)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(Context context) {
        for (com.ss.union.game.sdk.core.vapp.scheme.a.b bVar : this.d) {
            if (bVar.b(context)) {
                bVar.c(context);
                return true;
            }
        }
        return false;
    }

    public boolean c(Context context) {
        for (com.ss.union.game.sdk.core.vapp.scheme.a.b bVar : this.d) {
            if (bVar.b(context)) {
                return bVar.a(context);
            }
        }
        return false;
    }
}
