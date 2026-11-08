package com.meizu.cloud.pushsdk.b.e;

import com.meizu.cloud.pushsdk.b.c.e;
import com.meizu.cloud.pushsdk.b.c.i;
import com.meizu.cloud.pushsdk.b.c.k;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static String a;

    public static k a(com.meizu.cloud.pushsdk.b.a.b bVar) throws com.meizu.cloud.pushsdk.b.b.a {
        try {
            i.a aVarA = new i.a().a(bVar.e());
            a(aVarA, bVar);
            switch (bVar.d()) {
                case 0:
                    aVarA = aVarA.a();
                    break;
                case 1:
                    aVarA = aVarA.a(bVar.m());
                    break;
                case 2:
                    aVarA = aVarA.c(bVar.m());
                    break;
                case 3:
                    aVarA = aVarA.b(bVar.m());
                    break;
                case 4:
                    aVarA = aVarA.b();
                    break;
                case 5:
                    aVarA = aVarA.d(bVar.m());
                    break;
            }
            i iVarC = aVarA.c();
            bVar.a(new e());
            return bVar.l().a(iVarC);
        } catch (IOException e) {
            throw new com.meizu.cloud.pushsdk.b.b.a(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.meizu.cloud.pushsdk.b.c.i.a r2, com.meizu.cloud.pushsdk.b.a.b r3) {
        /*
            java.lang.String r0 = r3.h()
            if (r0 == 0) goto L10
            java.lang.String r0 = "User-Agent"
            java.lang.String r1 = r3.h()
        Lc:
            r2.a(r0, r1)
            goto L1e
        L10:
            java.lang.String r0 = com.meizu.cloud.pushsdk.b.e.a.a
            if (r0 == 0) goto L1e
            java.lang.String r0 = com.meizu.cloud.pushsdk.b.e.a.a
            r3.a(r0)
            java.lang.String r0 = "User-Agent"
            java.lang.String r1 = com.meizu.cloud.pushsdk.b.e.a.a
            goto Lc
        L1e:
            com.meizu.cloud.pushsdk.b.c.c r0 = r3.o()
            if (r0 == 0) goto L42
            r2.a(r0)
            java.lang.String r1 = r3.h()
            if (r1 == 0) goto L42
            java.util.Set r0 = r0.b()
            java.lang.String r1 = "User-Agent"
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L42
            java.lang.String r0 = "User-Agent"
            java.lang.String r3 = r3.h()
            r2.a(r0, r3)
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.b.e.a.a(com.meizu.cloud.pushsdk.b.c.i$a, com.meizu.cloud.pushsdk.b.a.b):void");
    }

    public static k b(com.meizu.cloud.pushsdk.b.a.b bVar) throws com.meizu.cloud.pushsdk.b.b.a {
        try {
            i.a aVarA = new i.a().a(bVar.e());
            a(aVarA, bVar);
            i iVarC = aVarA.a().c();
            bVar.a(new e());
            k kVarA = bVar.l().a(iVarC);
            com.meizu.cloud.pushsdk.b.i.b.a(kVarA, bVar.j(), bVar.k());
            return kVarA;
        } catch (IOException e) {
            try {
                File file = new File(bVar.j() + File.separator + bVar.k());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            throw new com.meizu.cloud.pushsdk.b.b.a(e);
        }
    }

    public static k c(com.meizu.cloud.pushsdk.b.a.b bVar) throws com.meizu.cloud.pushsdk.b.b.a {
        try {
            i.a aVarA = new i.a().a(bVar.e());
            a(aVarA, bVar);
            i iVarC = aVarA.a(new b(bVar.n(), bVar.i())).c();
            bVar.a(new e());
            return bVar.l().a(iVarC);
        } catch (IOException e) {
            throw new com.meizu.cloud.pushsdk.b.b.a(e);
        }
    }
}
