package com.vivo.push.c;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.umeng.message.entity.UMessage;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.y;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class l extends o {

    public interface a {
        void a();

        void b();
    }

    public l(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        if (gVar == null) {
            com.vivo.push.util.n.a("OnNotificationArrivedTask", "command is null");
            return;
        }
        boolean zIsEnablePush = ClientConfigManagerImpl.getInstance(this.a).isEnablePush();
        final com.vivo.push.b.q qVar = (com.vivo.push.b.q) gVar;
        final String strValueOf = String.valueOf(qVar.e);
        if (!com.vivo.push.util.r.a(this.a, this.a.getPackageName(), "com.vivo.pushservice.action.RECEIVE")) {
            a(strValueOf, 2101);
            return;
        }
        com.vivo.push.d.a().a(new com.vivo.push.b.h(String.valueOf(qVar.e)));
        com.vivo.push.util.n.d("OnNotificationArrivedTask", "PushMessageReceiver " + this.a.getPackageName() + " isEnablePush :" + zIsEnablePush);
        if (!zIsEnablePush) {
            a(strValueOf, DownloadErrorCode.ERROR_THREAD_INTERRUPT);
            return;
        }
        if (com.vivo.push.d.a().f && !a(y.d(this.a), qVar.c(), qVar.d)) {
            a(strValueOf, DownloadErrorCode.ERROR_ARGUMENT);
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) this.a.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            if (notificationManager != null && !notificationManager.areNotificationsEnabled()) {
                com.vivo.push.util.n.b("OnNotificationArrivedTask", "pkg name : " + this.a.getPackageName() + " notify switch is false");
                com.vivo.push.util.n.b(this.a, "通知开关关闭，导致通知无法展示，请到设置页打开应用通知开关");
                a(strValueOf, 2104);
                return;
            }
            if (Build.VERSION.SDK_INT >= 26 && notificationManager != null) {
                try {
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel(NotifyAdapterUtil.PRIMARY_CHANNEL);
                    if (notificationChannel != null && notificationChannel.getImportance() == 0) {
                        com.vivo.push.util.n.b("OnNotificationArrivedTask", "pkg name : " + this.a.getPackageName() + " notify channel switch is false");
                        com.vivo.push.util.n.b(this.a, "通知通道开关关闭，导致通知无法展示，请到设置页打开应用通知开关");
                        a(strValueOf, 2121);
                        return;
                    }
                } catch (Exception unused) {
                    com.vivo.push.util.n.b("OnNotificationArrivedTask", "判断通知通道出现系统错误");
                }
            }
        }
        final InsideNotificationItem insideNotificationItemB = qVar.b();
        if (insideNotificationItemB != null) {
            com.vivo.push.util.n.d("OnNotificationArrivedTask", "targetType is " + insideNotificationItemB.getTargetType() + " ; target is " + insideNotificationItemB.getTragetContent());
            com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.l.1
                @Override // java.lang.Runnable
                public final void run() throws PackageManager.NameNotFoundException {
                    char c;
                    if (l.this.c.onNotificationMessageArrived(l.this.a, com.vivo.push.util.o.a(insideNotificationItemB))) {
                        com.vivo.push.util.n.b("OnNotificationArrivedTask", "pkg name : " + l.this.a.getPackageName() + " 应用主动拦截通知");
                        com.vivo.push.util.n.b(l.this.a, "应用主动拦截通知，导致通知无法展示，如需打开请在onNotificationMessageArrived中返回false");
                        l.this.a(strValueOf, 2120);
                        return;
                    }
                    com.vivo.push.util.j jVar = new com.vivo.push.util.j(l.this.a, insideNotificationItemB, qVar.e, l.this.c.isAllowNet(l.this.a), new a() { // from class: com.vivo.push.c.l.1.1
                        @Override // com.vivo.push.c.l.a
                        public final void a() throws PackageManager.NameNotFoundException {
                            l.a(l.this, strValueOf);
                        }

                        @Override // com.vivo.push.c.l.a
                        public final void b() throws PackageManager.NameNotFoundException {
                            com.vivo.push.util.n.b("OnNotificationArrivedTask", "pkg name : " + l.this.a.getPackageName() + " 通知展示失败");
                            com.vivo.push.util.n.b(l.this.a, "系统错误导致通知展示失败");
                            l.this.a(strValueOf, 2119);
                        }
                    });
                    boolean zIsShowBigPicOnMobileNet = insideNotificationItemB.isShowBigPicOnMobileNet();
                    String purePicUrl = insideNotificationItemB.getPurePicUrl();
                    if (TextUtils.isEmpty(purePicUrl)) {
                        purePicUrl = insideNotificationItemB.getCoverUrl();
                    }
                    if (!TextUtils.isEmpty(purePicUrl)) {
                        com.vivo.push.util.n.c("OnNotificationArrivedTask", "showCode=" + zIsShowBigPicOnMobileNet);
                        if (!zIsShowBigPicOnMobileNet) {
                            com.vivo.push.util.n.a(l.this.a, "mobile net unshow");
                            NetworkInfo networkInfoA = com.vivo.push.util.p.a(l.this.a);
                            if (networkInfoA != null && networkInfoA.getState() == NetworkInfo.State.CONNECTED) {
                                int type = networkInfoA.getType();
                                c = type == 1 ? (char) 2 : type == 0 ? (char) 1 : (char) 3;
                            } else {
                                c = 0;
                            }
                            if (c == 1) {
                                purePicUrl = null;
                                insideNotificationItemB.clearCoverUrl();
                                insideNotificationItemB.clearPurePicUrl();
                            }
                        } else {
                            com.vivo.push.util.n.a(l.this.a, "mobile net show");
                        }
                    }
                    jVar.execute(insideNotificationItemB.getIconUrl(), purePicUrl);
                }
            });
            return;
        }
        com.vivo.push.util.n.a("OnNotificationArrivedTask", "notify is null");
        com.vivo.push.util.n.c(this.a, "通知内容为空，" + qVar.e);
        a(strValueOf, 1027);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) throws PackageManager.NameNotFoundException {
        HashMap map = new HashMap();
        map.put("messageID", str);
        String strB = y.b(this.a, this.a.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("remoteAppId", strB);
        }
        com.vivo.push.util.d.a(i, map);
    }

    static /* synthetic */ void a(l lVar, String str) throws PackageManager.NameNotFoundException {
        HashMap map = new HashMap();
        map.put("srt", "1");
        map.put("message_id", str);
        String strB = y.b(lVar.a, lVar.a.getPackageName());
        if (!TextUtils.isEmpty(strB)) {
            map.put("app_id", strB);
        }
        map.put("type", "1");
        map.put("dtp", "1");
        com.vivo.push.util.d.a(6L, map);
    }
}
