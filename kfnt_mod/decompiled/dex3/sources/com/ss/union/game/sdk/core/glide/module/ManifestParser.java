package com.ss.union.game.sdk.core.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ManifestParser {
    private static final String a = "ManifestParser";
    private static final String b = "GlideModule";
    private final Context c;

    public ManifestParser(Context context) {
        this.c = context;
    }

    public List<GlideModule> parse() throws PackageManager.NameNotFoundException {
        if (Log.isLoggable(a, 3)) {
            Log.d(a, "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.c.getPackageManager().getApplicationInfo(this.c.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "Got null app info metadata");
                }
                return arrayList;
            }
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Got app info metadata: " + applicationInfo.metaData);
            }
            for (String str : applicationInfo.metaData.keySet()) {
                if (b.equals(applicationInfo.metaData.get(str))) {
                    arrayList.add(a(str));
                    if (Log.isLoggable(a, 3)) {
                        Log.d(a, "Loaded Glide module: " + str);
                    }
                }
            }
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Finished loading Glide modules");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static GlideModule a(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName(str);
            Object objNewInstance = null;
            try {
                objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (IllegalAccessException e) {
                a(cls, e);
            } catch (InstantiationException e2) {
                a(cls, e2);
            } catch (NoSuchMethodException e3) {
                a(cls, e3);
            } catch (InvocationTargetException e4) {
                a(cls, e4);
            }
            if (!(objNewInstance instanceof GlideModule)) {
                throw new RuntimeException("Expected instanceof GlideModule, but found: " + objNewInstance);
            }
            return (GlideModule) objNewInstance;
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void a(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }
}
