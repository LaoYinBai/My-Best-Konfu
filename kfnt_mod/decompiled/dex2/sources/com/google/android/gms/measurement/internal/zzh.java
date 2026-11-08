package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzh {
    private long zzA;

    @Nullable
    private String zzB;
    private boolean zzC;
    private long zzD;
    private long zzE;
    private final zzfy zza;
    private final String zzb;

    @Nullable
    private String zzc;

    @Nullable
    private String zzd;

    @Nullable
    private String zze;

    @Nullable
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;

    @Nullable
    private String zzj;
    private long zzk;

    @Nullable
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private boolean zzp;

    @Nullable
    private String zzq;

    @Nullable
    private Boolean zzr;
    private long zzs;

    @Nullable
    private List zzt;

    @Nullable
    private String zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    @WorkerThread
    zzh(zzfy zzfyVar, String str) {
        Preconditions.checkNotNull(zzfyVar);
        Preconditions.checkNotEmpty(str);
        this.zza = zzfyVar;
        this.zzb = str;
        this.zza.zzaz().zzg();
    }

    @Nullable
    @WorkerThread
    public final String zzA() {
        this.zza.zzaz().zzg();
        return this.zze;
    }

    @Nullable
    @WorkerThread
    public final String zzB() {
        this.zza.zzaz().zzg();
        return this.zzu;
    }

    @Nullable
    @WorkerThread
    public final List zzC() {
        this.zza.zzaz().zzg();
        return this.zzt;
    }

    @WorkerThread
    public final void zzD() {
        this.zza.zzaz().zzg();
        this.zzC = false;
    }

    @WorkerThread
    public final void zzE() {
        this.zza.zzaz().zzg();
        long j = this.zzg + 1;
        if (j > 2147483647L) {
            this.zza.zzay().zzk().zzb("Bundle index overflow. appId", zzeo.zzn(this.zzb));
            j = 0;
        }
        this.zzC = true;
        this.zzg = j;
    }

    @WorkerThread
    public final void zzF(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzC |= true ^ zzg.zza(this.zzq, str);
        this.zzq = str;
    }

    @WorkerThread
    public final void zzG(boolean z) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzp != z;
        this.zzp = z;
    }

    @WorkerThread
    public final void zzH(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzc, str);
        this.zzc = str;
    }

    @WorkerThread
    public final void zzI(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzl, str);
        this.zzl = str;
    }

    @WorkerThread
    public final void zzJ(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzj, str);
        this.zzj = str;
    }

    @WorkerThread
    public final void zzK(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzk != j;
        this.zzk = j;
    }

    @WorkerThread
    public final void zzL(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzD != j;
        this.zzD = j;
    }

    @WorkerThread
    public final void zzM(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzy != j;
        this.zzy = j;
    }

    @WorkerThread
    public final void zzN(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzz != j;
        this.zzz = j;
    }

    @WorkerThread
    public final void zzO(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzx != j;
        this.zzx = j;
    }

    @WorkerThread
    public final void zzP(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzw != j;
        this.zzw = j;
    }

    @WorkerThread
    public final void zzQ(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzA != j;
        this.zzA = j;
    }

    @WorkerThread
    public final void zzR(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzv != j;
        this.zzv = j;
    }

    @WorkerThread
    public final void zzS(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzn != j;
        this.zzn = j;
    }

    @WorkerThread
    public final void zzT(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzs != j;
        this.zzs = j;
    }

    @WorkerThread
    public final void zzU(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzE != j;
        this.zzE = j;
    }

    @WorkerThread
    public final void zzV(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzf, str);
        this.zzf = str;
    }

    @WorkerThread
    public final void zzW(@Nullable String str) {
        this.zza.zzaz().zzg();
        if (true == TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzC |= true ^ zzg.zza(this.zzd, str);
        this.zzd = str;
    }

    @WorkerThread
    public final void zzX(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzm != j;
        this.zzm = j;
    }

    @WorkerThread
    public final void zzY(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzB, str);
        this.zzB = str;
    }

    @WorkerThread
    public final void zzZ(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzi != j;
        this.zzi = j;
    }

    @WorkerThread
    public final long zza() {
        this.zza.zzaz().zzg();
        return 0L;
    }

    @WorkerThread
    public final void zzaa(long j) {
        Preconditions.checkArgument(j >= 0);
        this.zza.zzaz().zzg();
        this.zzC |= this.zzg != j;
        this.zzg = j;
    }

    @WorkerThread
    public final void zzab(long j) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzh != j;
        this.zzh = j;
    }

    @WorkerThread
    public final void zzac(boolean z) {
        this.zza.zzaz().zzg();
        this.zzC |= this.zzo != z;
        this.zzo = z;
    }

    @WorkerThread
    public final void zzad(@Nullable Boolean bool) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzr, bool);
        this.zzr = bool;
    }

    @WorkerThread
    public final void zzae(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zze, str);
        this.zze = str;
    }

    @WorkerThread
    public final void zzaf(@Nullable List list) {
        this.zza.zzaz().zzg();
        if (zzg.zza(this.zzt, list)) {
            return;
        }
        this.zzC = true;
        this.zzt = list != null ? new ArrayList(list) : null;
    }

    @WorkerThread
    public final void zzag(@Nullable String str) {
        this.zza.zzaz().zzg();
        this.zzC |= !zzg.zza(this.zzu, str);
        this.zzu = str;
    }

    @WorkerThread
    public final boolean zzah() {
        this.zza.zzaz().zzg();
        return this.zzp;
    }

    @WorkerThread
    public final boolean zzai() {
        this.zza.zzaz().zzg();
        return this.zzo;
    }

    @WorkerThread
    public final boolean zzaj() {
        this.zza.zzaz().zzg();
        return this.zzC;
    }

    @WorkerThread
    public final long zzb() {
        this.zza.zzaz().zzg();
        return this.zzk;
    }

    @WorkerThread
    public final long zzc() {
        this.zza.zzaz().zzg();
        return this.zzD;
    }

    @WorkerThread
    public final long zzd() {
        this.zza.zzaz().zzg();
        return this.zzy;
    }

    @WorkerThread
    public final long zze() {
        this.zza.zzaz().zzg();
        return this.zzz;
    }

    @WorkerThread
    public final long zzf() {
        this.zza.zzaz().zzg();
        return this.zzx;
    }

    @WorkerThread
    public final long zzg() {
        this.zza.zzaz().zzg();
        return this.zzw;
    }

    @WorkerThread
    public final long zzh() {
        this.zza.zzaz().zzg();
        return this.zzA;
    }

    @WorkerThread
    public final long zzi() {
        this.zza.zzaz().zzg();
        return this.zzv;
    }

    @WorkerThread
    public final long zzj() {
        this.zza.zzaz().zzg();
        return this.zzn;
    }

    @WorkerThread
    public final long zzk() {
        this.zza.zzaz().zzg();
        return this.zzs;
    }

    @WorkerThread
    public final long zzl() {
        this.zza.zzaz().zzg();
        return this.zzE;
    }

    @WorkerThread
    public final long zzm() {
        this.zza.zzaz().zzg();
        return this.zzm;
    }

    @WorkerThread
    public final long zzn() {
        this.zza.zzaz().zzg();
        return this.zzi;
    }

    @WorkerThread
    public final long zzo() {
        this.zza.zzaz().zzg();
        return this.zzg;
    }

    @WorkerThread
    public final long zzp() {
        this.zza.zzaz().zzg();
        return this.zzh;
    }

    @Nullable
    @WorkerThread
    public final Boolean zzq() {
        this.zza.zzaz().zzg();
        return this.zzr;
    }

    @Nullable
    @WorkerThread
    public final String zzr() {
        this.zza.zzaz().zzg();
        return this.zzq;
    }

    @Nullable
    @WorkerThread
    public final String zzs() {
        this.zza.zzaz().zzg();
        String str = this.zzB;
        zzY(null);
        return str;
    }

    @WorkerThread
    public final String zzt() {
        this.zza.zzaz().zzg();
        return this.zzb;
    }

    @Nullable
    @WorkerThread
    public final String zzu() {
        this.zza.zzaz().zzg();
        return this.zzc;
    }

    @Nullable
    @WorkerThread
    public final String zzv() {
        this.zza.zzaz().zzg();
        return this.zzl;
    }

    @Nullable
    @WorkerThread
    public final String zzw() {
        this.zza.zzaz().zzg();
        return this.zzj;
    }

    @Nullable
    @WorkerThread
    public final String zzx() {
        this.zza.zzaz().zzg();
        return this.zzf;
    }

    @Nullable
    @WorkerThread
    public final String zzy() {
        this.zza.zzaz().zzg();
        return this.zzd;
    }

    @Nullable
    @WorkerThread
    public final String zzz() {
        this.zza.zzaz().zzg();
        return this.zzB;
    }
}
