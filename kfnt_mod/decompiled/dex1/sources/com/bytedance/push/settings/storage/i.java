package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface i {
    float a(String str, float f);

    int a(String str, int i);

    long a(String str, long j);

    String a(String str);

    String a(String str, String str2);

    void a(Context context, String str, String str2, com.bytedance.push.settings.a aVar);

    void a(com.bytedance.push.settings.a aVar);

    boolean a(String str, boolean z);

    int b(String str);

    SharedPreferences.Editor b();

    long c(String str);

    float d(String str);

    boolean e(String str);

    boolean f(String str);
}
