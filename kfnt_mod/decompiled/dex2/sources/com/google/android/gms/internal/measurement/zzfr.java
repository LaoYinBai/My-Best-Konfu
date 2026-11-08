package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzfr extends zzka implements zzlm {
    private zzfr() {
        super(zzfs.zza);
    }

    public final int zza() {
        return ((zzfs) this.zza).zzb();
    }

    public final long zzb() {
        return ((zzfs) this.zza).zzc();
    }

    public final long zzc() {
        return ((zzfs) this.zza).zzd();
    }

    public final zzfr zzd(Iterable iterable) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzm((zzfs) this.zza, iterable);
        return this;
    }

    public final zzfr zze(zzfv zzfvVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzk((zzfs) this.zza, (zzfw) zzfvVar.zzaE());
        return this;
    }

    public final zzfr zzf(zzfw zzfwVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzk((zzfs) this.zza, zzfwVar);
        return this;
    }

    public final zzfr zzg() {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        ((zzfs) this.zza).zzf = zzfs.zzbD();
        return this;
    }

    public final zzfr zzh(int i) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzo((zzfs) this.zza, i);
        return this;
    }

    public final zzfr zzi(String str) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzp((zzfs) this.zza, str);
        return this;
    }

    public final zzfr zzj(int i, zzfv zzfvVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzj((zzfs) this.zza, i, (zzfw) zzfvVar.zzaE());
        return this;
    }

    public final zzfr zzk(int i, zzfw zzfwVar) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzj((zzfs) this.zza, i, zzfwVar);
        return this;
    }

    public final zzfr zzl(long j) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzr((zzfs) this.zza, j);
        return this;
    }

    public final zzfr zzm(long j) {
        if (this.zzb) {
            zzaI();
            this.zzb = false;
        }
        zzfs.zzq((zzfs) this.zza, j);
        return this;
    }

    public final zzfw zzn(int i) {
        return ((zzfs) this.zza).zzg(i);
    }

    public final String zzo() {
        return ((zzfs) this.zza).zzh();
    }

    public final List zzp() {
        return Collections.unmodifiableList(((zzfs) this.zza).zzi());
    }

    public final boolean zzq() {
        return ((zzfs) this.zza).zzu();
    }

    /* synthetic */ zzfr(zzfj zzfjVar) {
        super(zzfs.zza);
    }
}
