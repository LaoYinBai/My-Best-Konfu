package com.bytedance.push.settings.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.settings.SettingsManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    public static String f4937a;

    /* renamed from: b, reason: collision with root package name */
    public static Uri f4938b;

    /* renamed from: c, reason: collision with root package name */
    private static UriMatcher f4939c;
    private static volatile boolean d;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo != null) {
            f4937a = providerInfo.authority;
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        d = true;
        if (f4939c == null) {
            try {
                b(getContext());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean a(Context context) {
        return d || com.bytedance.common.push.b.a.a.a.a.b(context);
    }

    private static void b(Context context) throws IllegalStateException {
        if (TextUtils.isEmpty(f4937a)) {
            f4937a = a(context, PushProvider.class.getName());
        }
        if (TextUtils.isEmpty(f4937a)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        f4939c = new UriMatcher(-1);
        f4939c.addURI(f4937a, "*/*/*/*", 65536);
        f4938b = Uri.parse("content://" + f4937a);
    }

    private static boolean a() {
        return TextUtils.isEmpty(f4937a) || f4939c == null;
    }

    static Runnable a(final Context context, final String str, final String str2, final String str3) {
        return new Runnable() { // from class: com.bytedance.push.settings.storage.PushProvider.1
            @Override // java.lang.Runnable
            public void run() {
                Uri uriA = PushProvider.a(context, new b(str, str2, "val", str3));
                if (uriA == null) {
                    return;
                }
                try {
                    context.getContentResolver().notifyChange(uriA, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static synchronized Uri b(Context context, String str, String str2, String str3) {
        if (f4938b == null) {
            try {
                b(context);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            return new b(str, str2, "val", str3).b(f4938b);
        }
        return f4938b;
    }

    static synchronized Uri a(Context context, b bVar) {
        if (f4938b == null) {
            try {
                b(context);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (bVar == null) {
            return f4938b;
        }
        return bVar.b(f4938b);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd." + f4937a + ".item";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0080  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.database.Cursor query(android.net.Uri r5, java.lang.String[] r6, java.lang.String r7, java.lang.String[] r8, java.lang.String r9) {
        /*
            r4 = this;
            boolean r6 = a()
            r7 = 0
            if (r6 == 0) goto L8
            return r7
        L8:
            android.content.UriMatcher r6 = com.bytedance.push.settings.storage.PushProvider.f4939c
            int r6 = r6.match(r5)
            r8 = 65536(0x10000, float:9.1835E-41)
            if (r6 == r8) goto L13
            return r7
        L13:
            com.bytedance.push.settings.storage.b r5 = com.bytedance.push.settings.storage.b.a(r5)
            if (r5 == 0) goto Leb
            java.lang.String r6 = r5.f4943a
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto Leb
            java.lang.String r6 = r5.f4944b
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto Leb
            java.lang.String r6 = r5.d
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L33
            goto Leb
        L33:
            com.bytedance.push.settings.i r6 = com.bytedance.push.settings.SettingsManager.getStorageFactory()
            android.content.Context r7 = r4.getContext()
            java.lang.String r8 = r5.f4943a
            r9 = 1
            com.bytedance.push.settings.storage.i r6 = r6.a(r7, r9, r8)
            java.lang.String r7 = r5.d
            r8 = -1
            int r0 = r7.hashCode()
            r1 = 0
            switch(r0) {
                case -891985903: goto L76;
                case 3327612: goto L6c;
                case 64711720: goto L62;
                case 97526364: goto L58;
                case 1958052158: goto L4e;
                default: goto L4d;
            }
        L4d:
            goto L80
        L4e:
            java.lang.String r0 = "integer"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L80
            r7 = 1
            goto L81
        L58:
            java.lang.String r0 = "float"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L80
            r7 = 3
            goto L81
        L62:
            java.lang.String r0 = "boolean"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L80
            r7 = 4
            goto L81
        L6c:
            java.lang.String r0 = "long"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L80
            r7 = 2
            goto L81
        L76:
            java.lang.String r0 = "string"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L80
            r7 = 0
            goto L81
        L80:
            r7 = -1
        L81:
            switch(r7) {
                case 0: goto Ld0;
                case 1: goto Lbf;
                case 2: goto Lae;
                case 3: goto L9d;
                case 4: goto L8c;
                default: goto L84;
            }
        L84:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "unknown type"
            r5.<init>(r6)
            throw r5
        L8c:
            java.lang.String r7 = r5.f4944b
            java.lang.String r8 = r5.f4945c
            boolean r8 = java.lang.Boolean.parseBoolean(r8)
            boolean r6 = r6.a(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            goto Ld8
        L9d:
            java.lang.String r7 = r5.f4944b
            java.lang.String r8 = r5.f4945c
            float r8 = java.lang.Float.parseFloat(r8)
            float r6 = r6.a(r7, r8)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            goto Ld8
        Lae:
            java.lang.String r7 = r5.f4944b
            java.lang.String r8 = r5.f4945c
            long r2 = java.lang.Long.parseLong(r8)
            long r6 = r6.a(r7, r2)
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            goto Ld8
        Lbf:
            java.lang.String r7 = r5.f4944b
            java.lang.String r8 = r5.f4945c
            int r8 = java.lang.Integer.parseInt(r8)
            int r6 = r6.a(r7, r8)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            goto Ld8
        Ld0:
            java.lang.String r7 = r5.f4944b
            java.lang.String r8 = r5.f4945c
            java.lang.String r6 = r6.a(r7, r8)
        Ld8:
            android.database.MatrixCursor r7 = new android.database.MatrixCursor
            java.lang.String[] r8 = new java.lang.String[r9]
            java.lang.String r5 = r5.f4944b
            r8[r1] = r5
            r7.<init>(r8)
            android.database.MatrixCursor$RowBuilder r5 = r7.newRow()
            r5.add(r6)
            return r7
        Leb:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.settings.storage.PushProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SharedPreferences.Editor editorB;
        ArrayList arrayList;
        String str;
        if (a()) {
            return null;
        }
        if (f4939c.match(uri) == 65536) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                return null;
            }
            String str2 = pathSegments.get(0);
            try {
                i iVarA = SettingsManager.getStorageFactory().a(getContext(), true, str2);
                editorB = iVarA.b();
                arrayList = new ArrayList();
                for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                    Object value = entry.getValue();
                    String key = entry.getKey();
                    if (value == null) {
                        editorB.remove(key);
                        str = null;
                    } else if (value instanceof String) {
                        if (!TextUtils.equals(iVarA.a(key), (String) value)) {
                            editorB.putString(key, (String) value);
                            str = "string";
                        }
                    } else if (value instanceof Boolean) {
                        if (iVarA.e(key) != ((Boolean) value).booleanValue()) {
                            editorB.putBoolean(key, ((Boolean) value).booleanValue());
                            str = "boolean";
                        }
                    } else if (value instanceof Long) {
                        if (iVarA.c(key) != ((Long) value).longValue()) {
                            editorB.putLong(key, ((Long) value).longValue());
                            str = "long";
                        }
                    } else if (value instanceof Integer) {
                        if (iVarA.b(key) != ((Integer) value).intValue()) {
                            editorB.putInt(key, ((Integer) value).intValue());
                            str = "integer";
                        }
                    } else {
                        if (!(value instanceof Float)) {
                            throw new IllegalArgumentException("Unsupported type " + uri);
                        }
                        if (iVarA.d(key) != ((Float) value).floatValue()) {
                            editorB.putFloat(key, ((Float) value).floatValue());
                            str = "float";
                        }
                    }
                    if (str != null) {
                        arrayList.add(a(getContext(), str2, key, str));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (editorB == null) {
                return null;
            }
            editorB.apply();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            return null;
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        e eVarA = f.a(str);
        if (eVarA == null) {
            return null;
        }
        return eVarA.a(getContext(), str2, bundle);
    }

    private static String a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                    if (str.equals(providerInfo.name)) {
                        return providerInfo.authority;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context.getPackageName() + ".push.settings";
    }
}
