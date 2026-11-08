package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.data.a;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.f;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.k;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PayTask {

    /* renamed from: a, reason: collision with root package name */
    static final Object f2547a = e.class;
    private static long i = 0;
    private static final long j = 3000;
    private static long k = -1;

    /* renamed from: b, reason: collision with root package name */
    private Activity f2548b;

    /* renamed from: c, reason: collision with root package name */
    private com.alipay.sdk.widget.a f2549c;
    private final String d = "wappaygw.alipay.com/service/rest.htm";
    private final String e = "mclient.alipay.com/service/rest.htm";
    private final String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private final String g = "mclient.alipay.com/cashier/mobilepay.htm";
    private Map<String, a> h = new HashMap();

    public String getVersion() {
        return "15.7.9";
    }

    public PayTask(Activity activity) {
        this.f2548b = activity;
        com.alipay.sdk.sys.b.a().a(this.f2548b);
        this.f2549c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.f2675b);
    }

    public synchronized String pay(String str, boolean z) {
        return a(new com.alipay.sdk.sys.a(this.f2548b, str, "pay"), str, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0229 A[Catch: all -> 0x0245, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0007, B:9:0x0018, B:10:0x001b, B:12:0x0023, B:14:0x0037, B:15:0x003c, B:17:0x005d, B:19:0x0065, B:20:0x0068, B:22:0x006c, B:24:0x0074, B:25:0x0086, B:27:0x008e, B:31:0x00da, B:33:0x0127, B:34:0x0134, B:48:0x01c0, B:44:0x0157, B:46:0x01a4, B:47:0x01b1, B:54:0x01dc, B:56:0x0229, B:57:0x0236, B:58:0x0244, B:16:0x0058), top: B:62:0x0001 }] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized java.lang.String a(com.alipay.sdk.sys.a r8, java.lang.String r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.sys.a, java.lang.String, boolean):java.lang.String");
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        com.alipay.sdk.sys.a aVar;
        aVar = new com.alipay.sdk.sys.a(this.f2548b, str, "payV2");
        return i.a(aVar, a(aVar, str, z));
    }

    public synchronized String fetchTradeToken() {
        return f.a(new com.alipay.sdk.sys.a(this.f2548b, "", "fetchTradeToken"), this.f2548b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, final boolean z, final H5PayCallback h5PayCallback) {
        final String strFetchOrderInfoFromH5PayUrl;
        strFetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl)) {
            com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "intercepted: " + strFetchOrderInfoFromH5PayUrl);
            new Thread(new Runnable() { // from class: com.alipay.sdk.app.PayTask.1
                @Override // java.lang.Runnable
                public void run() {
                    H5PayResultModel h5PayResultModelH5Pay = PayTask.this.h5Pay(new com.alipay.sdk.sys.a(PayTask.this.f2548b, strFetchOrderInfoFromH5PayUrl, "payInterceptorWithUrl"), strFetchOrderInfoFromH5PayUrl, z);
                    com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "inc finished: " + h5PayResultModelH5Pay.getResultCode());
                    h5PayCallback.onPayResult(h5PayResultModelH5Pay);
                }
            }).start();
        }
        return !TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl);
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String strTrim = str.trim();
                if (strTrim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || strTrim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                    String strTrim2 = strTrim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(strTrim2)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + k.a("<request_token>", "</request_token>", k.b(strTrim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.f2548b, "", "").a("sc", "h5tonative") + "\"";
                    }
                }
                if (strTrim.startsWith("https://mclient.alipay.com/service/rest.htm") || strTrim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                    String strTrim3 = strTrim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(strTrim3)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + k.a("<request_token>", "</request_token>", k.b(strTrim3).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.f2548b, "", "").a("sc", "h5tonative") + "\"";
                    }
                }
                if ((strTrim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || strTrim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && ((strTrim.contains("alipay.wap.create.direct.pay.by.user") || strTrim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(strTrim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim()))) {
                    com.alipay.sdk.sys.a aVar = new com.alipay.sdk.sys.a(this.f2548b, "", "");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", str);
                    jSONObject.put("bizcontext", aVar.a("sc", "h5tonative"));
                    return "new_external_info==" + jSONObject.toString();
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher(str).find()) {
                    String strA = k.a("?", "", str);
                    if (!TextUtils.isEmpty(strA)) {
                        Map<String, String> mapB = k.b(strA);
                        StringBuilder sb = new StringBuilder();
                        if (a(false, true, com.alipay.sdk.app.statistic.b.ar, sb, mapB, com.alipay.sdk.app.statistic.b.ar, "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", sb, mapB, "payPhaseId", "pay_phase_id", "out_relation_id");
                            sb.append("&biz_sub_type=\"TRADE\"");
                            sb.append("&biz_type=\"trade\"");
                            String str2 = mapB.get(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY);
                            if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(mapB.get("cid"))) {
                                str2 = "ali1688";
                            } else if (TextUtils.isEmpty(str2) && (!TextUtils.isEmpty(mapB.get("sid")) || !TextUtils.isEmpty(mapB.get("s_id")))) {
                                str2 = "tb";
                            }
                            sb.append("&app_name=\"" + str2 + "\"");
                            if (!a(true, true, "extern_token", sb, mapB, "extern_token", "cid", "sid", "s_id")) {
                                return "";
                            }
                            a(true, false, "appenv", sb, mapB, "appenv");
                            sb.append("&pay_channel_id=\"alipay_sdk\"");
                            a aVar2 = new a();
                            aVar2.a(mapB.get("return_url"));
                            aVar2.c(mapB.get("show_url"));
                            aVar2.b(mapB.get("pay_order_id"));
                            String str3 = sb.toString() + "&bizcontext=\"" + new com.alipay.sdk.sys.a(this.f2548b, "", "").a("sc", "h5tonative") + "\"";
                            this.h.put(str3, aVar2);
                            return str3;
                        }
                    }
                }
                if (!strTrim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") && !strTrim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") && (!EnvUtils.isSandBox() || !strTrim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                    if (com.alipay.sdk.data.a.p().d() && Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher(strTrim).find()) {
                        Uri uri = Uri.parse(strTrim);
                        String queryParameter = uri.getQueryParameter("return_url");
                        String queryParameter2 = uri.getQueryParameter("show_url");
                        String queryParameter3 = uri.getQueryParameter("pay_order_id");
                        String strA2 = a(uri.getQueryParameter("trade_nos"), uri.getQueryParameter("alipay_trade_no"));
                        String strA3 = a(uri.getQueryParameter("payPhaseId"), uri.getQueryParameter("pay_phase_id"), uri.getQueryParameter("out_relation_id"));
                        String[] strArr = new String[4];
                        strArr[0] = uri.getQueryParameter(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY);
                        strArr[1] = !TextUtils.isEmpty(uri.getQueryParameter("cid")) ? "ali1688" : "";
                        strArr[2] = !TextUtils.isEmpty(uri.getQueryParameter("sid")) ? "tb" : "";
                        strArr[3] = !TextUtils.isEmpty(uri.getQueryParameter("s_id")) ? "tb" : "";
                        String strA4 = a(strArr);
                        String strA5 = a(uri.getQueryParameter("extern_token"), uri.getQueryParameter("cid"), uri.getQueryParameter("sid"), uri.getQueryParameter("s_id"));
                        String strA6 = a(uri.getQueryParameter("appenv"));
                        if (!TextUtils.isEmpty(strA2) && !TextUtils.isEmpty(strA4) && !TextUtils.isEmpty(strA5)) {
                            String str4 = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", strA2, strA3, strA4, strA5, strA6, new com.alipay.sdk.sys.a(this.f2548b, "", "").a("sc", "h5tonative"));
                            a aVar3 = new a();
                            aVar3.a(queryParameter);
                            aVar3.c(queryParameter2);
                            aVar3.b(queryParameter3);
                            aVar3.d(strA2);
                            this.h.put(str4, aVar3);
                            return str4;
                        }
                    }
                }
                String strA7 = new com.alipay.sdk.sys.a(this.f2548b, "", "").a("sc", "h5tonative");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("url", strTrim);
                jSONObject2.put("bizcontext", strA7);
                return String.format("new_external_info==%s", jSONObject2.toString());
            }
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
        return "";
    }

    private static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        try {
            com.alipay.sdk.sys.b.a().a(context);
            long jElapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (jElapsedRealtime - i < com.alipay.sdk.data.a.p().f()) {
                return false;
            }
            i = jElapsedRealtime;
            com.alipay.sdk.data.a.p().a(com.alipay.sdk.sys.a.a(), context.getApplicationContext());
            return true;
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            return false;
        }
    }

    private class a {

        /* renamed from: b, reason: collision with root package name */
        private String f2555b;

        /* renamed from: c, reason: collision with root package name */
        private String f2556c;
        private String d;
        private String e;

        private a() {
            this.f2555b = "";
            this.f2556c = "";
            this.d = "";
            this.e = "";
        }

        public String a() {
            return this.f2555b;
        }

        public void a(String str) {
            this.f2555b = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.f2556c;
        }

        public void c(String str) {
            this.f2556c = str;
        }

        public String d() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }
    }

    private boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2 = "";
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
        return true;
    }

    public synchronized H5PayResultModel h5Pay(com.alipay.sdk.sys.a aVar, String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        try {
            String[] strArrSplit = a(aVar, str, z).split(f.f2657b);
            HashMap map = new HashMap();
            for (String str2 : strArrSplit) {
                int iIndexOf = str2.indexOf("={");
                if (iIndexOf >= 0) {
                    String strSubstring = str2.substring(0, iIndexOf);
                    map.put(strSubstring, a(str2, strSubstring));
                }
            }
            if (map.containsKey(i.f2663a)) {
                h5PayResultModel.setResultCode(map.get(i.f2663a));
            }
            h5PayResultModel.setReturnUrl(a(str, map));
            if (TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ah, "");
            }
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ai, th);
            com.alipay.sdk.util.c.a(th);
        }
        return h5PayResultModel;
    }

    private String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean zEquals = "9000".equals(map.get(i.f2663a));
        String str2 = map.get("result");
        a aVarRemove = this.h.remove(str);
        String[] strArr = new String[2];
        strArr[0] = aVarRemove != null ? aVarRemove.b() : "";
        strArr[1] = aVarRemove != null ? aVarRemove.d() : "";
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String strA = a(k.a("&callBackUrl=\"", "\"", str2), k.a("&call_back_url=\"", "\"", str2), k.a(com.alipay.sdk.cons.a.r, "\"", str2), URLDecoder.decode(k.a(com.alipay.sdk.cons.a.s, "&", str2), "utf-8"), URLDecoder.decode(k.a("&callBackUrl=", "&", str2), "utf-8"), k.a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(strA)) {
                return strA;
            }
        }
        if (aVarRemove != null) {
            String strA2 = zEquals ? aVarRemove.a() : aVarRemove.c();
            if (!TextUtils.isEmpty(strA2)) {
                return strA2;
            }
        }
        return aVarRemove != null ? com.alipay.sdk.data.a.p().e() : "";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(f.d));
    }

    private e.c a() {
        return new e.c() { // from class: com.alipay.sdk.app.PayTask.2
            @Override // com.alipay.sdk.util.e.c
            public void a() {
            }

            @Override // com.alipay.sdk.util.e.c
            public void b() {
                PayTask.this.dismissLoading();
            }
        };
    }

    public void showLoading() {
        if (this.f2549c != null) {
            this.f2549c.b();
        }
    }

    public void dismissLoading() {
        if (this.f2549c != null) {
            this.f2549c.c();
            this.f2549c = null;
        }
    }

    private String a(String str, com.alipay.sdk.sys.a aVar) {
        String strA = aVar.a(str);
        if (strA.contains("paymethod=\"expressGateway\"")) {
            return a(aVar, strA);
        }
        List<a.C0078a> listO = com.alipay.sdk.data.a.p().o();
        if (!com.alipay.sdk.data.a.p().f2585a || listO == null) {
            listO = com.alipay.sdk.app.a.f2557a;
        }
        if (k.b(aVar, this.f2548b, listO)) {
            e eVar = new e(this.f2548b, aVar, a());
            com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "pay inner started: " + strA);
            String strA2 = eVar.a(strA);
            com.alipay.sdk.util.c.b(com.alipay.sdk.cons.a.x, "pay inner raw result: " + strA2);
            eVar.a();
            if (TextUtils.equals(strA2, e.f2649a) || TextUtils.equals(strA2, e.f2650b)) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ac);
                return a(aVar, strA);
            }
            if (TextUtils.isEmpty(strA2)) {
                return b.c();
            }
            if (!strA2.contains(PayResultActivity.f2541a)) {
                return strA2;
            }
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ae);
            return a(aVar, strA, listO, strA2, this.f2548b);
        }
        com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ad);
        return a(aVar, strA);
    }

    private static String a(com.alipay.sdk.sys.a aVar, String str, List<a.C0078a> list, String str2, Activity activity) {
        k.a aVarA = k.a(aVar, activity, list);
        if (aVarA == null || aVarA.a(aVar) || aVarA.a() || !TextUtils.equals(aVarA.f2671a.packageName, PayResultActivity.f2543c)) {
            return str2;
        }
        com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "PayTask not_login");
        String strValueOf = String.valueOf(str.hashCode());
        PayResultActivity.f2542b.put(strValueOf, new Object());
        Intent intent = new Intent(activity, (Class<?>) PayResultActivity.class);
        intent.putExtra(PayResultActivity.e, str);
        intent.putExtra(PayResultActivity.f, activity.getPackageName());
        intent.putExtra(PayResultActivity.d, strValueOf);
        a.C0080a.a(aVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.f2542b.get(strValueOf)) {
            try {
                com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "PayTask wait");
                PayResultActivity.f2542b.get(strValueOf).wait();
            } catch (InterruptedException unused) {
                com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "PayTask interrupted");
                return b.c();
            }
        }
        String str3 = PayResultActivity.a.f2546b;
        com.alipay.sdk.util.c.a(com.alipay.sdk.cons.a.x, "PayTask ret: " + str3);
        return str3;
    }

    private String a(com.alipay.sdk.sys.a aVar, String str) {
        showLoading();
        c cVarB = null;
        try {
            try {
                JSONObject jSONObjectC = new com.alipay.sdk.packet.impl.f().a(aVar, this.f2548b.getApplicationContext(), str).c();
                String strOptString = jSONObjectC.optString("end_code", null);
                List<com.alipay.sdk.protocol.b> listA = com.alipay.sdk.protocol.b.a(jSONObjectC.optJSONObject(com.alipay.sdk.cons.c.f2582c).optJSONObject(com.alipay.sdk.cons.c.d));
                for (int i2 = 0; i2 < listA.size(); i2++) {
                    if (listA.get(i2).b() == com.alipay.sdk.protocol.a.Update) {
                        com.alipay.sdk.protocol.b.a(listA.get(i2));
                    }
                }
                a(aVar, jSONObjectC);
                dismissLoading();
                com.alipay.sdk.app.statistic.a.a(this.f2548b, aVar, str, aVar.q);
                for (int i3 = 0; i3 < listA.size(); i3++) {
                    com.alipay.sdk.protocol.b bVar = listA.get(i3);
                    if (bVar.b() == com.alipay.sdk.protocol.a.WapPay) {
                        return a(aVar, bVar);
                    }
                    if (bVar.b() == com.alipay.sdk.protocol.a.OpenWeb) {
                        return a(aVar, bVar, strOptString);
                    }
                }
            } catch (IOException e) {
                c cVarB2 = c.b(c.NETWORK_ERROR.a());
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2571a, e);
                dismissLoading();
                com.alipay.sdk.app.statistic.a.a(this.f2548b, aVar, str, aVar.q);
                cVarB = cVarB2;
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a(th);
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.v, th);
            }
            if (cVarB == null) {
                cVarB = c.b(c.FAILED.a());
            }
            return b.a(cVarB.a(), cVarB.b(), "");
        } finally {
            dismissLoading();
            com.alipay.sdk.app.statistic.a.a(this.f2548b, aVar, str, aVar.q);
        }
    }

    private void a(com.alipay.sdk.sys.a aVar, JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString("tid");
            String strOptString2 = jSONObject.optString("client_key");
            if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                return;
            }
            com.alipay.sdk.tid.a.a(com.alipay.sdk.sys.b.a().b()).a(strOptString, strOptString2);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.J, th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0091, code lost:
    
        r0 = r6.c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ab, code lost:
    
        r11 = com.alipay.sdk.app.b.a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], com.alipay.sdk.util.k.b(r10, r0[2]));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(com.alipay.sdk.sys.a r10, com.alipay.sdk.protocol.b r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.sys.a, com.alipay.sdk.protocol.b, java.lang.String):java.lang.String");
    }

    private String a(com.alipay.sdk.sys.a aVar, com.alipay.sdk.protocol.b bVar) {
        String[] strArrC = bVar.c();
        Intent intent = new Intent(this.f2548b, (Class<?>) H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrC[0]);
        if (strArrC.length == 2) {
            bundle.putString("cookie", strArrC[1]);
        }
        intent.putExtras(bundle);
        a.C0080a.a(aVar, intent);
        this.f2548b.startActivity(intent);
        synchronized (f2547a) {
            try {
                f2547a.wait();
            } catch (InterruptedException e) {
                com.alipay.sdk.util.c.a(e);
                return b.c();
            }
        }
        String strA = b.a();
        return TextUtils.isEmpty(strA) ? b.c() : strA;
    }

    private static boolean b() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - k < 3000) {
            return true;
        }
        k = jElapsedRealtime;
        return false;
    }
}
