package com.ss.union.game.sdk.core.base.event;

import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.union.game.sdk.common.util.AppUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d {
    private static final int a = 20;
    private static final int b = 5;
    private static final int c = 60;
    private static boolean d;
    private static final Runnable e = new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.d.1
        @Override // java.lang.Runnable
        public void run() {
            g.a("定时上报事件开始>>>>>");
            if (AppUtils.isDebug(GlobalApplicationUtils.getContext())) {
                g.a("剩余事件数量: " + e.a().c());
            }
            if (PageStater.a().handle() == -201) {
                boolean unused = d.d = false;
            } else {
                TeaSafeThread.getInst().ensureTeaThreadLiteDelay(d.e, 60000L);
            }
        }
    };
    private static volatile boolean f = false;
    private static volatile c g = new c();
    private static final Runnable h = new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.d.2
        @Override // java.lang.Runnable
        public void run() {
            g.a("兜底上报事件开始>>>>>");
            boolean unused = d.f = false;
            d.e();
        }
    };

    d() {
    }

    public static void a() {
        c cVar = g;
        g = new c();
        e.a().a(cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        INetUploadStrategy iNetUploadStrategyA = PageStater.a();
        if (iNetUploadStrategyA != null) {
            a();
            iNetUploadStrategyA.handle();
        }
    }

    public static void a(b bVar) {
        g.a(bVar);
        a(60L);
        if (g.a() >= 20) {
            e();
        } else {
            if (f) {
                return;
            }
            f = true;
            TeaSafeThread.getInst().ensureTeaThreadLiteDelay(h, AppLinkMonitor.DELAY_TIME);
        }
    }

    public static void a(final long j) {
        if (d) {
            return;
        }
        d = true;
        TeaSafeThread.getInst().ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.d.3
            @Override // java.lang.Runnable
            public void run() {
                TeaSafeThread.getInst().ensureTeaThreadLiteDelay(d.e, j * 1000);
            }
        });
    }

    static void b() {
        e();
    }
}
