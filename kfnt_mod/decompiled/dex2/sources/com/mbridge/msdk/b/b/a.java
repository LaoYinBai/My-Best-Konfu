package com.mbridge.msdk.b.b;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import java.util.List;

/* compiled from: ActiveAppUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private final String a;
    private List<com.mbridge.msdk.foundation.entity.a> b;
    private boolean c;
    private Handler d;

    private a() {
        this.a = "ActiveAppUtil";
        this.c = false;
        this.d = new Handler() { // from class: com.mbridge.msdk.b.b.a.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                synchronized (a.this) {
                    Message messageObtain = Message.obtain();
                    switch (message.what) {
                        case 1:
                            a.a(a.this);
                            messageObtain.what = 1;
                            messageObtain.arg1 = message.arg1;
                            sendMessageDelayed(messageObtain, message.arg1);
                            break;
                        case 2:
                            messageObtain.what = 1;
                            messageObtain.arg1 = message.arg2;
                            sendMessageDelayed(messageObtain, message.arg1);
                            break;
                    }
                }
            }
        };
    }

    public static a a() {
        return C0103a.a;
    }

    /* compiled from: ActiveAppUtil.java */
    /* renamed from: com.mbridge.msdk.b.b.a$a, reason: collision with other inner class name */
    private static class C0103a {
        private static final a a = new a();
    }

    public final void b() {
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
        }
    }

    public final boolean c() {
        return this.c;
    }

    public final synchronized void d() {
        List<com.mbridge.msdk.foundation.entity.a> listAq;
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            if (aVarB.ao() == 2 || (listAq = aVarB.aq()) == null || listAq.size() == 0) {
                b();
            } else {
                this.b = listAq;
            }
        }
    }

    public final synchronized void e() {
        com.mbridge.msdk.b.a aVarB;
        if (com.mbridge.msdk.foundation.controller.a.f().j() == null) {
            return;
        }
        try {
            aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        } catch (Throwable th) {
            s.d("ActiveAppUtil", th.getMessage());
        }
        if (aVarB != null && aVarB.ao() != 2) {
            this.b = aVarB.aq();
            if (this.b != null && this.b.size() != 0) {
                long jLongValue = ((Long) y.b(com.mbridge.msdk.foundation.controller.a.f().j(), "active_last_time", 0L)).longValue();
                Message messageObtain = Message.obtain();
                if (jLongValue == 0) {
                    messageObtain.what = 1;
                    messageObtain.arg1 = aVarB.ap() * 1000;
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis() - jLongValue;
                    int iAp = aVarB.ap() * 1000;
                    long j = iAp;
                    if (jCurrentTimeMillis > j) {
                        messageObtain.what = 1;
                        messageObtain.arg1 = iAp;
                    } else {
                        messageObtain.what = 2;
                        messageObtain.arg1 = (int) (j - jCurrentTimeMillis);
                        messageObtain.arg2 = iAp;
                    }
                }
                this.d.sendMessage(messageObtain);
                this.c = true;
                s.d("ActiveAppUtil", "init");
            }
        }
    }

    static /* synthetic */ void a(a aVar) {
        if (com.mbridge.msdk.foundation.controller.a.f().j() == null) {
            return;
        }
        if (aVar.b == null || aVar.b.size() == 0) {
            aVar.b();
        }
        y.a(com.mbridge.msdk.foundation.controller.a.f().j(), "active_last_time", Long.valueOf(System.currentTimeMillis()));
        try {
            for (com.mbridge.msdk.foundation.entity.a aVar2 : aVar.b) {
                Intent intent = new Intent();
                if (aVar2.b().contains("service")) {
                    if (!TextUtils.isEmpty(aVar2.a()) && !TextUtils.isEmpty(aVar2.c())) {
                        intent.setComponent(new ComponentName(aVar2.a(), aVar2.c()));
                        if (!TextUtils.isEmpty(aVar2.d())) {
                            intent.setAction(aVar2.d());
                        }
                        if (Build.VERSION.SDK_INT >= 26) {
                            com.mbridge.msdk.foundation.controller.a.f().j().startForegroundService(intent);
                        } else {
                            com.mbridge.msdk.foundation.controller.a.f().j().startService(intent);
                        }
                    }
                    return;
                }
                if (aVar2.b().contains("broadcast")) {
                    if (!TextUtils.isEmpty(aVar2.a()) && !TextUtils.isEmpty(aVar2.c()) && !TextUtils.isEmpty(aVar2.d())) {
                        intent.setComponent(new ComponentName(aVar2.a(), aVar2.c()));
                        intent.setAction(aVar2.d());
                        com.mbridge.msdk.foundation.controller.a.f().j().sendBroadcast(intent);
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            s.d("ActiveAppUtil", th.getMessage());
        }
    }
}
