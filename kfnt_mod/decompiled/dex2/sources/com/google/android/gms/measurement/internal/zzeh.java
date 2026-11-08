package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzeh extends zzf {
    private final zzeg zza;
    private boolean zzb;

    zzeh(zzfy zzfyVar) {
        super(zzfyVar);
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        this.zza = new zzeg(this, contextZzau, "google_app_measurement_local.db");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x011c A[PHI: r8
  0x011c: PHI (r8v3 android.database.sqlite.SQLiteDatabase) = (r8v2 android.database.sqlite.SQLiteDatabase), (r8v4 android.database.sqlite.SQLiteDatabase) binds: [B:56:0x00ed, B:71:0x011a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012f  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzq(int r18, byte[] r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeh.zzq(int, byte[]):boolean");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    @VisibleForTesting
    @WorkerThread
    final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0240 A[PHI: r7 r12
  0x0240: PHI (r7v3 int) = (r7v1 int), (r7v1 int), (r7v4 int) binds: [B:147:0x022e, B:162:0x025d, B:155:0x023e] A[DONT_GENERATE, DONT_INLINE]
  0x0240: PHI (r12v7 android.database.sqlite.SQLiteDatabase) = 
  (r12v5 android.database.sqlite.SQLiteDatabase)
  (r12v6 android.database.sqlite.SQLiteDatabase)
  (r12v8 android.database.sqlite.SQLiteDatabase)
 binds: [B:147:0x022e, B:162:0x025d, B:155:0x023e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x020c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x01ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0260 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0260 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0260 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzi(int r22) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeh.zzi(int):java.util.List");
    }

    @WorkerThread
    public final void zzj() {
        int iDelete;
        zzg();
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzh();
            if (sQLiteDatabaseZzh == null || (iDelete = sQLiteDatabaseZzh.delete("messages", null, null)) <= 0) {
                return;
            }
            this.zzs.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(iDelete));
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzb("Error resetting local analytics data. error", e);
        }
    }

    @WorkerThread
    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    @VisibleForTesting
    final boolean zzl() {
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        return contextZzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x009c  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzm() throws java.lang.Throwable {
        /*
            r10 = this;
            r10.zzg()
            boolean r0 = r10.zzb
            r1 = 0
            if (r0 == 0) goto L9
            return r1
        L9:
            boolean r0 = r10.zzl()
            if (r0 == 0) goto Laf
            r0 = 5
            r2 = 0
            r3 = 5
        L12:
            if (r2 >= r0) goto La0
            r4 = 0
            r5 = 1
            android.database.sqlite.SQLiteDatabase r6 = r10.zzh()     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4d android.database.sqlite.SQLiteDatabaseLockedException -> L70 android.database.sqlite.SQLiteFullException -> L7c
            if (r6 != 0) goto L26
            r10.zzb = r5     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            return r1
        L1f:
            r0 = move-exception
            goto L43
        L21:
            r4 = move-exception
            goto L45
        L23:
            r4 = move-exception
            goto L80
        L26:
            r6.beginTransaction()     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            java.lang.String[] r4 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            r7 = 3
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            r4[r1] = r7     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            r6.delete(r7, r8, r4)     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            r6.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            r6.endTransaction()     // Catch: java.lang.Throwable -> L1f android.database.sqlite.SQLiteException -> L21 android.database.sqlite.SQLiteFullException -> L23 android.database.sqlite.SQLiteDatabaseLockedException -> L49
            r6.close()
            return r5
        L43:
            r4 = r6
            goto L9a
        L45:
            r9 = r6
            r6 = r4
            r4 = r9
            goto L4e
        L49:
            r4 = r6
            goto L70
        L4b:
            r0 = move-exception
            goto L9a
        L4d:
            r6 = move-exception
        L4e:
            if (r4 == 0) goto L5c
            boolean r7 = r4.inTransaction()     // Catch: java.lang.Throwable -> L5a
            if (r7 == 0) goto L5c
            r4.endTransaction()     // Catch: java.lang.Throwable -> L5a
            goto L5c
        L5a:
            r0 = move-exception
            goto L9a
        L5c:
            com.google.android.gms.measurement.internal.zzfy r7 = r10.zzs     // Catch: java.lang.Throwable -> L5a
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzay()     // Catch: java.lang.Throwable -> L5a
            com.google.android.gms.measurement.internal.zzem r7 = r7.zzd()     // Catch: java.lang.Throwable -> L5a
            java.lang.String r8 = "Error deleting app launch break from local database"
            r7.zzb(r8, r6)     // Catch: java.lang.Throwable -> L5a
            r10.zzb = r5     // Catch: java.lang.Throwable -> L5a
            if (r4 == 0) goto L96
            goto L78
        L70:
            long r5 = (long) r3     // Catch: java.lang.Throwable -> L5a
            android.os.SystemClock.sleep(r5)     // Catch: java.lang.Throwable -> L5a
            int r3 = r3 + 20
            if (r4 == 0) goto L96
        L78:
            r4.close()
            goto L96
        L7c:
            r6 = move-exception
            r9 = r6
            r6 = r4
            r4 = r9
        L80:
            com.google.android.gms.measurement.internal.zzfy r7 = r10.zzs     // Catch: java.lang.Throwable -> L1f
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzay()     // Catch: java.lang.Throwable -> L1f
            com.google.android.gms.measurement.internal.zzem r7 = r7.zzd()     // Catch: java.lang.Throwable -> L1f
            java.lang.String r8 = "Error deleting app launch break from local database"
            r7.zzb(r8, r4)     // Catch: java.lang.Throwable -> L1f
            r10.zzb = r5     // Catch: java.lang.Throwable -> L1f
            if (r6 == 0) goto L96
            r6.close()
        L96:
            int r2 = r2 + 1
            goto L12
        L9a:
            if (r4 == 0) goto L9f
            r4.close()
        L9f:
            throw r0
        La0:
            com.google.android.gms.measurement.internal.zzfy r0 = r10.zzs
            com.google.android.gms.measurement.internal.zzeo r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzem r0 = r0.zzk()
            java.lang.String r2 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r2)
        Laf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzeh.zzm():boolean");
    }

    public final boolean zzn(zzac zzacVar) {
        byte[] bArrZzan = this.zzs.zzv().zzan(zzacVar);
        if (bArrZzan.length <= 131072) {
            return zzq(2, bArrZzan);
        }
        this.zzs.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzo(zzaw zzawVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzax.zza(zzawVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(0, bArrMarshall);
        }
        this.zzs.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzp(zzlc zzlcVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzld.zza(zzlcVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(1, bArrMarshall);
        }
        this.zzs.zzay().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
