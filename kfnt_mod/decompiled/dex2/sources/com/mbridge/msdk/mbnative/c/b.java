package com.mbridge.msdk.mbnative.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.l;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.HTMLResourceManager;
import com.mbridge.msdk.foundation.download.download.ResourceManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.entity.h;
import com.mbridge.msdk.foundation.entity.j;
import com.mbridge.msdk.foundation.entity.n;
import com.mbridge.msdk.foundation.same.e.a;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbbid.out.BidResponsed;
import com.mbridge.msdk.mbnative.c.a;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static boolean B = false;
    public static Map<String, Long> c = new HashMap();
    private static final String e = "b";
    private boolean A;
    private int C;
    private int D;
    private int E;
    private int F;
    private com.mbridge.msdk.foundation.same.e.b G;
    private Map<String, Boolean> H;
    private List<com.mbridge.msdk.mbnative.c.a> I;
    private List<a.InterfaceC0122a> J;
    private CopyOnWriteArrayList<com.mbridge.msdk.foundation.same.e.a> K;
    private int L;
    private String M;
    private i N;
    private int O;
    private a P;
    private boolean Q;
    private boolean R;
    private String S;
    private Timer T;
    private String U;
    private com.mbridge.msdk.b.d V;
    private long W;
    private int X;
    private int Y;
    private boolean Z;
    protected List<Integer> a;
    private int aa;
    private int ab;
    private boolean ac;
    protected List<Integer> b;
    Map<String, Object> d;
    private com.mbridge.msdk.b.c f;
    private com.mbridge.msdk.mbnative.d.a g;
    private NativeListener.NativeTrackingListener h;
    private Context i;
    private String j;
    private String k;
    private Queue<Integer> l;
    private Queue<Long> m;
    private String n;
    private e o;
    private com.mbridge.msdk.foundation.same.report.c p;
    private String q;
    private com.mbridge.msdk.click.b r;
    private int s;
    private int t;
    private int u;
    private int v;
    private String w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* compiled from: NativeController.java */
    /* renamed from: com.mbridge.msdk.mbnative.c.b$b, reason: collision with other inner class name */
    public interface InterfaceC0123b {
        void a();
    }

    /* compiled from: NativeController.java */
    private static class e extends Handler {
        private WeakReference<b> a;

        public e(b bVar) {
            this.a = new WeakReference<>(bVar);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            b bVar;
            super.handleMessage(message);
            try {
                if (message.what == 0 && this.a != null && (bVar = this.a.get()) != null) {
                    bVar.p.a(message.arg1, (String) message.obj);
                }
                if (message.what == 1) {
                    b bVar2 = null;
                    if (this.a != null && this.a.get() != null) {
                        bVar2 = this.a.get();
                    }
                    if (bVar2 != null) {
                        bVar2.z = true;
                        s.b(b.e, "time out return");
                        List<Campaign> listB = bVar2.b(bVar2.j, bVar2.t, bVar2.U);
                        if (bVar2.y) {
                            return;
                        }
                        s.b(b.e, "time out return isReturn = " + bVar2.y);
                        bVar2.a(listB);
                    }
                }
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }
    }

    public b(com.mbridge.msdk.mbnative.d.a aVar, NativeListener.NativeTrackingListener nativeTrackingListener, Map<String, Object> map, Context context) {
        com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA;
        this.s = 1;
        this.t = 1;
        this.u = -1;
        this.v = 0;
        this.y = false;
        this.z = false;
        this.A = false;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.H = new HashMap();
        this.K = new CopyOnWriteArrayList<>();
        this.L = 1;
        this.M = "";
        this.O = 2;
        this.S = "";
        this.U = "";
        this.i = context;
        this.d = map;
        this.f = new com.mbridge.msdk.b.c();
        this.g = aVar;
        this.h = nativeTrackingListener;
        this.I = new ArrayList();
        this.J = new ArrayList();
        this.j = (String) map.get(MBridgeConstans.PROPERTIES_UNIT_ID);
        if (TextUtils.isEmpty(this.j)) {
            s.c(e, "load error,make sure you have correct unitid");
            return;
        }
        if (map.containsKey(MBridgeConstans.PLACEMENT_ID) && map.get(MBridgeConstans.PLACEMENT_ID) != null) {
            this.k = (String) map.get(MBridgeConstans.PLACEMENT_ID);
        } else {
            this.k = "";
        }
        if (map.containsKey(MBridgeConstans.PREIMAGE)) {
            B = ((Boolean) map.get(MBridgeConstans.PREIMAGE)).booleanValue();
        }
        this.l = new LinkedList();
        this.m = new LinkedList();
        this.G = new com.mbridge.msdk.foundation.same.e.b(this.i);
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        this.o = new e(this);
        if (map.containsKey(MBridgeConstans.PROPERTIES_API_REUQEST_CATEGORY)) {
            this.w = (String) map.get(MBridgeConstans.PROPERTIES_API_REUQEST_CATEGORY);
        }
        try {
            if (!(com.mbridge.msdk.mbnative.c.c.b().containsKey(this.j) ? com.mbridge.msdk.mbnative.c.c.b().get(this.j).booleanValue() : false)) {
                if (map.containsKey("ad_num")) {
                    int iIntValue = ((Integer) map.get("ad_num")).intValue();
                    iIntValue = iIntValue < 1 ? 1 : iIntValue;
                    iIntValue = iIntValue > 10 ? 10 : iIntValue;
                    this.t = iIntValue;
                    this.s = iIntValue;
                }
                if (map.containsKey(MBridgeConstans.PROPERTIES_AD_FRAME_NUM)) {
                    this.E = ((Integer) map.get(MBridgeConstans.PROPERTIES_AD_FRAME_NUM)).intValue();
                }
            } else if (com.mbridge.msdk.mbnative.c.c.d().containsKey(this.j)) {
                this.t = com.mbridge.msdk.mbnative.c.c.d().get(this.j).intValue();
                if (map.containsKey("ad_num")) {
                    int iIntValue2 = ((Integer) map.get("ad_num")).intValue();
                    this.C = iIntValue2;
                    this.s = iIntValue2;
                }
                if (map.containsKey(MBridgeConstans.PROPERTIES_AD_FRAME_NUM)) {
                    int iIntValue3 = ((Integer) map.get(MBridgeConstans.PROPERTIES_AD_FRAME_NUM)).intValue();
                    this.D = iIntValue3;
                    this.E = iIntValue3;
                }
            }
        } catch (Exception e2) {
            s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
        }
        this.p = new com.mbridge.msdk.foundation.same.report.c(this.i);
        this.r = new com.mbridge.msdk.click.b(this.i, this.j);
        try {
            Class.forName("com.mbridge.msdk.nativex.view.MBMediaView");
            Class.forName("com.mbridge.msdk.videocommon.download.b");
            this.R = true;
            if (this.d != null && (this.d.containsKey(MBridgeConstans.NATIVE_VIDEO_WIDTH) || this.d.containsKey(MBridgeConstans.NATIVE_VIDEO_HEIGHT) || map.containsKey(MBridgeConstans.NATIVE_VIDEO_SUPPORT))) {
                this.Q = true;
            }
            com.mbridge.msdk.mbnative.c.d.a(this.i, this.j);
            q.b();
            if (TextUtils.isEmpty(this.j)) {
                return;
            }
            com.mbridge.msdk.foundation.db.f.a(i.a(this.i)).a();
            int iA = a(map.containsKey(MBridgeConstans.NATIVE_INFO) ? (String) map.get(MBridgeConstans.NATIVE_INFO) : null);
            String str = this.j;
            iA = iA <= 0 ? this.s : iA;
            this.V = com.mbridge.msdk.b.b.a().f("", str);
            if (this.V == null) {
                this.V = com.mbridge.msdk.b.d.d(str);
            }
            this.a = this.V.t();
            List<Campaign> listB = (this.a == null || this.a.size() <= 0 || !this.a.contains(1) || (bVarA = com.mbridge.msdk.mbnative.a.f.a(1)) == null) ? null : bVarA.b(str, iA);
            if (listB != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < listB.size(); i++) {
                    CampaignEx campaignEx = (CampaignEx) listB.get(i);
                    if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                        arrayList.add(campaignEx);
                    }
                }
                if (arrayList.size() > 0) {
                    Class<?> cls = Class.forName("com.mbridge.msdk.videocommon.download.b");
                    Class<?> cls2 = Class.forName("com.mbridge.msdk.videocommon.listener.a");
                    Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                    cls.getMethod("createUnitCache", Context.class, String.class, List.class, Integer.TYPE, cls2).invoke(objInvoke, context, this.j, arrayList, 1, null);
                    cls.getMethod("load", String.class).invoke(objInvoke, this.j);
                }
            }
        } catch (Throwable unused) {
            s.d(e, "please import the nativex aar");
        }
    }

    public b() {
        this.s = 1;
        this.t = 1;
        this.u = -1;
        this.v = 0;
        this.y = false;
        this.z = false;
        this.A = false;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.H = new HashMap();
        this.K = new CopyOnWriteArrayList<>();
        this.L = 1;
        this.M = "";
        this.O = 2;
        this.S = "";
        this.U = "";
    }

    private int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
                    if (2 == jSONObject.optInt("id", 0)) {
                        return jSONObject.optInt("ad_num");
                    }
                }
            }
        } catch (Exception e2) {
            s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
        }
        return 0;
    }

    public final void a(int i, String str) throws JSONException {
        String str2;
        this.y = false;
        this.z = false;
        this.A = false;
        this.Z = false;
        this.x = false;
        this.U = str;
        this.g.a(!TextUtils.isEmpty(str));
        String str3 = null;
        this.P = null;
        if (c != null && c.size() > 0) {
            c.clear();
        }
        if (((com.mbridge.msdk.mbnative.c.c.b() == null || !com.mbridge.msdk.mbnative.c.c.b().containsKey(this.j)) ? false : com.mbridge.msdk.mbnative.c.c.b().get(this.j).booleanValue()) && i == 1 && a(i)) {
            return;
        }
        if (this.d.containsKey("app_id") && this.d.containsKey(MBridgeConstans.APP_KEY) && this.d.containsKey(MBridgeConstans.KEY_WORD)) {
            str3 = (String) this.d.get("app_id");
            str2 = (String) this.d.get(MBridgeConstans.APP_KEY);
        } else {
            str2 = null;
        }
        this.f.a(this.i, str3, str2, this.j);
        this.V = com.mbridge.msdk.b.b.a().f(str3, this.j);
        if (this.V == null) {
            this.V = com.mbridge.msdk.b.d.d(this.j);
        }
        com.mbridge.msdk.mbnative.c.c.e().put(this.j, Integer.valueOf(this.V.x() * this.t));
        this.a = this.V.t();
        this.b = this.V.u();
        this.Y = this.V.r();
        this.X = this.V.s();
        this.t = this.s;
        if (this.a == null || this.a.size() == 0) {
            com.mbridge.msdk.mbnative.d.a aVar = this.g;
            if (aVar != null) {
                this.y = true;
                aVar.onAdLoadError("don't have sorceList");
                return;
            }
            return;
        }
        if (this.d.containsKey(MBridgeConstans.NATIVE_INFO) && this.n == null) {
            this.n = (String) this.d.get(MBridgeConstans.NATIVE_INFO);
            try {
                if (this.n != null) {
                    JSONArray jSONArray = new JSONArray(this.n);
                    if (jSONArray.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            JSONObject jSONObject = (JSONObject) jSONArray.opt(i2);
                            int iOptInt = jSONObject.optInt("id", 0);
                            if (2 == iOptInt) {
                                this.aa = jSONObject.optInt("ad_num");
                                if (this.Y > 0) {
                                    jSONObject.remove("ad_num");
                                    jSONObject.put("ad_num", this.Y);
                                }
                            } else if (3 == iOptInt) {
                                this.ab = jSONObject.optInt("ad_num");
                                if (this.Y > 0) {
                                    jSONObject.remove("ad_num");
                                    jSONObject.put("ad_num", this.Y);
                                }
                            }
                        }
                    }
                    this.L = Math.max(this.aa, this.ab);
                    this.n = jSONArray.toString();
                }
            } catch (JSONException e2) {
                s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
            }
        }
        if ((!this.a.contains(1) || this.a.get(0).intValue() == 1) && i == 0 && a(b(this.j, this.t, this.U))) {
            return;
        }
        this.ac = true;
        if (this.a.contains(1) && i == 0 && this.a.get(0).intValue() != 1) {
            int iIntValue = this.a.get(0).intValue();
            com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA = com.mbridge.msdk.mbnative.a.f.a(iIntValue);
            if (iIntValue == 2 && this.d.containsKey(MBridgeConstans.NATIVE_INFO)) {
                this.t = this.L;
            } else {
                this.t = this.s;
            }
            if (bVarA != null && a(a(iIntValue, d(bVarA.b(this.j, this.t))))) {
                return;
            }
            this.x = false;
            this.ac = false;
            try {
                a(this.b.get(this.a.indexOf(1)).intValue() * 1000, i, false, this.j, this.U);
            } catch (Exception unused) {
            }
        }
        this.o.sendEmptyMessageDelayed(1, this.V.o() * 1000);
        if (this.a != null && this.a.size() > 0) {
            if (this.l != null && this.l.size() > 0) {
                s.b(e, "setRequestQueue clear requestqueue");
                this.l.clear();
            }
            for (Integer num : this.a) {
                if (this.l != null) {
                    this.l.add(num);
                }
            }
        }
        if (this.b != null && this.b.size() > 0) {
            if (this.m != null && this.m.size() > 0) {
                this.m.clear();
            }
            for (Integer num2 : this.b) {
                if (this.m != null) {
                    this.m.add(Long.valueOf(num2.intValue() * 1000));
                }
            }
        }
        b(i, this.U);
    }

    private boolean a(int i) {
        int iMin;
        int iOptInt;
        int iOptInt2;
        com.mbridge.msdk.mbnative.d.a aVar;
        int i2 = 0;
        if (com.mbridge.msdk.mbnative.c.c.b().containsKey(this.j) && com.mbridge.msdk.mbnative.c.c.b().get(this.j).booleanValue()) {
            Map<String, Map<Long, Object>> mapA = com.mbridge.msdk.mbnative.c.c.a();
            Map<Long, Object> map = mapA.get(i + "_" + this.j);
            Integer num = com.mbridge.msdk.mbnative.c.c.d().get(this.j);
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (num != null) {
                this.t = num.intValue();
            }
            if (map != null && map.size() > 0) {
                Long next = map.keySet().iterator().next();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (aVarB == null) {
                    aVarB = com.mbridge.msdk.b.b.a().b();
                }
                if (jCurrentTimeMillis - next.longValue() >= aVarB.L() * 1000) {
                    mapA.remove(i + "_" + this.j);
                } else {
                    if (i == 1) {
                        List<Frame> list = (List) map.get(next);
                        if (list == null || list.size() <= 0 || (aVar = this.g) == null) {
                            return false;
                        }
                        if (this.E >= list.size()) {
                            mapA.remove(i + "_" + this.j);
                            aVar.onAdFramesLoaded(list);
                            return true;
                        }
                        if (this.E == 0) {
                            return false;
                        }
                        List<Frame> listSubList = list.subList(0, this.D);
                        aVar.onAdFramesLoaded(list);
                        list.removeAll(listSubList);
                        map.put(next, listSubList);
                        ArrayList arrayList = new ArrayList();
                        for (Frame frame : list) {
                            if (i2 >= this.E) {
                                arrayList.add(frame);
                            }
                            i2++;
                        }
                        map.put(next, arrayList);
                        mapA.put(i + "_" + this.j, map);
                        aVar.onAdFramesLoaded(listSubList);
                        return true;
                    }
                    List list2 = (List) map.get(next);
                    if (list2 != null && list2.size() > 0) {
                        List<Campaign> arrayList2 = new ArrayList<>();
                        if (((CampaignEx) list2.get(0)).getType() == 1) {
                            if (TextUtils.isEmpty(this.n)) {
                                iMin = Math.min(this.C, list2.size());
                            } else {
                                try {
                                    JSONArray jSONArray = new JSONArray(this.n);
                                    if (jSONArray.length() > 0) {
                                        iOptInt = 0;
                                        iOptInt2 = 0;
                                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                                            JSONObject jSONObject = (JSONObject) jSONArray.opt(i3);
                                            int iOptInt3 = jSONObject.optInt("id", 0);
                                            if (2 == iOptInt3) {
                                                iOptInt2 = jSONObject.optInt("ad_num");
                                            } else if (3 == iOptInt3) {
                                                iOptInt = jSONObject.optInt("ad_num");
                                            }
                                        }
                                    } else {
                                        iOptInt = 0;
                                        iOptInt2 = 0;
                                    }
                                    if (3 == ((CampaignEx) list2.get(0)).getTemplate()) {
                                        iMin = Math.min(iOptInt, list2.size());
                                    } else {
                                        iMin = Math.min(iOptInt2, list2.size());
                                    }
                                } catch (Exception unused) {
                                    s.d(e, "load from catch error in get nativeinfo adnum");
                                    iMin = 0;
                                }
                            }
                            if (iMin <= 0) {
                                return false;
                            }
                            Iterator it = list2.iterator();
                            while (it.hasNext() && i2 != iMin) {
                                CampaignEx campaignEx = (CampaignEx) it.next();
                                campaignEx.getTemplate();
                                arrayList2.add(campaignEx);
                                it.remove();
                                i2++;
                            }
                        } else {
                            int iMin2 = Math.min(this.C, list2.size());
                            if (iMin2 <= 0) {
                                return false;
                            }
                            Iterator it2 = list2.iterator();
                            while (it2.hasNext() && i2 != iMin2) {
                                CampaignEx campaignEx2 = (CampaignEx) it2.next();
                                campaignEx2.getTemplate();
                                arrayList2.add(campaignEx2);
                                it2.remove();
                                i2++;
                            }
                        }
                        a(arrayList2);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void b(int i, String str) {
        if (this.l != null && this.l.size() > 0) {
            int iIntValue = this.l.poll().intValue();
            this.W = MBridgeConstans.REQUEST_TIME_OUT;
            if (this.m != null && this.m.size() > 0) {
                this.W = this.m.poll().longValue();
            }
            s.b(e, "start queue adsource = " + iIntValue);
            b(iIntValue, this.W, i, str);
            return;
        }
        if (this.y || this.g == null) {
            return;
        }
        s.b(e, "no ad source return");
        this.y = true;
        this.g.onAdLoadError("no ad source");
    }

    private void b(int i, long j, int i2, String str) {
        com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA;
        if (i2 == 0 && (bVarA = com.mbridge.msdk.mbnative.a.f.a(i)) != null) {
            if ((i == 1 || i == 2) && this.d.containsKey(MBridgeConstans.NATIVE_INFO)) {
                this.t = this.L;
            } else {
                this.t = this.s;
            }
            if (a(a(i, d(bVarA.b(this.j, this.t))))) {
            }
        }
        this.x = false;
        switch (i) {
            case 1:
                a(j, i2, true, this.j, str);
                break;
            case 2:
                a(2, j, i2, str);
                break;
            default:
                a(i, j, i2, str);
                break;
        }
    }

    public final void a(String str, int i, String str2) {
        s.b(e, "request error msg = " + str);
        if ((this.l != null && this.l.size() <= 0) || this.l == null) {
            if (this.g == null || this.y) {
                return;
            }
            this.y = true;
            this.g.onAdLoadError(str);
            s.b(e, "requestError return listener isReturn = " + this.y);
            return;
        }
        s.b(e, "request queue in request error");
        b(i, str2);
    }

    public final synchronized void a(int i, long j, int i2, String str) {
        n nVar;
        try {
            if (this.d.containsKey(MBridgeConstans.NATIVE_INFO)) {
                this.t = Math.max(this.aa, this.ab);
            }
            if (i2 == 0) {
                com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA = com.mbridge.msdk.mbnative.a.f.a(i);
                if (this.x) {
                    if (bVarA != null && a(a(1, d(bVarA.b(this.j, this.t))))) {
                        return;
                    }
                    s.b(e, "isGetApiReulst = " + this.Z + " isReturn = " + this.y);
                    if (this.P != null && !this.Z) {
                        this.P.b(true);
                    }
                    if (this.Z && !this.y) {
                        s.b(e, "222222");
                        a("mb load failed", i2, str);
                    }
                    if (!this.ac) {
                        return;
                    }
                } else if (i != 1 && bVarA != null && a(a(i, d(bVarA.b(this.j, this.t))))) {
                    return;
                }
            }
            if (this.u == -1) {
                this.u = i;
            } else if (this.u != i) {
                this.v = 0;
            }
            com.mbridge.msdk.foundation.db.f.a(i.a(this.i)).a();
            s.b(e, "START LOAD MB MVNATIVE");
            com.mbridge.msdk.mbnative.f.a.a aVar = new com.mbridge.msdk.mbnative.f.a.a(this.i);
            com.mbridge.msdk.foundation.same.net.h.d dVar = new com.mbridge.msdk.foundation.same.net.h.d();
            String strK = com.mbridge.msdk.foundation.controller.a.f().k();
            String strL = com.mbridge.msdk.foundation.controller.a.f().l();
            if (this.d != null && this.d.containsKey("app_id") && this.d.containsKey(MBridgeConstans.APP_KEY) && this.d.containsKey(MBridgeConstans.KEY_WORD) && this.d.get(MBridgeConstans.KEY_WORD) != null) {
                if (this.d.get("app_id") instanceof String) {
                    strK = (String) this.d.get("app_id");
                }
                if (this.d.get(MBridgeConstans.APP_KEY) instanceof String) {
                    strL = (String) this.d.get(MBridgeConstans.APP_KEY);
                }
                String str2 = this.d.get(MBridgeConstans.KEY_WORD) instanceof String ? (String) this.d.get(MBridgeConstans.KEY_WORD) : null;
                if (!TextUtils.isEmpty(str2)) {
                    dVar.a("smart", com.mbridge.msdk.foundation.tools.n.a(str2));
                }
            }
            dVar.a("app_id", strK);
            dVar.a(MBridgeConstans.PROPERTIES_UNIT_ID, this.j);
            if (!TextUtils.isEmpty(this.k)) {
                dVar.a(MBridgeConstans.PLACEMENT_ID, this.k);
            }
            dVar.a("req_type", "2");
            if (!TextUtils.isEmpty(this.w)) {
                dVar.a("category", this.w);
            }
            dVar.a("sign", SameMD5.getMD5(strK + strL));
            if (this.Y > 0 && i2 == 0) {
                dVar.a("ad_num", this.Y + "");
            } else {
                dVar.a("ad_num", this.s + "");
            }
            String strE = w.e(this.j);
            if (!TextUtils.isEmpty(strE)) {
                dVar.a("j", strE);
            }
            dVar.a("only_impression", "1");
            dVar.a("ping_mode", "1");
            if (this.E != 0) {
                dVar.a("frame_num", this.E + "");
            }
            if (!TextUtils.isEmpty(this.n)) {
                dVar.a(MBridgeConstans.NATIVE_INFO, this.n);
                if (i == 1 && !com.mbridge.msdk.click.b.a) {
                    dVar.a("tnum", this.L + "");
                }
            } else if (i == 1 && !com.mbridge.msdk.click.b.a) {
                dVar.a("tnum", this.s + "");
            }
            if (i == 1 && !TextUtils.isEmpty(this.M)) {
                dVar.a("ttc_ids", this.M);
            }
            String strA = com.mbridge.msdk.foundation.same.a.d.a(this.j, "native");
            if (!TextUtils.isEmpty(strA)) {
                dVar.a(com.mbridge.msdk.foundation.same.net.h.d.b, strA);
            }
            if (Build.VERSION.SDK_INT >= 14) {
                if (this.d.containsKey(MBridgeConstans.NATIVE_VIDEO_WIDTH) && (this.d.get(MBridgeConstans.NATIVE_VIDEO_WIDTH) instanceof Integer)) {
                    dVar.a("video_width", ((Integer) this.d.get(MBridgeConstans.NATIVE_VIDEO_WIDTH)).intValue() + "");
                }
                if (this.d.containsKey(MBridgeConstans.NATIVE_VIDEO_HEIGHT) && (this.d.get(MBridgeConstans.NATIVE_VIDEO_HEIGHT) instanceof Integer)) {
                    dVar.a("video_height", ((Integer) this.d.get(MBridgeConstans.NATIVE_VIDEO_HEIGHT)).intValue() + "");
                }
                if (this.d.containsKey(MBridgeConstans.NATIVE_VIDEO_SUPPORT) && (this.d.get(MBridgeConstans.NATIVE_VIDEO_SUPPORT) instanceof Boolean)) {
                    ((Boolean) this.d.get(MBridgeConstans.NATIVE_VIDEO_SUPPORT)).booleanValue();
                }
                dVar.a("video_version", MBridgeConstans.NATIVE_VIDEO_VERSION);
            }
            if (com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k()) == null) {
                com.mbridge.msdk.b.b.a().b();
            }
            if (!a(this.d)) {
                s.b(e, "没有param 参数 要传fqci");
                JSONArray jSONArrayA = w.a(this.i, this.j);
                if (jSONArrayA.length() > 0) {
                    dVar.a(com.mbridge.msdk.foundation.same.net.h.d.c, w.a(jSONArrayA));
                }
            }
            JSONArray jSONArray = new JSONArray();
            List<Long> listI = com.mbridge.msdk.foundation.controller.a.f().i();
            if (listI != null && listI.size() > 0) {
                Iterator<Long> it = listI.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().longValue());
                }
            }
            if (jSONArray.length() > 0) {
                dVar.a("install_ids", w.a(jSONArray));
            }
            if (com.mbridge.msdk.mbnative.c.c.b().containsKey(this.j) && com.mbridge.msdk.mbnative.c.c.b().get(this.j).booleanValue() && com.mbridge.msdk.mbnative.c.c.c().get(this.j) != null && (nVar = com.mbridge.msdk.mbnative.c.c.c().get(this.j)) != null) {
                if (i == 1) {
                    this.v = nVar.b();
                } else if (i == 2) {
                    this.v = nVar.a();
                }
            }
            dVar.a("offset", this.v + "");
            dVar.a("ad_type", "42");
            dVar.a(CampaignEx.JSON_KEY_AD_SOURCE_ID, i + "");
            if (!TextUtils.isEmpty(this.q)) {
                dVar.a(com.mbridge.msdk.foundation.same.net.h.d.a, this.q);
            }
            a aVar2 = new a();
            aVar2.a(b(this.d));
            aVar2.d = this.j;
            aVar2.e = this.k;
            aVar2.f = 42;
            if (i != 1 || this.x) {
                aVar2.b(true);
            } else {
                this.P = aVar2;
                aVar2.b(false);
            }
            c cVar = new c(1, aVar2, i2, str);
            aVar2.a(cVar);
            aVar2.g(i2);
            aVar2.a(str);
            if (i2 == 0) {
                if (!TextUtils.isEmpty(str)) {
                    dVar.a(BidResponsed.KEY_TOKEN, str);
                }
                if (com.mbridge.msdk.foundation.same.net.g.d.c().b() && TextUtils.isEmpty(str)) {
                    aVar.b(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVar, aVar2);
                } else {
                    aVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(str), dVar, aVar2);
                }
            }
            if (i2 == 1) {
                aVar.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().g, dVar, aVar2);
            }
            this.o.postDelayed(cVar, j);
        } catch (Exception e2) {
            s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
            s.d(e, e2.getMessage());
        }
    }

    public final void a(Campaign campaign, View view, List<View> list) {
        try {
            if (campaign.getType() == 3) {
                return;
            }
            if (this.h != null) {
                this.r.a(this.h);
            }
            com.mbridge.msdk.mbnative.a.f.a(campaign.getType()).a(this.j, campaign, this.U);
            final CampaignEx campaignEx = (CampaignEx) campaign;
            Class<?> cls = null;
            try {
                cls = Class.forName("com.mbridge.msdk.nativex.view.MBMediaView");
            } catch (Throwable unused) {
                s.d("", "MBMediaView can't found");
            }
            com.mbridge.msdk.foundation.same.a.d.b(this.j, campaignEx, "native");
            if (campaignEx != null) {
                if (view != null) {
                    if (cls != null && cls.isInstance(view)) {
                        return;
                    } else {
                        view.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.mbnative.c.b.1
                            @Override // com.mbridge.msdk.widget.a
                            protected final void a(View view2) {
                                b.a(b.this, view2.getContext(), campaignEx);
                            }
                        });
                    }
                }
                if (list != null && list.size() > 0) {
                    for (View view2 : list) {
                        if (cls != null && cls.isInstance(view2)) {
                            break;
                        } else {
                            view2.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.mbnative.c.b.8
                                @Override // com.mbridge.msdk.widget.a
                                protected final void a(View view3) {
                                    b.a(b.this, view3.getContext(), campaignEx);
                                }
                            });
                        }
                    }
                }
                if (campaignEx.isReport()) {
                    return;
                }
                a(campaignEx, view, list);
                Log.e(e, "sendImpression" + campaignEx);
            }
        } catch (Exception unused2) {
            s.d(e, "registerview exception!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CampaignEx campaignEx) {
        if (campaignEx.isReportClick()) {
            return;
        }
        campaignEx.setReportClick(true);
        if (campaignEx == null || campaignEx.getNativeVideoTracking() == null || campaignEx.getNativeVideoTracking().j() == null) {
            return;
        }
        com.mbridge.msdk.click.b.a(this.i, campaignEx, campaignEx.getCampaignUnitId(), campaignEx.getNativeVideoTracking().j(), false, false);
    }

    public final void a(Campaign campaign, View view) {
        try {
            if (campaign.getType() == 3) {
                return;
            }
            if (this.h != null) {
                this.r.a(this.h);
            }
            com.mbridge.msdk.mbnative.a.f.a(campaign.getType()).a(this.j, campaign, this.U);
            final CampaignEx campaignEx = (CampaignEx) campaign;
            com.mbridge.msdk.foundation.same.a.d.b(this.j, campaignEx, "native");
            if (campaignEx != null) {
                com.mbridge.msdk.widget.a aVar = new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.mbnative.c.b.10
                    @Override // com.mbridge.msdk.widget.a
                    protected final void a(View view2) {
                        b.a(b.this, view2.getContext(), campaignEx);
                    }
                };
                try {
                    a(view, aVar, Class.forName("com.mbridge.msdk.nativex.view.MBMediaView"));
                } catch (Throwable unused) {
                    a(view, aVar, (Class) null);
                }
                if (campaignEx.isReport()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(view);
                a(campaignEx, view, (List<View>) arrayList);
                s.b(e, "sendImpression" + campaignEx);
            }
        } catch (Exception unused2) {
            s.d(e, "registerview exception!");
        }
    }

    private void a(CampaignEx campaignEx, View view, List<View> list) {
        try {
            int iD = this.V != null ? this.V.d() : 0;
            final f fVar = new f(campaignEx, view, list, this);
            if (this.K == null) {
                this.K = new CopyOnWriteArrayList<>();
            }
            this.K.add(fVar);
            fVar.setonStateChangeListener(new a.b() { // from class: com.mbridge.msdk.mbnative.c.b.11
                @Override // com.mbridge.msdk.foundation.same.e.a.b
                public final void a(a.EnumC0111a enumC0111a) {
                    if (enumC0111a != a.EnumC0111a.FINISH || b.this.K == null || b.this.K.size() <= 0 || !b.this.K.contains(fVar)) {
                        return;
                    }
                    b.this.K.remove(fVar);
                }
            });
            if (this.o != null) {
                this.o.postDelayed(fVar, iD * 1000);
            }
        } catch (Exception e2) {
            s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
        }
    }

    private void a(View view, View.OnClickListener onClickListener, Class cls) {
        try {
            if (view == null || onClickListener == null) {
                s.d("", "traverseView  subview or mOnClickListener is null");
                return;
            }
            if (cls == null || !cls.isInstance(view)) {
                view.setOnClickListener(onClickListener);
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        a(viewGroup.getChildAt(i), onClickListener, cls);
                    }
                }
            }
        } catch (Throwable unused) {
            s.d("", "traverseView  failed");
        }
    }

    /* compiled from: NativeController.java */
    private static class f extends com.mbridge.msdk.foundation.same.e.a {
        private CampaignEx a;
        private WeakReference<View> b;
        private WeakReference<List<View>> c;
        private WeakReference<b> d;

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void cancelTask() {
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void pauseTask(boolean z) {
        }

        public f(CampaignEx campaignEx, View view, List<View> list, b bVar) {
            this.a = campaignEx;
            this.b = new WeakReference<>(view);
            this.c = new WeakReference<>(list);
            this.d = new WeakReference<>(bVar);
        }

        @Override // com.mbridge.msdk.foundation.same.e.a
        public final void runTask() {
            s.d(b.e, "waitSomeTimeToReport run");
            try {
                if (this.d == null || this.b == null || this.c == null) {
                    return;
                }
                View view = this.b.get();
                List<View> list = this.c.get();
                b bVar = this.d.get();
                if (view == null || bVar == null) {
                    return;
                }
                b.a(bVar, this.a, view, list);
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }
    }

    public final void b(Campaign campaign, View view) {
        b(campaign, view, (List<View>) null);
    }

    public final void b(Campaign campaign, View view, List<View> list) {
        if (campaign != null) {
            switch (campaign.getType()) {
                case 1:
                case 2:
                    Class<?> cls = null;
                    try {
                        cls = Class.forName("com.mbridge.msdk.nativex.view.MBMediaView");
                    } catch (Throwable unused) {
                    }
                    if (list != null && list.size() > 0) {
                        Iterator<View> it = list.iterator();
                        while (it.hasNext()) {
                            a(it.next(), cls);
                        }
                        break;
                    } else if (view != null) {
                        a(view, cls);
                        break;
                    }
                    break;
            }
        }
    }

    private void a(View view, Class cls) {
        try {
            if (view == null) {
                s.d("", "traverseView  subview  is null");
                return;
            }
            if (cls == null || !cls.isInstance(view)) {
                view.setOnClickListener(null);
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        a(viewGroup.getChildAt(i), cls);
                    }
                }
            }
        } catch (Throwable unused) {
            s.d("", "traverseView  failed");
        }
    }

    public final void a() {
        com.mbridge.msdk.foundation.same.e.a next;
        if (this.G != null) {
            this.G.a();
            this.G = null;
        }
        if (this.o != null) {
            this.o.removeCallbacksAndMessages(null);
        }
        this.h = null;
        this.r.a();
        try {
            if (this.i != null) {
                com.mbridge.msdk.foundation.same.c.b.a(this.i).c();
            }
            if (this.I != null && this.I.size() > 0) {
                Iterator<com.mbridge.msdk.mbnative.c.a> it = this.I.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
                this.I.clear();
                this.I = null;
            }
            if (this.J != null && this.J.size() > 0) {
                for (a.InterfaceC0122a interfaceC0122a : this.J) {
                }
                this.J.clear();
                this.J = null;
            }
            if (this.K == null || this.K.size() <= 0) {
                return;
            }
            Iterator<com.mbridge.msdk.foundation.same.e.a> it2 = this.K.iterator();
            if (it2.hasNext() && (next = it2.next()) != null) {
                next.cancel();
                this.o.removeCallbacks(next);
            }
            this.K.clear();
        } catch (Exception unused) {
        }
    }

    /* compiled from: NativeController.java */
    public class c implements Runnable {
        private int b;
        private com.mbridge.msdk.foundation.same.e.c c;
        private int d;
        private String e;

        public c(int i, com.mbridge.msdk.foundation.same.e.c cVar, int i2, String str) {
            this.b = i;
            this.c = cVar;
            this.d = i2;
            this.e = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            s.b(b.e, "cancel task adsource is = " + this.b);
            this.c.a(true);
            switch (this.b) {
                case 1:
                    b.this.Z = true;
                    if (b.this.x || this.d == 1) {
                        b.this.a("REQUEST_TIMEOUT", this.d, this.e);
                        break;
                    }
                    break;
                case 2:
                    if (!b.this.y || this.d == 1) {
                        b.this.a("REQUEST_TIMEOUT", this.d, this.e);
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: NativeController.java */
    public class a extends com.mbridge.msdk.mbnative.f.a.b implements com.mbridge.msdk.foundation.same.e.c {
        private Runnable c;
        private boolean b = false;
        private boolean g = true;
        private List<String> h = null;

        public a() {
        }

        public final void b(boolean z) {
            this.g = z;
        }

        @Override // com.mbridge.msdk.foundation.same.e.c
        public final void a(boolean z) {
            this.b = z;
        }

        public final void a(List<String> list) {
            this.h = list;
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void a(List<com.mbridge.msdk.foundation.same.net.c.b> list, CampaignUnit campaignUnit) {
            b.this.Z = true;
            m mVarA = m.a(i.a(b.this.i));
            mVarA.b();
            if (this.c != null) {
                s.b(b.e, "REMOVE CANCEL TASK ON SUCCESS");
                b.this.o.removeCallbacks(this.c);
            }
            s.d(b.e, "onSuccess");
            w.a((List<CampaignEx>) campaignUnit.getAds());
            if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                b.this.q = campaignUnit.getSessionId();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                if (b.this.X <= 0) {
                    if (b.this.X == -3) {
                        b.this.X = campaignUnit.getAds().size();
                    } else {
                        b.this.X = b.this.t;
                    }
                    if (b.this.aa != 0 && campaignUnit.getTemplate() == 2) {
                        b.this.X = b.this.aa;
                    }
                    if (b.this.ab != 0 && campaignUnit.getTemplate() == 3) {
                        b.this.X = b.this.ab;
                    }
                }
                boolean z = false;
                for (int i = 0; i < campaignUnit.getAds().size(); i++) {
                    CampaignEx campaignEx = campaignUnit.getAds().get(i);
                    campaignEx.setCampaignUnitId(this.d);
                    if (!TextUtils.isEmpty(b.this.U)) {
                        campaignEx.setBidToken(b.this.U);
                        campaignEx.setIsBidCampaign(true);
                    }
                    if (b.B) {
                        campaignEx.loadIconUrlAsyncWithBlock(null);
                        campaignEx.loadImageUrlAsyncWithBlock(null);
                    }
                    if (campaignEx != null) {
                        boolean zC = w.c(b.this.i, campaignEx.getPackageName());
                        if (zC && com.mbridge.msdk.foundation.controller.a.e() != null) {
                            com.mbridge.msdk.foundation.controller.a.e().add(new j(campaignEx.getId(), campaignEx.getPackageName()));
                            z = true;
                        }
                        if (i < b.this.t && campaignEx.getOfferType() != 99) {
                            if (w.b(campaignEx)) {
                                campaignEx.setRtinsType(zC ? 1 : 2);
                            }
                            if (campaignEx.getWtick() == 1 || !zC) {
                                arrayList.add(campaignEx);
                                if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                                    arrayList3.add(campaignEx);
                                }
                            } else if (w.b(campaignEx)) {
                                arrayList.add(campaignEx);
                                if (!TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                                    arrayList3.add(campaignEx);
                                }
                            } else {
                                w.a(this.d, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                            }
                        }
                        if (i < b.this.X && campaignEx.getOfferType() != 99) {
                            if (w.b(campaignEx)) {
                                campaignEx.setRtinsType(zC ? 1 : 2);
                            }
                            if (!zC || w.b(campaignEx)) {
                                arrayList2.add(campaignEx);
                            }
                        }
                        if (!mVarA.a(campaignEx.getId())) {
                            com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                            iVar.a(campaignEx.getId());
                            iVar.a(campaignEx.getFca());
                            iVar.b(campaignEx.getFcb());
                            iVar.d(0);
                            iVar.c(0);
                            iVar.a(System.currentTimeMillis());
                            mVarA.a(iVar);
                        }
                    }
                }
                b.b(b.this, arrayList3);
                if (z) {
                    com.mbridge.msdk.foundation.controller.a.f().h();
                }
                int type = campaignUnit.getAds().get(0) != null ? campaignUnit.getAds().get(0).getType() : 1;
                com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA = com.mbridge.msdk.mbnative.a.f.a(type);
                if (bVarA != null) {
                    bVarA.a((com.mbridge.msdk.mbnative.a.b<String, List<Campaign>>) this.d, (String) arrayList2, b.this.U);
                }
                if (arrayList.size() == 0) {
                    if (b.this.x || type != 1) {
                        b.this.a("APP ALREADY INSTALLED", f(), e());
                        return;
                    }
                    return;
                }
                if (b.this.x || type != 1) {
                    b.this.a((List<Campaign>) b.this.a(type, (List<Campaign>) b.this.d(arrayList)));
                }
                if (!b.this.y && b.this.z && !b.this.x) {
                    b.this.a((List<Campaign>) b.this.a(type, (List<Campaign>) b.this.d(arrayList)));
                }
                if (com.mbridge.msdk.mbnative.c.c.b().containsKey(this.d) && com.mbridge.msdk.mbnative.c.c.b().get(this.d).booleanValue()) {
                    com.mbridge.msdk.mbnative.c.c.a(b.this.u, this.d);
                    return;
                }
                int iIntValue = com.mbridge.msdk.mbnative.c.c.e().containsKey(this.d) ? com.mbridge.msdk.mbnative.c.c.e().get(this.d).intValue() : 1;
                int i2 = b.this.t + b.this.v;
                if (i2 > iIntValue) {
                    i2 = 0;
                }
                b.this.v = i2;
                return;
            }
            this.d = "0_" + this.d;
            com.mbridge.msdk.mbnative.c.c.b(b.this.u, this.d);
            b.this.v = 0;
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void a(int i, String str) {
            b.this.Z = true;
            if (this.b) {
                if (b.this.y || !this.g) {
                    return;
                }
                s.b(b.e, "onFailed onnative fail");
                b.this.a(str, f(), e());
                return;
            }
            if (i == -1) {
                com.mbridge.msdk.mbnative.c.c.b(b.this.u, this.d);
                b.this.v = 0;
            }
            if (this.c != null) {
                s.b(b.e, "REMOVE CANCEL TASK ON onFailed");
                b.this.o.removeCallbacks(this.c);
            }
            if (b.this.y) {
                return;
            }
            if (f() == 1 || this.g) {
                b.this.a(str, f(), e());
            }
        }

        public final void a(Runnable runnable) {
            this.c = runnable;
        }

        @Override // com.mbridge.msdk.mbnative.f.a.b
        public final void b(List<Frame> list) {
            if (this.b) {
                return;
            }
            if (this.c != null) {
                s.b(b.e, "REMOVE CANCEL TASK ON onAdLoaded");
                b.this.o.removeCallbacks(this.c);
            }
            if (list == null || list.size() == 0) {
                if (b.this.g != null) {
                    b.this.y = true;
                    b.this.g.onAdLoadError("frame is empty");
                    return;
                }
                return;
            }
            Iterator<Frame> it = list.iterator();
            while (it.hasNext()) {
                List<CampaignEx> campaigns = it.next().getCampaigns();
                if (campaigns == null || campaigns.size() == 0) {
                    if (b.this.g != null) {
                        b.this.y = true;
                        b.this.g.onAdLoadError("ads in frame is empty");
                        return;
                    }
                    return;
                }
                for (CampaignEx campaignEx : campaigns) {
                    if (b.B) {
                        campaignEx.loadImageUrlAsyncWithBlock(null);
                        campaignEx.loadIconUrlAsyncWithBlock(null);
                    }
                }
            }
            if (b.this.g != null) {
                b.this.g.onAdFramesLoaded(list);
            }
        }
    }

    public final List<Campaign> b(String str, int i, String str2) {
        int i2;
        int i3;
        List<Campaign> listA = null;
        if (this.a != null) {
            ArrayList arrayList = new ArrayList(this.a);
            List<Campaign> listA2 = null;
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA = com.mbridge.msdk.mbnative.a.f.a(((Integer) arrayList.get(i4)).intValue());
                if (bVarA != null) {
                    if ((((Integer) arrayList.get(i4)).intValue() == 1 || ((Integer) arrayList.get(i4)).intValue() == 2) && this.d.containsKey(MBridgeConstans.NATIVE_INFO)) {
                        i3 = this.L;
                    } else {
                        i3 = this.s;
                    }
                    listA2 = a(((Integer) arrayList.get(i4)).intValue(), bVarA.b(str, i3));
                    if (listA2 != null) {
                        break;
                    }
                }
            }
            listA = listA2;
            if (listA == null) {
                for (int i5 = 0; i5 < arrayList.size(); i5++) {
                    com.mbridge.msdk.mbnative.a.b<String, List<Campaign>> bVarA2 = com.mbridge.msdk.mbnative.a.f.a(((Integer) arrayList.get(i5)).intValue());
                    if ((((Integer) arrayList.get(i5)).intValue() == 1 || ((Integer) arrayList.get(i5)).intValue() == 2) && this.d.containsKey(MBridgeConstans.NATIVE_INFO)) {
                        i2 = this.L;
                    } else {
                        i2 = this.s;
                    }
                    listA = a(((Integer) arrayList.get(i5)).intValue(), bVarA2.a((com.mbridge.msdk.mbnative.a.b<String, List<Campaign>>) str, i2));
                    if (listA != null) {
                        break;
                    }
                }
            }
        }
        return d(listA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(final List<Campaign> list) {
        if ((!TextUtils.isEmpty(this.U) && list != null && list.size() == 0) || list == null || list.size() <= 0) {
            return false;
        }
        final com.mbridge.msdk.mbnative.d.a aVar = this.g;
        if (aVar == null) {
            return true;
        }
        CampaignEx campaignEx = (CampaignEx) list.get(0);
        final int template = campaignEx != null ? campaignEx.getTemplate() : 2;
        m mVarA = m.a(i.a(this.i));
        mVarA.b();
        for (int i = 0; i < list.size(); i++) {
            CampaignEx campaignEx2 = (CampaignEx) list.get(i);
            if (!mVarA.a(campaignEx2.getId())) {
                com.mbridge.msdk.foundation.entity.i iVar = new com.mbridge.msdk.foundation.entity.i();
                iVar.a(campaignEx2.getId());
                iVar.a(campaignEx2.getFca());
                iVar.b(campaignEx2.getFcb());
                iVar.d(0);
                iVar.c(0);
                iVar.a(System.currentTimeMillis());
                mVarA.a(iVar);
            }
        }
        int type = campaignEx.getType();
        if (this.R && this.Q && type != 3 && type != 6 && type != 7) {
            if (e().i() == 3) {
                final List<Campaign> listB = b(list);
                if (list != null && list.size() > 0) {
                    a(list, new InterfaceC0123b() { // from class: com.mbridge.msdk.mbnative.c.b.13
                        @Override // com.mbridge.msdk.mbnative.c.b.InterfaceC0123b
                        public final void a() {
                            List listA = b.a(b.this, listB, true);
                            if (listA == null || listA.size() <= 0) {
                                b.this.a(aVar, "has no ads");
                            } else {
                                b.this.a((List<Campaign>) listA, template, aVar);
                            }
                        }
                    });
                    return true;
                }
                a(aVar, "has no ads");
                return true;
            }
            List<Campaign> listC = c(list);
            if (listC != null && listC.size() > 0) {
                a(listC, new InterfaceC0123b() { // from class: com.mbridge.msdk.mbnative.c.b.14
                    @Override // com.mbridge.msdk.mbnative.c.b.InterfaceC0123b
                    public final void a() {
                        List listA = b.a(b.this, list, false);
                        if (listA == null || listA.size() <= 0) {
                            b.this.a(aVar, "has no ads");
                        } else {
                            b.this.a((List<Campaign>) listA, template, aVar);
                        }
                    }
                });
                return true;
            }
            a(list, template, aVar);
            return true;
        }
        if (list != null && list.size() > 0) {
            Iterator<Campaign> it = list.iterator();
            while (it.hasNext()) {
                it.next().setVideoLength(0);
            }
            a(list, template, aVar);
            return true;
        }
        a(aVar, "has no ads");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<Campaign> list, final int i, final NativeListener.NativeAdListener nativeAdListener) {
        this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.b.15
            @Override // java.lang.Runnable
            public final void run() {
                if (list != null && list.size() > 0) {
                    CampaignEx campaignEx = (CampaignEx) list.get(0);
                    b.this.S = campaignEx.getRequestId();
                }
                b.this.y = true;
                nativeAdListener.onAdLoaded(list, i);
                com.mbridge.msdk.mbnative.e.a.a(b.this.i, list, b.this.j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final NativeListener.NativeAdListener nativeAdListener, final String str) {
        this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.b.2
            @Override // java.lang.Runnable
            public final void run() {
                nativeAdListener.onAdLoadError(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Campaign> a(int i, List<Campaign> list) {
        if (i != 1 || !this.d.containsKey(MBridgeConstans.NATIVE_INFO) || list == null || list.size() <= 0) {
            return list;
        }
        CampaignEx campaignEx = (CampaignEx) list.get(0);
        int template = campaignEx != null ? campaignEx.getTemplate() : 1;
        return template == 2 ? (list == null || list.size() < this.aa) ? list : list.subList(0, this.aa) : (template != 3 || list == null || list.size() < this.ab) ? list : list.subList(0, this.ab);
    }

    private void a(final long j, final int i, final boolean z, final String str, final String str2) {
        final UUID uuidC = o.C();
        if (uuidC == null) {
            this.H.put(str + "_" + z + "_ttc", false);
            this.H.put(str + "_" + z + "_post", false);
        } else {
            this.H.put(uuidC + str + "_" + z + "_ttc", false);
            this.H.put(uuidC + str + "_" + z + "_post", false);
        }
        final com.mbridge.msdk.foundation.same.e.a aVar = new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.mbnative.c.b.3
            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void cancelTask() {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void pauseTask(boolean z2) {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void runTask() {
                if (b.this.N == null) {
                    b.this.N = i.a(b.this.i);
                }
                com.mbridge.msdk.foundation.db.c cVarA = com.mbridge.msdk.foundation.db.c.a(b.this.N);
                cVarA.a();
                b.this.M = cVarA.a(str);
                if (b.this.H == null || b.this.H.isEmpty()) {
                    return;
                }
                if (uuidC == null) {
                    if (b.this.H.containsKey(str + "_" + z + "_ttc")) {
                        b.this.H.put(str + "_" + z + "_ttc", true);
                        return;
                    }
                }
                if (uuidC != null) {
                    if (b.this.H.containsKey(uuidC + str + "_" + z + "_ttc")) {
                        b.this.H.put(uuidC + str + "_" + z + "_ttc", true);
                    }
                }
            }
        };
        this.o.postDelayed(new com.mbridge.msdk.foundation.same.e.a() { // from class: com.mbridge.msdk.mbnative.c.b.4
            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void cancelTask() {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void pauseTask(boolean z2) {
            }

            @Override // com.mbridge.msdk.foundation.same.e.a
            public final void runTask() {
                boolean zBooleanValue = false;
                if (b.this.H != null && !b.this.H.isEmpty()) {
                    if (b.this.H.containsKey(str + "_" + z + "_ttc")) {
                        boolean zBooleanValue2 = ((Boolean) b.this.H.get(str + "_" + z + "_ttc")).booleanValue();
                        b.this.H.remove(str + "_" + z + "_ttc");
                        zBooleanValue = zBooleanValue2;
                    }
                    if (b.this.H.containsKey(uuidC + str + "_" + z + "_ttc")) {
                        zBooleanValue = ((Boolean) b.this.H.get(uuidC + str + "_" + z + "_ttc")).booleanValue();
                        b.this.H.remove(uuidC + str + "_" + z + "_ttc");
                    }
                }
                if (zBooleanValue) {
                    return;
                }
                b.this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.b.4.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (b.this.H != null && !b.this.H.isEmpty()) {
                            if (b.this.H.containsKey(str + "_" + z + "_post")) {
                                b.this.H.put(str + "_" + z + "_post", true);
                            }
                            if (b.this.H.containsKey(uuidC + str + "_" + z + "_post")) {
                                b.this.H.put(uuidC + str + "_" + z + "_post", true);
                            }
                        }
                        if (z) {
                            b.this.x = true;
                        } else {
                            b.this.x = false;
                        }
                        if (b.this.G != null) {
                            b.this.G.a(aVar);
                        }
                        b.this.a(1, j, i, str2);
                    }
                });
            }
        }, 90000L);
        a.b bVar = new a.b() { // from class: com.mbridge.msdk.mbnative.c.b.5
            @Override // com.mbridge.msdk.foundation.same.e.a.b
            public final void a(a.EnumC0111a enumC0111a) {
                if (enumC0111a == a.EnumC0111a.FINISH) {
                    b.this.o.post(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.b.5.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (z) {
                                b.this.x = true;
                            }
                            boolean zBooleanValue = false;
                            if (b.this.H != null && !b.this.H.isEmpty()) {
                                if (b.this.H.containsKey(str + "_" + z + "_post")) {
                                    zBooleanValue = ((Boolean) b.this.H.get(str + "_" + z + "_post")).booleanValue();
                                    b.this.H.remove(str + "_" + z + "_post");
                                }
                                if (b.this.H.containsKey(uuidC + str + "_" + z + "_post")) {
                                    zBooleanValue = ((Boolean) b.this.H.get(uuidC + str + "_" + z + "_post")).booleanValue();
                                    b.this.H.remove(uuidC + str + "_" + z + "_post");
                                }
                            }
                            if (zBooleanValue) {
                                return;
                            }
                            b.this.a(1, j, i, str2);
                        }
                    });
                }
            }
        };
        if (this.G == null) {
            this.G = new com.mbridge.msdk.foundation.same.e.b(this.i);
        }
        if (this.G != null) {
            this.G.a(aVar, bVar);
        }
    }

    public static boolean a(Map<String, Object> map) {
        if (map != null) {
            try {
                if (map.containsKey("app_id") && map.containsKey(MBridgeConstans.APP_KEY) && map.containsKey(MBridgeConstans.KEY_WORD) && map.get(MBridgeConstans.KEY_WORD) != null) {
                    s.b(e, "有smart 参数");
                    return true;
                }
            } catch (Exception e2) {
                s.d(e, com.mbridge.msdk.mbnative.b.a.a(e2));
            }
        }
        s.b(e, "木有smart 参数");
        return false;
    }

    public static List<String> b(Map<String, Object> map) {
        ArrayList arrayList = null;
        try {
            if (map.get(MBridgeConstans.KEY_WORD) instanceof String) {
                String str = (String) map.get(MBridgeConstans.KEY_WORD);
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() == 0) {
                    return null;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            String strOptString = jSONObjectOptJSONObject.optString(TtmlNode.TAG_P);
                            if (!TextUtils.isEmpty(strOptString)) {
                                arrayList2.add(strOptString);
                            }
                        }
                    } catch (Exception e2) {
                        arrayList = arrayList2;
                        e = e2;
                        s.d(e, com.mbridge.msdk.mbnative.b.a.a(e));
                        return arrayList;
                    }
                }
                return arrayList2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return arrayList;
    }

    /* compiled from: NativeController.java */
    private static class d implements H5DownLoadManager.IH5SourceDownloadListener {
        private String a;
        private CampaignEx b;
        private long c = System.currentTimeMillis();
        private boolean d;

        public d(String str, CampaignEx campaignEx, boolean z) {
            this.d = true;
            this.a = str;
            this.b = campaignEx;
            this.d = z;
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onSuccess(String str) {
            try {
                if (this.d) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
                    u.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                    com.mbridge.msdk.foundation.entity.q qVar = new com.mbridge.msdk.foundation.entity.q("2000043", 20, jCurrentTimeMillis + "", str, this.b.getId(), this.a, "", "2");
                    if (this.b != null && !TextUtils.isEmpty(this.b.getId())) {
                        qVar.n(this.b.getId());
                    }
                    if (this.b != null) {
                        qVar.k(this.b.getRequestId());
                        qVar.l(this.b.getRequestIdNotice());
                    }
                    qVar.h("1");
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a);
                }
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onFailed(String str, String str2) {
            try {
                if (this.d) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
                    u.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                    com.mbridge.msdk.foundation.entity.q qVar = new com.mbridge.msdk.foundation.entity.q("2000043", 21, jCurrentTimeMillis + "", str, this.b.getId(), this.a, str2, "2");
                    qVar.k(this.b.getRequestId());
                    qVar.l(this.b.getRequestIdNotice());
                    if (this.b != null && !TextUtils.isEmpty(this.b.getId())) {
                        qVar.n(this.b.getId());
                    }
                    qVar.h("1");
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a);
                }
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }
    }

    /* compiled from: NativeController.java */
    private static final class g implements H5DownLoadManager.ZipDownloadListener {
        String a;
        CampaignEx b;
        private long c = System.currentTimeMillis();
        private boolean d;

        public g(String str, CampaignEx campaignEx, boolean z) {
            this.d = true;
            this.a = str;
            this.b = campaignEx;
            this.d = z;
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onSuccess(String str) {
            try {
                if (this.d) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
                    u.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                    com.mbridge.msdk.foundation.entity.q qVar = new com.mbridge.msdk.foundation.entity.q("2000043", 14, jCurrentTimeMillis + "", str, this.b.getId(), this.a, "", "1");
                    if (this.b != null && !TextUtils.isEmpty(this.b.getId())) {
                        qVar.n(this.b.getId());
                    }
                    if (this.b != null) {
                        qVar.k(this.b.getRequestId());
                        qVar.l(this.b.getRequestIdNotice());
                    }
                    qVar.h("2");
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a);
                }
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onFailed(String str, String str2) {
            try {
                if (this.d) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
                    u.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                    com.mbridge.msdk.foundation.entity.q qVar = new com.mbridge.msdk.foundation.entity.q("2000043", 3, jCurrentTimeMillis + "", str2, this.b.getId(), this.a, str, "1");
                    if (this.b != null && !TextUtils.isEmpty(this.b.getId())) {
                        qVar.n(this.b.getId());
                    }
                    if (this.b != null) {
                        qVar.k(this.b.getRequestId());
                        qVar.l(this.b.getRequestIdNotice());
                    }
                    qVar.h("2");
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.a);
                }
            } catch (Exception e) {
                s.d(b.e, com.mbridge.msdk.mbnative.b.a.a(e));
            }
        }
    }

    public static void a(String str, CampaignEx campaignEx) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public com.mbridge.msdk.b.d e() {
        this.V = com.mbridge.msdk.b.b.a().f("", this.j);
        if (this.V == null) {
            this.V = com.mbridge.msdk.b.d.d(this.j);
        }
        return this.V;
    }

    private List<Campaign> b(List<Campaign> list) {
        if (list != null) {
            CampaignEx campaignEx = null;
            for (int size = list.size() - 1; size >= 0; size--) {
                try {
                    campaignEx = (CampaignEx) list.get(size);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (campaignEx != null && TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                    Campaign campaignRemove = list.remove(size);
                    com.mbridge.msdk.mbnative.a.f.a(campaignEx.getType()).a(this.j, campaignRemove, this.U);
                    s.a(e, "remove no videoURL ads:" + campaignRemove);
                }
            }
        }
        return list;
    }

    private List<Campaign> c(List<Campaign> list) {
        ArrayList arrayList = null;
        if (list != null) {
            for (Campaign campaign : list) {
                if (campaign instanceof CampaignEx) {
                    CampaignEx campaignEx = (CampaignEx) campaign;
                    if (TextUtils.isEmpty(campaignEx.getImageUrl()) && !TextUtils.isEmpty(campaignEx.getVideoUrlEncode())) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(campaign);
                    }
                }
            }
        }
        return arrayList;
    }

    private void a(final List<Campaign> list, final InterfaceC0123b interfaceC0123b) {
        f();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.T = new Timer();
        this.T.schedule(new TimerTask() { // from class: com.mbridge.msdk.mbnative.c.b.7
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                boolean z;
                s.a(b.e, "search campain status");
                if (System.currentTimeMillis() - jCurrentTimeMillis < 60000) {
                    int iC = o.C(b.this.i);
                    int iL = b.this.e().l();
                    if (iC != 9 && iL == 2) {
                        interfaceC0123b.a();
                        b.this.f();
                        return;
                    }
                    if (iL == 3) {
                        interfaceC0123b.a();
                        b.this.f();
                        return;
                    }
                    loop0: while (true) {
                        z = false;
                        for (Campaign campaign : list) {
                            String id = campaign.getId();
                            if (campaign instanceof CampaignEx) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(id);
                                CampaignEx campaignEx = (CampaignEx) campaign;
                                sb.append(campaignEx.getVideoUrlEncode());
                                sb.append(campaignEx.getBidToken());
                                id = sb.toString();
                            }
                            com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(b.this.j, id);
                            if (aVarA == null || !com.mbridge.msdk.videocommon.download.d.a(aVarA, b.this.e().g())) {
                                break;
                            } else {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        interfaceC0123b.a();
                        b.this.f();
                        return;
                    }
                    return;
                }
                interfaceC0123b.a();
                b.this.f();
            }
        }, 0L, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.T != null) {
            this.T.cancel();
            this.T = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Campaign> d(List<Campaign> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Campaign campaign : list) {
            if (campaign instanceof CampaignEx) {
                CampaignEx campaignEx = (CampaignEx) campaign;
                if (TextUtils.isEmpty(this.U) && TextUtils.isEmpty(campaignEx.getBidToken()) && !campaignEx.isBidCampaign()) {
                    arrayList2.add(campaignEx);
                } else if (!TextUtils.isEmpty(this.U) && TextUtils.equals(campaignEx.getBidToken(), this.U)) {
                    arrayList.add(campaign);
                }
            } else {
                arrayList2.add(campaign);
            }
        }
        return TextUtils.isEmpty(this.U) ? arrayList2 : arrayList;
    }

    public final String b() {
        return this.S;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0036 -> B:16:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.mbridge.msdk.mbnative.c.b r7, android.content.Context r8, final com.mbridge.msdk.foundation.entity.CampaignEx r9) {
        /*
            if (r9 == 0) goto L3f
            boolean r0 = r9.needShowIDialog()     // Catch: java.lang.Throwable -> L35
            if (r0 == 0) goto L3f
            com.mbridge.msdk.mbnative.c.b$9 r6 = new com.mbridge.msdk.mbnative.c.b$9     // Catch: java.lang.Throwable -> L35
            r6.<init>()     // Catch: java.lang.Throwable -> L35
            boolean r0 = com.mbridge.msdk.click.c.a(r9)     // Catch: java.lang.Throwable -> L35
            if (r0 == 0) goto L3f
            boolean r0 = r9.needShowIDialog()     // Catch: java.lang.Throwable -> L35
            if (r0 == 0) goto L3f
            com.mbridge.msdk.click.b r0 = r7.r     // Catch: java.lang.Throwable -> L35
            boolean r0 = r0.b(r9)     // Catch: java.lang.Throwable -> L35
            if (r0 != 0) goto L2f
            com.mbridge.msdk.mbjscommon.confirmation.e r1 = com.mbridge.msdk.mbjscommon.confirmation.e.a()     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = ""
            java.lang.String r5 = r7.j     // Catch: java.lang.Throwable -> L35
            r3 = r9
            r4 = r8
            r1.a(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L35
            goto L4e
        L2f:
            com.mbridge.msdk.mbnative.d.a r8 = r7.g     // Catch: java.lang.Throwable -> L35
            r8.onAdClick(r9)     // Catch: java.lang.Throwable -> L35
            goto L4e
        L35:
            r8 = move-exception
            java.lang.String r0 = com.mbridge.msdk.mbnative.c.b.e
            java.lang.String r8 = r8.getMessage()
            com.mbridge.msdk.foundation.tools.s.a(r0, r8)
        L3f:
            com.mbridge.msdk.click.b r8 = r7.r
            r8 = 0
            com.mbridge.msdk.click.b.a = r8
            com.mbridge.msdk.click.b r8 = r7.r
            com.mbridge.msdk.mbnative.d.a r0 = r7.g
            r8.a(r9, r0)
            r7.a(r9)
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbnative.c.b.a(com.mbridge.msdk.mbnative.c.b, android.content.Context, com.mbridge.msdk.foundation.entity.CampaignEx):void");
    }

    static /* synthetic */ void a(b bVar, final CampaignEx campaignEx, View view, List list) {
        try {
            s.d(e, "trackView start");
            a.InterfaceC0122a interfaceC0122a = new a.InterfaceC0122a() { // from class: com.mbridge.msdk.mbnative.c.b.12
                @Override // com.mbridge.msdk.mbnative.c.a.InterfaceC0122a
                public final void a(ArrayList<View> arrayList, ArrayList<View> arrayList2) {
                    s.d(b.e, "trackView onVisibilityChanged");
                    if (arrayList == null || arrayList.size() <= 0) {
                        return;
                    }
                    s.d(b.e, "trackView send");
                    com.mbridge.msdk.mbnative.e.b.a(campaignEx, b.this.i, b.this.j, b.this.g);
                }
            };
            com.mbridge.msdk.mbnative.c.a aVar = new com.mbridge.msdk.mbnative.c.a(list, interfaceC0122a, new Handler(Looper.getMainLooper()));
            aVar.a(view);
            if (bVar.I != null) {
                bVar.I.add(aVar);
            }
            if (bVar.J != null) {
                bVar.J.add(interfaceC0122a);
            }
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ void b(b bVar, List list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    s.b(e, "===创建下载任务");
                    try {
                        Class.forName("com.mbridge.msdk.nativex.view.MBMediaView");
                        Class.forName("com.mbridge.msdk.videocommon.download.b");
                        Class<?> cls = Class.forName("com.mbridge.msdk.videocommon.download.b");
                        Class<?> cls2 = Class.forName("com.mbridge.msdk.videocommon.listener.a");
                        Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                        if (objInvoke != null) {
                            cls.getMethod("createUnitCache", Context.class, String.class, List.class, Integer.TYPE, cls2).invoke(objInvoke, bVar.i, bVar.j, list, 1, null);
                            cls.getMethod("load", String.class).invoke(objInvoke, bVar.j);
                        }
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            CampaignEx campaignEx = (CampaignEx) it.next();
                            if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getImageUrl())) {
                                com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(campaignEx.getImageUrl(), new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.mbnative.c.b.6
                                    @Override // com.mbridge.msdk.foundation.same.c.c
                                    public final void onFailedLoad(String str, String str2) {
                                    }

                                    @Override // com.mbridge.msdk.foundation.same.c.c
                                    public final void onSuccessLoad(Bitmap bitmap, String str) {
                                    }
                                });
                            }
                            try {
                                String str = campaignEx.getendcard_url();
                                if (!TextUtils.isEmpty(str)) {
                                    if (str.contains(".zip") && str.contains(ResourceManager.KEY_MD5FILENAME)) {
                                        H5DownLoadManager.getInstance().downloadH5Res(str, new g(bVar.j, campaignEx, TextUtils.isEmpty(H5DownLoadManager.getInstance().getH5ResAddress(str))));
                                    } else {
                                        H5DownLoadManager.getInstance().downloadH5Res(str, new d(bVar.j, campaignEx, TextUtils.isEmpty(HTMLResourceManager.getInstance().getHtmlContentFromUrl(str))));
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                        return;
                    } catch (Throwable unused2) {
                        s.d(e, "please import the videocommon and nativex aar");
                        return;
                    }
                }
            } catch (Throwable th) {
                if (MBridgeConstans.DEBUG) {
                    th.printStackTrace();
                    return;
                }
                return;
            }
        }
        s.b(e, "onload 不用下载视频素材 size为0");
    }

    static /* synthetic */ List a(b bVar, List list, boolean z) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                Campaign campaign = (Campaign) list.get(size);
                String id = campaign.getId();
                boolean z2 = campaign instanceof CampaignEx;
                if (z2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(id);
                    CampaignEx campaignEx = (CampaignEx) campaign;
                    sb.append(campaignEx.getVideoUrlEncode());
                    sb.append(campaignEx.getBidToken());
                    id = sb.toString();
                }
                com.mbridge.msdk.videocommon.download.a aVarA = com.mbridge.msdk.videocommon.download.b.getInstance().a(bVar.j, id);
                if (z) {
                    if (aVarA == null || !com.mbridge.msdk.videocommon.download.d.a(aVarA, bVar.e().g())) {
                        com.mbridge.msdk.mbnative.a.f.a(campaign.getType()).a(bVar.j, (Campaign) list.remove(size), bVar.U);
                    }
                } else if (z2) {
                    CampaignEx campaignEx2 = (CampaignEx) campaign;
                    if (TextUtils.isEmpty(campaignEx2.getImageUrl()) && !TextUtils.isEmpty(campaignEx2.getVideoUrlEncode()) && (aVarA == null || !com.mbridge.msdk.videocommon.download.d.a(aVarA, bVar.e().g()))) {
                        com.mbridge.msdk.mbnative.a.f.a(campaign.getType()).a(bVar.j, (Campaign) list.remove(size), bVar.U);
                    }
                }
            }
        }
        return list;
    }
}
