package com.ss.android.push.daemon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.bytedance.push.c.o;
import com.bytedance.push.i;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.message.MessageReceiver;
import com.ss.android.push.daemon.b;
import com.ss.android.push.daemon.f;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {

    @SuppressLint({"CI_StaticFieldLeak", "StaticFieldLeak"})
    private static volatile c a;
    private static com.ss.android.push.daemon.b d;
    private Context b;
    private d c;
    private AtomicBoolean e = new AtomicBoolean(false);
    private a f;

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        return a;
    }

    private c(Context context) {
        this.b = context;
        try {
            if (d == null) {
                d = c();
            }
            this.c = new com.ss.android.push.daemon.a(d);
            try {
                this.c.a(new f.b() { // from class: com.ss.android.push.daemon.c.1
                    @Override // com.ss.android.push.daemon.f.b
                    public boolean a(String str) {
                        o oVar = i.j().d().u;
                        if (oVar == null) {
                            return false;
                        }
                        oVar.a(str);
                        return true;
                    }
                });
            } catch (Throwable unused) {
            }
            this.f = new a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        if (Build.VERSION.SDK_INT >= 21 && Boolean.valueOf(PushSetting.getInstance().isAllowPushDaemonMonitor()).booleanValue() && d() && !b()) {
            try {
                if (this.e.getAndSet(true)) {
                    return;
                }
                com.bytedance.common.push.utility.a.b("DaemonManager", "initDaemon: 初始化  双进程保活");
                this.c.a(this.b);
            } catch (Throwable th) {
                this.e.set(false);
                th.printStackTrace();
            }
        }
    }

    private com.ss.android.push.daemon.b c() {
        return new com.ss.android.push.daemon.b(new b.a(this.b.getPackageName() + ":push", PushProcessService.class.getCanonicalName(), MessageReceiver.class.getCanonicalName()), new b.a(this.b.getPackageName() + ":pushservice", PushService.class.getCanonicalName(), PushReceiver.class.getCanonicalName()), new b());
    }

    class b implements b.InterfaceC0035b {
        b() {
        }

        @Override // com.ss.android.push.daemon.b.InterfaceC0035b
        public void b(Context context) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("DaemonManager", "onPersistentStart");
            }
            if (c.this.f != null) {
                c.this.f.c(c.this.b);
            }
        }

        @Override // com.ss.android.push.daemon.b.InterfaceC0035b
        public void c(Context context) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("DaemonManager", "onDaemonAssistantStart");
            }
        }

        @Override // com.ss.android.push.daemon.b.InterfaceC0035b
        public void a() {
            if (c.this.f != null) {
                c.this.f.d(c.this.b);
            }
        }
    }

    class a {
        long a = 0;
        long b = 0;
        long c = 0;

        a(Context context) {
            a(context);
        }

        void a(Context context) {
            try {
                String pushDaemonMonitor = PushSetting.getInstance().getPushDaemonMonitor();
                if (TextUtils.isEmpty(pushDaemonMonitor)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(pushDaemonMonitor);
                this.b = jSONObject.optLong(PointCategory.START, 0L);
                if (DateUtils.isToday(this.b)) {
                    this.a = jSONObject.optLong("duration", 0L);
                    this.c = jSONObject.optLong("end", 0L);
                } else {
                    PushSetting.getInstance().setPushDaemonMonitorResult(pushDaemonMonitor);
                    this.b = 0L;
                    this.c = 0L;
                    this.a = 0L;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        void b(Context context) {
            try {
                if (this.b > 0 && this.c > 0) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(PointCategory.START, this.b);
                    jSONObject.put("end", this.c);
                    jSONObject.put("duration", this.a);
                    PushSetting.getInstance().setPushDaemonMonitor(jSONObject.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        void c(Context context) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!DateUtils.isToday(this.b)) {
                this.a = 0L;
            }
            this.b = jCurrentTimeMillis;
            this.c = jCurrentTimeMillis;
            b(context);
        }

        void d(Context context) {
            this.c = System.currentTimeMillis();
            if (this.c >= this.b) {
                this.a += this.c - this.b;
            }
            b(context);
        }
    }

    public boolean b() {
        return PushSetting.getInstance().isShutPushNotifyEnable();
    }

    private boolean d() {
        return PushSetting.getInstance().isAllowNetwork();
    }
}
