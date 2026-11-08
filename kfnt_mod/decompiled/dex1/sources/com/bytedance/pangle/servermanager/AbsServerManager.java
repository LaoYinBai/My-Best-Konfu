package com.bytedance.pangle.servermanager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbsServerManager extends ContentProvider {
    static final String ACTIVITY_QUERY_BINDER = "activity";
    static final String BUNDLE_BINDER = "binder";
    static final String METHOD_QUERY_BINDER = "query_binder";
    static final String PACKAGE_QUERY_BINDER = "package";
    static final String SERVICE_QUERY_BINDER = "service";

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.os.IBinder onBind(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            if (r0 == r1) goto L19
            r1 = 1984153269(0x7643c6b5, float:9.927033E32)
            if (r0 == r1) goto Lf
            goto L23
        Lf:
            java.lang.String r0 = "service"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L23
            r3 = 1
            goto L24
        L19:
            java.lang.String r0 = "package"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L23
            r3 = 0
            goto L24
        L23:
            r3 = -1
        L24:
            switch(r3) {
                case 0: goto L2e;
                case 1: goto L29;
                default: goto L27;
            }
        L27:
            r3 = 0
            return r3
        L29:
            com.bytedance.pangle.service.a.a r3 = com.bytedance.pangle.service.a.a.b()
            return r3
        L2e:
            com.bytedance.pangle.e.a r3 = com.bytedance.pangle.e.a.b()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.servermanager.AbsServerManager.onBind(java.lang.String):android.os.IBinder");
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        IBinder iBinderOnBind;
        if (!METHOD_QUERY_BINDER.equals(str) || (iBinderOnBind = onBind(str2)) == null) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(BUNDLE_BINDER, new a(iBinderOnBind));
        return bundle2;
    }
}
