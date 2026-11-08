package com.huawei.hms.framework.network.grs;

import android.annotation.SuppressLint;
import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.plugin.BasePlugin;
import com.huawei.hms.framework.network.restclient.hwhttp.plugin.PluginInterceptor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class GrsManager implements BasePlugin {
    private static final int GRS_KEY_INDEX = 1;
    private static final int GRS_PATH_INDEX = 2;
    private static final String GRS_SCHEMA = "grs://";
    private static final int GRS_SERVICE_INDEX = 0;
    private static final int MAX_GRS_SPLIT = 3;
    private static final String SEPARATOR = "/";
    private static final String TAG = "GrsManager";

    @SuppressLint({"StaticFieldLeak"})
    private static volatile GrsManager instance;
    private GrsConfig grsConfig;
    private e grsInterceptor;

    private GrsManager() {
    }

    public static GrsManager getInstance() {
        if (instance == null) {
            synchronized (GrsManager.class) {
                if (instance == null) {
                    instance = new GrsManager();
                }
            }
        }
        return instance;
    }

    private String getServiceNameUrl(String str, String str2) {
        return GrsApi.synGetGrsUrl(str, str2);
    }

    public static boolean isGRSSchema(String str) {
        return str != null && str.startsWith(GRS_SCHEMA);
    }

    private String[] parseGRSSchema(String str) {
        return str.substring(str.toLowerCase().indexOf(GRS_SCHEMA) + GRS_SCHEMA.length()).split(SEPARATOR, 3);
    }

    public PluginInterceptor getInterceptor() {
        if (this.grsInterceptor == null) {
            this.grsInterceptor = new e();
        }
        return this.grsInterceptor;
    }

    public boolean initGrs(Context context, GrsConfig grsConfig) {
        if (this.grsConfig != null && this.grsConfig.equal(grsConfig)) {
            return true;
        }
        this.grsConfig = grsConfig;
        if (context != null) {
            context = context.getApplicationContext();
        }
        GrsApi.grsSdkInit(context, this.grsConfig != null ? this.grsConfig.getGrsBaseInfo(context) : null);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String parseGrs(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "/"
            boolean r0 = r6.endsWith(r0)
            r1 = 1
            if (r0 == 0) goto L18
            java.lang.String r0 = "grs://"
            int r0 = r6.indexOf(r0)
            int r2 = r6.length()
            int r2 = r2 - r1
            java.lang.String r6 = r6.substring(r0, r2)
        L18:
            java.lang.String[] r6 = r5.parseGRSSchema(r6)
            java.lang.String r0 = ""
            int r2 = r6.length
            r3 = 0
            r4 = 2
            if (r2 != r1) goto L2c
            r0 = r6[r3]
            java.lang.String r1 = "ROOT"
        L27:
            java.lang.String r0 = r5.getServiceNameUrl(r0, r1)
            goto L3b
        L2c:
            int r2 = r6.length
            if (r2 < r4) goto L34
            r0 = r6[r3]
            r1 = r6[r1]
            goto L27
        L34:
            java.lang.String r1 = "GrsManager"
            java.lang.String r2 = "parseGrs params.length<1."
            com.huawei.hms.framework.common.Logger.i(r1, r2)
        L3b:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L6f
            int r1 = r6.length
            if (r1 <= r4) goto L6e
            java.lang.String r1 = "/"
            boolean r1 = r0.endsWith(r1)
            if (r1 == 0) goto L5e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r6 = r6[r4]
        L56:
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            goto L6e
        L5e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "/"
            r1.append(r0)
            r6 = r6[r4]
            goto L56
        L6e:
            return r0
        L6f:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "can not get url, do grsUrl(serviceName or key) error?"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.GrsManager.parseGrs(java.lang.String):java.lang.String");
    }
}
