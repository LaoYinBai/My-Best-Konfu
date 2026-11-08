package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static boolean a;

    public static long a(Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastSuccessfulBuildTime(context.getApplicationContext());
    }

    public static long b(Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastInstantBuildTime(context.getApplicationContext());
    }

    public static boolean a(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        if (b()) {
            return a(context, uMBusinessType, false);
        }
        return false;
    }

    public static boolean b(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        return a(context, uMBusinessType, false);
    }

    public static boolean a() {
        return b();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(android.content.Context r4, com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType r5, boolean r6) {
        /*
            if (r4 == 0) goto L3d
            android.content.Context r4 = r4.getApplicationContext()
            boolean r0 = com.umeng.commonsdk.framework.UMFrUtils.isOnline(r4)
            int r1 = com.umeng.commonsdk.framework.UMFrUtils.envelopeFileNumber(r4)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L36
            com.umeng.commonsdk.framework.UMLogDataProtocol$UMBusinessType r6 = com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType.U_INTERNAL
            if (r5 != r6) goto L20
            boolean r4 = com.umeng.commonsdk.framework.UMFrUtils.hasEnvelopeFile(r4, r5)
            if (r4 == 0) goto L1e
        L1c:
            r6 = 0
            goto L36
        L1e:
            r6 = 1
            goto L36
        L20:
            boolean r6 = com.umeng.commonsdk.framework.b.a()
            if (r6 == 0) goto L2f
            int r4 = com.umeng.commonsdk.framework.b.b()
            long r4 = (long) r4
            com.umeng.commonsdk.framework.c.a(r4)
            goto L1c
        L2f:
            boolean r4 = com.umeng.commonsdk.framework.UMFrUtils.hasEnvelopeFile(r4, r5)
            if (r4 == 0) goto L1e
            goto L1c
        L36:
            if (r0 == 0) goto L3d
            if (r1 <= 0) goto L3d
            com.umeng.commonsdk.framework.b.d()
        L3d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.a.a(android.content.Context, com.umeng.commonsdk.framework.UMLogDataProtocol$UMBusinessType, boolean):boolean");
    }

    public static JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) throws Throwable {
        JSONObject jSONObject3;
        JSONException e;
        ULog.d("--->>> buildEnvelopeFile Enter.");
        if (!UMGlobalContext.getInstance().isMainProcess(context)) {
            try {
                jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put(c.c, 101);
                } catch (JSONException e2) {
                    e = e2;
                    e.printStackTrace();
                    return jSONObject3;
                }
            } catch (JSONException e3) {
                jSONObject3 = null;
                e = e3;
            }
            return jSONObject3;
        }
        return new com.umeng.commonsdk.statistics.b().a(context.getApplicationContext(), jSONObject, jSONObject2);
    }

    public static String a(Context context, String str, String str2) {
        return context == null ? str2 : ImprintHandler.getImprintService(context.getApplicationContext()).c().a(str, str2);
    }

    public static long c(Context context) {
        if (context == null) {
            return 0L;
        }
        return com.umeng.commonsdk.statistics.b.a(context.getApplicationContext());
    }

    public static synchronized boolean b() {
        return a;
    }

    public static synchronized void a(boolean z) {
        a = z;
    }
}
