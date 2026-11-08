package com.qq.e.comm.managers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.qq.e.ads.dfa.GDTAppDialogClickListener;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.managers.devtool.DevTools;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.plugin.e;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.SystemUtil;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.common.Constants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GDTADManager implements IGDTAdManager {
    public static final ExecutorService INIT_EXECUTOR = Executors.newSingleThreadExecutor();
    private volatile Boolean a;
    private volatile Context b;
    private volatile SM c;
    private volatile PM d;
    private volatile DevTools e;
    private volatile APPStatus f;
    private volatile DeviceStatus g;
    private volatile String h;
    private e i;

    class a extends Thread {
        final /* synthetic */ long a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, long j) {
            super(str);
            this.a = j;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            GDTADManager.a(GDTADManager.this, this.a);
        }
    }

    private static final class b {
        private static GDTADManager a = new GDTADManager(null);
    }

    private GDTADManager() {
        this.a = Boolean.FALSE;
    }

    /* synthetic */ GDTADManager(a aVar) {
        this();
    }

    private void a(Context context, String str) {
        long jNanoTime = System.nanoTime();
        this.h = SystemUtil.getProcessName(context);
        this.b = context.getApplicationContext();
        this.c = new SM(this.b);
        this.d = new PM(this.b, this.i);
        this.f = new APPStatus(str, this.b);
        this.g = new DeviceStatus(this.b);
        if (Build.VERSION.SDK_INT > 7) {
            new a("GDT_ACTIVATE_LAUNCH", jNanoTime).start();
        }
    }

    static void a(GDTADManager gDTADManager, long j) {
        if (gDTADManager == null) {
            throw null;
        }
        com.qq.e.comm.b.a.a().a(gDTADManager.b, gDTADManager.c, gDTADManager.d, gDTADManager.g, gDTADManager.f, j);
    }

    public static GDTADManager getInstance() {
        return b.a;
    }

    public JSONObject buildS2SSBaseInfo() throws JSONException {
        if (!isInitialized()) {
            return null;
        }
        SM sm = this.c;
        JSONObject jSONObject = new JSONObject();
        if (sm != null) {
            jSONObject.putOpt("suid", sm.getSuid());
            jSONObject.putOpt(Constants.KEY_SID, sm.getSid());
        }
        jSONObject.put(PointCategory.APP, com.qq.e.comm.net.a.a(this.f));
        jSONObject.put("c", com.qq.e.comm.net.a.a(this.g));
        jSONObject.put("sdk", com.qq.e.comm.net.a.a(this.d));
        return jSONObject;
    }

    public String getADActivityClazz() {
        return CustomPkgConstants.getADActivityName();
    }

    public Context getAppContext() {
        return this.b;
    }

    public APPStatus getAppStatus() {
        return this.f;
    }

    @Override // com.qq.e.comm.managers.IGDTAdManager
    public String getBuyerId() {
        if (!isInitialized()) {
            return "";
        }
        try {
            return this.d.getPOFactory().getBuyerId();
        } catch (Exception e) {
            GDTLogger.e("SDK 初始化异常", e);
            return "";
        }
    }

    @Override // com.qq.e.comm.managers.IGDTAdManager
    public DevTools getDevTools() {
        if (this.e == null) {
            this.e = new DevTools();
        }
        return this.e;
    }

    public DeviceStatus getDeviceStatus() {
        return this.g;
    }

    public String getDownLoadClazz() {
        return CustomPkgConstants.getDownLoadServiceName();
    }

    public String getLandscapeADActivityClazz() {
        return CustomPkgConstants.getLandscapeADActivityName();
    }

    public PM getPM() {
        return this.d;
    }

    public String getPortraitADActivityClazz() {
        return CustomPkgConstants.getPortraitADActivityName();
    }

    public String getProcessName() {
        return this.h;
    }

    public String getRewardvideoLandscapeADActivityClazz() {
        return CustomPkgConstants.getRewardvideoLandscapeADActivityName();
    }

    public String getRewardvideoPortraitADActivityClazz() {
        return CustomPkgConstants.getRewardvideoPortraitADActivityName();
    }

    @Override // com.qq.e.comm.managers.IGDTAdManager
    public String getSDKInfo(String str) {
        if (!isInitialized()) {
            return "";
        }
        try {
            return this.d.getPOFactory().getSDKInfo(str);
        } catch (Exception e) {
            GDTLogger.e("SDK 初始化异常", e);
            return "";
        }
    }

    public SM getSM() {
        return this.c;
    }

    public synchronized boolean initWith(Context context, String str) {
        if (Build.VERSION.SDK_INT < 14) {
            GDTLogger.e("GDTADManager初始化错误，SDK不支持Android 4.0以下版本");
            return false;
        }
        if (this.a.booleanValue()) {
            return true;
        }
        if (context == null || TextUtils.isEmpty(str)) {
            GDTLogger.e("GDTADManager初始化错误，context和appId不能为空");
            return false;
        }
        try {
            a(context, str);
            this.a = Boolean.TRUE;
            return true;
        } catch (Throwable th) {
            GDTLogger.e("GDTADManager初始化错误", th);
            return false;
        }
    }

    public boolean isInitialized() {
        if (this.a != null && this.a.booleanValue()) {
            return true;
        }
        GDTLogger.e("SDK 尚未初始化，请在 Application 中调用 GDTAdSdk.init() 初始化");
        return false;
    }

    public void setPluginLoadListener(e eVar) {
        this.i = eVar;
    }

    @Override // com.qq.e.comm.managers.IGDTAdManager
    public int showOpenOrInstallAppDialog(GDTAppDialogClickListener gDTAppDialogClickListener) {
        if (!isInitialized()) {
            return 0;
        }
        try {
            return this.d.getPOFactory().showOpenOrInstallAppDialog(gDTAppDialogClickListener);
        } catch (Exception e) {
            GDTLogger.e("SDK 初始化异常", e);
            return 0;
        }
    }
}
