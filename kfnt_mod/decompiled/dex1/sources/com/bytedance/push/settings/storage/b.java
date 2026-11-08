package com.bytedance.push.settings.storage;

import android.net.Uri;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {

    /* renamed from: a, reason: collision with root package name */
    String f4943a;

    /* renamed from: b, reason: collision with root package name */
    String f4944b;

    /* renamed from: c, reason: collision with root package name */
    String f4945c;
    String d;

    b(String str, String str2, String str3, String str4) {
        this.f4943a = str;
        this.f4944b = str2;
        this.f4945c = str3;
        this.d = str4;
    }

    public static b a(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.size() < 4) {
            return null;
        }
        return new b(pathSegments.get(0), pathSegments.get(1), pathSegments.get(2), pathSegments.get(3));
    }

    Uri b(Uri uri) {
        return uri.buildUpon().appendPath(this.f4943a).appendPath(this.f4944b).appendPath(this.f4945c).appendPath(this.d).build();
    }
}
