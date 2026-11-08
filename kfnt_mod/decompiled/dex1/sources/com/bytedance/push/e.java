package com.bytedance.push;

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.bytepoet.push.BytePoetConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class e implements com.ss.android.pushmanager.b {

    /* renamed from: a, reason: collision with root package name */
    private final String f4778a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f4779b;

    e(boolean z, String str) {
        this.f4779b = z;
        this.f4778a = str;
    }

    void a(Application application) {
        String packageName = application.getPackageName();
        if (this.f4779b && !e()) {
            com.bytedance.push.utils.e.a("init", "i18n version，configuration not exist。ignore!");
        } else {
            if (!TextUtils.equals(packageName, BytePoetConfig.PACKAGE_NAME)) {
                throw new IllegalArgumentException("packageName is different between configuration");
            }
            if (BytePoetConfig.SUPPORT_SDK_VERSION < 1) {
                throw new IllegalArgumentException("configuration version mismatch，please go to [https://appcloud.bytedance.net/client-sdk/config] and generate new configuration again");
            }
        }
    }

    private boolean e() throws ClassNotFoundException {
        try {
            Class.forName("com.bytedance.bytepoet.push.BytePoetConfig");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public Pair<String, String> a() {
        return new Pair<>(Key.MI_PUSH_APP_ID, Key.MI_PUSH_APP_KEY);
    }

    public Pair<String, String> b() {
        return new Pair<>(Key.MZ_PUSH_APP_ID, Key.MZ_PUSH_APP_KEY);
    }

    public Pair<String, String> c() {
        return new Pair<>(Key.OPPO_PUSH_APP_KEY, Key.OPPO_PUSH_APP_SECRET);
    }

    public com.ss.android.push.b<String, String, String> d() {
        return new com.ss.android.push.b<String, String, String>() { // from class: com.bytedance.push.e.1
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String f() {
                return Key.UMENG_APP_KEY;
            }

            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public String e() {
                return Key.UMENG_MESSAGE_SECRET;
            }

            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public String d() {
                return e.this.f4778a;
            }
        };
    }
}
