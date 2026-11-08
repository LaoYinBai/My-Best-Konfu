package com.vivo.push;

import android.os.Bundle;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    public Bundle a;
    String b;
    private String c;

    public a(String str, String str2, Bundle bundle) {
        this.b = str;
        this.c = str2;
        this.a = bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.vivo.push.a a(android.content.Intent r5) {
        /*
            r0 = 0
            if (r5 != 0) goto Lb
            java.lang.String r5 = "BundleWapper"
            java.lang.String r1 = "create error : intent is null"
            com.vivo.push.util.n.a(r5, r1)
            return r0
        Lb:
            android.os.Bundle r1 = r5.getExtras()
            if (r1 == 0) goto L1e
            java.lang.String r2 = "client_pkgname"
            java.lang.String r2 = r1.getString(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L1e
            goto L1f
        L1e:
            r2 = r0
        L1f:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L2c
            java.lang.String r3 = "BundleWapper"
            java.lang.String r4 = "create warning: pkgName is null"
            com.vivo.push.util.n.b(r3, r4)
        L2c:
            java.lang.String r3 = r5.getPackage()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L54
            android.content.ComponentName r3 = r5.getComponent()
            if (r3 != 0) goto L3e
        L3c:
            r3 = r0
            goto L47
        L3e:
            android.content.ComponentName r5 = r5.getComponent()
            java.lang.String r0 = r5.getPackageName()
            goto L3c
        L47:
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 == 0) goto L54
            java.lang.String r5 = "BundleWapper"
            java.lang.String r0 = "create warning: targetPkgName is null"
            com.vivo.push.util.n.b(r5, r0)
        L54:
            com.vivo.push.a r5 = new com.vivo.push.a
            r5.<init>(r2, r3, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.a.a(android.content.Intent):com.vivo.push.a");
    }

    public final void a(String str, int i) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putInt(str, i);
    }

    public final void a(String str, long j) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putLong(str, j);
    }

    public final void a(String str, String str2) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putString(str, str2);
    }

    public final void a(String str, Serializable serializable) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putSerializable(str, serializable);
    }

    public final void a(String str, ArrayList<String> arrayList) {
        if (this.a == null) {
            this.a = new Bundle();
        }
        this.a.putStringArrayList(str, arrayList);
    }

    public final String a(String str) {
        if (this.a == null) {
            return null;
        }
        return this.a.getString(str);
    }

    public final int b(String str, int i) {
        return this.a == null ? i : this.a.getInt(str, i);
    }

    public final ArrayList<String> b(String str) {
        if (this.a == null) {
            return null;
        }
        return this.a.getStringArrayList(str);
    }

    public final long b(String str, long j) {
        return this.a == null ? j : this.a.getLong(str, j);
    }
}
