package com.bytedance.sdk.b.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final int f5133a = 1;

    /* renamed from: b, reason: collision with root package name */
    public static final int f5134b = 2;

    /* renamed from: c, reason: collision with root package name */
    public static final int f5135c = 3;
    public static final int d = 4;
    public static final int e = 3;
    private static final String f = "DNSConfigRefreshManager";
    private static final int g = 60000;
    private static final int h = 300;
    private com.bytedance.sdk.b.e.a.a i;
    private com.bytedance.sdk.b.b.a k;
    private Map<String, Boolean> l = new LinkedHashMap();
    private Handler m = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.sdk.b.c.b.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    b.this.a(false, (String) message.obj);
                    break;
                case 2:
                    b.this.a(true, (String) message.obj);
                    break;
                case 3:
                    b.this.a((com.bytedance.sdk.b.a.a) message.obj);
                    break;
                case 4:
                    b.this.c((String) message.obj);
                    break;
            }
        }
    };
    private Map<String, Long> n = new HashMap();
    private int o = 0;
    private com.bytedance.sdk.b.e.a.b j = new com.bytedance.sdk.b.d.a();

    public b(com.bytedance.sdk.b.e.a.a aVar) {
        this.i = aVar;
    }

    public b a(com.bytedance.sdk.b.b.a aVar) {
        this.k = aVar;
        return this;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str) || this.m.hasMessages(1)) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = str;
        this.m.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.bytedance.sdk.b.c.b$2] */
    public void a(boolean z, final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Boolean bool = this.l.get(str);
        if (bool == null || !bool.booleanValue()) {
            this.l.put(str, true);
            if (!com.bytedance.sdk.b.f.c.c(this.i.a())) {
                com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfig", " network bad");
                return;
            }
            String[] strArrC = this.i.c();
            if (strArrC == null || strArrC.length == 0) {
                com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfig", "configServices is empty");
                return;
            }
            Long l = this.n.get(str);
            Long lValueOf = Long.valueOf(l == null ? 0L : l.longValue());
            if (z || System.currentTimeMillis() - lValueOf.longValue() > 60000) {
                new Thread("DNSConfigRefreshThread") { // from class: com.bytedance.sdk.b.c.b.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        b.this.a(b.this.i.c(), 0, str);
                    }
                }.start();
            } else {
                com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfig", "Less than 1 minutes since last update");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr, int i, String str) {
        if (strArr == null || strArr.length <= i) {
            com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "configServers array out of bounds");
            com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "getDnsBySystem");
            List<InetAddress> listB = b(str);
            if (listB == null || listB.size() <= 0) {
                com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "getDnsBySystem failure");
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.obj = str;
                this.m.sendMessage(messageObtain);
                return;
            }
            com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "getDnsBySystem success");
            Message messageObtain2 = Message.obtain();
            messageObtain2.what = 3;
            messageObtain2.obj = com.bytedance.sdk.b.a.a.a(str, 300, listB);
            this.m.sendMessage(messageObtain2);
            return;
        }
        try {
            String str2 = strArr[i];
            if (TextUtils.isEmpty(str2)) {
                com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "configServer is empty");
                a(strArr, i + 1, str);
                return;
            }
            String strA = a(str2, str);
            if (TextUtils.isEmpty(strA)) {
                com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", "url is empty");
                a(strArr, i + 1, str);
                return;
            }
            com.bytedance.sdk.b.e.a.b bVarF = this.i.f();
            if (bVarF == null) {
                bVarF = this.j;
            }
            JSONObject jSONObject = new JSONObject(bVarF.a(this.i, strA, null));
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("ips");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                throw new Exception("response json is failure");
            }
            Message messageObtain3 = Message.obtain();
            messageObtain3.what = 3;
            messageObtain3.obj = com.bytedance.sdk.b.a.a.a(jSONObject);
            this.m.sendMessage(messageObtain3);
        } catch (Throwable th) {
            com.bytedance.sdk.b.f.b.a(f, "getDNSInternalNext", (Object) "try app config exception:", th);
            a(strArr, i + 1, str);
        }
    }

    private List<InetAddress> b(String str) {
        try {
            return Arrays.asList(InetAddress.getAllByName(str));
        } catch (Throwable unused) {
            return null;
        }
    }

    private String a(String str, String str2) {
        com.bytedance.sdk.b.f.d dVar = new com.bytedance.sdk.b.f.d("https://" + str + "/q");
        dVar.a(com.alipay.sdk.cons.c.f, str2);
        dVar.a("aid", this.i.b());
        dVar.a("p", DispatchConstants.ANDROID);
        int iE = this.i.e();
        if (iE >= 0 && iE <= 2) {
            dVar.a("f", iE);
        }
        com.bytedance.sdk.b.f.b.a(f, "buildUrl", "result url:", dVar);
        return dVar.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.bytedance.sdk.b.a.a aVar) {
        com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfigSuccess", "success");
        if (this.k != null) {
            this.k.a(aVar.f5128a, aVar);
        }
        this.n.put(aVar.f5128a, Long.valueOf(System.currentTimeMillis()));
        this.o = 0;
        this.l.put(aVar.f5128a, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfigFailure", "failure");
        if (this.o < 3) {
            this.o++;
            com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfigFailure", "schedule refresh again after 1 minutes");
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            messageObtain.obj = str;
            this.m.sendMessageDelayed(messageObtain, 60000L);
        } else {
            com.bytedance.sdk.b.f.b.a(f, "innerRefreshConfigFailure", "retry refresh over times");
        }
        this.l.put(str, false);
    }
}
