package com.bytedance.sdk.adtnc.e;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.sdk.adtnc.g.d;
import com.google.android.gms.actions.SearchIntents;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    public static Uri f5102a;

    /* renamed from: b, reason: collision with root package name */
    private static String f5103b;

    /* renamed from: c, reason: collision with root package name */
    private static UriMatcher f5104c;
    private static C0155b f;
    private SharedPreferences d;
    private Map<String, Object> e = new ConcurrentHashMap();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        Context f5105a;

        /* renamed from: b, reason: collision with root package name */
        private ContentValues f5106b;

        private a(Context context) {
            this.f5106b = new ContentValues();
            this.f5105a = context.getApplicationContext();
        }

        public a a(String str, float f) {
            this.f5106b.put(str, Float.valueOf(f));
            return this;
        }

        public a a(String str, int i) {
            this.f5106b.put(str, Integer.valueOf(i));
            return this;
        }

        public a a(String str, long j) {
            this.f5106b.put(str, Long.valueOf(j));
            return this;
        }

        public a a(String str, String str2) {
            this.f5106b.put(str, str2);
            return this;
        }

        public a a(String str, boolean z) {
            this.f5106b.put(str, Boolean.valueOf(z));
            return this;
        }

        public synchronized void a() {
            try {
                this.f5105a.getContentResolver().insert(b.a(this.f5105a, "key", "type"), this.f5106b);
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized void b() {
            a();
        }
    }

    /* renamed from: com.bytedance.sdk.adtnc.e.b$b, reason: collision with other inner class name */
    public static class C0155b {

        /* renamed from: a, reason: collision with root package name */
        private Context f5107a;

        private C0155b(Context context) {
            this.f5107a = context.getApplicationContext();
        }

        public int a(String str, int i) {
            try {
                return b.b(this.f5107a.getContentResolver().query(b.a(this.f5107a, str, "integer"), null, null, null, null), i);
            } catch (Throwable unused) {
                return i;
            }
        }

        public String a(String str, String str2) {
            try {
                return b.b(this.f5107a.getContentResolver().query(b.a(this.f5107a, str, "string"), null, null, null, null), str2);
            } catch (Throwable unused) {
                return str2;
            }
        }
    }

    private synchronized SharedPreferences a() {
        if (this.d != null) {
            return this.d;
        }
        this.d = getContext().getApplicationContext().getSharedPreferences("ad_tnc_multi_process_config", Build.VERSION.SDK_INT >= 11 ? 4 : 0);
        return this.d;
    }

    public static final synchronized Uri a(Context context, String str, String str2) {
        if (f5102a == null) {
            try {
                d(context);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return f5102a.buildUpon().appendPath(str).appendPath(str2).build();
    }

    public static a a(Context context) {
        return new a(context);
    }

    private void a(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(Cursor cursor, int i) {
        if (cursor == null) {
            return i;
        }
        try {
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return i;
    }

    public static synchronized C0155b b(Context context) {
        if (f == null) {
            f = new C0155b(context);
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Cursor cursor, String str) {
        if (cursor == null) {
            return str;
        }
        try {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return str;
    }

    private void b() {
        SharedPreferences sharedPreferencesA = a();
        if (sharedPreferencesA == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : sharedPreferencesA.getAll().entrySet()) {
            this.e.put(entry.getKey(), entry.getValue());
        }
    }

    private static String c(Context context) {
        ProviderInfo[] providerInfoArrD;
        if (TextUtils.isEmpty(f5103b) && (providerInfoArrD = d.d(context)) != null && providerInfoArrD.length > 0) {
            int length = providerInfoArrD.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                ProviderInfo providerInfo = providerInfoArrD[i];
                if ("com.bytedance.sdk.adtnc.sdk.provider.AdTNCSharedProvider".equals(providerInfo.name)) {
                    f5103b = providerInfo.authority;
                    break;
                }
                i++;
            }
        }
        return f5103b;
    }

    private static void d(Context context) {
        String strC = c(context);
        f5104c = new UriMatcher(-1);
        f5104c.addURI(strC, "*/*", 65536);
        f5102a = Uri.parse("content://" + strC);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (f5104c.match(uri) != 65536) {
            throw new IllegalArgumentException("Unsupported uri " + uri);
        }
        try {
            a().edit().clear().commit();
            this.e.clear();
            a(a(getContext(), "key", "type"));
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd." + c(getContext()) + ".item";
    }

    @Override // android.content.ContentProvider
    @SuppressLint({"NewApi"})
    public Uri insert(Uri uri, ContentValues contentValues) {
        SharedPreferences.Editor editorEdit;
        Uri uriA;
        com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "insert", "start");
        if (f5104c.match(uri) != 65536) {
            throw new IllegalArgumentException("Unsupported uri " + uri);
        }
        try {
            editorEdit = null;
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "insert", "key =", key, "value =", value);
                boolean z = true;
                if (value == null) {
                    this.e.remove(key);
                } else {
                    Object obj = this.e.get(key);
                    if (obj == null || !obj.equals(value)) {
                        this.e.put(key, value);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    if (editorEdit == null) {
                        editorEdit = a().edit();
                    }
                    if (value == null) {
                        editorEdit.remove(key);
                    } else {
                        if (value instanceof String) {
                            editorEdit.putString(key, (String) value);
                            uriA = a(getContext(), key, "string");
                        } else if (value instanceof Boolean) {
                            editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                            uriA = a(getContext(), key, "boolean");
                        } else if (value instanceof Long) {
                            editorEdit.putLong(key, ((Long) value).longValue());
                            uriA = a(getContext(), key, "long");
                        } else if (value instanceof Integer) {
                            editorEdit.putInt(key, ((Integer) value).intValue());
                            uriA = a(getContext(), key, "integer");
                        } else {
                            if (!(value instanceof Float)) {
                                throw new IllegalArgumentException("Unsupported type " + uri);
                            }
                            editorEdit.putFloat(key, ((Float) value).floatValue());
                            uriA = a(getContext(), key, "float");
                        }
                        a(uriA);
                    }
                }
            }
        } catch (Exception e) {
            com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "insert", (Object) "insert error", (Throwable) e);
        }
        if (editorEdit == null) {
            return null;
        }
        editorEdit.apply();
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "onCreate", "init start");
        if (f5104c != null) {
            com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "onCreate", "sMatcher is null");
            return true;
        }
        try {
            d(getContext());
            b();
            com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "onCreate", "init success");
            return true;
        } catch (Exception e) {
            com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", "onCreate", (Object) "init error", (Throwable) e);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor;
        com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", SearchIntents.EXTRA_QUERY, "start");
        if (f5104c.match(uri) != 65536) {
            throw new IllegalArgumentException("Unsupported uri " + uri);
        }
        MatrixCursor matrixCursor2 = null;
        try {
            if (!"all".equals(uri.getPathSegments().get(1))) {
                String str3 = uri.getPathSegments().get(0);
                if (!this.e.containsKey(str3)) {
                    return null;
                }
                MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{str3});
                try {
                    Object objValueOf = this.e.get(str3);
                    MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor3.newRow();
                    if (objValueOf instanceof Boolean) {
                        objValueOf = Integer.valueOf(((Boolean) objValueOf).booleanValue() ? 1 : 0);
                    }
                    rowBuilderNewRow.add(objValueOf);
                    matrixCursor = matrixCursor3;
                    matrixCursor2 = rowBuilderNewRow;
                    break;
                    break;
                } catch (Exception e) {
                    e = e;
                    matrixCursor = matrixCursor3;
                    com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", SearchIntents.EXTRA_QUERY, (Object) "query error", (Throwable) e);
                    return matrixCursor;
                }
            }
            Map<String, ?> all = a().getAll();
            matrixCursor = new MatrixCursor(new String[]{"key_column", "value_column", "type_column"});
            try {
                Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    matrixCursor2 = zHasNext;
                    if (zHasNext != 0) {
                        Map.Entry<String, ?> next = it.next();
                        String key = next.getKey();
                        Object value = next.getValue();
                        MatrixCursor.RowBuilder rowBuilderNewRow2 = matrixCursor.newRow();
                        String str4 = "string";
                        if (value instanceof String) {
                            str4 = "string";
                        } else if (value instanceof Boolean) {
                            str4 = "boolean";
                            value = Integer.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
                        } else if (value instanceof Integer) {
                            str4 = "integer";
                        } else if (value instanceof Long) {
                            str4 = "long";
                        } else if (value instanceof Float) {
                            str4 = "float";
                        }
                        rowBuilderNewRow2.add(key);
                        rowBuilderNewRow2.add(value);
                        rowBuilderNewRow2.add(str4);
                    }
                    break;
                }
            } catch (Exception e2) {
                e = e2;
                com.bytedance.sdk.adtnc.g.b.a("MultiProcessAdTNCSharedProvider", SearchIntents.EXTRA_QUERY, (Object) "query error", (Throwable) e);
                return matrixCursor;
            }
        } catch (Exception e3) {
            e = e3;
            matrixCursor = matrixCursor2;
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
