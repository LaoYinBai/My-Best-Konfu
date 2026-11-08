package com.mbridge.msdk.playercommon.exoplayer2.offline;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager;
import com.mbridge.msdk.playercommon.exoplayer2.scheduler.Requirements;
import com.mbridge.msdk.playercommon.exoplayer2.scheduler.RequirementsWatcher;
import com.mbridge.msdk.playercommon.exoplayer2.scheduler.Scheduler;
import com.mbridge.msdk.playercommon.exoplayer2.util.NotificationUtil;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class DownloadService extends Service {
    public static final String ACTION_ADD = "com.google.android.exoplayer.downloadService.action.ADD";
    public static final String ACTION_INIT = "com.google.android.exoplayer.downloadService.action.INIT";
    private static final String ACTION_RESTART = "com.google.android.exoplayer.downloadService.action.RESTART";
    private static final String ACTION_START_DOWNLOADS = "com.google.android.exoplayer.downloadService.action.START_DOWNLOADS";
    private static final String ACTION_STOP_DOWNLOADS = "com.google.android.exoplayer.downloadService.action.STOP_DOWNLOADS";
    private static final boolean DEBUG = false;
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    public static final String KEY_DOWNLOAD_ACTION = "download_action";
    public static final String KEY_FOREGROUND = "foreground";
    private static final String TAG = "DownloadService";
    private static final HashMap<Class<? extends DownloadService>, RequirementsHelper> requirementsHelpers = new HashMap<>();
    private final String channelId;
    private final int channelName;
    private DownloadManager downloadManager;
    private DownloadManagerListener downloadManagerListener;
    private final ForegroundNotificationUpdater foregroundNotificationUpdater;
    private int lastStartId;
    private boolean startedInForeground;
    private boolean taskRemoved;

    private void logd(String str) {
    }

    protected abstract DownloadManager getDownloadManager();

    protected abstract Notification getForegroundNotification(DownloadManager.TaskState[] taskStateArr);

    protected abstract Scheduler getScheduler();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onTaskStateChanged(DownloadManager.TaskState taskState) {
    }

    protected DownloadService(int i) {
        this(i, 1000L);
    }

    protected DownloadService(int i, long j) {
        this(i, j, null, 0);
    }

    protected DownloadService(int i, long j, String str, int i2) {
        this.foregroundNotificationUpdater = new ForegroundNotificationUpdater(i, j);
        this.channelId = str;
        this.channelName = i2;
    }

    public static Intent buildAddActionIntent(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        return new Intent(context, cls).setAction(ACTION_ADD).putExtra(KEY_DOWNLOAD_ACTION, downloadAction.toByteArray()).putExtra(KEY_FOREGROUND, z);
    }

    public static void startWithAction(Context context, Class<? extends DownloadService> cls, DownloadAction downloadAction, boolean z) {
        Intent intentBuildAddActionIntent = buildAddActionIntent(context, cls, downloadAction, z);
        if (z) {
            Util.startForegroundService(context, intentBuildAddActionIntent);
        } else {
            context.startService(intentBuildAddActionIntent);
        }
    }

    public static void start(Context context, Class<? extends DownloadService> cls) {
        context.startService(new Intent(context, cls).setAction(ACTION_INIT));
    }

    public static void startForeground(Context context, Class<? extends DownloadService> cls) {
        Util.startForegroundService(context, new Intent(context, cls).setAction(ACTION_INIT).putExtra(KEY_FOREGROUND, true));
    }

    @Override // android.app.Service
    public void onCreate() {
        logd("onCreate");
        if (this.channelId != null) {
            NotificationUtil.createNotificationChannel(this, this.channelId, this.channelName, 2);
        }
        this.downloadManager = getDownloadManager();
        this.downloadManagerListener = new DownloadManagerListener();
        this.downloadManager.addListener(this.downloadManagerListener);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0081  */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r5, int r6, int r7) {
        /*
            r4 = this;
            r4.lastStartId = r7
            r6 = 0
            r4.taskRemoved = r6
            r0 = 1
            if (r5 == 0) goto L26
            java.lang.String r1 = r5.getAction()
            boolean r2 = r4.startedInForeground
            java.lang.String r3 = "foreground"
            boolean r3 = r5.getBooleanExtra(r3, r6)
            if (r3 != 0) goto L21
            java.lang.String r3 = "com.google.android.exoplayer.downloadService.action.RESTART"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L1f
            goto L21
        L1f:
            r3 = 0
            goto L22
        L21:
            r3 = 1
        L22:
            r2 = r2 | r3
            r4.startedInForeground = r2
            goto L27
        L26:
            r1 = 0
        L27:
            if (r1 != 0) goto L2b
            java.lang.String r1 = "com.google.android.exoplayer.downloadService.action.INIT"
        L2b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onStartCommand action: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r3 = " startId: "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r4.logd(r7)
            r7 = -1
            int r2 = r1.hashCode()
            switch(r2) {
                case -871181424: goto L77;
                case -382886238: goto L6d;
                case -337334865: goto L63;
                case 1015676687: goto L5a;
                case 1286088717: goto L50;
                default: goto L4f;
            }
        L4f:
            goto L81
        L50:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.STOP_DOWNLOADS"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L81
            r6 = 3
            goto L82
        L5a:
            java.lang.String r2 = "com.google.android.exoplayer.downloadService.action.INIT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L81
            goto L82
        L63:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.START_DOWNLOADS"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L81
            r6 = 4
            goto L82
        L6d:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.ADD"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L81
            r6 = 2
            goto L82
        L77:
            java.lang.String r6 = "com.google.android.exoplayer.downloadService.action.RESTART"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L81
            r6 = 1
            goto L82
        L81:
            r6 = -1
        L82:
            switch(r6) {
                case 0: goto Lc6;
                case 1: goto Lc6;
                case 2: goto La8;
                case 3: goto La2;
                case 4: goto L9c;
                default: goto L85;
            }
        L85:
            java.lang.String r5 = "DownloadService"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Ignoring unrecognized action: "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r5, r6)
            goto Lc6
        L9c:
            com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager r5 = r4.downloadManager
            r5.startDownloads()
            goto Lc6
        La2:
            com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager r5 = r4.downloadManager
            r5.stopDownloads()
            goto Lc6
        La8:
            java.lang.String r6 = "download_action"
            byte[] r5 = r5.getByteArrayExtra(r6)
            if (r5 != 0) goto Lb8
            java.lang.String r5 = "DownloadService"
            java.lang.String r6 = "Ignoring ADD action with no action data"
            android.util.Log.e(r5, r6)
            goto Lc6
        Lb8:
            com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager r6 = r4.downloadManager     // Catch: java.io.IOException -> Lbe
            r6.handleAction(r5)     // Catch: java.io.IOException -> Lbe
            goto Lc6
        Lbe:
            r5 = move-exception
            java.lang.String r6 = "DownloadService"
            java.lang.String r7 = "Failed to handle ADD action"
            android.util.Log.e(r6, r7, r5)
        Lc6:
            r4.maybeStartWatchingRequirements()
            com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager r5 = r4.downloadManager
            boolean r5 = r5.isIdle()
            if (r5 == 0) goto Ld4
            r4.stop()
        Ld4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        logd("onTaskRemoved rootIntent: " + intent);
        this.taskRemoved = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        logd("onDestroy");
        this.foregroundNotificationUpdater.stopPeriodicUpdates();
        this.downloadManager.removeListener(this.downloadManagerListener);
        maybeStopWatchingRequirements();
    }

    protected Requirements getRequirements() {
        return new Requirements(1, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void maybeStartWatchingRequirements() {
        if (this.downloadManager.getDownloadCount() == 0) {
            return;
        }
        Class<?> cls = getClass();
        if (requirementsHelpers.get(cls) == null) {
            RequirementsHelper requirementsHelper = new RequirementsHelper(this, getRequirements(), getScheduler(), cls);
            requirementsHelpers.put(cls, requirementsHelper);
            requirementsHelper.start();
            logd("started watching requirements");
        }
    }

    private void maybeStopWatchingRequirements() {
        RequirementsHelper requirementsHelperRemove;
        if (this.downloadManager.getDownloadCount() <= 0 && (requirementsHelperRemove = requirementsHelpers.remove(getClass())) != null) {
            requirementsHelperRemove.stop();
            logd("stopped watching requirements");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        this.foregroundNotificationUpdater.stopPeriodicUpdates();
        if (this.startedInForeground && Util.SDK_INT >= 26) {
            this.foregroundNotificationUpdater.showNotificationIfNotAlready();
        }
        if (Util.SDK_INT < 28 && this.taskRemoved) {
            stopSelf();
            logd("stopSelf()");
            return;
        }
        logd("stopSelf(" + this.lastStartId + ") result: " + stopSelfResult(this.lastStartId));
    }

    private final class DownloadManagerListener implements DownloadManager.Listener {
        private DownloadManagerListener() {
        }

        @Override // com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager.Listener
        public final void onInitialized(DownloadManager downloadManager) {
            DownloadService.this.maybeStartWatchingRequirements();
        }

        @Override // com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager.Listener
        public final void onTaskStateChanged(DownloadManager downloadManager, DownloadManager.TaskState taskState) {
            DownloadService.this.onTaskStateChanged(taskState);
            if (taskState.state == 1) {
                DownloadService.this.foregroundNotificationUpdater.startPeriodicUpdates();
            } else {
                DownloadService.this.foregroundNotificationUpdater.update();
            }
        }

        @Override // com.mbridge.msdk.playercommon.exoplayer2.offline.DownloadManager.Listener
        public final void onIdle(DownloadManager downloadManager) {
            DownloadService.this.stop();
        }
    }

    private final class ForegroundNotificationUpdater implements Runnable {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private boolean notificationDisplayed;
        private final int notificationId;
        private boolean periodicUpdatesStarted;
        private final long updateInterval;

        public ForegroundNotificationUpdater(int i, long j) {
            this.notificationId = i;
            this.updateInterval = j;
        }

        public final void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            update();
        }

        public final void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacks(this);
        }

        public final void update() {
            DownloadService.this.startForeground(this.notificationId, DownloadService.this.getForegroundNotification(DownloadService.this.downloadManager.getAllTaskStates()));
            this.notificationDisplayed = true;
            if (this.periodicUpdatesStarted) {
                this.handler.removeCallbacks(this);
                this.handler.postDelayed(this, this.updateInterval);
            }
        }

        public final void showNotificationIfNotAlready() {
            if (this.notificationDisplayed) {
                return;
            }
            update();
        }

        @Override // java.lang.Runnable
        public final void run() {
            update();
        }
    }

    private static final class RequirementsHelper implements RequirementsWatcher.Listener {
        private final Context context;
        private final Requirements requirements;
        private final RequirementsWatcher requirementsWatcher;
        private final Scheduler scheduler;
        private final Class<? extends DownloadService> serviceClass;

        private RequirementsHelper(Context context, Requirements requirements, Scheduler scheduler, Class<? extends DownloadService> cls) {
            this.context = context;
            this.requirements = requirements;
            this.scheduler = scheduler;
            this.serviceClass = cls;
            this.requirementsWatcher = new RequirementsWatcher(context, this, requirements);
        }

        public final void start() {
            this.requirementsWatcher.start();
        }

        public final void stop() {
            this.requirementsWatcher.stop();
            if (this.scheduler != null) {
                this.scheduler.cancel();
            }
        }

        @Override // com.mbridge.msdk.playercommon.exoplayer2.scheduler.RequirementsWatcher.Listener
        public final void requirementsMet(RequirementsWatcher requirementsWatcher) {
            startServiceWithAction(DownloadService.ACTION_START_DOWNLOADS);
            if (this.scheduler != null) {
                this.scheduler.cancel();
            }
        }

        @Override // com.mbridge.msdk.playercommon.exoplayer2.scheduler.RequirementsWatcher.Listener
        public final void requirementsNotMet(RequirementsWatcher requirementsWatcher) {
            startServiceWithAction(DownloadService.ACTION_STOP_DOWNLOADS);
            if (this.scheduler != null) {
                if (this.scheduler.schedule(this.requirements, this.context.getPackageName(), DownloadService.ACTION_RESTART)) {
                    return;
                }
                Log.e(DownloadService.TAG, "Scheduling downloads failed.");
            }
        }

        private void startServiceWithAction(String str) {
            Util.startForegroundService(this.context, new Intent(this.context, this.serviceClass).setAction(str).putExtra(DownloadService.KEY_FOREGROUND, true));
        }
    }
}
