package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzhd extends ContentObserver {
    final /* synthetic */ zzhe zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzhd(zzhe zzheVar, Handler handler) {
        super(null);
        this.zza = zzheVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}
