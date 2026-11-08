package com.mbridge.msdk.videocommon.download;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.t;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.download.DownloadError;
import com.mbridge.msdk.foundation.download.DownloadMessage;
import com.mbridge.msdk.foundation.download.DownloadPriority;
import com.mbridge.msdk.foundation.download.DownloadProgress;
import com.mbridge.msdk.foundation.download.DownloadResourceType;
import com.mbridge.msdk.foundation.download.DownloadStatus;
import com.mbridge.msdk.foundation.download.MBDownloadManager;
import com.mbridge.msdk.foundation.download.OnDownloadStateListener;
import com.mbridge.msdk.foundation.download.OnProgressStateListener;
import com.mbridge.msdk.foundation.download.core.DownloadRequest;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.b.e;
import com.mbridge.msdk.foundation.tools.p;
import com.mbridge.msdk.foundation.tools.q;
import com.mbridge.msdk.foundation.tools.s;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: CampaignDownLoadTask.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements Serializable {
    private boolean A;
    private int B;
    private File C;
    private int b;
    private int c;
    private volatile int e;
    private com.mbridge.msdk.videocommon.listener.a g;
    private com.mbridge.msdk.videocommon.listener.a h;
    private CampaignEx i;
    private String j;
    private Context k;
    private long l;
    private String m;
    private String n;
    private long o;
    private String p;
    private long r;
    private t u;
    private String w;
    private DownloadRequest x;
    private DownloadMessage y;
    private int z;
    private boolean a = false;
    private boolean d = false;
    private CopyOnWriteArrayList<c> f = new CopyOnWriteArrayList<>();
    private boolean q = false;
    private int s = 100;
    private boolean t = false;
    private boolean v = false;
    private int D = 1;
    private OnDownloadStateListener E = new OnDownloadStateListener() { // from class: com.mbridge.msdk.videocommon.download.a.1
        @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
        public final void onCancelDownload(DownloadMessage downloadMessage) {
        }

        @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
        public final void onDownloadStart(DownloadMessage downloadMessage) {
            a.this.e = 1;
            if (a.this.u == null) {
                a.this.u = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            }
            a.this.u.a(a.this.i, a.this.l, a.this.p, a.this.e);
        }

        @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
        public final void onDownloadComplete(DownloadMessage downloadMessage) {
            try {
                s.d("CampaignDownLoadTask", "onDownloadComplete callback : " + a.this.o + "    " + a.this.l);
                if (a.this.u == null) {
                    a.this.u = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                }
                a.this.u.a(a.this.m, a.this.o, 5);
                String videoMD5Value = a.this.i.getVideoMD5Value();
                if (TextUtils.isEmpty(videoMD5Value)) {
                    a.this.a(a.this.o, false);
                    a.this.c(a.this.f(3));
                    return;
                }
                try {
                    if (videoMD5Value.equals(p.a(new File(a.this.n)))) {
                        a.this.a(a.this.o, true);
                        a.this.c(a.this.f(3));
                        return;
                    }
                } catch (Throwable th) {
                    s.b("CampaignDownLoadTask", th.getMessage(), th);
                }
                a.this.a("Video download complete but MD5 check failed");
            } catch (Exception e) {
                s.d("CampaignDownLoadTask", e.getMessage());
            }
        }

        @Override // com.mbridge.msdk.foundation.download.OnDownloadStateListener
        public final void onDownloadError(DownloadMessage downloadMessage, DownloadError downloadError) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
            if (a.this.x == null || a.this.x.getStatus() == DownloadStatus.CANCELLED) {
                return;
            }
            String message = "Video Download Error";
            if (downloadError != null && downloadError.getException() != null) {
                message = downloadError.getException().getMessage();
            }
            a.this.p();
            a.this.a(3, message);
            a.this.a(message);
        }
    };
    private OnProgressStateListener F = new OnProgressStateListener() { // from class: com.mbridge.msdk.videocommon.download.a.2
        @Override // com.mbridge.msdk.foundation.download.OnProgressStateListener
        public final void onProgress(DownloadMessage downloadMessage, DownloadProgress downloadProgress) {
            try {
                a.this.o = downloadProgress.getCurrent();
                a.this.l = downloadProgress.getTotal();
                a.this.z = downloadProgress.getCurrentDownloadRate();
                if (a.this.s != 100 && downloadProgress.getCurrentDownloadRate() >= a.this.s) {
                    s.d("CampaignDownLoadTask", "Rate : " + downloadProgress.getCurrentDownloadRate() + " ReadyRate & cdRate & ctnType = " + a.this.s + " " + a.this.c + " " + a.this.D);
                    if (a.this.d) {
                        return;
                    }
                    a.this.d = true;
                    if (!a.this.A) {
                        a.this.c(a.this.f(1));
                        a.this.a(downloadProgress.getCurrent(), false);
                    }
                    if (a.this.x == null || a.this.D != 2 || a.this.A) {
                        return;
                    }
                    a.this.x.cancel(downloadMessage);
                }
            } catch (Exception e) {
                s.d("CampaignDownLoadTask", e.getMessage());
            }
        }
    };

    public a(Context context, CampaignEx campaignEx, String str, int i) throws IOException {
        this.b = 1;
        this.e = 0;
        this.o = 0L;
        if (context == null && campaignEx == null) {
            return;
        }
        this.r = System.currentTimeMillis();
        this.k = com.mbridge.msdk.foundation.controller.a.f().j();
        this.i = campaignEx;
        this.j = str;
        this.b = i;
        if (this.i != null) {
            this.m = this.i.getVideoUrlEncode();
        }
        this.w = q.d(this.m);
        this.n = e.b(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_VC) + File.separator;
        this.p = this.n + this.w;
        s.b("CampaignDownLoadTask", this.i.getAppName() + " videoLocalPath:" + this.p + " videoUrl: " + this.i.getVideoUrlEncode() + " " + this.s);
        try {
            if (TextUtils.isEmpty(this.m)) {
                return;
            }
            File file = null;
            if (!TextUtils.isEmpty(this.n)) {
                file = new File(this.n);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            if (file != null && file.exists() && (this.C == null || !this.C.exists())) {
                this.C = new File(file + "/.nomedia");
                if (!this.C.exists()) {
                    this.C.createNewFile();
                }
            }
            t tVarA = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            com.mbridge.msdk.foundation.entity.p pVarA = tVarA.a(this.m, "");
            if (pVarA != null) {
                this.o = pVarA.b();
                if (this.e != 2) {
                    this.e = pVarA.d();
                }
                if (this.e == 1) {
                    this.e = 2;
                }
                this.l = pVarA.c();
                if (pVarA.a() > 0) {
                    this.r = pVarA.a();
                }
                if (this.e == 5) {
                    if (new File(this.n + this.w).exists()) {
                        this.p = this.n + this.w;
                    } else {
                        o();
                    }
                } else if (this.e != 0) {
                    this.p = this.n + this.w;
                }
            } else {
                tVarA.a(this.m, this.r);
            }
            String str2 = this.m;
            if (TextUtils.isEmpty(str2)) {
                this.h.a("VideoUrl is NULL, Please check it.", "");
                return;
            }
            if (this.e == 1) {
                s.a("CampaignDownLoadTask", "Run : Task is RUNNING, Will return.");
                return;
            }
            if (this.e == 5) {
                s.a("CampaignDownLoadTask", "Run : Video Done, Will callback.");
                if (this.g != null) {
                    this.g.a(this.m);
                }
                if (this.h != null) {
                    this.h.a(this.m);
                    return;
                }
                return;
            }
            if (this.b == 3) {
                s.a("CampaignDownLoadTask", "Run : Dlnet is 3, Will callback.");
                a(0L, false);
                return;
            }
            if (this.i.getReady_rate() == 0) {
                if (this.g != null) {
                    this.g.a(this.m);
                }
                if (this.h != null) {
                    this.h.a(this.m);
                }
            }
            this.y = new DownloadMessage(new Object(), str2, this.w, 100, DownloadResourceType.DOWNLOAD_RESOURCE_TYPE_VIDEO);
            this.x = MBDownloadManager.getInstance().download(this.y).withReadTimeout(30000L).withConnectTimeout(20000L).withDownloadPriority(DownloadPriority.HIGH).withHttpRetryCounter(1).withDirectoryPathInternal(this.n).withDownloadStateListener(this.E).withProgressStateListener(this.F).build();
        } catch (Exception e) {
            s.b("CampaignDownLoadTask", e.getMessage());
        }
    }

    public final String a() {
        return this.m;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final boolean b() {
        return this.v;
    }

    public final void a(boolean z) {
        this.v = z;
    }

    public final long c() {
        return this.r;
    }

    public final void b(boolean z) {
        this.q = z;
    }

    public final String d() {
        return this.p;
    }

    public final long e() {
        return this.l;
    }

    public final int f() {
        return this.e;
    }

    public final void a(int i, int i2) {
        this.e = i;
        if (this.u == null) {
            this.u = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        this.u.a(this.m, i2, i);
    }

    public final CampaignEx g() {
        return this.i;
    }

    public final void a(CampaignEx campaignEx) {
        this.i = campaignEx;
    }

    public final void c(int i) {
        this.s = i;
    }

    public final long h() {
        return this.o;
    }

    public final void d(int i) {
        this.B = i;
    }

    public final void e(int i) {
        this.D = i;
    }

    public final void a(long j, boolean z) {
        s.d("CampaignDownLoadTask", "setStateToDone 下载完成  ： mProgressSize" + this.o + "  progressSize " + j + "  " + this.z + "%   FileSize : " + this.l + "  " + this.i.getAppName());
        if (this.s == 100 && this.b != 3 && j != this.l && !z) {
            File file = new File(this.p);
            s.d("CampaignDownLoadTask", "progressSize = " + j + " fileSize = " + this.l + " " + z + " absFileSize = " + (file.exists() ? file.length() : 0L));
            a("File size is not match witch download size.");
            return;
        }
        this.e = 5;
        a(1, "");
        if (this.l != 0 && this.u != null) {
            this.u.b(this.m, this.l);
        }
        this.a = false;
        a(j, this.e);
    }

    public final void a(String str) {
        if (this.g != null) {
            this.g.a(str, this.m);
        }
        if (this.h != null) {
            this.h.a(str, this.m);
        }
        s.d("CampaignDownLoadTask", "Video download stop : " + str);
        if (this.e == 4 || this.e == 2 || this.e == 5) {
            return;
        }
        this.e = 4;
        a(this.o, this.e);
    }

    public final void i() {
        s.d("CampaignDownLoadTask", "start()");
        try {
            if (this.m != null) {
                s.d("CampaignDownLoadTask", new URL(this.m).getPath());
            }
            if (this.s == 0 && this.D == 2) {
                s.d("CampaignDownLoadTask", "Can not start download because readyRate is 0 and videoCtnType is 2");
            } else if (this.x != null) {
                this.x.start();
            }
        } catch (Throwable unused) {
            if (this.g != null) {
                this.g.a("VideoUrl is not illegal, Please check it.", "");
            }
            if (this.h != null) {
                this.h.a("VideoUrl is not illegal, Please check it.", "");
            }
        }
    }

    public final void j() {
        try {
            this.A = true;
            if (this.D == 1) {
                s.d("CampaignDownLoadTask", "Can not call resume(), because videoCtnType = " + this.D);
                return;
            }
            s.d("CampaignDownLoadTask", "resume()");
            if (this.y == null) {
                this.y = new DownloadMessage(new Object(), this.m, this.w, 100, DownloadResourceType.DOWNLOAD_RESOURCE_TYPE_VIDEO);
            }
            this.x = MBDownloadManager.getInstance().download(this.y).withReadTimeout(30000L).withConnectTimeout(20000L).withDownloadPriority(DownloadPriority.HIGH).withHttpRetryCounter(1).withDirectoryPathInternal(this.n).withDownloadStateListener(this.E).withProgressStateListener(this.F).build();
            this.x.start();
            c(f(2));
        } catch (Exception e) {
            s.d("CampaignDownLoadTask", e.getMessage());
        }
    }

    public final void b(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        p();
        a(2, str);
        this.e = 4;
    }

    public final void a(c cVar) {
        if (this.f != null) {
            this.f.add(cVar);
        }
    }

    public final String k() {
        String message;
        if (this.b == 3) {
            return "";
        }
        String str = this.n + this.w;
        File file = new File(str);
        try {
            if (!file.exists()) {
                message = "file is not exist ";
            } else if (!file.isFile()) {
                message = "file is not file ";
            } else if (!file.canRead()) {
                message = "file can not read ";
            } else if (file.length() > 0) {
                this.p = str;
                message = "";
            } else {
                message = "file length is 0 ";
            }
        } catch (Throwable th) {
            s.d("CampaignDownLoadTask", th.getMessage());
            message = th.getMessage();
        }
        if (this.e == 5 && !TextUtils.isEmpty(message)) {
            o();
        }
        return message;
    }

    public final void a(com.mbridge.msdk.videocommon.listener.a aVar) {
        this.g = aVar;
    }

    public final void b(com.mbridge.msdk.videocommon.listener.a aVar) {
        this.h = aVar;
    }

    private void a(long j, int i) {
        this.o = j;
        if (100 * j >= this.s * this.l && !this.t && i != 4) {
            if (this.s == 100 && i != 5) {
                this.e = 5;
                return;
            }
            this.t = true;
            s.d("CampaignDownLoadTask", "UpdateListener : state: " + i + " progress : " + j);
            String strK = k();
            if (TextUtils.isEmpty(strK)) {
                if (this.g != null) {
                    this.g.a(this.m);
                }
                if (this.h != null) {
                    this.h.a(this.m);
                }
            } else {
                if (this.g != null) {
                    this.g.a("file is not effective " + strK, this.m);
                }
                if (this.h != null) {
                    this.h.a("file is not effective " + strK, this.m);
                }
            }
        }
        if (!this.a && j > 0) {
            this.a = true;
            if (this.u == null) {
                this.u = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            }
            this.u.a(this.m, j, this.e);
        }
        if (this.f != null) {
            Iterator<c> it = this.f.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next != null) {
                    next.a(j, i);
                }
            }
        }
    }

    public final void l() {
        if (this.f != null) {
            this.f = null;
        }
    }

    private void o() {
        if (this.u == null) {
            this.u = t.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        }
        try {
            try {
                this.u.b(this.m);
                File file = new File(this.p);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            } catch (Throwable unused) {
                s.d("CampaignDownLoadTask", "del DB or file failed");
            }
        } finally {
            this.e = 0;
        }
    }

    public final void m() {
        try {
            try {
                o();
            } catch (Exception unused) {
                s.d("CampaignDownLoadTask", "del file is failed");
            }
            if (this.i == null || this.i.getPlayable_ads_without_video() != 2) {
                com.mbridge.msdk.videocommon.a.a aVarA = com.mbridge.msdk.videocommon.a.a.a();
                if (aVarA != null) {
                    aVarA.a(this.i);
                }
            }
        } finally {
            this.e = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.reward.a.a");
            cls.getMethod("insertExcludeId", String.class, CampaignEx.class).invoke(cls.newInstance(), this.j, this.i);
            Class<?> cls2 = Class.forName("com.mbridge.msdk.mbnative.c.b");
            cls2.getMethod("insertExcludeId", String.class, CampaignEx.class).invoke(cls2.newInstance(), this.j, this.i);
        } catch (Exception e) {
            s.d("CampaignDownLoadTask", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        u uVarA = u.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        com.mbridge.msdk.foundation.entity.q qVar = new com.mbridge.msdk.foundation.entity.q(this.k, this.i, i, Long.toString(this.r != 0 ? System.currentTimeMillis() - this.r : 0L), this.l, this.B);
        qVar.n(this.i.getId());
        qVar.e(this.i.getVideoUrlEncode());
        qVar.p(str);
        qVar.k(this.i.getRequestId());
        qVar.m(this.j);
        uVarA.a(qVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                com.mbridge.msdk.foundation.same.report.a.a().a(str);
                return;
            }
            new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j()).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, com.mbridge.msdk.foundation.same.report.d.a(str, com.mbridge.msdk.foundation.controller.a.f().j(), this.j), null);
        } catch (Exception e) {
            s.d("CampaignDownLoadTask", e.getMessage());
        }
    }

    public final String n() {
        try {
            File file = new File(this.p);
            if (file.exists() && file.isFile()) {
                return this.p;
            }
        } catch (Exception e) {
            s.d("CampaignDownLoadTask", e.getMessage());
        }
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String f(int i) {
        if (this.i == null || TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.i.getRequestId()) || TextUtils.isEmpty(this.i.getVideoUrlEncode())) {
            return "";
        }
        return "key=2000077" + ContainerUtils.FIELD_DELIMITER + "unit_id=" + this.j + ContainerUtils.FIELD_DELIMITER + "rid=" + this.i.getRequestId() + ContainerUtils.FIELD_DELIMITER + "rid_n=" + this.i.getRequestIdNotice() + ContainerUtils.FIELD_DELIMITER + "package_name=" + com.mbridge.msdk.foundation.controller.a.f().d() + ContainerUtils.FIELD_DELIMITER + "app_id=" + com.mbridge.msdk.foundation.controller.a.f().k() + ContainerUtils.FIELD_DELIMITER + "video_url=" + URLEncoder.encode(this.i.getVideoUrlEncode()) + ContainerUtils.FIELD_DELIMITER + "process_size=" + this.o + ContainerUtils.FIELD_DELIMITER + "file_size=" + this.l + ContainerUtils.FIELD_DELIMITER + "ready_rate=" + this.s + ContainerUtils.FIELD_DELIMITER + "cd_rate=" + this.c + ContainerUtils.FIELD_DELIMITER + "cid=" + this.i.getId() + ContainerUtils.FIELD_DELIMITER + "type=" + this.e + ContainerUtils.FIELD_DELIMITER + "video_download_status=" + i;
    }
}
