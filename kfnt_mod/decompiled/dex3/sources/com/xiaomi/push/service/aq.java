package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.push.ho;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class aq {
    private static volatile aq a;

    /* renamed from: a, reason: collision with other field name */
    protected SharedPreferences f890a;

    /* renamed from: a, reason: collision with other field name */
    private HashSet<a> f891a = new HashSet<>();

    public static abstract class a implements Runnable {
        private String mDescription;
        private int mId;

        public a(int i, String str) {
            this.mId = i;
            this.mDescription = str;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && this.mId == ((a) obj).mId;
        }

        public int hashCode() {
            return this.mId;
        }

        protected abstract void onCallback();

        @Override // java.lang.Runnable
        public final void run() {
            onCallback();
        }
    }

    private aq(Context context) {
        this.f890a = context.getSharedPreferences("mipush_oc", 0);
    }

    public static aq a(Context context) {
        if (a == null) {
            synchronized (aq.class) {
                if (a == null) {
                    a = new aq(context);
                }
            }
        }
        return a;
    }

    private String a(int i) {
        return "normal_oc_" + i;
    }

    private void a(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
            return;
        }
        if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
            return;
        }
        if (!(pair.second instanceof String)) {
            if (pair.second instanceof Boolean) {
                editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
            }
        } else {
            String strA = (String) pair.second;
            if (str.equals(a(ho.AppIsInstalledList.a()))) {
                strA = com.xiaomi.push.bj.a(strA);
            }
            editor.putString(str, strA);
        }
    }

    private String b(int i) {
        return "custom_oc_" + i;
    }

    public int a(int i, int i2) {
        String strB = b(i);
        if (this.f890a.contains(strB)) {
            return this.f890a.getInt(strB, 0);
        }
        String strA = a(i);
        return this.f890a.contains(strA) ? this.f890a.getInt(strA, 0) : i2;
    }

    public String a(int i, String str) {
        String strB = b(i);
        if (this.f890a.contains(strB)) {
            return this.f890a.getString(strB, null);
        }
        String strA = a(i);
        return this.f890a.contains(strA) ? this.f890a.getString(strA, null) : str;
    }

    public synchronized void a() {
        this.f891a.clear();
    }

    public synchronized void a(a aVar) {
        if (!this.f891a.contains(aVar)) {
            this.f891a.add(aVar);
        }
    }

    public void a(List<Pair<Integer, Object>> list) {
        if (com.xiaomi.push.ad.a(list)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.f890a.edit();
        for (Pair<Integer, Object> pair : list) {
            if (pair.first != null && pair.second != null) {
                a(editorEdit, pair, a(((Integer) pair.first).intValue()));
            }
        }
        editorEdit.commit();
    }

    public boolean a(int i, boolean z) {
        String strB = b(i);
        if (this.f890a.contains(strB)) {
            return this.f890a.getBoolean(strB, false);
        }
        String strA = a(i);
        return this.f890a.contains(strA) ? this.f890a.getBoolean(strA, false) : z;
    }

    void b() {
        com.xiaomi.channel.commonutils.logger.b.c("OC_Callback : receive new oc data");
        HashSet hashSet = new HashSet();
        synchronized (this) {
            hashSet.addAll(this.f891a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar != null) {
                aVar.run();
            }
        }
        hashSet.clear();
    }

    public void b(List<Pair<Integer, Object>> list) {
        if (com.xiaomi.push.ad.a(list)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.f890a.edit();
        for (Pair<Integer, Object> pair : list) {
            if (pair.first != null) {
                String strB = b(((Integer) pair.first).intValue());
                if (pair.second == null) {
                    editorEdit.remove(strB);
                } else {
                    a(editorEdit, pair, strB);
                }
            }
        }
        editorEdit.commit();
    }
}
