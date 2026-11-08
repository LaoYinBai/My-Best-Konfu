package com.huawei.hms.support.api.push.b.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.hms.utils.Util;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.net.URISyntaxException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String[] a = {"phone", "url", PushConstants.EXTRA_APPLICATION_PENDING_INTENT, "cosa", "rp"};
    private Context b;
    private com.huawei.hms.support.api.push.b.b.a c;

    public a(Context context, com.huawei.hms.support.api.push.b.b.a aVar) {
        this.b = context;
        this.c = aVar;
    }

    public static boolean a(String str) {
        for (String str2 : a) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void a() throws URISyntaxException {
        HMSLog.d("PushSelfShowLog", "enter launchNotify()");
        if (this.b == null || this.c == null) {
            HMSLog.d("PushSelfShowLog", "launchNotify  context or msg is null");
            return;
        }
        if (PushConstants.EXTRA_APPLICATION_PENDING_INTENT.equals(this.c.j())) {
            c();
            return;
        }
        if ("cosa".equals(this.c.j())) {
            f();
            return;
        }
        if ("phone".equals(this.c.j())) {
            b();
            return;
        }
        if ("rp".equals(this.c.j())) {
            HMSLog.w("PushSelfShowLog", this.c.j() + " not support rich message.");
            return;
        }
        if ("url".equals(this.c.j())) {
            HMSLog.w("PushSelfShowLog", this.c.j() + " not support URL.");
            return;
        }
        HMSLog.d("PushSelfShowLog", this.c.j() + " is not exist in hShowType");
    }

    private void b() {
        HMSLog.d("PushSelfShowLog", "enter launchCall");
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.DIAL").setData(Uri.parse("tel:" + this.c.n())).setFlags(268435456);
            this.b.startActivity(intent);
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", e.toString(), e);
        }
    }

    private void c() {
        try {
            HMSLog.i("PushSelfShowLog", "enter launchApp, appPackageName =" + this.c.o() + ",and msg.intentUri is " + this.c.g());
            if (com.huawei.hms.support.api.push.b.d.a.c(this.b, this.c.o())) {
                f();
            } else {
                HMSLog.i("PushSelfShowLog", "enter launch app, appPackageName =" + this.c.o() + ",and msg.intentUri is " + this.c.g());
                d();
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "launchApp error:" + e.toString());
        }
    }

    private String b(String str) {
        try {
            int iIndexOf = str.indexOf(63);
            if (iIndexOf == -1) {
                return str;
            }
            int i = iIndexOf + 1;
            String[] strArrSplit = str.substring(i).split(ContainerUtils.FIELD_DELIMITER);
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArrSplit) {
                if (!str2.startsWith("h_w_hiapp_referrer") && !str2.startsWith("h_w_gp_referrer")) {
                    arrayList.add(str2);
                }
            }
            StringBuilder sb = new StringBuilder();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                sb.append((String) arrayList.get(i2));
                if (i2 < arrayList.size() - 1) {
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            String strSubstring = arrayList.size() == 0 ? str.substring(0, iIndexOf) : str.substring(0, i) + sb.toString();
            HMSLog.d("PushSelfShowLog", "after delete referrer, the new IntentUri is:" + strSubstring);
            return strSubstring;
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "delete referrer exception" + e.toString());
            return str;
        }
    }

    private void d() {
        try {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.c.g())) {
                sb.append("&referrer=");
                sb.append(Uri.encode(b(this.c.g())));
            }
            String str = "market://details?id=" + this.c.o() + ((Object) sb);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.setPackage(PackageConstants.SERVICES_PACKAGE_APPMARKET);
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(str));
            intent2.setPackage("com.android.vending");
            if (com.huawei.hms.support.api.push.b.d.a.a(this.b, "com.android.vending", intent2).booleanValue()) {
                intent2.setFlags(402653184);
                HMSLog.i("PushSelfShowLog", "open google play store's app detail, IntentUrl is:" + intent2.toURI());
                this.b.startActivity(intent2);
                return;
            }
            if (com.huawei.hms.support.api.push.b.d.a.a(this.b, PackageConstants.SERVICES_PACKAGE_APPMARKET, intent).booleanValue()) {
                intent.setFlags(402653184);
                HMSLog.i("PushSelfShowLog", "open HiApp's app detail, IntentUrl is:" + intent.toURI());
                this.b.startActivity(intent);
                return;
            }
            HMSLog.i("PushSelfShowLog", "open app detail by browser.");
            e();
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "open market app detail failed,exception:" + e);
        }
    }

    private void e() {
        String strDecode;
        String queryParameter = "";
        String queryParameter2 = "";
        try {
            Uri uri = Uri.parse(Uri.decode(this.c.g()));
            try {
                queryParameter = uri.getQueryParameter("h_w_hiapp_referrer");
            } catch (Exception unused) {
                HMSLog.i("PushSelfShowLog", "parse h_w_hiapp_referrer faied");
            }
            try {
                queryParameter2 = uri.getQueryParameter("h_w_gp_referrer");
            } catch (Exception unused2) {
                HMSLog.i("PushSelfShowLog", "parse h_w_hiapp_referrer faied");
            }
        } catch (Exception e) {
            HMSLog.w("PushSelfShowLog", "parse intentUri error" + e.toString());
        }
        String string = null;
        if (!Util.isEMUI() || !Util.isChinaROM()) {
            HMSLog.i("PushSelfShowLog", "not EMUI system or not in China, open google play web, referrer: " + queryParameter2);
            strDecode = Uri.decode(queryParameter2);
            if (!URLUtil.isValidUrl(strDecode)) {
                ResourceLoaderUtil.setmContext(this.b);
                strDecode = ResourceLoaderUtil.getString("hms_push_google") + this.c.o();
            }
        } else {
            HMSLog.i("PushSelfShowLog", "It is China device, open Huawei market web, referrer: " + queryParameter);
            strDecode = Uri.decode(queryParameter);
            if (!URLUtil.isValidUrl(strDecode)) {
                if (this.b != null) {
                    ResourceLoaderUtil.setmContext(this.b);
                    string = ResourceLoaderUtil.getString("hms_push_vmall");
                }
            }
            HMSLog.i("PushSelfShowLog", "open the URL by browser: " + string);
            com.huawei.hms.support.api.push.b.d.a.d(this.b, string);
        }
        string = strDecode;
        HMSLog.i("PushSelfShowLog", "open the URL by browser: " + string);
        com.huawei.hms.support.api.push.b.d.a.d(this.b, string);
    }

    private void f() throws URISyntaxException {
        HMSLog.i("PushSelfShowLog", "run into launchCosaApp ");
        try {
            HMSLog.i("PushSelfShowLog", "enter launchExistApp cosa, appPackageName =" + this.c.o() + ",and msg.intentUri is " + this.c.g());
            Intent intentB = com.huawei.hms.support.api.push.b.d.a.b(this.b, this.c.o());
            boolean z = false;
            if (this.c.g() != null) {
                try {
                    Intent uri = Intent.parseUri(this.c.g(), 0);
                    HMSLog.i("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0)," + uri.toURI());
                    boolean zBooleanValue = com.huawei.hms.support.api.push.b.d.a.a(this.b, this.c.o(), uri).booleanValue();
                    if (zBooleanValue) {
                        intentB = uri;
                    }
                    z = zBooleanValue;
                } catch (Exception e) {
                    HMSLog.w("PushSelfShowLog", "intentUri error" + e.toString());
                }
            } else {
                if (this.c.p() != null) {
                    Intent intent = new Intent(this.c.p());
                    if (com.huawei.hms.support.api.push.b.d.a.a(this.b, this.c.o(), intent).booleanValue()) {
                        intentB = intent;
                    }
                }
                intentB.setPackage(this.c.o());
            }
            if (intentB == null) {
                HMSLog.i("PushSelfShowLog", "launchCosaApp,intent == null");
                return;
            }
            if (!com.huawei.hms.support.api.push.b.d.a.a(this.b, intentB)) {
                HMSLog.w("PushSelfShowLog", "no permission to start Activity");
                return;
            }
            if (z) {
                intentB.addFlags(268435456);
            } else {
                intentB.setFlags(805437440);
            }
            HMSLog.i("PushSelfShowLog", "start " + intentB.toURI());
            this.b.startActivity(intentB);
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", "launch Cosa App exception" + e2.toString());
        }
    }
}
