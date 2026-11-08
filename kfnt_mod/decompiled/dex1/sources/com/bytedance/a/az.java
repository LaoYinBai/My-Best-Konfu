package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.applog.InitConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class az {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3385a;

    /* renamed from: b, reason: collision with root package name */
    public final InitConfig f3386b;

    /* renamed from: c, reason: collision with root package name */
    public final SharedPreferences f3387c;
    public final SharedPreferences d;
    public final SharedPreferences e;
    public volatile JSONObject f;
    public volatile String g;
    public volatile JSONObject h;
    public volatile HashSet<String> k;
    public int l = 0;
    public int m = 27;
    public long n = 0;
    public int o = 0;
    public long p = 0;
    public int q = 1;
    public final HashSet<String> i = new HashSet<>();
    public final HashSet<String> j = new HashSet<>();

    public az(Context context, InitConfig initConfig) {
        this.f3385a = context;
        this.f3386b = initConfig;
        this.e = this.f3385a.getSharedPreferences(this.f3386b.getSpName(), 0);
        this.f3387c = this.f3385a.getSharedPreferences("header_custom", 0);
        this.d = this.f3385a.getSharedPreferences("last_sp_session", 0);
    }

    public String a() {
        String channel = this.f3386b.getChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = this.f3386b.getTweakedChannel();
        }
        if (!TextUtils.isEmpty(channel)) {
            return channel;
        }
        try {
            return this.f3385a.getPackageManager().getApplicationInfo(this.f3385a.getPackageName(), 128).metaData.getString("UMENG_CHANNEL");
        } catch (Throwable th) {
            de.a("getChannel", th);
            return channel;
        }
    }

    public ArrayList<bu> a(ArrayList<bu> arrayList) {
        Iterator<bu> it = arrayList.iterator();
        ArrayList<bu> arrayList2 = null;
        while (it.hasNext()) {
            bu next = it.next();
            String string = "!_NO_NAME_!";
            if (next instanceof cj) {
                cj cjVar = (cj) next;
                StringBuilder sb = new StringBuilder();
                sb.append(cjVar.n);
                sb.append(!TextUtils.isEmpty(cjVar.o) ? cjVar.o : "");
                string = sb.toString();
            } else if (next instanceof ct) {
                string = ((ct) next).o;
            }
            HashSet<String> hashSet = this.k;
            if (hashSet == null) {
                try {
                    JSONArray jSONArray = new JSONArray(this.e.getString("real_time_events", "[]"));
                    int length = jSONArray.length();
                    HashSet<String> hashSet2 = new HashSet<>();
                    for (int i = 0; i < length; i++) {
                        String string2 = jSONArray.getString(i);
                        if (!TextUtils.isEmpty(string2)) {
                            hashSet2.add(string2);
                        }
                    }
                    hashSet = hashSet2;
                } catch (Throwable th) {
                    de.a("U SHALL NOT PASS!", th);
                    hashSet = new HashSet<>();
                }
            }
            if (hashSet.contains(string)) {
                it.remove();
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public void a(String str) {
        de.a("setExternalAbVersion, " + str, null);
        a.a(this.f3387c, "external_ab_version", str);
        this.g = null;
    }

    public void a(JSONObject jSONObject) {
        String str;
        String string = jSONObject == null ? "" : jSONObject.toString();
        if (de.f3494c) {
            str = "setAbConfig, " + string;
        } else {
            str = "setAbConfig";
        }
        de.a(str, null);
        a.a(this.f3387c, "ab_configure", string);
        this.f = null;
    }

    public String b() {
        return this.f3387c.getString("ab_sdk_version", "");
    }

    public boolean b(ArrayList<bu> arrayList) {
        if (arrayList == null || arrayList.size() == 0 || (this.i.size() == 0 && this.j.size() == 0)) {
            return true;
        }
        Iterator<bu> it = arrayList.iterator();
        while (it.hasNext()) {
            bu next = it.next();
            if (next instanceof cj) {
                cj cjVar = (cj) next;
                StringBuilder sb = new StringBuilder();
                sb.append(cjVar.n);
                sb.append(!TextUtils.isEmpty(cjVar.o) ? cjVar.o : "");
                if (this.i.contains(sb.toString())) {
                    it.remove();
                }
            } else if ((next instanceof ct) && this.j.contains(((ct) next).o)) {
                it.remove();
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
    
        com.bytedance.a.dj.f3509a = r4.processName;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ce A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c() throws java.io.IOException {
        /*
            r8 = this;
            com.bytedance.applog.InitConfig r0 = r8.f3386b
            int r0 = r0.getProcess()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Lc6
            android.content.Context r0 = r8.f3385a
            java.lang.String r3 = com.bytedance.a.dj.f3509a
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L16
            goto Laa
        L16:
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L3f
            java.lang.String r4 = "activity"
            java.lang.Object r0 = r0.getSystemService(r4)     // Catch: java.lang.Exception -> L3f
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch: java.lang.Exception -> L3f
            java.util.List r0 = r0.getRunningAppProcesses()     // Catch: java.lang.Exception -> L3f
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L3f
        L2a:
            boolean r4 = r0.hasNext()     // Catch: java.lang.Exception -> L3f
            if (r4 == 0) goto L45
            java.lang.Object r4 = r0.next()     // Catch: java.lang.Exception -> L3f
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4     // Catch: java.lang.Exception -> L3f
            int r5 = r4.pid     // Catch: java.lang.Exception -> L3f
            if (r5 != r3) goto L2a
            java.lang.String r0 = r4.processName     // Catch: java.lang.Exception -> L3f
            com.bytedance.a.dj.f3509a = r0     // Catch: java.lang.Exception -> L3f
            goto L45
        L3f:
            r0 = move-exception
            java.lang.String r3 = ""
            com.bytedance.a.de.a(r3, r0)
        L45:
            r0 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L89
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L89
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L89
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
            r6.<init>()     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = "/proc/"
            r6.append(r7)     // Catch: java.lang.Throwable -> L89
            int r7 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L89
            r6.append(r7)     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = "/cmdline"
            r6.append(r7)     // Catch: java.lang.Throwable -> L89
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L89
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L89
            java.lang.String r6 = "iso-8859-1"
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L89
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L89
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a
            r4.<init>()     // Catch: java.lang.Throwable -> L8a
        L76:
            int r5 = r3.read()     // Catch: java.lang.Throwable -> L8a
            if (r5 <= 0) goto L81
            char r5 = (char) r5     // Catch: java.lang.Throwable -> L8a
            r4.append(r5)     // Catch: java.lang.Throwable -> L8a
            goto L76
        L81:
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L8a
            r3.close()     // Catch: java.lang.Exception -> L90
            goto L90
        L89:
            r3 = r0
        L8a:
            if (r3 == 0) goto L8f
            r3.close()     // Catch: java.lang.Exception -> L8f
        L8f:
            r4 = r0
        L90:
            com.bytedance.a.dj.f3509a = r4
            boolean r3 = com.bytedance.a.de.f3494c
            if (r3 == 0) goto La8
            java.lang.String r3 = "getProcessName, "
            java.lang.StringBuilder r3 = com.bytedance.a.a.a(r3)
            java.lang.String r4 = com.bytedance.a.dj.f3509a
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.bytedance.a.de.a(r3, r0)
        La8:
            java.lang.String r3 = com.bytedance.a.dj.f3509a
        Laa:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto Lc1
            com.bytedance.applog.InitConfig r0 = r8.f3386b
            java.lang.String r4 = ":"
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto Lbc
            r3 = 2
            goto Lbd
        Lbc:
            r3 = 1
        Lbd:
            r0.setProcess(r3)
            goto Lc6
        Lc1:
            com.bytedance.applog.InitConfig r0 = r8.f3386b
            r0.setProcess(r1)
        Lc6:
            com.bytedance.applog.InitConfig r0 = r8.f3386b
            int r0 = r0.getProcess()
            if (r0 != r2) goto Lcf
            r1 = 1
        Lcf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.az.c():boolean");
    }

    public JSONObject d() {
        JSONObject jSONObject = this.f;
        if (jSONObject == null) {
            synchronized (this) {
                try {
                    jSONObject = new JSONObject(this.f3387c.getString("ab_configure", ""));
                } catch (JSONException unused) {
                }
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                this.f = jSONObject;
            }
        }
        return jSONObject;
    }

    public String e() {
        String string = this.g;
        if (TextUtils.isEmpty(string)) {
            synchronized (this) {
                string = this.f3387c.getString("external_ab_version", "");
                this.g = string;
            }
        }
        return string;
    }

    public boolean f() {
        return this.f3386b.isAutoTrackEnabled();
    }

    public long g() {
        return this.e.getLong("session_interval", com.bytedance.b.a.a.c.g.e);
    }

    public long h() {
        long j = this.p;
        return (j > 10000L ? 1 : (j == 10000L ? 0 : -1)) >= 0 && (j > 300000L ? 1 : (j == 300000L ? 0 : -1)) <= 0 ? this.p : this.e.getLong("batch_event_interval", 60000L);
    }

    public String i() {
        StringBuilder sbA = a.a("ssid_");
        sbA.append(this.f3386b.getAid());
        return sbA.toString();
    }

    public void b(@NonNull JSONObject jSONObject) {
        this.l = jSONObject.optInt("backoff_ratio", 0);
        int i = this.l;
        if (i < 0 || i > 10000) {
            this.l = 0;
        }
        int i2 = this.l > 0 ? 1 : 27;
        this.m = jSONObject.optInt("max_request_frequency", i2);
        int i3 = this.m;
        if (i3 < 1 || i3 > 27) {
            this.m = i2;
        }
        if (this.l > 0 && this.n == 0) {
            this.n = System.currentTimeMillis();
            this.o = 1;
        } else if (this.l == 0) {
            this.n = 0L;
            this.o = 0;
        }
        this.p = jSONObject.optLong("batch_event_interval", 0L) * 1000;
        StringBuilder sbA = a.a("updateLogRespConfig mBackoffRatio: ");
        sbA.append(this.l);
        sbA.append(", mMaxRequestFrequency: ");
        sbA.append(this.m);
        sbA.append(", mBackoffWindowStartTime: ");
        sbA.append(this.n);
        sbA.append(", mBackoffWindowSendCount: ");
        sbA.append(this.o);
        sbA.append(", mEventIntervalFromLogResp: ");
        sbA.append(this.p);
        de.a(sbA.toString(), null);
    }
}
