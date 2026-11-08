package com.mbridge.msdk.foundation.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.mbridge.msdk.foundation.tools.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: SharedPerferenceManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static final String a = "a";
    private static a c;
    SharedPreferences b;

    private a() {
    }

    public static synchronized a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public final void a(String str, String str2) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return;
            }
            if (this.b == null && contextJ != null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            SharedPreferences.Editor editorEdit = this.b.edit();
            editorEdit.putString(str, str2);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(String str, int i) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return;
            }
            if (this.b == null && contextJ != null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            SharedPreferences.Editor editorEdit = this.b.edit();
            editorEdit.putInt(str, i);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(String str, long j) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                s.d(a, "context is null in put");
                return;
            }
            if (this.b == null && contextJ != null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            SharedPreferences.Editor editorEdit = this.b.edit();
            editorEdit.putLong(str, j);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final Long a(String str) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                s.d(a, "context is null in get");
                return 0L;
            }
            if (this.b == null && contextJ != null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            return Long.valueOf(this.b.getLong(str, 0L));
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public final int b(String str, int i) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return i;
            }
            if (this.b == null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            return this.b.getInt(str, i);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public final String b(String str) {
        try {
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return null;
            }
            if (this.b == null && contextJ != null) {
                this.b = contextJ.getSharedPreferences("mbridge", 0);
            }
            return this.b.getString(str, "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final List<String> b() {
        ArrayList arrayList = new ArrayList();
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ == null) {
            return null;
        }
        if (this.b == null && contextJ != null) {
            this.b = contextJ.getSharedPreferences("mbridge", 0);
        }
        Iterator<Map.Entry<String, ?>> it = this.b.getAll().entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        return arrayList;
    }

    public final void c(String str) {
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ == null) {
            return;
        }
        if (this.b == null && contextJ != null) {
            this.b = contextJ.getSharedPreferences("mbridge", 0);
        }
        this.b.edit().remove(str).apply();
    }
}
