package com.xiaomi.mipush.sdk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ab implements Runnable {
    final /* synthetic */ NotificationClickedActivity a;

    ab(NotificationClickedActivity notificationClickedActivity) {
        this.a = notificationClickedActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.channel.commonutils.logger.b.m22a("clicked activity finish by timeout.");
        this.a.finish();
    }
}
