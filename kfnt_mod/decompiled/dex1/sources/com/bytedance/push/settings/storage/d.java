package com.bytedance.push.settings.storage;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements i {

    /* renamed from: a, reason: collision with root package name */
    private final Context f4951a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4952b;

    /* renamed from: c, reason: collision with root package name */
    private Map<com.bytedance.push.settings.a, ContentObserver> f4953c = new ConcurrentHashMap();

    d(Context context, String str) {
        this.f4951a = context;
        this.f4952b = str;
    }

    @Override // com.bytedance.push.settings.storage.i
    public String a(String str) {
        return a(str, (String) null);
    }

    @Override // com.bytedance.push.settings.storage.i
    public String a(String str, String str2) {
        try {
            return a(this.f4951a.getContentResolver().query(a(this.f4951a, this.f4952b, str, str2, "string"), null, null, null, null), str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public int b(String str) {
        return a(str, 0);
    }

    @Override // com.bytedance.push.settings.storage.i
    public int a(String str, int i) {
        try {
            return a(this.f4951a.getContentResolver().query(a(this.f4951a, this.f4952b, str, String.valueOf(i), "integer"), null, null, null, null), i);
        } catch (Throwable unused) {
            return i;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public long c(String str) {
        return a(str, 0L);
    }

    @Override // com.bytedance.push.settings.storage.i
    public long a(String str, long j) {
        try {
            return a(this.f4951a.getContentResolver().query(a(this.f4951a, this.f4952b, str, String.valueOf(j), "long"), null, null, null, null), j);
        } catch (Throwable unused) {
            return j;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public float d(String str) {
        return a(str, 0.0f);
    }

    @Override // com.bytedance.push.settings.storage.i
    public float a(String str, float f) {
        try {
            return a(this.f4951a.getContentResolver().query(a(this.f4951a, this.f4952b, str, String.valueOf(f), "float"), null, null, null, null), f);
        } catch (Throwable unused) {
            return f;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean e(String str) {
        return a(str, false);
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean a(String str, boolean z) {
        try {
            return a(this.f4951a.getContentResolver().query(a(this.f4951a, this.f4952b, str, String.valueOf(z), "boolean"), null, null, null, null), z);
        } catch (Throwable unused) {
            return z;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean f(String str) {
        try {
            return com.bytedance.push.settings.storage.a.a(this.f4951a.getContentResolver().call(b(this.f4951a, null), "_contains", (String) null, com.bytedance.push.settings.storage.a.a(this.f4952b, str)));
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public SharedPreferences.Editor b() {
        return new a(this.f4951a, this.f4952b, new j() { // from class: com.bytedance.push.settings.storage.d.1
            @Override // com.bytedance.push.settings.storage.j
            public Uri a(Context context, b bVar) {
                return d.b(context, bVar);
            }
        });
    }

    @Override // com.bytedance.push.settings.storage.i
    public void a(Context context, String str, String str2, final com.bytedance.push.settings.a aVar) {
        Uri uriB;
        if (aVar == null || (uriB = PushProvider.b(context, this.f4952b, str, str2)) == null) {
            return;
        }
        ContentObserver contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.bytedance.push.settings.storage.d.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                aVar.a();
            }
        };
        context.getContentResolver().registerContentObserver(uriB, true, contentObserver);
        this.f4953c.put(aVar, contentObserver);
    }

    @Override // com.bytedance.push.settings.storage.i
    public void a(com.bytedance.push.settings.a aVar) {
        ContentObserver contentObserverRemove;
        if (aVar == null || (contentObserverRemove = this.f4953c.remove(aVar)) == null) {
            return;
        }
        this.f4951a.getContentResolver().unregisterContentObserver(contentObserverRemove);
    }

    private static String a(Cursor cursor, String str) {
        if (cursor == null) {
            return str;
        }
        try {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
        } catch (Throwable unused) {
        }
        a(cursor);
        return str;
    }

    private static boolean a(Cursor cursor, boolean z) {
        try {
            if (cursor == null) {
                return z;
            }
            try {
                if (cursor.moveToFirst()) {
                    z = cursor.getInt(0) > 0;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return z;
        } finally {
            a(cursor);
        }
    }

    private static int a(Cursor cursor, int i) {
        if (cursor == null) {
            return i;
        }
        try {
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
        } catch (Throwable unused) {
        }
        a(cursor);
        return i;
    }

    private static long a(Cursor cursor, long j) {
        if (cursor == null) {
            return j;
        }
        try {
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
            }
        } catch (Throwable unused) {
        }
        a(cursor);
        return j;
    }

    private static float a(Cursor cursor, float f) {
        if (cursor == null) {
            return f;
        }
        try {
            if (cursor.moveToFirst()) {
                f = cursor.getFloat(0);
            }
        } catch (Throwable unused) {
        }
        a(cursor);
        return f;
    }

    private Uri a(Context context, String str, String str2, String str3, String str4) {
        return b(context, new b(str, str2, str3, str4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized Uri b(Context context, b bVar) {
        return PushProvider.a(context, bVar);
    }

    private static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    private static final class a implements SharedPreferences.Editor {

        /* renamed from: a, reason: collision with root package name */
        private final j f4957a;

        /* renamed from: b, reason: collision with root package name */
        private final String f4958b;

        /* renamed from: c, reason: collision with root package name */
        private Context f4959c;
        private ContentValues d = new ContentValues();

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return false;
        }

        a(Context context, String str, j jVar) {
            this.f4959c = context.getApplicationContext();
            this.f4957a = jVar;
            this.f4958b = str;
        }

        @Override // android.content.SharedPreferences.Editor
        public synchronized void apply() {
            try {
                this.f4959c.getContentResolver().insert(this.f4957a.a(this.f4959c, new b(this.f4958b, "key", "val", "type")), this.d);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a putString(String str, String str2) {
            this.d.put(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            throw new UnsupportedOperationException("不支持putStringSet");
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a putLong(String str, long j) {
            this.d.put(str, Long.valueOf(j));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a putBoolean(String str, boolean z) {
            this.d.put(str, Boolean.valueOf(z));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a putInt(String str, int i) {
            this.d.put(str, Integer.valueOf(i));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a putFloat(String str, float f) {
            this.d.put(str, Float.valueOf(f));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a remove(String str) {
            this.d.putNull(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a clear() {
            throw new UnsupportedOperationException("不支持clear");
        }
    }
}
