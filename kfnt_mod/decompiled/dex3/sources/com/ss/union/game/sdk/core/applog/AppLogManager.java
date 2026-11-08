package com.ss.union.game.sdk.core.applog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IDataObserver;
import com.bytedance.applog.ILogger;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.InitConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppLogManager implements com.ss.union.game.sdk.core.applog.b {
    private static final String a = "is_adult";
    private static final String b = "local_app_id";
    private static final String c = "open_id";
    private static final String d = "lgsdk_header";
    private static AppLogManager e = new AppLogManager();
    private final List<AppLogIdUpdateListener> f = new ArrayList();
    private final List<AppLogOaidListener> g = new ArrayList();
    private final List<AppLogAbConfigGetListener> h = new ArrayList();
    private String i;
    private String j;
    private String k;

    @Override // com.ss.union.game.sdk.core.applog.b
    public String getVersion() {
        return "6.3.0";
    }

    public static AppLogManager getInstance() {
        return e;
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void init(Context context, String str, String str2, boolean z) {
        try {
            com.ss.union.game.sdk.core.applog.a.a.a("appLog init() -- start：" + str + ",appLogChannel:" + str2 + ",enablePlaySession:" + z);
            InitConfig initConfig = new InitConfig(str, str2);
            initConfig.setEnablePlay(z);
            initConfig.setAbEnable(true);
            initConfig.setAutoStart(true);
            initConfig.setLogger(new ILogger() { // from class: com.ss.union.game.sdk.core.applog.AppLogManager.1
                public void log(String str3, Throwable th) {
                    com.ss.union.game.sdk.core.applog.a.a.a("msg" + str3 + " throwable ：" + Log.getStackTraceString(th));
                }
            });
            AppLog.addDataObserver(new a());
            AppLog.setOaidObserver(new b());
            AppLog.init(context, initConfig);
            com.ss.union.game.sdk.core.applog.a.a.a("appLog init() -- done");
        } catch (Exception e2) {
            com.ss.union.game.sdk.core.applog.a.a.a("appLog init fail " + Log.getStackTraceString(e2));
        }
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public String getDid() {
        return AppLog.getDid();
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public String getIid() {
        return AppLog.getIid();
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public String getUserID() {
        return AppLog.getUserID();
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void onEventV3(String str, JSONObject jSONObject) {
        AppLog.onEventV3(str, jSONObject);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void onEventV3(String str, String str2) {
        try {
            AppLog.onEventV3(str, new JSONObject(str2));
        } catch (Exception e2) {
            com.ss.union.game.sdk.core.applog.a.a.a("onEventV3 StringToJSONObject fail:eventName:" + str + "extra:" + str2 + " error " + Log.getStackTraceString(e2));
        }
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void registerIdUpdateListener(AppLogIdUpdateListener appLogIdUpdateListener) {
        if (appLogIdUpdateListener == null) {
            return;
        }
        if (!TextUtils.isEmpty(AppLog.getDid()) && !TextUtils.isEmpty(AppLog.getIid())) {
            appLogIdUpdateListener.onIdChanged(AppLog.getDid(), AppLog.getIid());
        } else {
            this.f.add(appLogIdUpdateListener);
        }
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void unRegisterIdUpdateListener(AppLogIdUpdateListener appLogIdUpdateListener) {
        if (appLogIdUpdateListener == null) {
            return;
        }
        this.f.remove(appLogIdUpdateListener);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void registerOaidListener(AppLogOaidListener appLogOaidListener) {
        this.g.add(appLogOaidListener);
        if (TextUtils.isEmpty(this.i)) {
            return;
        }
        a(this.i);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void unRegisterOaidListener(AppLogOaidListener appLogOaidListener) {
        this.g.remove(appLogOaidListener);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void registerAbConfigGetListener(AppLogAbConfigGetListener appLogAbConfigGetListener) {
        if (appLogAbConfigGetListener == null) {
            return;
        }
        this.h.add(appLogAbConfigGetListener);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void unRegisterAbConfigGetListener(AppLogAbConfigGetListener appLogAbConfigGetListener) {
        if (appLogAbConfigGetListener == null) {
            return;
        }
        this.h.remove(appLogAbConfigGetListener);
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void updateUserInfo(String str, String str2, boolean z, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("open_id", str);
            }
            jSONObject.put("is_adult", z ? 1 : 0);
            jSONObject.put(b, str3);
            HashMap map = new HashMap();
            map.put(d, jSONObject);
            AppLog.setHeaderInfo(map);
        } catch (Throwable th) {
            com.ss.union.game.sdk.core.applog.a.a.a("用户成年信息AppLog同步失败 " + Log.getStackTraceString(th));
        }
    }

    @Override // com.ss.union.game.sdk.core.applog.b
    public void updateDeviceAdultInfo(boolean z, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("is_adult", z ? 1 : 0);
            jSONObject.put(b, str);
            HashMap map = new HashMap();
            map.put(d, jSONObject);
            AppLog.setHeaderInfo(map);
        } catch (Throwable th) {
            com.ss.union.game.sdk.core.applog.a.a.a("设备成年信息AppLog同步失败 " + Log.getStackTraceString(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.f.size() <= 0) {
            return;
        }
        AppLogOaidListener[] appLogOaidListenerArr = (AppLogOaidListener[]) this.g.toArray(new AppLogOaidListener[0]);
        this.g.clear();
        for (AppLogOaidListener appLogOaidListener : appLogOaidListenerArr) {
            appLogOaidListener.onOaidLoaded(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f.size() <= 0) {
            return;
        }
        if (AppLog.getDid().equals(this.j) && AppLog.getIid().equals(this.k)) {
            return;
        }
        this.j = AppLog.getDid();
        this.k = AppLog.getIid();
        for (AppLogIdUpdateListener appLogIdUpdateListener : (AppLogIdUpdateListener[]) this.f.toArray(new AppLogIdUpdateListener[0])) {
            appLogIdUpdateListener.onIdChanged(AppLog.getDid(), AppLog.getIid());
        }
    }

    class b implements IOaidObserver {
        b() {
        }

        public void onOaidLoaded(IOaidObserver.Oaid oaid) {
            com.ss.union.game.sdk.core.applog.a.a.a("oaid: " + oaid.id);
            if (oaid == null || TextUtils.isEmpty(oaid.id)) {
                return;
            }
            AppLogManager.this.i = oaid.id;
            AppLogManager.this.a(oaid.id);
        }
    }

    class a implements IDataObserver {
        public void onAbVidsChange(String str, String str2) {
        }

        public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
        }

        a() {
        }

        public void onIdLoaded(String str, String str2, String str3) {
            com.ss.union.game.sdk.core.applog.a.a.a("onIdLoaded did:" + str + " iid:" + str2 + " ssid:" + str3);
            if (TextUtils.isEmpty(AppLog.getDid()) || TextUtils.isEmpty(AppLog.getIid())) {
                return;
            }
            AppLogManager.this.a();
        }

        public void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
            com.ss.union.game.sdk.core.applog.a.a.a("changed: " + z + " onIdChanged did:" + str + " newDid:" + str2 + " iid:" + str3 + " newIid:" + str4 + " ssid:" + str5 + " newSsid:" + str6);
            if (TextUtils.isEmpty(AppLog.getDid()) || TextUtils.isEmpty(AppLog.getIid())) {
                return;
            }
            AppLogManager.this.a();
        }

        public void onRemoteAbConfigGet(boolean z, JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            Iterator it = AppLogManager.this.h.iterator();
            while (it.hasNext()) {
                ((AppLogAbConfigGetListener) it.next()).onRemoteAbConfigGet(z, jSONObject.toString());
            }
        }
    }
}
