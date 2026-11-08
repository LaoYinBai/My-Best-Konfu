package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.taobao.accs.data.Message;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dv extends dx {
    private String a;

    public dv(Context context, int i, String str) {
        super(context, i);
        this.a = str;
    }

    private String[] a() {
        if (TextUtils.isEmpty(this.a)) {
            return null;
        }
        String strB = bj.b(this.a);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB.contains(Constants.ACCEPT_TIME_SEPARATOR_SP) ? strB.split(Constants.ACCEPT_TIME_SEPARATOR_SP) : new String[]{strB};
    }

    @Override // com.xiaomi.push.dx
    /* renamed from: a, reason: collision with other method in class */
    public hl mo205a() {
        return hl.AppIsInstalled;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "24";
    }

    @Override // com.xiaomi.push.dx
    public String b() throws PackageManager.NameNotFoundException {
        String installerPackageName;
        String[] strArrA = a();
        if (strArrA == null || strArrA.length <= 0) {
            return null;
        }
        PackageManager packageManager = this.f249a.getPackageManager();
        StringBuilder sb = new StringBuilder();
        for (String str : strArrA) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, Message.FLAG_REQ_BIT1);
                if (packageInfo != null) {
                    if (sb.length() > 0) {
                        sb.append(";");
                    }
                    try {
                        installerPackageName = packageManager.getInstallerPackageName(str);
                    } catch (IllegalArgumentException unused) {
                        installerPackageName = null;
                    }
                    if (TextUtils.isEmpty(installerPackageName)) {
                        installerPackageName = "null";
                    }
                    sb.append(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.packageName);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.versionName);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.versionCode);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.firstInstallTime);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.lastUpdateTime);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(installerPackageName);
                }
            } catch (Exception unused2) {
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }
}
