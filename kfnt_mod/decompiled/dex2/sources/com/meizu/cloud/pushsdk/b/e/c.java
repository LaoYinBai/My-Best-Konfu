package com.meizu.cloud.pushsdk.b.e;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.meizu.cloud.pushsdk.b.a.e;
import com.meizu.cloud.pushsdk.b.c.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    public static <T> com.meizu.cloud.pushsdk.b.a.c<T> a(com.meizu.cloud.pushsdk.b.a.b bVar) {
        switch (bVar.g()) {
            case 0:
                return b(bVar);
            case 1:
                return c(bVar);
            case 2:
                return d(bVar);
            default:
                return new com.meizu.cloud.pushsdk.b.a.c<>(new com.meizu.cloud.pushsdk.b.b.a());
        }
    }

    private static <T> com.meizu.cloud.pushsdk.b.a.c<T> b(com.meizu.cloud.pushsdk.b.a.b bVar) throws Throwable {
        Throwable th;
        k kVarA;
        Exception e;
        com.meizu.cloud.pushsdk.b.b.a e2;
        try {
            try {
                kVarA = a.a(bVar);
                try {
                    if (kVarA == null) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a()));
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                        return cVar;
                    }
                    if (bVar.f() == e.OK_HTTP_RESPONSE) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar2 = new com.meizu.cloud.pushsdk.b.a.c<>(kVarA);
                        cVar2.a(kVarA);
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                        return cVar2;
                    }
                    if (kVarA.a() >= 400) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar3 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a(kVarA), bVar, kVarA.a()));
                        cVar3.a(kVarA);
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                        return cVar3;
                    }
                    com.meizu.cloud.pushsdk.b.a.c<T> cVarA = bVar.a(kVarA);
                    cVarA.a(kVarA);
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                    return cVarA;
                } catch (com.meizu.cloud.pushsdk.b.b.a e3) {
                    e2 = e3;
                    com.meizu.cloud.pushsdk.b.a.c<T> cVar4 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a(e2)));
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                    return cVar4;
                } catch (Exception e4) {
                    e = e4;
                    com.meizu.cloud.pushsdk.b.a.c<T> cVar5 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(e));
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarA, bVar);
                    return cVar5;
                }
            } catch (Throwable th2) {
                th = th2;
                com.meizu.cloud.pushsdk.b.i.a.a(null, bVar);
                throw th;
            }
        } catch (com.meizu.cloud.pushsdk.b.b.a e5) {
            kVarA = null;
            e2 = e5;
        } catch (Exception e6) {
            kVarA = null;
            e = e6;
        } catch (Throwable th3) {
            th = th3;
            com.meizu.cloud.pushsdk.b.i.a.a(null, bVar);
            throw th;
        }
    }

    private static <T> com.meizu.cloud.pushsdk.b.a.c<T> c(com.meizu.cloud.pushsdk.b.a.b bVar) {
        try {
            k kVarB = a.b(bVar);
            if (kVarB == null) {
                return new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a()));
            }
            if (kVarB.a() >= 400) {
                com.meizu.cloud.pushsdk.b.a.c<T> cVar = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a(kVarB), bVar, kVarB.a()));
                cVar.a(kVarB);
                return cVar;
            }
            com.meizu.cloud.pushsdk.b.a.c<T> cVar2 = new com.meizu.cloud.pushsdk.b.a.c<>(FirebaseAnalytics.Param.SUCCESS);
            cVar2.a(kVarB);
            return cVar2;
        } catch (com.meizu.cloud.pushsdk.b.b.a e) {
            return new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a(e)));
        } catch (Exception e2) {
            return new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(e2));
        }
    }

    private static <T> com.meizu.cloud.pushsdk.b.a.c<T> d(com.meizu.cloud.pushsdk.b.a.b bVar) throws Throwable {
        Throwable th;
        k kVarC;
        Exception e;
        com.meizu.cloud.pushsdk.b.b.a e2;
        try {
            try {
                kVarC = a.c(bVar);
                try {
                    if (kVarC == null) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a()));
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                        return cVar;
                    }
                    if (bVar.f() == e.OK_HTTP_RESPONSE) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar2 = new com.meizu.cloud.pushsdk.b.a.c<>(kVarC);
                        cVar2.a(kVarC);
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                        return cVar2;
                    }
                    if (kVarC.a() >= 400) {
                        com.meizu.cloud.pushsdk.b.a.c<T> cVar3 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(new com.meizu.cloud.pushsdk.b.b.a(kVarC), bVar, kVarC.a()));
                        cVar3.a(kVarC);
                        com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                        return cVar3;
                    }
                    com.meizu.cloud.pushsdk.b.a.c<T> cVarA = bVar.a(kVarC);
                    cVarA.a(kVarC);
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                    return cVarA;
                } catch (com.meizu.cloud.pushsdk.b.b.a e3) {
                    e2 = e3;
                    com.meizu.cloud.pushsdk.b.a.c<T> cVar4 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(e2));
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                    return cVar4;
                } catch (Exception e4) {
                    e = e4;
                    com.meizu.cloud.pushsdk.b.a.c<T> cVar5 = new com.meizu.cloud.pushsdk.b.a.c<>(com.meizu.cloud.pushsdk.b.i.b.a(e));
                    com.meizu.cloud.pushsdk.b.i.a.a(kVarC, bVar);
                    return cVar5;
                }
            } catch (Throwable th2) {
                th = th2;
                com.meizu.cloud.pushsdk.b.i.a.a(null, bVar);
                throw th;
            }
        } catch (com.meizu.cloud.pushsdk.b.b.a e5) {
            kVarC = null;
            e2 = e5;
        } catch (Exception e6) {
            kVarC = null;
            e = e6;
        } catch (Throwable th3) {
            th = th3;
            com.meizu.cloud.pushsdk.b.i.a.a(null, bVar);
            throw th;
        }
    }
}
