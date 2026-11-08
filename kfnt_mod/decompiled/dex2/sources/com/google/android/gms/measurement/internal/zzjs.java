package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
@VisibleForTesting
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzjs extends zzf {
    private final zzjr zza;
    private zzee zzb;
    private volatile Boolean zzc;
    private final zzap zzd;
    private final zzkj zze;
    private final List zzf;
    private final zzap zzg;

    protected zzjs(zzfy zzfyVar) {
        super(zzfyVar);
        this.zzf = new ArrayList();
        this.zze = new zzkj(zzfyVar.zzav());
        this.zza = new zzjr(this);
        this.zzd = new zzjc(this, zzfyVar);
        this.zzg = new zzje(this, zzfyVar);
    }

    @WorkerThread
    private final zzq zzO(boolean z) {
        Pair pairZza;
        this.zzs.zzaw();
        zzef zzefVarZzh = this.zzs.zzh();
        String str = null;
        if (z) {
            zzeo zzeoVarZzay = this.zzs.zzay();
            if (zzeoVarZzay.zzs.zzm().zzb != null && (pairZza = zzeoVarZzay.zzs.zzm().zzb.zza()) != null && pairZza != zzfd.zza) {
                str = String.valueOf(pairZza.second) + ":" + ((String) pairZza.first);
            }
        }
        return zzefVarZzh.zzj(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzP() {
        zzg();
        this.zzs.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        Iterator it = this.zzf.iterator();
        while (it.hasNext()) {
            try {
                ((Runnable) it.next()).run();
            } catch (RuntimeException e) {
                this.zzs.zzay().zzd().zzb("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zzb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzQ() {
        zzg();
        this.zze.zzb();
        zzap zzapVar = this.zzd;
        this.zzs.zzf();
        zzapVar.zzd(((Long) zzeb.zzI.zza(null)).longValue());
    }

    @WorkerThread
    private final void zzR(Runnable runnable) throws IllegalStateException {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.zzf.size();
        this.zzs.zzf();
        if (size >= 1000) {
            this.zzs.zzay().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable);
        this.zzg.zzd(60000L);
        zzr();
    }

    private final boolean zzS() {
        this.zzs.zzaw();
        return true;
    }

    static /* bridge */ /* synthetic */ void zzo(zzjs zzjsVar, ComponentName componentName) {
        zzjsVar.zzg();
        if (zzjsVar.zzb != null) {
            zzjsVar.zzb = null;
            zzjsVar.zzs.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzjsVar.zzg();
            zzjsVar.zzr();
        }
    }

    @WorkerThread
    protected final void zzA(zzaw zzawVar, String str) {
        Preconditions.checkNotNull(zzawVar);
        zzg();
        zza();
        zzS();
        zzR(new zzjh(this, true, zzO(true), this.zzs.zzi().zzo(zzawVar), zzawVar, str));
    }

    @WorkerThread
    public final void zzB(com.google.android.gms.internal.measurement.zzcf zzcfVar, zzaw zzawVar, String str) throws IllegalStateException {
        zzg();
        zza();
        if (this.zzs.zzv().zzo(12451000) == 0) {
            zzR(new zzjd(this, zzawVar, str, zzcfVar));
        } else {
            this.zzs.zzay().zzk().zza("Not bundling data. Service unavailable or out of date");
            this.zzs.zzv().zzS(zzcfVar, new byte[0]);
        }
    }

    @WorkerThread
    protected final void zzC() {
        zzg();
        zza();
        zzq zzqVarZzO = zzO(false);
        zzS();
        this.zzs.zzi().zzj();
        zzR(new zziw(this, zzqVarZzO));
    }

    @VisibleForTesting
    @WorkerThread
    final void zzD(zzee zzeeVar, AbstractSafeParcelable abstractSafeParcelable, zzq zzqVar) {
        int size;
        zzg();
        zza();
        zzS();
        this.zzs.zzf();
        int i = 0;
        int i2 = 100;
        while (i < 1001 && i2 == 100) {
            ArrayList arrayList = new ArrayList();
            List listZzi = this.zzs.zzi().zzi(100);
            if (listZzi != null) {
                arrayList.addAll(listZzi);
                size = listZzi.size();
            } else {
                size = 0;
            }
            if (abstractSafeParcelable != null && size < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i3);
                if (abstractSafeParcelable2 instanceof zzaw) {
                    try {
                        zzeeVar.zzk((zzaw) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e) {
                        this.zzs.zzay().zzd().zzb("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzlc) {
                    try {
                        zzeeVar.zzt((zzlc) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e2) {
                        this.zzs.zzay().zzd().zzb("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzac) {
                    try {
                        zzeeVar.zzn((zzac) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e3) {
                        this.zzs.zzay().zzd().zzb("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    this.zzs.zzay().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i++;
            i2 = size;
        }
    }

    @WorkerThread
    protected final void zzE(zzac zzacVar) throws IllegalStateException {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zza();
        this.zzs.zzaw();
        zzR(new zzji(this, true, zzO(true), this.zzs.zzi().zzn(zzacVar), new zzac(zzacVar), zzacVar));
    }

    @WorkerThread
    protected final void zzF(boolean z) {
        zzg();
        zza();
        if (z) {
            zzS();
            this.zzs.zzi().zzj();
        }
        if (zzM()) {
            zzR(new zzjg(this, zzO(false)));
        }
    }

    @WorkerThread
    protected final void zzG(zzik zzikVar) {
        zzg();
        zza();
        zzR(new zzja(this, zzikVar));
    }

    @WorkerThread
    public final void zzH(Bundle bundle) {
        zzg();
        zza();
        zzR(new zzjb(this, zzO(false), bundle));
    }

    @WorkerThread
    protected final void zzI() {
        zzg();
        zza();
        zzR(new zzjf(this, zzO(true)));
    }

    @VisibleForTesting
    @WorkerThread
    protected final void zzJ(zzee zzeeVar) {
        zzg();
        Preconditions.checkNotNull(zzeeVar);
        this.zzb = zzeeVar;
        zzQ();
        zzP();
    }

    @WorkerThread
    protected final void zzK(zzlc zzlcVar) {
        zzg();
        zza();
        zzS();
        zzR(new zziu(this, zzO(true), this.zzs.zzi().zzp(zzlcVar), zzlcVar));
    }

    @WorkerThread
    public final boolean zzL() {
        zzg();
        zza();
        return this.zzb != null;
    }

    @WorkerThread
    final boolean zzM() {
        zzg();
        zza();
        return !zzN() || this.zzs.zzv().zzm() >= ((Integer) zzeb.zzaf.zza(null)).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0127  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final boolean zzN() {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjs.zzN():boolean");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    final Boolean zzj() {
        return this.zzc;
    }

    @WorkerThread
    protected final void zzq() throws IllegalStateException {
        zzg();
        zza();
        zzq zzqVarZzO = zzO(true);
        this.zzs.zzi().zzk();
        zzR(new zziz(this, zzqVarZzO));
    }

    @WorkerThread
    final void zzr() {
        zzg();
        zza();
        if (zzL()) {
            return;
        }
        if (zzN()) {
            this.zza.zzc();
            return;
        }
        if (this.zzs.zzf().zzx()) {
            return;
        }
        this.zzs.zzaw();
        List<ResolveInfo> listQueryIntentServices = this.zzs.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzs.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), C.DEFAULT_BUFFER_SEGMENT_SIZE);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            this.zzs.zzay().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzaw();
        intent.setComponent(new ComponentName(contextZzau, "com.google.android.gms.measurement.AppMeasurementService"));
        this.zza.zzb(intent);
    }

    @WorkerThread
    public final void zzs() {
        zzg();
        zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzs.zzau(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    @WorkerThread
    public final void zzt(com.google.android.gms.internal.measurement.zzcf zzcfVar) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zziy(this, zzO(false), zzcfVar));
    }

    @WorkerThread
    public final void zzu(AtomicReference atomicReference) {
        zzg();
        zza();
        zzR(new zzix(this, atomicReference, zzO(false)));
    }

    @WorkerThread
    protected final void zzv(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zzjk(this, str, str2, zzO(false), zzcfVar));
    }

    @WorkerThread
    protected final void zzw(AtomicReference atomicReference, String str, String str2, String str3) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zzjj(this, atomicReference, null, str2, str3, zzO(false)));
    }

    @WorkerThread
    protected final void zzx(AtomicReference atomicReference, boolean z) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zziv(this, atomicReference, zzO(false), z));
    }

    @WorkerThread
    protected final void zzy(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zzit(this, str, str2, zzO(false), z, zzcfVar));
    }

    @WorkerThread
    protected final void zzz(AtomicReference atomicReference, String str, String str2, String str3, boolean z) throws IllegalStateException {
        zzg();
        zza();
        zzR(new zzjl(this, atomicReference, null, str2, str3, zzO(false), z));
    }
}
