package com.mbridge.msdk.mbdownload;

import android.R;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.foundation.tools.MBFileProvider;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* compiled from: DownloadTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class e {
    private static final String a = "com.mbridge.msdk.mbdownload.e";
    private SparseArray<b> b;
    private Map<b.a, Messenger> c;
    private i d;

    public e(SparseArray<b> sparseArray, Map<b.a, Messenger> map, i iVar) {
        this.b = sparseArray;
        this.c = map;
        this.d = iVar;
    }

    /* compiled from: DownloadTool.java */
    static class b {
        k a;
        a b;
        int c;
        int d;
        b.a e;
        long[] f = new long[3];

        public b(b.a aVar, int i) {
            this.c = i;
            this.e = aVar;
        }
    }

    /* compiled from: DownloadTool.java */
    static class a extends h {
        String a;
        String b;
        String c;
        Context d;

        public a(Context context) {
            super(context);
            this.d = context;
        }

        public final a a(CharSequence charSequence) {
            int iV = this.d != null ? o.v(this.d) : 0;
            if (!TextUtils.isEmpty(charSequence) && charSequence.toString().contains("%")) {
                this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_status", "id"), 8);
            }
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_progess", "id"), 0);
            if ((iV >= 26 && Build.VERSION.SDK_INT >= 26) || Build.VERSION.SDK_INT >= 16) {
                this.j.setTextViewText(m.a(this.e, "mbridge_download_notify_progress_progess", "id"), charSequence);
            } else {
                this.b = charSequence.toString();
            }
            return this;
        }

        public final a b(CharSequence charSequence) {
            int iV = this.d != null ? o.v(this.d) : 0;
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_status", "id"), 0);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_progess", "id"), 8);
            if ((iV >= 26 && Build.VERSION.SDK_INT >= 26) || Build.VERSION.SDK_INT >= 16) {
                this.j.setTextViewText(m.a(this.e, "mbridge_download_notify_progress_status", "id"), charSequence);
            } else {
                this.b = charSequence.toString();
            }
            return this;
        }

        public final a c(CharSequence charSequence) {
            if (((this.d != null ? o.v(this.d) : 0) >= 26 && Build.VERSION.SDK_INT >= 26) || Build.VERSION.SDK_INT >= 16) {
                this.j.setTextViewText(m.a(this.e, "mbridge_download_notify_target_name", "id"), charSequence);
            } else {
                this.a = charSequence.toString();
            }
            return this;
        }

        public final a a(String str) {
            if (this.d != null) {
                o.v(this.d);
            }
            if (!TextUtils.isEmpty(str)) {
                com.mbridge.msdk.foundation.same.c.b.a(this.d).a(str, new com.mbridge.msdk.foundation.same.c.c() { // from class: com.mbridge.msdk.mbdownload.e.a.1
                    @Override // com.mbridge.msdk.foundation.same.c.c
                    public final void onFailedLoad(String str2, String str3) {
                    }

                    @Override // com.mbridge.msdk.foundation.same.c.c
                    public final void onSuccessLoad(Bitmap bitmap, String str2) {
                        if (bitmap.isRecycled()) {
                            return;
                        }
                        a.this.j.setImageViewBitmap(m.a(a.this.e, "mbridge_download_notify_target_icon", "id"), bitmap);
                    }
                });
            }
            return this;
        }

        public final a a() {
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_pause", "id"), 0);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_continue", "id"), 8);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_status", "id"), 8);
            return this;
        }

        public final a b() {
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_pause", "id"), 8);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_continue", "id"), 8);
            return this;
        }

        public final a c() {
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_pause", "id"), 8);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_continue", "id"), 0);
            this.j.setViewVisibility(m.a(this.d, "mbridge_download_notify_progress_status", "id"), 8);
            return this;
        }

        public final a a(int i, int i2, boolean z) {
            if (Build.VERSION.SDK_INT >= 16) {
                try {
                    this.j.setProgressBar(m.a(this.e, "mbridge_download_notify_progress", "id"), i, i2, z);
                } catch (Exception e) {
                    s.d(e.a, e.getMessage());
                }
            } else {
                this.c = i2 + "%";
            }
            return this;
        }

        public final Notification d() {
            if ((this.d != null ? o.v(this.d) : 0) >= 26 && Build.VERSION.SDK_INT >= 26) {
                return this.h.build();
            }
            if (Build.VERSION.SDK_INT >= 16) {
                return this.g.build();
            }
            if (Build.VERSION.SDK_INT < 16) {
                this.f = new NotificationCompat.Builder(this.e).setTicker(this.a).setContentIntent(this.i).build();
            } else {
                this.f = new Notification.Builder(this.e).setTicker(this.a).setContentIntent(this.i).build();
            }
            return this.f;
        }
    }

    final a a(Context context, b.a aVar, int i, int i2) {
        Context applicationContext = context.getApplicationContext();
        a aVar2 = new a(applicationContext);
        PendingIntent.getActivity(applicationContext, 0, new Intent(), 134217728);
        if (aVar.q) {
            aVar2.e();
            a(applicationContext, aVar2, i, 2);
        }
        a(applicationContext, aVar2, i, 3);
        aVar2.a(aVar.j);
        g.f(applicationContext);
        aVar2.a(R.drawable.stat_sys_download).a(System.currentTimeMillis());
        if (aVar.i) {
            aVar2.a();
        }
        aVar2.c(aVar.c).a((CharSequence) (i2 + "%")).a(100, i2, false);
        aVar2.a(aVar.h).b(true);
        return aVar2;
    }

    private void a(Context context, a aVar, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            PendingIntent pendingIntentA = j.a(context, j.a(i, "continue"));
            PendingIntent pendingIntentA2 = j.a(context, j.a(i, "install or active"));
            PendingIntent pendingIntentA3 = j.a(context, j.a(i, "continue"));
            PendingIntent pendingIntentA4 = j.a(context, j.a(i, "parent_view_click"));
            switch (i2) {
                case 1:
                    aVar.j.setOnClickPendingIntent(m.a(context, "mbridge_download_notify_continue", "id"), pendingIntentA);
                    break;
                case 2:
                    aVar.j.setOnClickPendingIntent(m.a(context, "mbridge_download_notify_pause", "id"), pendingIntentA3);
                    break;
                case 3:
                    aVar.j.setOnClickPendingIntent(m.a(context, "mbridge_download_notify_progress_status", "id"), pendingIntentA2);
                    break;
            }
            aVar.j.setOnClickPendingIntent(m.a(context, "mbridge_download_notify_parent_view", "id"), pendingIntentA4);
        }
    }

    final boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    final boolean a(b.a aVar, boolean z, Messenger messenger) {
        if (z) {
            int iNextInt = new Random().nextInt(1000);
            if (this.c != null) {
                for (b.a aVar2 : this.c.keySet()) {
                    s.a(a, "_" + iNextInt + " downling  " + aVar2.c + "   " + aVar2.d);
                }
            } else {
                s.a(a, "_" + iNextInt + "downling  null");
            }
        }
        if (this.c == null) {
            return false;
        }
        for (b.a aVar3 : this.c.keySet()) {
            if (aVar.f != null && aVar.f.equals(aVar3.f)) {
                this.c.put(aVar3, messenger);
                return true;
            }
            if (aVar3.d.equals(aVar.d)) {
                this.c.put(aVar3, messenger);
                return true;
            }
        }
        return false;
    }

    final int a(b.a aVar) {
        for (int i = 0; i < this.b.size(); i++) {
            int iKeyAt = this.b.keyAt(i);
            if (aVar.f != null && aVar.f.equals(this.b.get(iKeyAt).e.f)) {
                return this.b.get(iKeyAt).c;
            }
            if (this.b.get(iKeyAt).e.d.equals(aVar.d)) {
                return this.b.get(iKeyAt).c;
            }
        }
        return -1;
    }

    private void b(Context context, int i) {
        Context applicationContext = context.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(RemoteMessageConst.NOTIFICATION);
        j.a();
        b bVar = this.b.get(i);
        bVar.b.e();
        a(applicationContext, bVar.b, i, 1);
        bVar.b.c(bVar.e.c).a(bVar.e.h).b(true);
        if (bVar.e.i) {
            bVar.b.c();
        }
        notificationManager.notify(i, bVar.b.d());
    }

    final void a(Context context, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(RemoteMessageConst.NOTIFICATION);
        b bVar = this.b.get(i);
        if (bVar != null) {
            s.a(a, "download service clear cache " + bVar.e.c);
            if (bVar.a != null) {
                bVar.a.a(2);
            }
            notificationManager.cancel(bVar.c);
            if (this.c.containsKey(bVar.e)) {
                this.c.remove(bVar.e);
            }
            SparseArray<b> sparseArray = this.b;
            if (sparseArray.indexOfKey(bVar.c) >= 0) {
                sparseArray.remove(bVar.c);
            }
            this.d.b(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01b0 A[Catch: RemoteException -> 0x0198, Exception -> 0x02f6, TRY_LEAVE, TryCatch #7 {RemoteException -> 0x0198, blocks: (B:75:0x01a6, B:77:0x01b0, B:69:0x0180, B:71:0x018a), top: B:149:0x0149, outer: #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final boolean a(com.mbridge.msdk.mbdownload.c r12, android.content.Intent r13) throws java.lang.NumberFormatException, android.os.RemoteException {
        /*
            Method dump skipped, instructions count: 763
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbdownload.e.a(com.mbridge.msdk.mbdownload.c, android.content.Intent):boolean");
    }

    /* compiled from: DownloadTool.java */
    class c extends AsyncTask<String, Void, Integer> {
        public int a;
        public String b;
        private b.a d;
        private Context e;
        private NotificationManager f;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Integer doInBackground(String[] strArr) {
            return 1;
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Integer num) {
            try {
                if (num.intValue() == 1) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addFlags(268435456);
                    try {
                        if (o.v(this.e) >= 24 && Build.VERSION.SDK_INT >= 24) {
                            Uri uriForFile = MBFileProvider.getUriForFile(this.e, this.e.getApplicationContext().getPackageName() + ".mbFileProvider", new File(this.b));
                            if (uriForFile != null) {
                                intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                                intent.addFlags(1);
                            }
                        } else {
                            intent.setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
                        }
                    } catch (Exception unused) {
                        intent.setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
                    }
                    Notification notificationA = e.a(this.e, g.g(this.e), PendingIntent.getActivity(this.e, 0, intent, 134217728));
                    if (notificationA != null) {
                        notificationA.flags = 16;
                        this.f.notify(this.a + 1, notificationA);
                    }
                    if (e.this.a(this.e)) {
                        this.f.cancel(this.a + 1);
                        this.e.startActivity(intent);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString(FileDownloadModel.FILENAME, this.b);
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 5;
                    messageObtain.arg1 = 1;
                    messageObtain.arg2 = this.a;
                    messageObtain.setData(bundle);
                    try {
                        if (e.this.c.get(this.d) != null) {
                            ((Messenger) e.this.c.get(this.d)).send(messageObtain);
                            return;
                        }
                        return;
                    } catch (RemoteException unused2) {
                        e.this.a(this.e, this.a);
                        return;
                    }
                }
                this.f.cancel(this.a + 1);
                Bundle bundle2 = new Bundle();
                bundle2.putString(FileDownloadModel.FILENAME, this.b);
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 5;
                messageObtain2.arg1 = 3;
                messageObtain2.arg2 = this.a;
                messageObtain2.setData(bundle2);
                try {
                    if (e.this.c.get(this.d) != null) {
                        ((Messenger) e.this.c.get(this.d)).send(messageObtain2);
                    }
                } catch (RemoteException unused3) {
                    e.this.a(this.e, this.a);
                }
            } catch (Throwable unused4) {
            }
        }

        public c(Context context, int i, b.a aVar, String str) {
            this.e = context.getApplicationContext();
            this.f = (NotificationManager) this.e.getSystemService(RemoteMessageConst.NOTIFICATION);
            j.a();
            this.a = i;
            this.d = aVar;
            this.b = str;
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            super.onPreExecute();
        }
    }

    public static boolean b(Context context) {
        try {
            return context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0 && ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(String str) throws NoSuchAlgorithmException {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
    }

    private static boolean a(String str, int i, int i2, int i3) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, str, Integer.valueOf(i), -1, -1);
            return true;
        } catch (ClassNotFoundException e) {
            s.a(a, "error when set permissions:", e);
            return false;
        } catch (IllegalAccessException e2) {
            s.a(a, "error when set permissions:", e2);
            return false;
        } catch (IllegalArgumentException e3) {
            s.a(a, "error when set permissions:", e3);
            return false;
        } catch (NoSuchMethodException e4) {
            s.a(a, "error when set permissions:", e4);
            return false;
        } catch (InvocationTargetException e5) {
            s.a(a, "error when set permissions:", e5);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0064 A[Catch: Throwable -> 0x0062, TryCatch #0 {Throwable -> 0x0062, blocks: (B:7:0x000c, B:9:0x0010, B:11:0x0019, B:12:0x003a, B:15:0x0064, B:17:0x006d, B:18:0x008b), top: B:23:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.app.Notification a(android.content.Context r4, java.lang.String r5, android.app.PendingIntent r6) {
        /*
            if (r4 == 0) goto L7
            int r0 = com.mbridge.msdk.foundation.tools.o.v(r4)
            goto L8
        L7:
            r0 = 0
        L8:
            r1 = 26
            if (r0 < r1) goto L64
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L62
            if (r0 < r1) goto L64
            android.content.res.Resources r0 = r4.getResources()     // Catch: java.lang.Throwable -> L62
            r1 = 17301634(0x1080082, float:2.497962E-38)
            if (r0 != 0) goto L3a
            androidx.core.app.NotificationCompat$Builder r0 = new androidx.core.app.NotificationCompat$Builder     // Catch: java.lang.Throwable -> L62
            java.lang.String r2 = "download"
            r0.<init>(r4, r2)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r0.setSmallIcon(r1)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setTicker(r5)     // Catch: java.lang.Throwable -> L62
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setWhen(r0)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setContentIntent(r6)     // Catch: java.lang.Throwable -> L62
            android.app.Notification r4 = r4.build()     // Catch: java.lang.Throwable -> L62
            goto Lb3
        L3a:
            androidx.core.app.NotificationCompat$Builder r2 = new androidx.core.app.NotificationCompat$Builder     // Catch: java.lang.Throwable -> L62
            java.lang.String r3 = "download"
            r2.<init>(r4, r3)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r2.setSmallIcon(r1)     // Catch: java.lang.Throwable -> L62
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r0, r1)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setLargeIcon(r0)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setTicker(r5)     // Catch: java.lang.Throwable -> L62
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setWhen(r0)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setContentIntent(r6)     // Catch: java.lang.Throwable -> L62
            android.app.Notification r4 = r4.build()     // Catch: java.lang.Throwable -> L62
            goto Lb3
        L62:
            r4 = move-exception
            goto La9
        L64:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L62
            r1 = 16
            r2 = 17301633(0x1080081, float:2.4979616E-38)
            if (r0 >= r1) goto L8b
            androidx.core.app.NotificationCompat$Builder r0 = new androidx.core.app.NotificationCompat$Builder     // Catch: java.lang.Throwable -> L62
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r0.setSmallIcon(r2)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setTicker(r5)     // Catch: java.lang.Throwable -> L62
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setWhen(r0)     // Catch: java.lang.Throwable -> L62
            androidx.core.app.NotificationCompat$Builder r4 = r4.setContentIntent(r6)     // Catch: java.lang.Throwable -> L62
            android.app.Notification r4 = r4.build()     // Catch: java.lang.Throwable -> L62
            goto Lb3
        L8b:
            android.app.Notification$Builder r0 = new android.app.Notification$Builder     // Catch: java.lang.Throwable -> L62
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L62
            android.app.Notification$Builder r4 = r0.setSmallIcon(r2)     // Catch: java.lang.Throwable -> L62
            android.app.Notification$Builder r4 = r4.setTicker(r5)     // Catch: java.lang.Throwable -> L62
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L62
            android.app.Notification$Builder r4 = r4.setWhen(r0)     // Catch: java.lang.Throwable -> L62
            android.app.Notification$Builder r4 = r4.setContentIntent(r6)     // Catch: java.lang.Throwable -> L62
            android.app.Notification r4 = r4.build()     // Catch: java.lang.Throwable -> L62
            goto Lb3
        La9:
            java.lang.String r5 = "DownloadTool"
            java.lang.String r4 = r4.getMessage()
            com.mbridge.msdk.foundation.tools.s.a(r5, r4)
            r4 = 0
        Lb3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbdownload.e.a(android.content.Context, java.lang.String, android.app.PendingIntent):android.app.Notification");
    }

    public static File a(String str, Context context, boolean[] zArr) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(com.mbridge.msdk.foundation.same.b.e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_700_APK) + "/download/.mb" + str);
            file.mkdirs();
            if (file.exists()) {
                zArr[0] = true;
                return file;
            }
        }
        String absolutePath = context.getCacheDir().getAbsolutePath();
        new File(absolutePath).mkdir();
        a(absolutePath, 505, -1, -1);
        String str2 = absolutePath + "/mbdownload";
        new File(str2).mkdir();
        a(str2, 505, -1, -1);
        File file2 = new File(str2);
        zArr[0] = false;
        return file2;
    }
}
