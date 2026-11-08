package com.ss.android.push.daemon;

import android.content.Context;
import android.os.Process;
import com.ss.android.push.daemon.b;
import com.ss.android.push.daemon.e;
import com.ss.android.push.daemon.f;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements b.InterfaceC0035b, d {
    private b a;
    private b.InterfaceC0035b b;
    private Context c;
    private final String d = "d_permit";
    private final String e = "permitted";
    private BufferedReader f;

    public a(b bVar) {
        this.a = bVar;
        if (bVar != null) {
            this.b = this.a.c;
            this.a.c = this;
        }
    }

    @Override // com.ss.android.push.daemon.d
    public void a(Context context) {
        d(context);
    }

    @Override // com.ss.android.push.daemon.d
    public void a(f.b bVar) {
        f.a(bVar);
    }

    private void d(Context context) {
        try {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("DaemonClient", "initDaemon");
            }
            this.c = context.getApplicationContext();
            if (e(context) && this.a != null) {
                String strB = b();
                context.getPackageName();
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("DaemonClient", "processName = " + strB);
                    com.bytedance.common.push.utility.a.b("DaemonClient", "mConfigurations.PERSISTENT_CONFIG.PROCESS_NAME = " + this.a.a.a);
                    com.bytedance.common.push.utility.a.b("DaemonClient", "mConfigurations.DAEMON_ASSISTANT_CONFIG.PROCESS_NAME = " + this.a.b.a);
                }
                if (strB.endsWith(this.a.a.a)) {
                    e.a.a().a(context, this.a);
                } else if (strB.endsWith(this.a.b.a)) {
                    e.a.a().b(context, this.a);
                }
                c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String b() throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int i = bufferedReader.read();
                    if (i <= 0) {
                        break;
                    }
                    sb.append((char) i);
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("Process", "get processName = " + sb.toString());
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (Exception unused) {
                }
                return string;
            } catch (Throwable unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            th = th;
        }
    }

    private void c() throws IOException {
        if (this.f != null) {
            try {
                this.f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f = null;
        }
    }

    private boolean e(Context context) {
        return context.getSharedPreferences("d_permit", 0).getBoolean("permitted", true);
    }

    @Override // com.ss.android.push.daemon.b.InterfaceC0035b
    public void b(Context context) {
        if (this.b != null) {
            this.b.b(context);
        }
    }

    @Override // com.ss.android.push.daemon.b.InterfaceC0035b
    public void c(Context context) {
        if (this.b != null) {
            this.b.c(context);
        }
    }

    @Override // com.ss.android.push.daemon.b.InterfaceC0035b
    public void a() {
        if (this.c != null) {
            d(this.c);
        }
        if (this.b != null) {
            this.b.a();
        }
    }
}
