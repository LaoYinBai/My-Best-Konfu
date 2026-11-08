package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.service.UMJobIntentService;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengDownloadResourceService extends UMJobIntentService {
    public static final String TAG = "com.umeng.message.UmengDownloadResourceService";
    private static final String o = ".tmp";
    private static final String p = "RETRY_TIME";
    private static final String q = "OPERATIOIN";
    private static final int r = 1;
    private static final int s = 2;
    private static final long t = 1048576;
    private static final long u = 86400000;
    private static final int v = 300000;
    private static final int w = 3;
    private static Thread x;
    ScheduledThreadPoolExecutor a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    Context b = this;
    ArrayList<String> c = new ArrayList<>();

    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    protected void a(Intent intent) throws Throwable {
        MLog.i("wuchi", "--->>> UmengDownloadResourceService onHandleWork");
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra(q, 2);
        int intExtra2 = intent.getIntExtra(p, 3);
        try {
            UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
            uMessage.message_id = intent.getStringExtra(AdDownloadModel.JsonKey.ID);
            uMessage.task_id = intent.getStringExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID);
            if (this.c.contains(uMessage.msg_id)) {
                return;
            }
            this.c.add(uMessage.msg_id);
            switch (intExtra) {
                case 1:
                    deleteAlarm(uMessage, intExtra2);
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TAG, 2, "下载资源后显示通知");
                    notification(uMessage);
                    this.c.remove(uMessage.msg_id);
                    if (this.c.size() == 0) {
                        stopSelf();
                        break;
                    }
                    break;
                case 2:
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TAG, 2, "开始下载资源");
                    int i = intExtra2 - 1;
                    setAlarm(uMessage, i);
                    checkCache();
                    downloadResource(uMessage, i);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notification(UMessage uMessage) {
        UHandler messageHandler;
        if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            messageHandler = PushAgent.getInstance(this).getAdHandler();
        } else {
            messageHandler = PushAgent.getInstance(this).getMessageHandler();
        }
        if (messageHandler != null) {
            if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
                UmengMessageHandler umengMessageHandler = (UmengMessageHandler) PushAgent.getInstance(this.b).getMessageHandler();
                if (umengMessageHandler != null) {
                    umengMessageHandler.dealWithNotificationMessage(this.b, uMessage);
                    return;
                }
                return;
            }
            messageHandler.handleMessage(this, uMessage);
        }
    }

    @SuppressLint({"NewApi"})
    public void downloadResource(UMessage uMessage, int i) {
        DownloadResourceTask downloadResourceTask = new DownloadResourceTask(uMessage, i);
        if (Build.VERSION.SDK_INT >= 11) {
            downloadResourceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            downloadResourceTask.execute(new Void[0]);
        }
    }

    public void setAlarm(UMessage uMessage, int i) {
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 2, "setAlarm");
        ((AlarmManager) getSystemService("alarm")).set(1, System.currentTimeMillis() + 300000, a(uMessage, i));
    }

    public void deleteAlarm(UMessage uMessage, int i) {
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 2, "deleteAlarm");
        ((AlarmManager) getSystemService("alarm")).cancel(a(uMessage, i));
    }

    private PendingIntent a(UMessage uMessage, int i) {
        String string = uMessage.getRaw().toString();
        int iHashCode = uMessage.msg_id.hashCode();
        Intent intent = new Intent(this.b, (Class<?>) UmengDownloadResourceService.class);
        intent.putExtra("body", string);
        intent.putExtra(AdDownloadModel.JsonKey.ID, uMessage.message_id);
        intent.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, uMessage.task_id);
        intent.putExtra(q, 2);
        intent.putExtra(p, i);
        PendingIntent service = PendingIntent.getService(this.b, iHashCode, intent, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 2, "PendingIntent: msgId:" + uMessage.msg_id + ",requestCode:" + iHashCode + ",retryTime:" + i);
        return service;
    }

    public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean> {
        UMessage a;
        ArrayList<String> b = new ArrayList<>();
        int c;

        public DownloadResourceTask(UMessage uMessage, int i) {
            this.a = uMessage;
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                try {
                    this.b.add(new JSONObject(uMessage.custom).optString("img"));
                } catch (Exception unused) {
                }
            }
            if (uMessage.isLargeIconFromInternet()) {
                this.b.add(uMessage.img);
            }
            if (uMessage.isSoundFromInternet()) {
                this.b.add(uMessage.sound);
            }
            if (!TextUtils.isEmpty(uMessage.bar_image)) {
                this.b.add(uMessage.bar_image);
            }
            if (!TextUtils.isEmpty(uMessage.expand_image)) {
                this.b.add(uMessage.expand_image);
            }
            this.c = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            Iterator<String> it = this.b.iterator();
            boolean zDownload = true;
            while (it.hasNext()) {
                zDownload &= download(it.next());
            }
            return Boolean.valueOf(zDownload);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            UmengDownloadResourceService.this.c.remove(this.a.msg_id);
            if (bool.booleanValue() || this.c <= 0) {
                MessageSharedPrefs.getInstance(UmengDownloadResourceService.this.b).setMessageResourceDownloaded(this.a.msg_id);
                String string = this.a.getRaw().toString();
                Intent intent = new Intent(UmengDownloadResourceService.this.b, (Class<?>) UmengDownloadResourceService.class);
                intent.putExtra("body", string);
                intent.putExtra(AdDownloadModel.JsonKey.ID, this.a.message_id);
                intent.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, this.a.task_id);
                intent.putExtra(UmengDownloadResourceService.q, 1);
                intent.putExtra(UmengDownloadResourceService.p, this.c);
                UMJobIntentService.enqueueWork(UmengDownloadResourceService.this.b, (Class<? extends UMJobIntentService>) UmengDownloadResourceService.class, intent);
                return;
            }
            if (UmengDownloadResourceService.this.c.size() == 0) {
                UmengDownloadResourceService.this.stopSelf();
            }
        }

        public boolean download(String str) throws Throwable {
            FileOutputStream fileOutputStream;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            InputStream inputStream = null;
            try {
                String str2 = str.hashCode() + "";
                String messageResourceFolder = UmengDownloadResourceService.getMessageResourceFolder(UmengDownloadResourceService.this.b, this.a);
                File file = new File(messageResourceFolder, str2 + UmengDownloadResourceService.o);
                File file2 = new File(messageResourceFolder, str2);
                if (file2.exists()) {
                    UmengDownloadResourceService.this.close(null);
                    UmengDownloadResourceService.this.close(null);
                    return true;
                }
                File file3 = new File(messageResourceFolder);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                InputStream inputStreamOpenStream = new URL(new URI(str).toASCIIString()).openStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
                try {
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int i = inputStreamOpenStream.read(bArr);
                        if (i <= 0) {
                            file.renameTo(file2);
                            UmengDownloadResourceService.this.close(inputStreamOpenStream);
                            UmengDownloadResourceService.this.close(fileOutputStream);
                            return true;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    inputStream = inputStreamOpenStream;
                    e = e;
                    try {
                        e.printStackTrace();
                        UmengDownloadResourceService.this.close(inputStream);
                        UmengDownloadResourceService.this.close(fileOutputStream);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        UmengDownloadResourceService.this.close(inputStream);
                        UmengDownloadResourceService.this.close(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStreamOpenStream;
                    th = th;
                    UmengDownloadResourceService.this.close(inputStream);
                    UmengDownloadResourceService.this.close(fileOutputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
            }
        }
    }

    public void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCache() {
        try {
            checkDir(new File(getMessageResourceFolder(this.b, null)), 1048576L, 86400000L);
        } catch (Throwable unused) {
        }
    }

    public static void checkDir(final File file, long j, final long j2) throws IOException {
        if (!file.exists() || a(file.getCanonicalFile()) <= j) {
            return;
        }
        if (x == null) {
            x = new Thread(new Runnable() { // from class: com.umeng.message.UmengDownloadResourceService.1
                @Override // java.lang.Runnable
                public void run() {
                    UmengDownloadResourceService.b(file, j2);
                    Thread unused = UmengDownloadResourceService.x = null;
                }
            });
        }
        synchronized (x) {
            x.start();
        }
    }

    private static long a(File file) {
        long length = 0;
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0L;
        }
        Stack stack = new Stack();
        stack.clear();
        stack.push(file);
        while (!stack.isEmpty()) {
            for (File file2 : ((File) stack.pop()).listFiles()) {
                if (!file2.isDirectory()) {
                    length += file2.length();
                }
            }
        }
        return length;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && System.currentTimeMillis() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    public static String getMessageResourceFolder(Context context, UMessage uMessage) {
        String str = context.getCacheDir() + "/umeng_push/";
        if (uMessage == null || uMessage.msg_id == null) {
            return str;
        }
        return str + uMessage.msg_id + "/";
    }
}
