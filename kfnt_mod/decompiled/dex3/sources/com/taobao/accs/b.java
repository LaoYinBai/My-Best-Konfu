package com.taobao.accs;

import android.app.Notification;
import com.taobao.accs.ChannelService;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements Runnable {
    final /* synthetic */ ChannelService.KernelService a;

    b(ChannelService.KernelService kernelService) {
        this.a = kernelService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            ChannelService channelService = ChannelService.getInstance();
            int i = this.a.b.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).applicationInfo.icon;
            if (i != 0) {
                Notification.Builder builder = new Notification.Builder(this.a.b);
                builder.setSmallIcon(i);
                builder.setContentText("正在运行…");
                channelService.startForeground(9371, builder.build());
                Notification.Builder builder2 = new Notification.Builder(this.a.b);
                builder2.setSmallIcon(i);
                builder2.setContentText("正在运行…");
                ChannelService.KernelService.a.startForeground(9371, builder2.build());
                ChannelService.KernelService.a.stopForeground(true);
            }
            ChannelService.KernelService.a.stopSelf();
        } catch (Throwable th) {
            ALog.e("ChannelService", " onStartCommand run", th, new Object[0]);
        }
    }
}
