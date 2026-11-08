package com.mbridge.msdk.reward.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.j;
import com.mbridge.msdk.foundation.db.l;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.download.download.ResourceManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.h;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.RewardInfo;
import com.mbridge.msdk.reward.adapter.c;
import com.mbridge.msdk.videocommon.a;
import com.mbridge.msdk.videocommon.listener.InterVideoOutListener;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardVideoController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a;
    private boolean E;
    private Queue<Integer> K;
    private String L;
    private List<CampaignEx> U;
    private List<CampaignEx> V;
    private Context h;
    private int i;
    private com.mbridge.msdk.reward.adapter.d j;
    private com.mbridge.msdk.videocommon.d.c k;
    private com.mbridge.msdk.videocommon.d.a l;
    private volatile InterVideoOutListener m;
    private volatile c n;
    private String o;
    private String p;
    private volatile String q;
    private MBridgeIds r;
    private String s;
    private String t;
    private int x;
    private int y;
    private int z;
    private static Map<String, Integer> I = new HashMap();
    public static Map<String, d> b = new HashMap();
    private static ConcurrentHashMap<String, String> J = new ConcurrentHashMap<>();
    private int u = 0;
    private int w = 2;
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private boolean F = false;
    private ArrayList<Integer> G = new ArrayList<>(7);
    private boolean H = false;
    private i M = null;
    private volatile boolean N = true;
    private volatile boolean O = false;
    private volatile boolean P = false;
    private volatile boolean Q = false;
    private volatile boolean R = false;
    private volatile boolean S = false;
    private volatile boolean T = false;
    volatile boolean c = false;
    volatile boolean d = false;
    volatile boolean e = false;
    volatile boolean f = false;
    volatile boolean g = false;
    private Handler v = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.reward.a.a.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 8:
                    if (a.this.U != null && a.this.U.size() > 0) {
                        boolean z = (a.this.V == null || a.this.V.size() <= 0) ? false : !TextUtils.isEmpty(((CampaignEx) a.this.V.get(0)).getMof_template_url());
                        int nscpt = ((CampaignEx) a.this.U.get(0)).getNscpt();
                        if (a.this.j == null || !a.this.j.a(a.this.U, z, nscpt)) {
                            if (a.this.n != null && a.this.E) {
                                com.mbridge.msdk.videocommon.a.a();
                                com.mbridge.msdk.videocommon.a.b();
                                c.a(a.this.n, "load timeout");
                                break;
                            }
                        } else if (a.this.n != null && a.this.E) {
                            c.a(a.this.n, a.this.q, a.this.p);
                            break;
                        }
                    }
                    break;
                case 9:
                    if (a.this.m != null && a.this.E) {
                        Object obj = message.obj;
                        if (obj instanceof String) {
                            obj.toString();
                        }
                        Bundle data = message.getData();
                        if (data != null && data.containsKey(MBridgeConstans.PLACEMENT_ID)) {
                            TextUtils.isEmpty(data.getString(MBridgeConstans.PLACEMENT_ID));
                        }
                        try {
                            if (a.this.B) {
                                a.j(a.this);
                            }
                            a.this.m.onVideoLoadSuccess(a.this.r);
                            break;
                        } catch (Exception e) {
                            if (MBridgeConstans.DEBUG) {
                                e.printStackTrace();
                                return;
                            }
                            return;
                        }
                    }
                    break;
                case 16:
                case 18:
                    if (a.this.m != null && a.this.E) {
                        Object obj2 = message.obj;
                        String string = obj2 instanceof String ? obj2.toString() : "";
                        com.mbridge.msdk.videocommon.a.a();
                        com.mbridge.msdk.videocommon.a.b();
                        try {
                            if (a.this.B) {
                                a.j(a.this);
                            }
                            a.this.m.onVideoLoadFail(a.this.r, string);
                            break;
                        } catch (Exception e2) {
                            if (MBridgeConstans.DEBUG) {
                                e2.printStackTrace();
                                return;
                            }
                            return;
                        }
                    }
                    break;
                case 17:
                    if (a.this.m != null && a.this.E) {
                        Object obj3 = message.obj;
                        if (obj3 instanceof String) {
                            obj3.toString();
                        }
                        Bundle data2 = message.getData();
                        if (data2 != null && data2.containsKey(MBridgeConstans.PLACEMENT_ID)) {
                            TextUtils.isEmpty(data2.getString(MBridgeConstans.PLACEMENT_ID));
                        }
                        try {
                            if (a.this.B) {
                                a.j(a.this);
                            }
                            a.this.m.onLoadSuccess(a.this.r);
                            break;
                        } catch (Exception e3) {
                            if (MBridgeConstans.DEBUG) {
                                e3.printStackTrace();
                                return;
                            }
                            return;
                        }
                    }
                    break;
                case 1001001:
                    a.this.d(false);
                    break;
                case 1001002:
                    int iH = a.this.k != null ? a.this.k.H() : 0;
                    if (a.this.j != null) {
                        if (a.this.j.b()) {
                            if (a.this.n != null) {
                                s.a("RewardVideoController", "load timeout task called for onVideoLoadSuccess by isReady exception");
                                try {
                                    if (a.this.U != null && a.this.U.size() > 0) {
                                        com.mbridge.msdk.reward.b.a.a((CampaignEx) a.this.U.get(0), com.mbridge.msdk.foundation.controller.a.f().j(), "load timeout task called onVideoLoadSuccess after " + iH + "s exception", a.this.p, a.this.B, "", "", iH);
                                        com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) a.this.U, a.this.p);
                                    }
                                } catch (Exception unused) {
                                }
                                c.b(a.this.n, a.this.q, a.this.p);
                                break;
                            }
                        } else if (a.this.j.f(false)) {
                            if (a.this.j.b()) {
                                if (a.this.n != null) {
                                    s.a("RewardVideoController", "load timeout task called for onVideoLoadSuccess by isReady but updateCampaignsLoadTimeoutState exception");
                                    a.this.j.d(false);
                                    try {
                                        if (a.this.U != null && a.this.U.size() > 0) {
                                            com.mbridge.msdk.reward.b.a.a((CampaignEx) a.this.U.get(0), com.mbridge.msdk.foundation.controller.a.f().j(), "load timeout task called onVideoLoadSuccess after " + iH + "s exception", a.this.p, a.this.B, "", "", iH);
                                            com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) a.this.U, a.this.p);
                                        }
                                    } catch (Exception unused2) {
                                    }
                                    c.b(a.this.n, a.this.q, a.this.p);
                                    break;
                                }
                            } else {
                                a.this.j.e(false);
                                if (a.this.j.f(true)) {
                                    if (a.this.j.b()) {
                                        if (a.this.n != null) {
                                            s.a("RewardVideoController", "load timeout task called for onVideoLoadSuccess by isReady but updateCampaignsLoadTimeoutState exception");
                                            a.this.j.d(true);
                                            try {
                                                if (a.this.U != null && a.this.U.size() > 0) {
                                                    com.mbridge.msdk.reward.b.a.a((CampaignEx) a.this.U.get(0), com.mbridge.msdk.foundation.controller.a.f().j(), "load timeout task called onVideoLoadSuccess after " + iH + "s exception", a.this.p, a.this.B, "", "", iH);
                                                    com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) a.this.U, a.this.p);
                                                }
                                            } catch (Exception unused3) {
                                            }
                                            c.b(a.this.n, a.this.q, a.this.p);
                                            break;
                                        }
                                    } else {
                                        if (a.this.n != null) {
                                            s.a("RewardVideoController", "load timeout task called for onVideoLoadFail after " + iH + "s exception");
                                        }
                                        a.this.j.e(true);
                                        break;
                                    }
                                } else if (a.this.n != null) {
                                    s.a("RewardVideoController", "load timeout task called for onVideoLoadFail after " + iH + "s exception");
                                    break;
                                }
                            }
                        } else if (a.this.j.f(true)) {
                            if (a.this.j.b()) {
                                if (a.this.n != null) {
                                    s.a("RewardVideoController", "load timeout task called for onVideoLoadSuccess by isReady but updateCampaignsLoadTimeoutState");
                                    a.this.j.d(true);
                                    try {
                                        if (a.this.U != null && a.this.U.size() > 0) {
                                            com.mbridge.msdk.reward.b.a.a((CampaignEx) a.this.U.get(0), com.mbridge.msdk.foundation.controller.a.f().j(), "load timeout task called onVideoLoadSuccess after " + iH + "s exception", a.this.p, a.this.B, "", "", iH);
                                            com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) a.this.U, a.this.p);
                                        }
                                    } catch (Exception unused4) {
                                    }
                                    c.b(a.this.n, a.this.q, a.this.p);
                                    break;
                                }
                            } else {
                                if (a.this.n != null) {
                                    s.a("RewardVideoController", "load timeout task called for onVideoLoadFail after " + iH + " s");
                                }
                                a.this.j.e(true);
                                break;
                            }
                        } else if (a.this.n != null) {
                            s.a("RewardVideoController", "load timeout task called for onVideoLoadFail after " + iH + " s");
                            break;
                        }
                    } else if (a.this.n != null) {
                        s.a("RewardVideoController", "load timeout task called for onVideoLoadFail by mRewardMvVideoAdapter is null exception");
                        break;
                    }
                    break;
            }
        }
    };

    public final void a(boolean z) {
        this.A = z;
    }

    public final void b(boolean z) {
        this.B = z;
    }

    public final void a(int i) {
        this.w = i;
    }

    public final void a(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            y.a(this.h, "MBridge_ConfirmTitle" + this.p, str.trim());
        }
        if (!TextUtils.isEmpty(str2)) {
            y.a(this.h, "MBridge_ConfirmContent" + this.p, str2.trim());
        }
        if (!TextUtils.isEmpty(str4)) {
            y.a(this.h, "MBridge_CancelText" + this.p, str4.trim());
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        y.a(this.h, "MBridge_ConfirmText" + this.p, str3.trim());
    }

    public final void c(boolean z) {
        this.F = z;
    }

    public final void a(int i, int i2, int i3) throws JSONException {
        this.x = i;
        this.y = i2;
        if (this.y == com.mbridge.msdk.foundation.same.a.y) {
            this.z = i3 < 0 ? 5 : i3;
        }
        if (this.y == com.mbridge.msdk.foundation.same.a.x) {
            this.z = i3 < 0 ? 80 : i3;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ivRewardEnable", 1);
            jSONObject.put("ivRewardMode", i == com.mbridge.msdk.foundation.same.a.v ? 0 : 1);
            jSONObject.put("ivRewardPlayValueMode", i2 == com.mbridge.msdk.foundation.same.a.x ? 0 : 1);
            jSONObject.put("ivRewardPlayValue", i3);
            com.mbridge.msdk.b.b.a();
            String str = this.p;
            String string = jSONObject.toString();
            com.mbridge.msdk.foundation.a.a.a.a().a("ivreward_" + str, string);
        } catch (Exception unused) {
            s.d("RewardVideoController", "setIVRewardEnable to SP was ERROR");
        }
    }

    public static void a(String str, int i) {
        try {
            if (I == null || !z.b(str)) {
                return;
            }
            I.put(str, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int a(String str) {
        Integer num;
        try {
            if (!z.b(str) || I == null || !I.containsKey(str) || (num = I.get(str)) == null) {
                return 0;
            }
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || J == null || J.containsKey(str)) {
            return;
        }
        J.put(str, str2);
    }

    public final void a() {
        if (!this.H || J == null || J.containsKey(b())) {
            return;
        }
        J.remove(b());
    }

    public static void insertExcludeId(String str, CampaignEx campaignEx) {
        if (TextUtils.isEmpty(str) || campaignEx == null || com.mbridge.msdk.foundation.controller.a.f().j() == null) {
            return;
        }
        l lVarA = l.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        h hVar = new h();
        hVar.a(System.currentTimeMillis());
        hVar.b(str);
        hVar.a(campaignEx.getId());
        lVarA.a(hVar);
    }

    public final String b() {
        return this.j != null ? this.j.c(this.H) : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RewardVideoController.java */
    public final class d implements com.mbridge.msdk.video.bt.module.b.h {
        private a b;
        private int c;
        private Handler d;
        private int e;

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final boolean b() {
            return false;
        }

        private d(a aVar, int i, Handler handler) {
            this.e = 0;
            this.b = aVar;
            this.c = i;
            this.d = handler;
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a() {
            s.a("RewardVideoController", "=====================onAdShow=====================");
            try {
                if (this.b != null) {
                    this.b.H = true;
                    if (this.b.j != null) {
                        this.b.j.b = "";
                    }
                    this.b.b(this.c);
                    if (this.b.m != null) {
                        if (a.this.B) {
                            a.j(a.this);
                        }
                        this.b.m.onAdShow(a.this.r);
                        this.e = 2;
                    }
                }
            } catch (Throwable th) {
                if (MBridgeConstans.DEBUG) {
                    th.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(boolean z, com.mbridge.msdk.videocommon.b.d dVar) {
            s.a("RewardVideoController", "=====================onAdClose=====================");
            try {
                if (this.b == null || this.b.m == null) {
                    return;
                }
                if (dVar == null) {
                    dVar = com.mbridge.msdk.videocommon.b.d.b(this.b.t);
                }
                if (a.this.B) {
                    a.j(a.this);
                    a.this.a();
                    com.mbridge.msdk.foundation.same.a.d.c(a.this.p, a.this.b());
                }
                this.b.m.onAdClose(a.this.r, new RewardInfo(z, dVar.a(), String.valueOf(dVar.b())));
                this.e = 7;
                s.a("RewardVideoController", "onAdClose start release");
                this.b.H = false;
                a.this.G.clear();
                this.b = null;
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(String str) {
            s.a("RewardVideoController", "=====================onShowFail=====================");
            try {
                if (this.b != null) {
                    if (a.this.B) {
                        a.j(a.this);
                    }
                    this.b.H = false;
                    if (this.b.m != null) {
                        try {
                            if (a.this.U != null && a.this.U.size() > 0) {
                                com.mbridge.msdk.reward.b.a.a((CampaignEx) a.this.U.get(0), com.mbridge.msdk.foundation.controller.a.f().j(), "show failed: " + str, a.this.p, a.this.B, "", "", 0L);
                            }
                        } catch (Exception unused) {
                        }
                        try {
                            this.b.m.onShowFail(a.this.r, str);
                        } catch (Exception e) {
                            if (MBridgeConstans.DEBUG) {
                                e.printStackTrace();
                            }
                        }
                        this.e = 4;
                    }
                }
            } catch (Exception e2) {
                this.e = 0;
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(boolean z, String str, String str2) {
            s.a("RewardVideoController", "=====================onVideoAdClicked=====================");
            try {
                if (this.b == null || this.b.m == null) {
                    return;
                }
                try {
                    if (a.this.B) {
                        a.j(a.this);
                    }
                    this.b.m.onVideoAdClicked(z, a.this.r);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(String str, String str2) {
            s.a("RewardVideoController", "=====================onVideoComplete=====================");
            s.a("RewardVideoController", "onVideoComplete start");
            try {
                if (this.b == null || this.b.m == null) {
                    return;
                }
                try {
                    this.b.m.onVideoComplete(a.this.r);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
                this.e = 5;
                s.a("RewardVideoController", "onEndcardShow callback");
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(boolean z, int i) {
            s.a("RewardVideoController", "=====================onAdCloseWithIVReward=====================");
            s.a("RewardVideoController", "onAdCloseWithIVReward start");
            try {
                if (this.b == null || this.b.m == null) {
                    return;
                }
                this.b.H = false;
                try {
                    this.b.m.onAdCloseWithIVReward(a.this.r, new RewardInfo(z, i));
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
                s.a("RewardVideoController", "onAdCloseWithIVReward callback");
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void b(String str, String str2) {
            s.a("RewardVideoController", "=====================onEndcardShow=====================");
            s.a("RewardVideoController", "onEndcardShow start");
            try {
                if (this.b == null || this.b.m == null) {
                    return;
                }
                try {
                    this.b.m.onEndcardShow(a.this.r);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
                this.e = 6;
                s.a("RewardVideoController", "onEndcardShow callback");
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.mbridge.msdk.video.bt.module.b.h
        public final void a(int i, String str, String str2) {
            this.e = i;
            s.a("RewardVideoController", "===================== onAutoLoad =====================");
            if (this.b == null || this.b.B || this.b.A || this.b.k == null || !this.b.k.v(this.e) || this.b.n == null || c.a(this.b.n) == 1 || c.a(this.b.n) == 3 || a.this.G.contains(Integer.valueOf(this.e))) {
                return;
            }
            a.this.G.add(Integer.valueOf(this.e));
            s.a("RewardVideoController", "===================== onAutoLoad 进来了=====================");
            int iE = this.b.k.E() * 1000;
            if (this.e == 4) {
                iE = PushConstants.WORK_RECEIVER_EVENT_CORE_ERROR;
            }
            if (this.d != null) {
                this.d.removeMessages(1001001);
                this.d.sendEmptyMessageDelayed(1001001, iE);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mbridge.msdk.reward.a.a.d.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.this.b != null) {
                            d.this.b.d(false);
                        }
                    }
                }, iE);
            }
        }
    }

    public final void a(InterVideoOutListener interVideoOutListener) {
        this.m = interVideoOutListener;
        this.n = new c(interVideoOutListener, this.v, this.p);
    }

    public final void b(String str, String str2) {
        List<CampaignEx> listA;
        try {
            this.h = com.mbridge.msdk.foundation.controller.a.f().j();
            this.p = str2;
            this.q = str;
            this.r = new MBridgeIds(this.q, this.p);
            this.l = com.mbridge.msdk.videocommon.d.b.a().b();
            com.mbridge.msdk.reward.b.a.a(this.h, this.p);
            q.b();
            ResourceManager.getinstance().cleanZipRes();
            HTMLResourceManager.getInstance().cleanHtmlRes();
            com.mbridge.msdk.videocommon.d.b.a().a(this.p);
            if (!TextUtils.isEmpty(this.p) && (listA = com.mbridge.msdk.videocommon.a.a.a().a(this.p, 1)) != null && listA.size() > 0) {
                com.mbridge.msdk.videocommon.download.b.getInstance().createUnitCache(this.h, this.p, listA, 94, (com.mbridge.msdk.videocommon.listener.a) null);
            }
            if (this.M == null) {
                this.M = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
            }
        } catch (Throwable th) {
            s.b("RewardVideoController", th.getMessage(), th);
        }
    }

    public final void d(boolean z) {
        a(z, "");
    }

    private void c() {
        f.a(i.a(this.h)).a(this.p);
    }

    private void d() {
        try {
            List<CampaignEx> listB = f.a(i.a(this.h)).b(this.p);
            if (listB == null || listB.size() <= 0) {
                return;
            }
            for (CampaignEx campaignEx : listB) {
                if (!TextUtils.isEmpty(campaignEx.getMof_template_url())) {
                    com.mbridge.msdk.videocommon.a.b(this.p + "_" + campaignEx.getRequestId() + "_" + campaignEx.getMof_template_url());
                    if (campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d())) {
                        com.mbridge.msdk.videocommon.a.b(this.p + "_" + campaignEx.getId() + "_" + campaignEx.getRequestId() + "_" + campaignEx.getRewardTemplateMode().d());
                    }
                } else {
                    com.mbridge.msdk.videocommon.a.b(campaignEx.getAdType(), campaignEx);
                }
            }
        } catch (Exception unused) {
        }
    }

    public final void a(boolean z, String str) {
        s.a("RewardVideoController", "发起一次 load： isDevCall " + z + " " + str);
        if (this.n != null && c.a(this.n) == 1) {
            if (!this.E) {
                if (z) {
                    s.a("RewardVideoController", "本地存在由自动 load 发起的load，此次 load 是开发者发起的，将回调转移给开发者");
                    this.E = z;
                    return;
                } else {
                    s.a("RewardVideoController", "本地存在由自动 load 发起的load，此次 load 是自动 load 发起的，不做任何处理");
                    return;
                }
            }
            if (z) {
                s.a("RewardVideoController", "本地存在由开发者发起的load，此次 load 也是开发者发起的，回调 CURRENT_UNIT_IS_LOADING");
                com.mbridge.msdk.reward.b.a.b(this.h, "current unit is loading", this.p, this.B, "", "");
                this.n.a("errorCode: 3501 errorMessage: current unit is loading");
                return;
            }
            s.a("RewardVideoController", "本地存在由开发者发起的load，此次 load 是自动 load，不做任何处理");
            return;
        }
        if (this.n != null && c.a(this.n) == 3) {
            this.N = false;
        } else {
            this.N = true;
            c.a(this.n, 1);
        }
        this.E = z;
        this.v.removeMessages(1001001);
        this.Q = false;
        this.P = false;
        this.R = false;
        this.S = false;
        d();
        c();
        com.mbridge.msdk.reward.adapter.c.a().b();
        try {
            if (TextUtils.isEmpty(str)) {
                if (this.A) {
                    com.mbridge.msdk.d.b.getInstance().addInterstitialList(this.q, this.p);
                } else {
                    com.mbridge.msdk.d.b.getInstance().addRewardList(this.q, this.p);
                }
            }
            if (this.B && TextUtils.isEmpty(str)) {
                if (this.n != null) {
                    com.mbridge.msdk.reward.b.a.b(this.h, "bidToken is empty", this.p, this.B, "", "");
                    c.c(this.n, "bidToken is empty");
                    return;
                }
                return;
            }
            if (com.mbridge.msdk.system.a.a == null) {
                if (this.n != null) {
                    com.mbridge.msdk.reward.b.a.b(this.h, "init error", this.p, this.B, "", "");
                    c.c(this.n, "init error");
                    s.a("RewardVideoController", "SDK 初始化错误");
                    return;
                }
                return;
            }
            this.k = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.p);
            if (this.k == null) {
                s.a("RewardVideoController", "当前 RewardUnitSetting 为空，请求新的配置信息");
                this.L = com.mbridge.msdk.foundation.controller.a.f().k();
                com.mbridge.msdk.videocommon.d.b.a().a(this.L, com.mbridge.msdk.foundation.controller.a.f().l(), this.p, new com.mbridge.msdk.videocommon.c.c() { // from class: com.mbridge.msdk.reward.a.a.2
                    @Override // com.mbridge.msdk.videocommon.c.c
                    public final void a(String str2) {
                        s.a("RewardVideoController", "RewardUnitSetting 请求成功： " + str2);
                    }

                    @Override // com.mbridge.msdk.videocommon.c.c
                    public final void b(String str2) {
                        s.a("RewardVideoController", "RewardUnitSetting 请求失败： " + str2);
                    }
                });
                this.k = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.p, this.A);
            }
            if (!TextUtils.isEmpty(this.q)) {
                this.k.b(this.q);
            }
            int iH = this.k.H() * 1000;
            if (this.v != null) {
                s.a("RewardVideoController", "start load timeout for " + iH + " ms");
                this.v.sendEmptyMessageDelayed(1001002, (long) iH);
            }
            this.K = this.k.F();
            try {
                if (f()) {
                    s.a("RewardVideoController", "当前 cap 全满，清除所有的 cap");
                    e();
                }
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            }
            try {
                b(z, str);
            } catch (Exception e2) {
                try {
                    if (this.U != null && this.U.size() > 0) {
                        com.mbridge.msdk.reward.b.a.a(this.U.get(0), this.h, "load exception: " + e2.getMessage(), this.p, this.B, "", "", 0L);
                    }
                } catch (Exception unused) {
                }
                if (this.n != null) {
                    c.a(this.n, "load exception");
                }
            }
        } catch (Exception e3) {
            if (this.n != null) {
                c.a(this.n, "load exception");
                com.mbridge.msdk.reward.b.a.b(this.h, "load exception " + e3.getMessage(), this.p, this.B, "", "");
            }
            if (MBridgeConstans.DEBUG) {
                e3.printStackTrace();
            }
        }
    }

    private void b(boolean z, String str) {
        if (this.n != null) {
            if (!this.B) {
                this.T = b(this.p);
                if (this.T) {
                    s.a("RewardVideoController", "本地存在可用的缓存: " + this.U.size() + " 条");
                    s.a("RewardVideoController", "本地存在可用的缓存，非 BID Campaign");
                    if (!j()) {
                        s.a("RewardVideoController", "本地存在可用的缓存，没有超过上限，并预加载各种资源");
                        List<CampaignEx> list = this.U;
                        if (list == null || list.size() <= 0) {
                            return;
                        }
                        c.c(this.n, this.q, this.p);
                        s.a("RewardVideoController", "非BID，本地存在可用的缓存，没有超过上限，回调 onCampaignLoadSuccess，开始预加载资源");
                        CampaignEx campaignEx = list.get(0);
                        this.n.a(this.B);
                        if (this.j != null && this.j.a(list, true ^ TextUtils.isEmpty(campaignEx.getMof_template_url()), campaignEx.getNscpt())) {
                            com.mbridge.msdk.reward.b.a.a(this.h, this.U, this.p);
                            c.b(this.n, this.q, this.p);
                        } else {
                            a(list, this.j);
                        }
                        if (this.N) {
                            if (this.j != null) {
                                this.j.a(this.U);
                            }
                            a(this.K, z, str);
                            return;
                        }
                        return;
                    }
                    s.a("RewardVideoController", "非BID，本地存在可用的缓存，超过上限");
                    if (this.U != null && this.U.size() > 0) {
                        Iterator<CampaignEx> it = this.U.iterator();
                        while (it.hasNext()) {
                            com.mbridge.msdk.videocommon.a.a.a().a(it.next(), this.p);
                        }
                    }
                    if (z) {
                        if (this.n != null) {
                            c.c(this.n, "checkOverCap failed");
                            return;
                        }
                        return;
                    } else {
                        this.T = false;
                        this.n.a(this.B);
                        a(this.K, z, str);
                        return;
                    }
                }
                s.a("RewardVideoController", "非BID，不存在可用的缓存");
                if (!j()) {
                    this.T = false;
                    if (this.j != null) {
                        this.j.a(this.U);
                    }
                    this.n.a(this.B);
                    a(this.K, z, str);
                    return;
                }
                if (z) {
                    if (this.n != null) {
                        c.c(this.n, "checkOverCap failed");
                        return;
                    }
                    return;
                } else {
                    this.T = false;
                    if (this.j != null) {
                        this.j.a(this.U);
                    }
                    this.n.a(this.B);
                    a(this.K, z, str);
                    return;
                }
            }
            List<CampaignEx> listA = com.mbridge.msdk.videocommon.a.a.a().a(this.p, 1, this.B, "");
            if (listA != null && listA.size() > 0) {
                s.d("HBOPTIMIZE", "执行存在缓存逻辑");
                s.a("RewardVideoController", "BID，本地存在可用的缓存");
                if (!j()) {
                    List<CampaignEx> listB = b(str, listA);
                    if (listB != null && listB.size() > 0) {
                        s.d("HBOPTIMIZE", "执行 BidToken 存在逻辑");
                        a(listB, str);
                        return;
                    }
                    List<CampaignEx> listA2 = a(str, listA);
                    if (listA2 != null && listA2.size() > 0) {
                        s.d("HBOPTIMIZE", "需要被替换Offer的 bidToken " + str);
                        a(listA2, str);
                        com.mbridge.msdk.foundation.db.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, listA2);
                        if (this.j != null) {
                            this.j.a(listA2);
                        }
                    }
                    this.T = false;
                    this.n.a(this.B);
                    s.d("HBOPTIMIZE", "执行tokenRule 为 1 继续请求逻辑");
                    a(this.K, z, str);
                    return;
                }
                s.a("RewardVideoController", "BID，本地存在可用的缓存，超过上限");
                com.mbridge.msdk.videocommon.a.a.a().a(this.p, str);
                if (!z || this.n == null) {
                    return;
                }
                c.c(this.n, "checkOverCap failed");
                return;
            }
            s.a("RewardVideoController", "BID，不存在可用的缓存");
            s.d("HBOPTIMIZE", "执行 无可用缓存逻辑");
            if (!j()) {
                this.T = false;
                this.n.a(this.B);
                a(this.K, z, str);
            } else {
                com.mbridge.msdk.videocommon.a.a.a().a(this.p, str);
                if (!z || this.n == null) {
                    return;
                }
                c.c(this.n, "checkOverCap failed");
            }
        }
    }

    private void a(List<CampaignEx> list, String str) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.T = true;
        this.n.a(this.B);
        c.c(this.n, this.q, this.p);
        CampaignEx campaignEx = list.get(0);
        a(campaignEx.getRequestId(), str);
        if (this.j != null && this.j.a(list, true ^ TextUtils.isEmpty(campaignEx.getMof_template_url()), campaignEx.getNscpt())) {
            com.mbridge.msdk.reward.b.a.a(this.h, this.U, this.p);
            c.b(this.n, this.q, this.p);
        } else {
            a(list, this.j);
        }
    }

    private List<CampaignEx> a(String str, List<CampaignEx> list) {
        ArrayList arrayList = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    int vcn = 1;
                    for (CampaignEx campaignEx : list) {
                        if (campaignEx != null) {
                            if (campaignEx.getVcn() > vcn) {
                                vcn = campaignEx.getVcn();
                            }
                            if (campaignEx.getTokenRule() == 1) {
                                String requestId = campaignEx.getRequestId();
                                if (!TextUtils.isEmpty(requestId)) {
                                    if (linkedHashMap.containsKey(requestId)) {
                                        ((List) linkedHashMap.get(requestId)).add(campaignEx);
                                    } else {
                                        ArrayList arrayList2 = new ArrayList();
                                        arrayList2.add(campaignEx);
                                        linkedHashMap.put(requestId, arrayList2);
                                    }
                                }
                            } else {
                                String requestId2 = campaignEx.getRequestId();
                                if (!TextUtils.isEmpty(requestId2)) {
                                    if (linkedHashMap2.containsKey(requestId2)) {
                                        ((List) linkedHashMap2.get(requestId2)).add(campaignEx);
                                    } else {
                                        ArrayList arrayList3 = new ArrayList();
                                        arrayList3.add(campaignEx);
                                        linkedHashMap2.put(requestId2, arrayList3);
                                    }
                                }
                            }
                        }
                    }
                    if (linkedHashMap2.size() + linkedHashMap.size() >= vcn && linkedHashMap.size() > 0 && linkedHashMap.entrySet() != null && linkedHashMap.entrySet().iterator() != null && linkedHashMap.entrySet().iterator().next() != null && ((Map.Entry) linkedHashMap.entrySet().iterator().next()).getValue() != null) {
                        arrayList.addAll((Collection) ((Map.Entry) linkedHashMap.entrySet().iterator().next()).getValue());
                        com.mbridge.msdk.foundation.same.a.d.a(this.p, str, vcn);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private List<CampaignEx> b(String str, List<CampaignEx> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0 && !TextUtils.isEmpty(str)) {
            for (CampaignEx campaignEx : list) {
                if (campaignEx != null && str.equals(campaignEx.getBidToken())) {
                    arrayList.add(campaignEx);
                }
            }
        }
        return arrayList;
    }

    private void a(List<CampaignEx> list, com.mbridge.msdk.reward.adapter.d dVar) {
        final CampaignEx campaignEx = list.get(0);
        final boolean z = !TextUtils.isEmpty(campaignEx.getMof_template_url());
        final int nscpt = campaignEx.getNscpt();
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        com.mbridge.msdk.reward.adapter.c.a().a(this.h, z, nscpt, this.B, this.A ? 287 : 94, this.q, this.p, campaignEx.getRequestId(), list, new AnonymousClass3(z, campaignEx, dVar, nscpt), new c.i() { // from class: com.mbridge.msdk.reward.a.a.4
            @Override // com.mbridge.msdk.reward.adapter.c.i
            public final void a(String str, String str2, String str3, String str4) {
                s.a("RewardVideoController", "Cache template 下载成功： ");
            }

            @Override // com.mbridge.msdk.reward.adapter.c.i
            public final void a(String str, String str2, String str3, String str4, String str5) {
                s.a("RewardVideoController", "Cache template 下载失败： ");
                a.a(a.this, str2, a.this.V);
                if (!z && a.this.n != null) {
                    if (a.this.f) {
                        return;
                    }
                    a.this.f = true;
                    com.mbridge.msdk.reward.b.a.a(a.this.h, "" + str5, a.this.p, a.this.B, str3, campaignEx.getRequestIdNotice());
                    c.d(a.this.n, "errorCode: 3202 errorMessage: temp resource download failed");
                    return;
                }
                if (nscpt == 1) {
                    s.a("RewardVideoController", "播放模板下载失败，大模板，nscpt 1");
                    if (a.this.f || a.this.n == null) {
                        return;
                    }
                    a.this.f = true;
                    com.mbridge.msdk.reward.b.a.a(a.this.h, "temp resource download failed", a.this.p, a.this.B, str3, campaignEx.getRequestIdNotice());
                    c.d(a.this.n, "errorCode: 3202 errorMessage: temp resource download failed");
                }
            }
        });
        if (z) {
            com.mbridge.msdk.reward.adapter.c.a().a(this.h, campaignEx, this.q, this.p, campaignEx.getRequestId(), new AnonymousClass5(campaignEx, list, dVar, z, nscpt));
        }
    }

    /* compiled from: RewardVideoController.java */
    /* renamed from: com.mbridge.msdk.reward.a.a$3, reason: invalid class name */
    final class AnonymousClass3 implements c.InterfaceC0138c {
        final /* synthetic */ boolean a;
        final /* synthetic */ CampaignEx b;
        final /* synthetic */ com.mbridge.msdk.reward.adapter.d c;
        final /* synthetic */ int d;

        AnonymousClass3(boolean z, CampaignEx campaignEx, com.mbridge.msdk.reward.adapter.d dVar, int i) {
            this.a = z;
            this.b = campaignEx;
            this.c = dVar;
            this.d = i;
        }

        @Override // com.mbridge.msdk.reward.adapter.c.InterfaceC0138c
        public final void a(final String str, final String str2, final String str3, final List<CampaignEx> list) {
            s.a("RewardVideoController", "Campaign Cache 下载成功： " + list.size());
            a.this.c = true;
            if (!this.a) {
                s.a("RewardVideoController", "Campaign Cache 下载成功： 非大模板");
                for (final CampaignEx campaignEx : list) {
                    if (campaignEx != null && campaignEx.getRewardTemplateMode() != null && !TextUtils.isEmpty(campaignEx.getRewardTemplateMode().d()) && a.this.v != null) {
                        a.this.v.post(new Runnable() { // from class: com.mbridge.msdk.reward.a.a.3.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                com.mbridge.msdk.reward.adapter.c.a().a(a.this.F, a.this.v, a.this.A, a.this.B, null, campaignEx.getRewardTemplateMode().d(), a.this.w, AnonymousClass3.this.b, list, H5DownLoadManager.getInstance().getH5ResAddress(campaignEx.getRewardTemplateMode().d()), str, str2, str3, campaignEx.getRequestIdNotice(), a.this.k, new c.j() { // from class: com.mbridge.msdk.reward.a.a.3.1.1
                                    @Override // com.mbridge.msdk.reward.adapter.c.j
                                    public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a) {
                                        s.a("RewardVideoController", "Campaign Cache 下载成功： 非大模板，播放模板预加载");
                                        if (AnonymousClass3.this.c == null || !AnonymousClass3.this.c.a(list, AnonymousClass3.this.a, AnonymousClass3.this.d)) {
                                            a.a(a.this, str6, a.this.V);
                                            if (a.this.n == null || a.this.f) {
                                                return;
                                            }
                                            a.this.f = true;
                                            s.a("RewardVideoController", "Cache onVideoLoadFailForCache");
                                            com.mbridge.msdk.reward.b.a.a(a.this.h, "temp preload success but isReady false", a.this.p, a.this.B, str7, campaignEx.getRequestIdNotice());
                                            c.d(a.this.n, "errorCode: 3502 errorMessage: temp preload success but isReady false");
                                            return;
                                        }
                                        if (a.this.n == null || a.this.g) {
                                            return;
                                        }
                                        a.this.g = true;
                                        s.a("RewardVideoController", "Cache onVideoLoadSuccessForCache");
                                        com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) list, a.this.p);
                                        c.b(a.this.n, str5, str6);
                                    }

                                    @Override // com.mbridge.msdk.reward.adapter.c.j
                                    public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a, String str9) {
                                        s.a("RewardVideoController", "Campaign Cache 下载成功： 非大模板，播放模板预加载失败");
                                        a.a(a.this, str6, a.this.V);
                                        if (a.this.n == null || a.this.f) {
                                            return;
                                        }
                                        a.this.f = true;
                                        com.mbridge.msdk.reward.b.a.a(a.this.h, "temp preload failed: " + str9, a.this.p, a.this.B, str7, campaignEx.getRequestIdNotice());
                                        c.d(a.this.n, "errorCode: 3301 errorMessage: temp preload failed: " + str9);
                                    }
                                });
                            }
                        });
                    } else {
                        s.a("RewardVideoController", "Campaign Cache 下载成功： 非大模板，不存在播放模板");
                        if (this.c == null || !this.c.a(list, this.a, this.d)) {
                            a.a(a.this, str2, a.this.V);
                            if (a.this.n != null && !a.this.f) {
                                a.this.f = true;
                                s.a("RewardVideoController", "Cache onVideoLoadFailForCache");
                                String requestIdNotice = "";
                                if (campaignEx != null) {
                                    requestIdNotice = TextUtils.isEmpty(campaignEx.getRequestIdNotice()) ? "" : campaignEx.getRequestIdNotice();
                                }
                                com.mbridge.msdk.reward.b.a.a(a.this.h, "have no temp but isReady false", a.this.p, a.this.B, str3, requestIdNotice);
                                c.d(a.this.n, "errorCode: 3503 errorMessage: have no temp but isReady false");
                            }
                        } else if (a.this.n != null && !a.this.g) {
                            a.this.g = true;
                            s.a("RewardVideoController", "Cache onVideoLoadSuccessForCache");
                            com.mbridge.msdk.reward.b.a.a(a.this.h, list, a.this.p);
                            c.b(a.this.n, str, str2);
                        }
                    }
                }
                return;
            }
            s.a("RewardVideoController", "Campaign Cache 下载成功： 大模板");
            if (!a.this.d || a.this.e || a.this.v == null) {
                return;
            }
            s.a("RewardVideoController", "Campaign Cache 下载成功： 大模板，campaign 下载成功，开始预加载大模板");
            a.this.e = true;
            a.this.v.post(new Runnable() { // from class: com.mbridge.msdk.reward.a.a.3.2
                @Override // java.lang.Runnable
                public final void run() {
                    com.mbridge.msdk.reward.adapter.c.a().a(a.this.F, a.this.v, a.this.A, a.this.B, str3, AnonymousClass3.this.b.getRequestIdNotice(), str, str2, AnonymousClass3.this.b.getMof_template_url(), a.this.w, AnonymousClass3.this.b, list, H5DownLoadManager.getInstance().getH5ResAddress(AnonymousClass3.this.b.getMof_template_url()), str2, a.this.k, new c.j() { // from class: com.mbridge.msdk.reward.a.a.3.2.1
                        @Override // com.mbridge.msdk.reward.adapter.c.j
                        public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a) {
                            s.a("RewardVideoController", "Campaign Cache 下载成功： 大模板预加载成功");
                            if (AnonymousClass3.this.c == null || !AnonymousClass3.this.c.a(list, AnonymousClass3.this.a, AnonymousClass3.this.d)) {
                                a.a(a.this, str6, a.this.V);
                                if (a.this.n == null || a.this.f) {
                                    return;
                                }
                                a.this.f = true;
                                com.mbridge.msdk.reward.b.a.a(a.this.h, "tpl temp preload success but isReady false", a.this.p, a.this.B, str7, AnonymousClass3.this.b.getRequestIdNotice());
                                c.d(a.this.n, "errorCode: 3504 errorMessage: tpl temp preload success but isReady false");
                                return;
                            }
                            if (a.this.n == null || a.this.g) {
                                return;
                            }
                            a.this.g = true;
                            s.a("RewardVideoController", "Cache onVideoLoadSuccessForCache");
                            com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) list, a.this.p);
                            c.b(a.this.n, str5, str6);
                        }

                        @Override // com.mbridge.msdk.reward.adapter.c.j
                        public final void a(String str4, String str5, String str6, String str7, String str8, a.C0146a c0146a, String str9) {
                            s.a("RewardVideoController", "Campaign Cache 下载成功： 大模板预加载失败");
                            a.a(a.this, str6, a.this.V);
                            if (a.this.n == null || a.this.f) {
                                return;
                            }
                            a.this.f = true;
                            com.mbridge.msdk.reward.b.a.a(a.this.h, "tpl temp preload failed: " + str9, a.this.p, a.this.B, str7, AnonymousClass3.this.b.getRequestIdNotice());
                            c.d(a.this.n, "errorCode: 3302 errorMessage: tpl temp preload failed: " + str9);
                        }
                    }, true);
                }
            });
        }

        @Override // com.mbridge.msdk.reward.adapter.c.InterfaceC0138c
        public final void a(String str, String str2, String str3, List<CampaignEx> list, String str4) {
            s.a("RewardVideoController", "Campaign Cache 下载失败： " + list.size());
            a.this.c = false;
            a.a(a.this, str2, a.this.V);
            if (a.this.n == null || a.this.f) {
                return;
            }
            a.this.f = true;
            com.mbridge.msdk.reward.b.a.a(a.this.h, "" + str4, a.this.p, a.this.B, str3, this.b.getRequestIdNotice());
            c.d(a.this.n, "errorCode: 3201 errorMessage: campaign resource download failed");
        }
    }

    /* compiled from: RewardVideoController.java */
    /* renamed from: com.mbridge.msdk.reward.a.a$5, reason: invalid class name */
    final class AnonymousClass5 implements c.i {
        final /* synthetic */ CampaignEx a;
        final /* synthetic */ List b;
        final /* synthetic */ com.mbridge.msdk.reward.adapter.d c;
        final /* synthetic */ boolean d;
        final /* synthetic */ int e;

        AnonymousClass5(CampaignEx campaignEx, List list, com.mbridge.msdk.reward.adapter.d dVar, boolean z, int i) {
            this.a = campaignEx;
            this.b = list;
            this.c = dVar;
            this.d = z;
            this.e = i;
        }

        @Override // com.mbridge.msdk.reward.adapter.c.i
        public final void a(final String str, final String str2, final String str3, String str4) {
            a.this.d = true;
            if (!a.this.c || a.this.e || a.this.v == null) {
                return;
            }
            a.this.e = true;
            a.this.v.post(new Runnable() { // from class: com.mbridge.msdk.reward.a.a.5.1
                @Override // java.lang.Runnable
                public final void run() {
                    com.mbridge.msdk.reward.adapter.c.a().a(a.this.F, a.this.v, a.this.A, a.this.B, str3, AnonymousClass5.this.a.getRequestIdNotice(), str, str2, AnonymousClass5.this.a.getMof_template_url(), a.this.w, AnonymousClass5.this.a, AnonymousClass5.this.b, H5DownLoadManager.getInstance().getH5ResAddress(AnonymousClass5.this.a.getMof_template_url()), str2, a.this.k, new c.j() { // from class: com.mbridge.msdk.reward.a.a.5.1.1
                        @Override // com.mbridge.msdk.reward.adapter.c.j
                        public final void a(String str5, String str6, String str7, String str8, String str9, a.C0146a c0146a) {
                            if (AnonymousClass5.this.c.a(AnonymousClass5.this.b, AnonymousClass5.this.d, AnonymousClass5.this.e)) {
                                if (a.this.n == null || a.this.g) {
                                    return;
                                }
                                a.this.g = true;
                                s.a("RewardVideoController", "Cache onVideoLoadSuccess");
                                com.mbridge.msdk.reward.b.a.a(a.this.h, (List<CampaignEx>) AnonymousClass5.this.b, a.this.p);
                                c.b(a.this.n, str6, str7);
                                return;
                            }
                            a.a(a.this, str7, a.this.V);
                            if (a.this.n == null || a.this.f) {
                                return;
                            }
                            a.this.f = true;
                            com.mbridge.msdk.reward.b.a.a(a.this.h, "tpl temp preload success but isReady false", a.this.p, a.this.B, str8, AnonymousClass5.this.a.getRequestIdNotice());
                            c.d(a.this.n, "errorCode: 3505 errorMessage: tpl temp preload success but isReady false");
                        }

                        @Override // com.mbridge.msdk.reward.adapter.c.j
                        public final void a(String str5, String str6, String str7, String str8, String str9, a.C0146a c0146a, String str10) {
                            a.a(a.this, str7, a.this.V);
                            if (a.this.n == null || a.this.f) {
                                return;
                            }
                            a.this.f = true;
                            com.mbridge.msdk.reward.b.a.a(a.this.h, "tpl temp preload failed: " + str10, a.this.p, a.this.B, str8, AnonymousClass5.this.a.getRequestIdNotice());
                            c.d(a.this.n, "errorCode: 3303 errorMessage: tpl temp preload failed: " + str10);
                        }
                    }, true);
                }
            });
        }

        @Override // com.mbridge.msdk.reward.adapter.c.i
        public final void a(String str, String str2, String str3, String str4, String str5) {
            a.a(a.this, str2, a.this.V);
            a.this.d = false;
            if (a.this.n == null || a.this.f) {
                return;
            }
            a.this.f = true;
            com.mbridge.msdk.reward.b.a.a(a.this.h, "" + str5, a.this.p, a.this.B, str3, this.a.getRequestIdNotice());
            c.d(a.this.n, "errorCode: 3203 errorMessage: tpl temp resource download failed");
        }
    }

    private boolean b(String str) {
        try {
            if (this.j == null || !str.equals(this.j.a())) {
                this.j = new com.mbridge.msdk.reward.adapter.d(this.h, this.q, str);
                this.j.a(this.A);
                this.j.b(this.B);
            }
            this.j.a(this.w);
            this.j.a(this.k);
        } catch (Exception unused) {
        }
        List<CampaignEx> listA = com.mbridge.msdk.videocommon.a.a.a().a(str, 1, this.B);
        StringBuilder sb = new StringBuilder();
        sb.append("当前可用的缓存数据： ");
        sb.append(listA == null ? 0 : listA.size());
        s.a("RewardVideoController", sb.toString());
        this.V = com.mbridge.msdk.videocommon.a.a.a().b(str, 1, this.B);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("当前全部的缓存数据： ");
        sb2.append(this.V == null ? 0 : this.V.size());
        s.a("RewardVideoController", sb2.toString());
        if (listA != null && listA.size() > 0) {
            if (this.U == null) {
                this.U = new ArrayList();
            } else {
                this.U.clear();
            }
            this.U.addAll(listA);
            return true;
        }
        if (this.U != null) {
            this.U.clear();
        }
        return false;
    }

    /* compiled from: RewardVideoController.java */
    private final class c {
        private WeakReference<InterVideoOutListener> b;
        private volatile AtomicInteger c;
        private Handler d;
        private String e;
        private boolean f;

        private c(InterVideoOutListener interVideoOutListener, Handler handler, String str) {
            this.b = new WeakReference<>(interVideoOutListener);
            this.c = new AtomicInteger(0);
            this.d = handler;
            this.e = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            if (this.d != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = str;
                messageObtain.what = 18;
                this.d.sendMessage(messageObtain);
            }
        }

        public final void a(boolean z) {
            this.f = z;
        }

        static /* synthetic */ void a(c cVar, String str, String str2) {
            if (cVar.d != null) {
                cVar.d.removeMessages(1001002);
            }
            s.a("RewardVideoController_Listener", "收到 onVideoLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q);
            if (cVar.b == null || cVar.b.get() == null) {
                return;
            }
            if ((cVar.c.get() == 1 || cVar.c.get() == 3) && cVar.d != null) {
                cVar.c.set(2);
                if (!a.this.Q) {
                    s.d("RewardVideoController_Listener", "收到 onVideoLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q + " 响应");
                    a.this.Q = true;
                    Message messageObtain = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                    bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                    messageObtain.setData(bundle);
                    messageObtain.obj = str2;
                    messageObtain.what = 9;
                    cVar.d.sendMessage(messageObtain);
                    return;
                }
                s.a("RewardVideoController_Listener", "收到 onVideoLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q + " 无需响应");
            }
        }

        static /* synthetic */ void a(c cVar, String str) {
            if (cVar.d != null) {
                cVar.d.removeMessages(1001002);
            }
            s.a("RewardVideoController_Listener", "收到 onVideoLoadFail，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFail: " + a.this.R + " " + str);
            if (cVar.b == null || cVar.b.get() == null) {
                return;
            }
            if ((cVar.c.get() == 1 || cVar.c.get() == 3) && cVar.d != null) {
                a.this.R = true;
                if (!a.this.T || str.contains("resource load timeout")) {
                    a.this.S = true;
                }
                if (a.this.Q) {
                    cVar.c.set(2);
                }
                if (a.this.R && a.this.S && !a.this.Q) {
                    cVar.c.set(2);
                    s.d("RewardVideoController_Listener", "收到 onVideoLoadFail，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFail: " + a.this.R + " " + str + " 响应");
                    Message messageObtain = Message.obtain();
                    messageObtain.obj = str;
                    messageObtain.what = 16;
                    cVar.d.sendMessage(messageObtain);
                    return;
                }
                s.a("RewardVideoController_Listener", "收到 onVideoLoadFail，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFail: " + a.this.R + " " + str + " 无需响应");
            }
        }

        static /* synthetic */ void b(c cVar, String str, String str2) {
            if (cVar.d != null) {
                cVar.d.removeMessages(1001002);
            }
            s.a("RewardVideoController_Listener", "收到 onVideoLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q);
            if (cVar.b == null || cVar.b.get() == null) {
                return;
            }
            if ((cVar.c.get() == 1 || cVar.c.get() == 3) && cVar.d != null) {
                if (cVar.c.get() == 1) {
                    cVar.c.set(3);
                }
                if (!a.this.Q) {
                    s.d("RewardVideoController_Listener", "收到 onVideoLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q + " 响应");
                    a.this.Q = true;
                    Message messageObtain = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                    bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                    messageObtain.setData(bundle);
                    messageObtain.obj = str2;
                    messageObtain.what = 9;
                    cVar.d.sendMessage(messageObtain);
                    if (a.this.R) {
                        cVar.c.set(2);
                        return;
                    }
                    return;
                }
                s.a("RewardVideoController_Listener", "收到 onVideoLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadSuccess: " + a.this.Q + " 无需响应");
            }
        }

        static /* synthetic */ int a(c cVar) {
            return cVar.c.get();
        }

        static /* synthetic */ void a(c cVar, int i) {
            cVar.c.set(i);
        }

        static /* synthetic */ void c(c cVar, String str) {
            cVar.c.set(2);
            cVar.a(str);
        }

        static /* synthetic */ void c(c cVar, String str, String str2) {
            s.a("RewardVideoController_Listener", "收到 onCampaignLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P);
            if (cVar.b == null || cVar.b.get() == null || cVar.d == null) {
                return;
            }
            if (!a.this.P) {
                s.d("RewardVideoController_Listener", "收到 onCampaignLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P + " 响应");
                a.this.P = true;
                Message messageObtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                messageObtain.setData(bundle);
                messageObtain.obj = str2;
                messageObtain.what = 17;
                cVar.d.sendMessage(messageObtain);
                return;
            }
            s.a("RewardVideoController_Listener", "收到 onCampaignLoadSuccessForCache，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P + " 无需响应");
        }

        static /* synthetic */ void d(c cVar, String str) {
            s.a("RewardVideoController_Listener", "收到 onVideoLoadFailForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFailedForCache: " + a.this.S + " " + str);
            if (cVar.b == null || cVar.b.get() == null) {
                return;
            }
            if ((cVar.c.get() == 1 || cVar.c.get() == 3) && cVar.d != null) {
                a.this.S = true;
                if (str.contains("resource load timeout")) {
                    a.this.R = true;
                }
                if (a.this.S && a.this.R && !a.this.Q) {
                    cVar.c.set(2);
                    s.d("RewardVideoController_Listener", "收到 hasCalledVideoLoadFailedForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFailedForCache: " + a.this.S + " " + str + " 响应");
                    Message messageObtain = Message.obtain();
                    messageObtain.obj = str;
                    messageObtain.what = 16;
                    cVar.d.sendMessage(messageObtain);
                    return;
                }
                s.a("RewardVideoController_Listener", "收到 onVideoLoadFailForCache，当前状态： " + cVar.c.get() + " hasCalledVideoLoadFailedForCache: " + a.this.S + " " + str + " 无需响应");
            }
        }

        static /* synthetic */ void d(c cVar, String str, String str2) {
            s.a("RewardVideoController_Listener", "收到 onCampaignLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P);
            if (cVar.b == null || cVar.b.get() == null) {
                return;
            }
            if ((cVar.c.get() == 1 || cVar.c.get() == 3) && cVar.d != null) {
                if (!a.this.P) {
                    s.d("RewardVideoController_Listener", "收到 onCampaignLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P + " 响应");
                    a.this.P = true;
                    Message messageObtain = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                    bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                    messageObtain.setData(bundle);
                    messageObtain.obj = str2;
                    messageObtain.what = 17;
                    cVar.d.sendMessage(messageObtain);
                    return;
                }
                s.a("RewardVideoController_Listener", "收到 onCampaignLoadSuccess，当前状态： " + cVar.c.get() + " hasCalledCampaignLoadSuccess: " + a.this.P + " 无需响应");
            }
        }
    }

    private void e() {
        try {
            List<com.mbridge.msdk.videocommon.b.b> listX = this.k.x();
            if (listX == null || listX.size() <= 0) {
                return;
            }
            for (int i = 0; i < listX.size(); i++) {
                com.mbridge.msdk.videocommon.b.b bVar = listX.get(i);
                y.a(this.h, this.q + "_" + bVar.a(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean f() {
        try {
            List<com.mbridge.msdk.videocommon.b.b> listX = this.k.x();
            if (this.l == null) {
                this.l = com.mbridge.msdk.videocommon.d.b.a().b();
            }
            Map<String, Integer> mapH = this.l.h();
            if (listX == null || listX.size() <= 0) {
                return true;
            }
            for (int i = 0; i < listX.size(); i++) {
                com.mbridge.msdk.videocommon.b.b bVar = listX.get(i);
                int iIntValue = mapH.containsKey(bVar.a() + "") ? mapH.get(bVar.a() + "").intValue() : 0;
                Object objB = y.b(this.h, this.q + "_" + bVar.a(), 0);
                if ((objB != null ? ((Integer) objB).intValue() : 0) < iIntValue) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            s.d("RewardVideoController", e.getMessage());
            return true;
        }
    }

    private void a(Queue<Integer> queue, boolean z, String str) {
        int iIntValue = 8;
        if (queue != null) {
            try {
                if (queue.size() > 0) {
                    iIntValue = queue.poll().intValue();
                }
            } catch (Exception e) {
                com.mbridge.msdk.reward.b.a.b(this.h, "can't show because unknow error", this.p, this.B, "", "");
                c("can't show because unknow error");
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                    return;
                }
                return;
            }
        }
        try {
            a(1, iIntValue, z, str);
        } catch (Exception e2) {
            com.mbridge.msdk.reward.b.a.b(this.h, "load mv api error:" + e2.getMessage(), this.p, this.B, "", "");
            c("load mv api error:" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (this.n != null) {
            this.S = true;
            c.a(this.n, str);
        }
    }

    private void a(int i, int i2, boolean z, String str) {
        try {
            s.a("RewardVideoController", "开始从 SOURCE_MBAPI 请求新的 offer: " + i2);
            if (this.j == null || !this.p.equals(this.j.a())) {
                this.j = new com.mbridge.msdk.reward.adapter.d(this.h, this.q, this.p);
                this.j.a(this.A);
                this.j.b(this.B);
            }
            if (this.A) {
                this.j.a(this.x, this.y, this.z);
            }
            this.j.a(this.w);
            this.j.a(this.k);
            RunnableC0137a runnableC0137a = new RunnableC0137a(this.j, i, z);
            b bVar = new b(this.j, z);
            bVar.a(runnableC0137a);
            this.j.a(bVar);
            this.v.postDelayed(runnableC0137a, i2 * 1000);
            this.j.a(i, i2, z, str, this.F);
        } catch (Exception e) {
            c(e.getMessage());
            com.mbridge.msdk.reward.b.a.b(this.h, e.getMessage(), this.p, this.B, "", "");
        }
    }

    public final void b(int i) {
        try {
            if (this.M == null) {
                this.M = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            j jVarA = j.a(this.M);
            if (jVarA != null) {
                jVarA.a(this.p);
            }
        } catch (Throwable unused) {
            s.d("RewardVideoController", "can't find DailyPlayCapDao");
        }
        if (i != 1) {
            return;
        }
        y.a(this.h, this.q + "_" + i, Integer.valueOf(this.i + 1));
    }

    private void d(String str) throws Throwable {
        boolean zB;
        boolean zC;
        Map<String, Integer> mapH;
        try {
            int iIntValue = (this.l == null || (mapH = this.l.h()) == null || !mapH.containsKey("1")) ? 0 : mapH.get("1").intValue();
            if (y.b(this.h, this.q + "_1", 0) != null) {
                this.i = ((Integer) y.b(this.h, this.q + "_1", 0)).intValue();
            }
            if (this.j == null) {
                i();
            }
            if (this.j != null) {
                s.d("RewardVideoController", "controller 819");
                if (this.B) {
                    zB = g();
                } else {
                    zB = this.j.b();
                }
                if (zB) {
                    if (this.i < iIntValue || iIntValue <= 0) {
                        s.b("RewardVideoController", "invoke adapter show isReady");
                        d dVar = new d(this, 1, this.v);
                        b.put(this.p, dVar);
                        this.j.a(dVar, str, this.o, this.w, this.s);
                        return;
                    }
                    return;
                }
                if (this.B) {
                    zC = h();
                } else {
                    zC = this.j.c();
                }
                if (zC) {
                    s.b("RewardVideoController", "invoke adapter show isSpareOfferReady");
                    d dVar2 = new d(this, 1, this.v);
                    b.put(this.p, dVar2);
                    this.j.a(dVar2, str, this.o, this.w, this.s);
                    return;
                }
            }
            if (this.i != 0) {
                y.a(this.h, this.q + "_1", 0);
                d(str);
                return;
            }
            if (this.m != null) {
                try {
                    this.m.onShowFail(this.r, "can't show because load is failed");
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.A || this.B || this.k == null || !this.k.v(4) || this.n == null || c.a(this.n) == 1 || c.a(this.n) == 3) {
                return;
            }
            a(false, "");
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                s.d("RewardVideoController", e2.getLocalizedMessage());
            }
            if (this.m != null) {
                try {
                    this.m.onShowFail(this.r, "show exception");
                } catch (Exception unused) {
                    if (MBridgeConstans.DEBUG) {
                        e2.printStackTrace();
                    }
                }
            }
            if (this.A || this.B || this.k == null || !this.k.v(4) || this.n == null || c.a(this.n) == 1 || c.a(this.n) == 3) {
                return;
            }
            a(false, "");
        }
    }

    public final boolean e(boolean z) {
        boolean zG;
        try {
            if (j()) {
                return false;
            }
            if (this.B) {
                try {
                    zG = g();
                } catch (Exception unused) {
                    return false;
                }
            } else {
                if (this.j == null) {
                    i();
                }
                if (this.j == null) {
                    return false;
                }
                zG = this.j.b();
            }
            return zG;
        } catch (Throwable th) {
            if (!MBridgeConstans.DEBUG) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private boolean g() {
        if (this.j == null) {
            i();
        }
        List<com.mbridge.msdk.foundation.entity.c> listA = com.mbridge.msdk.videocommon.a.a.a().a(this.p);
        if (listA == null || listA.size() <= 0) {
            return false;
        }
        String strB = com.mbridge.msdk.foundation.same.a.d.b(this.p);
        boolean zB = TextUtils.isEmpty(strB) ? false : b(listA, strB);
        if (zB) {
            return zB;
        }
        for (com.mbridge.msdk.foundation.entity.c cVar : listA) {
            if (cVar != null) {
                this.j.a(cVar.a());
                if (this.j.b()) {
                    return true;
                }
            }
        }
        return zB;
    }

    private boolean b(List<com.mbridge.msdk.foundation.entity.c> list, String str) {
        if (list == null) {
            return false;
        }
        for (com.mbridge.msdk.foundation.entity.c cVar : list) {
            if (cVar != null && str.equals(cVar.b())) {
                this.j.a(cVar.a());
                if (this.j.b()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean h() {
        if (this.j == null) {
            i();
        }
        List<com.mbridge.msdk.foundation.entity.c> listA = com.mbridge.msdk.videocommon.a.a.a().a(this.p);
        if (listA == null || listA.size() <= 0) {
            return false;
        }
        for (com.mbridge.msdk.foundation.entity.c cVar : listA) {
            if (cVar != null) {
                this.j.a(cVar.a());
                if (this.j.c()) {
                    return true;
                }
                com.mbridge.msdk.videocommon.a.a.a().b(this.p, cVar.a());
            }
        }
        return false;
    }

    private void i() {
        this.j = new com.mbridge.msdk.reward.adapter.d(this.h, this.q, this.p);
        this.j.a(this.A);
        this.j.b(this.B);
        if (this.A) {
            this.j.a(this.x, this.y, this.z);
        }
        this.j.a(this.k);
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r7, java.lang.String r8, java.lang.String r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.reward.a.a.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private boolean j() {
        try {
            if (this.M == null) {
                this.M = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            j jVarA = j.a(this.M);
            if (this.k == null) {
                this.k = com.mbridge.msdk.videocommon.d.b.a().a(com.mbridge.msdk.foundation.controller.a.f().k(), this.p, this.A);
            }
            int iF = this.k.f();
            if (jVarA != null) {
                return jVarA.a(this.p, iF);
            }
            return false;
        } catch (Throwable unused) {
            s.d("RewardVideoController", "cap check error");
            return false;
        }
    }

    /* compiled from: RewardVideoController.java */
    /* renamed from: com.mbridge.msdk.reward.a.a$a, reason: collision with other inner class name */
    public class RunnableC0137a implements Runnable {
        private com.mbridge.msdk.reward.adapter.a b;
        private int c;
        private boolean d;

        public RunnableC0137a(com.mbridge.msdk.reward.adapter.a aVar, int i, boolean z) {
            this.b = aVar;
            this.c = i;
            this.d = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            s.d("RewardVideoController", "adSource=" + this.c + " CommonCancelTimeTask mIsDevCall：" + this.d);
            com.mbridge.msdk.reward.b.a.b(a.this.h, "v3 is timeout", a.this.p, a.this.B, "", "");
            a.this.c("v3 is timeout");
        }
    }

    /* compiled from: RewardVideoController.java */
    public class b implements com.mbridge.msdk.reward.adapter.b {
        private com.mbridge.msdk.reward.adapter.a b;
        private boolean c;
        private Runnable d;

        public b(com.mbridge.msdk.reward.adapter.a aVar, boolean z) {
            this.b = aVar;
            this.c = z;
        }

        public final void a(Runnable runnable) {
            this.d = runnable;
        }

        @Override // com.mbridge.msdk.reward.adapter.b
        public final void b(String str) {
            if (this.d != null) {
                a.this.v.removeCallbacks(this.d);
            }
            if (a.this.n != null) {
                c.d(a.this.n, a.this.q, a.this.p);
            }
        }

        @Override // com.mbridge.msdk.reward.adapter.b
        public final void a() {
            if (this.d != null) {
                a.this.v.removeCallbacks(this.d);
            }
            if (a.this.n != null) {
                c.a(a.this.n, a.this.q, a.this.p);
            }
        }

        @Override // com.mbridge.msdk.reward.adapter.b
        public final void a(String str) {
            if (this.d != null) {
                a.this.v.removeCallbacks(this.d);
            }
            if (this.b != null) {
                this.b.a(null);
                this.b = null;
            }
            if (a.this.n != null) {
                c.a(a.this.n, str);
            }
        }
    }

    static /* synthetic */ void j(a aVar) {
        String str = "";
        if (aVar.r != null) {
            if (aVar.H) {
                String strB = aVar.b();
                if (TextUtils.isEmpty(strB)) {
                    return;
                }
                if (J != null && J.containsKey(strB) && !TextUtils.isEmpty(J.get(strB))) {
                    str = J.get(strB);
                }
            }
            aVar.r.setBidToken(str);
        }
    }

    static /* synthetic */ void a(a aVar, String str, List list) {
        if (list == null || list.size() <= 0 || aVar.Q) {
            return;
        }
        s.a("RewardVideoController", "标记缓存数据 ： " + list.size());
        f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a((List<CampaignEx>) list, str);
    }
}
