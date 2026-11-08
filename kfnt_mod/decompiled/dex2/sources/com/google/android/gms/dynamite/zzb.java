package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzb {

    @Nullable
    @GuardedBy("DynamiteLoaderV2ClassLoader.class")
    private static volatile ClassLoader zza;

    @Nullable
    @GuardedBy("DynamiteLoaderV2ClassLoader.class")
    private static volatile Thread zzb;

    @Nullable
    public static synchronized ClassLoader zza() {
        if (zza == null) {
            zza = zzb();
        }
        return zza;
    }

    @Nullable
    private static synchronized ClassLoader zzb() {
        ClassLoader contextClassLoader = null;
        if (zzb == null) {
            zzb = zzc();
            if (zzb == null) {
                return null;
            }
        }
        synchronized (zzb) {
            try {
                contextClassLoader = zzb.getContextClassLoader();
            } catch (SecurityException e) {
                Log.w("DynamiteLoaderV2CL", "Failed to get thread context classloader " + e.getMessage());
            }
        }
        return contextClassLoader;
    }

    @Nullable
    private static synchronized Thread zzc() {
        Thread thread;
        SecurityException e;
        ThreadGroup threadGroup;
        ThreadGroup threadGroup2 = Looper.getMainLooper().getThread().getThreadGroup();
        if (threadGroup2 == null) {
            return null;
        }
        synchronized (Void.class) {
            try {
                ThreadGroup[] threadGroupArr = new ThreadGroup[threadGroup2.activeGroupCount()];
                threadGroup2.enumerate(threadGroupArr);
                int length = threadGroupArr.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        threadGroup = null;
                        break;
                    }
                    threadGroup = threadGroupArr[i2];
                    if ("dynamiteLoader".equals(threadGroup.getName())) {
                        break;
                    }
                    i2++;
                }
                if (threadGroup == null) {
                    threadGroup = new ThreadGroup(threadGroup2, "dynamiteLoader");
                }
                Thread[] threadArr = new Thread[threadGroup.activeCount()];
                threadGroup.enumerate(threadArr);
                int length2 = threadArr.length;
                while (true) {
                    if (i >= length2) {
                        thread = null;
                        break;
                    }
                    thread = threadArr[i];
                    if ("GmsDynamite".equals(thread.getName())) {
                        break;
                    }
                    i++;
                }
                if (thread == null) {
                    try {
                        zza zzaVar = new zza(threadGroup, "GmsDynamite");
                        try {
                            zzaVar.setContextClassLoader(null);
                            zzaVar.start();
                            thread = zzaVar;
                        } catch (SecurityException e2) {
                            e = e2;
                            thread = zzaVar;
                            Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + e.getMessage());
                            return thread;
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                    }
                }
            } catch (SecurityException e4) {
                thread = null;
                e = e4;
            }
        }
        return thread;
    }
}
