package com.bytedance.sdk.adtnc.c;

import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.sdk.b.a f5089a;

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.sdk.b.b f5090b;
    private com.bytedance.sdk.adtnc.b.b d;
    private long g;
    private Handler e = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.sdk.adtnc.c.d.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            d dVar;
            boolean z;
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    dVar = d.this;
                    z = false;
                    break;
                case 2:
                    dVar = d.this;
                    z = true;
                    break;
                case 3:
                    d.this.a((JSONObject) message.obj);
                    return;
                case 4:
                    d.this.b();
                    return;
                default:
                    return;
            }
            dVar.b(z);
        }
    };
    private int h = 0;

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.sdk.b.b f5091c = new com.bytedance.sdk.adtnc.d.a();
    private boolean f = false;

    public d(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        this.f5089a = aVar;
    }

    private String a(String str) {
        Address addressA = this.f5089a.a(this.f5089a.a());
        com.bytedance.sdk.adtnc.g.e eVar = new com.bytedance.sdk.adtnc.g.e("https://" + str + "/get_domains/v4/");
        if (addressA != null && addressA.hasLatitude() && addressA.hasLongitude()) {
            eVar.a("latitude", addressA.getLatitude());
            eVar.a("longitude", addressA.getLongitude());
            String locality = addressA.getLocality();
            if (!TextUtils.isEmpty(locality)) {
                eVar.a("city", Uri.encode(locality));
            }
        }
        try {
            eVar.a("abi", Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        eVar.a(this.f5089a.b() ? "sdk_app_id" : "aid", this.f5089a.c());
        eVar.a(this.f5089a.b() ? PluginConstants.KEY_SDK_VERSION : "version_code", this.f5089a.d());
        eVar.a("device_platform", DispatchConstants.ANDROID);
        eVar.a("channel", this.f5089a.f());
        eVar.a("custom_info_1", this.f5089a.e());
        eVar.a("ad_tnc_version_code", 1);
        com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "buildUrl", "result url:", eVar);
        return eVar.toString();
    }

    private void a() {
        this.e.removeMessages(1);
        this.e.removeMessages(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "innerRefreshConfigSuccess", "success");
        if (this.d != null) {
            this.d.a(jSONObject);
        }
        this.g = System.currentTimeMillis();
        this.h = 0;
        this.f = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr, int i) {
        if (strArr == null || strArr.length <= i) {
            this.e.sendEmptyMessage(4);
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "getDomainInternalNext", "configServers array out of bounds");
            return;
        }
        try {
            String str = strArr[i];
            if (TextUtils.isEmpty(str)) {
                com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "getDomainInternalNext", "configServer is empty");
                a(strArr, i + 1);
                return;
            }
            String strA = a(str);
            if (TextUtils.isEmpty(strA)) {
                com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "getDomainInternalNext", "url is empty");
                a(strArr, i + 1);
                return;
            }
            com.bytedance.sdk.adtnc.sdk.b.b bVar = this.f5090b;
            if (bVar == null) {
                bVar = this.f5091c;
            }
            JSONObject jSONObject = new JSONObject(bVar.a(this.f5089a, strA, null));
            if (!"success".equals(jSONObject.getString("message"))) {
                throw new Exception("response json is failure");
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = jSONObject;
            this.e.sendMessage(messageObtain);
        } catch (Throwable th) {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "getDomainInternalNext", (Object) "try app config exception:", th);
            a(strArr, i + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "innerRefreshConfigFailure", "failure");
        if (this.h < 3) {
            this.h++;
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "innerRefreshConfigFailure", "schedule refresh again after 3 minutes");
            this.e.sendEmptyMessageDelayed(1, 180000L);
        } else {
            com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "innerRefreshConfigFailure", "retry refresh over times");
        }
        this.f = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.bytedance.sdk.adtnc.c.d$2] */
    public void b(boolean z) {
        String str;
        String str2;
        String str3;
        if (this.f) {
            str = "TNCConfigRefreshManager";
            str2 = "innerRefreshConfig";
            str3 = "config refreshing";
        } else if (com.bytedance.sdk.adtnc.g.d.c(this.f5089a.a())) {
            String[] strArrG = this.f5089a.g();
            if (strArrG != null && strArrG.length != 0) {
                if (!z && System.currentTimeMillis() - this.g <= 180000) {
                    com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "innerRefreshConfig", "Less than 3 minutes since last update");
                    return;
                } else {
                    this.f = true;
                    new Thread("AppConfigRefreshThread") { // from class: com.bytedance.sdk.adtnc.c.d.2
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            d.this.a(d.this.f5089a.g(), 0);
                        }
                    }.start();
                    return;
                }
            }
            str = "TNCConfigRefreshManager";
            str2 = "innerRefreshConfig";
            str3 = "configServices is empty";
        } else {
            str = "TNCConfigRefreshManager";
            str2 = "innerRefreshConfig";
            str3 = " network bad";
        }
        com.bytedance.sdk.adtnc.g.b.a(str, str2, str3);
    }

    public d a(com.bytedance.sdk.adtnc.b.b bVar) {
        this.d = bVar;
        return this;
    }

    public void a(boolean z) {
        Handler handler;
        int i;
        com.bytedance.sdk.adtnc.g.b.a("TNCConfigRefreshManager", "refreshConfig", "start refreshConfig isFocus =", Boolean.valueOf(z));
        a();
        if (z) {
            handler = this.e;
            i = 2;
        } else {
            handler = this.e;
            i = 1;
        }
        handler.sendEmptyMessage(i);
    }
}
