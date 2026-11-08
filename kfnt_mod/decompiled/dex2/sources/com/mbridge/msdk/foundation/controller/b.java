package com.mbridge.msdk.foundation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.MBridgeSDK;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.db.d;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.p;
import com.mbridge.msdk.foundation.db.r;
import com.mbridge.msdk.foundation.entity.e;
import com.mbridge.msdk.foundation.entity.k;
import com.mbridge.msdk.foundation.entity.o;
import com.mbridge.msdk.foundation.same.report.c;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.u;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.AdMobClickListener;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.MBridgeSDKFactory;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* compiled from: SDKController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b j;
    private Context k;
    private String l;
    private String m;
    private String n;
    private String o;
    private com.mbridge.msdk.a.a q;
    private String r;
    private Map<String, Object> s;
    private int t;
    private AdMobClickListener u;
    private boolean p = false;
    public final int a = 1;
    public final int b = 2;
    public final int c = 3;
    public final int d = 4;
    public final int e = 5;
    public final int f = 6;
    public final int g = 7;
    public final int h = 8;
    Handler i = new Handler() { // from class: com.mbridge.msdk.foundation.controller.b.4
        @Override // android.os.Handler
        public final void handleMessage(Message message) throws Throwable {
            List list;
            List list2;
            try {
                switch (message.what) {
                    case 2:
                        if ((message.obj instanceof List) && (list = (List) message.obj) != null && list.size() > 0) {
                            c cVar = new c(b.this.k, 0);
                            for (int i = 0; i < list.size(); i++) {
                                o oVar = (o) list.get(i);
                                boolean z = false;
                                if (i == list.size()) {
                                    z = true;
                                }
                                cVar.a(oVar, z);
                            }
                            break;
                        }
                        break;
                    case 3:
                        File file = (File) message.obj;
                        if (file != null) {
                            String strA = q.a(file);
                            if (!TextUtils.isEmpty(strA)) {
                                String[] strArrSplit = strA.split("====");
                                if (strArrSplit.length > 0) {
                                    new c(b.this.k).a(strArrSplit[0], file);
                                    break;
                                }
                            }
                        }
                        break;
                    case 4:
                        String str = (String) message.obj;
                        if (!TextUtils.isEmpty(str)) {
                            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                                com.mbridge.msdk.foundation.same.report.a.a().a(str);
                                break;
                            } else {
                                new c(b.this.k, 0).a("click_duration", str, (String) null, (Frame) null);
                                break;
                            }
                        }
                        break;
                    case 5:
                        String str2 = (String) message.obj;
                        if (!TextUtils.isEmpty(str2)) {
                            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                                com.mbridge.msdk.foundation.same.report.a.a().a(str2);
                                break;
                            } else {
                                new c(b.this.k, 0).a("load_duration", str2, (String) null, (Frame) null);
                                break;
                            }
                        }
                        break;
                    case 6:
                        if (message.obj != null && (message.obj instanceof String)) {
                            String str3 = (String) message.obj;
                            if (!TextUtils.isEmpty(str3)) {
                                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                                    com.mbridge.msdk.foundation.same.report.a.a().a(str3);
                                    break;
                                } else {
                                    new c(b.this.k, 0).a("device_data", str3, (String) null, (Frame) null);
                                    break;
                                }
                            }
                        }
                        break;
                    case 7:
                        if (message.obj != null && (message.obj instanceof List) && (list2 = (List) message.obj) != null && list2.size() > 0 && com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_applist")) {
                            y.a(b.this.k, "mbridge_setting_campaign_time", new Long(System.currentTimeMillis()));
                            Iterator it = list2.iterator();
                            while (it.hasNext()) {
                                new c(b.this.k, 0).a((o) it.next());
                            }
                            break;
                        }
                        break;
                    case 8:
                        if (message.obj != null && (message.obj instanceof String)) {
                            String str4 = (String) message.obj;
                            if (z.b(str4)) {
                                if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                                    com.mbridge.msdk.foundation.same.report.a.a().a(str4);
                                    break;
                                } else {
                                    new c(b.this.k, 0).a(str4);
                                    break;
                                }
                            }
                        }
                        break;
                    case 9:
                        if (((com.mbridge.msdk.b.a) message.obj).n() == 1) {
                            com.mbridge.msdk.foundation.same.report.b.a.a(b.this.k).a();
                            break;
                        }
                        break;
                }
            } catch (Exception unused) {
                s.d("SDKController", "REPORT HANDLE ERROR!");
            }
        }
    };

    private b() {
    }

    public static b a() {
        if (j == null) {
            synchronized (b.class) {
                if (j == null) {
                    j = new b();
                }
            }
        }
        return j;
    }

    public final void a(Map map, final Context context) throws JSONException, IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (context != null) {
            if (map.containsKey(MBridgeConstans.ID_MBRIDGE_APPID)) {
                this.n = (String) map.get(MBridgeConstans.ID_MBRIDGE_APPID);
            }
            this.k = context.getApplicationContext();
            a.f().b(this.k);
            try {
                String str = (String) map.get(MBridgeConstans.ID_MBRIDGE_APPID);
                if (!TextUtils.isEmpty(str)) {
                    com.mbridge.msdk.foundation.a.a.a.a().a(MBridgeConstans.SDK_APP_ID, str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (Build.VERSION.SDK_INT < 26) {
                com.mbridge.msdk.c.b.a(context).a(30000, this.n);
            }
            if (this.p) {
                com.mbridge.msdk.rover.b bVarA = com.mbridge.msdk.rover.b.a();
                bVarA.a(context);
                bVarA.b();
                new c(context).a();
                return;
            }
            com.mbridge.msdk.foundation.tools.o.B(context);
            new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.foundation.controller.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    com.mbridge.msdk.foundation.tools.o.A(context);
                }
            }, 300L);
            if (map != null) {
                if (map.containsKey(MBridgeConstans.ID_MBRIDGE_APPID)) {
                    this.n = (String) map.get(MBridgeConstans.ID_MBRIDGE_APPID);
                }
                if (map.containsKey(MBridgeConstans.ID_MBRIDGE_APPKEY)) {
                    this.o = (String) map.get(MBridgeConstans.ID_MBRIDGE_APPKEY);
                }
                if (map.containsKey(MBridgeConstans.PACKAGE_NAME_MANIFEST)) {
                    this.r = (String) map.get(MBridgeConstans.PACKAGE_NAME_MANIFEST);
                }
                if (map.containsKey(MBridgeConstans.ID_MBRIDGE_STARTUPCRASH)) {
                    this.m = (String) map.get(MBridgeConstans.ID_MBRIDGE_STARTUPCRASH);
                }
                e();
                a.f().c(this.n);
                a.f().d(this.o);
                a.f().b(this.l);
                a.f().a(this.r);
                a.f().a(new a.b() { // from class: com.mbridge.msdk.foundation.controller.b.6
                    @Override // com.mbridge.msdk.foundation.controller.a.b
                    public final void a() {
                        b.d(b.this);
                    }
                }, this.i);
                s.b("SDKController", "facebook = " + this.l + "appId = " + this.n + "appKey = " + this.o);
                try {
                    String string = "";
                    String string2 = "";
                    SharedPreferences sharedPreferences = this.k.getApplicationContext().getSharedPreferences(n.b("H+tU+FeXHM=="), 0);
                    if (sharedPreferences != null) {
                        string = sharedPreferences.getString(n.b("H+tU+bfPhM=="), "");
                        string2 = sharedPreferences.getString(n.b("H+tU+Fz8"), "");
                    }
                    if (TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.s) && TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.t)) {
                        com.mbridge.msdk.foundation.same.a.s = com.mbridge.msdk.foundation.a.a.a.a().b(com.huawei.updatesdk.service.d.a.b.a);
                        com.mbridge.msdk.foundation.same.a.t = com.mbridge.msdk.foundation.a.a.a.a().b("c");
                    }
                    if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
                        if ((!TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.s) || !TextUtils.isEmpty(com.mbridge.msdk.foundation.same.a.t)) && sharedPreferences != null) {
                            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                            editorEdit.putString(n.b("H+tU+bfPhM=="), com.mbridge.msdk.foundation.same.a.s);
                            editorEdit.putString(n.b("H+tU+Fz8"), com.mbridge.msdk.foundation.same.a.t);
                            editorEdit.apply();
                        }
                    } else {
                        com.mbridge.msdk.foundation.same.a.s = string;
                        com.mbridge.msdk.foundation.same.a.t = string2;
                        com.mbridge.msdk.foundation.a.a.a.a().a(com.huawei.updatesdk.service.d.a.b.a, com.mbridge.msdk.foundation.same.a.s);
                        com.mbridge.msdk.foundation.a.a.a.a().a("c", com.mbridge.msdk.foundation.same.a.t);
                    }
                } catch (Throwable th2) {
                    s.b("SDKController", th2.getMessage(), th2);
                }
                try {
                    if (MBridgeConstans.INIT_UA_IN) {
                        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.foundation.controller.b.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                Looper.prepare();
                                b.this.d();
                                b.a(b.this, b.this.n);
                                Looper.loop();
                            }
                        });
                    } else if (Looper.myLooper() == Looper.getMainLooper()) {
                        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.foundation.controller.b.3
                            @Override // java.lang.Runnable
                            public final void run() {
                                Looper.prepare();
                                b.this.d();
                                Looper.loop();
                            }
                        });
                    } else {
                        d();
                    }
                } catch (Exception unused) {
                    s.d("SDKController", "get app setting failed");
                }
                c();
                if (MBridgeConstans.INIT_UA_IN) {
                    try {
                        Class<?> cls = Class.forName("com.mbridge.msdk.appwall.service.HandlerProvider");
                        new HashMap().put(MBridgeConstans.PLUGIN_NAME, new String[]{com.mbridge.msdk.a.PLUGIN_WALL});
                        cls.getMethod("getLayout", Context.class, String.class, String.class).invoke(cls.newInstance(), this.k, this.n, null);
                    } catch (Exception unused2) {
                        s.b("SDKController", "if you integrate the appwall ad style,please import appwall.aar or ignore it");
                    }
                }
                u.a(this.k);
                this.p = true;
                com.mbridge.msdk.rover.b bVarA2 = com.mbridge.msdk.rover.b.a();
                bVarA2.a(context);
                bVarA2.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.d.b");
            cls.getDeclaredMethod("start", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0]);
        } catch (Throwable th) {
            s.b("SDKController", th.getMessage(), th);
        }
    }

    private void e() {
        List<com.mbridge.msdk.foundation.entity.b> listA;
        Object objNewInstance;
        Class<?> cls;
        Object objNewInstance2;
        try {
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(a.f().k());
            if (aVarB == null || (listA = aVarB.a()) == null || listA.size() <= 0) {
                return;
            }
            for (com.mbridge.msdk.foundation.entity.b bVar : listA) {
                if (bVar.a() == 287) {
                    Class<?> cls2 = Class.forName("com.mbridge.msdk.interstitialvideo.out.MBInterstitialVideoHandler");
                    if (this.k != null && cls2 != null && (objNewInstance = cls2.getConstructor(String.class, String.class).newInstance("", bVar.b())) != null) {
                        cls2.getMethod("loadFormSelfFilling", new Class[0]).invoke(objNewInstance, new Object[0]);
                    }
                } else if (bVar.a() == 94 && (cls = Class.forName("com.mbridge.msdk.out.MBRewardVideoHandler")) != null && (objNewInstance2 = cls.getConstructor(String.class, String.class).newInstance("", bVar.b())) != null) {
                    cls.getMethod("loadFormSelfFilling", new Class[0]).invoke(objNewInstance2, new Object[0]);
                }
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
    }

    public final void b() {
        if (this.i != null) {
            this.i.removeCallbacksAndMessages(null);
        }
    }

    public final void c() {
        new Thread(new Runnable() { // from class: com.mbridge.msdk.foundation.controller.b.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    com.mbridge.msdk.foundation.same.report.c.a.a();
                    Context context = b.this.k;
                    String string = null;
                    List<o> listA = (context != null && p.a(i.a(context)).a() > 0) ? p.a(i.a(context)).a(20) : null;
                    if (listA != null && listA.size() > 0) {
                        Message messageObtainMessage = b.this.i.obtainMessage();
                        messageObtainMessage.what = 2;
                        messageObtainMessage.obj = listA;
                        b.this.i.sendMessage(messageObtainMessage);
                    }
                    i iVarA = i.a(b.this.k);
                    d dVarA = d.a(iVarA);
                    if (dVarA.a() >= 20) {
                        String strA = e.a(dVarA.a(20));
                        Message messageObtain = Message.obtain();
                        messageObtain.obj = strA;
                        messageObtain.what = 4;
                        b.this.i.sendMessage(messageObtain);
                    }
                    com.mbridge.msdk.foundation.db.n nVarA = com.mbridge.msdk.foundation.db.n.a(iVarA);
                    if (nVarA.a() > 20) {
                        List<k> listA2 = nVarA.a(20);
                        if (listA2 != null && listA2.size() > 0) {
                            StringBuffer stringBuffer = new StringBuffer();
                            for (k kVar : listA2) {
                                if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                                    stringBuffer.append("ad_source_id=" + kVar.b());
                                    stringBuffer.append("&time=" + kVar.c());
                                    stringBuffer.append("&num=" + kVar.d());
                                    stringBuffer.append("&unit_id=" + kVar.e());
                                    stringBuffer.append("&key=2000006");
                                    stringBuffer.append("&fb=" + kVar.f());
                                    stringBuffer.append("&network_str=" + kVar.i());
                                    stringBuffer.append("&network_type=" + kVar.h());
                                } else {
                                    stringBuffer.append("ad_source_id=" + kVar.b());
                                    stringBuffer.append("&time=" + kVar.c());
                                    stringBuffer.append("&num=" + kVar.d());
                                    stringBuffer.append("&unit_id=" + kVar.e());
                                    stringBuffer.append("&key=2000006");
                                    stringBuffer.append("&fb=" + kVar.f());
                                }
                                if (kVar.a() == 1) {
                                    stringBuffer.append("&hb=1");
                                }
                                stringBuffer.append("&timeout=" + kVar.g() + "\n");
                            }
                            string = stringBuffer.toString();
                        }
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.obj = string;
                        messageObtain2.what = 5;
                        b.this.i.sendMessage(messageObtain2);
                    }
                    String strB = com.mbridge.msdk.foundation.same.b.e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_CRASH_INFO);
                    File file = new File(strB);
                    if (file.exists() && file.isDirectory() && file.list().length > 0) {
                        for (String str : file.list()) {
                            File file2 = new File(strB + "/" + str);
                            Message messageObtain3 = Message.obtain();
                            messageObtain3.obj = file2;
                            messageObtain3.what = 3;
                            b.this.i.sendMessage(messageObtain3);
                        }
                    }
                } catch (Exception unused) {
                    s.d("SDKController", "report netstate error !");
                }
            }
        }).start();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00d1 -> B:39:0x00d4). Please report as a decompilation issue!!! */
    public final void a(Map<String, Object> map, int i) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (MBridgeSDKFactory.getMBridgeSDK().getStatus() != MBridgeSDK.PLUGIN_LOAD_STATUS.COMPLETED) {
            s.d("SDKController", "preloaad failed,sdk do not inited");
            return;
        }
        this.s = map;
        this.t = i;
        a.f().k();
        if (map != null) {
            if (this.q == null) {
                this.q = new com.mbridge.msdk.a.a();
            }
            try {
                if (this.s != null && this.s.size() > 0 && this.s.containsKey(MBridgeConstans.PROPERTIES_LAYOUT_TYPE)) {
                    int iIntValue = ((Integer) this.s.get(MBridgeConstans.PROPERTIES_LAYOUT_TYPE)).intValue();
                    if (iIntValue == 0) {
                        com.mbridge.msdk.a.a aVar = this.q;
                        Map<String, Object> map2 = this.s;
                        int i2 = this.t;
                        AdMobClickListener adMobClickListener = this.u;
                        try {
                            Class<?> cls = Class.forName("com.mbridge.msdk.mbnative.f.a");
                            cls.getMethod("preload", Map.class, Integer.TYPE, AdMobClickListener.class).invoke(cls.newInstance(), map2, Integer.valueOf(i2), adMobClickListener);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (3 == iIntValue) {
                        com.mbridge.msdk.a.a aVar2 = this.q;
                        Map<String, Object> map3 = this.s;
                        try {
                            Class<?> cls2 = Class.forName("com.mbridge.msdk.appwall.service.HandlerProvider");
                            cls2.getMethod("preload", Map.class).invoke(cls2.newInstance(), map3);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else if (1 == iIntValue || 2 == iIntValue) {
                        com.mbridge.msdk.a.a aVar3 = this.q;
                        Map<String, Object> map4 = this.s;
                    } else {
                        s.d("SDKController", "unknow layout type in preload");
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(b bVar, String str) {
        if (com.mbridge.msdk.b.b.a() == null) {
            return;
        }
        if (com.mbridge.msdk.b.b.a().a(str) && com.mbridge.msdk.b.b.a().a(str, 1, (String) null)) {
            new com.mbridge.msdk.b.c().a(bVar.k, str, bVar.o);
            return;
        }
        new c(bVar.k).a();
        com.mbridge.msdk.b.b bVarA = com.mbridge.msdk.b.b.a();
        if (bVarA != null) {
            com.mbridge.msdk.b.a aVarB = bVarA.b(str);
            if (aVarB != null) {
                MBridgeConstans.OMID_JS_SERVICE_URL = aVarB.an();
                com.mbridge.msdk.foundation.same.net.g.d.c().c = aVarB.R();
                com.mbridge.msdk.foundation.same.net.g.d.c().e();
                return;
            }
            MBridgeConstans.OMID_JS_SERVICE_URL = "https://cdn-adn-https.rayjump.com/cdn-adn/v2/portal/19/08/20/11/06/5d5b63cb457e2.js";
        }
    }

    static /* synthetic */ void d(b bVar) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(bVar.n);
            if (aVarB == null) {
                aVarB = com.mbridge.msdk.b.b.a().b();
            }
            long jLongValue = ((Long) y.b(bVar.k, "mbridge_setting_campaign_time", new Long(0L))).longValue();
            if (jLongValue <= 0 || jLongValue + (aVarB.q() * 1000) <= jCurrentTimeMillis) {
                if (aVarB.p() <= 0) {
                    r.a(i.a(a.f().j())).b();
                    return;
                }
                List<o> listC = r.a(i.a(bVar.k)).c();
                if (listC == null || listC.size() <= 0) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.obj = listC;
                messageObtain.what = 7;
                bVar.i.sendMessage(messageObtain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
