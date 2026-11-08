package com.huawei.updatesdk.service.appmgr.bean;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.updatesdk.a.a.c.a.a;
import com.huawei.updatesdk.a.a.c.d;
import com.huawei.updatesdk.a.a.c.g;
import com.huawei.updatesdk.service.a.c;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c {
    public static final String APIMETHOD = "client.updateCheck";
    public static final int DEFAULT_UPGRADE_RESULT = 0;
    public static final int FULL_UPGRADE_RESULT = 1;
    public static final int INSTALL_CHECK_DEFAULT = 0;
    private static final int PARSE_IS_REMOVABLE_PREINSTALLED_APK = 33554432;
    public static final int PRE_DOWNLOAD_CLOSE = 0;
    private static final String TAG = "UpgradeRequest";
    private static final int TYPE_NOT_PREINSTALL = 0;
    private static final int TYPE_PREINSTALL = 2;
    private static final int TYPE_PREINSTALL_REMOVABLE = 1;
    private String agVersion_;
    private String buildNumber_;
    private String density_;
    private com.huawei.updatesdk.a.a.c.a.a deviceSpecParams_;
    private String firmwareVersion_;
    private int gmsSupport_;
    private int mapleVer_;
    private String packageName_;
    private String phoneType_;
    private C0014a pkgInfo_;
    private String resolution_;
    private String serviceCountry_;
    private int versionCode_;
    private String version_;
    private int isFirstLaunch_ = 0;
    private int emuiApiLevel_ = 0;
    private int installCheck_ = 0;
    private int isFullUpgrade_ = 0;
    private int getSafeGame_ = 1;
    private int supportMaple_ = 0;

    /* renamed from: com.huawei.updatesdk.service.appmgr.bean.a$a, reason: collision with other inner class name */
    public static class C0014a extends com.huawei.updatesdk.a.b.d.a.b {
        private List<b> params_;

        public void a(List<b> list) {
            this.params_ = list;
        }
    }

    public static class b extends com.huawei.updatesdk.a.b.d.a.b {
        private static final String FILE_SHA_KEY = "fileshakey";
        private static final String PACKAGE_KEY = "packagekey";
        private String fSha2_;
        private int isPre_;
        private int maple_;
        private String oldVersion_;
        private String package_;
        private String sSha2_;
        private int targetSdkVersion_;
        private int versionCode_;

        public b() {
        }

        public b(PackageInfo packageInfo) throws Throwable {
            this.package_ = packageInfo.packageName;
            this.versionCode_ = packageInfo.versionCode;
            this.oldVersion_ = packageInfo.versionName == null ? "null" : packageInfo.versionName;
            this.targetSdkVersion_ = packageInfo.applicationInfo.targetSdkVersion;
            this.isPre_ = a.b(packageInfo);
            this.maple_ = com.huawei.updatesdk.service.e.c.b(this.package_);
            if (packageInfo.signatures != null) {
                this.sSha2_ = g.c(com.huawei.updatesdk.a.a.c.a.a(g.d(packageInfo.signatures[0].toCharsString())));
            }
            String str = PACKAGE_KEY + this.package_;
            String str2 = FILE_SHA_KEY + this.package_;
            if (TextUtils.isEmpty(packageInfo.applicationInfo.sourceDir)) {
                this.fSha2_ = null;
                com.huawei.updatesdk.service.a.a.a().d(str);
                com.huawei.updatesdk.service.a.a.a().d(str2);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.lastUpdateTime);
            sb.append(packageInfo.versionCode);
            sb.append(this.package_);
            boolean z = !TextUtils.equals(sb.toString(), com.huawei.updatesdk.service.a.a.a().c(str));
            if (z) {
                com.huawei.updatesdk.service.a.a.a().a(str, sb.toString());
            }
            String strC = com.huawei.updatesdk.service.a.a.a().c(str2);
            if (TextUtils.isEmpty(strC) || z) {
                strC = d.a(packageInfo.applicationInfo.sourceDir, AaidIdConstant.SIGNATURE_SHA256);
                com.huawei.updatesdk.service.a.a.a().a(str2, strC);
            }
            this.fSha2_ = strC;
        }
    }

    public a() {
        Context contextB = com.huawei.updatesdk.a.b.a.a.a().b();
        b(APIMETHOD);
        c("1.2");
        e(com.huawei.updatesdk.a.a.c.a.b.e());
        n(com.huawei.updatesdk.a.a.c.a.b.b());
        g(com.huawei.updatesdk.a.a.c.a.b.i(contextB));
        h(com.huawei.updatesdk.a.a.c.a.b.a());
        i(Build.MODEL);
        f(com.huawei.updatesdk.a.a.c.a.b.a(contextB));
        e(com.huawei.updatesdk.a.a.c.a.b.f(contextB));
        f(com.huawei.updatesdk.a.a.c.a.b.e(contextB) ? 1 : 0);
        j(com.huawei.updatesdk.a.a.c.a.b.d(contextB));
        k(com.huawei.updatesdk.a.b.a.a.a().b().getPackageName());
        g(com.huawei.updatesdk.service.e.b.a().b());
        l(com.huawei.updatesdk.service.a.a.a().c());
        c(com.huawei.updatesdk.a.a.c.a.b.f().g());
        d(com.huawei.updatesdk.a.a.c.a.b.f().h());
        this.deviceSpecParams_ = new a.C0011a(contextB).a(true).a();
        m(com.huawei.updatesdk.a.a.c.a.b.h(contextB));
    }

    public static a a(List<PackageInfo> list) {
        a aVar = new a();
        C0014a c0014a = new C0014a();
        aVar.a(c0014a);
        ArrayList arrayList = new ArrayList();
        c0014a.a(arrayList);
        Iterator<PackageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new b(it.next()));
        }
        return aVar;
    }

    private static boolean a(ApplicationInfo applicationInfo) throws ClassNotFoundException {
        String str;
        StringBuilder sb;
        String string;
        int i = applicationInfo.flags;
        Integer numA = com.huawei.updatesdk.service.e.c.a();
        if (numA != null && (i & numA.intValue()) != 0) {
            return true;
        }
        Field fieldB = com.huawei.updatesdk.service.e.c.b();
        if (fieldB == null) {
            return false;
        }
        try {
            return (fieldB.getInt(applicationInfo) & PARSE_IS_REMOVABLE_PREINSTALLED_APK) != 0;
        } catch (IllegalAccessException e) {
            str = TAG;
            sb = new StringBuilder();
            sb.append("can not get hwflags");
            string = e.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, sb.toString());
            return false;
        } catch (IllegalArgumentException e2) {
            str = TAG;
            sb = new StringBuilder();
            sb.append("can not get hwflags");
            string = e2.toString();
            sb.append(string);
            com.huawei.updatesdk.a.a.b.a.a.a.a(str, sb.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(PackageInfo packageInfo) {
        if ((packageInfo.applicationInfo.flags & 1) == 0) {
            return 0;
        }
        return a(packageInfo.applicationInfo) ? 1 : 2;
    }

    public static a d(String str) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = str;
        packageInfo.versionName = "1.0";
        packageInfo.versionCode = 1;
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.targetSdkVersion = 19;
        packageInfo.applicationInfo = applicationInfo;
        ArrayList arrayList = new ArrayList();
        arrayList.add(packageInfo);
        a aVarA = a(arrayList);
        aVarA.b(1);
        return aVarA;
    }

    public void a(int i) {
        this.installCheck_ = i;
    }

    public void a(C0014a c0014a) {
        this.pkgInfo_ = c0014a;
    }

    public void b(int i) {
        this.isFullUpgrade_ = i;
    }

    public void c(int i) {
        this.supportMaple_ = i;
    }

    public void d(int i) {
        this.mapleVer_ = i;
    }

    public void e(int i) {
        this.versionCode_ = i;
    }

    public void e(String str) {
        this.firmwareVersion_ = str;
    }

    public void f(int i) {
        this.gmsSupport_ = i;
    }

    public void f(String str) {
        this.density_ = str;
    }

    public void g(int i) {
        this.emuiApiLevel_ = i;
    }

    public void g(String str) {
        this.version_ = str;
    }

    public void h(String str) {
        this.buildNumber_ = str;
    }

    public void i(String str) {
        this.phoneType_ = str;
    }

    public void j(String str) {
        this.resolution_ = str;
    }

    public void k(String str) {
        this.packageName_ = str;
    }

    public void l(String str) {
        this.serviceCountry_ = str;
    }

    public void m(String str) {
        this.agVersion_ = str;
    }
}
