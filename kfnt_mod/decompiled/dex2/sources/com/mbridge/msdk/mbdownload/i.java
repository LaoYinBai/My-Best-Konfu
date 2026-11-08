package com.mbridge.msdk.mbdownload;

import android.content.Context;
import android.content.SharedPreferences;
import com.mbridge.msdk.foundation.tools.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: NotificationRuntimeCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i {
    private static final String a = "com.mbridge.msdk.mbdownload.i";
    private final Context b;

    public i(Context context) {
        this.b = context;
    }

    public final void a(int i) {
        try {
            SharedPreferences sharedPreferences = this.b.getSharedPreferences("MB_RUNTIME_CACHE", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            synchronized (sharedPreferences) {
                editorEdit.putString("" + i, "");
                editorEdit.apply();
            }
            s.b(a, "add nid [" + i + "] to runtime cache.");
        } catch (Exception unused) {
        }
    }

    public final List<Integer> a() throws NumberFormatException {
        ArrayList arrayList = new ArrayList();
        try {
            SharedPreferences sharedPreferences = this.b.getSharedPreferences("MB_RUNTIME_CACHE", 0);
            Iterator<String> it = sharedPreferences.getAll().keySet().iterator();
            while (it.hasNext()) {
                try {
                    int i = Integer.parseInt(it.next());
                    arrayList.add(Integer.valueOf(i));
                    s.b(a, "get nid [" + i + "]");
                } catch (NumberFormatException unused) {
                }
            }
            sharedPreferences.edit().clear().apply();
        } catch (Exception unused2) {
        }
        return arrayList;
    }

    public final boolean b() {
        return this.b.getSharedPreferences("MB_RUNTIME_CACHE", 0).getAll().size() > 0;
    }

    public final void b(int i) {
        try {
            SharedPreferences sharedPreferences = this.b.getSharedPreferences("MB_RUNTIME_CACHE", 0);
            if (sharedPreferences.contains("" + i)) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.remove("" + i);
                editorEdit.apply();
            }
            s.b(a, "remove nid [" + i + "] to runtime cache.");
        } catch (Exception unused) {
        }
    }
}
