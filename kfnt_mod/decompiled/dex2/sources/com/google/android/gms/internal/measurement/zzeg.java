package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzeg extends zzka implements zzlm {
    private zzeg() {
        super(zzeh.zza);
    }

    public final int zza() {
        return ((zzeh) this.zza).zzb();
    }

    public final int zzb() {
        return ((zzeh) this.zza).zzc();
    }

    public final zzeg zzc(int i, zzei zzeiVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzeh.zzj((zzeh) this.zza, i, (zzej) zzeiVar.zzaE());
        return this;
    }

    public final zzeg zzd(int i, zzer zzerVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzeh.zzi((zzeh) this.zza, i, (zzes) zzerVar.zzaE());
        return this;
    }

    public final zzej zze(int i) {
        return ((zzeh) this.zza).zze(i);
    }

    public final zzes zzf(int i) {
        return ((zzeh) this.zza).zzf(i);
    }

    /* synthetic */ zzeg(zzef zzefVar) {
        super(zzeh.zza);
    }
}
