package com.qq.e.comm.b;

import android.content.Context;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.plugin.d;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final a b = new a();
    private volatile Boolean a = Boolean.FALSE;

    /* renamed from: com.qq.e.comm.b.a$a, reason: collision with other inner class name */
    class C0000a implements NetworkCallBack {
        final /* synthetic */ PM a;

        C0000a(a aVar, PM pm) {
            this.a = pm;
        }

        @Override // com.qq.e.comm.net.NetworkCallBack
        public void onException(Exception exc) {
            GDTLogger.d("ActivateError");
        }

        @Override // com.qq.e.comm.net.NetworkCallBack
        public void onResponse(Request request, Response response) throws IllegalStateException, JSONException {
            String str;
            StringBuilder sb;
            try {
                if (response.getStatusCode() == 200) {
                    String stringContent = response.getStringContent();
                    GDTLogger.d("ACTIVERESPONSE:" + stringContent);
                    if (StringUtil.isEmpty(stringContent)) {
                        GDTLogger.d("SDK Server response empty string,maybe zip or tea format error");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(stringContent);
                    int i = jSONObject.has(Constants.KEYS.RET) ? jSONObject.getInt(Constants.KEYS.RET) : -1;
                    if (i == 0) {
                        if (this.a != null) {
                            try {
                                this.a.getPOFactory().config(1, stringContent);
                            } catch (d e) {
                                e.printStackTrace();
                            }
                            if (jSONObject.has("sig")) {
                                JSONObject jSONObject2 = jSONObject.getJSONObject("sig");
                                if (jSONObject2.has("jar") && jSONObject2.has("url")) {
                                    this.a.update(jSONObject2.getString("jar"), jSONObject2.getString("url"));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    sb = new StringBuilder();
                    sb.append("Response Error,retCode=");
                    sb.append(i);
                } else {
                    sb = new StringBuilder();
                    sb.append("SDK server response code error while launch or activate,code:");
                    sb.append(response.getStatusCode());
                }
                GDTLogger.d(sb.toString());
            } catch (IOException unused) {
                str = "ActivateError";
                GDTLogger.d(str);
            } catch (JSONException unused2) {
                str = "Parse Active or launch response exception";
                GDTLogger.d(str);
            }
        }
    }

    public static a a() {
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.qq.e.comm.managers.setting.SM r5, com.qq.e.comm.managers.plugin.PM r6, com.qq.e.comm.managers.status.DeviceStatus r7, com.qq.e.comm.managers.status.APPStatus r8, android.content.Context r9, long r10) throws org.json.JSONException {
        /*
            r4 = this;
            org.json.JSONObject r9 = new org.json.JSONObject
            r9.<init>()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9c
            r0.<init>()     // Catch: org.json.JSONException -> L9c
            if (r5 == 0) goto L1e
            java.lang.String r1 = r5.getSuid()     // Catch: org.json.JSONException -> L9c
            java.lang.String r2 = "suid"
            r0.putOpt(r2, r1)     // Catch: org.json.JSONException -> L9c
            java.lang.String r1 = r5.getSid()     // Catch: org.json.JSONException -> L9c
            java.lang.String r2 = "sid"
            r0.putOpt(r2, r1)     // Catch: org.json.JSONException -> L9c
        L1e:
            java.lang.String r9 = "sig"
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9d
            r1.<init>()     // Catch: org.json.JSONException -> L9d
            if (r5 == 0) goto L39
            java.lang.String r2 = r5.getDevCloudSettingSig()     // Catch: org.json.JSONException -> L9d
            java.lang.String r3 = "app"
            r1.putOpt(r3, r2)     // Catch: org.json.JSONException -> L9d
            java.lang.String r2 = r5.getSdkCloudSettingSig()     // Catch: org.json.JSONException -> L9d
            java.lang.String r3 = "sdk"
            r1.putOpt(r3, r2)     // Catch: org.json.JSONException -> L9d
        L39:
            if (r6 == 0) goto L51
            java.lang.String r2 = r6.getLocalSig()     // Catch: org.json.JSONException -> L9d
            java.lang.String r3 = "jar"
            r1.putOpt(r3, r2)     // Catch: org.json.JSONException -> L9d
            int r2 = r6.getPluginVersion()     // Catch: org.json.JSONException -> L9d
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L9d
            java.lang.String r3 = "plugin_version"
            r1.putOpt(r3, r2)     // Catch: org.json.JSONException -> L9d
        L51:
            r0.put(r9, r1)     // Catch: org.json.JSONException -> L9d
            java.lang.String r9 = "dev"
            org.json.JSONObject r1 = com.qq.e.comm.net.a.b(r7)     // Catch: org.json.JSONException -> L9d
            r0.put(r9, r1)     // Catch: org.json.JSONException -> L9d
            java.lang.String r9 = "app"
            org.json.JSONObject r8 = com.qq.e.comm.net.a.a(r8)     // Catch: org.json.JSONException -> L9d
            r0.put(r9, r8)     // Catch: org.json.JSONException -> L9d
            org.json.JSONObject r7 = com.qq.e.comm.net.a.a(r7)     // Catch: org.json.JSONException -> L9d
            java.lang.String r8 = "c"
            r0.put(r8, r7)     // Catch: org.json.JSONException -> L9d
            java.lang.String r7 = "sdk"
            org.json.JSONObject r8 = com.qq.e.comm.net.a.a(r6)     // Catch: org.json.JSONException -> L9d
            r0.put(r7, r8)     // Catch: org.json.JSONException -> L9d
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9d
            r7.<init>()     // Catch: org.json.JSONException -> L9d
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9d
            r8.<init>()     // Catch: org.json.JSONException -> L9d
            java.lang.String r9 = "sdk_init_time"
            long r1 = java.lang.System.nanoTime()     // Catch: org.json.JSONException -> L9d
            r3 = 0
            long r1 = r1 - r10
            r10 = 1000000(0xf4240, double:4.940656E-318)
            long r1 = r1 / r10
            r8.put(r9, r1)     // Catch: org.json.JSONException -> L9d
            java.lang.String r9 = "performance"
            r7.put(r9, r8)     // Catch: org.json.JSONException -> L9d
            java.lang.String r8 = "biz"
            r0.put(r8, r7)     // Catch: org.json.JSONException -> L9d
            goto La2
        L9c:
            r0 = r9
        L9d:
            java.lang.String r7 = "JSONException while build init req"
            com.qq.e.comm.util.GDTLogger.d(r7)
        La2:
            java.lang.String r7 = r0.toString()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "launch request: "
            r8.append(r9)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            com.qq.e.comm.util.GDTLogger.d(r8)
            java.lang.String r8 = "https://sdk.e.qq.com/activate"
            java.lang.String r5 = r5.getSuid()
            boolean r5 = com.qq.e.comm.util.StringUtil.isEmpty(r5)
            if (r5 != 0) goto Lc8
            java.lang.String r8 = "https://sdk.e.qq.com/launch"
        Lc8:
            java.lang.System.currentTimeMillis()
            com.qq.e.comm.b.a$a r5 = new com.qq.e.comm.b.a$a
            r5.<init>(r4, r6)
            com.qq.e.comm.net.rr.S2SSRequest r6 = new com.qq.e.comm.net.rr.S2SSRequest
            java.lang.String r9 = "UTF-8"
            java.nio.charset.Charset r9 = java.nio.charset.Charset.forName(r9)
            byte[] r7 = r7.getBytes(r9)
            r6.<init>(r8, r7)
            com.qq.e.comm.net.NetworkClient r7 = com.qq.e.comm.net.NetworkClientImpl.getInstance()
            com.qq.e.comm.net.NetworkClient$Priority r8 = com.qq.e.comm.net.NetworkClient.Priority.High
            r7.submit(r6, r8, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.b.a.a(com.qq.e.comm.managers.setting.SM, com.qq.e.comm.managers.plugin.PM, com.qq.e.comm.managers.status.DeviceStatus, com.qq.e.comm.managers.status.APPStatus, android.content.Context, long):void");
    }

    public void a(Context context, SM sm, PM pm, DeviceStatus deviceStatus, APPStatus aPPStatus, long j) {
        if (this.a.booleanValue()) {
            return;
        }
        synchronized (a.class) {
            if (this.a.booleanValue()) {
                return;
            }
            a(sm, pm, deviceStatus, aPPStatus, context, j);
            this.a = Boolean.TRUE;
        }
    }
}
