package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.adapter.util.WeakHandler;
import com.bytedance.msdk.api.UserInfoForSegment;
import com.bytedance.msdk.api.v2.GMConfigUserInfoForSegment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements WeakHandler.IHandler, Runnable {
    private static volatile boolean h = false;
    private static volatile boolean i = false;
    private static final AtomicLong j = new AtomicLong(0);

    @SuppressLint({"StaticFieldLeak"})
    private static volatile b k;

    /* renamed from: a, reason: collision with root package name */
    private final bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a f1834a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f1835b;

    /* renamed from: c, reason: collision with root package name */
    private final ExecutorService f1836c = Executors.newFixedThreadPool(2);
    private final AtomicInteger d = new AtomicInteger(0);
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final AtomicBoolean f = new AtomicBoolean(false);
    private final WeakHandler g;

    class a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f1837a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int[] f1838b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ long f1839c;
        final /* synthetic */ boolean d;

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b$a$a, reason: collision with other inner class name */
        class RunnableC0063a implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b f1840a;

            RunnableC0063a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b bVar) {
                this.f1840a = bVar;
            }

            /* JADX WARN: Removed duplicated region for block: B:36:0x0119 A[Catch: all -> 0x01dc, TryCatch #3 {all -> 0x01dc, blocks: (B:6:0x000c, B:8:0x001d, B:10:0x0039, B:12:0x0061, B:29:0x00e0, B:31:0x00fc, B:33:0x0102, B:34:0x0109, B:36:0x0119, B:39:0x0123, B:41:0x0150, B:44:0x0173, B:46:0x017d, B:47:0x01b6, B:49:0x01bf, B:18:0x007b, B:20:0x0096, B:26:0x00cb, B:28:0x00d5, B:14:0x0071, B:23:0x00ac), top: B:69:0x000c, inners: #0, #2 }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0123 A[Catch: all -> 0x01dc, TryCatch #3 {all -> 0x01dc, blocks: (B:6:0x000c, B:8:0x001d, B:10:0x0039, B:12:0x0061, B:29:0x00e0, B:31:0x00fc, B:33:0x0102, B:34:0x0109, B:36:0x0119, B:39:0x0123, B:41:0x0150, B:44:0x0173, B:46:0x017d, B:47:0x01b6, B:49:0x01bf, B:18:0x007b, B:20:0x0096, B:26:0x00cb, B:28:0x00d5, B:14:0x0071, B:23:0x00ac), top: B:69:0x000c, inners: #0, #2 }] */
            /* JADX WARN: Removed duplicated region for block: B:43:0x0171  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 544
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a.RunnableC0063a.run():void");
            }
        }

        a(int[] iArr, int[] iArr2, long j, boolean z) {
            this.f1837a = iArr;
            this.f1838b = iArr2;
            this.f1839c = j;
            this.d = z;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b bVar2) {
            ThreadHelper.runOnMSDKThread(new RunnableC0063a(bVar2));
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.b bVar, IOException iOException) {
            Logger.e("SdkSettingsHelper", "setting request failed..." + iOException.getMessage());
            try {
                b.this.k();
                Logger.e("SdkSettingsHelper", "request failed...>>>>> retryLoadSettingData");
            } catch (Throwable unused) {
            }
            b.this.e.set(false);
            this.f1838b[0] = 0;
            this.f1837a[0] = -2;
            if (b.i) {
                return;
            }
            boolean unused2 = b.i = true;
            g.a(this.f1838b[0], this.f1837a[0], System.currentTimeMillis() - this.f1839c, this.d);
        }
    }

    private b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a aVar) {
        this.f1834a = aVar == null ? bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f() : aVar;
        this.f1835b = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        this.g = new WeakHandler(Looper.getMainLooper(), this);
    }

    public static b a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a aVar) {
        if (k == null) {
            synchronized (b.class) {
                try {
                    if (k == null) {
                        k = new b(aVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return k;
    }

    private String a(JSONObject jSONObject) {
        JSONObject jSONObjectA = e0.a(jSONObject);
        if (jSONObjectA != null) {
            return jSONObjectA.toString();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        HashMap map2 = new HashMap();
        for (String str : map.keySet()) {
            if (!TextUtils.isEmpty(str)) {
                map2.put(str.toLowerCase(), map.get(str));
            }
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.optBoolean("remote_log_enabled")) {
                Logger.openDebugMode();
            }
        } catch (Throwable unused) {
        }
    }

    private void b(boolean z) {
        int[] iArr = {0};
        int[] iArr2 = new int[1];
        long jCurrentTimeMillis = System.currentTimeMillis();
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.c cVarB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b.b().a().b();
        cVarB.b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c.f());
        String strP = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().p();
        if (!TextUtils.isEmpty(strP)) {
            cVarB.a("X-Tt-Env", strP);
            cVarB.a("x-use-ppe", "1");
        }
        cVarB.a("User-Agent", com.bytedance.msdk.base.b.f4463b);
        cVarB.c(a(j()));
        cVarB.a(new a(iArr2, iArr, jCurrentTimeMillis, z));
    }

    private boolean f() {
        long jA = u.a("tt_sdk_settings", this.f1835b).a("max_expire_time", 0L);
        if (this.f.get() && System.currentTimeMillis() > jA) {
            Logger.i("SdkSettingsHelper", "setting 缓存过期，再次发起请求...");
            this.f.set(false);
            d();
        }
        return System.currentTimeMillis() > jA;
    }

    private static List<String> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("pangle");
        arrayList.add("gdt");
        arrayList.add("admob");
        arrayList.add("unity");
        arrayList.add("baidu");
        arrayList.add("sigmob");
        arrayList.add("mintegral");
        arrayList.add("ks");
        return arrayList;
    }

    private JSONObject h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Map<String, ITTAdapterConfiguration> mapE = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c.e();
        List<String> listG = g();
        if (mapE != null && listG != null) {
            for (String str : listG) {
                ITTAdapterConfiguration iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName(str));
                if (iTTAdapterConfiguration != null) {
                    try {
                        jSONObject.put(str, iTTAdapterConfiguration.getNetworkSdkVersion());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jSONObject;
    }

    private JSONObject i() throws JSONException {
        Map<String, String> customInfos;
        JSONObject jSONObject = new JSONObject();
        GMConfigUserInfoForSegment gMConfigUserInfoForSegmentF = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().f();
        if (gMConfigUserInfoForSegmentF != null) {
            Map<String, String> customInfos2 = gMConfigUserInfoForSegmentF.getCustomInfos();
            if (customInfos2 != null) {
                try {
                    for (String str : customInfos2.keySet()) {
                        jSONObject.put(str, customInfos2.get(str));
                    }
                    return jSONObject;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        UserInfoForSegment userInfoForSegmentT = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().t();
        if (userInfoForSegmentT != null && (customInfos = userInfoForSegmentT.getCustomInfos()) != null) {
            try {
                for (String str2 : customInfos.keySet()) {
                    jSONObject.put(str2, customInfos.get(str2));
                }
                return jSONObject;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0054, code lost:
    
        if (((((68 - 1) * 68) * ((68 * 2) - 1)) % 6) == 0) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007e, code lost:
    
        if ((((87 * 87) + (34 * 34)) + (16 * 16)) >= (((87 * 34) + (34 * 16)) + (87 * 16))) goto L117;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x002a. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject j() throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.j():org.json.JSONObject");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() throws Throwable {
        u.a("tt_sdk_settings", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).f("server_dist_host");
        this.e.set(false);
        if (this.f.get()) {
            this.g.removeMessages(1);
            return;
        }
        if (this.d.get() < 3) {
            this.d.incrementAndGet();
            c();
        } else {
            this.g.removeMessages(1);
            this.g.sendEmptyMessageDelayed(1, 600000L);
            Logger.i("SdkSettingsHelper", "setting 重试次数超过3次就间隔时间:600000毫秒后再执行....");
        }
    }

    public void a() {
        try {
            if (this.e.get()) {
                Logger.i("SdkSettingsHelper", "setting 正在加载，无需再次发起请求...");
            } else {
                this.f1836c.execute(this);
            }
        } catch (Throwable th) {
            Logger.d("SdkSettingsHelper", "load sdk settings error: ", th);
        }
    }

    public JSONObject b() throws JSONException {
        String userId;
        String userId2;
        JSONObject jSONObject = new JSONObject();
        GMConfigUserInfoForSegment gMConfigUserInfoForSegmentF = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().f();
        if (gMConfigUserInfoForSegmentF != null) {
            if (gMConfigUserInfoForSegmentF != null) {
                try {
                    userId2 = gMConfigUserInfoForSegmentF.getUserId();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                userId2 = "";
            }
            jSONObject.put("user_id", userId2);
            jSONObject.put("channel", gMConfigUserInfoForSegmentF != null ? gMConfigUserInfoForSegmentF.getChannel() : "");
            jSONObject.put("sub_channel", gMConfigUserInfoForSegmentF != null ? gMConfigUserInfoForSegmentF.getSubChannel() : "");
            jSONObject.put("age", gMConfigUserInfoForSegmentF != null ? gMConfigUserInfoForSegmentF.getAge() : 0);
            jSONObject.put("gender", gMConfigUserInfoForSegmentF != null ? gMConfigUserInfoForSegmentF.getGender() : "");
            jSONObject.put("user_value_group", gMConfigUserInfoForSegmentF != null ? gMConfigUserInfoForSegmentF.getUserValueGroup() : "");
            jSONObject.put("init_time", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().e());
            jSONObject.put("android_os_version_int", Build.VERSION.SDK_INT);
            return jSONObject;
        }
        UserInfoForSegment userInfoForSegmentT = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().t();
        if (userInfoForSegmentT != null) {
            try {
                userId = userInfoForSegmentT.getUserId();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            userId = "";
        }
        jSONObject.put("user_id", userId);
        jSONObject.put("channel", userInfoForSegmentT != null ? userInfoForSegmentT.getChannel() : "");
        jSONObject.put("sub_channel", userInfoForSegmentT != null ? userInfoForSegmentT.getSubChannel() : "");
        jSONObject.put("age", userInfoForSegmentT != null ? userInfoForSegmentT.getAge() : 0);
        jSONObject.put("gender", userInfoForSegmentT != null ? userInfoForSegmentT.getGender() : "");
        jSONObject.put("user_value_group", userInfoForSegmentT != null ? userInfoForSegmentT.getUserValueGroup() : "");
        jSONObject.put("init_time", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().e());
        jSONObject.put("android_os_version_int", Build.VERSION.SDK_INT);
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[Catch: all -> 0x0060, TryCatch #0 {all -> 0x0060, blocks: (B:11:0x003c, B:13:0x0042, B:15:0x004a, B:17:0x0052, B:19:0x005a), top: B:24:0x003c }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004a A[Catch: all -> 0x0060, TryCatch #0 {all -> 0x0060, blocks: (B:11:0x003c, B:13:0x0042, B:15:0x004a, B:17:0x0052, B:19:0x005a), top: B:24:0x003c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c() {
        /*
            r5 = this;
        L0:
            r0 = 1
            r1 = 52
            switch(r1) {
                case 52: goto L37;
                case 53: goto L7;
                case 54: goto L21;
                default: goto L6;
            }
        L6:
            goto L30
        L7:
            int r1 = r1 * r1
            r2 = 31
            int r3 = r2 * r2
            int r1 = r1 + r3
            r3 = 15
            int r4 = r3 * r3
            int r1 = r1 + r4
            r4 = 52
            int r4 = r4 * 31
            int r2 = r2 * 15
            int r4 = r4 + r2
            r2 = 52
            int r2 = r2 * 15
            int r4 = r4 + r2
            if (r1 >= r4) goto L37
        L21:
            r1 = 30
            int r2 = 30 - r0
            int r2 = r2 * 30
            int r1 = r1 * 2
            int r1 = r1 - r0
            int r2 = r2 * r1
            int r2 = r2 % 6
            if (r2 == 0) goto L0
        L30:
            r1 = 39
            int r1 = r1 + r0
            int r1 = r1 * 39
            int r1 = r1 % 2
        L37:
            android.content.Context r0 = r5.f1835b
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n.d(r0)
            boolean r0 = r5.f()     // Catch: java.lang.Throwable -> L60
            if (r0 != 0) goto L4a
            java.lang.String r0 = "SdkSettingsHelper"
            java.lang.String r1 = "setting 缓存未过期，无需再次发起请求..."
            com.bytedance.msdk.adapter.util.Logger.i(r0, r1)     // Catch: java.lang.Throwable -> L60
            return
        L4a:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.e     // Catch: java.lang.Throwable -> L60
            boolean r0 = r0.get()     // Catch: java.lang.Throwable -> L60
            if (r0 == 0) goto L5a
            java.lang.String r0 = "SdkSettingsHelper"
            java.lang.String r1 = "setting 正在加载，无需再次发起请求..."
            com.bytedance.msdk.adapter.util.Logger.i(r0, r1)     // Catch: java.lang.Throwable -> L60
            return
        L5a:
            java.util.concurrent.ExecutorService r0 = r5.f1836c     // Catch: java.lang.Throwable -> L60
            r0.execute(r5)     // Catch: java.lang.Throwable -> L60
            goto L68
        L60:
            r0 = move-exception
            java.lang.String r1 = "SdkSettingsHelper"
            java.lang.String r2 = "load sdk settings error: "
            com.bytedance.msdk.adapter.util.Logger.d(r1, r2, r0)
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.c():void");
    }

    public b d() {
        this.d.set(0);
        return this;
    }

    @Override // com.bytedance.msdk.adapter.util.WeakHandler.IHandler
    public void handleMsg(Message message) {
        int i2 = message.what;
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            g.a(((Boolean) message.obj).booleanValue());
        } else {
            this.d.set(0);
            try {
                if (this.f.get()) {
                    return;
                }
                c();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
    
        if (((((7 - 1) * 7) * ((7 * 2) - 1)) % 6) != 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
    
        if ((((17 + 1) * 17) % 2) == 0) goto L46;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0005. Please report as an issue. */
    /* JADX WARN: Path cross not found for [B:13:0x0021, B:46:?], limit reached: 44 */
    /* JADX WARN: Path cross not found for [B:46:?, B:13:0x0021], limit reached: 44 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0001 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x000f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:1: B:6:0x0009->B:47:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x001f -> B:6:0x0009). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0027 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0037 -> B:6:0x0009). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0056 -> B:11:0x0013). Please report as a decompilation issue!!! */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r8 = this;
            r0 = r8
        L1:
            r1 = 39
            r2 = 2
            r3 = 1
            switch(r1) {
                case 39: goto L9;
                case 40: goto L3a;
                case 41: goto L49;
                default: goto L8;
            }
        L8:
            goto L1
        L9:
            r4 = 31
            switch(r4) {
                case 29: goto L1;
                case 30: goto Lf;
                case 31: goto L59;
                default: goto Le;
            }
        Le:
            goto L9
        Lf:
            switch(r1) {
                case 57: goto L13;
                case 58: goto L21;
                case 59: goto L29;
                default: goto L12;
            }
        L12:
            goto L59
        L13:
            r4 = 7
            int r5 = 7 - r3
            int r5 = r5 * 7
            int r4 = r4 * 2
            int r4 = r4 - r3
            int r5 = r5 * r4
            int r5 = r5 % 6
            if (r5 == 0) goto L9
        L21:
            r4 = 17
            int r4 = r4 + r3
            int r4 = r4 * 17
            int r4 = r4 % r2
            if (r4 == 0) goto L9
        L29:
            r4 = 12
            int r5 = r4 * r4
            r6 = 37
            int r7 = r6 * r6
            int r5 = r5 + r7
            int r4 = r4 * 37
            int r4 = r4 * 2
            int r5 = r5 - r4
            if (r5 >= 0) goto L3a
            goto L9
        L3a:
            r4 = 29
            int r5 = 29 - r3
            int r5 = r5 * 29
            int r4 = r4 * 2
            int r4 = r4 - r3
            int r5 = r5 * r4
            int r5 = r5 % 6
            if (r5 == 0) goto L59
        L49:
            r4 = 38
            int r5 = 38 - r3
            int r5 = r5 * 38
            int r4 = r4 * 2
            int r4 = r4 - r3
            int r5 = r5 * r4
            int r5 = r5 % 6
            if (r5 == 0) goto L1
            goto L13
        L59:
            android.content.Context r1 = r0.f1835b
            boolean r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.q.a(r1)
            if (r1 != 0) goto L67
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.a r0 = r0.f1834a     // Catch: java.lang.Throwable -> L99
            r0.a()     // Catch: java.lang.Throwable -> L99
            goto L99
        L67:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()
            boolean r1 = r1.t()
            boolean r4 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.h
            if (r4 != 0) goto L88
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.h = r3
            android.os.Message r4 = android.os.Message.obtain()
            r4.what = r2
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r4.obj = r2
            com.bytedance.msdk.adapter.util.WeakHandler r2 = r0.g
            r5 = 3000(0xbb8, double:1.482E-320)
            r2.sendMessageDelayed(r4, r5)
        L88:
            java.util.concurrent.atomic.AtomicBoolean r2 = r0.e
            r2.set(r3)
            java.util.concurrent.atomic.AtomicLong r2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.j
            long r3 = android.os.SystemClock.currentThreadTimeMillis()
            r2.set(r3)
            r0.b(r1)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.run():void");
    }
}
