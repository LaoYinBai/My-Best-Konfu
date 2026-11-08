package b.b.a.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.k.v;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1069a;

    /* renamed from: b, reason: collision with root package name */
    public final com.apm.applog.d f1070b;

    /* renamed from: c, reason: collision with root package name */
    public final SharedPreferences f1071c;
    public final SharedPreferences d;
    public final SharedPreferences e;
    public volatile JSONObject f;
    public volatile String g;
    public final HashSet<String> h;
    public final HashSet<String> i;
    public volatile HashSet<String> j;
    public int k = 0;
    public int l = 27;
    public long m = 0;
    public int n = 0;
    public long o = 0;
    public String p;

    public g(Context context, com.apm.applog.d dVar) {
        this.p = null;
        this.f1069a = context;
        this.f1070b = dVar;
        this.e = this.f1069a.getSharedPreferences(this.f1070b.P(), 0);
        Context context2 = this.f1069a;
        StringBuilder sbA = b.a.a.a.a.a("header_custom_");
        sbA.append(dVar.h());
        this.f1071c = context2.getSharedPreferences(sbA.toString(), 0);
        Context context3 = this.f1069a;
        StringBuilder sbA2 = b.a.a.a.a.a("last_sp_session_");
        sbA2.append(dVar.h());
        this.d = context3.getSharedPreferences(sbA2.toString(), 0);
        this.h = new HashSet<>();
        this.i = new HashSet<>();
        this.p = dVar.a();
    }

    public String a() {
        return this.f1070b.h();
    }

    public void a(int i) {
        this.e.edit().putInt("is_first_time_launch", i).apply();
    }

    public void a(boolean z) {
    }

    public String b() {
        String strI = this.f1070b.i();
        if (TextUtils.isEmpty(strI)) {
            strI = i();
        }
        if (!TextUtils.isEmpty(strI)) {
            return strI;
        }
        try {
            return this.f1069a.getPackageManager().getApplicationInfo(this.f1069a.getPackageName(), 128).metaData.getString("UMENG_CHANNEL");
        } catch (Throwable th) {
            v.a("getChannel", th);
            return strI;
        }
    }

    public String c() {
        return this.f1071c.getString("ab_sdk_version", "");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
    
        b.b.a.k.x.f1153a = r4.processName;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cc A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d() throws java.io.IOException {
        /*
            r8 = this;
            com.apm.applog.d r0 = r8.f1070b
            int r0 = r0.n()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Lc4
            android.content.Context r0 = r8.f1069a
            java.lang.String r3 = b.b.a.k.x.f1153a
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L16
            goto La8
        L16:
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L3f
            java.lang.String r4 = "activity"
            java.lang.Object r0 = r0.getSystemService(r4)     // Catch: java.lang.Exception -> L3f
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch: java.lang.Exception -> L3f
            java.util.List r0 = r0.getRunningAppProcesses()     // Catch: java.lang.Exception -> L3f
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L3f
        L2a:
            boolean r4 = r0.hasNext()     // Catch: java.lang.Exception -> L3f
            if (r4 == 0) goto L43
            java.lang.Object r4 = r0.next()     // Catch: java.lang.Exception -> L3f
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4     // Catch: java.lang.Exception -> L3f
            int r5 = r4.pid     // Catch: java.lang.Exception -> L3f
            if (r5 != r3) goto L2a
            java.lang.String r0 = r4.processName     // Catch: java.lang.Exception -> L3f
            b.b.a.k.x.f1153a = r0     // Catch: java.lang.Exception -> L3f
            goto L43
        L3f:
            r0 = move-exception
            r0.printStackTrace()
        L43:
            r0 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L87
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L87
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L87
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r6.<init>()     // Catch: java.lang.Throwable -> L87
            java.lang.String r7 = "/proc/"
            r6.append(r7)     // Catch: java.lang.Throwable -> L87
            int r7 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L87
            r6.append(r7)     // Catch: java.lang.Throwable -> L87
            java.lang.String r7 = "/cmdline"
            r6.append(r7)     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L87
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = "iso-8859-1"
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L87
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L87
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r4.<init>()     // Catch: java.lang.Throwable -> L88
        L74:
            int r5 = r3.read()     // Catch: java.lang.Throwable -> L88
            if (r5 <= 0) goto L7f
            char r5 = (char) r5     // Catch: java.lang.Throwable -> L88
            r4.append(r5)     // Catch: java.lang.Throwable -> L88
            goto L74
        L7f:
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L88
            r3.close()     // Catch: java.lang.Exception -> L8e
            goto L8e
        L87:
            r3 = r0
        L88:
            if (r3 == 0) goto L8d
            r3.close()     // Catch: java.lang.Exception -> L8d
        L8d:
            r4 = r0
        L8e:
            b.b.a.k.x.f1153a = r4
            boolean r3 = b.b.a.k.v.f1150b
            if (r3 == 0) goto La6
            java.lang.String r3 = "getProcessName, "
            java.lang.StringBuilder r3 = b.a.a.a.a.a(r3)
            java.lang.String r4 = b.b.a.k.x.f1153a
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            b.b.a.k.v.a(r3, r0)
        La6:
            java.lang.String r3 = b.b.a.k.x.f1153a
        La8:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto Lbf
            com.apm.applog.d r0 = r8.f1070b
            java.lang.String r4 = ":"
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto Lba
            r3 = 2
            goto Lbb
        Lba:
            r3 = 1
        Lbb:
            r0.a(r3)
            goto Lc4
        Lbf:
            com.apm.applog.d r0 = r8.f1070b
            r0.a(r1)
        Lc4:
            com.apm.applog.d r0 = r8.f1070b
            int r0 = r0.n()
            if (r0 != r2) goto Lcd
            r1 = 1
        Lcd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.f.g.d():boolean");
    }

    public String f() {
        String string = this.g;
        if (TextUtils.isEmpty(string)) {
            synchronized (this) {
                string = this.f1071c.getString("external_ab_version", "");
                this.g = string;
            }
        }
        return string;
    }

    public long g() {
        return this.e.getLong("session_interval", com.bytedance.b.a.a.c.g.e);
    }

    public long h() {
        long j = this.o;
        return (j > 10000L ? 1 : (j == 10000L ? 0 : -1)) >= 0 && (j > 300000L ? 1 : (j == 300000L ? 0 : -1)) <= 0 ? this.o : this.e.getLong("batch_event_interval", 60000L);
    }

    public String i() {
        return this.f1070b.A();
    }

    public String j() {
        StringBuilder sbA = b.a.a.a.a.a("ssid_");
        sbA.append(this.f1070b.h());
        return sbA.toString();
    }

    public ArrayList<b.b.a.j.b> a(ArrayList<b.b.a.j.b> arrayList) {
        Iterator<b.b.a.j.b> it = arrayList.iterator();
        ArrayList<b.b.a.j.b> arrayList2 = null;
        while (it.hasNext()) {
            b.b.a.j.b next = it.next();
            String string = "!_NO_NAME_!";
            if (next instanceof b.b.a.j.e) {
                b.b.a.j.e eVar = (b.b.a.j.e) next;
                StringBuilder sb = new StringBuilder();
                sb.append(eVar.m);
                sb.append(!TextUtils.isEmpty(eVar.n) ? eVar.n : "");
                string = sb.toString();
            } else if (next instanceof b.b.a.j.g) {
                string = ((b.b.a.j.g) next).n;
            }
            HashSet<String> hashSet = this.j;
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
                    v.a(th);
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

    public JSONObject e() {
        JSONObject jSONObject = this.f;
        if (jSONObject == null) {
            synchronized (this) {
                try {
                    jSONObject = new JSONObject(this.f1071c.getString("ab_configure", ""));
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

    public void a(String str) {
        v.a("setExternalAbVersion, " + str, (Throwable) null);
        b.a.a.a.a.a(this.f1071c, "external_ab_version", str);
        this.g = null;
    }

    public boolean b(ArrayList<b.b.a.j.b> arrayList) {
        if (arrayList == null || arrayList.size() == 0 || (this.h.size() == 0 && this.i.size() == 0)) {
            return true;
        }
        Iterator<b.b.a.j.b> it = arrayList.iterator();
        while (it.hasNext()) {
            b.b.a.j.b next = it.next();
            if (next instanceof b.b.a.j.e) {
                b.b.a.j.e eVar = (b.b.a.j.e) next;
                StringBuilder sb = new StringBuilder();
                sb.append(eVar.m);
                sb.append(!TextUtils.isEmpty(eVar.n) ? eVar.n : "");
                if (this.h.contains(sb.toString())) {
                    it.remove();
                }
            } else if ((next instanceof b.b.a.j.g) && this.i.contains(((b.b.a.j.g) next).n)) {
                it.remove();
            }
        }
        return true;
    }

    public void a(@NonNull JSONObject jSONObject) {
        this.k = jSONObject.optInt("backoff_ratio", 0);
        int i = this.k;
        if (i < 0 || i > 10000) {
            this.k = 0;
        }
        int i2 = this.k > 0 ? 1 : 27;
        this.l = jSONObject.optInt("max_request_frequency", i2);
        int i3 = this.l;
        if (i3 < 1 || i3 > 27) {
            this.l = i2;
        }
        if (this.k > 0 && this.m == 0) {
            this.m = System.currentTimeMillis();
            this.n = 1;
        } else if (this.k == 0) {
            this.m = 0L;
            this.n = 0;
        }
        this.o = jSONObject.optLong("batch_event_interval", 0L) * 1000;
        StringBuilder sbA = b.a.a.a.a.a("updateLogRespConfig mBackoffRatio: ");
        sbA.append(this.k);
        sbA.append(", mMaxRequestFrequency: ");
        sbA.append(this.l);
        sbA.append(", mBackoffWindowStartTime: ");
        sbA.append(this.m);
        sbA.append(", mBackoffWindowSendCount: ");
        sbA.append(this.n);
        sbA.append(", mEventIntervalFromLogResp: ");
        sbA.append(this.o);
        v.a(sbA.toString());
    }
}
