package com.bytedance.b.a.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.b.a.b.c;
import com.bytedance.b.a.c.j;
import com.bytedance.frameworks.core.encrypt.TTEncryptUtils;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import com.bytedance.services.apm.api.IHttpService;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k implements com.bytedance.b.a.a.a.b, com.bytedance.b.a.a.c.c, com.bytedance.b.a.a.d.b {
    private static final int K = 1;

    /* renamed from: b, reason: collision with root package name */
    private static final int f3699b = 400;
    private volatile boolean A;
    private volatile boolean B;
    private volatile boolean D;
    private volatile long F;
    private String I;
    private boolean J;

    /* renamed from: c, reason: collision with root package name */
    private Context f3700c;
    private JSONObject d;
    private com.bytedance.b.a.a.e e;
    private com.bytedance.b.a.a.b f;
    private volatile long h;
    private volatile int i;
    private volatile int j;
    private volatile int k;
    private volatile int l;
    private volatile JSONObject m;
    private volatile JSONObject n;
    private volatile IHttpService o;
    private volatile int p;
    private volatile int q;
    private volatile List<String> r;
    private volatile List<Pattern> s;
    private volatile List<String> t;
    private volatile List<Pattern> u;
    private volatile long w;
    private c x;
    private Map<String, String> y;
    private volatile long g = 0;
    private volatile int v = 1;
    private List<String> z = new LinkedList();
    private volatile boolean C = true;
    private List<String> G = new LinkedList();
    private volatile boolean E = false;
    private com.bytedance.b.a.c.b H = new com.bytedance.b.a.c.b();

    public interface a {
        void a(boolean z);
    }

    public interface b {
        String a();
    }

    public interface c {
        String a();

        Map<String, String> b();
    }

    protected k(String str) {
        this.I = str;
    }

    public boolean a(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull final b bVar) {
        if (bVar == null) {
            return a(context, jSONObject, new c() { // from class: com.bytedance.b.a.c.k.1
                @Override // com.bytedance.b.a.c.k.c
                public String a() {
                    return null;
                }

                @Override // com.bytedance.b.a.c.k.c
                public Map<String, String> b() {
                    return null;
                }
            });
        }
        return a(context, jSONObject, new c() { // from class: com.bytedance.b.a.c.k.7
            @Override // com.bytedance.b.a.c.k.c
            public Map<String, String> b() {
                return null;
            }

            @Override // com.bytedance.b.a.c.k.c
            public String a() {
                return bVar.a();
            }
        });
    }

    public boolean a(@NonNull final Context context, @NonNull final JSONObject jSONObject, @NonNull final c cVar) {
        this.D = true;
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.8
            @Override // java.lang.Runnable
            public void run() throws JSONException, PackageManager.NameNotFoundException {
                k.this.b(context, jSONObject, cVar);
                if (k.this.E) {
                    k.this.H.a(k.this);
                }
            }
        }, 5000L);
        return true;
    }

    protected boolean j() {
        return this.D;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, JSONObject jSONObject, c cVar) throws JSONException, PackageManager.NameNotFoundException {
        if (context == null || jSONObject == null) {
            return;
        }
        this.D = true;
        this.f3700c = context.getApplicationContext();
        this.d = jSONObject;
        try {
            this.d.put("aid", this.I);
            this.d.put("os", "Android");
            this.d.put("device_platform", DispatchConstants.ANDROID);
            this.d.put("os_version", Build.VERSION.RELEASE);
            this.d.put("os_api", Build.VERSION.SDK_INT);
            this.d.put("device_model", Build.MODEL);
            this.d.put("device_brand", Build.BRAND);
            this.d.put("device_manufacturer", Build.MANUFACTURER);
            this.d.put(h.L, com.bytedance.frameworks.core.a.a.f);
            this.d.put(h.N, "sdk");
            if (TextUtils.isEmpty(this.d.optString(h.t))) {
                this.d.put(h.t, context.getPackageName());
            }
            PackageInfo packageInfo = null;
            if (TextUtils.isEmpty(this.d.optString("version_name"))) {
                packageInfo = this.f3700c.getPackageManager().getPackageInfo(this.f3700c.getPackageName(), 0);
                this.d.put("version_name", packageInfo.versionName);
            }
            if (TextUtils.isEmpty(this.d.optString("version_code"))) {
                if (packageInfo == null) {
                    packageInfo = this.f3700c.getPackageManager().getPackageInfo(this.f3700c.getPackageName(), 0);
                }
                this.d.put("version_code", packageInfo.versionCode);
            }
            this.x = cVar;
            if (this.x == null) {
                this.x = new c() { // from class: com.bytedance.b.a.c.k.9
                    @Override // com.bytedance.b.a.c.k.c
                    public String a() {
                        return null;
                    }

                    @Override // com.bytedance.b.a.c.k.c
                    public Map<String, String> b() {
                        return null;
                    }
                };
            }
            this.y = this.x.b();
            if (this.y == null) {
                this.y = new HashMap();
            }
            this.J = TextUtils.equals(this.y.get("oversea"), "1");
            this.y.put("aid", this.I);
            this.y.put(h.x, this.d.optString(h.x));
            this.y.put("device_platform", DispatchConstants.ANDROID);
            this.y.put(h.t, this.d.optString(h.t));
            this.y.put("channel", this.d.optString("channel"));
            this.y.put("app_version", this.d.optString("app_version"));
            this.y.put(h.L, com.bytedance.frameworks.core.a.a.f);
            this.y.put(h.M, "1");
            com.bytedance.b.a.a.a.c.a(this.I, this);
            com.bytedance.b.a.a.c.d.a(this.I, this);
            v();
            this.e = new com.bytedance.b.a.a.e(this.f3700c, this.I);
            this.e.a();
            this.o = (IHttpService) com.bytedance.news.common.service.manager.e.a(IHttpService.class);
            s();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IHttpService r() {
        if (this.o == null) {
            this.o = (IHttpService) com.bytedance.news.common.service.manager.e.a(IHttpService.class);
        }
        return this.o;
    }

    void a(List<String> list) {
        if (list == null || g.a(list)) {
            return;
        }
        this.z.clear();
        this.z.addAll(list);
    }

    void b(List<String> list) {
        if (list == null || g.a(list)) {
            return;
        }
        this.G.clear();
        this.G.addAll(list);
    }

    private void s() {
        SharedPreferences sharedPreferencesT = t();
        String string = sharedPreferencesT.getString(h.j, null);
        this.g = sharedPreferencesT.getLong(h.k, 0L);
        boolean z = sharedPreferencesT.getInt(h.l, 0) == 1;
        com.bytedance.b.a.a.d.a.a().a(this);
        if (!TextUtils.isEmpty(string)) {
            try {
                this.E = true;
                JSONObject jSONObject = new JSONObject(string);
                if (!z) {
                    jSONObject.remove("report_host_new");
                }
                a(jSONObject);
            } catch (Exception unused) {
                Log.e(h.i, "config error");
            }
        }
        a(false);
    }

    private SharedPreferences t() {
        String strA = i.a(this.f3700c);
        return this.f3700c.getSharedPreferences(h.i + this.I + strA, 0);
    }

    void a(boolean z) {
        if (this.h < 600) {
            this.h = 600L;
        }
        if ((z || u()) && j.c(this.f3700c)) {
            synchronized (k.class) {
                this.g = System.currentTimeMillis();
            }
            try {
                com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.10
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (k.this.y != null) {
                                Iterator it = k.this.z.iterator();
                                while (it.hasNext()) {
                                    StringBuilder sbD = k.this.d((String) it.next());
                                    sbD.append("&encrypt=close");
                                    String string = sbD.toString();
                                    try {
                                        byte[] bArrB = k.this.r() != null ? k.this.r().doGet(string, null).b() : j.a(string, null, k.this.C);
                                        if (bArrB != null) {
                                            k.this.b(new JSONObject(new String(bArrB)));
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th) {
                                        th.printStackTrace();
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            if (!k.this.E) {
                                k.this.E = true;
                                k.this.H.a(k.this);
                            }
                            th2.printStackTrace();
                        }
                    }
                });
            } catch (Throwable unused) {
            }
        }
    }

    private boolean u() {
        return (System.currentTimeMillis() - this.g) / 1000 > this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        try {
            a(jSONObject.getJSONObject("ret"));
            SharedPreferences.Editor editorEdit = t().edit();
            editorEdit.putLong(h.k, System.currentTimeMillis());
            editorEdit.putInt(h.l, 1);
            editorEdit.putString(h.j, jSONObject.getJSONObject("ret").toString());
            editorEdit.apply();
            if (this.E) {
                return;
            }
            this.E = true;
            this.H.a(this);
        } catch (Throwable unused) {
        }
    }

    synchronized void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObjectA = f.a(jSONObject, h.S, h.V);
        if (jSONObjectA != null) {
            JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject(h.Z);
            if (jSONObjectOptJSONObject != null) {
                this.h = jSONObjectOptJSONObject.optLong(h.aa, h.m);
            }
            if (this.h < 600) {
                this.h = 600L;
            }
            if (u()) {
                return;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectA.optJSONObject(h.ab);
            if (jSONObjectOptJSONObject2 != null) {
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject2.optJSONArray(DispatchConstants.HOSTS);
                    if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                        int length = jSONArrayOptJSONArray.length();
                        for (int i = 0; i < length; i++) {
                            String string = jSONArrayOptJSONArray.getString(i);
                            if (this.J && (string.contains(".snssdk.") || string.contains(".toutiao."))) {
                                arrayList.clear();
                                break;
                            }
                            if (!TextUtils.isEmpty(string) && string.indexOf(46) > 0) {
                                arrayList.add(string);
                            }
                        }
                    }
                } catch (Exception unused) {
                }
                if (!arrayList.isEmpty()) {
                    this.G = l.a(arrayList);
                }
                int i2 = 120;
                this.i = jSONObjectOptJSONObject2.optInt("uploading_interval", 120);
                if (this.i >= 0) {
                    i2 = this.i;
                }
                this.i = i2;
                int i3 = 100;
                this.j = jSONObjectOptJSONObject2.optInt("once_max_count", 100);
                if (this.j >= 0) {
                    i3 = this.j;
                }
                this.j = i3;
                this.k = jSONObjectOptJSONObject2.optInt("max_retry_count", 4);
                this.l = jSONObjectOptJSONObject2.optInt("report_fail_base_interval", 15);
                this.v = jSONObjectOptJSONObject2.optInt("log_send_switch", 1);
                this.w = jSONObjectOptJSONObject2.optLong("more_channel_stop_interval", h.o);
                this.C = jSONObjectOptJSONObject2.optBoolean("enable_encrypt", true);
                this.F = jSONObjectOptJSONObject2.optLong("memory_store_cache_max_count", 2000L);
                this.A = jSONObjectOptJSONObject2.optBoolean("log_remove_switch", false);
            }
        }
        if (jSONObject.length() <= 0) {
            this.G = null;
            return;
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject(h.T);
        if (jSONObjectOptJSONObject3 != null) {
            this.m = jSONObjectOptJSONObject3.optJSONObject("allow_service_name");
            this.n = jSONObjectOptJSONObject3.optJSONObject("allow_log_type");
        }
        JSONObject jSONObjectA2 = f.a(jSONObject, h.U, h.W);
        if (jSONObjectA2 != null) {
            this.p = jSONObjectA2.optInt("enable_api_error_upload", 0);
            this.q = jSONObjectA2.optInt("enable_api_all_upload", 0);
            this.r = g.a(jSONObjectA2, "api_block_list");
            this.s = g.b(jSONObjectA2, "api_block_list");
            this.t = g.a(jSONObjectA2, "api_allow_list");
            this.u = g.b(jSONObjectA2, "api_allow_list");
        }
        if (this.f == null) {
            this.f = new com.bytedance.b.a.a.b(this.f3700c, this.I);
            this.f.a();
        }
        this.f.d();
    }

    public boolean b(String str) {
        return (this.m == null || TextUtils.isEmpty(str) || this.m.opt(str) == null) ? false : true;
    }

    public boolean c(String str) {
        return (this.n == null || TextUtils.isEmpty(str) || this.n.opt(str) == null) ? false : true;
    }

    boolean k() {
        return this.v == 1;
    }

    public int l() {
        return com.bytedance.b.a.a.e.e.d(this.f3700c).a();
    }

    @Override // com.bytedance.b.a.a.d.b
    public void a(long j) {
        if (this.h <= 0) {
            return;
        }
        a(false);
    }

    @Override // com.bytedance.b.a.a.a.b
    public boolean h() {
        return this.v == 1;
    }

    @Override // com.bytedance.b.a.a.a.b
    public int a() {
        if (this.i <= 0) {
            return 120;
        }
        return this.i;
    }

    @Override // com.bytedance.b.a.a.a.b
    public int b() {
        if (this.j <= 0) {
            return 100;
        }
        return this.j;
    }

    @Override // com.bytedance.b.a.a.a.b
    public List<String> a(String str) {
        return this.G;
    }

    @Override // com.bytedance.b.a.a.a.b
    public long i() {
        return i();
    }

    @Override // com.bytedance.b.a.a.a.b
    public JSONObject c() {
        return this.d;
    }

    @Override // com.bytedance.b.a.a.a.b
    public int d() {
        if (this.k <= 0) {
            return 4;
        }
        return this.k;
    }

    @Override // com.bytedance.b.a.a.a.b
    public int e() {
        if (this.l <= 0) {
            return 15;
        }
        return this.l;
    }

    @Override // com.bytedance.b.a.a.a.b
    public long f() {
        if (this.w == 0) {
            return 1800000L;
        }
        return this.w * 1000;
    }

    @Override // com.bytedance.b.a.a.a.b
    public boolean g() {
        return this.B ? this.B : this.A;
    }

    @Override // com.bytedance.b.a.a.c.c
    public com.bytedance.b.a.a.c.f a(String str, byte[] bArr) {
        byte[] bArrB;
        Map<String, String> mapC;
        com.bytedance.b.a.a.c.f fVar = new com.bytedance.b.a.a.c.f();
        try {
            String string = d(str).toString();
            new HashMap();
            if (r() != null) {
                HashMap map = new HashMap();
                byte[] bArrA = j.a(bArr, map);
                if (this.C) {
                    byte[] bArrA2 = TTEncryptUtils.a(bArrA, bArrA.length);
                    if (bArrA2 != null) {
                        string = string + "&tt_data=a";
                        map.put("Content-Type", "application/octet-stream;tt-data=a");
                    }
                    com.bytedance.services.apm.api.d dVarDoPost = r().doPost(string, bArrA2, map);
                    bArrB = dVarDoPost.b();
                    mapC = dVarDoPost.c();
                } else {
                    com.bytedance.services.apm.api.d dVarDoPost2 = r().doPost(string, bArrA, map);
                    bArrB = dVarDoPost2.b();
                    mapC = dVarDoPost2.c();
                }
            } else {
                com.bytedance.services.apm.api.d dVarA = j.a(string, bArr, j.a.GZIP, com.bytedance.b.a.a.c.e.f3610c, this.C);
                bArrB = dVarA.b();
                mapC = dVarA.c();
            }
            String str2 = null;
            if (mapC != null && !mapC.isEmpty()) {
                str2 = mapC.get("ran");
            }
            fVar.f3611a = 200;
            JSONObject jSONObject = new JSONObject(new String(bArrB));
            try {
                String strOptString = jSONObject.optString("data");
                if (!strOptString.isEmpty()) {
                    String strA = com.bytedance.b.a.a.e.b.a(strOptString.getBytes(), str2);
                    if (!TextUtils.isEmpty(strA)) {
                        jSONObject = new JSONObject(strA);
                    } else {
                        jSONObject.put("message", "success");
                    }
                }
                fVar.f3612b = jSONObject;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            fVar.f3612b = jSONObject;
            return fVar;
        } catch (Throwable th2) {
            if (th2 instanceof d) {
                fVar.f3611a = ((d) th2).a();
            } else {
                fVar.f3611a = -1;
            }
            return fVar;
        }
    }

    public void b(long j) {
        this.f.b(j);
    }

    public void m() {
        this.f.e();
    }

    public void n() {
        this.f.f();
        this.B = true;
    }

    public void b(boolean z) {
        this.e.a(z);
    }

    public void o() {
        try {
            this.e.b();
            this.f.b();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuilder d(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (TextUtils.isDigitsOnly(str) || this.y == null) {
            return sb;
        }
        if (!str.contains("?")) {
            sb.append("?");
        }
        a(sb, PluginConstants.KEY_SDK_VERSION, String.valueOf(f3699b));
        Map<String, String> map = this.y;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null) {
                    a(sb, String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
                }
            }
        }
        return sb;
    }

    private StringBuilder a(@NonNull StringBuilder sb, String str, String str2) {
        if (sb.charAt(sb.length() - 1) != '?') {
            sb.append("&");
        }
        sb.append(a(str, "UTF-8"));
        sb.append('=');
        sb.append(a(str2, "UTF-8"));
        return sb;
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "UTF-8";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void v() {
        com.bytedance.b.a.b.c.a(new c.b() { // from class: com.bytedance.b.a.c.k.11
            @Override // com.bytedance.b.a.b.c.b
            public boolean a(Context context) {
                return j.c(context);
            }
        });
    }

    private String w() {
        if (this.x != null) {
            return this.x.a();
        }
        return null;
    }

    public void a(String str, int i, JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (!this.E) {
                this.H.a(new m(str, i, jSONObject, null, null, jSONObject2, System.currentTimeMillis()));
            } else {
                a(str, i, jSONObject, (JSONObject) null, (JSONObject) null, jSONObject2, System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str, int i, JSONObject jSONObject) {
        a(str, i, (JSONObject) null, jSONObject);
    }

    public void a(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        a(str, 0, jSONObject, jSONObject2);
    }

    public void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        try {
            if (!this.E) {
                this.H.a(new m(str, 0, null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis()));
            } else {
                a(str, 0, (JSONObject) null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str, int i, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        try {
            if (!this.E) {
                this.H.a(new m(str, i, null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis()));
            } else {
                a(str, i, (JSONObject) null, jSONObject, jSONObject2, jSONObject3, System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    public void a(final long j, final long j2, final String str, final String str2, final String str3, final int i, final JSONObject jSONObject) {
        try {
            if (!this.E) {
                this.H.a(new com.bytedance.b.a.c.a(h.r, j, j2, str, str2, str3, i, jSONObject));
            } else {
                com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.12
                    @Override // java.lang.Runnable
                    public void run() throws JSONException {
                        k.this.c(j, j2, str, str2, str3, i, jSONObject);
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public void b(final long j, final long j2, final String str, final String str2, final String str3, final int i, final JSONObject jSONObject) {
        try {
            if (!this.E) {
                this.H.a(new com.bytedance.b.a.c.a(h.r, j, j2, str, str2, str3, i, jSONObject));
            } else {
                com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.13
                    @Override // java.lang.Runnable
                    public void run() throws JSONException {
                        k.this.d(j, j2, str, str2, str3, i, jSONObject);
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    void a(final String str, final int i, final JSONObject jSONObject, final JSONObject jSONObject2, final JSONObject jSONObject3, final JSONObject jSONObject4, final long j) {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.14
            @Override // java.lang.Runnable
            public void run() {
                k.this.b(str, i, jSONObject, jSONObject2, jSONObject3, jSONObject4, j);
            }
        });
    }

    void b(String str, int i, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j) {
        if (jSONObject4 == null) {
            try {
                jSONObject4 = new JSONObject();
            } catch (Throwable unused) {
                return;
            }
        }
        jSONObject4.put(com.bytedance.b.a.a.d.o, h.q);
        jSONObject4.put("service", str);
        jSONObject4.put("status", i);
        jSONObject4.put(com.bytedance.b.a.a.d.s, l());
        jSONObject4.put(com.bytedance.b.a.a.a.n, jSONObject);
        if (jSONObject2 != null) {
            jSONObject4.put("category", jSONObject2);
        }
        if (jSONObject3 != null) {
            jSONObject4.put("metric", jSONObject3);
        }
        if (jSONObject4.isNull("timestamp")) {
            jSONObject4.put("timestamp", j);
        }
        if (!TextUtils.isEmpty(w())) {
            jSONObject4.put(com.bytedance.b.a.a.d.p, w());
        }
        if (this.e == null || !b(str)) {
            return;
        }
        this.e.a(h.q, h.q, jSONObject4);
    }

    public void a(String str, JSONObject jSONObject) {
        try {
            if (!this.E) {
                this.H.a(new com.bytedance.b.a.c.c(str, jSONObject, System.currentTimeMillis()));
            } else {
                a(str, jSONObject, System.currentTimeMillis());
            }
        } catch (Throwable unused) {
        }
    }

    public void p() {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.2
            @Override // java.lang.Runnable
            public void run() {
                if (k.this.e != null) {
                    k.this.e.a(0L, true);
                }
            }
        });
    }

    public void a(final a aVar) {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    boolean zA = k.this.e != null ? k.this.e.a(0L, true) : false;
                } finally {
                    aVar.a(false);
                }
            }
        });
    }

    public void q() {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.4
            @Override // java.lang.Runnable
            public void run() {
                if (k.this.e == null || k.this.f == null) {
                    return;
                }
                k.this.f.a(true);
            }
        });
    }

    public void b(final a aVar) {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.5
            @Override // java.lang.Runnable
            public void run() {
                boolean zA = false;
                try {
                    if (k.this.e != null && k.this.f != null) {
                        zA = k.this.f.a(true);
                    }
                } finally {
                    aVar.a(false);
                }
            }
        });
    }

    void a(final String str, final JSONObject jSONObject, final long j) {
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.k.6
            @Override // java.lang.Runnable
            public void run() {
                k.this.b(str, jSONObject, j);
            }
        });
    }

    void b(String str, JSONObject jSONObject, long j) {
        try {
            jSONObject.put(com.bytedance.b.a.a.d.o, str);
            jSONObject.put(com.bytedance.b.a.a.d.s, l());
            if (!TextUtils.isEmpty(w())) {
                jSONObject.put(com.bytedance.b.a.a.d.p, w());
            }
            if (jSONObject.isNull("timestamp")) {
                jSONObject.put("timestamp", j);
            }
            if (this.e == null || !c(str)) {
                return;
            }
            this.e.a(com.bytedance.b.a.a.d.n, com.bytedance.b.a.a.d.n, jSONObject);
        } catch (Throwable unused) {
        }
    }

    void c(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) throws JSONException {
        if (f(str) || !j.c(this.f3700c)) {
            return;
        }
        JSONObject jSONObjectA = a(h.r, j, j2, str, str2, str3, i);
        a(jSONObjectA, jSONObject);
        if ((jSONObjectA == null || !e(str)) && this.q == 0) {
            return;
        }
        try {
            jSONObjectA.put(com.bytedance.b.a.a.d.u, 1);
            this.e.a(h.r, h.r, jSONObjectA);
        } catch (JSONException unused) {
        }
    }

    void d(long j, long j2, String str, String str2, String str3, int i, JSONObject jSONObject) throws JSONException {
        if (this.p == 1 || f(str)) {
            return;
        }
        JSONObject jSONObjectA = a(h.s, j, j2, str, str2, str3, i);
        a(jSONObjectA, jSONObject);
        if (jSONObjectA == null || jSONObjectA.length() <= 0 || this.e == null) {
            return;
        }
        this.e.a(h.s, h.s, jSONObjectA);
    }

    private boolean e(String str) {
        return a(str, this.t, this.u);
    }

    private boolean f(String str) {
        return a(str, this.r, this.s);
    }

    private boolean a(String str, List<String> list, List<Pattern> list2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!g.a(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.contains(it.next())) {
                    return true;
                }
            }
        }
        try {
            String path = new URI(str).getPath();
            if (!g.a(list2)) {
                Iterator<Pattern> it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (it2.next().matcher(path).matches()) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject a(String str, long j, long j2, String str2, String str3, String str4, int i) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.bytedance.b.a.a.d.o, str);
            jSONObject.put("duration", j);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("uri", Uri.parse(str2));
            }
            if (j2 > 0) {
                jSONObject.put("timestamp", j2);
            }
            jSONObject.put("status", i);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("ip", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("trace_code", str4);
            } else {
                jSONObject.put("trace_code", "");
            }
            jSONObject.put(com.bytedance.b.a.a.d.s, j.b(this.f3700c).a());
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }
}
