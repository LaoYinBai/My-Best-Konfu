package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.common.util.AGCUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.NetWorkUtil;
import com.huawei.hms.utils.PackageManagerHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements IBridgeActivityDelegate {
    public WeakReference<Activity> a;
    public IBridgeActivityDelegate b;
    protected UpdateBean c = null;
    protected b d = null;
    protected boolean e = false;
    protected int f = -1;
    protected String g = null;
    protected String h = null;
    protected int i = 0;
    protected String j = null;

    abstract void a();

    public void a(b bVar) {
    }

    abstract void a(Class<? extends b> cls);

    public void b(b bVar) {
    }

    public static String a(int i) {
        if (i == 0) {
            return k.class.getName();
        }
        if (i != 2) {
            switch (i) {
                case 4:
                    return g.class.getName();
                case 5:
                    return h.class.getName();
                case 6:
                    return l.class.getName();
                default:
                    return "";
            }
        }
        return f.class.getName();
    }

    protected Activity b() {
        if (this.a == null) {
            return null;
        }
        return this.a.get();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        this.a = new WeakReference<>(activity);
        if (this.c == null) {
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            this.c = (UpdateBean) intent.getSerializableExtra(BridgeActivity.EXTRA_DELEGATE_UPDATE_INFO);
            if (this.c == null) {
                return;
            }
        }
        this.g = this.c.b();
        this.h = this.c.getClientAppName();
        this.i = this.c.c();
        this.j = this.c.d();
        this.b = null;
        this.e = false;
        this.f = -1;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        this.a = null;
        c();
        if (!this.e || this.b == null) {
            return;
        }
        this.b.onBridgeActivityDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        if (this.e && this.b != null) {
            this.b.onBridgeConfigurationChanged();
        } else {
            if (this.d == null) {
                return;
            }
            Class<?> cls = this.d.getClass();
            this.d.c();
            this.d = null;
            a((Class<? extends b>) cls);
        }
    }

    protected void c() {
        if (this.d == null) {
            return;
        }
        try {
            this.d.c();
            this.d = null;
        } catch (IllegalStateException e) {
            HMSLog.e("AbsUpdateWizard", "In dismissDialog, Failed to dismiss the dialog." + e.getMessage());
        }
    }

    protected void a(int i, int i2) {
        if (!SystemUtils.isChinaROM()) {
            HMSLog.i("AbsUpdateWizard", "not ChinaROM ");
            return;
        }
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            return;
        }
        int packageVersionCode = new PackageManagerHelper(activityB).getPackageVersionCode(this.g);
        HashMap map = new HashMap();
        map.put("package", activityB.getPackageName());
        map.put(HiAnalyticsConstant.BI_KEY_TARGET_PACKAGE, this.g);
        map.put(HiAnalyticsConstant.BI_KEY_TARGET_VER, String.valueOf(packageVersionCode));
        map.put(HiAnalyticsConstant.BI_KEY_SDK_VER, String.valueOf(40004300));
        map.put("app_id", AGCUtils.getAppId(activityB));
        map.put(HiAnalyticsConstant.BI_KEY_TRIGGER_API, "core.connnect");
        map.put(HiAnalyticsConstant.BI_KEY_UPDATE_TYPE, String.valueOf(i2));
        map.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, String.valueOf(NetWorkUtil.getNetworkType(activityB)));
        map.put("result", c(i, i2));
        HiAnalyticsUtils.getInstance().onEvent(activityB, HiAnalyticsConstant.BI_TYPE_UPDATE, map);
    }

    private String c(int i, int i2) {
        String strValueOf = String.valueOf(i);
        if (i2 == 0) {
            return "0000" + strValueOf;
        }
        if (i2 == 2) {
            return "2000" + strValueOf;
        }
        switch (i2) {
            case 4:
                return "6000" + strValueOf;
            case 5:
                return "5000" + strValueOf;
            case 6:
                return "4000" + strValueOf;
            default:
                return strValueOf;
        }
    }

    protected boolean a(boolean z) {
        Activity activityB = b();
        if (activityB == null) {
            return false;
        }
        ArrayList typeList = this.c.getTypeList();
        if (typeList.size() > 0) {
            typeList.remove(0);
        }
        if (this.b == null) {
            a(typeList);
        }
        if (this.b == null) {
            return false;
        }
        this.e = true;
        this.c.setTypeList(typeList);
        this.c.setNeedConfirm(z);
        this.b.onBridgeActivityCreate(activityB);
        return true;
    }

    private void a(ArrayList arrayList) {
        String strA = (arrayList == null || arrayList.size() <= 0) ? null : a(((Integer) arrayList.get(0)).intValue());
        if (strA == null) {
            return;
        }
        try {
            this.b = (IBridgeActivityDelegate) Class.forName(strA).asSubclass(IBridgeActivityDelegate.class).newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            HMSLog.e("AbsUpdateWizard", "getBridgeActivityDelegate error" + e.getMessage());
        }
    }

    protected void b(int i, int i2) {
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            return;
        }
        a(i, i2);
        Intent intent = new Intent();
        intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, getClass().getName());
        intent.putExtra(BridgeActivity.EXTRA_RESULT, i);
        activityB.setResult(-1, intent);
        activityB.finish();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        if (!this.e || this.b == null) {
            return;
        }
        this.b.onKeyUp(i, keyEvent);
    }

    protected boolean a(String str, int i) {
        Activity activityB;
        return (TextUtils.isEmpty(str) || (activityB = b()) == null || activityB.isFinishing() || new PackageManagerHelper(activityB).getPackageVersionCode(str) < i) ? false : true;
    }
}
