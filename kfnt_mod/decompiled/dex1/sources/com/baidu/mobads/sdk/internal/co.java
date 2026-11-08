package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.ArticleInfo;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.api.XAdNativeResponse;
import com.baidu.mobads.sdk.internal.e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class co extends ar {
    private List<NativeResponse> o;
    private int p;
    private boolean q;
    private String r;
    private String s;
    private int t;
    private int u;
    private RequestParameters v;
    private boolean w;
    private e.a x;
    private e.b y;

    public co(Context context, String str, String str2, boolean z, int i) {
        super(context);
        this.p = 8000;
        this.w = false;
        this.s = str;
        this.r = str2;
        this.q = z;
        this.p = i;
        this.t = 600;
        this.u = 500;
    }

    public void a(e.a aVar) {
        this.x = aVar;
    }

    public String f() {
        return this.r;
    }

    public void a(e.b bVar) {
        this.y = bVar;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() {
        if (this.j == null) {
            this.k = false;
        } else {
            this.k = true;
            this.j.loadAd(k(), l());
        }
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.t = width;
            this.u = height;
        }
        this.v = requestParameters;
        a(requestParameters.getExtras());
    }

    public void c(boolean z) {
        this.w = z;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(IOAdEvent iOAdEvent) {
        if (this.y == null || iOAdEvent == null || this.o == null) {
            return;
        }
        String message = iOAdEvent.getMessage();
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(message)) {
                this.y.a(xAdNativeResponse);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c1 A[SYNTHETIC] */
    @Override // com.baidu.mobads.sdk.internal.ar
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void q() {
        /*
            r14 = this;
            com.baidu.mobads.sdk.internal.e$a r0 = r14.x
            if (r0 == 0) goto Lcc
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r14.m
            com.baidu.mobads.sdk.internal.b r1 = com.baidu.mobads.sdk.internal.b.a(r1)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r3 = 0
            r4 = 0
        L16:
            java.util.List r5 = r1.a()
            int r5 = r5.size()
            if (r4 >= r5) goto Lc5
            java.util.List r5 = r1.a()
            java.lang.Object r5 = r5.get(r4)
            com.baidu.mobads.sdk.internal.a r5 = (com.baidu.mobads.sdk.internal.a) r5
            java.lang.String r6 = r5.m()
            int r7 = r5.p()
            r8 = 3
            r9 = 2
            r10 = 1
            if (r7 != r10) goto L3b
        L37:
            r6 = 0
        L38:
            r8 = 1
            goto Lab
        L3b:
            int r7 = r5.p()
            if (r7 != r9) goto L6c
            if (r6 == 0) goto L6a
            java.lang.String r7 = ""
            boolean r7 = r6.equals(r7)
            if (r7 != 0) goto L6a
            java.lang.String r7 = "null"
            boolean r7 = r6.equals(r7)
            if (r7 != 0) goto L6a
            boolean r7 = r2.contains(r6)
            if (r7 == 0) goto L5a
            goto L6a
        L5a:
            r2.add(r6)
            android.content.Context r7 = r14.g
            boolean r6 = com.baidu.mobads.sdk.internal.bc.a(r7, r6)
            if (r6 == 0) goto L67
        L65:
            r6 = 0
            goto Lab
        L67:
            r6 = 0
            r8 = 2
            goto Lab
        L6a:
            r6 = 1
            goto L38
        L6c:
            int r7 = r5.p()
            r11 = 512(0x200, float:7.17E-43)
            if (r7 != r11) goto L37
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L92
            java.lang.String r11 = r5.q()     // Catch: java.lang.Throwable -> L92
            r7.<init>(r11)     // Catch: java.lang.Throwable -> L92
            java.lang.String r11 = "fb_act"
            int r11 = r7.optInt(r11, r3)     // Catch: java.lang.Throwable -> L92
            java.lang.String r12 = "page"
            java.lang.String r13 = ""
            java.lang.String r7 = r7.optString(r12, r13)     // Catch: java.lang.Throwable -> L93
            android.content.Context r12 = r14.g     // Catch: java.lang.Throwable -> L93
            boolean r7 = com.baidu.mobads.sdk.internal.bc.b(r12, r7)     // Catch: java.lang.Throwable -> L93
            goto L94
        L92:
            r11 = 0
        L93:
            r7 = 0
        L94:
            if (r7 == 0) goto L97
            goto L65
        L97:
            if (r11 != r10) goto L9a
            goto L37
        L9a:
            if (r11 != r9) goto L37
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L67
            android.content.Context r7 = r14.g
            boolean r6 = com.baidu.mobads.sdk.internal.bc.a(r7, r6)
            if (r6 == 0) goto L67
            goto L65
        Lab:
            if (r6 != 0) goto Lc1
            com.baidu.mobads.sdk.api.XAdNativeResponse r6 = new com.baidu.mobads.sdk.api.XAdNativeResponse
            android.content.Context r7 = r14.g
            r6.<init>(r7, r14, r5)
            if (r8 != r9) goto Lb7
            goto Lb8
        Lb7:
            r10 = 0
        Lb8:
            r6.setIsDownloadApp(r10)
            r6.setAdActionType(r8)
            r0.add(r6)
        Lc1:
            int r4 = r4 + 1
            goto L16
        Lc5:
            r14.o = r0
            com.baidu.mobads.sdk.internal.e$a r1 = r14.x
            r1.a(r0)
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.sdk.internal.co.q():void");
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        if (this.x != null) {
            this.x.b(i, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        if (this.x != null) {
            this.x.a(i, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        String message = iOAdEvent.getMessage();
        if (this.x == null || TextUtils.isEmpty(message) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(message)) {
                this.x.a(xAdNativeResponse);
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void f(IOAdEvent iOAdEvent) {
        if (iOAdEvent == null) {
            return;
        }
        Map<String, Object> data = iOAdEvent.getData();
        if (this.x == null || data == null || this.o == null) {
            return;
        }
        String str = (String) data.get("instanceInfo");
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse != null && xAdNativeResponse.getUniqueId().equals(str)) {
                this.x.a(xAdNativeResponse, Integer.parseInt((String) data.get("showState")));
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        String message = iOAdEvent.getMessage();
        if (this.x == null || TextUtils.isEmpty(message) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(message)) {
                this.x.b(xAdNativeResponse);
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e() {
        if (this.x != null) {
            this.x.a();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d_() {
        if (this.x != null) {
            this.x.b();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c() {
        if (this.x != null) {
            this.x.c();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c(String str) {
        if (TextUtils.isEmpty(str) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(str)) {
                xAdNativeResponse.onAdUnionClick();
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d(String str) {
        if (TextUtils.isEmpty(str) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(str)) {
                xAdNativeResponse.onADPrivacyClick();
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, boolean z) {
        if (TextUtils.isEmpty(str) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(str)) {
                xAdNativeResponse.onADPermissionShow(z);
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(String str, boolean z) {
        if (TextUtils.isEmpty(str) || this.o == null) {
            return;
        }
        for (int i = 0; i < this.o.size(); i++) {
            XAdNativeResponse xAdNativeResponse = (XAdNativeResponse) this.o.get(i);
            if (xAdNativeResponse.getUniqueId().equals(str)) {
                xAdNativeResponse.onAdDownloadWindow(z);
            }
        }
    }

    public RequestParameters g() {
        return this.v;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void a(Map<String, String> map) {
        int length;
        if (map == null || map.isEmpty()) {
            return;
        }
        HashMap map2 = new HashMap(map);
        HashMap<String, String> map3 = new HashMap<>();
        int i = 0;
        for (String str : ArticleInfo.PREDEFINED_KEYS) {
            if (map2.containsKey(str)) {
                String str2 = (String) map2.remove(str);
                if (!TextUtils.isEmpty(str2) && (length = str2.length() + i) < 150) {
                    map3.put(str, str2);
                    i = length;
                }
            }
        }
        if (!map2.isEmpty()) {
            for (String str3 : map2.keySet()) {
                if (!TextUtils.isEmpty(str3)) {
                    String str4 = (String) map2.get(str3);
                    if (!TextUtils.isEmpty(str4)) {
                        int length2 = i + str3.length() + str4.length();
                        if (length2 >= 150) {
                            break;
                        }
                        map3.put("c_" + str3, str4);
                        i = length2 + 2;
                    } else {
                        continue;
                    }
                }
            }
        }
        this.l = map3;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(IAdInterListener.AdReqParam.PROD, this.r);
            this.j.createProdHandler(jSONObject2);
            this.j.setAdContainer(this.f);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, this.r);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.s);
            if (by.a().b()) {
                jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,MSSP,VIDEO,NMON,HTML");
            } else {
                jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,MSSP,VIDEO,NMON,HTML,CLICK2VIDEO");
            }
            jSONObject.put(IAdInterListener.AdReqParam.AD_COUNT, "1");
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            if ("video".equals(this.r)) {
                jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "10");
                jSONObject.put(IAdInterListener.AdReqParam.MIME_TYPE, "video/mp4,image/jpg,image/gif,image/png");
                jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,HTML,MSSP,VIDEO,NMON");
            } else {
                jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "2");
            }
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + this.t);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + this.u);
            jSONObject.put("msa", 7);
            return j.a(jSONObject, b(this.l));
        } catch (Throwable th) {
            th.printStackTrace();
            return jSONObject;
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timeout", this.p);
            jSONObject.put("isCacheVideo", this.q);
            jSONObject.put("cacheVideoOnlyWifi", this.w);
            jSONObject.put("appConfirmPolicy", this.v == null ? 1 : this.v.getAPPConfirmPolicy());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
