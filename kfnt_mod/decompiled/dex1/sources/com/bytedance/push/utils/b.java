package com.bytedance.push.utils;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobService;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    private static class a implements Handler.Callback {

        /* renamed from: a, reason: collision with root package name */
        Handler f4991a;

        /* renamed from: b, reason: collision with root package name */
        Handler.Callback f4992b;

        public a(Handler handler, Handler.Callback callback) {
            this.f4991a = handler;
            this.f4992b = callback;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message != null) {
                try {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("PushJobService", "handleMsg what = " + message.what);
                    }
                    if (!(this.f4992b != null ? this.f4992b.handleMessage(message) : false) && this.f4991a != null) {
                        this.f4991a.handleMessage(message);
                    }
                    return true;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (this.f4992b != null) {
                return this.f4992b.handleMessage(message);
            }
            return false;
        }
    }

    @TargetApi(21)
    public static void a(Service service) {
        com.bytedance.common.push.utility.d.a aVarA;
        try {
            if (service instanceof JobService) {
                JobService jobService = (JobService) service;
                if (com.ss.android.message.a.b.c(jobService) || Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT >= 26 || (aVarA = com.bytedance.common.push.utility.d.a.a(jobService)) == null) {
                    return;
                }
                aVarA.a("ensureHandler");
                Handler handler = (Handler) aVarA.a("mHandler", Class.forName("android.app.job.JobService$JobHandler")).a();
                if (handler != null) {
                    com.bytedance.common.push.utility.d.a aVarA2 = com.bytedance.common.push.utility.d.a.a(handler);
                    aVarA2.a("mCallback", new a(handler, (Handler.Callback) aVarA2.a("mCallback", Handler.Callback.class).a()));
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("PushJobService", "tryHackJobHandler success");
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
