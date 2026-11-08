package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c extends g {

    /* renamed from: a, reason: collision with root package name */
    private final Context f4946a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4947b;

    c(Context context, String str) {
        super(context, str);
        this.f4947b = str;
        this.f4946a = context;
    }

    @Override // com.bytedance.push.settings.storage.g, com.bytedance.push.settings.storage.i
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b() {
        return new a(this.f4946a, super.b());
    }

    private final class a implements SharedPreferences.Editor {

        /* renamed from: b, reason: collision with root package name */
        private final Context f4949b;

        /* renamed from: c, reason: collision with root package name */
        private Map<String, String> f4950c = new ConcurrentHashMap();
        private final SharedPreferences.Editor d;

        a(Context context, SharedPreferences.Editor editor) {
            this.f4949b = context;
            this.d = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.d.putString(str, str2);
            if (TextUtils.equals(c.this.a(str), str2)) {
                this.f4950c.remove(str);
            } else {
                this.f4950c.put(str, "string");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            throw new UnsupportedOperationException("不支持putStringSet");
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            this.d.putInt(str, i);
            if (i == c.this.b(str)) {
                this.f4950c.remove(str);
            } else {
                this.f4950c.put(str, "integer");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            this.d.putLong(str, j);
            if (j == c.this.c(str)) {
                this.f4950c.remove(str);
            } else {
                this.f4950c.put(str, "long");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            this.d.putFloat(str, f);
            if (f == c.this.d(str)) {
                this.f4950c.remove(str);
            } else {
                this.f4950c.put(str, "float");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.d.putBoolean(str, z);
            if (z == c.this.e(str)) {
                this.f4950c.remove(str);
            } else {
                this.f4950c.put(str, "boolean");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.d.remove(str);
            this.f4950c.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            throw new UnsupportedOperationException("不支持clear");
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            boolean zCommit = this.d.commit();
            a();
            return zCommit;
        }

        private void a() {
            for (Map.Entry<String, String> entry : this.f4950c.entrySet()) {
                PushProvider.a(this.f4949b, c.this.f4947b, entry.getKey(), entry.getValue()).run();
            }
            this.f4950c.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.d.apply();
            a();
        }
    }
}
