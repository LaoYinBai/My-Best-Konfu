package com.huawei.a.b;

import android.content.Context;
import com.huawei.a.a.b.k.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {

    public static class a {
        com.huawei.a.a.b.c.a.c a;
        com.huawei.a.a.b.c.a.c b;
        Context c;
        String d;

        public a(Context context) {
            if (context != null) {
                this.c = context.getApplicationContext();
            }
            this.a = new com.huawei.a.a.b.c.a.c();
            this.b = new com.huawei.a.a.b.c.a.c();
        }

        public a a(int i, String str) {
            com.huawei.a.a.b.c.a.c cVar;
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.setCollectURL(int type,String collectURL) is execute.TYPE : " + i);
            if (!f.a(str)) {
                str = "";
            }
            switch (i) {
                case 0:
                    cVar = this.a;
                    cVar.b(str);
                    return this;
                case 1:
                    cVar = this.b;
                    cVar.b(str);
                    return this;
                default:
                    com.huawei.a.a.b.e.a.c("hmsSdk", "Builder.setCollectURL(int type,String collectURL): invalid type!");
                    return this;
            }
        }

        public a a(String str) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.setAppID is execute");
            this.d = str;
            return this;
        }

        public void a() {
            if (this.c == null) {
                com.huawei.a.a.b.e.a.d("hmsSdk", "analyticsConf create(): context is null,create failed!");
                return;
            }
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.create() is execute.");
            com.huawei.a.a.f.c cVar = new com.huawei.a.a.f.c("_hms_config_tag");
            cVar.c(new com.huawei.a.a.b.c.a.c(this.a));
            cVar.a(new com.huawei.a.a.b.c.a.c(this.b));
            com.huawei.a.a.f.a.a().a(this.c);
            com.huawei.a.a.f.b.a().a(this.c);
            c.a().a(cVar);
            com.huawei.a.a.f.a.a().b(this.d);
        }

        public void a(boolean z) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.refresh() is execute.");
            com.huawei.a.a.b.c.a.c cVar = new com.huawei.a.a.b.c.a.c(this.b);
            com.huawei.a.a.b.c.a.c cVar2 = new com.huawei.a.a.b.c.a.c(this.a);
            com.huawei.a.a.f.c cVarB = c.a().b();
            if (cVarB == null) {
                com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstance.Builder.Refresh(): calling refresh before create. TAG: _hms_config_tag has no instance. ");
                return;
            }
            cVarB.a(1, cVar);
            cVarB.a(0, cVar2);
            if (this.d != null) {
                com.huawei.a.a.f.a.a().b(this.d);
            }
            if (z) {
                com.huawei.a.a.f.a.a().a("_hms_config_tag");
            }
        }

        @Deprecated
        public a b(boolean z) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.setEnableUDID(boolean isReportUDID) is execute.");
            this.a.b().c(z);
            this.b.b().c(z);
            return this;
        }

        @Deprecated
        public a c(boolean z) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.setEnableSN(boolean isReportSN) is execute.");
            this.a.b().b(z);
            this.b.b().b(z);
            return this;
        }

        @Deprecated
        public a d(boolean z) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "Builder.setEnableImei(boolean isReportAndroidImei) is execute.");
            this.a.b().a(z);
            this.b.b().a(z);
            return this;
        }
    }
}
