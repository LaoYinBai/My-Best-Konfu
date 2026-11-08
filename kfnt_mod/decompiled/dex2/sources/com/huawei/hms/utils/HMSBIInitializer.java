package com.huawei.hms.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.a.b.a;
import com.huawei.a.b.b;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.grs.IQueryUrlCallBack;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.countrycode.CountryCodeBean;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HMSBIInitializer {
    private static final Object a = new Object();
    private static final Object b = new Object();
    private static HMSBIInitializer c;
    private final Context d;
    private b.a e;

    private HMSBIInitializer(Context context) {
        this.d = context;
        this.e = new b.a(this.d);
    }

    public static HMSBIInitializer getInstance(Context context) {
        synchronized (a) {
            if (c == null) {
                c = new HMSBIInitializer(context);
            }
        }
        return c;
    }

    public void initBI() {
        synchronized (b) {
            boolean zB = a.b();
            HMSLog.i("HMSBIInitializer", "Builder->biInitFlag :" + zB);
            if (zB) {
                return;
            }
            boolean biSetting = getBiSetting(this.d);
            HMSLog.i("HMSBIInitializer", "Builder->biSetting :" + biSetting);
            if (biSetting) {
                return;
            }
            String strA = new CountryCodeBean(this.d, false).a();
            String upperCase = TextUtils.isEmpty(strA) ? "CN" : strA.toUpperCase(Locale.ENGLISH);
            if (GrsBaseInfo.CountryCodeSource.UNKNOWN.equalsIgnoreCase(upperCase)) {
                HMSLog.e("HMSBIInitializer", "Failed to get device issue country");
                return;
            }
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            grsBaseInfo.setIssueCountry(upperCase);
            new GrsClient(this.d, grsBaseInfo).ayncGetGrsUrl("com.huawei.cloud.opensdkhianalytics", "ROOT", new IQueryUrlCallBack() { // from class: com.huawei.hms.utils.HMSBIInitializer.1
                @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
                public void onCallBackSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    HMSBIInitializer.this.e.d(false).b(false).c(false).a(0, str).a(1, str).a("com.huawei.hwid").a();
                    HMSLog.i("HMSBIInitializer", "BI URL acquired successfully");
                }

                @Override // com.huawei.hms.framework.network.grs.IQueryUrlCallBack
                public void onCallBackFail(int i) {
                    HMSLog.e("HMSBIInitializer", "get grs failed, the errorcode is " + i);
                }
            });
        }
    }

    public static boolean getBiSetting(Context context) {
        if (context == null) {
            HMSLog.e("HMSBIInitializer", "In getBiSetting, context is null.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("HMSBIInitializer", "In getBiSetting, Failed to get 'PackageManager' instance.");
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getBoolean("com.huawei.hms.client.bi.setting");
            }
            HMSLog.i("HMSBIInitializer", "In getBiSetting, Failed to read meta data bisetting.");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.e("HMSBIInitializer", "In getBiSetting, Failed to read meta data bisetting.");
            return false;
        }
    }

    public boolean isInit() {
        return a.b();
    }
}
