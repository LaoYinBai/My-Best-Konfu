package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.1.1 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class zzey {
    final /* synthetic */ zzfd zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzey(zzfd zzfdVar, String str, Bundle bundle) {
        this.zza = zzfdVar;
        Preconditions.checkNotEmpty("default_event_parameters");
        this.zzb = "default_event_parameters";
        this.zzc = new Bundle();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006a A[Catch: NumberFormatException | JSONException -> 0x00a3, NumberFormatException | JSONException -> 0x00a3, TRY_ENTER, TryCatch #0 {NumberFormatException | JSONException -> 0x00a3, blocks: (B:10:0x0027, B:29:0x006a, B:29:0x006a, B:33:0x0093, B:33:0x0093, B:30:0x006d, B:30:0x006d, B:31:0x007b, B:31:0x007b, B:32:0x0089, B:32:0x0089), top: B:45:0x0027, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d A[Catch: NumberFormatException | JSONException -> 0x00a3, NumberFormatException | JSONException -> 0x00a3, TryCatch #0 {NumberFormatException | JSONException -> 0x00a3, blocks: (B:10:0x0027, B:29:0x006a, B:29:0x006a, B:33:0x0093, B:33:0x0093, B:30:0x006d, B:30:0x006d, B:31:0x007b, B:31:0x007b, B:32:0x0089, B:32:0x0089), top: B:45:0x0027, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b A[Catch: NumberFormatException | JSONException -> 0x00a3, NumberFormatException | JSONException -> 0x00a3, TryCatch #0 {NumberFormatException | JSONException -> 0x00a3, blocks: (B:10:0x0027, B:29:0x006a, B:29:0x006a, B:33:0x0093, B:33:0x0093, B:30:0x006d, B:30:0x006d, B:31:0x007b, B:31:0x007b, B:32:0x0089, B:32:0x0089), top: B:45:0x0027, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0089 A[Catch: NumberFormatException | JSONException -> 0x00a3, NumberFormatException | JSONException -> 0x00a3, TryCatch #0 {NumberFormatException | JSONException -> 0x00a3, blocks: (B:10:0x0027, B:29:0x006a, B:29:0x006a, B:33:0x0093, B:33:0x0093, B:30:0x006d, B:30:0x006d, B:31:0x007b, B:31:0x007b, B:32:0x0089, B:32:0x0089), top: B:45:0x0027, outer: #1 }] */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle zza() {
        /*
            r9 = this;
            android.os.Bundle r0 = r9.zzd
            if (r0 == 0) goto L6
            goto Ld4
        L6:
            com.google.android.gms.measurement.internal.zzfd r0 = r9.zza
            android.content.SharedPreferences r0 = r0.zza()
            java.lang.String r1 = r9.zzb
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            if (r0 == 0) goto Lcc
            android.os.Bundle r1 = new android.os.Bundle     // Catch: org.json.JSONException -> Lbb
            r1.<init>()     // Catch: org.json.JSONException -> Lbb
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: org.json.JSONException -> Lbb
            r2.<init>(r0)     // Catch: org.json.JSONException -> Lbb
            r0 = 0
            r3 = 0
        L21:
            int r4 = r2.length()     // Catch: org.json.JSONException -> Lbb
            if (r3 >= r4) goto Lb8
            org.json.JSONObject r4 = r2.getJSONObject(r3)     // Catch: java.lang.Throwable -> La3
            java.lang.String r5 = "n"
            java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = "t"
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Throwable -> La3
            int r7 = r6.hashCode()     // Catch: java.lang.Throwable -> La3
            r8 = 100
            if (r7 == r8) goto L5c
            r8 = 108(0x6c, float:1.51E-43)
            if (r7 == r8) goto L52
            r8 = 115(0x73, float:1.61E-43)
            if (r7 == r8) goto L48
            goto L66
        L48:
            java.lang.String r7 = "s"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L66
            r7 = 0
            goto L67
        L52:
            java.lang.String r7 = "l"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L66
            r7 = 2
            goto L67
        L5c:
            java.lang.String r7 = "d"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L66
            r7 = 1
            goto L67
        L66:
            r7 = -1
        L67:
            switch(r7) {
                case 0: goto L89;
                case 1: goto L7b;
                case 2: goto L6d;
                default: goto L6a;
            }
        L6a:
            com.google.android.gms.measurement.internal.zzfd r4 = r9.zza     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto L93
        L6d:
            java.lang.String r6 = "v"
            java.lang.String r4 = r4.getString(r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            long r6 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putLong(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L7b:
            java.lang.String r6 = "v"
            java.lang.String r4 = r4.getString(r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            double r6 = java.lang.Double.parseDouble(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putDouble(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L89:
            java.lang.String r6 = "v"
            java.lang.String r4 = r4.getString(r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            r1.putString(r5, r4)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        L93:
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzs     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            com.google.android.gms.measurement.internal.zzeo r4 = r4.zzay()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            com.google.android.gms.measurement.internal.zzem r4 = r4.zzd()     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            java.lang.String r5 = "Unrecognized persisted bundle type. Type"
            r4.zzb(r5, r6)     // Catch: java.lang.Throwable -> La3 java.lang.Throwable -> La3
            goto Lb4
        La3:
            com.google.android.gms.measurement.internal.zzfd r4 = r9.zza     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzs     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzeo r4 = r4.zzay()     // Catch: org.json.JSONException -> Lbb
            com.google.android.gms.measurement.internal.zzem r4 = r4.zzd()     // Catch: org.json.JSONException -> Lbb
            java.lang.String r5 = "Error reading value from SharedPreferences. Value dropped"
            r4.zza(r5)     // Catch: org.json.JSONException -> Lbb
        Lb4:
            int r3 = r3 + 1
            goto L21
        Lb8:
            r9.zzd = r1     // Catch: org.json.JSONException -> Lbb
            goto Lcc
        Lbb:
            com.google.android.gms.measurement.internal.zzfd r0 = r9.zza
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzs
            com.google.android.gms.measurement.internal.zzeo r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzem r0 = r0.zzd()
            java.lang.String r1 = "Error loading bundle from SharedPreferences. Values will be lost"
            r0.zza(r1)
        Lcc:
            android.os.Bundle r0 = r9.zzd
            if (r0 != 0) goto Ld4
            android.os.Bundle r0 = r9.zzc
            r9.zzd = r0
        Ld4:
            android.os.Bundle r0 = r9.zzd
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzey.zza():android.os.Bundle");
    }

    @WorkerThread
    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor editorEdit = this.zza.zza().edit();
        if (bundle.size() == 0) {
            editorEdit.remove(this.zzb);
        } else {
            String str = this.zzb;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("n", str2);
                        jSONObject.put(NotifyType.VIBRATE, obj.toString());
                        if (obj instanceof String) {
                            jSONObject.put("t", NotifyType.SOUND);
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", NotifyType.LIGHTS);
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", "d");
                        } else {
                            this.zza.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        this.zza.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences", e);
                    }
                }
            }
            editorEdit.putString(str, jSONArray.toString());
        }
        editorEdit.apply();
        this.zzd = bundle;
    }
}
