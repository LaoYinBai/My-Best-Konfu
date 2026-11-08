package com.taobao.accs.client;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.taobao.accs.IAgooAppReceiver;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.f;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.t;
import com.taobao.aranger.ARanger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlobalClientInfo {
    public static final String AGOO_SERVICE_ID = "agooSend";
    public static Context a;
    public static IAgooAppReceiver b;
    public static String c;
    public static boolean d;
    private static volatile GlobalClientInfo e;
    private static Map<String, String> k = new ConcurrentHashMap();
    private static Map<String, Map<String, String>> l = new ConcurrentHashMap();
    private ConcurrentHashMap<String, ILoginInfo> f;
    private ConcurrentHashMap<String, IAppReceiver> g;
    private ActivityManager h;
    private ConnectivityManager i;
    private PackageInfo j;
    private Map<String, AccsDataListener> m = new ConcurrentHashMap();

    static {
        k.put(AGOO_SERVICE_ID, "org.android.agoo.accs.AgooService");
        k.put("agooAck", "org.android.agoo.accs.AgooService");
        k.put("agooTokenReport", "org.android.agoo.accs.AgooService");
    }

    @Keep
    public static GlobalClientInfo getInstance(Context context) {
        if (e == null) {
            synchronized (GlobalClientInfo.class) {
                if (e == null) {
                    e = new GlobalClientInfo(context);
                }
            }
        }
        return e;
    }

    public static Context getContext() {
        return a;
    }

    private GlobalClientInfo(Context context) {
        a = getContext();
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        ThreadPoolExecutorFactory.execute(new c(this));
    }

    public ActivityManager getActivityManager() {
        if (this.h == null) {
            this.h = (ActivityManager) a.getSystemService("activity");
        }
        return this.h;
    }

    public ConnectivityManager getConnectivityManager() {
        if (this.i == null) {
            this.i = (ConnectivityManager) a.getSystemService("connectivity");
        }
        return this.i;
    }

    public void setLoginInfoImpl(String str, ILoginInfo iLoginInfo) {
        if (this.f == null) {
            this.f = new ConcurrentHashMap<>(1);
        }
        if (iLoginInfo != null) {
            this.f.put(str, iLoginInfo);
        }
    }

    public void clearLoginInfoImpl() {
        this.f = null;
    }

    public String getSid(String str) {
        ILoginInfo iLoginInfo;
        if (this.f == null || (iLoginInfo = this.f.get(str)) == null) {
            return null;
        }
        return iLoginInfo.getSid();
    }

    public String getUserId(String str) {
        ILoginInfo iLoginInfo;
        if (this.f == null || (iLoginInfo = this.f.get(str)) == null) {
            return null;
        }
        return iLoginInfo.getUserId();
    }

    public String getNick(String str) {
        ILoginInfo iLoginInfo;
        if (this.f == null || (iLoginInfo = this.f.get(str)) == null) {
            return null;
        }
        return iLoginInfo.getNick();
    }

    public void setAppReceiver(String str, IAppReceiver iAppReceiver) {
        if ((!t.g() || UtilityImpl.isMainProcess(a)) && iAppReceiver != null) {
            if (iAppReceiver instanceof IAgooAppReceiver) {
                b = (IAgooAppReceiver) iAppReceiver;
            } else {
                if (this.g == null) {
                    this.g = new ConcurrentHashMap<>(2);
                }
                this.g.put(str, iAppReceiver);
                a(str, iAppReceiver.getAllServices());
            }
            if (t.g()) {
                try {
                    ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{a});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Map<String, IAppReceiver> getAppReceiver() {
        return this.g;
    }

    public Map<String, AccsDataListener> getListener() {
        return this.m;
    }

    public void registerService(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        k.put(str, str2);
        if (t.g() && UtilityImpl.isMainProcess(a)) {
            try {
                ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{a});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void unRegisterService(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        k.remove(str);
        if (t.g() && UtilityImpl.isMainProcess(a)) {
            try {
                ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{a});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public String getService(String str) {
        return k.get(str);
    }

    public String getService(String str, String str2) {
        if (l.get(str) != null) {
            return l.get(str).get(str2);
        }
        return null;
    }

    public void registerListener(String str, AccsAbstractDataListener accsAbstractDataListener) {
        registerListener(str, (AccsDataListener) accsAbstractDataListener);
    }

    public void registerListener(String str, AccsDataListener accsDataListener) {
        if ((t.g() && !UtilityImpl.isMainProcess(a)) || TextUtils.isEmpty(str) || accsDataListener == null) {
            return;
        }
        this.m.put(str, accsDataListener);
        if (t.g()) {
            try {
                ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{a});
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void unregisterListener(String str) {
        if (!t.g() || UtilityImpl.isMainProcess(a)) {
            this.m.remove(str);
            if (t.g()) {
                try {
                    ARanger.getInstance(com.taobao.accs.a.class.getName(), f.class, new Object[]{a});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public AccsDataListener getListener(String str) {
        return this.m.get(str);
    }

    public PackageInfo getPackageInfo() {
        try {
            if (this.j == null) {
                this.j = a.getPackageManager().getPackageInfo(a.getPackageName(), 0);
            }
        } catch (Throwable th) {
            ALog.e("GlobalClientInfo", "getPackageInfo", th, new Object[0]);
        }
        return this.j;
    }

    public Map<String, String> getAllService(String str) {
        if (l.get(str) == null || l.get(str).isEmpty()) {
            return null;
        }
        return l.get(str);
    }

    private void a(String str, Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (l.get(str) == null) {
            l.put(str, new ConcurrentHashMap());
        }
        l.get(str).putAll(map);
    }

    @Keep
    public void setRemoteAgooAppReceiver(IAgooAppReceiver iAgooAppReceiver) {
        b = iAgooAppReceiver;
    }

    @Keep
    public void setRemoteAppReceiver(String str, IAppReceiver iAppReceiver) {
        if (this.g == null) {
            this.g = new ConcurrentHashMap<>(2);
        }
        this.g.put(str, iAppReceiver);
        a(str, iAppReceiver.getAllServices());
    }

    @Keep
    public void registerRemoteListener(String str, AccsDataListener accsDataListener) {
        this.m.put(str, accsDataListener);
    }

    @Keep
    public void unregisterRemoteListener(String str) {
        this.m.remove(str);
    }

    @Keep
    public void registerRemoteService(String str, String str2) {
        k.put(str, str2);
    }

    @Keep
    public void unregisterRemoteService(String str) {
        k.remove(str);
    }
}
