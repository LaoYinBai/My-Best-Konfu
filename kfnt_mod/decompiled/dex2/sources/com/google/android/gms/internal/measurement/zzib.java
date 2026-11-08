package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzib implements zzhj {

    @GuardedBy("SharedPreferencesLoader.class")
    private static final Map zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;

    @Nullable
    static zzib zza(Context context, String str, Runnable runnable) {
        zzib zzibVar;
        if (zzha.zzb()) {
            throw null;
        }
        synchronized (zzib.class) {
            zzibVar = (zzib) zza.get(null);
            if (zzibVar == null) {
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    throw null;
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    throw th;
                }
            }
        }
        return zzibVar;
    }

    static synchronized void zzc() {
        Iterator it = zza.values().iterator();
        if (it.hasNext()) {
            zzib zzibVar = (zzib) it.next();
            SharedPreferences sharedPreferences = zzibVar.zzb;
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = zzibVar.zzc;
            throw null;
        }
        zza.clear();
    }

    @Override // com.google.android.gms.internal.measurement.zzhj
    @Nullable
    public final Object zzb(String str) {
        throw null;
    }
}
