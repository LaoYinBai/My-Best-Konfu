package com.kwad.sdk.api.loader;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import dalvik.system.DexClassLoader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class d {
    private static final List<String> a = new ArrayList();

    private static class a extends DexClassLoader {
        public a(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        private boolean a(String str) {
            return !TextUtils.isEmpty(str) && str.startsWith("com.kwad.sdk.api");
        }

        @Override // java.lang.ClassLoader
        protected Class<?> loadClass(String str, boolean z) {
            if (a(str)) {
                return getParent().loadClass(str);
            }
            Class<?> clsFindLoadedClass = findLoadedClass(str);
            if (clsFindLoadedClass != null) {
                return clsFindLoadedClass;
            }
            try {
                clsFindLoadedClass = findClass(str);
            } catch (ClassNotFoundException unused) {
            }
            return clsFindLoadedClass != null ? clsFindLoadedClass : super.loadClass(str, z);
        }
    }

    static {
        a.add("com.kwad.sdk");
        a.add("com.ksad");
        a.add("com.kwai");
        a.add("kwad.support");
        a.add("android.support.rastermill");
    }

    @NonNull
    static ClassLoader a(Context context, String str, String str2, String str3) {
        return new a(str, str2, str3, context.getClassLoader());
    }
}
