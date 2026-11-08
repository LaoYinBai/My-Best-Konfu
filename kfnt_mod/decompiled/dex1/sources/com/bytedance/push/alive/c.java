package com.bytedance.push.alive;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface c {
    void a(Context context);

    void a(Context context, Handler handler);

    boolean a(Context context, String str) throws PackageManager.NameNotFoundException;

    boolean b();
}
