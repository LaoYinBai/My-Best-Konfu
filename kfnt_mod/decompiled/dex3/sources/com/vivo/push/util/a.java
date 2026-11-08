package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    protected Context a;
    private String b;
    private volatile SharedPreferences c;
    private HashMap<String, String> d = new HashMap<>();
    private HashMap<String, Long> e = new HashMap<>();
    private HashMap<String, Integer> f = new HashMap<>();
    private HashMap<String, Boolean> g = new HashMap<>();

    public final void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("sharedFileName can't be null");
        }
        this.b = str;
        this.c = context.getSharedPreferences(this.b, 0);
        this.a = context;
    }

    public final void a(String str, String str2) {
        this.d.put(str, str2);
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putString(str, str2);
            a(editorEdit);
        }
    }

    public final void a(String str, int i) {
        this.f.put(str, Integer.valueOf(i));
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putInt(str, i);
            a(editorEdit);
        }
    }

    public final void a(String str, long j) {
        this.e.put(str, Long.valueOf(j));
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putLong(str, j);
            a(editorEdit);
        }
    }

    public final String a(String str) {
        String string = this.d.get(str);
        if (string != null) {
            return string;
        }
        b();
        if (this.c != null) {
            string = this.c.getString(str, null);
            if (!TextUtils.isEmpty(string) && !string.equals(null)) {
                this.d.put(str, string);
            }
        }
        return string;
    }

    public final int b(String str) {
        Integer numValueOf = this.f.get(str);
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        b();
        if (this.c != null) {
            numValueOf = Integer.valueOf(this.c.getInt(str, 0));
            if (!numValueOf.equals(0)) {
                this.f.put(str, numValueOf);
            }
        }
        return numValueOf.intValue();
    }

    public final long b(String str, long j) {
        Long lValueOf = this.e.get(str);
        if (lValueOf != null) {
            return lValueOf.longValue();
        }
        b();
        if (this.c != null) {
            lValueOf = Long.valueOf(this.c.getLong(str, j));
            if (!lValueOf.equals(Long.valueOf(j))) {
                this.e.put(str, lValueOf);
            }
        }
        return lValueOf.longValue();
    }

    public final void c(String str) {
        this.e.remove(str);
        this.f.remove(str);
        this.g.remove(str);
        this.d.remove(str);
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            if (this.c.contains(str)) {
                editorEdit.remove(str);
                a(editorEdit);
            }
        }
    }

    public static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public final void a() {
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.d.clear();
        b();
        if (this.c != null) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.clear();
            a(editorEdit);
        }
    }

    private void b() {
        if (this.c == null) {
            if (this.a == null) {
                throw new RuntimeException("SharedPreferences is not init", new Throwable());
            }
            this.c = this.a.getSharedPreferences(this.b, 0);
        }
    }
}
