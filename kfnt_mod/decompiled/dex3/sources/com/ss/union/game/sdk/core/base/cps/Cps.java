package com.ss.union.game.sdk.core.base.cps;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.XORUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Cps implements ICpsInject {
    private static volatile Cps cps;
    private boolean mEnableInject = false;
    private boolean mIsSimpleCpsChannel = false;
    private String mSimpleCpsChannel = "";
    private CpsParamsParser cpsParamsParser = new CpsParamsParser();

    Cps() throws Throwable {
        init();
    }

    public static Cps getInstance() {
        if (cps == null) {
            synchronized (Cps.class) {
                if (cps == null) {
                    cps = new Cps();
                }
            }
        }
        return cps;
    }

    private static String inputStream2Str(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toString("UTF-8");
            }
        }
    }

    private static JSONObject decrypt(String str) throws Exception {
        try {
            return new JSONObject(str);
        } catch (Exception unused) {
            String strDecrypt = XORUtils.decrypt(str);
            CpsLog.d("解析sdk参数成功： " + strDecrypt);
            return new JSONObject(strDecrypt);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean initFromAssets(android.content.Context r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            java.lang.String r1 = "lg-cps-sdk-services.json"
            java.io.InputStream r5 = r5.open(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            java.lang.String r0 = inputStream2Str(r5)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            org.json.JSONObject r0 = decrypt(r0)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            boolean r1 = r4.fastSimpleCpsInit(r0)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r2 = 1
            if (r1 == 0) goto L27
            java.lang.String r0 = "初始化cps配置成功"
            java.lang.String r1 = "简单的CPS渠道"
            com.ss.union.game.sdk.core.base.cps.CpsLog.d(r0, r1)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            if (r5 == 0) goto L26
            r5.close()     // Catch: java.lang.Throwable -> L26
        L26:
            return r2
        L27:
            com.ss.union.game.sdk.core.base.cps.CpsParamsParser r1 = new com.ss.union.game.sdk.core.base.cps.CpsParamsParser     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r1.<init>(r0)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r4.cpsParamsParser = r1     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r4.mEnableInject = r2     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            java.lang.String r0 = "初始化cps配置成功"
            java.lang.String r1 = "换参数的CPS渠道"
            com.ss.union.game.sdk.core.base.cps.CpsLog.d(r0, r1)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            if (r5 == 0) goto L3c
            r5.close()     // Catch: java.lang.Throwable -> L3c
        L3c:
            return r2
        L3d:
            r0 = move-exception
            goto L48
        L3f:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L59
        L44:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L48:
            java.lang.String r1 = "初始化cps配置失败，若未接入cps渠道可忽略此异常"
            java.lang.String r2 = android.util.Log.getStackTraceString(r0)     // Catch: java.lang.Throwable -> L58
            com.ss.union.game.sdk.core.base.cps.CpsLog.d(r1, r2, r0)     // Catch: java.lang.Throwable -> L58
            r0 = 0
            if (r5 == 0) goto L57
            r5.close()     // Catch: java.lang.Throwable -> L57
        L57:
            return r0
        L58:
            r0 = move-exception
        L59:
            if (r5 == 0) goto L5e
            r5.close()     // Catch: java.lang.Throwable -> L5e
        L5e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.base.cps.Cps.initFromAssets(android.content.Context):boolean");
    }

    private boolean fastSimpleCpsInit(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("simpleCps");
        if (!TextUtils.isEmpty(strOptString)) {
            this.mIsSimpleCpsChannel = true;
            this.mSimpleCpsChannel = strOptString;
        }
        return this.mIsSimpleCpsChannel;
    }

    private void init() throws Throwable {
        long jCurrentTimeMillis = System.currentTimeMillis();
        initFromAssets(GlobalApplicationUtils.getContext());
        CpsLog.d("CPS初始化耗时：" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
    }

    @Override // com.ss.union.game.sdk.core.base.cps.ICpsInject
    public String cpReplace(String str) {
        String str2 = this.cpsParamsParser.cpSelfParams.get(str);
        if (TextUtils.isEmpty(str2)) {
            CpsLog.forceE("cp替换三方sdk参数失败:参数=" + str);
            throw new RuntimeException("cp替换三方sdk参数失败:参数=" + str);
        }
        CpsLog.d("cpReplace替换参数: 参数" + str + "====>" + str2);
        return str2;
    }

    @Override // com.ss.union.game.sdk.core.base.cps.ICpsInject
    public String getAdCpsCodeId(String str) {
        if (isEnable()) {
            return this.cpsParamsParser.adParams.get(str);
        }
        return null;
    }

    @Override // com.ss.union.game.sdk.core.base.cps.ICpsInject
    public boolean isEnable() {
        return this.mEnableInject;
    }

    @Override // com.ss.union.game.sdk.core.base.cps.ICpsInject
    public boolean isPushEnable() {
        return this.cpsParamsParser.hasPush;
    }

    @Override // com.ss.union.game.sdk.core.base.cps.ICpsInject
    public boolean isSimpleCpsChannel() {
        return this.mIsSimpleCpsChannel;
    }
}
