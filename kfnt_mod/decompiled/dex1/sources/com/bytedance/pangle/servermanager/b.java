package com.bytedance.pangle.servermanager;

import com.bytedance.pangle.c;
import com.bytedance.pangle.d;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4683a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f4684b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static final Object f4685c = new Object();
    private static final Map<String, Boolean> d = new ConcurrentHashMap();
    private static final Map<String, d> e = new ConcurrentHashMap();
    private static c f;

    public static d a(String str) {
        Boolean bool = d.get(str);
        if (bool == null || !bool.booleanValue()) {
            e.remove(str);
        }
        if (e.get(str) == null) {
            synchronized (f4684b) {
                d dVar = (d) a("service", str);
                if (dVar == null) {
                    ZeusLogger.e(ZeusLogger.TAG_SERVER, "getServiceManager failed!!!");
                    return null;
                }
                e.put(str, dVar);
            }
        }
        return e.get(str);
    }

    public static c a() {
        Boolean bool = d.get("main");
        if (bool == null || !bool.booleanValue()) {
            f = null;
        }
        if (f == null) {
            synchronized (f4685c) {
                c cVar = (c) a("package", "main");
                if (cVar == null) {
                    ZeusLogger.e(ZeusLogger.TAG_SERVER, "getPackageManager failed!!!");
                    return null;
                }
                f = cVar;
            }
        }
        return f;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0088 A[Catch: RemoteException -> 0x0092, TryCatch #0 {RemoteException -> 0x0092, blocks: (B:13:0x0050, B:25:0x0084, B:27:0x0088, B:29:0x008d, B:18:0x0070, B:21:0x007a), top: B:36:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008d A[Catch: RemoteException -> 0x0092, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0092, blocks: (B:13:0x0050, B:25:0x0084, B:27:0x0088, B:29:0x008d, B:18:0x0070, B:21:0x007a), top: B:36:0x0050 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.os.IInterface a(java.lang.String r5, final java.lang.String r6) throws android.os.RemoteException {
        /*
            java.util.HashMap r0 = com.bytedance.pangle.Zeus.getServerManagerHashMap()
            java.lang.Object r0 = r0.get(r6)
            android.content.pm.ProviderInfo r0 = (android.content.pm.ProviderInfo) r0
            if (r0 == 0) goto L9b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "content://"
            r1.<init>(r2)
            java.lang.String r0 = r0.authority
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.app.Application r1 = com.bytedance.pangle.Zeus.getAppApplication()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "query_binder"
            r3 = 0
            android.os.Bundle r0 = r1.call(r0, r2, r5, r3)
            if (r0 == 0) goto L47
            java.lang.Class<com.bytedance.pangle.servermanager.AbsServerManager> r1 = com.bytedance.pangle.servermanager.AbsServerManager.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            java.lang.String r1 = "binder"
            android.os.Parcelable r0 = r0.getParcelable(r1)
            com.bytedance.pangle.servermanager.a r0 = (com.bytedance.pangle.servermanager.a) r0
            if (r0 == 0) goto L47
            android.os.IBinder r0 = r0.f4682a
            goto L48
        L47:
            r0 = r3
        L48:
            if (r0 == 0) goto L9a
            boolean r1 = r0.isBinderAlive()
            if (r1 == 0) goto L9a
            com.bytedance.pangle.servermanager.b$1 r1 = new com.bytedance.pangle.servermanager.b$1     // Catch: android.os.RemoteException -> L92
            r1.<init>()     // Catch: android.os.RemoteException -> L92
            r2 = 0
            r0.linkToDeath(r1, r2)     // Catch: android.os.RemoteException -> L92
            java.util.Map<java.lang.String, java.lang.Boolean> r1 = com.bytedance.pangle.servermanager.b.d     // Catch: android.os.RemoteException -> L92
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch: android.os.RemoteException -> L92
            r1.put(r6, r4)     // Catch: android.os.RemoteException -> L92
            r6 = -1
            int r1 = r5.hashCode()     // Catch: android.os.RemoteException -> L92
            r4 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            if (r1 == r4) goto L7a
            r2 = 1984153269(0x7643c6b5, float:9.927033E32)
            if (r1 == r2) goto L70
            goto L83
        L70:
            java.lang.String r1 = "service"
            boolean r5 = r5.equals(r1)     // Catch: android.os.RemoteException -> L92
            if (r5 == 0) goto L83
            r2 = 1
            goto L84
        L7a:
            java.lang.String r1 = "package"
            boolean r5 = r5.equals(r1)     // Catch: android.os.RemoteException -> L92
            if (r5 == 0) goto L83
            goto L84
        L83:
            r2 = -1
        L84:
            switch(r2) {
                case 0: goto L8d;
                case 1: goto L88;
                default: goto L87;
            }     // Catch: android.os.RemoteException -> L92
        L87:
            goto L9a
        L88:
            com.bytedance.pangle.d r5 = com.bytedance.pangle.d.a.a(r0)     // Catch: android.os.RemoteException -> L92
            return r5
        L8d:
            com.bytedance.pangle.c r5 = com.bytedance.pangle.c.a.a(r0)     // Catch: android.os.RemoteException -> L92
            return r5
        L92:
            r5 = move-exception
            java.lang.String r6 = "Zeus/server"
            java.lang.String r0 = "generateServerManager failed."
            com.bytedance.pangle.log.ZeusLogger.e(r6, r0, r5)
        L9a:
            return r3
        L9b:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r0 = "宿主中没有找对对应进程的serverManager "
            java.lang.String r6 = r0.concat(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.servermanager.b.a(java.lang.String, java.lang.String):android.os.IInterface");
    }
}
