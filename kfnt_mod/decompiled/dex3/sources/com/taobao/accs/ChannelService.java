package com.taobao.accs;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.utils.RomVersionParamHelper;
import com.taobao.accs.base.BaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.t;
import com.umeng.message.entity.UMessage;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ChannelService extends BaseService {
    public static final int DEFAULT_FORGROUND_V = 21;
    static final int NOTIFY_ID = 9371;
    public static int SDK_VERSION_CODE = Constants.SDK_VERSION_CODE;
    public static final String SUPPORT_FOREGROUND_VERSION_KEY = "support_foreground_v";
    static final String TAG = "ChannelService";
    private static final String id = "accs_agoo_normal_channel";
    private static ChannelService mInstance;
    private boolean mFristStarted = true;
    private AtomicInteger notificationId = new AtomicInteger(0);
    private NotificationManager notificationManager;
    private ScheduledFuture scheduledFuture;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public static ChannelService getInstance() {
        return mInstance;
    }

    @Override // com.taobao.accs.base.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        GlobalClientInfo.a = getApplicationContext();
        mInstance = this;
        if (Build.VERSION.SDK_INT < 18) {
            try {
                startForeground(NOTIFY_ID, new Notification());
                return;
            } catch (Throwable th) {
                ALog.e(TAG, "ChannelService onCreate", th, new Object[0]);
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= 26 && t.d() && "com.taobao.taobao".equals(getApplicationContext().getPackageName())) {
            foreground();
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.bytedance.push.settings.PushOnlineSettings, java.lang.Object] */
    @Override // com.taobao.accs.base.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.mFristStarted) {
            this.mFristStarted = false;
            startKernel(getApplicationContext());
        }
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (SettingsManager.obtain(getApplicationContext(), PushOnlineSettings.class).needControlFlares() && RomVersionParamHelper.isMiui12()) {
            Log.d("MIUI12", "ControlFlares");
            return 2;
        }
        Log.d("MIUI12", "not ControlFlares");
        return iOnStartCommand;
    }

    @Override // com.taobao.accs.base.BaseService, android.app.Service
    public void onDestroy() {
        if (Build.VERSION.SDK_INT < 18) {
            try {
                stopForeground(true);
            } catch (Throwable th) {
                ALog.e(TAG, "ChannelService onDestroy", th, new Object[0]);
            }
        }
        stopKernel(getApplicationContext());
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        if (this.scheduledThreadPoolExecutor != null) {
            this.scheduledThreadPoolExecutor.shutdown();
        }
        super.onDestroy();
    }

    static void startKernel(Context context) {
        try {
            if (Build.VERSION.SDK_INT < getSupportForegroundVer(context)) {
                Intent intent = new Intent(context, (Class<?>) KernelService.class);
                intent.setPackage(context.getPackageName());
                context.startService(intent);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "startKernel", th, new Object[0]);
        }
    }

    static void stopKernel(Context context) {
        try {
            if (Build.VERSION.SDK_INT < getSupportForegroundVer(context)) {
                Intent intent = new Intent(context, (Class<?>) KernelService.class);
                intent.setPackage(context.getPackageName());
                context.stopService(intent);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "stopKernel", th, new Object[0]);
        }
    }

    static int getSupportForegroundVer(Context context) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(SUPPORT_FOREGROUND_VERSION_KEY, 21);
        } catch (Throwable th) {
            ALog.e(TAG, "getSupportForegroundVer fail:", th, com.ss.union.game.sdk.redemptionCode.a.p, SUPPORT_FOREGROUND_VERSION_KEY);
            return 21;
        }
    }

    public static class KernelService extends Service {
        private static KernelService a;
        private Context b;

        @Override // android.app.Service
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override // android.app.Service
        public void onCreate() {
            super.onCreate();
            a = this;
            this.b = getApplicationContext();
        }

        @Override // android.app.Service
        public int onStartCommand(Intent intent, int i, int i2) {
            try {
                ThreadPoolExecutorFactory.execute(new b(this));
            } catch (Throwable th) {
                ALog.e(ChannelService.TAG, " onStartCommand", th, new Object[0]);
            }
            return super.onStartCommand(intent, i, i2);
        }

        @Override // android.app.Service
        public void onDestroy() {
            try {
                stopForeground(true);
            } catch (Throwable th) {
                ALog.e(ChannelService.TAG, "onDestroy", th, new Object[0]);
            }
            a = null;
            super.onDestroy();
        }
    }

    @TargetApi(26)
    public void foreground() {
        ApplicationInfo applicationInfo;
        try {
            this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            this.notificationManager = (NotificationManager) mInstance.getApplicationContext().getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            try {
                applicationInfo = mInstance.getPackageManager().getApplicationInfo(mInstance.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                applicationInfo = null;
            }
            synchronized (ChannelService.class) {
                NotificationChannel notificationChannel = new NotificationChannel(id, "后台服务", 1);
                notificationChannel.setSound(null, null);
                notificationChannel.setShowBadge(false);
                notificationChannel.enableLights(false);
                notificationChannel.enableVibration(false);
                this.notificationManager.createNotificationChannel(notificationChannel);
                Notification.Builder builder = new Notification.Builder(mInstance, notificationChannel.getId());
                if (applicationInfo != null) {
                    builder.setSmallIcon(applicationInfo.icon);
                    builder.setContentText("正在运行…");
                }
                ALog.e(TAG, "start foreground", new Object[0]);
                if (this.scheduledFuture != null) {
                    this.scheduledFuture.cancel(true);
                }
                mInstance.startForeground(this.notificationId.incrementAndGet(), builder.build());
                this.scheduledFuture = this.scheduledThreadPoolExecutor.scheduleAtFixedRate(new a(this.notificationId.get()), 100L, 100L, TimeUnit.MILLISECONDS);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "start foreground error", th, new Object[0]);
        }
    }

    @TargetApi(26)
    class a implements Runnable {
        int a;
        private int c = 0;

        a(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ALog.i(ChannelService.TAG, "scan foreground notification times: ", Integer.valueOf(this.c));
                int i = this.c;
                this.c = i + 1;
                if (i < 100) {
                    for (StatusBarNotification statusBarNotification : ChannelService.this.notificationManager.getActiveNotifications()) {
                        if (statusBarNotification.getNotification() != null && ChannelService.id.equals(statusBarNotification.getNotification().getChannelId())) {
                            if (statusBarNotification.getId() == this.a) {
                                synchronized (ChannelService.class) {
                                    ChannelService.this.scheduledFuture.cancel(false);
                                    ALog.i(ChannelService.TAG, "find foreground notification try to delete it", new Object[0]);
                                    ChannelService.this.notificationManager.deleteNotificationChannel(ChannelService.id);
                                }
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                ALog.e(ChannelService.TAG, "schedule 10s, but not find foreground notification, try to stop foreground", new Object[0]);
                ChannelService.this.scheduledFuture.cancel(false);
                ChannelService.mInstance.stopForeground(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
