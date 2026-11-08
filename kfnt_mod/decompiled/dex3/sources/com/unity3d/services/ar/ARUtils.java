package com.unity3d.services.ar;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ARUtils {
    public static final int AR_CHECK_NOT_SUPPORTED = 0;
    public static final int AR_CHECK_SUPPORTED = 1;
    public static final int AR_CHECK_TRANSIENT = 2;
    private static Config.LightEstimationMode[] lightEstimationModes;
    private static Config.PlaneFindingMode[] planeFindingModes;
    private static Config.UpdateMode[] updateModes;

    public static int isSupported(Context context) {
        if (!ARCheck.isFrameworkPresent()) {
            return 0;
        }
        if (planeFindingModes == null) {
            planeFindingModes = Config.PlaneFindingMode.values();
            lightEstimationModes = Config.LightEstimationMode.values();
            updateModes = Config.UpdateMode.values();
        }
        ArCoreApk.Availability availabilityCheckAvailability = ArCoreApk.getInstance().checkAvailability(context);
        if (availabilityCheckAvailability.isTransient()) {
            return 2;
        }
        if (availabilityCheckAvailability != ArCoreApk.Availability.SUPPORTED_INSTALLED) {
            return 0;
        }
        try {
            new Session(context);
            return 1;
        } catch (FatalException | UnavailableException unused) {
            return 0;
        } catch (SecurityException unused2) {
            return 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
    
        r0.setPlaneFindingMode(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r0.setUpdateMode(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
    
        r0.setLightEstimationMode(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.ar.core.Config createConfiguration(org.json.JSONObject r7, com.google.ar.core.Session r8) throws org.json.JSONException, java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            com.google.ar.core.Config r0 = new com.google.ar.core.Config
            r0.<init>(r8)
            java.lang.String r8 = "lightEstimationMode"
            boolean r8 = r7.has(r8)
            r1 = 0
            if (r8 == 0) goto L32
            java.lang.String r8 = "lightEstimationMode"
            java.lang.String r8 = r7.getString(r8)     // Catch: org.json.JSONException -> L2d
            com.google.ar.core.Config$LightEstimationMode[] r2 = com.unity3d.services.ar.ARUtils.lightEstimationModes     // Catch: org.json.JSONException -> L2d
            int r3 = r2.length     // Catch: org.json.JSONException -> L2d
            r4 = 0
        L18:
            if (r4 >= r3) goto L32
            r5 = r2[r4]     // Catch: org.json.JSONException -> L2d
            java.lang.String r6 = r5.name()     // Catch: org.json.JSONException -> L2d
            boolean r6 = r8.equalsIgnoreCase(r6)     // Catch: org.json.JSONException -> L2d
            if (r6 == 0) goto L2a
            r0.setLightEstimationMode(r5)     // Catch: org.json.JSONException -> L2d
            goto L32
        L2a:
            int r4 = r4 + 1
            goto L18
        L2d:
            java.lang.String r8 = "lightEstimationEnabled is not a string."
            com.unity3d.services.core.log.DeviceLog.warning(r8)
        L32:
            java.lang.String r8 = "planeFindingMode"
            boolean r8 = r7.has(r8)
            if (r8 == 0) goto L5e
            java.lang.String r8 = "planeFindingMode"
            java.lang.String r8 = r7.getString(r8)     // Catch: org.json.JSONException -> L59
            com.google.ar.core.Config$PlaneFindingMode[] r2 = com.unity3d.services.ar.ARUtils.planeFindingModes     // Catch: org.json.JSONException -> L59
            int r3 = r2.length     // Catch: org.json.JSONException -> L59
            r4 = 0
        L44:
            if (r4 >= r3) goto L5e
            r5 = r2[r4]     // Catch: org.json.JSONException -> L59
            java.lang.String r6 = r5.name()     // Catch: org.json.JSONException -> L59
            boolean r6 = r8.equalsIgnoreCase(r6)     // Catch: org.json.JSONException -> L59
            if (r6 == 0) goto L56
            r0.setPlaneFindingMode(r5)     // Catch: org.json.JSONException -> L59
            goto L5e
        L56:
            int r4 = r4 + 1
            goto L44
        L59:
            java.lang.String r8 = "planeFindingMode is not a string."
            com.unity3d.services.core.log.DeviceLog.warning(r8)
        L5e:
            java.lang.String r8 = "updateMode"
            boolean r8 = r7.has(r8)
            if (r8 == 0) goto L89
            java.lang.String r8 = "updateMode"
            java.lang.String r7 = r7.getString(r8)     // Catch: org.json.JSONException -> L84
            com.google.ar.core.Config$UpdateMode[] r8 = com.unity3d.services.ar.ARUtils.updateModes     // Catch: org.json.JSONException -> L84
            int r2 = r8.length     // Catch: org.json.JSONException -> L84
        L6f:
            if (r1 >= r2) goto L89
            r3 = r8[r1]     // Catch: org.json.JSONException -> L84
            java.lang.String r4 = r3.name()     // Catch: org.json.JSONException -> L84
            boolean r4 = r7.equalsIgnoreCase(r4)     // Catch: org.json.JSONException -> L84
            if (r4 == 0) goto L81
            r0.setUpdateMode(r3)     // Catch: org.json.JSONException -> L84
            goto L89
        L81:
            int r1 = r1 + 1
            goto L6f
        L84:
            java.lang.String r7 = "updateMode is not a string."
            com.unity3d.services.core.log.DeviceLog.warning(r7)
        L89:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.services.ar.ARUtils.createConfiguration(org.json.JSONObject, com.google.ar.core.Session):com.google.ar.core.Config");
    }

    @TargetApi(19)
    public static JSONObject getConfigEnums() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            ArrayList arrayList = new ArrayList();
            for (Config.LightEstimationMode lightEstimationMode : Config.LightEstimationMode.values()) {
                arrayList.add(lightEstimationMode.name());
            }
            jSONObject.put("lightEstimationMode", new JSONArray(arrayList.toArray()));
            arrayList.clear();
            for (Config.PlaneFindingMode planeFindingMode : Config.PlaneFindingMode.values()) {
                arrayList.add(planeFindingMode.name());
            }
            jSONObject.put("planeFindingMode", new JSONArray(arrayList.toArray()));
            arrayList.clear();
            for (Config.UpdateMode updateMode : Config.UpdateMode.values()) {
                arrayList.add(updateMode.name());
            }
            jSONObject.put("updateMode", new JSONArray(arrayList.toArray()));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
