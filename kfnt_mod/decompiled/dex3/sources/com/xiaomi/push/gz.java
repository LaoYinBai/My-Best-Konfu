package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class gz {

    static class a {
        fc a;

        /* renamed from: a, reason: collision with other field name */
        String f455a;

        a() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Throwable] */
    static a a(Exception exc) {
        m372a(exc);
        boolean z = exc instanceof ga;
        Exception excA = exc;
        if (z) {
            ga gaVar = (ga) exc;
            excA = exc;
            if (gaVar.a() != null) {
                excA = gaVar.a();
            }
        }
        a aVar = new a();
        String message = excA.getMessage();
        if (excA.getCause() != null) {
            message = excA.getCause().getMessage();
        }
        String str = excA.getClass().getSimpleName() + Constants.COLON_SEPARATOR + message;
        int iA = fr.a(excA);
        if (iA != 0) {
            aVar.a = fc.a(fc.GSLB_REQUEST_SUCCESS.a() + iA);
        }
        if (aVar.a == null) {
            aVar.a = fc.GSLB_TCP_ERR_OTHER;
        }
        if (aVar.a == fc.GSLB_TCP_ERR_OTHER) {
            aVar.f455a = str;
        }
        return aVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static void m372a(Exception exc) {
        if (exc == null) {
            throw new NullPointerException();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083  */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.xiaomi.push.gz.a b(java.lang.Exception r5) {
        /*
            m372a(r5)
            boolean r0 = r5 instanceof com.xiaomi.push.ga
            if (r0 == 0) goto L14
            r0 = r5
            com.xiaomi.push.ga r0 = (com.xiaomi.push.ga) r0
            java.lang.Throwable r1 = r0.a()
            if (r1 == 0) goto L14
            java.lang.Throwable r5 = r0.a()
        L14:
            com.xiaomi.push.gz$a r0 = new com.xiaomi.push.gz$a
            r0.<init>()
            java.lang.String r1 = r5.getMessage()
            java.lang.Throwable r2 = r5.getCause()
            if (r2 == 0) goto L2b
            java.lang.Throwable r1 = r5.getCause()
            java.lang.String r1 = r1.getMessage()
        L2b:
            int r2 = com.xiaomi.push.fr.a(r5)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r3.append(r4)
            java.lang.String r4 = ":"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            if (r2 == 0) goto L6d
            com.xiaomi.push.fc r3 = com.xiaomi.push.fc.CONN_SUCCESS
            int r3 = r3.a()
            int r3 = r3 + r2
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.a(r3)
            r0.a = r2
            com.xiaomi.push.fc r2 = r0.a
            com.xiaomi.push.fc r3 = com.xiaomi.push.fc.CONN_BOSH_ERR
            if (r2 != r3) goto L71
            java.lang.Throwable r5 = r5.getCause()
            if (r5 == 0) goto L71
            boolean r5 = r5 instanceof java.net.UnknownHostException
            if (r5 == 0) goto L71
            com.xiaomi.push.fc r5 = com.xiaomi.push.fc.CONN_BOSH_UNKNOWNHOST
            goto L6f
        L6d:
            com.xiaomi.push.fc r5 = com.xiaomi.push.fc.CONN_XMPP_ERR
        L6f:
            r0.a = r5
        L71:
            com.xiaomi.push.fc r5 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CONN_TCP_ERR_OTHER
            if (r5 == r2) goto L83
            com.xiaomi.push.fc r5 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CONN_XMPP_ERR
            if (r5 == r2) goto L83
            com.xiaomi.push.fc r5 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CONN_BOSH_ERR
            if (r5 != r2) goto L85
        L83:
            r0.f455a = r1
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gz.b(java.lang.Exception):com.xiaomi.push.gz$a");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.xiaomi.push.gz.a c(java.lang.Exception r4) {
        /*
            m372a(r4)
            boolean r0 = r4 instanceof com.xiaomi.push.ga
            if (r0 == 0) goto L14
            r0 = r4
            com.xiaomi.push.ga r0 = (com.xiaomi.push.ga) r0
            java.lang.Throwable r1 = r0.a()
            if (r1 == 0) goto L14
            java.lang.Throwable r4 = r0.a()
        L14:
            com.xiaomi.push.gz$a r0 = new com.xiaomi.push.gz$a
            r0.<init>()
            java.lang.String r1 = r4.getMessage()
            java.lang.Throwable r2 = r4.getCause()
            if (r2 == 0) goto L2b
            java.lang.Throwable r1 = r4.getCause()
            java.lang.String r1 = r1.getMessage()
        L2b:
            int r2 = com.xiaomi.push.fr.a(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r3.append(r4)
            java.lang.String r4 = ":"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = r3.toString()
            r3 = 105(0x69, float:1.47E-43)
            if (r2 == r3) goto L77
            r3 = 199(0xc7, float:2.79E-43)
            if (r2 == r3) goto L74
            r3 = 499(0x1f3, float:6.99E-43)
            if (r2 == r3) goto L65
            switch(r2) {
                case 109: goto L62;
                case 110: goto L5f;
                default: goto L5a;
            }
        L5a:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_XMPP_ERR
        L5c:
            r0.a = r1
            goto L7a
        L5f:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_TCP_BROKEN_PIPE
            goto L5c
        L62:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_TCP_CONNRESET
            goto L5c
        L65:
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.BIND_BOSH_ERR
            r0.a = r2
            java.lang.String r2 = "Terminal binding condition encountered: item-not-found"
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L7a
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_BOSH_ITEM_NOT_FOUND
            goto L5c
        L74:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_TCP_ERR
            goto L5c
        L77:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.BIND_TCP_READ_TIMEOUT
            goto L5c
        L7a:
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.BIND_TCP_ERR
            if (r1 == r2) goto L8c
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.BIND_XMPP_ERR
            if (r1 == r2) goto L8c
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.BIND_BOSH_ERR
            if (r1 != r2) goto L8e
        L8c:
            r0.f455a = r4
        L8e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gz.c(java.lang.Exception):com.xiaomi.push.gz$a");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.xiaomi.push.gz.a d(java.lang.Exception r4) {
        /*
            m372a(r4)
            boolean r0 = r4 instanceof com.xiaomi.push.ga
            if (r0 == 0) goto L14
            r0 = r4
            com.xiaomi.push.ga r0 = (com.xiaomi.push.ga) r0
            java.lang.Throwable r1 = r0.a()
            if (r1 == 0) goto L14
            java.lang.Throwable r4 = r0.a()
        L14:
            com.xiaomi.push.gz$a r0 = new com.xiaomi.push.gz$a
            r0.<init>()
            java.lang.String r1 = r4.getMessage()
            int r2 = com.xiaomi.push.fr.a(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r3.append(r4)
            java.lang.String r4 = ":"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = r3.toString()
            r3 = 105(0x69, float:1.47E-43)
            if (r2 == r3) goto L69
            r3 = 199(0xc7, float:2.79E-43)
            if (r2 == r3) goto L66
            r3 = 499(0x1f3, float:6.99E-43)
            if (r2 == r3) goto L57
            switch(r2) {
                case 109: goto L54;
                case 110: goto L51;
                default: goto L4c;
            }
        L4c:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_XMPPEXCEPTION
        L4e:
            r0.a = r1
            goto L6c
        L51:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_TCP_BROKEN_PIPE
            goto L4e
        L54:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_TCP_CONNRESET
            goto L4e
        L57:
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CHANNEL_BOSH_EXCEPTION
            r0.a = r2
            java.lang.String r2 = "Terminal binding condition encountered: item-not-found"
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L6c
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_BOSH_ITEMNOTFIND
            goto L4e
        L66:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_TCP_ERR
            goto L4e
        L69:
            com.xiaomi.push.fc r1 = com.xiaomi.push.fc.CHANNEL_TCP_READTIMEOUT
            goto L4e
        L6c:
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CHANNEL_TCP_ERR
            if (r1 == r2) goto L7e
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CHANNEL_XMPPEXCEPTION
            if (r1 == r2) goto L7e
            com.xiaomi.push.fc r1 = r0.a
            com.xiaomi.push.fc r2 = com.xiaomi.push.fc.CHANNEL_BOSH_EXCEPTION
            if (r1 != r2) goto L80
        L7e:
            r0.f455a = r4
        L80:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gz.d(java.lang.Exception):com.xiaomi.push.gz$a");
    }
}
