package com.xiaomi.push.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ai;
import com.xiaomi.push.cu;
import com.xiaomi.push.dd;
import com.xiaomi.push.eh;
import com.xiaomi.push.er;
import com.xiaomi.push.es;
import com.xiaomi.push.ex;
import com.xiaomi.push.fg;
import com.xiaomi.push.fn;
import com.xiaomi.push.fp;
import com.xiaomi.push.fq;
import com.xiaomi.push.fs;
import com.xiaomi.push.fu;
import com.xiaomi.push.fv;
import com.xiaomi.push.ga;
import com.xiaomi.push.ge;
import com.xiaomi.push.gf;
import com.xiaomi.push.gg;
import com.xiaomi.push.gi;
import com.xiaomi.push.gu;
import com.xiaomi.push.hb;
import com.xiaomi.push.hd;
import com.xiaomi.push.he;
import com.xiaomi.push.hh;
import com.xiaomi.push.hj;
import com.xiaomi.push.hn;
import com.xiaomi.push.ho;
import com.xiaomi.push.ii;
import com.xiaomi.push.ij;
import com.xiaomi.push.it;
import com.xiaomi.push.iz;
import com.xiaomi.push.service.av;
import com.xiaomi.push.service.h;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class XMPushService extends Service implements fs {

    /* renamed from: a, reason: collision with other field name */
    private ContentObserver f832a;

    /* renamed from: a, reason: collision with other field name */
    private fn f834a;

    /* renamed from: a, reason: collision with other field name */
    private fp f835a;

    /* renamed from: a, reason: collision with other field name */
    private fq f836a;

    /* renamed from: a, reason: collision with other field name */
    private e f838a;

    /* renamed from: a, reason: collision with other field name */
    private p f839a;

    /* renamed from: a, reason: collision with other field name */
    private bf f841a;

    /* renamed from: a, reason: collision with other field name */
    private com.xiaomi.push.service.e f842a;

    /* renamed from: a, reason: collision with other field name */
    private String f845a;

    /* renamed from: b, reason: collision with other field name */
    private ContentObserver f848b;
    private int a = 0;
    private int b = 0;

    /* renamed from: a, reason: collision with other field name */
    private long f831a = 0;

    /* renamed from: a, reason: collision with other field name */
    protected Class f844a = XMJobService.class;

    /* renamed from: a, reason: collision with other field name */
    private au f840a = null;

    /* renamed from: a, reason: collision with other field name */
    private com.xiaomi.push.service.h f843a = null;

    /* renamed from: a, reason: collision with other field name */
    Messenger f833a = null;

    /* renamed from: a, reason: collision with other field name */
    private Collection<ai> f847a = Collections.synchronizedCollection(new ArrayList());

    /* renamed from: a, reason: collision with other field name */
    private ArrayList<l> f846a = new ArrayList<>();

    /* renamed from: a, reason: collision with other field name */
    private fu f837a = new bt(this);

    class a extends i {

        /* renamed from: a, reason: collision with other field name */
        av.b f849a;

        public a(av.b bVar) {
            super(9);
            this.f849a = null;
            this.f849a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "bind the client. " + this.f849a.g;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            String str;
            try {
                if (!XMPushService.this.m574c()) {
                    com.xiaomi.channel.commonutils.logger.b.d("trying bind while the connection is not created, quit!");
                    return;
                }
                av.b bVarA = av.a().a(this.f849a.g, this.f849a.f906b);
                if (bVarA == null) {
                    str = "ignore bind because the channel " + this.f849a.g + " is removed ";
                } else if (bVarA.f901a == av.c.unbind) {
                    bVarA.a(av.c.binding, 0, 0, (String) null, (String) null);
                    XMPushService.this.f835a.a(bVarA);
                    hd.a(XMPushService.this, bVarA);
                    return;
                } else {
                    str = "trying duplicate bind, ingore! " + bVarA.f901a;
                }
                com.xiaomi.channel.commonutils.logger.b.m22a(str);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                XMPushService.this.a(10, e);
            } catch (Throwable unused) {
            }
        }
    }

    static class b extends i {
        private final av.b a;

        public b(av.b bVar) {
            super(12);
            this.a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "bind time out. chid=" + this.a.g;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            this.a.a(av.c.unbind, 1, 21, (String) null, (String) null);
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return TextUtils.equals(((b) obj).a.g, this.a.g);
            }
            return false;
        }

        public int hashCode() {
            return this.a.g.hashCode();
        }
    }

    class c extends i {
        private fg a;

        public c(fg fgVar) {
            super(8);
            this.a = null;
            this.a = fgVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "receive a message.";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.f840a.a(this.a);
        }
    }

    public class d extends i {
        d() {
            super(1);
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "do reconnect..";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            if (XMPushService.this.m570a() && XMPushService.this.a(XMPushService.this.getApplicationContext())) {
                XMPushService.this.f();
            } else {
                com.xiaomi.channel.commonutils.logger.b.m22a("should not connect. quit the job.");
            }
        }
    }

    class e extends BroadcastReceiver {
        e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    public class f extends i {

        /* renamed from: a, reason: collision with other field name */
        public Exception f851a;
        public int b;

        f(int i, Exception exc) {
            super(2);
            this.b = i;
            this.f851a = exc;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "disconnect the connection.";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.a(this.b, this.f851a);
        }
    }

    class g extends i {
        g() {
            super(65535);
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "Init Job";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.c();
        }
    }

    class h extends i {
        private Intent a;

        public h(Intent intent) {
            super(15);
            this.a = null;
            this.a = intent;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "Handle intent action = " + this.a.getAction();
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() throws PackageManager.NameNotFoundException, NumberFormatException {
            XMPushService.this.c(this.a);
        }
    }

    public static abstract class i extends h.b {
        public i(int i) {
            super(i);
        }

        public abstract String a();

        /* renamed from: a */
        public abstract void mo342a();

        @Override // java.lang.Runnable
        public void run() {
            if (this.a != 4 && this.a != 8) {
                com.xiaomi.channel.commonutils.logger.b.m23a(com.xiaomi.channel.commonutils.logger.a.a, a());
            }
            mo342a();
        }
    }

    class j extends i {
        public j() {
            super(5);
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "ask the job queue to quit";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.f843a.m627a();
        }
    }

    class k extends i {
        private gg a;

        public k(gg ggVar) {
            super(8);
            this.a = null;
            this.a = ggVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "receive a message.";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.f840a.a(this.a);
        }
    }

    public interface l {
        /* renamed from: a */
        void mo381a();
    }

    class m extends i {

        /* renamed from: a, reason: collision with other field name */
        boolean f854a;

        public m(boolean z) {
            super(4);
            this.f854a = z;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "send ping..";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            if (XMPushService.this.m574c()) {
                try {
                    if (!this.f854a) {
                        hd.a();
                    }
                    XMPushService.this.f835a.b(this.f854a);
                } catch (ga e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    XMPushService.this.a(10, e);
                }
            }
        }
    }

    class n extends i {

        /* renamed from: a, reason: collision with other field name */
        av.b f855a;

        public n(av.b bVar) {
            super(4);
            this.f855a = null;
            this.f855a = bVar;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "rebind the client. " + this.f855a.g;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            try {
                this.f855a.a(av.c.unbind, 1, 16, (String) null, (String) null);
                XMPushService.this.f835a.a(this.f855a.g, this.f855a.f906b);
                this.f855a.a(av.c.binding, 1, 16, (String) null, (String) null);
                XMPushService.this.f835a.a(this.f855a);
            } catch (ga e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                XMPushService.this.a(10, e);
            }
        }
    }

    class o extends i {
        o() {
            super(3);
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "reset the connection.";
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            XMPushService.this.a(11, (Exception) null);
            if (XMPushService.this.m570a() && XMPushService.this.a(XMPushService.this.getApplicationContext())) {
                XMPushService.this.f();
            }
        }
    }

    class p extends BroadcastReceiver {
        p() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    class q extends i {

        /* renamed from: a, reason: collision with other field name */
        av.b f856a;

        /* renamed from: a, reason: collision with other field name */
        String f857a;
        int b;

        /* renamed from: b, reason: collision with other field name */
        String f858b;

        public q(av.b bVar, int i, String str, String str2) {
            super(9);
            this.f856a = null;
            this.f856a = bVar;
            this.b = i;
            this.f857a = str;
            this.f858b = str2;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        public String a() {
            return "unbind the channel. " + this.f856a.g;
        }

        @Override // com.xiaomi.push.service.XMPushService.i
        /* renamed from: a */
        public void mo342a() {
            if (this.f856a.f901a != av.c.unbind && XMPushService.this.f835a != null) {
                try {
                    XMPushService.this.f835a.a(this.f856a.g, this.f856a.f906b);
                } catch (ga e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    XMPushService.this.a(10, e);
                }
            }
            this.f856a.a(av.c.unbind, this.b, 0, this.f858b, this.f857a);
        }
    }

    static {
        cu.a("cn.app.chat.xiaomi.net", "cn.app.chat.xiaomi.net");
    }

    private gg a(gg ggVar, String str, String str2) {
        StringBuilder sb;
        String str3;
        av avVarA = av.a();
        List<String> listM606a = avVarA.m606a(str);
        if (listM606a.isEmpty()) {
            sb = new StringBuilder();
            str3 = "open channel should be called first before sending a packet, pkg=";
        } else {
            ggVar.o(str);
            str = ggVar.k();
            if (TextUtils.isEmpty(str)) {
                str = listM606a.get(0);
                ggVar.l(str);
            }
            av.b bVarA = avVarA.a(str, ggVar.m());
            if (!m574c()) {
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not connected, chid=";
            } else {
                if (bVarA != null && bVarA.f901a == av.c.binded) {
                    if (TextUtils.equals(str2, bVarA.i)) {
                        return ggVar;
                    }
                    sb = new StringBuilder();
                    sb.append("invalid session. ");
                    sb.append(str2);
                    com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
                    return null;
                }
                sb = new StringBuilder();
                str3 = "drop a packet as the channel is not opened, chid=";
            }
        }
        sb.append(str3);
        sb.append(str);
        com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
        return null;
    }

    private av.b a(String str, Intent intent) {
        av.b bVarA = av.a().a(str, intent.getStringExtra(az.p));
        if (bVarA == null) {
            bVarA = new av.b(this);
        }
        bVarA.g = intent.getStringExtra(az.r);
        bVarA.f906b = intent.getStringExtra(az.p);
        bVarA.c = intent.getStringExtra(az.t);
        bVarA.f903a = intent.getStringExtra(az.z);
        bVarA.e = intent.getStringExtra(az.x);
        bVarA.f = intent.getStringExtra(az.y);
        bVarA.f905a = intent.getBooleanExtra(az.w, false);
        bVarA.h = intent.getStringExtra(az.v);
        bVarA.i = intent.getStringExtra(az.C);
        bVarA.d = intent.getStringExtra(az.u);
        bVarA.f902a = this.f842a;
        bVarA.a((Messenger) intent.getParcelableExtra(az.G));
        bVarA.f895a = getApplicationContext();
        av.a().a(bVarA);
        return bVarA;
    }

    private String a() {
        String strB;
        com.xiaomi.push.ao.a();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = new Object();
        String strName = null;
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            bc bcVarA = bc.a(this);
            strB = null;
            while (true) {
                if (!TextUtils.isEmpty(strB) && bcVarA.a() != 0) {
                    break;
                }
                if (TextUtils.isEmpty(strB)) {
                    strB = com.xiaomi.push.l.m545a("ro.miui.region");
                    if (TextUtils.isEmpty(strB)) {
                        strB = com.xiaomi.push.l.m545a("ro.product.locale.region");
                    }
                }
                try {
                    synchronized (obj) {
                        obj.wait(100L);
                    }
                } catch (InterruptedException unused) {
                }
            }
        } else {
            strB = com.xiaomi.push.l.b();
        }
        if (!TextUtils.isEmpty(strB)) {
            com.xiaomi.push.service.a.a(getApplicationContext()).b(strB);
            strName = com.xiaomi.push.l.a(strB).name();
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("wait region :" + strName + " cost = " + (SystemClock.elapsedRealtime() - jElapsedRealtime));
        return strName;
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e2) {
                com.xiaomi.channel.commonutils.logger.b.a(e2);
            }
        }
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra(az.z);
        String stringExtra2 = intent.getStringExtra(az.C);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        av avVarA = av.a();
        fg fgVarA = null;
        if (bundleExtra != null) {
            gf gfVar = (gf) a(new gf(bundleExtra), stringExtra, stringExtra2);
            if (gfVar == null) {
                return;
            } else {
                fgVarA = fg.a(gfVar, avVarA.a(gfVar.k(), gfVar.m()).h);
            }
        } else {
            byte[] byteArrayExtra = intent.getByteArrayExtra("ext_raw_packet");
            if (byteArrayExtra != null) {
                long longExtra = intent.getLongExtra(az.p, 0L);
                String stringExtra3 = intent.getStringExtra(az.q);
                String stringExtra4 = intent.getStringExtra("ext_chid");
                av.b bVarA = avVarA.a(stringExtra4, Long.toString(longExtra));
                if (bVarA != null) {
                    fg fgVar = new fg();
                    try {
                        fgVar.a(Integer.parseInt(stringExtra4));
                    } catch (NumberFormatException unused) {
                    }
                    fgVar.a("SECMSG", (String) null);
                    fgVar.a(longExtra, "xiaomi.com", stringExtra3);
                    fgVar.a(intent.getStringExtra("ext_pkt_id"));
                    fgVar.a(byteArrayExtra, bVarA.h);
                    fgVarA = fgVar;
                }
            }
        }
        if (fgVarA != null) {
            c(new bg(this, fgVarA));
        }
    }

    private void a(Intent intent, int i2) {
        byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
        boolean booleanExtra = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        ii iiVar = new ii();
        try {
            it.a(iiVar, byteArrayExtra);
            com.xiaomi.push.ai.a(getApplicationContext()).a((ai.a) new com.xiaomi.push.service.b(iiVar, new WeakReference(this), booleanExtra), i2);
        } catch (iz unused) {
            com.xiaomi.channel.commonutils.logger.b.d("aw_ping : send help app ping  error");
        }
    }

    private void a(String str, int i2) {
        Collection<av.b> collectionM605a = av.a().m605a(str);
        if (collectionM605a != null) {
            for (av.b bVar : collectionM605a) {
                if (bVar != null) {
                    a(new q(bVar, i2, null, null));
                }
            }
        }
        av.a().m608a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context) throws InterruptedException {
        try {
            com.xiaomi.push.ao.a();
            for (int i2 = 100; i2 > 0; i2--) {
                if (com.xiaomi.push.bg.c(context)) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("network connectivity ok.");
                    return true;
                }
                try {
                    Thread.sleep(100L);
                } catch (Exception unused) {
                }
            }
            return false;
        } catch (Exception unused2) {
            return true;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    private boolean m559a(String str, Intent intent) {
        av.b bVarA = av.a().a(str, intent.getStringExtra(az.p));
        boolean z = false;
        if (bVarA == null || str == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(az.C);
        String stringExtra2 = intent.getStringExtra(az.v);
        if (!TextUtils.isEmpty(bVarA.i) && !TextUtils.equals(stringExtra, bVarA.i)) {
            com.xiaomi.channel.commonutils.logger.b.m22a("session changed. old session=" + bVarA.i + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(bVarA.h)) {
            return z;
        }
        com.xiaomi.channel.commonutils.logger.b.m22a("security changed. chid = " + str + " sechash = " + com.xiaomi.push.bl.a(stringExtra2));
        return true;
    }

    /* renamed from: a, reason: collision with other method in class */
    private int[] m560a() {
        String[] strArrSplit;
        String strA = aq.a(getApplicationContext()).a(ho.FallDownTimeRange.a(), "");
        if (!TextUtils.isEmpty(strA) && (strArrSplit = strA.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) != null && strArrSplit.length >= 2) {
            int[] iArr = new int[2];
            try {
                iArr[0] = Integer.valueOf(strArrSplit[0]).intValue();
                iArr[1] = Integer.valueOf(strArrSplit[1]).intValue();
                if (iArr[0] >= 0 && iArr[0] <= 23 && iArr[1] >= 0 && iArr[1] <= 23) {
                    if (iArr[0] != iArr[1]) {
                        return iArr;
                    }
                }
            } catch (NumberFormatException e2) {
                com.xiaomi.channel.commonutils.logger.b.d("parse falldown time range failure: " + e2);
            }
        }
        return null;
    }

    private void b(Intent intent) {
        String stringExtra = intent.getStringExtra(az.z);
        String stringExtra2 = intent.getStringExtra(az.C);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        gf[] gfVarArr = new gf[parcelableArrayExtra.length];
        intent.getBooleanExtra("ext_encrypt", true);
        for (int i2 = 0; i2 < parcelableArrayExtra.length; i2++) {
            gfVarArr[i2] = new gf((Bundle) parcelableArrayExtra[i2]);
            gfVarArr[i2] = (gf) a(gfVarArr[i2], stringExtra, stringExtra2);
            if (gfVarArr[i2] == null) {
                return;
            }
        }
        av avVarA = av.a();
        fg[] fgVarArr = new fg[gfVarArr.length];
        for (int i3 = 0; i3 < gfVarArr.length; i3++) {
            gf gfVar = gfVarArr[i3];
            fgVarArr[i3] = fg.a(gfVar, avVarA.a(gfVar.k(), gfVar.m()).h);
        }
        c(new com.xiaomi.push.service.c(this, fgVarArr));
    }

    private void b(boolean z) {
        this.f831a = System.currentTimeMillis();
        if (m574c()) {
            if (com.xiaomi.push.bg.b(this)) {
                c(new m(z));
                return;
            }
            c(new f(17, null));
        }
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String str;
        com.xiaomi.push.service.a aVarA = com.xiaomi.push.service.a.a(getApplicationContext());
        String strA = aVarA.a();
        com.xiaomi.channel.commonutils.logger.b.m22a("region of cache is " + strA);
        if (TextUtils.isEmpty(strA)) {
            strA = a();
        }
        if (TextUtils.isEmpty(strA)) {
            this.f845a = com.xiaomi.push.o.China.name();
        } else {
            this.f845a = strA;
            aVarA.a(strA);
            if (com.xiaomi.push.o.Global.name().equals(this.f845a)) {
                str = "app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.Europe.name().equals(this.f845a)) {
                str = "fr.app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.Russia.name().equals(this.f845a)) {
                str = "ru.app.chat.global.xiaomi.net";
            } else if (com.xiaomi.push.o.India.name().equals(this.f845a)) {
                str = "idmb.app.chat.global.xiaomi.net";
            }
            fq.a(str);
        }
        if (com.xiaomi.push.o.China.name().equals(this.f845a)) {
            fq.a("cn.app.chat.xiaomi.net");
        }
        if (m565h()) {
            cc ccVar = new cc(this, 11);
            a(ccVar);
            com.xiaomi.push.service.m.a(new cd(this, ccVar));
        }
        try {
            if (com.xiaomi.push.t.m646a()) {
                this.f842a.a(this);
            }
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Intent intent) throws PackageManager.NameNotFoundException, NumberFormatException {
        String str;
        com.xiaomi.push.service.e eVar;
        boolean z;
        int i2;
        String str2;
        i nVar;
        String str3;
        String strB;
        int intExtra;
        String str4;
        bg bgVar;
        av avVarA = av.a();
        boolean z2 = true;
        if (az.d.equalsIgnoreCase(intent.getAction()) || az.j.equalsIgnoreCase(intent.getAction())) {
            String stringExtra = intent.getStringExtra(az.r);
            if (!TextUtils.isEmpty(intent.getStringExtra(az.v))) {
                if (stringExtra == null) {
                    str = "channel id is empty, do nothing!";
                    com.xiaomi.channel.commonutils.logger.b.d(str);
                    return;
                }
                boolean zM559a = m559a(stringExtra, intent);
                av.b bVarA = a(stringExtra, intent);
                if (com.xiaomi.push.bg.b(this)) {
                    if (m574c()) {
                        if (bVarA.f901a == av.c.unbind) {
                            nVar = new a(bVarA);
                        } else if (zM559a) {
                            nVar = new n(bVarA);
                        } else if (bVarA.f901a == av.c.binding) {
                            str2 = String.format("the client is binding. %1$s %2$s.", bVarA.g, av.b.a(bVarA.f906b));
                        } else {
                            if (bVarA.f901a != av.c.binded) {
                                return;
                            }
                            eVar = this.f842a;
                            z = true;
                            i2 = 0;
                        }
                        c(nVar);
                        return;
                    }
                    a(true);
                    return;
                }
                eVar = this.f842a;
                z = false;
                i2 = 2;
                eVar.a(this, bVarA, z, i2, null);
                return;
            }
            str2 = "security is empty. ignore.";
            com.xiaomi.channel.commonutils.logger.b.m22a(str2);
            return;
        }
        if (az.i.equalsIgnoreCase(intent.getAction())) {
            String stringExtra2 = intent.getStringExtra(az.z);
            String stringExtra3 = intent.getStringExtra(az.r);
            String stringExtra4 = intent.getStringExtra(az.p);
            com.xiaomi.channel.commonutils.logger.b.m22a("Service called close channel chid = " + stringExtra3 + " res = " + av.b.a(stringExtra4));
            if (TextUtils.isEmpty(stringExtra3)) {
                Iterator<String> it = avVarA.m606a(stringExtra2).iterator();
                while (it.hasNext()) {
                    a(it.next(), 2);
                }
                return;
            } else if (TextUtils.isEmpty(stringExtra4)) {
                a(stringExtra3, 2);
                return;
            } else {
                a(stringExtra3, stringExtra4, 2, null, null);
                return;
            }
        }
        if (az.e.equalsIgnoreCase(intent.getAction())) {
            a(intent);
            return;
        }
        if (az.g.equalsIgnoreCase(intent.getAction())) {
            b(intent);
            return;
        }
        if (az.f.equalsIgnoreCase(intent.getAction())) {
            gg ggVarA = a(new ge(intent.getBundleExtra("ext_packet")), intent.getStringExtra(az.z), intent.getStringExtra(az.C));
            if (ggVarA == null) {
                return;
            } else {
                bgVar = new bg(this, fg.a(ggVarA, avVarA.a(ggVarA.k(), ggVarA.m()).h));
            }
        } else {
            if (!az.h.equalsIgnoreCase(intent.getAction())) {
                if (!az.k.equals(intent.getAction())) {
                    av.b bVarA2 = null;
                    if (!az.l.equals(intent.getAction())) {
                        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) || "android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                            if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                                if (m566i()) {
                                    return;
                                }
                                com.xiaomi.channel.commonutils.logger.b.m22a("exit falldown mode, activate alarm.");
                                e();
                                if (m574c() || m575d()) {
                                    return;
                                }
                                a(true);
                                return;
                            }
                            if (!"android.intent.action.SCREEN_OFF".equals(intent.getAction()) || !m566i() || !ex.m305a()) {
                                return;
                            } else {
                                str3 = "enter falldown mode, stop alarm.";
                            }
                        } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
                            if (bc.a(getApplicationContext()).m613a() && bc.a(getApplicationContext()).a() == 0) {
                                str4 = "register without being provisioned. " + intent.getStringExtra("mipush_app_package");
                            } else {
                                byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                                String stringExtra5 = intent.getStringExtra("mipush_app_package");
                                boolean booleanExtra = intent.getBooleanExtra("mipush_env_chanage", false);
                                int intExtra2 = intent.getIntExtra("mipush_env_type", 1);
                                com.xiaomi.push.service.n.a(this).d(stringExtra5);
                                if (!booleanExtra || "com.xiaomi.xmsf".equals(getPackageName())) {
                                    a(byteArrayExtra, stringExtra5);
                                    return;
                                }
                                nVar = new ce(this, 14, intExtra2, byteArrayExtra, stringExtra5);
                            }
                        } else {
                            if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                                String stringExtra6 = intent.getStringExtra("mipush_app_package");
                                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                                boolean booleanExtra2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                                if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                                    com.xiaomi.push.service.n.a(this).a(stringExtra6);
                                }
                                a(stringExtra6, byteArrayExtra2, booleanExtra2);
                                return;
                            }
                            if (!bd.a.equals(intent.getAction())) {
                                if (bd.b.equals(intent.getAction())) {
                                    String stringExtra7 = intent.getStringExtra("data_cleared_pkg_name");
                                    if (stringExtra7 == null || TextUtils.isEmpty(stringExtra7.trim())) {
                                        return;
                                    }
                                    com.xiaomi.channel.commonutils.logger.b.m22a("clear notifications of package " + stringExtra7);
                                    ac.m578a((Context) this, stringExtra7);
                                    ak.a((Context) this, stringExtra7);
                                    return;
                                }
                                if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
                                    String stringExtra8 = intent.getStringExtra(az.z);
                                    int intExtra3 = intent.getIntExtra(az.A, -2);
                                    if (TextUtils.isEmpty(stringExtra8)) {
                                        return;
                                    }
                                    if (intExtra3 >= -1) {
                                        ac.a(this, stringExtra8, intExtra3);
                                        return;
                                    } else {
                                        ac.a(this, stringExtra8, intent.getStringExtra(az.E), intent.getStringExtra(az.F));
                                        return;
                                    }
                                }
                                if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
                                    String stringExtra9 = intent.getStringExtra(az.z);
                                    String stringExtra10 = intent.getStringExtra(az.D);
                                    if (intent.hasExtra(az.B)) {
                                        intExtra = intent.getIntExtra(az.B, 0);
                                        strB = com.xiaomi.push.bl.b(stringExtra9 + intExtra);
                                        z2 = false;
                                    } else {
                                        strB = com.xiaomi.push.bl.b(stringExtra9);
                                        intExtra = 0;
                                    }
                                    if (!TextUtils.isEmpty(stringExtra9) && TextUtils.equals(stringExtra10, strB)) {
                                        if (z2) {
                                            ac.m583b((Context) this, stringExtra9);
                                            return;
                                        } else {
                                            ac.b(this, stringExtra9, intExtra);
                                            return;
                                        }
                                    }
                                    str = "invalid notification for " + stringExtra9;
                                    com.xiaomi.channel.commonutils.logger.b.d(str);
                                    return;
                                }
                                if ("com.xiaomi.mipush.DISABLE_PUSH".equals(intent.getAction())) {
                                    String stringExtra11 = intent.getStringExtra("mipush_app_package");
                                    if (!TextUtils.isEmpty(stringExtra11)) {
                                        com.xiaomi.push.service.n.a(this).b(stringExtra11);
                                    }
                                    if ("com.xiaomi.xmsf".equals(getPackageName())) {
                                        return;
                                    }
                                    a(19, (Exception) null);
                                    e();
                                    stopSelf();
                                    return;
                                }
                                if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                    String stringExtra12 = intent.getStringExtra("mipush_app_package");
                                    byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
                                    String stringExtra13 = intent.getStringExtra("mipush_app_id");
                                    String stringExtra14 = intent.getStringExtra("mipush_app_token");
                                    if ("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                        com.xiaomi.push.service.n.a(this).c(stringExtra12);
                                    }
                                    if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction())) {
                                        com.xiaomi.push.service.n.a(this).e(stringExtra12);
                                        com.xiaomi.push.service.n.a(this).f(stringExtra12);
                                    }
                                    if (byteArrayExtra3 == null) {
                                        com.xiaomi.push.service.p.a(this, stringExtra12, byteArrayExtra3, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
                                        return;
                                    }
                                    com.xiaomi.push.service.p.b(stringExtra12, byteArrayExtra3);
                                    a(new com.xiaomi.push.service.o(this, stringExtra12, stringExtra13, stringExtra14, byteArrayExtra3));
                                    if ("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE".equals(intent.getAction()) && this.f838a == null) {
                                        this.f838a = new e();
                                        registerReceiver(this.f838a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                                        return;
                                    }
                                    return;
                                }
                                if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                                    String stringExtra15 = intent.getStringExtra("mipush_app_package");
                                    byte[] byteArrayExtra4 = intent.getByteArrayExtra("mipush_payload");
                                    hn hnVar = new hn();
                                    try {
                                        it.a(hnVar, byteArrayExtra4);
                                        hh.a(this).a(hnVar, stringExtra15);
                                        return;
                                    } catch (iz e2) {
                                        com.xiaomi.channel.commonutils.logger.b.a(e2);
                                        return;
                                    }
                                }
                                if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                                    com.xiaomi.channel.commonutils.logger.b.m22a("Service called on timer");
                                    if (!m566i()) {
                                        ex.a(false);
                                        if (!m562e()) {
                                            return;
                                        }
                                    } else if (!ex.m305a()) {
                                        return;
                                    } else {
                                        str3 = "enter falldown mode, stop alarm";
                                    }
                                } else {
                                    if (!"com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                                        if ("com.xiaomi.mipush.thirdparty".equals(intent.getAction())) {
                                            com.xiaomi.channel.commonutils.logger.b.m22a("on thirdpart push :" + intent.getStringExtra("com.xiaomi.mipush.thirdparty_DESC"));
                                            ex.a(this, intent.getIntExtra("com.xiaomi.mipush.thirdparty_LEVEL", 0));
                                            return;
                                        }
                                        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                                            d();
                                            return;
                                        }
                                        if ("action_cr_config".equals(intent.getAction())) {
                                            boolean booleanExtra3 = intent.getBooleanExtra("action_cr_event_switch", false);
                                            long longExtra = intent.getLongExtra("action_cr_event_frequency", 86400L);
                                            boolean booleanExtra4 = intent.getBooleanExtra("action_cr_perf_switch", false);
                                            long longExtra2 = intent.getLongExtra("action_cr_perf_frequency", 86400L);
                                            boolean booleanExtra5 = intent.getBooleanExtra("action_cr_event_en", true);
                                            long longExtra3 = intent.getLongExtra("action_cr_max_file_size", 1048576L);
                                            Config configBuild = Config.getBuilder().setEventUploadSwitchOpen(booleanExtra3).setEventUploadFrequency(longExtra).setPerfUploadSwitchOpen(booleanExtra4).setPerfUploadFrequency(longExtra2).setAESKey(com.xiaomi.push.bq.a(getApplicationContext())).setEventEncrypted(booleanExtra5).setMaxFileLength(longExtra3).build(getApplicationContext());
                                            if ("com.xiaomi.xmsf".equals(getPackageName()) || longExtra <= 0 || longExtra2 <= 0 || longExtra3 <= 0) {
                                                return;
                                            }
                                            er.a(getApplicationContext(), configBuild);
                                            return;
                                        }
                                        if (!"action_help_ping".equals(intent.getAction())) {
                                            if ("action_aw_app_logic".equals(intent.getAction())) {
                                                d(intent);
                                                return;
                                            }
                                            return;
                                        }
                                        boolean booleanExtra6 = intent.getBooleanExtra("extra_help_ping_switch", false);
                                        int intExtra4 = intent.getIntExtra("extra_help_ping_frequency", 0);
                                        if (intExtra4 >= 0 && intExtra4 < 30) {
                                            com.xiaomi.channel.commonutils.logger.b.c("aw_ping: frquency need > 30s.");
                                            intExtra4 = 30;
                                        }
                                        if (intExtra4 < 0) {
                                            booleanExtra6 = false;
                                        }
                                        com.xiaomi.channel.commonutils.logger.b.m22a("aw_ping: receive a aw_ping message. switch: " + booleanExtra6 + " frequency: " + intExtra4);
                                        if (!booleanExtra6 || intExtra4 <= 0 || "com.xiaomi.xmsf".equals(getPackageName())) {
                                            return;
                                        }
                                        a(intent, intExtra4);
                                        return;
                                    }
                                    com.xiaomi.channel.commonutils.logger.b.m22a("Service called on check alive.");
                                    if (!m562e()) {
                                        return;
                                    }
                                }
                                b(false);
                                return;
                            }
                            String stringExtra16 = intent.getStringExtra("uninstall_pkg_name");
                            if (stringExtra16 == null || TextUtils.isEmpty(stringExtra16.trim())) {
                                return;
                            }
                            try {
                                getPackageManager().getPackageInfo(stringExtra16, 0);
                                z2 = false;
                            } catch (PackageManager.NameNotFoundException unused) {
                            }
                            if (!"com.xiaomi.channel".equals(stringExtra16) || av.a().m605a("1").isEmpty() || !z2) {
                                SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                                String string = sharedPreferences.getString(stringExtra16, null);
                                if (TextUtils.isEmpty(string) || !z2) {
                                    return;
                                }
                                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                editorEdit.remove(stringExtra16);
                                editorEdit.commit();
                                if (ac.m584b((Context) this, stringExtra16)) {
                                    ac.m583b((Context) this, stringExtra16);
                                }
                                ac.m578a((Context) this, stringExtra16);
                                if (!m574c() || string == null) {
                                    return;
                                }
                                try {
                                    y.a(this, y.a(stringExtra16, string));
                                    com.xiaomi.channel.commonutils.logger.b.m22a("uninstall " + stringExtra16 + " msg sent");
                                    return;
                                } catch (ga e3) {
                                    com.xiaomi.channel.commonutils.logger.b.d("Fail to send Message: " + e3.getMessage());
                                    a(10, e3);
                                    return;
                                }
                            }
                            a("1", 0);
                            str4 = "close the miliao channel as the app is uninstalled.";
                        }
                        com.xiaomi.channel.commonutils.logger.b.m22a(str3);
                        ex.a();
                        return;
                    }
                    String stringExtra17 = intent.getStringExtra(az.z);
                    List<String> listM606a = avVarA.m606a(stringExtra17);
                    if (!listM606a.isEmpty()) {
                        String stringExtra18 = intent.getStringExtra(az.r);
                        String stringExtra19 = intent.getStringExtra(az.p);
                        if (TextUtils.isEmpty(stringExtra18)) {
                            stringExtra18 = listM606a.get(0);
                        }
                        if (TextUtils.isEmpty(stringExtra19)) {
                            Collection<av.b> collectionM605a = avVarA.m605a(stringExtra18);
                            if (collectionM605a != null && !collectionM605a.isEmpty()) {
                                bVarA2 = collectionM605a.iterator().next();
                            }
                        } else {
                            bVarA2 = avVarA.a(stringExtra18, stringExtra19);
                        }
                        if (bVarA2 != null) {
                            if (intent.hasExtra(az.x)) {
                                bVarA2.e = intent.getStringExtra(az.x);
                            }
                            if (intent.hasExtra(az.y)) {
                                bVarA2.f = intent.getStringExtra(az.y);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    str4 = "open channel should be called first before update info, pkg=" + stringExtra17;
                    com.xiaomi.channel.commonutils.logger.b.m22a(str4);
                    return;
                }
                String stringExtra20 = intent.getStringExtra(az.r);
                String stringExtra21 = intent.getStringExtra(az.p);
                if (stringExtra20 == null) {
                    return;
                }
                com.xiaomi.channel.commonutils.logger.b.m22a("request reset connection from chid = " + stringExtra20);
                av.b bVarA3 = av.a().a(stringExtra20, stringExtra21);
                if (bVarA3 == null || !bVarA3.h.equals(intent.getStringExtra(az.v)) || bVarA3.f901a != av.c.binded) {
                    return;
                }
                fp fpVarM567a = m567a();
                if (fpVarM567a != null && fpVarM567a.a(System.currentTimeMillis() - 15000)) {
                    return;
                } else {
                    nVar = new o();
                }
                c(nVar);
                return;
            }
            gg ggVarA2 = a(new gi(intent.getBundleExtra("ext_packet")), intent.getStringExtra(az.z), intent.getStringExtra(az.C));
            if (ggVarA2 == null) {
                return;
            } else {
                bgVar = new bg(this, fg.a(ggVarA2, avVarA.a(ggVarA2.k(), ggVarA2.m()).h));
            }
        }
        c(bgVar);
    }

    private void c(i iVar) {
        this.f843a.a(iVar);
    }

    private void c(boolean z) {
        try {
            if (com.xiaomi.push.t.m646a()) {
                if (!z) {
                    sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
                    return;
                }
                sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
                for (ai aiVar : (ai[]) this.f847a.toArray(new ai[0])) {
                    aiVar.mo626a();
                }
            }
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
        }
    }

    private void d() {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network changed,");
            sb.append("[type: " + activeNetworkInfo.getTypeName() + "[" + activeNetworkInfo.getSubtypeName() + "], state: " + activeNetworkInfo.getState() + "/" + activeNetworkInfo.getDetailedState());
            com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
            NetworkInfo.State state = activeNetworkInfo.getState();
            if (state == NetworkInfo.State.SUSPENDED || state == NetworkInfo.State.UNKNOWN) {
                return;
            }
        } else {
            com.xiaomi.channel.commonutils.logger.b.m22a("network changed, no active network");
        }
        if (hb.a() != null) {
            hb.a().m373a();
        }
        gu.m370a((Context) this);
        this.f834a.d();
        if (com.xiaomi.push.bg.b(this)) {
            if (m574c() && m562e()) {
                b(false);
            }
            if (!m574c() && !m575d()) {
                this.f843a.a(1);
                a(new d());
            }
            dd.a(this).a();
        } else {
            a(new f(2, null));
        }
        e();
    }

    private void d(Intent intent) throws NumberFormatException {
        int i2;
        try {
            eh.a(getApplicationContext()).a(new bb());
            String stringExtra = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra == null) {
                return;
            }
            ii iiVar = new ii();
            it.a(iiVar, byteArrayExtra);
            String strB = iiVar.b();
            Map<String, String> mapM477a = iiVar.m477a();
            if (mapM477a != null) {
                String str = mapM477a.get("extra_help_aw_info");
                String str2 = mapM477a.get("extra_aw_app_online_cmd");
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    i2 = Integer.parseInt(str2);
                } catch (NumberFormatException unused) {
                    i2 = 0;
                }
                if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(strB) || TextUtils.isEmpty(str)) {
                    return;
                }
                eh.a(getApplicationContext()).a(this, str, i2, stringExtra, strB);
            }
        } catch (iz e2) {
            com.xiaomi.channel.commonutils.logger.b.d("aw_logic: translate fail. " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (!m570a()) {
            ex.a();
        } else {
            if (ex.m305a()) {
                return;
            }
            ex.a(true);
        }
    }

    /* renamed from: e, reason: collision with other method in class */
    private boolean m562e() {
        if (System.currentTimeMillis() - this.f831a < com.umeng.commonsdk.proguard.b.d) {
            return false;
        }
        return com.xiaomi.push.bg.d(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        String str;
        if (this.f835a != null && this.f835a.m334b()) {
            str = "try to connect while connecting.";
        } else {
            if (this.f835a == null || !this.f835a.m335c()) {
                this.f836a.b(com.xiaomi.push.bg.m124a((Context) this));
                g();
                if (this.f835a == null) {
                    av.a().a(this);
                    c(false);
                    return;
                }
                return;
            }
            str = "try to connect while is connected.";
        }
        com.xiaomi.channel.commonutils.logger.b.d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: collision with other method in class */
    public boolean m563f() {
        return "com.xiaomi.xmsf".equals(getPackageName()) && Settings.Secure.getInt(getContentResolver(), "EXTREME_POWER_MODE_ENABLE", 0) == 1;
    }

    private void g() {
        try {
            this.f834a.a(this.f837a, new bw(this));
            this.f834a.e();
            this.f835a = this.f834a;
        } catch (ga e2) {
            com.xiaomi.channel.commonutils.logger.b.a("fail to create Slim connection", e2);
            this.f834a.b(3, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: collision with other method in class */
    public boolean m564g() {
        return "com.xiaomi.xmsf".equals(getPackageName()) && Settings.System.getInt(getContentResolver(), "power_supersave_mode_open", 0) == 1;
    }

    private void h() {
    }

    /* renamed from: h, reason: collision with other method in class */
    private boolean m565h() {
        return "com.xiaomi.xmsf".equals(getPackageName()) || !com.xiaomi.push.service.n.a(this).m641b(getPackageName());
    }

    private void i() {
        synchronized (this.f846a) {
            this.f846a.clear();
        }
    }

    /* renamed from: i, reason: collision with other method in class */
    private boolean m566i() {
        return getApplicationContext().getPackageName().equals("com.xiaomi.xmsf") && j() && !com.xiaomi.push.i.m438b((Context) this) && !com.xiaomi.push.i.m435a(getApplicationContext());
    }

    private boolean j() {
        int iIntValue = Integer.valueOf(String.format("%tH", new Date())).intValue();
        if (this.a > this.b) {
            if (iIntValue >= this.a || iIntValue < this.b) {
                return true;
            }
        } else if (this.a < this.b && iIntValue >= this.a && iIntValue < this.b) {
            return true;
        }
        return false;
    }

    private boolean k() {
        if (TextUtils.equals(getPackageName(), "com.xiaomi.xmsf")) {
            return false;
        }
        return aq.a(this).a(ho.ForegroundServiceSwitch.a(), false);
    }

    /* renamed from: a, reason: collision with other method in class */
    public fp m567a() {
        return this.f835a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public com.xiaomi.push.service.e m568a() {
        return new com.xiaomi.push.service.e();
    }

    /* renamed from: a, reason: collision with other method in class */
    void m569a() {
        if (System.currentTimeMillis() - this.f831a >= fv.a() && com.xiaomi.push.bg.d(this)) {
            b(true);
        }
    }

    public void a(int i2) {
        this.f843a.a(i2);
    }

    public void a(int i2, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("disconnect ");
        sb.append(hashCode());
        sb.append(", ");
        sb.append(this.f835a == null ? null : Integer.valueOf(this.f835a.hashCode()));
        com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
        if (this.f835a != null) {
            this.f835a.b(i2, exc);
            this.f835a = null;
        }
        a(7);
        a(4);
        av.a().a(this, i2);
    }

    public void a(fg fgVar) throws ga {
        if (this.f835a == null) {
            throw new ga("try send msg while connection is null.");
        }
        this.f835a.b(fgVar);
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar) {
        hb.a().a(fpVar);
        c(true);
        this.f841a.m615a();
        if (!ex.m305a() && !m566i()) {
            com.xiaomi.channel.commonutils.logger.b.m22a("reconnection successful, reactivate alarm.");
            ex.a(true);
        }
        Iterator<av.b> it = av.a().m604a().iterator();
        while (it.hasNext()) {
            a(new a(it.next()));
        }
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, int i2, Exception exc) {
        hb.a().a(fpVar, i2, exc);
        if (m566i()) {
            return;
        }
        a(false);
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, Exception exc) {
        hb.a().a(fpVar, exc);
        c(false);
        if (m566i()) {
            return;
        }
        a(false);
    }

    public void a(i iVar) {
        a(iVar, 0L);
    }

    public void a(i iVar, long j2) {
        try {
            this.f843a.a(iVar, j2);
        } catch (IllegalStateException e2) {
            com.xiaomi.channel.commonutils.logger.b.m22a("can't execute job err = " + e2.getMessage());
        }
    }

    public void a(l lVar) {
        synchronized (this.f846a) {
            this.f846a.add(lVar);
        }
    }

    public void a(av.b bVar) {
        if (bVar != null) {
            long jA = bVar.a();
            com.xiaomi.channel.commonutils.logger.b.m22a("schedule rebind job in " + (jA / 1000));
            a(new a(bVar), jA);
        }
    }

    public void a(String str, String str2, int i2, String str3, String str4) {
        av.b bVarA = av.a().a(str, str2);
        if (bVarA != null) {
            a(new q(bVarA, i2, str4, str3));
        }
        av.a().m609a(str, str2);
    }

    void a(String str, byte[] bArr, boolean z) {
        Collection<av.b> collectionM605a = av.a().m605a("5");
        if (collectionM605a.isEmpty()) {
            if (!z) {
                return;
            }
        } else if (collectionM605a.iterator().next().f901a == av.c.binded) {
            a(new bu(this, 4, str, bArr));
            return;
        } else if (!z) {
            return;
        }
        com.xiaomi.push.service.p.b(str, bArr);
    }

    public void a(boolean z) {
        this.f841a.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            com.xiaomi.push.service.p.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
            com.xiaomi.channel.commonutils.logger.b.m22a("register request without payload");
            return;
        }
        Cif cif = new Cif();
        try {
            it.a(cif, bArr);
            if (cif.f631a == hj.Registration) {
                ij ijVar = new ij();
                try {
                    it.a(ijVar, cif.m467a());
                    com.xiaomi.push.service.p.a(cif.b(), bArr);
                    a(new com.xiaomi.push.service.o(this, cif.b(), ijVar.b(), ijVar.c(), bArr));
                    es.a(getApplicationContext()).a(cif.b(), "E100003", ijVar.a(), 6002, null);
                } catch (iz e2) {
                    com.xiaomi.channel.commonutils.logger.b.d("app register error. " + e2);
                    com.xiaomi.push.service.p.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data action error.");
                }
            } else {
                com.xiaomi.push.service.p.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " registration action required.");
                com.xiaomi.channel.commonutils.logger.b.m22a("register request with invalid payload");
            }
        } catch (iz e3) {
            com.xiaomi.channel.commonutils.logger.b.d("app register fail. " + e3);
            com.xiaomi.push.service.p.a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data container error.");
        }
    }

    public void a(fg[] fgVarArr) throws ga {
        if (this.f835a == null) {
            throw new ga("try send msg while connection is null.");
        }
        this.f835a.a(fgVarArr);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m570a() {
        return com.xiaomi.push.bg.b(this) && av.a().m603a() > 0 && !m573b() && m565h() && !m564g() && !m563f();
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m571a(int i2) {
        return this.f843a.m629a(i2);
    }

    public com.xiaomi.push.service.e b() {
        return this.f842a;
    }

    /* renamed from: b, reason: collision with other method in class */
    void m572b() {
        Iterator it = new ArrayList(this.f846a).iterator();
        while (it.hasNext()) {
            ((l) it.next()).mo381a();
        }
    }

    @Override // com.xiaomi.push.fs
    public void b(fp fpVar) {
        com.xiaomi.channel.commonutils.logger.b.c("begin to connect...");
        hb.a().b(fpVar);
    }

    public void b(i iVar) {
        this.f843a.a(iVar.a, iVar);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m573b() {
        try {
            Class<?> clsA = com.xiaomi.push.t.a(this, "miui.os.Build");
            Field field = clsA.getField("IS_CM_CUSTOMIZATION_TEST");
            Field field2 = clsA.getField("IS_CU_CUSTOMIZATION_TEST");
            Field field3 = clsA.getField("IS_CT_CUSTOMIZATION_TEST");
            if (!field.getBoolean(null) && !field2.getBoolean(null)) {
                if (!field3.getBoolean(null)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m574c() {
        return this.f835a != null && this.f835a.m335c();
    }

    /* renamed from: d, reason: collision with other method in class */
    public boolean m575d() {
        return this.f835a != null && this.f835a.m334b();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f833a.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() throws PackageManager.NameNotFoundException {
        String[] strArrSplit;
        super.onCreate();
        com.xiaomi.channel.commonutils.logger.b.a(getApplicationContext());
        com.xiaomi.push.t.m645a((Context) this);
        com.xiaomi.push.service.l lVarA = com.xiaomi.push.service.m.a((Context) this);
        if (lVarA != null) {
            com.xiaomi.push.ab.a(lVarA.a);
        }
        this.f833a = new Messenger(new bx(this));
        ba.a(this);
        this.f836a = new by(this, null, 5222, "xiaomi.com", null);
        this.f836a.a(true);
        this.f834a = new fn(this, this.f836a);
        this.f842a = m568a();
        ex.a(this);
        this.f834a.a(this);
        this.f840a = new au(this);
        this.f841a = new bf(this);
        new com.xiaomi.push.service.f().a();
        hb.m374a().a(this);
        this.f843a = new com.xiaomi.push.service.h("Connection Controller Thread");
        av avVarA = av.a();
        avVarA.b();
        avVarA.a(new bz(this));
        if (k()) {
            h();
        }
        hh.a(this).a(new com.xiaomi.push.service.j(this), "UPLOADER_PUSH_CHANNEL");
        a(new he(this));
        a(new g());
        this.f847a.add(bm.a(this));
        if (m565h()) {
            this.f838a = new e();
            registerReceiver(this.f838a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            Uri uriFor = Settings.Secure.getUriFor("EXTREME_POWER_MODE_ENABLE");
            if (uriFor != null) {
                this.f832a = new ca(this, new Handler(Looper.getMainLooper()));
                try {
                    getContentResolver().registerContentObserver(uriFor, false, this.f832a);
                } catch (Throwable th) {
                    com.xiaomi.channel.commonutils.logger.b.m22a("register observer err:" + th.getMessage());
                }
            }
            Uri uriFor2 = Settings.System.getUriFor("power_supersave_mode_open");
            if (uriFor2 != null) {
                this.f848b = new cb(this, new Handler(Looper.getMainLooper()));
                try {
                    getContentResolver().registerContentObserver(uriFor2, false, this.f848b);
                } catch (Throwable th2) {
                    com.xiaomi.channel.commonutils.logger.b.d("register super-power-mode observer err:" + th2.getMessage());
                }
            }
            int[] iArrM560a = m560a();
            if (iArrM560a != null) {
                this.f839a = new p();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                registerReceiver(this.f839a, intentFilter);
                this.a = iArrM560a[0];
                this.b = iArrM560a[1];
                com.xiaomi.channel.commonutils.logger.b.m22a("falldown initialized: " + this.a + Constants.ACCEPT_TIME_SEPARATOR_SP + this.b);
            }
        }
        String str = "";
        if (lVarA != null) {
            try {
                if (!TextUtils.isEmpty(lVarA.f960a) && (strArrSplit = lVarA.f960a.split("@")) != null && strArrSplit.length > 0) {
                    str = strArrSplit[0];
                }
            } catch (Exception unused) {
            }
        }
        com.xiaomi.channel.commonutils.logger.b.e("XMPushService created. pid=" + Process.myPid() + ", uid=" + Process.myUid() + ", uuid=" + str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.f838a != null) {
            a(this.f838a);
            this.f838a = null;
        }
        if (this.f839a != null) {
            a(this.f839a);
            this.f839a = null;
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && this.f832a != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f832a);
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.m22a("unregister observer err:" + th.getMessage());
            }
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && this.f848b != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f848b);
            } catch (Throwable th2) {
                com.xiaomi.channel.commonutils.logger.b.d("unregister super-power-mode err:" + th2.getMessage());
            }
        }
        this.f847a.clear();
        this.f843a.m630b();
        a(new bv(this, 2));
        a(new j());
        av.a().b();
        av.a().a(this, 15);
        av.a().m607a();
        this.f834a.b(this);
        bk.a().m624a();
        ex.a();
        i();
        super.onDestroy();
        com.xiaomi.channel.commonutils.logger.b.m22a("Service destroyed");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i2) {
        h hVar;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (intent == null) {
            com.xiaomi.channel.commonutils.logger.b.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.logger.b.m22a(String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s", intent.getAction(), intent.getStringExtra(az.r), intent.getStringExtra(az.z), intent.getStringExtra("mipush_app_package")));
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.f843a.m628a()) {
                    com.xiaomi.channel.commonutils.logger.b.d("ERROR, the job controller is blocked.");
                    av.a().a(this, 14);
                    stopSelf();
                } else {
                    hVar = new h(intent);
                    a(hVar);
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                hVar = new h(intent);
                a(hVar);
            }
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        if (jCurrentTimeMillis2 > 50) {
            com.xiaomi.channel.commonutils.logger.b.c("[Prefs] spend " + jCurrentTimeMillis2 + " ms, too more times.");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        onStart(intent, i3);
        return 1;
    }
}
