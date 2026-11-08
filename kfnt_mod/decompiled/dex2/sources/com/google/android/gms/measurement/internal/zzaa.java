package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzaa extends zzkn {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzkz zzkzVar) {
        super(zzkzVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzuVar.zze.get(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:397:0x09f1, code lost:
    
        r6 = r59.zzs.zzay().zzk();
        r8 = com.google.android.gms.measurement.internal.zzeo.zzn(r59.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x0a05, code lost:
    
        if (r7.zzj() == false) goto L400;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0a07, code lost:
    
        r15 = java.lang.Integer.valueOf(r7.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0a10, code lost:
    
        r15 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0a11, code lost:
    
        r6.zzc("Invalid property filter ID. appId, id", r8, java.lang.String.valueOf(r15));
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x029c A[PHI: r0 r5
  0x029c: PHI (r0v64 java.util.Map) = (r0v46 java.util.Map), (r0v67 java.util.Map), (r0v40 java.util.Map) binds: [B:116:0x02c9, B:105:0x02a4, B:102:0x029a] A[DONT_GENERATE, DONT_INLINE]
  0x029c: PHI (r5v29 android.database.Cursor) = (r5v5 android.database.Cursor), (r5v30 android.database.Cursor), (r5v30 android.database.Cursor) binds: [B:116:0x02c9, B:105:0x02a4, B:102:0x029a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0735 A[PHI: r0 r15 r58
  0x0735: PHI (r0v135 java.util.Map) = (r0v138 java.util.Map), (r0v145 java.util.Map) binds: [B:289:0x075f, B:276:0x0733] A[DONT_GENERATE, DONT_INLINE]
  0x0735: PHI (r15v22 android.database.Cursor) = (r15v23 android.database.Cursor), (r15v28 android.database.Cursor) binds: [B:289:0x075f, B:276:0x0733] A[DONT_GENERATE, DONT_INLINE]
  0x0735: PHI (r58v3 com.google.android.gms.measurement.internal.zzw) = (r58v4 com.google.android.gms.measurement.internal.zzw), (r58v7 com.google.android.gms.measurement.internal.zzw) binds: [B:289:0x075f, B:276:0x0733] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0769  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x077d  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x080b  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x08cd A[PHI: r0 r15
  0x08cd: PHI (r0v100 java.util.Map) = (r0v103 java.util.Map), (r0v111 java.util.Map) binds: [B:364:0x08f9, B:347:0x08cb] A[DONT_GENERATE, DONT_INLINE]
  0x08cd: PHI (r15v14 android.database.Cursor) = (r15v15 android.database.Cursor), (r15v17 android.database.Cursor) binds: [B:364:0x08f9, B:347:0x08cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0903  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0917  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0a45  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0149 A[PHI: r0 r5
  0x0149: PHI (r0v176 java.util.Map) = (r0v175 java.util.Map), (r0v182 java.util.Map) binds: [B:51:0x016d, B:40:0x0147] A[DONT_GENERATE, DONT_INLINE]
  0x0149: PHI (r5v66 android.database.Cursor) = (r5v65 android.database.Cursor), (r5v68 android.database.Cursor) binds: [B:51:0x016d, B:40:0x0147] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0ad8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01af A[Catch: all -> 0x0204, SQLiteException -> 0x0208, TRY_LEAVE, TryCatch #32 {SQLiteException -> 0x0208, all -> 0x0204, blocks: (B:60:0x01a9, B:62:0x01af, B:65:0x01ba, B:66:0x01bf, B:67:0x01c7, B:68:0x01d7, B:71:0x01f7, B:70:0x01e0), top: B:470:0x01a9, inners: #32 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ba A[Catch: all -> 0x0204, SQLiteException -> 0x0208, TRY_ENTER, TryCatch #32 {SQLiteException -> 0x0208, all -> 0x0204, blocks: (B:60:0x01a9, B:62:0x01af, B:65:0x01ba, B:66:0x01bf, B:67:0x01c7, B:68:0x01d7, B:71:0x01f7, B:70:0x01e0), top: B:470:0x01a9, inners: #32 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0238  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.List zza(java.lang.String r60, java.util.List r61, java.util.List r62, java.lang.Long r63, java.lang.Long r64) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2780
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    protected final boolean zzb() {
        return false;
    }
}
