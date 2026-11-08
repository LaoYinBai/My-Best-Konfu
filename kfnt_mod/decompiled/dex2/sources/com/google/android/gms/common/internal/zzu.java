package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzu {

    @Nullable
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final boolean zzd;

    public zzu(String str, @Nullable String str2, boolean z, int i, boolean z2) {
        this.zzb = str;
        this.zza = str2;
        this.zzc = i;
        this.zzd = z2;
    }

    final int zza() {
        return this.zzc;
    }

    final String zzb() {
        return this.zzb;
    }

    @Nullable
    final String zzc() {
        return this.zza;
    }

    final boolean zzd() {
        return this.zzd;
    }
}
