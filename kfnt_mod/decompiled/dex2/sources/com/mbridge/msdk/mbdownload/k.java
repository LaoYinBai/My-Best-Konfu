package com.mbridge.msdk.mbdownload;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.b;
import com.mbridge.msdk.mbdownload.manager.ADownloadManager;
import com.mbridge.msdk.out.IDownloadListener;
import java.io.File;

/* compiled from: WorkThread.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class k extends Thread {
    public static int a = 1;
    public static int b = 2;
    public static int c = 3;
    public static int d = 4;
    private static final String e = "k";
    private static Boolean t = false;
    private c f;
    private Context g;
    private boolean h;
    private File i;
    private int j;
    private long k;
    private long l;
    private int n;
    private Handler o;
    private b.a p;
    private NotificationManager q;
    private IDownloadListener r;
    private int m = -1;
    private int s = 0;

    public k(c cVar, b.a aVar, int i, int i2) {
        String str;
        long[] jArr;
        this.j = 0;
        this.k = -1L;
        this.l = -1L;
        this.f = cVar;
        this.g = cVar.c().getApplicationContext();
        this.p = aVar;
        this.j = i2;
        this.q = (NotificationManager) cVar.c().getSystemService(RemoteMessageConst.NOTIFICATION);
        j.a();
        this.o = new Handler(this.f.c().getMainLooper());
        try {
            if (c.d.indexOfKey(i) >= 0 && (jArr = c.d.get(i).f) != null && jArr.length > 1) {
                this.k = jArr[0];
                this.l = jArr[1];
            }
            this.n = i;
            boolean[] zArr = new boolean[1];
            this.i = e.a("/apk", this.g, zArr);
            this.h = zArr[0];
            boolean z = this.h;
            b.a aVar2 = this.p;
            if (aVar2.f != null) {
                str = aVar2.f + ".apk.tmp";
            } else {
                str = e.a(aVar2.d) + ".apk.tmp";
            }
            this.i = new File(this.i, aVar2.b.equalsIgnoreCase("delta_update") ? str.replace(".apk", ".patch") : str);
        } catch (Exception e2) {
            s.b(e, e2.getMessage(), e2);
            this.f.a(this.n, e2);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.j = 0;
        try {
            if (this.f != null) {
                this.f.a(this.n);
            }
            a(this.k > 0);
            if (c.c.size() <= 0) {
                this.f.c().stopSelf();
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            if (MBridgeConstans.DEBUG) {
                th.printStackTrace();
            }
        }
        this.s = a;
    }

    public final void a(int i) {
        this.m = i;
        this.s = c;
        ADownloadManager.getInstance().pause(this.p.g);
        ADownloadManager.getInstance().deleteDownloadListener(this.p.g, this.r);
    }

    public final void a() {
        this.s = b;
        ADownloadManager.getInstance().pause(this.p.g);
    }

    public final void b() {
        this.s = a;
        a(false);
    }

    public final int c() {
        return this.s;
    }

    public final void d() {
        s.a("workthread", "=====installOrActive");
        String strH = com.mbridge.msdk.foundation.db.e.a(com.mbridge.msdk.foundation.db.i.a(this.g)).h(this.p.g);
        if (!com.mbridge.msdk.click.c.d(this.g, strH)) {
            com.mbridge.msdk.click.c.a(this.g, Uri.fromFile(this.i), this.p.d, this.p.g);
        } else {
            com.mbridge.msdk.click.c.f(this.g, strH);
        }
    }

    private void a(boolean z) {
        if (this.r == null) {
            this.r = new IDownloadListener() { // from class: com.mbridge.msdk.mbdownload.k.1
                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onStart() {
                    s.a("download workthread", "onstart");
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onProgressUpdate(int i) {
                    try {
                        if (k.this.f != null) {
                            k.this.f.a(k.this.n, i);
                        }
                        k.a(k.this, i);
                    } catch (RemoteException | IllegalStateException e2) {
                        e2.printStackTrace();
                    }
                    d.a(k.this.g).a(k.this.p.b, k.this.p.d, i);
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onEnd(int i, int i2, String str) {
                    s.a("download workthread", "onEnd:" + k.this.i);
                    try {
                        if (k.this.f == null || TextUtils.isEmpty(str)) {
                            return;
                        }
                        k.this.i = new File(str);
                        k.this.f.a(k.this.n, str);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onStatus(int i) {
                    if (i == 9) {
                        try {
                            if (k.this.f != null) {
                                k.this.f.b(k.this.n, i);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            };
        }
        ADownloadManager.getInstance().addDownloadListener(this.p.g, this.r);
        ADownloadManager.getInstance().start(this.p.g, this.p.d);
    }

    static /* synthetic */ void a(k kVar, int i) throws RemoteException {
        try {
            if (c.c.get(kVar.p) != null) {
                c.c.get(kVar.p).send(Message.obtain(null, 3, i, 0));
            }
        } catch (DeadObjectException unused) {
            s.d(e, String.format("Service Client for downloading %1$15s is dead. Removing messenger from the service", kVar.p.c));
            c.c.put(kVar.p, null);
        }
    }
}
