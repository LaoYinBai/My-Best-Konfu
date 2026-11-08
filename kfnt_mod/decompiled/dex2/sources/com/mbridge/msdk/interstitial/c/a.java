package com.mbridge.msdk.interstitial.c;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.b.d;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.interstitial.view.MBInterstitialActivity;
import com.mbridge.msdk.out.InterstitialListener;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.HashMap;
import java.util.Map;

/* compiled from: InterstitialController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static String a;
    public static Map<String, Integer> c = new HashMap();
    public static Map<String, Integer> d = new HashMap();
    public static Map<String, c> e = new HashMap();
    private Context g;
    private String h;
    private String i;
    private String j;
    private MBridgeIds k;
    private Handler l;
    private d m;
    private InterstitialListener n;
    private String f = "InterstitialController";
    public boolean b = false;
    private String o = "";
    private String p = "";
    private boolean q = false;

    public static void a(String str, int i) {
        try {
            if (c == null || TextUtils.isEmpty(str)) {
                return;
            }
            c.put(str, Integer.valueOf(i));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int a(String str) {
        Integer num;
        try {
            if (TextUtils.isEmpty(str) || c == null || !c.containsKey(str) || (num = c.get(str)) == null) {
                return 0;
            }
            return num.intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public final void a(InterstitialListener interstitialListener) {
        this.n = interstitialListener;
    }

    public a() {
        try {
            this.l = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.interstitial.c.a.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    if (message == null) {
                    }
                    switch (message.what) {
                        case 1:
                            if (a.this.n != null) {
                                a.this.n.onInterstitialLoadSuccess(a.this.k);
                                s.b(a.this.f, "handler 数据load成功");
                                break;
                            }
                            break;
                        case 2:
                            if (a.this.n != null) {
                                String str = "";
                                if (message.obj != null && (message.obj instanceof String)) {
                                    str = (String) message.obj;
                                }
                                if (TextUtils.isEmpty(str)) {
                                    str = "can't show because unknow error";
                                }
                                a.this.n.onInterstitialLoadFail(a.this.k, str);
                                s.b(a.this.f, "handler 数据load失败:" + str);
                                break;
                            }
                            break;
                        case 3:
                            a.this.b = true;
                            if (a.this.n != null) {
                                a.this.n.onInterstitialShowSuccess(a.this.k);
                                s.b(a.this.f, "handler 数据show成功");
                                break;
                            }
                            break;
                        case 4:
                            if (a.this.n != null) {
                                String str2 = "";
                                if (message.obj != null && (message.obj instanceof String)) {
                                    str2 = (String) message.obj;
                                }
                                if (TextUtils.isEmpty(str2)) {
                                    str2 = "can't show because unknow error";
                                }
                                a.this.n.onInterstitialShowFail(a.this.k, str2);
                                s.b(a.this.f, "handler 数据show失败:" + str2);
                                break;
                            }
                            break;
                        case 6:
                            if (a.this.n != null) {
                                a.this.n.onInterstitialAdClick(a.this.k);
                                break;
                            }
                            break;
                        case 7:
                            a.this.b = false;
                            if (a.this.n != null) {
                                a.this.n.onInterstitialClosed(a.this.k);
                                break;
                            }
                            break;
                    }
                }
            };
        } catch (Exception e2) {
            try {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public final boolean a(Context context, Map<String, Object> map) {
        try {
            this.q = false;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.q = false;
        }
        if (map == null) {
            s.c(this.f, "init error params==null");
            return false;
        }
        if (context == null) {
            s.c(this.f, "init context ==null");
            return false;
        }
        if (map.containsKey(MBridgeConstans.PROPERTIES_UNIT_ID) && (map.get(MBridgeConstans.PROPERTIES_UNIT_ID) instanceof String)) {
            if (map.containsKey(MBridgeConstans.PROPERTIES_API_REUQEST_CATEGORY) && (map.get(MBridgeConstans.PROPERTIES_API_REUQEST_CATEGORY) instanceof String)) {
                this.h = (String) map.get(MBridgeConstans.PROPERTIES_API_REUQEST_CATEGORY);
            }
            this.i = (String) map.get(MBridgeConstans.PROPERTIES_UNIT_ID);
            this.g = context;
            if (map.containsKey(MBridgeConstans.PLACEMENT_ID) && map.get(MBridgeConstans.PLACEMENT_ID) != null) {
                this.j = (String) map.get(MBridgeConstans.PLACEMENT_ID);
            }
            this.k = new MBridgeIds(this.j, this.i);
            this.q = true;
            return this.q;
        }
        s.c(this.f, "init error,make sure you have unitid");
        return false;
    }

    public final void a() {
        try {
            if (this.g == null) {
                b("context is null");
                s.b(this.f, "load context is null");
                return;
            }
            if (TextUtils.isEmpty(this.i)) {
                b("unitid is null");
                s.b(this.f, "load unitid is null");
                return;
            }
            if (!this.q) {
                b("init error");
                s.b(this.f, "load init error");
                return;
            }
            d();
            try {
                if (this.m != null) {
                    int iS = this.m.s();
                    int iX = this.m.x();
                    if (iS <= 0) {
                        iS = 1;
                    }
                    if (iX <= 0) {
                        iX = 1;
                    }
                    int i = iX * iS;
                    if (d != null && !TextUtils.isEmpty(this.i)) {
                        d.put(this.i, Integer.valueOf(i));
                    }
                    s.b(this.f, "maxOffset:" + i + " apiCacheNum:" + iS + " mUnitId:" + this.i);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            a(false);
        } catch (Exception e3) {
            e3.printStackTrace();
            b("can't show because unknow error");
        }
    }

    public final String b() {
        if (this.b) {
            return this.p;
        }
        return this.o;
    }

    public final void c() {
        try {
            if (this.g == null) {
                c("context is null");
                s.b(this.f, "show context is null");
            } else if (TextUtils.isEmpty(this.i)) {
                c("unitid is null");
                s.b(this.f, "show unitid is null");
            } else if (!this.q) {
                c("init error");
                s.b(this.f, "show init error");
            } else {
                d();
                b(true);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            c("can't show because unknow error");
        }
    }

    private void d() {
        try {
            s.b(this.f, "initUnitSetting");
            e();
            this.m = com.mbridge.msdk.b.b.a().f(com.mbridge.msdk.foundation.controller.a.f().k(), this.i);
            if (this.m == null) {
                this.m = d.f(this.i);
                s.b(this.f, "获取默认的unitsetting");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(boolean z) {
        try {
            com.mbridge.msdk.interstitial.a.a aVar = new com.mbridge.msdk.interstitial.a.a(this.g, this.i, this.j, this.h, z);
            b bVar = new b(aVar);
            aVar.a(new C0116a(aVar, bVar));
            if (this.l != null) {
                this.l.postDelayed(bVar, 30000L);
            }
            aVar.b();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (z) {
                return;
            }
            b("can't show because unknow error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        try {
            s.b(this.f, "showInterstitial isShowCall:" + z);
            CampaignEx campaignExA = new com.mbridge.msdk.interstitial.a.a(this.g, this.i, this.j, this.h, true).a();
            if (campaignExA != null) {
                a(campaignExA);
            } else if (z) {
                s.d(this.f, "showInterstitial 发现cmapaign为空 去load一遍=========");
                a(true);
            } else {
                c("no ads available can show");
                s.b(this.f, "showInterstitial 发现cmapaign为空");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (this.n != null) {
                c("can't show because unknow error");
            }
        }
    }

    private void a(CampaignEx campaignEx) {
        c cVar = new c();
        if (e != null && !TextUtils.isEmpty(this.i)) {
            e.put(this.i, cVar);
        }
        Intent intent = new Intent(this.g, (Class<?>) MBInterstitialActivity.class);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        if (!TextUtils.isEmpty(this.i)) {
            intent.putExtra("unitId", this.i);
        }
        if (campaignEx != null) {
            this.p = campaignEx.getRequestId();
            intent.putExtra("campaign", campaignEx);
        }
        if (this.g != null) {
            this.g.startActivity(intent);
        }
    }

    /* compiled from: InterstitialController.java */
    public class b implements Runnable {
        private com.mbridge.msdk.interstitial.a.a b;

        public b(com.mbridge.msdk.interstitial.a.a aVar) {
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                s.d(a.this.f, "CommonCancelTimeTask");
                if (this.b != null) {
                    if (this.b.d()) {
                        a.this.c("load timeout");
                    } else if (a.this.n != null) {
                        a.this.b("load timeout");
                    }
                    this.b.a((C0116a) null);
                    this.b = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: InterstitialController.java */
    /* renamed from: com.mbridge.msdk.interstitial.c.a$a, reason: collision with other inner class name */
    public class C0116a {
        private com.mbridge.msdk.interstitial.a.a b;
        private b c;

        public C0116a(com.mbridge.msdk.interstitial.a.a aVar, b bVar) {
            this.b = aVar;
            this.c = bVar;
        }

        public final void a(boolean z, String str) {
            try {
                a.this.o = str;
                if (this.c != null) {
                    if (a.this.l != null) {
                        a.this.l.removeCallbacks(this.c);
                    }
                    if (z) {
                        a.this.b(false);
                    } else if (a.this.n != null) {
                        a.e(a.this);
                    }
                    s.d(a.this.f, "onInterstitialLoadSuccess remove task ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public final void b(boolean z, String str) {
            try {
                if (this.b != null) {
                    this.b.a((C0116a) null);
                    this.b = null;
                }
                if (this.c != null) {
                    s.d(a.this.f, "LoadIntersInnerListener onIntersLoadFail remove task");
                    if (a.this.l != null) {
                        a.this.l.removeCallbacks(this.c);
                    }
                    if (z) {
                        if (a.this.n != null) {
                            a.this.c(str);
                        }
                    } else if (a.this.n != null) {
                        a.this.b(str);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: InterstitialController.java */
    public class c {
        public c() {
        }

        public final void a(String str) {
            try {
                a.this.c(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public final void a() {
            try {
                a.f(a.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public final void b() {
            try {
                if (a.this.l != null) {
                    a.this.l.sendEmptyMessage(7);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public final void c() {
            try {
                if (a.this.l != null) {
                    a.this.l.sendEmptyMessage(6);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            if (this.l != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 2;
                this.l.sendMessage(messageObtain);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        try {
            if (this.l != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 4;
                this.l.sendMessage(messageObtain);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        try {
            new com.mbridge.msdk.b.c().a(this.g, (String) null, (String) null, this.i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static /* synthetic */ void e(a aVar) {
        if (aVar.l != null) {
            aVar.l.sendEmptyMessage(1);
        }
    }

    static /* synthetic */ void f(a aVar) {
        if (aVar.l != null) {
            aVar.l.sendEmptyMessage(3);
        }
    }
}
