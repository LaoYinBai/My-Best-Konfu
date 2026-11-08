package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10;

import android.os.SystemClock;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.b.a.a.c.g;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomInitConfig;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a {

    /* renamed from: a, reason: collision with root package name */
    private String f1842a;

    /* renamed from: b, reason: collision with root package name */
    private long f1843b;

    /* renamed from: c, reason: collision with root package name */
    private long f1844c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private String j;
    private int k;
    private long q;
    private long r;
    private List<GMSettingConfigCallback> w;
    private Map<String, Boolean> x;
    private final AtomicBoolean y;
    private String l = null;
    private int m = 0;
    private String n = "log-api.pangolin-sdk-toutiao.com/service/2/app_log/";
    private int o = 100;
    private int p = 0;
    private long s = g.g;
    private Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> t = new ConcurrentHashMap();
    private Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> u = new ConcurrentHashMap();
    private Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> v = new HashMap();

    class a implements bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f1845a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f1846b;

        a(boolean z, boolean z2) {
            this.f1845a = z;
            this.f1846b = z2;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_int108.c
        public void a() {
            Logger.e("TTMediationSDK_SDK_Init", "MSDK init finish.........hasConfig:" + this.f1845a);
            if (this.f1845a) {
                if (!c.this.y.get()) {
                    int size = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().i().size();
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c.f();
                    if (d.d()) {
                        Logger.d("TMe", "-----==---- 正常上报sdk_init_end");
                        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(jElapsedRealtime, size, this.f1846b ? 1 : 0, -1L);
                    } else {
                        Logger.d("TMe", "-----==---- 延时上报sdk_init_end");
                        d.a(jElapsedRealtime, size, this.f1846b ? 1 : 0);
                    }
                    Logger.d("TTMediationSDK", "sdk init end, duration: " + jElapsedRealtime + ", initAdnCount: " + size + ", isFromLocalConfig: " + this.f1846b);
                }
                c.this.y.set(true);
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a.c();
                c.this.I();
            }
        }
    }

    public c() {
        new HashMap();
        this.w = new CopyOnWriteArrayList();
        this.x = new HashMap();
        this.y = new AtomicBoolean(false);
    }

    private void G() {
        this.t.clear();
        this.u.clear();
    }

    private boolean H() {
        Boolean bool = this.x.get("active_control");
        return bool == null || !bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (u()) {
            synchronized (this.w) {
                Iterator<GMSettingConfigCallback> it = this.w.iterator();
                while (it.hasNext()) {
                    it.next().configLoad();
                }
                this.w.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Long a(int r7) {
        /*
            r6 = this;
        L0:
            r0 = 92
            r1 = 14
            r2 = 4
            r3 = 20
            switch(r1) {
                case 13: goto L11;
                case 14: goto L65;
                case 15: goto Lb;
                default: goto La;
            }
        La:
            goto L0
        Lb:
            switch(r0) {
                case 94: goto L1f;
                case 95: goto L37;
                case 96: goto L4e;
                default: goto Le;
            }
        Le:
            r0 = 95
            goto Lb
        L11:
            r0 = 66
            int r0 = r0 * r0
            r1 = 16
            int r1 = r1 * r1
            int r1 = r1 * 34
            int r0 = r0 - r1
            r1 = -1
            if (r0 != r1) goto L37
        L1f:
            r0 = 29
            int r1 = r0 * r0
            r4 = 45
            int r5 = r4 * r4
            int r1 = r1 + r5
            int r5 = r3 * r3
            int r1 = r1 + r5
            r5 = 29
            int r5 = r5 * 45
            int r4 = r4 * 20
            int r5 = r5 + r4
            int r0 = r0 * 20
            int r5 = r5 + r0
            if (r1 >= r5) goto L4e
        L37:
            r0 = 70
            int r1 = r0 * r0
            r4 = 7
            int r5 = r4 * r4
            int r1 = r1 + r5
            int r5 = r2 * r2
            int r1 = r1 + r5
            r5 = 70
            int r5 = r5 * 7
            int r4 = r4 * 4
            int r5 = r5 + r4
            int r0 = r0 * 4
            int r5 = r5 + r0
            if (r1 >= r5) goto L0
        L4e:
            r0 = 69
            int r1 = r0 * r0
            int r4 = r3 * r3
            int r1 = r1 + r4
            int r4 = r2 * r2
            int r1 = r1 + r4
            r4 = 69
            int r4 = r4 * 20
            int r3 = r3 * 4
            int r4 = r4 + r3
            int r0 = r0 * 4
            int r4 = r4 + r0
            if (r1 >= r4) goto L65
            goto L74
        L65:
            r0 = 0
            switch(r7) {
                case 1: goto L74;
                case 2: goto L6f;
                case 3: goto L6f;
                case 4: goto L6a;
                case 5: goto L74;
                case 6: goto L6a;
                case 7: goto L6f;
                case 8: goto L6f;
                default: goto L6a;
            }
        L6a:
            java.lang.Long r7 = java.lang.Long.valueOf(r0)
            return r7
        L6f:
            java.lang.Long r7 = java.lang.Long.valueOf(r0)
            return r7
        L74:
            r0 = 1000(0x3e8, double:4.94E-321)
            java.lang.Long r7 = java.lang.Long.valueOf(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.a(int):java.lang.Long");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00eb, code lost:
    
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.c.a().a(null);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x006b. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x0084. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x005c A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00dc -> B:33:0x0097). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(org.json.JSONArray r9) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.a(org.json.JSONArray):java.lang.String");
    }

    private void a(boolean z) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d(), new a(t(), z));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x001c, code lost:
    
        r4 = r6;
        r6 = '_';
        r3 = '_';
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002d A[LOOP:0: B:2:0x0000->B:20:0x002d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0038 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0035 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x001c -> B:25:0x0038). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Long b(int r6) {
        /*
            r5 = this;
        L0:
            r0 = 95
            r1 = 10000(0x2710, double:4.9407E-320)
            switch(r6) {
                case 1: goto L12;
                case 2: goto L12;
                case 3: goto L10;
                case 4: goto L7;
                case 5: goto L12;
                case 6: goto L7;
                case 7: goto L8;
                case 8: goto L8;
                default: goto L7;
            }
        L7:
            goto L17
        L8:
            r0 = 600000(0x927c0, double:2.964394E-318)
        Lb:
            java.lang.Long r6 = java.lang.Long.valueOf(r0)
            return r6
        L10:
            r4 = r6
            goto L38
        L12:
            java.lang.Long r6 = java.lang.Long.valueOf(r1)
            return r6
        L17:
            r4 = r6
            r6 = 95
            r3 = 95
        L1c:
            switch(r6) {
                case 94: goto L2f;
                case 95: goto L20;
                case 96: goto L29;
                default: goto L1f;
            }
        L1f:
            goto L38
        L20:
            switch(r3) {
                case 94: goto L33;
                case 95: goto L24;
                case 96: goto L33;
                default: goto L23;
            }
        L23:
            goto L29
        L24:
            java.lang.Long r6 = java.lang.Long.valueOf(r1)
            return r6
        L29:
            switch(r3) {
                case 55: goto L2d;
                case 56: goto L38;
                case 57: goto L2d;
                default: goto L2c;
            }
        L2c:
            goto L35
        L2d:
            r6 = r4
            goto L0
        L2f:
            r6 = 39
            if (r3 != r6) goto L35
        L33:
            r6 = r4
            goto L17
        L35:
            r0 = 5000(0x1388, double:2.4703E-320)
            goto Lb
        L38:
            r6 = 94
            r3 = 125(0x7d, float:1.75E-43)
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.b(int):java.lang.Long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x03e9, code lost:
    
        if (((((19 - 1) * 19) * ((19 * 2) - 1)) % 6) == 0) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x03f2, code lost:
    
        r2 = ((41 * 41) + (15 * 15)) + (23 * 23);
        r6 = ((41 * 15) + (15 * 23)) + (41 * 23);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:63:0x0385. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:72:0x03b0. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x03b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:? A[LOOP:4: B:71:0x03ae->B:116:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0377 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x03b0 -> B:74:0x03b4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x03b0 -> B:76:0x03c1). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String b(org.json.JSONArray r18) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1064
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.b(org.json.JSONArray):java.lang.String");
    }

    private Map<String, String> c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap map = new HashMap();
        try {
            Iterator<String> itKeys = jSONObject.keys();
            if (itKeys == null) {
                return null;
            }
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String strOptString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(strOptString)) {
                    map.put(next, strOptString);
                }
            }
            if (map.size() == 0) {
                return null;
            }
            return map;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b1, code lost:
    
        if (((((99 * 99) * 99) + ((1 * 1) * 1)) + ((19 * 19) * 19)) < (((99 * 1) * 19) * 3)) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00bc, code lost:
    
        if (((75 * 75) - ((33 * 33) * 34)) != (-1)) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bf, code lost:
    
        r1 = '\'';
        r2 = 27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c5, code lost:
    
        switch(r1) {
            case 37: goto L30;
            case 38: goto L32;
            case 39: goto L28;
            default: goto L25;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c9, code lost:
    
        switch(r2) {
            case 25: goto L39;
            case 26: goto L41;
            case 27: goto L43;
            default: goto L35;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d4, code lost:
    
        if ((((62 + 1) * 62) % 2) == 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d8, code lost:
    
        if (r2 <= '=') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e6, code lost:
    
        if (((84 * 84) - ((26 * 26) * 34)) != (-1)) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ef, code lost:
    
        if ((((10 + 1) * 10) % 2) == 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f8, code lost:
    
        if (((34 * 34) - ((39 * 39) * 34)) != (-1)) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0103, code lost:
    
        if (((33 * 33) - ((8 * 8) * 34)) != (-1)) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0105, code lost:
    
        r9 = (((36 - 1) * 36) * ((36 * 2) - 1)) % 6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0114, code lost:
    
        switch(94) {
            case 94: goto L67;
            case 95: goto L68;
            case 96: goto L66;
            default: goto L71;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0123, code lost:
    
        if (((98 * 98) - ((30 * 30) * 34)) != (-1)) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x013c, code lost:
    
        if ((((82 * 82) + (15 * 15)) + (4 * 4)) >= (((82 * 15) + (15 * 4)) + (82 * 4))) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d6, code lost:
    
        switch(r1) {
            case 37: goto L30;
            case 38: goto L32;
            case 39: goto L28;
            default: goto L25;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:?, code lost:
    
        return null;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00bf -> B:26:0x00c5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c9 -> B:35:0x00db). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c9 -> B:39:0x00f1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c9 -> B:41:0x00fa). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00c9 -> B:43:0x0105). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00d4 -> B:32:0x00d6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0103 -> B:37:0x00e8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(org.json.JSONObject r18) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.d(org.json.JSONObject):java.lang.String");
    }

    private String e(JSONObject jSONObject) {
        this.x.clear();
        if (jSONObject == null) {
            return null;
        }
        try {
            if (jSONObject.optBoolean("ad_event_control")) {
                this.x.put("ad_event_control", true);
            }
            if (jSONObject.optBoolean("type_banner_control")) {
                this.x.put("type_banner_control", true);
            }
            if (jSONObject.optBoolean("type_interaction_control")) {
                this.x.put("type_interaction_control", true);
            }
            if (jSONObject.optBoolean("type_interactionfull_control")) {
                this.x.put("type_interactionfull_control", true);
            }
            if (jSONObject.optBoolean("type_splash_control")) {
                this.x.put("type_splash_control", true);
            }
            if (jSONObject.optBoolean("type_feed_control")) {
                this.x.put("type_feed_control", true);
            }
            if (jSONObject.optBoolean("type_reward_control")) {
                this.x.put("type_reward_control", true);
            }
            if (jSONObject.optBoolean("type_full_control")) {
                this.x.put("type_full_control", true);
            }
            if (jSONObject.optBoolean("type_native_control")) {
                this.x.put("type_native_control", true);
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private String f(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.k = jSONObject.optInt("fetch_ad_type", 0);
        return jSONObject.toString();
    }

    public boolean A() {
        return this.m == 1;
    }

    public boolean B() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_reward_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean C() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_splash_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean D() {
        return this.h == 1;
    }

    public boolean E() {
        return this.h == 2;
    }

    public boolean F() {
        return this.k == 1;
    }

    public bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a a(String str) {
        return this.t.get(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x003b. Please report as an issue. */
    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.a():void");
    }

    public void a(GMSettingConfigCallback gMSettingConfigCallback) {
        if (gMSettingConfigCallback == null) {
            return;
        }
        if (u()) {
            gMSettingConfigCallback.configLoad();
            return;
        }
        synchronized (this.w) {
            if (!this.w.contains(gMSettingConfigCallback)) {
                this.w.add(gMSettingConfigCallback);
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a
    public void a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optInt("state_code") != 30004) {
            return;
        }
        u uVarA = u.a("tt_sdk_settings", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
        this.f1843b = jSONObject.optLong("max_age");
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f1843b;
        this.f1844c = jCurrentTimeMillis + j;
        uVarA.b("max_age", j);
        uVarA.b("max_expire_time", this.f1844c);
    }

    public boolean a(String str, int i) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map = this.v;
        return map == null || map.size() == 0 || (cVar = this.v.get(str)) == null || cVar.n() == i;
    }

    public bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c b(String str) {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map = this.v;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public void b() {
        u.a("tt_sdk_settings", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a();
        this.f1842a = null;
    }

    public void b(GMSettingConfigCallback gMSettingConfigCallback) {
        if (gMSettingConfigCallback == null) {
            return;
        }
        synchronized (this.w) {
            if (this.w.contains(gMSettingConfigCallback)) {
                this.w.remove(gMSettingConfigCallback);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:126:0x0002, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0033 -> B:16:0x0036). Please report as a decompilation issue!!! */
    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(org.json.JSONObject r13) {
        /*
            Method dump skipped, instructions count: 708
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c.b(org.json.JSONObject):void");
    }

    public long c(String str) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVarB = b(str);
        return cVarB != null ? cVarB.b() : OnekeyLoginConfig.OVER_TIME;
    }

    public Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> c() {
        return this.t;
    }

    public Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> d() {
        return this.u;
    }

    public boolean d(String str) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar;
        Map<Integer, List<i>> mapT;
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map = this.v;
        if (map == null || map.size() == 0) {
            return true;
        }
        return (TextUtils.isEmpty(str) || (cVar = this.v.get(str)) == null || (mapT = cVar.t()) == null || mapT.size() == 0) ? false : true;
    }

    public Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> e() {
        return this.v;
    }

    public boolean e(String str) {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map;
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> map2 = this.t;
        return (map2 == null || map2.isEmpty() || (map = this.v) == null || map.isEmpty() || this.v.get(str) == null) ? false : true;
    }

    public String f() {
        return this.l;
    }

    public boolean f(String str) {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a> map;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVar;
        GMCustomInitConfig gMCustomInitConfigC;
        if (TextUtils.isEmpty(str) || (map = this.u) == null || (aVar = map.get(str)) == null || (gMCustomInitConfigC = aVar.c()) == null) {
            return false;
        }
        return gMCustomInitConfigC.isCustom();
    }

    public String g() {
        return this.n;
    }

    public void g(String str) {
        boolean z;
        String str2;
        try {
            u uVarA = u.a("tt_sdk_settings", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
            if (OnekeyLoginConstants.UNICOM_TYPE.equals(str)) {
                z = true;
                this.x.put("active_control", true);
                str2 = "all_active_control";
            } else {
                z = false;
                this.x.put("active_control", false);
                str2 = "all_active_control";
            }
            uVarA.b(str2, z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String h() {
        return this.f;
    }

    public String i() {
        if (this.f1842a == null) {
            this.f1842a = u.a("tt_sdk_settings", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).d("etag");
        }
        return this.f1842a;
    }

    public int j() {
        return this.o;
    }

    public int k() {
        return this.p;
    }

    public int l() {
        return this.h;
    }

    public long m() {
        return this.s;
    }

    public String n() {
        return this.g;
    }

    public boolean o() {
        try {
            return Integer.valueOf(this.j).intValue() > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean p() {
        return this.i == 1;
    }

    public boolean q() {
        try {
            return Integer.valueOf(this.j).intValue() > 1;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean r() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("ad_event_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean s() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_banner_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean t() {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map = this.v;
        return map != null && map.size() > 0;
    }

    public boolean u() {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c> map = this.v;
        return map != null && map.size() > 0 && this.y.get();
    }

    public boolean v() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_feed_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean w() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_full_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean x() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_interaction_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean y() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_interactionfull_control");
        return bool == null || !bool.booleanValue();
    }

    public boolean z() {
        if (!H()) {
            return false;
        }
        Boolean bool = this.x.get("type_native_control");
        return bool == null || !bool.booleanValue();
    }
}
