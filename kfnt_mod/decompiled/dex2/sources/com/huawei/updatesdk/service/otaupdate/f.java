package com.huawei.updatesdk.service.otaupdate;

import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static String a = "com.huawei.appmarket";
    private static b b;

    private static class a implements com.huawei.updatesdk.a.b.d.a.a {
        private a() {
        }

        @Override // com.huawei.updatesdk.a.b.d.a.a
        public void a(com.huawei.updatesdk.a.b.d.a.c cVar, com.huawei.updatesdk.a.b.d.a.d dVar) {
        }

        @Override // com.huawei.updatesdk.a.b.d.a.a
        public void b(com.huawei.updatesdk.a.b.d.a.c cVar, com.huawei.updatesdk.a.b.d.a.d dVar) {
            if (!(dVar instanceof com.huawei.updatesdk.service.appmgr.bean.b)) {
                if (f.b != null) {
                    f.b.a(dVar.a());
                    return;
                }
                return;
            }
            com.huawei.updatesdk.service.appmgr.bean.b bVar = (com.huawei.updatesdk.service.appmgr.bean.b) dVar;
            if (dVar.a() != 0 || dVar.c() != 0) {
                if (f.b != null) {
                    f.b.b(dVar.a());
                }
            } else if ((bVar.list_ == null || bVar.list_.size() == 0) && (bVar.notRcmList_ == null || bVar.notRcmList_.size() == 0)) {
                if (f.b != null) {
                    f.b.a(dVar.a());
                }
            } else {
                ApkUpgradeInfo apkUpgradeInfoB = f.b(bVar.list_);
                if (f.b != null) {
                    f.b.a(apkUpgradeInfoB);
                }
            }
        }
    }

    public static void a(b bVar) {
        b = bVar;
    }

    public static void a(String str) {
        a = str;
        com.huawei.updatesdk.service.d.a.c.a(com.huawei.updatesdk.service.appmgr.bean.a.d(a), new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ApkUpgradeInfo b(List<ApkUpgradeInfo> list) {
        if (list == null) {
            return null;
        }
        for (ApkUpgradeInfo apkUpgradeInfo : list) {
            if (a != null && a.equals(apkUpgradeInfo.getPackage_())) {
                return apkUpgradeInfo;
            }
        }
        return null;
    }
}
