package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzny;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzz extends zzy {
    final /* synthetic */ zzaa zza;
    private final com.google.android.gms.internal.measurement.zzes zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(zzaa zzaaVar, String str, int i, com.google.android.gms.internal.measurement.zzes zzesVar) {
        super(str, i);
        this.zza = zzaaVar;
        this.zzh = zzesVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final int zza() {
        return this.zzh.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzc() {
        return true;
    }

    final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzgl zzglVar, boolean z) {
        zzny.zzc();
        boolean zZzs = this.zza.zzs.zzf().zzs(this.zzb, zzeb.zzU);
        boolean zZzg = this.zzh.zzg();
        boolean zZzh = this.zzh.zzh();
        boolean zZzi = this.zzh.zzi();
        boolean z2 = zZzg || zZzh || zZzi;
        Boolean boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        boolZzj = null;
        if (z && !z2) {
            this.zza.zzs.zzay().zzj().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), this.zzh.zzj() ? Integer.valueOf(this.zzh.zza()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzel zzelVarZzb = this.zzh.zzb();
        boolean zZzg2 = zzelVarZzb.zzg();
        if (zzglVar.zzr()) {
            if (zzelVarZzb.zzi()) {
                boolZzj = zzj(zzh(zzglVar.zzb(), zzelVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzs.zzay().zzk().zzb("No number filter for long property. property", this.zza.zzs.zzj().zzf(zzglVar.zzf()));
            }
        } else if (zzglVar.zzq()) {
            if (zzelVarZzb.zzi()) {
                boolZzj = zzj(zzg(zzglVar.zza(), zzelVarZzb.zzc()), zZzg2);
            } else {
                this.zza.zzs.zzay().zzk().zzb("No number filter for double property. property", this.zza.zzs.zzj().zzf(zzglVar.zzf()));
            }
        } else if (!zzglVar.zzt()) {
            this.zza.zzs.zzay().zzk().zzb("User property has no value, property", this.zza.zzs.zzj().zzf(zzglVar.zzf()));
        } else if (zzelVarZzb.zzk()) {
            boolZzj = zzj(zzf(zzglVar.zzg(), zzelVarZzb.zzd(), this.zza.zzs.zzay()), zZzg2);
        } else if (!zzelVarZzb.zzi()) {
            this.zza.zzs.zzay().zzk().zzb("No string or number filter defined. property", this.zza.zzs.zzj().zzf(zzglVar.zzf()));
        } else if (zzlb.zzx(zzglVar.zzg())) {
            boolZzj = zzj(zzi(zzglVar.zzg(), zzelVarZzb.zzc()), zZzg2);
        } else {
            this.zza.zzs.zzay().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.zza.zzs.zzj().zzf(zzglVar.zzf()), zzglVar.zzg());
        }
        this.zza.zzs.zzay().zzj().zzb("Property filter result", boolZzj == null ? "null" : boolZzj);
        if (boolZzj == null) {
            return false;
        }
        this.zzd = true;
        if (zZzi && !boolZzj.booleanValue()) {
            return true;
        }
        if (!z || this.zzh.zzg()) {
            this.zze = boolZzj;
        }
        if (boolZzj.booleanValue() && z2 && zzglVar.zzs()) {
            long jZzc = zzglVar.zzc();
            if (l != null) {
                jZzc = l.longValue();
            }
            if (zZzs && this.zzh.zzg() && !this.zzh.zzh() && l2 != null) {
                jZzc = l2.longValue();
            }
            if (this.zzh.zzh()) {
                this.zzg = Long.valueOf(jZzc);
            } else {
                this.zzf = Long.valueOf(jZzc);
            }
        }
        return true;
    }
}
