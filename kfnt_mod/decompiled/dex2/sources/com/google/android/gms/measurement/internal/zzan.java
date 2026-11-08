package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzan {
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[Catch: SQLiteException -> 0x00db, TryCatch #6 {SQLiteException -> 0x00db, blocks: (B:23:0x004a, B:25:0x0070, B:27:0x007f, B:29:0x0087, B:30:0x008a, B:31:0x00a8, B:33:0x00ab, B:35:0x00ae, B:37:0x00b6, B:38:0x00bd, B:39:0x00c0, B:41:0x00c6, B:44:0x00d7, B:45:0x00da, B:24:0x0069), top: B:59:0x004a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ab A[Catch: SQLiteException -> 0x00db, LOOP:1: B:33:0x00ab->B:38:0x00bd, LOOP_START, PHI: r12
  0x00ab: PHI (r12v1 int) = (r12v0 int), (r12v2 int) binds: [B:32:0x00a9, B:38:0x00bd] A[DONT_GENERATE, DONT_INLINE], TryCatch #6 {SQLiteException -> 0x00db, blocks: (B:23:0x004a, B:25:0x0070, B:27:0x007f, B:29:0x0087, B:30:0x008a, B:31:0x00a8, B:33:0x00ab, B:35:0x00ae, B:37:0x00b6, B:38:0x00bd, B:39:0x00c0, B:41:0x00c6, B:44:0x00d7, B:45:0x00da, B:24:0x0069), top: B:59:0x004a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c6 A[Catch: SQLiteException -> 0x00db, TryCatch #6 {SQLiteException -> 0x00db, blocks: (B:23:0x004a, B:25:0x0070, B:27:0x007f, B:29:0x0087, B:30:0x008a, B:31:0x00a8, B:33:0x00ab, B:35:0x00ae, B:37:0x00b6, B:38:0x00bd, B:39:0x00c0, B:41:0x00c6, B:44:0x00d7, B:45:0x00da, B:24:0x0069), top: B:59:0x004a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void zza(com.google.android.gms.measurement.internal.zzeo r14, android.database.sqlite.SQLiteDatabase r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String[] r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzan.zza(com.google.android.gms.measurement.internal.zzeo, android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    static void zzb(zzeo zzeoVar, SQLiteDatabase sQLiteDatabase) {
        if (zzeoVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzeoVar.zzk().zza("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzeoVar.zzk().zza("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzeoVar.zzk().zza("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzeoVar.zzk().zza("Failed to turn on database write permission for owner");
    }
}
