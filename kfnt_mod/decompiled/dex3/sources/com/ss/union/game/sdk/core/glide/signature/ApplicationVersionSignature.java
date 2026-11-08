package com.ss.union.game.sdk.core.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ApplicationVersionSignature {
    private static final String a = "AppVersionSignature";
    private static final ConcurrentMap<String, Key> b = new ConcurrentHashMap();

    public static Key obtain(Context context) {
        String packageName = context.getPackageName();
        Key key = b.get(packageName);
        if (key != null) {
            return key;
        }
        Key keyA = a(context);
        Key keyPutIfAbsent = b.putIfAbsent(packageName, keyA);
        return keyPutIfAbsent == null ? keyA : keyPutIfAbsent;
    }

    static void a() {
        b.clear();
    }

    private static Key a(Context context) {
        return new ObjectKey(a(b(context)));
    }

    private static String a(PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    private static PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(a, "Cannot resolve info for" + context.getPackageName(), e);
            return null;
        }
    }

    private ApplicationVersionSignature() {
    }
}
