package com.bytedance.push.h;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    static final Object f4825a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final com.bytedance.push.third.c f4826b;
    private Context d;

    /* renamed from: c, reason: collision with root package name */
    private int f4827c = 10;
    private final Handler e = new Handler(com.ss.android.message.d.a().b()) { // from class: com.bytedance.push.h.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            a.this.a(message);
        }
    };
    private int f = 0;

    public static void a(Context context, com.bytedance.push.third.c cVar) {
        com.bytedance.common.push.utility.b.b.a(new a(context, cVar));
    }

    public static String a(Context context, int i) {
        com.bytedance.push.e.c cVarA = b.a(context, i);
        if (cVarA == null) {
            return null;
        }
        return cVarA.f4794b;
    }

    private a(Context context, com.bytedance.push.third.c cVar) {
        this.d = context;
        this.f4826b = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x01ce A[Catch: Throwable -> 0x01e4, TryCatch #2 {Throwable -> 0x01e4, blocks: (B:5:0x0012, B:7:0x0018, B:9:0x0037, B:11:0x003d, B:13:0x0043, B:14:0x0061, B:16:0x009a, B:18:0x00c0, B:20:0x00c6, B:22:0x00d6, B:24:0x00fc, B:45:0x01ce, B:46:0x01d3, B:43:0x01c9, B:38:0x018e, B:47:0x01d6, B:40:0x01a1), top: B:63:0x0012, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3 A[Catch: Throwable -> 0x01e4, TryCatch #2 {Throwable -> 0x01e4, blocks: (B:5:0x0012, B:7:0x0018, B:9:0x0037, B:11:0x003d, B:13:0x0043, B:14:0x0061, B:16:0x009a, B:18:0x00c0, B:20:0x00c6, B:22:0x00d6, B:24:0x00fc, B:45:0x01ce, B:46:0x01d3, B:43:0x01c9, B:38:0x018e, B:47:0x01d6, B:40:0x01a1), top: B:63:0x0012, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.bytedance.push.third.c r19) {
        /*
            Method dump skipped, instructions count: 508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.h.a.a(com.bytedance.push.third.c):void");
    }

    private boolean a(int i) {
        return com.bytedance.push.third.e.c(i);
    }

    public void a(Message message) {
        if (message.what == 1) {
            b(message);
        }
    }

    private void b(Message message) {
        try {
            boolean z = false;
            switch (message.arg1) {
                case 11:
                    z = true;
                    break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Send token ");
            sb.append(z ? "success" : AccountMonitorConstants.CommonParameter.RESULT_FAIL);
            com.bytedance.push.utils.e.a("SendTokenTask", sb.toString());
            if (z) {
                return;
            }
            a((String) message.obj);
        } catch (Throwable unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d == null || this.f4826b == null) {
            return;
        }
        synchronized (f4825a) {
            a(this.f4826b);
        }
    }

    private void a(String str) {
        try {
            this.f++;
            if (this.f > 3 || TextUtils.isEmpty(str)) {
                return;
            }
            com.bytedance.push.utils.e.b("SendTokenTask", "token fail, token = " + str + ". retry = " + this.f);
            com.ss.android.message.d.a().a(new Runnable() { // from class: com.bytedance.push.h.a.2
                @Override // java.lang.Runnable
                public void run() {
                    a.this.run();
                }
            }, TimeUnit.SECONDS.toMillis(4L) * ((long) this.f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
