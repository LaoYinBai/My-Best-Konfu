package com.ta.utdid2.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ta.utdid2.a.a.g;
import com.ta.utdid2.b.a.b;
import com.umeng.commonsdk.proguard.o;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {

    /* renamed from: a, reason: collision with other field name */
    private SharedPreferences f11a;

    /* renamed from: a, reason: collision with other field name */
    private b f13a;

    /* renamed from: a, reason: collision with other field name */
    private d f14a;
    private String b;
    private String c;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private Context mContext;
    private SharedPreferences.Editor a = null;

    /* renamed from: a, reason: collision with other field name */
    private b.a f12a = null;

    /* JADX WARN: Removed duplicated region for block: B:62:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    private d a(String str) {
        File fileM7a = m7a(str);
        if (fileM7a == null) {
            return null;
        }
        this.f14a = new d(fileM7a.getAbsolutePath());
        return this.f14a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private File m7a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        b.a aVarA;
        if (sharedPreferences == null || bVar == null || (aVarA = bVar.a()) == null) {
            return;
        }
        aVarA.b();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                aVarA.a(key, (String) value);
            } else if (value instanceof Integer) {
                aVarA.a(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                aVarA.a(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                aVarA.a(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                aVarA.a(key, ((Boolean) value).booleanValue());
            }
        }
        try {
            aVarA.commit();
        } catch (Exception unused) {
        }
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorEdit;
        if (bVar == null || sharedPreferences == null || (editorEdit = sharedPreferences.edit()) == null) {
            return;
        }
        editorEdit.clear();
        for (Map.Entry<String, ?> entry : bVar.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                editorEdit.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editorEdit.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                editorEdit.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                editorEdit.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
            }
        }
        editorEdit.commit();
    }

    private boolean c() {
        if (this.f13a == null) {
            return false;
        }
        boolean zB = this.f13a.b();
        if (!zB) {
            commit();
        }
        return zB;
    }

    private void b() {
        if (this.a == null && this.f11a != null) {
            this.a = this.f11a.edit();
        }
        if (this.h && this.f12a == null && this.f13a != null) {
            this.f12a = this.f13a.a();
        }
        c();
    }

    public void putString(String str, String str2) {
        if (g.m6a(str) || str.equals(o.av)) {
            return;
        }
        b();
        if (this.a != null) {
            this.a.putString(str, str2);
        }
        if (this.f12a != null) {
            this.f12a.a(str, str2);
        }
    }

    public void remove(String str) {
        if (g.m6a(str) || str.equals(o.av)) {
            return;
        }
        b();
        if (this.a != null) {
            this.a.remove(str);
        }
        if (this.f12a != null) {
            this.f12a.a(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean commit() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.a
            r3 = 0
            if (r2 == 0) goto L22
            boolean r2 = r5.i
            if (r2 != 0) goto L18
            android.content.SharedPreferences r2 = r5.f11a
            if (r2 == 0) goto L18
            android.content.SharedPreferences$Editor r2 = r5.a
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L18:
            android.content.SharedPreferences$Editor r0 = r5.a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L22
            r0 = 0
            goto L23
        L22:
            r0 = 1
        L23:
            android.content.SharedPreferences r1 = r5.f11a
            if (r1 == 0) goto L35
            android.content.Context r1 = r5.mContext
            if (r1 == 0) goto L35
            android.content.Context r1 = r5.mContext
            java.lang.String r2 = r5.b
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f11a = r1
        L35:
            r1 = 0
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L3c
            r1 = r2
            goto L40
        L3c:
            r2 = move-exception
            r2.printStackTrace()
        L40:
            boolean r2 = com.ta.utdid2.a.a.g.m6a(r1)
            if (r2 != 0) goto Lad
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L8b
            com.ta.utdid2.b.a.b r2 = r5.f13a
            if (r2 != 0) goto L7e
            java.lang.String r2 = r5.c
            com.ta.utdid2.b.a.d r2 = r5.a(r2)
            if (r2 == 0) goto L8b
            java.lang.String r4 = r5.b
            com.ta.utdid2.b.a.b r2 = r2.a(r4, r3)
            r5.f13a = r2
            boolean r2 = r5.i
            if (r2 != 0) goto L6e
            android.content.SharedPreferences r2 = r5.f11a
            com.ta.utdid2.b.a.b r4 = r5.f13a
            r5.a(r2, r4)
            goto L75
        L6e:
            com.ta.utdid2.b.a.b r2 = r5.f13a
            android.content.SharedPreferences r4 = r5.f11a
            r5.a(r2, r4)
        L75:
            com.ta.utdid2.b.a.b r2 = r5.f13a
            com.ta.utdid2.b.a.b$a r2 = r2.a()
            r5.f12a = r2
            goto L8b
        L7e:
            com.ta.utdid2.b.a.b$a r2 = r5.f12a     // Catch: java.lang.Exception -> L8a
            if (r2 == 0) goto L8b
            com.ta.utdid2.b.a.b$a r2 = r5.f12a     // Catch: java.lang.Exception -> L8a
            boolean r2 = r2.commit()     // Catch: java.lang.Exception -> L8a
            if (r2 != 0) goto L8b
        L8a:
            r0 = 0
        L8b:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L9f
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto Lad
            com.ta.utdid2.b.a.b r1 = r5.f13a
            if (r1 == 0) goto Lad
        L9f:
            com.ta.utdid2.b.a.d r1 = r5.f14a     // Catch: java.lang.Exception -> Lad
            if (r1 == 0) goto Lad
            com.ta.utdid2.b.a.d r1 = r5.f14a     // Catch: java.lang.Exception -> Lad
            java.lang.String r2 = r5.b     // Catch: java.lang.Exception -> Lad
            com.ta.utdid2.b.a.b r1 = r1.a(r2, r3)     // Catch: java.lang.Exception -> Lad
            r5.f13a = r1     // Catch: java.lang.Exception -> Lad
        Lad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.commit():boolean");
    }

    public String getString(String str) {
        c();
        if (this.f11a != null) {
            String string = this.f11a.getString(str, "");
            if (!g.m6a(string)) {
                return string;
            }
        }
        return this.f13a != null ? this.f13a.getString(str, "") : "";
    }
}
