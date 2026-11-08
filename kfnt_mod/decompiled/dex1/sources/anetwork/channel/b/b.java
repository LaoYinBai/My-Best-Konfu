package anetwork.channel.b;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.statist.RequestStatistic;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.utils.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpUrl;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f675a = true;

    /* renamed from: b, reason: collision with root package name */
    private static volatile boolean f676b = true;

    /* renamed from: c, reason: collision with root package name */
    private static volatile boolean f677c = true;
    private static volatile int d = 5;
    private static volatile boolean e = true;
    private static volatile boolean f = true;
    private static volatile boolean g = false;
    private static volatile long h = 0;
    private static volatile boolean i = false;
    private static volatile ConcurrentHashMap<String, List<String>> j;
    private static final List<String> k = new ArrayList();
    private static volatile int l = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
    private static volatile boolean m = true;
    private static volatile boolean n = false;
    private static volatile int o = 60000;
    private static volatile CopyOnWriteArrayList<String> p = null;
    private static volatile ConcurrentHashMap<String, List<String>> q = null;
    private static volatile boolean r = true;
    private static volatile a s;

    public static void a() {
        h = PreferenceManager.getDefaultSharedPreferences(anetwork.channel.e.b.a()).getLong("Cache.Flag", 0L);
    }

    public static boolean b() {
        return f675a;
    }

    public static void a(boolean z) {
        f676b = z;
    }

    public static boolean c() {
        return f676b;
    }

    public static int d() {
        return d;
    }

    public static boolean e() {
        return f677c;
    }

    public static void a(a aVar) {
        if (s != null) {
            s.unRegister();
        }
        if (aVar != null) {
            aVar.register();
        }
        s = aVar;
    }

    public static boolean f() {
        return e;
    }

    public static boolean g() {
        return e && g;
    }

    public static boolean h() {
        return f;
    }

    public static void b(boolean z) {
        f = z;
    }

    public static void a(long j2) {
        if (j2 != h) {
            ALog.i("anet.NetworkConfigCenter", "set cache flag", null, "old", Long.valueOf(h), "new", Long.valueOf(j2));
            h = j2;
            SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(anetwork.channel.e.b.a()).edit();
            editorEdit.putLong("Cache.Flag", h);
            editorEdit.apply();
            anetwork.channel.a.b.a();
        }
    }

    public static void a(String str) throws JSONException {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkConfigCenter", "updateWhiteUrlList", null, "White List", str);
        }
        if (TextUtils.isEmpty(str)) {
            j = null;
            return;
        }
        ConcurrentHashMap<String, List<String>> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj = jSONObject.get(next);
                try {
                    if ("*".equals(obj)) {
                        concurrentHashMap.put(next, k);
                    } else if (obj instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) obj;
                        int length = jSONArray.length();
                        ArrayList arrayList = new ArrayList(length);
                        for (int i2 = 0; i2 < length; i2++) {
                            Object obj2 = jSONArray.get(i2);
                            if (obj2 instanceof String) {
                                arrayList.add((String) obj2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            concurrentHashMap.put(next, arrayList);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        } catch (JSONException e2) {
            ALog.e("anet.NetworkConfigCenter", "parse jsonObject failed", null, e2, new Object[0]);
        }
        j = concurrentHashMap;
    }

    public static boolean a(HttpUrl httpUrl) {
        ConcurrentHashMap<String, List<String>> concurrentHashMap;
        List<String> list;
        if (httpUrl == null || (concurrentHashMap = j) == null || (list = concurrentHashMap.get(httpUrl.host())) == null) {
            return false;
        }
        if (list == k) {
            return true;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (httpUrl.path().startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static int i() {
        return l;
    }

    public static void a(int i2) {
        l = i2;
    }

    public static boolean j() {
        return i;
    }

    public static void c(boolean z) {
        i = z;
    }

    public static void b(String str) throws JSONException {
        if (GlobalAppRuntimeInfo.isTargetProcess()) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                ArrayList arrayList = new ArrayList(length);
                for (int i2 = 0; i2 < length; i2++) {
                    String string = jSONArray.getString(i2);
                    if (c.c(string)) {
                        arrayList.add(string);
                    }
                }
                HttpDispatcher.getInstance().addHosts(arrayList);
            } catch (JSONException e2) {
                ALog.e("anet.NetworkConfigCenter", "parse hosts failed", null, e2, new Object[0]);
            }
        }
    }

    public static boolean k() {
        return m;
    }

    public static void d(boolean z) {
        m = z;
    }

    public static boolean l() {
        return n;
    }

    public static void e(boolean z) {
        n = z;
    }

    public static int m() {
        return o;
    }

    public static void b(int i2) {
        o = i2;
    }

    public static void c(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            p = null;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray(com.alipay.sdk.cons.c.f);
            int length = jSONArray.length();
            CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            for (int i2 = 0; i2 < length; i2++) {
                String string = jSONArray.getString(i2);
                if (c.c(string)) {
                    copyOnWriteArrayList.add(string);
                }
            }
            p = copyOnWriteArrayList;
        } catch (JSONException e2) {
            ALog.e("anet.NetworkConfigCenter", "parse hosts failed", null, e2, new Object[0]);
        }
    }

    public static boolean a(RequestStatistic requestStatistic) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList;
        if (requestStatistic == null || (copyOnWriteArrayList = p) == null || TextUtils.isEmpty(requestStatistic.host)) {
            return false;
        }
        Iterator<String> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (requestStatistic.host.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void d(String str) throws JSONException {
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkConfigCenter", "setDegradeRequestList", null, "Degrade List", str);
        }
        if (TextUtils.isEmpty(str)) {
            q = null;
            return;
        }
        ConcurrentHashMap<String, List<String>> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj = jSONObject.get(next);
                try {
                    if ("*".equals(obj)) {
                        concurrentHashMap.put(next, k);
                    } else if (obj instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) obj;
                        int length = jSONArray.length();
                        ArrayList arrayList = new ArrayList(length);
                        for (int i2 = 0; i2 < length; i2++) {
                            Object obj2 = jSONArray.get(i2);
                            if (obj2 instanceof String) {
                                arrayList.add((String) obj2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            concurrentHashMap.put(next, arrayList);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        } catch (JSONException e2) {
            ALog.e("anet.NetworkConfigCenter", "parse jsonObject failed", null, e2, new Object[0]);
        }
        q = concurrentHashMap;
    }

    public static boolean b(HttpUrl httpUrl) {
        ConcurrentHashMap<String, List<String>> concurrentHashMap;
        List<String> list;
        if (httpUrl == null || (concurrentHashMap = q) == null || (list = concurrentHashMap.get(httpUrl.host())) == null) {
            return false;
        }
        if (list == k) {
            return true;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (httpUrl.path().startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean n() {
        return r;
    }

    public static void f(boolean z) {
        r = z;
    }
}
