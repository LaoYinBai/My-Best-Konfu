package com.xiaomi.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.UtilityImpl;
import com.xiaomi.push.service.module.PushChannelRegion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class cu {
    protected static Context a;

    /* renamed from: a, reason: collision with other field name */
    private static a f203a;

    /* renamed from: a, reason: collision with other field name */
    private static cu f204a;
    private static String c;
    private static String d;

    /* renamed from: a, reason: collision with other field name */
    private long f206a;

    /* renamed from: a, reason: collision with other field name */
    private ct f207a;

    /* renamed from: a, reason: collision with other field name */
    protected b f208a;

    /* renamed from: a, reason: collision with other field name */
    private String f209a;

    /* renamed from: a, reason: collision with other field name */
    protected Map<String, cr> f210a;

    /* renamed from: b, reason: collision with other field name */
    private final long f211b;

    /* renamed from: b, reason: collision with other field name */
    private String f212b;

    /* renamed from: c, reason: collision with other field name */
    private long f213c;
    protected static Map<String, cq> b = new HashMap();

    /* renamed from: a, reason: collision with other field name */
    protected static boolean f205a = false;

    public interface a {
        cu a(Context context, ct ctVar, b bVar, String str);
    }

    public interface b {
        String a(String str);
    }

    protected cu(Context context, ct ctVar, b bVar, String str) {
        this(context, ctVar, bVar, str, null, null);
    }

    protected cu(Context context, ct ctVar, b bVar, String str, String str2, String str3) {
        this.f210a = new HashMap();
        this.f209a = Constants.FAIL;
        this.f206a = 0L;
        this.f211b = 15L;
        this.f213c = 0L;
        this.f212b = "isp_prov_city_country_ip";
        this.f208a = bVar;
        this.f207a = ctVar == null ? new cv(this) : ctVar;
        this.f209a = str;
        c = str2 == null ? context.getPackageName() : str2;
        d = str3 == null ? f() : str3;
    }

    public static synchronized cu a() {
        if (f204a == null) {
            throw new IllegalStateException("the host manager is not initialized yet.");
        }
        return f204a;
    }

    /* renamed from: a, reason: collision with other method in class */
    static String m182a() {
        NetworkInfo activeNetworkInfo;
        if (a == null) {
            return UtilityImpl.NET_TYPE_UNKNOWN;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) a.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) a.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            if (wifiManager == null || wifiManager.getConnectionInfo() == null) {
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
            return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
        } catch (Throwable unused) {
            return UtilityImpl.NET_TYPE_UNKNOWN;
        }
    }

    static String a(String str) throws UnsupportedEncodingException {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                byte b2 = bytes[i];
                int i2 = b2 & 240;
                if (i2 != 240) {
                    bytes[i] = (byte) (((b2 & com.umeng.commonsdk.proguard.az.m) ^ ((byte) (((b2 >> 4) + length) & 15))) | i2);
                }
            }
            return new String(bytes);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    private ArrayList<cq> a(ArrayList<String> arrayList) throws JSONException {
        boolean z;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        m191d();
        synchronized (this.f210a) {
            m187a();
            for (String str : this.f210a.keySet()) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        boolean zIsEmpty = b.isEmpty();
        synchronized (b) {
            z = zIsEmpty;
            for (Object obj : b.values().toArray()) {
                cq cqVar = (cq) obj;
                if (!cqVar.b()) {
                    b.remove(cqVar.f200b);
                    z = true;
                }
            }
        }
        if (!arrayList.contains(b())) {
            arrayList.add(b());
        }
        ArrayList<cq> arrayList2 = new ArrayList<>(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            String str2 = bg.e(a) ? UtilityImpl.NET_TYPE_WIFI : "wap";
            String strA = a(arrayList, str2, this.f209a, z);
            if (!TextUtils.isEmpty(strA)) {
                JSONObject jSONObject3 = new JSONObject(strA);
                com.xiaomi.channel.commonutils.logger.b.b(strA);
                if ("OK".equalsIgnoreCase(jSONObject3.getString("S"))) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("R");
                    String string = jSONObject4.getString("province");
                    String string2 = jSONObject4.getString("city");
                    String string3 = jSONObject4.getString("isp");
                    String string4 = jSONObject4.getString("ip");
                    String string5 = jSONObject4.getString(com.umeng.commonsdk.proguard.o.N);
                    JSONObject jSONObject5 = jSONObject4.getJSONObject(str2);
                    com.xiaomi.channel.commonutils.logger.b.c("get bucket: net=" + string3 + ", hosts=" + jSONObject5.toString());
                    int i2 = 0;
                    while (i2 < arrayList.size()) {
                        String str3 = arrayList.get(i2);
                        JSONArray jSONArrayOptJSONArray = jSONObject5.optJSONArray(str3);
                        if (jSONArrayOptJSONArray == null) {
                            com.xiaomi.channel.commonutils.logger.b.m22a("no bucket found for " + str3);
                            jSONObject = jSONObject5;
                        } else {
                            cq cqVar2 = new cq(str3);
                            int i3 = 0;
                            while (i3 < jSONArrayOptJSONArray.length()) {
                                String string6 = jSONArrayOptJSONArray.getString(i3);
                                if (TextUtils.isEmpty(string6)) {
                                    jSONObject2 = jSONObject5;
                                } else {
                                    jSONObject2 = jSONObject5;
                                    cqVar2.a(new cz(string6, jSONArrayOptJSONArray.length() - i3));
                                }
                                i3++;
                                jSONObject5 = jSONObject2;
                            }
                            jSONObject = jSONObject5;
                            arrayList2.set(i2, cqVar2);
                            cqVar2.g = string5;
                            cqVar2.c = string;
                            cqVar2.e = string3;
                            cqVar2.f = string4;
                            cqVar2.d = string2;
                            if (jSONObject4.has("stat-percent")) {
                                cqVar2.a(jSONObject4.getDouble("stat-percent"));
                            }
                            if (jSONObject4.has("stat-domain")) {
                                cqVar2.b(jSONObject4.getString("stat-domain"));
                            }
                            if (jSONObject4.has("ttl")) {
                                cqVar2.a(jSONObject4.getInt("ttl") * 1000);
                            }
                            m186a(cqVar2.a());
                        }
                        i2++;
                        jSONObject5 = jSONObject;
                    }
                    JSONObject jSONObjectOptJSONObject = jSONObject4.optJSONObject("reserved");
                    if (jSONObjectOptJSONObject != null) {
                        long j = jSONObject4.has("reserved-ttl") ? jSONObject4.getInt("reserved-ttl") * 1000 : 604800000L;
                        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray(next);
                            if (jSONArrayOptJSONArray2 == null) {
                                com.xiaomi.channel.commonutils.logger.b.m22a("no bucket found for " + next);
                            } else {
                                cq cqVar3 = new cq(next);
                                cqVar3.a(j);
                                for (int i4 = 0; i4 < jSONArrayOptJSONArray2.length(); i4++) {
                                    String string7 = jSONArrayOptJSONArray2.getString(i4);
                                    if (!TextUtils.isEmpty(string7)) {
                                        cqVar3.a(new cz(string7, jSONArrayOptJSONArray2.length() - i4));
                                    }
                                }
                                synchronized (b) {
                                    if (this.f207a.a(next)) {
                                        b.put(next, cqVar3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("failed to get bucket " + e.getMessage());
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            cq cqVar4 = arrayList2.get(i5);
            if (cqVar4 != null) {
                a(arrayList.get(i5), cqVar4);
            }
        }
        m190c();
        return arrayList2;
    }

    public static synchronized void a(Context context, ct ctVar, b bVar, String str, String str2, String str3) {
        a = context.getApplicationContext();
        if (a == null) {
            a = context;
        }
        if (f204a == null) {
            if (f203a == null) {
                f204a = new cu(context, ctVar, bVar, str, str2, str3);
            } else {
                f204a = f203a.a(context, ctVar, bVar, str);
            }
        }
    }

    public static synchronized void a(a aVar) {
        f203a = aVar;
        f204a = null;
    }

    public static void a(String str, String str2) {
        cq cqVar = b.get(str);
        synchronized (b) {
            try {
                if (cqVar == null) {
                    cq cqVar2 = new cq(str);
                    cqVar2.a(604800000L);
                    cqVar2.m175a(str2);
                    b.put(str, cqVar2);
                } else {
                    cqVar.m175a(str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private String f() throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = a.getPackageManager().getPackageInfo(a.getPackageName(), Message.FLAG_REQ_BIT1);
            return packageInfo != null ? packageInfo.versionName : Constants.FAIL;
        } catch (Exception unused) {
            return Constants.FAIL;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public cq m183a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty");
        }
        return a(new URL(str).getHost(), true);
    }

    public cq a(String str, boolean z) {
        cq cqVarD;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        if (!this.f207a.a(str)) {
            return null;
        }
        cq cqVarC = c(str);
        return (cqVarC == null || !cqVarC.b()) ? (z && bg.b(a) && (cqVarD = d(str)) != null) ? cqVarD : new cw(this, str, cqVarC) : cqVarC;
    }

    protected String a(ArrayList<String> arrayList, String str, String str2, boolean z) throws IOException {
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<bf> arrayList3 = new ArrayList();
        arrayList3.add(new bd("type", str));
        if (str.equals("wap")) {
            arrayList3.add(new bd("conpt", a(bg.m124a(a))));
        }
        if (z) {
            arrayList3.add(new bd("reserved", "1"));
        }
        arrayList3.add(new bd("uuid", str2));
        arrayList3.add(new bd("list", bm.a(arrayList, com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP)));
        arrayList3.add(new bd("countrycode", com.xiaomi.push.service.a.a(a).b()));
        cq cqVarC = c(b());
        String str3 = String.format(Locale.US, "https://%1$s/gslb/?ver=4.0", b());
        if (cqVarC == null) {
            arrayList2.add(str3);
            synchronized (b) {
                cq cqVar = b.get("resolver.msg.xiaomi.net");
                if (cqVar != null) {
                    Iterator<String> it = cqVar.a(true).iterator();
                    while (it.hasNext()) {
                        arrayList2.add(String.format(Locale.US, "https://%1$s/gslb/?ver=4.0", it.next()));
                    }
                }
            }
        } else {
            arrayList2 = cqVarC.a(str3);
        }
        Iterator<String> it2 = arrayList2.iterator();
        IOException e = null;
        while (it2.hasNext()) {
            Uri.Builder builderBuildUpon = Uri.parse(it2.next()).buildUpon();
            for (bf bfVar : arrayList3) {
                builderBuildUpon.appendQueryParameter(bfVar.a(), bfVar.b());
            }
            try {
                return this.f208a == null ? bg.a(a, new URL(builderBuildUpon.toString())) : this.f208a.a(builderBuildUpon.toString());
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e == null) {
            return null;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("network exception: " + e.getMessage());
        throw e;
    }

    /* renamed from: a, reason: collision with other method in class */
    protected JSONObject m184a() {
        JSONObject jSONObject;
        synchronized (this.f210a) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            JSONArray jSONArray = new JSONArray();
            Iterator<cr> it = this.f210a.values().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().m179a());
            }
            jSONObject.put("data", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<cq> it2 = b.values().iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().m174a());
            }
            jSONObject.put("reserved", jSONArray2);
        }
        return jSONObject;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m185a() {
        synchronized (this.f210a) {
            this.f210a.clear();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m186a(String str) {
        this.f212b = str;
    }

    public void a(String str, cq cqVar) {
        if (TextUtils.isEmpty(str) || cqVar == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + cqVar);
        }
        if (this.f207a.a(str)) {
            synchronized (this.f210a) {
                m187a();
                if (this.f210a.containsKey(str)) {
                    this.f210a.get(str).a(cqVar);
                } else {
                    cr crVar = new cr(str);
                    crVar.a(cqVar);
                    this.f210a.put(str, crVar);
                }
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    protected boolean m187a() {
        synchronized (this.f210a) {
            if (f205a) {
                return true;
            }
            f205a = true;
            this.f210a.clear();
            try {
                String strD = d();
                if (!TextUtils.isEmpty(strD)) {
                    m189b(strD);
                    com.xiaomi.channel.commonutils.logger.b.b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.m22a("load bucket failure: " + th.getMessage());
            }
            return false;
        }
    }

    public cq b(String str) {
        return a(str, true);
    }

    protected String b() {
        String strA = com.xiaomi.push.service.a.a(a).a();
        return (TextUtils.isEmpty(strA) || PushChannelRegion.China.name().equals(strA)) ? "resolver.msg.xiaomi.net" : "resolver.msg.global.xiaomi.net";
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m188b() throws JSONException {
        ArrayList<String> arrayList;
        synchronized (this.f210a) {
            m187a();
            arrayList = new ArrayList<>(this.f210a.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                cr crVar = this.f210a.get(arrayList.get(size));
                if (crVar != null && crVar.a() != null) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList<cq> arrayListA = a(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayListA.get(i) != null) {
                a(arrayList.get(i), arrayListA.get(i));
            }
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    protected void m189b(String str) {
        synchronized (this.f210a) {
            this.f210a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("data");
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                cr crVarA = new cr().a(jSONArrayOptJSONArray.getJSONObject(i));
                this.f210a.put(crVarA.m177a(), crVarA);
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("reserved");
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                cq cqVarA = new cq("").a(jSONArrayOptJSONArray2.getJSONObject(i2));
                b.put(cqVarA.f200b, cqVarA);
            }
        }
    }

    protected cq c(String str) {
        cr crVar;
        cq cqVarA;
        synchronized (this.f210a) {
            m187a();
            crVar = this.f210a.get(str);
        }
        if (crVar == null || (cqVarA = crVar.a()) == null) {
            return null;
        }
        return cqVarA;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.f210a) {
            for (Map.Entry<String, cr> entry : this.f210a.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":\n");
                sb.append(entry.getValue().toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: c, reason: collision with other method in class */
    public void m190c() {
        synchronized (this.f210a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(a.openFileOutput(e(), 0)));
                String string = m184a().toString();
                if (!TextUtils.isEmpty(string)) {
                    bufferedWriter.write(string);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.m22a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    protected cq d(String str) {
        if (System.currentTimeMillis() - this.f213c <= this.f206a * 60 * 1000) {
            return null;
        }
        this.f213c = System.currentTimeMillis();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        cq cqVar = a(arrayList).get(0);
        if (cqVar != null) {
            this.f206a = 0L;
            return cqVar;
        }
        if (this.f206a >= 15) {
            return null;
        }
        this.f206a++;
        return null;
    }

    protected String d() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        File file;
        try {
            file = new File(a.getFilesDir(), e());
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        if (!file.isFile()) {
            y.a((Closeable) null);
            return null;
        }
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        try {
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        String string = sb.toString();
                        y.a(bufferedReader);
                        return string;
                    }
                    sb.append(line);
                }
            } catch (Throwable th3) {
                th = th3;
                com.xiaomi.channel.commonutils.logger.b.m22a("load host exception " + th.getMessage());
                y.a(bufferedReader);
                return null;
            }
        } catch (Throwable th4) {
            th = th4;
            y.a(bufferedReader);
            throw th;
        }
    }

    /* renamed from: d, reason: collision with other method in class */
    public void m191d() {
        String next;
        synchronized (this.f210a) {
            Iterator<cr> it = this.f210a.values().iterator();
            while (it.hasNext()) {
                it.next().a(true);
            }
            while (true) {
                for (boolean z = false; !z; z = true) {
                    Iterator<String> it2 = this.f210a.keySet().iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (this.f210a.get(next).m178a().isEmpty()) {
                            break;
                        }
                    }
                }
                this.f210a.remove(next);
            }
        }
    }

    protected String e() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) a.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "com.xiaomi";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                return runningAppProcessInfo.processName;
            }
        }
        return "com.xiaomi";
    }
}
