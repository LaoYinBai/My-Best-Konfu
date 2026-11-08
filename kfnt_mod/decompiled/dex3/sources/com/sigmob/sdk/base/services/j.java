package com.sigmob.sdk.base.services;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j {
    private static a a;
    private static a b;
    private static a c;
    private static a d;

    public interface a {
        boolean b();

        void c();
    }

    public static synchronized a a(String str) {
        char c2 = 65535;
        int iHashCode = str.hashCode();
        if (iHashCode != -967662845) {
            if (iHashCode != 353926395) {
                if (iHashCode != 854806816) {
                    if (iHashCode == 1382229229 && str.equals("DownloadService")) {
                        c2 = 3;
                    }
                } else if (str.equals("LocationService")) {
                    c2 = 0;
                }
            } else if (str.equals("AppInstallService")) {
                c2 = 1;
            }
        } else if (str.equals("WifiScanService")) {
            c2 = 2;
        }
        switch (c2) {
            case 0:
                if (a != null) {
                    return a;
                }
                e eVar = new e();
                a = eVar;
                return eVar;
            case 1:
                if (b != null) {
                    return b;
                }
                b bVar = new b();
                b = bVar;
                return bVar;
            case 2:
                if (c != null) {
                    return c;
                }
                l lVar = new l();
                c = lVar;
                return lVar;
            case 3:
                if (d != null) {
                    return d;
                }
                d dVar = new d();
                d = dVar;
                return dVar;
            default:
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r2, boolean r3) {
        /*
            int r0 = r2.hashCode()
            r1 = -967662845(0xffffffffc652a303, float:-13480.753)
            if (r0 == r1) goto L37
            r1 = 353926395(0x15187cfb, float:3.0794748E-26)
            if (r0 == r1) goto L2d
            r1 = 854806816(0x32f35120, float:2.8325815E-8)
            if (r0 == r1) goto L23
            r1 = 1382229229(0x526324ed, float:2.4389427E11)
            if (r0 == r1) goto L19
            goto L41
        L19:
            java.lang.String r0 = "DownloadService"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L41
            r0 = 3
            goto L42
        L23:
            java.lang.String r0 = "LocationService"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L41
            r0 = 0
            goto L42
        L2d:
            java.lang.String r0 = "AppInstallService"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L41
            r0 = 1
            goto L42
        L37:
            java.lang.String r0 = "WifiScanService"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L41
            r0 = 2
            goto L42
        L41:
            r0 = -1
        L42:
            switch(r0) {
                case 0: goto L6e;
                case 1: goto L5a;
                case 2: goto L50;
                case 3: goto L46;
                default: goto L45;
            }
        L45:
            return
        L46:
            if (r3 == 0) goto L49
            goto L5c
        L49:
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.d
            if (r2 == 0) goto L88
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.d
            goto L6a
        L50:
            if (r3 == 0) goto L53
            goto L5c
        L53:
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.c
            if (r2 == 0) goto L88
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.c
            goto L6a
        L5a:
            if (r3 == 0) goto L64
        L5c:
            com.sigmob.sdk.base.services.j$a r2 = a(r2)
            r2.b()
            goto L88
        L64:
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.b
            if (r2 == 0) goto L88
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.b
        L6a:
            r2.c()
            goto L88
        L6e:
            if (r3 == 0) goto L78
            com.sigmob.sdk.base.services.j$a r2 = a(r2)
            r2.b()
            goto L81
        L78:
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.a
            if (r2 == 0) goto L81
            com.sigmob.sdk.base.services.j$a r2 = com.sigmob.sdk.base.services.j.a
            r2.c()
        L81:
            com.sigmob.sdk.common.a r2 = com.sigmob.sdk.common.a.n()
            r2.b(r3)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.services.j.a(java.lang.String, boolean):void");
    }
}
