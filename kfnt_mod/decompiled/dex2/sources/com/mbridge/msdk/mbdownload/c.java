package com.mbridge.msdk.mbdownload;

import android.R;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.mbridge.msdk.foundation.tools.MBFileProvider;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.b;
import com.mbridge.msdk.mbdownload.e;
import com.mbridge.msdk.mbdownload.e.c;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* compiled from: DownloadProvider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static final String a = "c";
    public static boolean b = false;
    protected static Map<b.a, Messenger> c = new HashMap();
    protected static SparseArray<e.b> d = new SparseArray<>();
    private NotificationManagerCompat g;
    private e h;
    private i i;
    private Handler j;
    private Service l;
    final Messenger e = new Messenger(new b());
    private boolean k = true;
    SparseArray<Long> f = new SparseArray<>();

    public final IBinder a(Intent intent) {
        s.a(a, "onBind ");
        return this.e.getBinder();
    }

    public final int a(Intent intent, int i, int i2) throws NumberFormatException, RemoteException {
        if (intent != null && intent.getExtras() != null) {
            this.h.a(this, intent);
        }
        if (Build.VERSION.SDK_INT >= 19 && (this.i.b() || this.k)) {
            try {
                Intent intent2 = new Intent(this.l.getApplicationContext(), getClass());
                intent2.setPackage(this.l.getPackageName());
                ((AlarmManager) this.l.getApplicationContext().getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, PendingIntent.getService(this.l.getApplicationContext(), 1, intent2, 1073741824));
            } catch (Exception unused) {
            }
        }
        if (this.k) {
            d();
            this.k = false;
        }
        return 1;
    }

    public final void a() {
        if (b) {
            Debug.waitForDebugger();
        }
        this.g = NotificationManagerCompat.from(this.l.getApplicationContext());
        j.a();
        this.i = new i(this.l);
        this.h = new e(d, c, this.i);
        this.j = new a();
    }

    public final void b() {
        try {
            d.a(this.l.getApplicationContext()).a(259200);
            d.a(this.l.getApplicationContext()).finalize();
        } catch (Exception e) {
            s.d(a, e.getMessage());
        }
    }

    private void d() {
        Iterator<Integer> it = this.i.a().iterator();
        while (it.hasNext()) {
            this.g.cancel(it.next().intValue());
        }
    }

    public final void a(int i) {
        NotificationChannel notificationChannel;
        if (d.indexOfKey(i) >= 0) {
            e.b bVar = d.get(i);
            long[] jArr = bVar.f;
            int i2 = 0;
            if (jArr != null && jArr[1] > 0 && (i2 = (int) ((jArr[0] / jArr[1]) * 100.0f)) > 100) {
                i2 = 99;
            }
            if (bVar.e.r) {
                return;
            }
            this.f.put(i, -1L);
            e.a aVarA = this.h.a(this.l, bVar.e, i, i2);
            bVar.b = aVarA;
            try {
                this.g.notify(i, aVarA.d());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String str = this.g.areNotificationsEnabled() ? "true" : "false";
                String strValueOf = "";
                NotificationManager notificationManager = (NotificationManager) this.l.getSystemService(RemoteMessageConst.NOTIFICATION);
                if (Build.VERSION.SDK_INT >= 26 && (notificationChannel = notificationManager.getNotificationChannel("download")) != null) {
                    strValueOf = String.valueOf(notificationChannel.getImportance());
                }
                com.mbridge.msdk.foundation.same.report.d.a(this.l, str, String.valueOf(this.g.getImportance()), strValueOf);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public final void a(int i, int i2) {
        if (d.indexOfKey(i) >= 0) {
            e.b bVar = d.get(i);
            b.a aVar = bVar.e;
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (aVar.r || jCurrentTimeMillis - this.f.get(i).longValue() <= 500) {
                return;
            }
            this.f.put(i, Long.valueOf(jCurrentTimeMillis));
            e.a aVar2 = bVar.b;
            aVar2.a(100, i2, false).a((CharSequence) (String.valueOf(i2) + "%"));
            if (bVar.e.i) {
                aVar2.a();
            }
            try {
                this.g.notify(i, aVar2.d());
            } catch (Exception unused) {
                a(bVar, i);
                e.a aVar3 = bVar.b;
                aVar3.a(100, i2, false).a((CharSequence) (String.valueOf(i2) + "%"));
                if (bVar.e.i) {
                    aVar3.a();
                }
                try {
                    this.g.notify(i, aVar3.d());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public final void b(int i, int i2) {
        if (i2 == 1) {
            e.b bVar = d.get(i);
            e.a aVar = bVar.b;
            aVar.a(100, 100, false).a(bVar.e.j).b(g.a(this.l.getBaseContext())).a((CharSequence) g.c(this.l.getBaseContext())).b().a(bVar.e.h);
            try {
                this.g.notify(i, aVar.d());
                return;
            } catch (Exception unused) {
                a(bVar, i);
                e.a aVar2 = bVar.b;
                aVar2.a(100, 100, false).a(bVar.e.j).b(g.a(this.l.getBaseContext())).a((CharSequence) g.c(this.l.getBaseContext())).b().a(bVar.e.h);
                try {
                    this.g.notify(i, aVar2.d());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        if (i2 == 9) {
            e.b bVar2 = d.get(i);
            e.a aVar3 = bVar2.b;
            aVar3.a(100, 100, false).a(bVar2.e.j).b(g.b(this.l.getBaseContext())).a((CharSequence) g.d(this.l.getBaseContext())).b().a(false);
            try {
                this.g.notify(i, aVar3.d());
            } catch (Exception unused2) {
                a(bVar2, i);
                e.a aVar4 = bVar2.b;
                aVar4.a(100, 100, false).a(bVar2.e.j).b(g.b(this.l.getBaseContext())).a((CharSequence) g.d(this.l.getBaseContext())).b().a(false);
                try {
                    this.g.notify(i, aVar4.d());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public final void a(int i, String str) {
        e.b bVar;
        if (d.indexOfKey(i) < 0 || (bVar = d.get(i)) == null) {
            return;
        }
        b.a aVar = bVar.e;
        e.a aVar2 = bVar.b;
        aVar2.a(100, 100, false).a(aVar.j).b(g.a(this.l.getBaseContext())).a((CharSequence) g.c(this.l.getBaseContext())).b().a(aVar.h).a(R.drawable.stat_sys_download_done);
        try {
            this.g.notify(i, aVar2.d());
        } catch (Exception e) {
            e.printStackTrace();
        }
        d.a(this.l).a(aVar.b, aVar.d, 100);
        Bundle bundle = new Bundle();
        bundle.putString(FileDownloadModel.FILENAME, str);
        if (aVar.b.equalsIgnoreCase("delta_update")) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 6;
            messageObtain.arg1 = 1;
            messageObtain.obj = aVar;
            messageObtain.arg2 = i;
            messageObtain.setData(bundle);
            this.j.sendMessage(messageObtain);
            return;
        }
        Message messageObtain2 = Message.obtain();
        messageObtain2.what = 5;
        messageObtain2.arg1 = 1;
        messageObtain2.obj = aVar;
        messageObtain2.arg2 = i;
        messageObtain2.setData(bundle);
        this.j.sendMessage(messageObtain2);
        Message messageObtain3 = Message.obtain();
        messageObtain3.what = 5;
        messageObtain3.arg1 = 1;
        messageObtain3.arg2 = i;
        messageObtain3.setData(bundle);
        try {
            if (c.get(aVar) != null) {
                c.get(aVar).send(messageObtain3);
            }
        } catch (RemoteException unused) {
            this.h.a(this.l, i);
        }
    }

    public final void a(int i, Exception exc) {
        if (d.indexOfKey(i) >= 0) {
            this.h.a(this.l, i);
        }
    }

    private void a(e.b bVar, int i) {
        bVar.b = this.h.a(this.l, bVar.e, i, 0);
    }

    /* compiled from: DownloadProvider.java */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) throws RemoteException {
            try {
                s.a(c.a, "IncomingHandler(msg.what:" + message.what + " msg.arg1:" + message.arg1 + " msg.arg2:" + message.arg2 + " msg.replyTo:" + message.replyTo);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (message.what == 4) {
                Bundle data = message.getData();
                s.a(c.a, "IncomingHandler(msg.getData():" + data);
                b.a aVar = new b.a(data.getString("mComponentName"), data.getString("mTitle"), data.getString("mUrl"), data.getString("uniqueKey"), data.getBoolean("mOnGoingStatus"));
                aVar.e = data.getString("mMd5");
                aVar.f = data.getString("mTargetMd5");
                aVar.a = data.getString("mReqClz");
                aVar.k = data.getStringArray("succUrls");
                aVar.m = data.getStringArray("faiUrls");
                aVar.n = data.getStringArray("startUrls");
                aVar.o = data.getStringArray("pauseUrls");
                aVar.p = data.getStringArray("cancelUrls");
                aVar.l = data.getStringArray("carryonUrls");
                aVar.q = data.getBoolean("rich_notification");
                aVar.r = data.getBoolean("mSilent");
                aVar.s = data.getBoolean("mWifiOnly");
                aVar.i = data.getBoolean("mCanPause");
                aVar.j = data.getString("mTargetAppIconUrl");
                if (!c.this.h.a(aVar, c.b, message.replyTo)) {
                    if (!e.b(c.this.l)) {
                        Toast.makeText(c.this.l, g.e(c.this.l.getBaseContext()), 0).show();
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.arg1 = 4;
                        messageObtain.arg2 = 0;
                        try {
                            message.replyTo.send(messageObtain);
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    c.c.put(aVar, message.replyTo);
                    Message messageObtain2 = Message.obtain();
                    messageObtain2.what = 1;
                    messageObtain2.arg1 = 1;
                    messageObtain2.arg2 = 0;
                    try {
                        message.replyTo.send(messageObtain2);
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                    }
                    c.a(c.this, aVar);
                    return;
                }
                s.b(c.a, aVar.c + " is already in downloading list. ");
                int iA = c.this.h.a(aVar);
                if (iA == -1 || c.d.get(iA).a != null) {
                    if (c.this.l != null && c.this.l.getBaseContext() != null) {
                        Toast.makeText(c.this.l, g.h(c.this.l.getBaseContext()), 0).show();
                    }
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 2;
                    messageObtain3.arg1 = 2;
                    messageObtain3.arg2 = 0;
                    try {
                        message.replyTo.send(messageObtain3);
                        return;
                    } catch (RemoteException e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                String strA = j.a(iA, "continue");
                Intent intent = new Intent(c.this.l, (Class<?>) c.class);
                intent.putExtra("com.mbridge.msdk.broadcast.download.msg", strA);
                c.this.h.a(c.this, intent);
                return;
                e.printStackTrace();
                return;
            }
            super.handleMessage(message);
        }
    }

    /* compiled from: DownloadProvider.java */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 5:
                    b.a aVar = (b.a) message.obj;
                    int i = message.arg2;
                    try {
                        try {
                            String string = message.getData().getString(FileDownloadModel.FILENAME);
                            s.a(c.a, "Cancel old notification....");
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(268435456);
                            try {
                                Context applicationContext = c.this.l.getApplicationContext();
                                if (o.v(applicationContext) >= 24 && Build.VERSION.SDK_INT >= 24) {
                                    Uri uriForFile = MBFileProvider.getUriForFile(applicationContext, applicationContext.getApplicationContext().getPackageName() + ".mbFileProvider", new File(string));
                                    if (uriForFile != null) {
                                        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
                                        intent.addFlags(1);
                                    }
                                } else {
                                    intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                                }
                            } catch (Exception unused) {
                                intent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                            }
                            PendingIntent activity = PendingIntent.getActivity(c.this.l, 0, intent, 134217728);
                            Notification notificationA = aVar.r ? e.a(c.this.l, g.j(c.this.l.getBaseContext()), activity) : e.a(c.this.l, g.i(c.this.l.getBaseContext()), activity);
                            if (notificationA != null) {
                                notificationA.flags = 16;
                                try {
                                    c.this.g.notify(i + 1, notificationA);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                s.a(c.a, "Show new  notification....");
                            }
                            boolean zA = c.this.h.a(c.this.l);
                            s.a(c.a, String.format("isAppOnForeground = %1$B", Boolean.valueOf(zA)));
                            if (zA && !aVar.r) {
                                c.this.g.cancel(i + 1);
                                com.mbridge.msdk.click.c.c(c.this.l, aVar.g);
                                com.mbridge.msdk.click.c.a(c.this.l, Uri.fromFile(new File(string)), "", aVar.g);
                            }
                            s.b(c.a, String.format("%1$10s downloaded. Saved to: %2$s", aVar.c, string));
                            break;
                        } catch (Exception e2) {
                            s.d(c.a, "can not install. " + e2.getMessage());
                            c.this.g.cancel(i + 1);
                        }
                    } catch (Throwable unused2) {
                        return;
                    }
                    break;
                case 6:
                    b.a aVar2 = (b.a) message.obj;
                    int i2 = message.arg2;
                    String string2 = message.getData().getString(FileDownloadModel.FILENAME);
                    c.this.g.cancel(i2);
                    Notification notificationA2 = e.a(c.this.l, g.k(c.this.l.getBaseContext()), PendingIntent.getActivity(c.this.l, 0, new Intent(), 134217728));
                    if (notificationA2 != null) {
                        try {
                            c.this.g.notify(i2 + 1, notificationA2);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    String strReplace = string2.replace(".patch", ".apk");
                    String strA = com.mbridge.msdk.mbdownload.a.a(c.this.l);
                    e eVar = c.this.h;
                    Objects.requireNonNull(eVar);
                    eVar.new c(c.this.l, i2, aVar2, strReplace).execute(strA, strReplace, string2);
                    break;
            }
        }
    }

    public final void a(Service service) {
        this.l = service;
    }

    public final Service c() {
        return this.l;
    }

    static /* synthetic */ void a(c cVar, b.a aVar) {
        s.a(a, "startDownload([mComponentName:" + aVar.b + " mTitle:" + aVar.c + " mUrl:" + aVar.d + "])");
        e eVar = cVar.h;
        int iAbs = Math.abs((int) (((long) ((aVar.c.hashCode() >> 2) + (aVar.d.hashCode() >> 3))) + System.currentTimeMillis()));
        k kVar = new k(cVar, aVar, iAbs, 0);
        e.b bVar = new e.b(aVar, iAbs);
        cVar.i.a(iAbs);
        d.put(bVar.c, bVar);
        bVar.a = kVar;
        kVar.start();
        if (b) {
            int size = c.size();
            int size2 = d.size();
            s.b(a, "Client size =" + size + "   cacheSize = " + size2);
            if (size != size2) {
                throw new RuntimeException("Client size =" + size + "   cacheSize = " + size2);
            }
        }
        if (b) {
            for (int i = 0; i < d.size(); i++) {
                e.b bVarValueAt = d.valueAt(i);
                s.a(a, "Running task " + bVarValueAt.e.c);
            }
        }
    }
}
