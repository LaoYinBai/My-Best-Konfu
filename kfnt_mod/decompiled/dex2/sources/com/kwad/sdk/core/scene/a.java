package com.kwad.sdk.core.scene;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static a a;
    private static Object b = new Object();
    private HashMap<String, URLPackage> c = new HashMap<>();
    private HashMap<String, URLPackage> d = new HashMap<>();
    private final URLPackage e = new URLPackage("", 0);
    private final EntryPackage f = new EntryPackage("", 0);

    public static a a() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    @Nullable
    public URLPackage a(@NonNull String str) {
        URLPackage uRLPackage;
        return (TextUtils.isEmpty(str) || (uRLPackage = this.c.get(str)) == null || (uRLPackage instanceof EntryPackage)) ? this.e : uRLPackage;
    }

    public EntryPackage b(String str) {
        if (!TextUtils.isEmpty(str)) {
            URLPackage uRLPackage = this.c.get(str);
            int size = this.c.size();
            for (int i = 0; uRLPackage != null && !(uRLPackage instanceof EntryPackage) && i < size; i++) {
                String str2 = uRLPackage.identity;
                if (TextUtils.isEmpty(str2)) {
                    break;
                }
                uRLPackage = this.c.get(str2);
            }
            if (uRLPackage instanceof EntryPackage) {
                return (EntryPackage) uRLPackage;
            }
        }
        return this.f;
    }
}
