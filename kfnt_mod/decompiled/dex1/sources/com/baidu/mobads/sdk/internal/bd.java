package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mobads.sdk.internal.bh;
import com.baidu.mobads.sdk.internal.bq;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bd extends Thread {

    /* renamed from: b, reason: collision with root package name */
    private static final String f3153b = "ApkDownloadThread";

    /* renamed from: c, reason: collision with root package name */
    private static final int f3154c = 900000;
    private static volatile bd h;
    private volatile String d;
    private String e;
    private double f;
    private Handler g;
    private final Context i;
    private final bf k;
    private bq j = null;
    private bb l = bb.a();

    /* renamed from: a, reason: collision with root package name */
    bq.a f3155a = new be(this);

    public static bd a(Context context, bf bfVar, String str, Handler handler) {
        if (h == null) {
            h = new bd(context, bfVar, str, handler);
        }
        return h;
    }

    private bd(Context context, bf bfVar, String str, Handler handler) {
        this.e = null;
        this.i = context;
        this.k = bfVar;
        a(bfVar.c());
        this.g = handler;
        this.e = str;
    }

    public void a(String str) {
        this.d = str;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (b()) {
                try {
                    a();
                    this.l.a(f3153b, "download apk successfully, downloader exit");
                    h = null;
                } catch (IOException e) {
                    this.l.a(f3153b, "create File or HTTP Get failed, exception: " + e.getMessage());
                }
                this.l.a(f3153b, "no newer apk, downloader exit");
                h = null;
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, bf bfVar, String str2) {
        if (str.equals(bh.k) || str.equals(bh.l)) {
            Message messageObtainMessage = this.g.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putParcelable(bh.m, bfVar);
            bundle.putString(bh.n, str);
            messageObtainMessage.setData(bundle);
            this.g.sendMessage(messageObtainMessage);
        }
    }

    private String a() throws IOException {
        String str = "__xadsdk__remote__final__" + UUID.randomUUID().toString() + ".jar";
        String str2 = this.e + str;
        File file = new File(str2);
        try {
            file.createNewFile();
            this.j.a(this.e, str);
            return str2;
        } catch (IOException e) {
            file.delete();
            throw e;
        }
    }

    private boolean b() throws bh.a {
        double d;
        try {
            try {
                this.j = new bq(this.i, new URL(this.d), this.k, this.f3155a);
            } catch (MalformedURLException unused) {
                this.j = new bq(this.i, this.d, this.k, this.f3155a);
            }
            if (bh.q != null) {
                d = bh.q.f3137b;
            } else if (bh.p == null) {
                d = 0.0d;
            } else if (bh.p.f3137b > 0.0d) {
                d = bh.p.f3137b;
            } else {
                d = bh.p.f3137b;
            }
            this.l.a(f3153b, "isNewApkAvailable: local apk version is: " + d + ", remote apk version: " + this.k.b());
            if (d > 0.0d) {
                if (this.k.b() > 0.0d) {
                    this.l.a(f3153b, "remote not null, local apk version is null, force upgrade");
                    this.f = this.k.b();
                    return true;
                }
                this.l.a(f3153b, "remote is null, local apk version is null, do not upgrade");
                return false;
            }
            if (this.k.b() > 0.0d) {
                if (this.k.b() <= d) {
                    return false;
                }
                this.f = this.k.b();
                return true;
            }
            this.l.a(f3153b, "remote apk version is: null, local apk version is: " + d + ", do not upgrade");
            return false;
        } catch (Exception e) {
            String str = "parse apk failed, error:" + e.toString();
            this.l.a(f3153b, str);
            throw new bh.a(str);
        }
    }
}
