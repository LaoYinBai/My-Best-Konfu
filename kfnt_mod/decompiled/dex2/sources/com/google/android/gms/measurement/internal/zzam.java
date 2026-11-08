package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzns;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class zzam extends zzkn {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzal zzj;
    private final zzkj zzk;

    zzam(zzkz zzkzVar) {
        super(zzkzVar);
        this.zzk = new zzkj(this.zzs.zzav());
        this.zzs.zzf();
        this.zzj = new zzal(this, this.zzs.zzau(), "google_app_measurement.db");
    }

    @WorkerThread
    static final void zzV(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Invalid value type");
            }
            contentValues.put("value", (Double) obj);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003e  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzZ(java.lang.String r4, java.lang.String[] r5) throws java.lang.Throwable {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.zzh()
            r1 = 0
            android.database.Cursor r5 = r0.rawQuery(r4, r5)     // Catch: java.lang.Throwable -> L28 android.database.sqlite.SQLiteException -> L2a
            boolean r0 = r5.moveToFirst()     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L25
            if (r0 == 0) goto L1a
            r0 = 0
            long r0 = r5.getLong(r0)     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L25
            if (r5 == 0) goto L19
            r5.close()
        L19:
            return r0
        L1a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L25
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L25
            throw r0     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L25
        L22:
            r4 = move-exception
            r1 = r5
            goto L3c
        L25:
            r0 = move-exception
            r1 = r5
            goto L2b
        L28:
            r4 = move-exception
            goto L3c
        L2a:
            r0 = move-exception
        L2b:
            com.google.android.gms.measurement.internal.zzfy r5 = r3.zzs     // Catch: java.lang.Throwable -> L3b
            com.google.android.gms.measurement.internal.zzeo r5 = r5.zzay()     // Catch: java.lang.Throwable -> L3b
            com.google.android.gms.measurement.internal.zzem r5 = r5.zzd()     // Catch: java.lang.Throwable -> L3b
            java.lang.String r2 = "Database error"
            r5.zzc(r2, r4, r0)     // Catch: java.lang.Throwable -> L3b
            throw r0     // Catch: java.lang.Throwable -> L3b
        L3b:
            r4 = move-exception
        L3c:
            if (r1 == 0) goto L41
            r1.close()
        L41:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzZ(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003c  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzaa(java.lang.String r3, java.lang.String[] r4, long r5) throws java.lang.Throwable {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.zzh()
            r1 = 0
            android.database.Cursor r4 = r0.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L26 android.database.sqlite.SQLiteException -> L28
            boolean r0 = r4.moveToFirst()     // Catch: java.lang.Throwable -> L20 android.database.sqlite.SQLiteException -> L23
            if (r0 == 0) goto L1a
            r5 = 0
            long r5 = r4.getLong(r5)     // Catch: java.lang.Throwable -> L20 android.database.sqlite.SQLiteException -> L23
            if (r4 == 0) goto L19
            r4.close()
        L19:
            return r5
        L1a:
            if (r4 == 0) goto L1f
            r4.close()
        L1f:
            return r5
        L20:
            r3 = move-exception
            r1 = r4
            goto L3a
        L23:
            r5 = move-exception
            r1 = r4
            goto L29
        L26:
            r3 = move-exception
            goto L3a
        L28:
            r5 = move-exception
        L29:
            com.google.android.gms.measurement.internal.zzfy r4 = r2.zzs     // Catch: java.lang.Throwable -> L39
            com.google.android.gms.measurement.internal.zzeo r4 = r4.zzay()     // Catch: java.lang.Throwable -> L39
            com.google.android.gms.measurement.internal.zzem r4 = r4.zzd()     // Catch: java.lang.Throwable -> L39
            java.lang.String r6 = "Database error"
            r4.zzc(r6, r3, r5)     // Catch: java.lang.Throwable -> L39
            throw r5     // Catch: java.lang.Throwable -> L39
        L39:
            r3 = move-exception
        L3a:
            if (r1 == 0) goto L3f
            r1.close()
        L3f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzaa(java.lang.String, java.lang.String[], long):long");
    }

    @WorkerThread
    public final void zzA(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzd("Error deleting user property. appId", zzeo.zzn(str), this.zzs.zzj().zzf(str2), e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x034a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x034b, code lost:
    
        r16.zzs.zzay().zzd().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzeo.zzn(r17), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x035e, code lost:
    
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r17);
        r0 = zzh();
        r0.delete("property_filters", "app_id=? and audience_id=?", new java.lang.String[]{r17, java.lang.String.valueOf(r9)});
        r0.delete("event_filters", "app_id=? and audience_id=?", new java.lang.String[]{r17, java.lang.String.valueOf(r9)});
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x016e, code lost:
    
        r10 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x017a, code lost:
    
        if (r10.hasNext() == false) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0186, code lost:
    
        if (((com.google.android.gms.internal.measurement.zzes) r10.next()).zzj() != false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0188, code lost:
    
        r16.zzs.zzay().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzeo.zzn(r17), java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01a1, code lost:
    
        r10 = r0.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x01ae, code lost:
    
        if (r10.hasNext() == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01b0, code lost:
    
        r11 = (com.google.android.gms.internal.measurement.zzej) r10.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r17);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01ca, code lost:
    
        if (r11.zzg().isEmpty() == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01cc, code lost:
    
        r0 = r16.zzs.zzay().zzk();
        r12 = com.google.android.gms.measurement.internal.zzeo.zzn(r17);
        r13 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e4, code lost:
    
        if (r11.zzp() == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01e6, code lost:
    
        r8 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01ee, code lost:
    
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r12, r13, java.lang.String.valueOf(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01f7, code lost:
    
        r15 = r11.zzbv();
        r6 = new android.content.ContentValues();
        r6.put("app_id", r17);
        r6.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0214, code lost:
    
        if (r11.zzp() == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0216, code lost:
    
        r12 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x021f, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0220, code lost:
    
        r6.put("filter_id", r12);
        r6.put("event_name", r11.zzg());
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0232, code lost:
    
        if (r11.zzq() == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0234, code lost:
    
        r11 = java.lang.Boolean.valueOf(r11.zzn());
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x023d, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x023e, code lost:
    
        r6.put("session_scoped", r11);
        r6.put("data", r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0254, code lost:
    
        if (zzh().insertWithOnConflict("event_filters", null, r6, 5) != (-1)) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0256, code lost:
    
        r16.zzs.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzeo.zzn(r17));
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x026c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x026d, code lost:
    
        r16.zzs.zzay().zzd().zzc("Error storing event filter. appId", com.google.android.gms.measurement.internal.zzeo.zzn(r17), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0282, code lost:
    
        r0 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x028e, code lost:
    
        if (r0.hasNext() == false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0290, code lost:
    
        r6 = (com.google.android.gms.internal.measurement.zzes) r0.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r17);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02aa, code lost:
    
        if (r6.zze().isEmpty() == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x02ac, code lost:
    
        r0 = r16.zzs.zzay().zzk();
        r10 = com.google.android.gms.measurement.internal.zzeo.zzn(r17);
        r11 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02c4, code lost:
    
        if (r6.zzj() == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02c6, code lost:
    
        r8 = java.lang.Integer.valueOf(r6.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x02ce, code lost:
    
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r10, r11, java.lang.String.valueOf(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02d7, code lost:
    
        r7 = r6.zzbv();
        r10 = new android.content.ContentValues();
        r10.put("app_id", r17);
        r10.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02f4, code lost:
    
        if (r6.zzj() == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02f6, code lost:
    
        r12 = java.lang.Integer.valueOf(r6.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02ff, code lost:
    
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0300, code lost:
    
        r10.put("filter_id", r12);
        r10.put("property_name", r6.zze());
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0312, code lost:
    
        if (r6.zzk() == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0314, code lost:
    
        r6 = java.lang.Boolean.valueOf(r6.zzi());
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x031d, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x031e, code lost:
    
        r10.put("session_scoped", r6);
        r10.put("data", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0334, code lost:
    
        if (zzh().insertWithOnConflict("property_filters", null, r10, 5) != (-1)) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0336, code lost:
    
        r16.zzs.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzeo.zzn(r17));
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final void zzB(java.lang.String r17, java.util.List r18) {
        /*
            Method dump skipped, instructions count: 1149
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzB(java.lang.String, java.util.List):void");
    }

    @WorkerThread
    public final void zzC() {
        zzW();
        zzh().setTransactionSuccessful();
    }

    @WorkerThread
    public final void zzD(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        zzW();
        String strZzt = zzhVar.zzt();
        Preconditions.checkNotNull(strZzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", strZzt);
        contentValues.put("app_instance_id", zzhVar.zzu());
        contentValues.put("gmp_app_id", zzhVar.zzy());
        contentValues.put("resettable_device_id_hash", zzhVar.zzA());
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put("app_version", zzhVar.zzw());
        contentValues.put("app_store", zzhVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzai()));
        contentValues.put("day", Long.valueOf(zzhVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzb()));
        contentValues.put("firebase_instance_id", zzhVar.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzh()));
        contentValues.put("health_monitor_sample", zzhVar.zzz());
        zzhVar.zza();
        contentValues.put("android_id", (Long) 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzah()));
        contentValues.put("admob_app_id", zzhVar.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzk()));
        contentValues.put("session_stitching_token", zzhVar.zzB());
        List listZzC = zzhVar.zzC();
        if (listZzC != null) {
            if (listZzC.isEmpty()) {
                this.zzs.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", strZzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", listZzC));
            }
        }
        zzns.zzc();
        if (this.zzs.zzf().zzs(null, zzeb.zzai) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzh();
            if (sQLiteDatabaseZzh.update("apps", contentValues, "app_id = ?", new String[]{strZzt}) == 0 && sQLiteDatabaseZzh.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzeo.zzn(strZzt));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing app. appId", zzeo.zzn(strZzt), e);
        }
    }

    @WorkerThread
    public final void zzE(zzas zzasVar) {
        Preconditions.checkNotNull(zzasVar);
        zzg();
        zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasVar.zza);
        contentValues.put("name", zzasVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzasVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzasVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzasVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzasVar.zzg));
        contentValues.put("last_bundled_day", zzasVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzasVar.zzi);
        contentValues.put("last_sampling_rate", zzasVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzasVar.zze));
        Boolean bool = zzasVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzh().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzeo.zzn(zzasVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing event aggregates. appId", zzeo.zzn(zzasVar.zza), e);
        }
    }

    public final boolean zzF() {
        return zzZ("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzG() {
        return zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzH() {
        return zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    @VisibleForTesting
    protected final boolean zzI() {
        Context contextZzau = this.zzs.zzau();
        this.zzs.zzf();
        return contextZzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzJ(String str, Long l, long j, com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        zzg();
        zzW();
        Preconditions.checkNotNull(zzfsVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] bArrZzbv = zzfsVar.zzbv();
        this.zzs.zzay().zzj().zzc("Saving complex main event, appId, data size", this.zzs.zzj().zzd(str), Integer.valueOf(bArrZzbv.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", bArrZzbv);
        try {
            if (zzh().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            this.zzs.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzeo.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing complex main event. appId", zzeo.zzn(str), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean zzK(zzac zzacVar) throws Throwable {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zzW();
        String str = zzacVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzacVar.zzc.zzb) == null) {
            long jZzZ = zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzs.zzf();
            if (jZzZ >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzacVar.zzb);
        contentValues.put("name", zzacVar.zzc.zzb);
        zzV(contentValues, "value", Preconditions.checkNotNull(zzacVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzacVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzacVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzacVar.zzh));
        contentValues.put("timed_out_event", this.zzs.zzv().zzan(zzacVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzacVar.zzd));
        contentValues.put("triggered_event", this.zzs.zzv().zzan(zzacVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzacVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzacVar.zzj));
        contentValues.put("expired_event", this.zzs.zzv().zzan(zzacVar.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzeo.zzn(str));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing conditional user property", zzeo.zzn(str), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zzL(zzle zzleVar) {
        Preconditions.checkNotNull(zzleVar);
        zzg();
        zzW();
        if (zzp(zzleVar.zza, zzleVar.zzc) == null) {
            if (zzlh.zzai(zzleVar.zzc)) {
                if (zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzleVar.zza}) >= this.zzs.zzf().zzf(zzleVar.zza, zzeb.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzleVar.zzc)) {
                long jZzZ = zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzleVar.zza, zzleVar.zzb});
                this.zzs.zzf();
                if (jZzZ >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzleVar.zza);
        contentValues.put("origin", zzleVar.zzb);
        contentValues.put("name", zzleVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzleVar.zzd));
        zzV(contentValues, "value", zzleVar.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.zzs.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzeo.zzn(zzleVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzc("Error storing user property. appId", zzeo.zzn(zzleVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[Catch: all -> 0x0222, SQLiteException -> 0x0228, TRY_LEAVE, TryCatch #17 {SQLiteException -> 0x0228, all -> 0x0222, blocks: (B:43:0x00ee, B:45:0x00f4, B:49:0x010d, B:50:0x0111, B:51:0x0121, B:53:0x0127, B:54:0x013a, B:56:0x0149, B:58:0x0168, B:57:0x015c), top: B:133:0x00ee }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzU(java.lang.String r23, long r24, long r26, com.google.android.gms.measurement.internal.zzkw r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 606
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzU(java.lang.String, long, long, com.google.android.gms.measurement.internal.zzkw):void");
    }

    @WorkerThread
    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzs.zzay().zzd().zzd("Error deleting conditional property", zzeo.zzn(str), this.zzs.zzj().zzf(str2), e);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    protected final boolean zzb() {
        return false;
    }

    @VisibleForTesting
    @WorkerThread
    protected final long zzc(String str, String str2) {
        long jZzaa;
        SQLiteException e;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzW();
        SQLiteDatabase sQLiteDatabaseZzh = zzh();
        sQLiteDatabaseZzh.beginTransaction();
        try {
            try {
                jZzaa = zzaa("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
                if (jZzaa == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (sQLiteDatabaseZzh.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        this.zzs.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzeo.zzn(str), "first_open_count");
                        return -1L;
                    }
                    jZzaa = 0;
                }
                try {
                    contentValues = new ContentValues();
                    contentValues.put("app_id", str);
                    contentValues.put("first_open_count", Long.valueOf(1 + jZzaa));
                } catch (SQLiteException e2) {
                    e = e2;
                    this.zzs.zzay().zzd().zzd("Error inserting column. appId", zzeo.zzn(str), "first_open_count", e);
                    return jZzaa;
                }
            } finally {
                sQLiteDatabaseZzh.endTransaction();
            }
        } catch (SQLiteException e3) {
            jZzaa = 0;
            e = e3;
        }
        if (sQLiteDatabaseZzh.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
            this.zzs.zzay().zzd().zzc("Failed to update column (got 0). appId", zzeo.zzn(str), "first_open_count");
            return -1L;
        }
        sQLiteDatabaseZzh.setTransactionSuccessful();
        return jZzaa;
    }

    @WorkerThread
    public final long zzd() {
        return zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    @WorkerThread
    public final long zze() {
        return zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    @VisibleForTesting
    @WorkerThread
    final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzs.zzay().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle zzi(java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.zzg()
            r7.zzW()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.zzh()     // Catch: java.lang.Throwable -> Lc4 android.database.sqlite.SQLiteException -> Lc7
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lc4 android.database.sqlite.SQLiteException -> Lc7
            r3 = 0
            r2[r3] = r8     // Catch: java.lang.Throwable -> Lc4 android.database.sqlite.SQLiteException -> Lc7
            java.lang.String r4 = "select parameters from default_event_params where app_id=?"
            android.database.Cursor r1 = r1.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> Lc4 android.database.sqlite.SQLiteException -> Lc7
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r2 != 0) goto L32
            com.google.android.gms.measurement.internal.zzfy r8 = r7.zzs     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.measurement.internal.zzeo r8 = r8.zzay()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.measurement.internal.zzem r8 = r8.zzj()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            java.lang.String r2 = "Default event parameters not found"
            r8.zza(r2)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r1 == 0) goto L31
            r1.close()
        L31:
            return r0
        L32:
            byte[] r2 = r1.getBlob(r3)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzfr r3 = com.google.android.gms.internal.measurement.zzfs.zze()     // Catch: java.io.IOException -> La6 java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzlk r2 = com.google.android.gms.measurement.internal.zzlb.zzl(r3, r2)     // Catch: java.io.IOException -> La6 java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzfr r2 = (com.google.android.gms.internal.measurement.zzfr) r2     // Catch: java.io.IOException -> La6 java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzke r2 = r2.zzaE()     // Catch: java.io.IOException -> La6 java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzfs r2 = (com.google.android.gms.internal.measurement.zzfs) r2     // Catch: java.io.IOException -> La6 java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.measurement.internal.zzkz r8 = r7.zzf     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r8.zzu()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            java.util.List r8 = r2.zzi()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            android.os.Bundle r2 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r2.<init>()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            java.util.Iterator r8 = r8.iterator()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
        L58:
            boolean r3 = r8.hasNext()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r3 == 0) goto La0
            java.lang.Object r3 = r8.next()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.internal.measurement.zzfw r3 = (com.google.android.gms.internal.measurement.zzfw) r3     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            java.lang.String r4 = r3.zzg()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            boolean r5 = r3.zzu()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r5 == 0) goto L76
            double r5 = r3.zza()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r2.putDouble(r4, r5)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            goto L58
        L76:
            boolean r5 = r3.zzv()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r5 == 0) goto L84
            float r3 = r3.zzb()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r2.putFloat(r4, r3)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            goto L58
        L84:
            boolean r5 = r3.zzy()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r5 == 0) goto L92
            java.lang.String r3 = r3.zzh()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r2.putString(r4, r3)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            goto L58
        L92:
            boolean r5 = r3.zzw()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r5 == 0) goto L58
            long r5 = r3.zzd()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r2.putLong(r4, r5)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            goto L58
        La0:
            if (r1 == 0) goto La5
            r1.close()
        La5:
            return r2
        La6:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfy r3 = r7.zzs     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.measurement.internal.zzeo r3 = r3.zzay()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            com.google.android.gms.measurement.internal.zzem r3 = r3.zzd()     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzeo.zzn(r8)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            r3.zzc(r4, r8, r2)     // Catch: java.lang.Throwable -> Lc0 android.database.sqlite.SQLiteException -> Lc2
            if (r1 == 0) goto Lbf
            r1.close()
        Lbf:
            return r0
        Lc0:
            r8 = move-exception
            goto Ldf
        Lc2:
            r8 = move-exception
            goto Lc9
        Lc4:
            r8 = move-exception
            r1 = r0
            goto Ldf
        Lc7:
            r8 = move-exception
            r1 = r0
        Lc9:
            com.google.android.gms.measurement.internal.zzfy r2 = r7.zzs     // Catch: java.lang.Throwable -> Lde
            com.google.android.gms.measurement.internal.zzeo r2 = r2.zzay()     // Catch: java.lang.Throwable -> Lde
            com.google.android.gms.measurement.internal.zzem r2 = r2.zzd()     // Catch: java.lang.Throwable -> Lde
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zzb(r3, r8)     // Catch: java.lang.Throwable -> Lde
            if (r1 == 0) goto Ldd
            r1.close()
        Ldd:
            return r0
        Lde:
            r8 = move-exception
        Ldf:
            if (r1 == 0) goto Le4
            r1.close()
        Le4:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzi(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x01f3  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzh zzj(java.lang.String r35) {
        /*
            Method dump skipped, instructions count: 503
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzh");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x012f  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzac zzk(java.lang.String r31, java.lang.String r32) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzk(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzac");
    }

    @WorkerThread
    public final zzak zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1L, false, false, z3, false, z5);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0130  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzak zzm(long r19, java.lang.String r21, long r22, boolean r24, boolean r25, boolean r26, boolean r27, boolean r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzm(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzak");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x014f  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzas zzn(java.lang.String r28, java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzn(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzas");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00ad  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzle zzp(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r1 = r19
            r9 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21)
            r19.zzg()
            r19.zzW()
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.zzh()     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            java.lang.String r0 = "set_timestamp"
            java.lang.String r2 = "value"
            java.lang.String r3 = "origin"
            java.lang.String[] r13 = new java.lang.String[]{r0, r2, r3}     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            r0 = 2
            java.lang.String[] r15 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            r2 = 0
            r15[r2] = r20     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            r3 = 1
            r15[r3] = r9     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            java.lang.String r12 = "user_attributes"
            java.lang.String r14 = "app_id=? and name=?"
            r16 = 0
            r17 = 0
            r18 = 0
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch: java.lang.Throwable -> L83 android.database.sqlite.SQLiteException -> L86
            boolean r4 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            if (r4 != 0) goto L42
            if (r11 == 0) goto L41
            r11.close()
        L41:
            return r10
        L42:
            long r6 = r11.getLong(r2)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            java.lang.Object r8 = r1.zzq(r11, r3)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            if (r8 != 0) goto L52
            if (r11 == 0) goto L51
            r11.close()
        L51:
            return r10
        L52:
            java.lang.String r4 = r11.getString(r0)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            com.google.android.gms.measurement.internal.zzle r0 = new com.google.android.gms.measurement.internal.zzle     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            r2 = r0
            r3 = r20
            r5 = r21
            r2.<init>(r3, r4, r5, r6, r8)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            boolean r2 = r11.moveToNext()     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            if (r2 == 0) goto L79
            com.google.android.gms.measurement.internal.zzfy r2 = r1.zzs     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            com.google.android.gms.measurement.internal.zzeo r2 = r2.zzay()     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            com.google.android.gms.measurement.internal.zzem r2 = r2.zzd()     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            java.lang.String r3 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeo.zzn(r20)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
            r2.zzb(r3, r4)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L81
        L79:
            if (r11 == 0) goto L7e
            r11.close()
        L7e:
            return r0
        L7f:
            r0 = move-exception
            goto Lab
        L81:
            r0 = move-exception
            goto L88
        L83:
            r0 = move-exception
            r11 = r10
            goto Lab
        L86:
            r0 = move-exception
            r11 = r10
        L88:
            com.google.android.gms.measurement.internal.zzfy r2 = r1.zzs     // Catch: java.lang.Throwable -> L7f
            com.google.android.gms.measurement.internal.zzeo r2 = r2.zzay()     // Catch: java.lang.Throwable -> L7f
            com.google.android.gms.measurement.internal.zzem r2 = r2.zzd()     // Catch: java.lang.Throwable -> L7f
            java.lang.String r3 = "Error querying user property. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeo.zzn(r20)     // Catch: java.lang.Throwable -> L7f
            com.google.android.gms.measurement.internal.zzfy r5 = r1.zzs     // Catch: java.lang.Throwable -> L7f
            com.google.android.gms.measurement.internal.zzej r5 = r5.zzj()     // Catch: java.lang.Throwable -> L7f
            java.lang.String r5 = r5.zzf(r9)     // Catch: java.lang.Throwable -> L7f
            r2.zzd(r3, r4, r5, r0)     // Catch: java.lang.Throwable -> L7f
            if (r11 == 0) goto Laa
            r11.close()
        Laa:
            return r10
        Lab:
            if (r11 == 0) goto Lb0
            r11.close()
        Lb0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzp(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzle");
    }

    @VisibleForTesting
    @WorkerThread
    final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                this.zzs.zzay().zzd().zza("Loaded invalid null value from database");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                this.zzs.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
                break;
            default:
                this.zzs.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                break;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzr() throws java.lang.Throwable {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.zzh()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L26 android.database.sqlite.SQLiteException -> L2b
            boolean r2 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L24
            if (r2 == 0) goto L1c
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L22 android.database.sqlite.SQLiteException -> L24
            if (r0 == 0) goto L1b
            r0.close()
        L1b:
            return r2
        L1c:
            if (r0 == 0) goto L21
            r0.close()
        L21:
            return r1
        L22:
            r1 = move-exception
            goto L43
        L24:
            r2 = move-exception
            goto L2e
        L26:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L43
        L2b:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L2e:
            com.google.android.gms.measurement.internal.zzfy r3 = r6.zzs     // Catch: java.lang.Throwable -> L22
            com.google.android.gms.measurement.internal.zzeo r3 = r3.zzay()     // Catch: java.lang.Throwable -> L22
            com.google.android.gms.measurement.internal.zzem r3 = r3.zzd()     // Catch: java.lang.Throwable -> L22
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzb(r4, r2)     // Catch: java.lang.Throwable -> L22
            if (r0 == 0) goto L42
            r0.close()
        L42:
            return r1
        L43:
            if (r0 == 0) goto L48
            r0.close()
        L48:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzr():java.lang.String");
    }

    @WorkerThread
    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0058, code lost:
    
        r3 = r25.zzs.zzay().zzd();
        r25.zzs.zzf();
        r3.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzt(java.lang.String r26, java.lang.String[] r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzt(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b0  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzu(java.lang.String r14) throws java.lang.Throwable {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzg()
            r13.zzW()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r9 = "1000"
            r10 = 0
            android.database.sqlite.SQLiteDatabase r1 = r13.zzh()     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            java.lang.String r2 = "user_attributes"
            java.lang.String r3 = "name"
            java.lang.String r4 = "origin"
            java.lang.String r5 = "set_timestamp"
            java.lang.String r6 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6}     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            java.lang.String r4 = "app_id=?"
            r11 = 1
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            r12 = 0
            r5[r12] = r14     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            java.lang.String r8 = "rowid"
            com.google.android.gms.measurement.internal.zzfy r6 = r13.zzs     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            r6.zzf()     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8d android.database.sqlite.SQLiteException -> L8f
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            if (r2 == 0) goto L81
        L3e:
            java.lang.String r6 = r1.getString(r12)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            java.lang.String r2 = r1.getString(r11)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            if (r2 != 0) goto L4a
            java.lang.String r2 = ""
        L4a:
            r5 = r2
            r2 = 2
            long r7 = r1.getLong(r2)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            r2 = 3
            java.lang.Object r9 = r13.zzq(r1, r2)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            if (r9 != 0) goto L6b
            com.google.android.gms.measurement.internal.zzfy r2 = r13.zzs     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            com.google.android.gms.measurement.internal.zzeo r2 = r2.zzay()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            com.google.android.gms.measurement.internal.zzem r2 = r2.zzd()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            java.lang.String r3 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzeo.zzn(r14)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            r2.zzb(r3, r4)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            goto L75
        L6b:
            com.google.android.gms.measurement.internal.zzle r2 = new com.google.android.gms.measurement.internal.zzle     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            r3 = r2
            r4 = r14
            r3.<init>(r4, r5, r6, r7, r9)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            r0.add(r2)     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
        L75:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L87 android.database.sqlite.SQLiteException -> L8a
            if (r2 != 0) goto L3e
            if (r1 == 0) goto L80
            r1.close()
        L80:
            return r0
        L81:
            if (r1 == 0) goto L86
            r1.close()
        L86:
            return r0
        L87:
            r14 = move-exception
            r10 = r1
            goto Lae
        L8a:
            r0 = move-exception
            r10 = r1
            goto L90
        L8d:
            r14 = move-exception
            goto Lae
        L8f:
            r0 = move-exception
        L90:
            com.google.android.gms.measurement.internal.zzfy r1 = r13.zzs     // Catch: java.lang.Throwable -> Lad
            com.google.android.gms.measurement.internal.zzeo r1 = r1.zzay()     // Catch: java.lang.Throwable -> Lad
            com.google.android.gms.measurement.internal.zzem r1 = r1.zzd()     // Catch: java.lang.Throwable -> Lad
            java.lang.String r2 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzeo.zzn(r14)     // Catch: java.lang.Throwable -> Lad
            r1.zzc(r2, r14, r0)     // Catch: java.lang.Throwable -> Lad
            java.util.List r14 = java.util.Collections.emptyList()     // Catch: java.lang.Throwable -> Lad
            if (r10 == 0) goto Lac
            r10.close()
        Lac:
            return r14
        Lad:
            r14 = move-exception
        Lae:
            if (r10 == 0) goto Lb3
            r10.close()
        Lb3:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzu(java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ac, code lost:
    
        r0 = r17.zzs.zzay().zzd();
        r17.zzs.zzf();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013c  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzv(java.lang.String r18, java.lang.String r19, java.lang.String r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzv(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    @WorkerThread
    public final void zzw() {
        zzW();
        zzh().beginTransaction();
    }

    @WorkerThread
    public final void zzx() {
        zzW();
        zzh().endTransaction();
    }

    @VisibleForTesting
    @WorkerThread
    final void zzy(List list) throws SQLException {
        zzg();
        zzW();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzI()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                this.zzs.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                zzh().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                this.zzs.zzay().zzd().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    @WorkerThread
    final void zzz() {
        zzg();
        zzW();
        if (zzI()) {
            long jZza = this.zzf.zzs().zza.zza();
            long jElapsedRealtime = this.zzs.zzav().elapsedRealtime();
            long jAbs = Math.abs(jElapsedRealtime - jZza);
            this.zzs.zzf();
            if (jAbs > ((Long) zzeb.zzx.zza(null)).longValue()) {
                this.zzf.zzs().zza.zzb(jElapsedRealtime);
                zzg();
                zzW();
                if (zzI()) {
                    SQLiteDatabase sQLiteDatabaseZzh = zzh();
                    this.zzs.zzf();
                    int iDelete = sQLiteDatabaseZzh.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzs.zzav().currentTimeMillis()), String.valueOf(zzag.zzA())});
                    if (iDelete > 0) {
                        this.zzs.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
                    }
                }
            }
        }
    }
}
