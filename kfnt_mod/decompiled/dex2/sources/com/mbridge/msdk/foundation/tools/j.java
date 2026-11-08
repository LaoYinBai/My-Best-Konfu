package com.mbridge.msdk.foundation.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.framework.common.ContainerUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: MIMManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class j {
    private static volatile String d;
    private CopyOnWriteArrayList<CampaignEx> a;
    private volatile b b;
    private final AtomicBoolean c;
    private volatile Boolean e;
    private CampaignEx f;
    private int g;
    private Context h;

    private j() {
        this.g = 0;
        this.a = new CopyOnWriteArrayList<>();
        this.c = new AtomicBoolean(false);
    }

    public static j a() {
        return a.a;
    }

    public final void a(CampaignEx campaignEx) {
        try {
            if (this.e == null || campaignEx == null || !this.e.booleanValue()) {
                return;
            }
            this.f = campaignEx;
            this.a.add(campaignEx);
        } catch (Exception e) {
            s.d("MIMManager", e.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            r5.e = r1
            r1 = 0
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L79
            if (r2 != 0) goto L83
            java.lang.Boolean r2 = r5.e     // Catch: java.lang.Exception -> L79
            if (r2 == 0) goto L83
            java.lang.Boolean r2 = r5.e     // Catch: java.lang.Exception -> L79
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> L79
            if (r2 == 0) goto L83
            java.lang.String r6 = r6.trim()     // Catch: java.lang.Exception -> L83
            java.lang.String r2 = "YkQwD+z8H+N1WBK3H+xtLkeAyM=="
            java.lang.String r2 = com.mbridge.msdk.foundation.tools.n.b(r2)     // Catch: java.lang.Exception -> L83
            boolean r2 = r6.startsWith(r2)     // Catch: java.lang.Exception -> L83
            if (r2 == 0) goto L76
            java.lang.String r2 = "H75TDkQsVbxPY7V="
            java.lang.String r2 = com.mbridge.msdk.foundation.tools.n.b(r2)     // Catch: java.lang.Exception -> L83
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83
            r3.<init>()     // Catch: java.lang.Exception -> L83
            r3.append(r2)     // Catch: java.lang.Exception -> L83
            java.lang.String r4 = "=1"
            r3.append(r4)     // Catch: java.lang.Exception -> L83
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L83
            boolean r3 = r6.contains(r3)     // Catch: java.lang.Exception -> L83
            if (r3 != 0) goto L77
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83
            r3.<init>()     // Catch: java.lang.Exception -> L83
            r3.append(r2)     // Catch: java.lang.Exception -> L83
            java.lang.String r4 = "=2"
            r3.append(r4)     // Catch: java.lang.Exception -> L83
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L83
            boolean r3 = r6.contains(r3)     // Catch: java.lang.Exception -> L83
            if (r3 != 0) goto L77
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83
            r3.<init>()     // Catch: java.lang.Exception -> L83
            r3.append(r2)     // Catch: java.lang.Exception -> L83
            java.lang.String r2 = "=3"
            r3.append(r2)     // Catch: java.lang.Exception -> L83
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Exception -> L83
            boolean r6 = r6.contains(r2)     // Catch: java.lang.Exception -> L83
            if (r6 == 0) goto L76
            goto L77
        L76:
            r0 = 0
        L77:
            r1 = r0
            goto L83
        L79:
            r6 = move-exception
            java.lang.String r0 = "MIMManager"
            java.lang.String r6 = r6.getMessage()
            com.mbridge.msdk.foundation.tools.s.d(r0, r6)
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.tools.j.a(java.lang.String):boolean");
    }

    public final void b(CampaignEx campaignEx) {
        try {
            if (this.e == null || campaignEx == null || !this.e.booleanValue()) {
                return;
            }
            this.f = null;
            try {
                this.a.remove(campaignEx);
            } catch (Exception e) {
                s.a("MIMManager", e.getMessage());
            }
        } catch (Exception e2) {
            s.d("MIMManager", e2.getMessage());
        }
    }

    final CampaignEx b() {
        return this.f;
    }

    public final void c() {
        this.g++;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d() {
        this.g--;
        if (this.g <= 0) {
            Context context = this.h;
            if (this.b == null || context == null) {
                return;
            }
            try {
                try {
                    context.unregisterReceiver(this.b);
                } catch (Exception e) {
                    s.a("MIMManager", e.getMessage());
                }
            } finally {
                this.f = null;
                this.a.clear();
                this.a = null;
                this.b = null;
                this.h = null;
            }
        }
    }

    public final void a(final Context context) {
        this.h = context;
        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.j.1
            @Override // java.lang.Runnable
            public final void run() {
                if (j.this.c.compareAndSet(false, true)) {
                    try {
                        j.this.e = j.this.g();
                    } catch (Exception e) {
                        s.a("MIMManager", e.getMessage());
                    }
                    if (j.this.e == null || !j.this.e.booleanValue() || context == null) {
                        return;
                    }
                    try {
                        j.this.b = new b();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xj26Vjcdx5KyVj5GxVN="));
                        intentFilter.addAction(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xj26Vjcdx5KanjKnxVN="));
                        intentFilter.addAction(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xjQZxVfV+T2SZVe6V2xS5c5n"));
                        context.registerReceiver(j.this.b, intentFilter);
                    } catch (Exception e2) {
                        s.a("MIMManager", e2.getMessage());
                    }
                }
            }
        });
    }

    public final Boolean e() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean g() {
        Cursor cursorQuery;
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        Boolean boolValueOf = null;
        if (contextJ != null) {
            try {
                if (contextJ.getContentResolver() != null) {
                    try {
                        cursorQuery = contextJ.getContentResolver().query(Uri.parse(n.b("DFK/J75/JaEXWFfXYZP9LkcXYk3/YkcBLF5TWgSBYbHuH75BW3xuhr5UJj2tLkeNhrKFLkxQhl==")), null, null, null, null);
                    } catch (Exception unused) {
                        cursorQuery = null;
                    }
                    if (cursorQuery != null) {
                        while (cursorQuery.moveToNext()) {
                            try {
                                String string = cursorQuery.getString(cursorQuery.getColumnIndex("support"));
                                if (!TextUtils.isEmpty(string) && !string.equalsIgnoreCase("null") && (string.equalsIgnoreCase("false") || string.equalsIgnoreCase("true"))) {
                                    boolValueOf = Boolean.valueOf(Boolean.parseBoolean(string));
                                }
                            } catch (Exception e) {
                                s.a("MIMManager", e.getMessage());
                            }
                            try {
                                d = cursorQuery.getString(cursorQuery.getColumnIndex("detailStyle"));
                            } catch (Exception e2) {
                                s.a("MIMManager", e2.getMessage());
                            }
                        }
                        try {
                            cursorQuery.close();
                        } catch (Exception e3) {
                            s.a("MIMManager", e3.getMessage());
                        }
                    }
                }
            } catch (Exception e4) {
                s.d("MIMManager", e4.getMessage());
            }
        }
        return boolValueOf;
    }

    public final String f() {
        try {
            return d == null ? "" : String.format("[%s]", d);
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MIMManager.java */
    static final class a {
        private static final j a = new j();
    }

    /* compiled from: MIMManager.java */
    private static class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(final Context context, Intent intent) {
            final CampaignEx campaignExB;
            Runnable runnable;
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (action.equals(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xj26Vjcdx5KyVj5GxVN="))) {
                a.a.a(context, "dm_page_opened", a.a.b());
                return;
            }
            if (action.equals(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xj26Vjcdx5KanjKnxVN="))) {
                a.a.a(context, "dm_page_closed", a.a.b());
                return;
            }
            if (action.equals(n.b("DFKwWgtuDkKwLZPwD+z8H+N/xjQZxVfV+T2SZVe6V2xS5c5n"))) {
                try {
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        return;
                    }
                    int i = extras.getInt(CommandMessage.CODE, -1);
                    String string = extras.getString("packageName", "");
                    if (i == -1 || TextUtils.isEmpty(string) || (campaignExB = a.a.b(string)) == null) {
                        return;
                    }
                    final j jVar = a.a;
                    if (i != 4) {
                        switch (i) {
                            case 1:
                                runnable = new Runnable() { // from class: com.mbridge.msdk.foundation.tools.j.4
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        String[] strArrR;
                                        try {
                                            j jVar2 = j.this;
                                            Context context2 = context;
                                            CampaignEx campaignEx = campaignExB;
                                            if (campaignEx != null) {
                                                try {
                                                    com.mbridge.msdk.foundation.entity.m nativeVideoTracking = campaignEx.getNativeVideoTracking();
                                                    if (nativeVideoTracking == null || (strArrR = nativeVideoTracking.r()) == null) {
                                                        return;
                                                    }
                                                    for (String str : strArrR) {
                                                        com.mbridge.msdk.click.b.a(context2, campaignEx, campaignEx.getCampaignUnitId(), str, false, false);
                                                    }
                                                } catch (Exception e) {
                                                    s.d("MIMManager", e.getMessage());
                                                }
                                            }
                                        } catch (Exception e2) {
                                            s.d("MIMManager", e2.getMessage());
                                        }
                                    }
                                };
                                break;
                            case 2:
                                runnable = new Runnable() { // from class: com.mbridge.msdk.foundation.tools.j.3
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        String[] strArrS;
                                        try {
                                            j jVar2 = j.this;
                                            Context context2 = context;
                                            CampaignEx campaignEx = campaignExB;
                                            if (campaignEx != null) {
                                                try {
                                                    com.mbridge.msdk.foundation.entity.m nativeVideoTracking = campaignEx.getNativeVideoTracking();
                                                    if (nativeVideoTracking == null || (strArrS = nativeVideoTracking.s()) == null) {
                                                        return;
                                                    }
                                                    for (String str : strArrS) {
                                                        com.mbridge.msdk.click.b.a(context2, campaignEx, campaignEx.getCampaignUnitId(), str, false, false);
                                                    }
                                                } catch (Exception e) {
                                                    s.d("MIMManager", e.getMessage());
                                                }
                                            }
                                        } catch (Exception e2) {
                                            s.d("MIMManager", e2.getMessage());
                                        }
                                    }
                                };
                                break;
                            default:
                                runnable = null;
                                break;
                        }
                    } else {
                        runnable = new Runnable() { // from class: com.mbridge.msdk.foundation.tools.j.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                String[] strArrT;
                                try {
                                    try {
                                        j jVar2 = j.this;
                                        Context context2 = context;
                                        CampaignEx campaignEx = campaignExB;
                                        if (campaignEx != null) {
                                            try {
                                                com.mbridge.msdk.foundation.entity.m nativeVideoTracking = campaignEx.getNativeVideoTracking();
                                                if (nativeVideoTracking != null && (strArrT = nativeVideoTracking.t()) != null) {
                                                    for (String str : strArrT) {
                                                        com.mbridge.msdk.click.b.a(context2, campaignEx, campaignEx.getCampaignUnitId(), str, false, false);
                                                    }
                                                }
                                            } catch (Exception e) {
                                                s.d("MIMManager", e.getMessage());
                                            }
                                        }
                                        if (j.this.a == null || !j.this.a.contains(campaignExB)) {
                                            return;
                                        }
                                    } catch (Exception e2) {
                                        s.d("MIMManager", e2.getMessage());
                                        if (j.this.a == null || !j.this.a.contains(campaignExB)) {
                                            return;
                                        }
                                    }
                                    j.this.a.remove(campaignExB);
                                } catch (Throwable th) {
                                    if (j.this.a != null && j.this.a.contains(campaignExB)) {
                                        j.this.a.remove(campaignExB);
                                    }
                                    throw th;
                                }
                            }
                        };
                    }
                    if (runnable != null) {
                        com.mbridge.msdk.foundation.same.f.b.a().execute(runnable);
                    }
                } catch (Exception e) {
                    s.d("MIMManager", e.getMessage());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CampaignEx b(String str) {
        try {
            if (this.f != null && TextUtils.equals(this.f.getPackageName(), str)) {
                return this.f;
            }
            if (this.a == null || this.a.size() <= 0) {
                return null;
            }
            Iterator<CampaignEx> it = this.a.iterator();
            while (it.hasNext()) {
                CampaignEx next = it.next();
                if (next != null && TextUtils.equals(next.getPackageName(), str)) {
                    return next;
                }
            }
            return null;
        } catch (Exception e) {
            s.d("MIMManager", e.getMessage());
            return null;
        }
    }

    final void a(final Context context, final String str, final CampaignEx campaignEx) {
        com.mbridge.msdk.foundation.same.f.b.a().execute(new Runnable() { // from class: com.mbridge.msdk.foundation.tools.j.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (context == null || TextUtils.isEmpty(str) || campaignEx == null) {
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("key=2000100&");
                    stringBuffer.append("network_type=");
                    stringBuffer.append(o.C(context));
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    if (!TextUtils.isEmpty(campaignEx.getRequestIdNotice())) {
                        stringBuffer.append("rid_n=");
                        stringBuffer.append(campaignEx.getRequestIdNotice());
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    if (!TextUtils.isEmpty(campaignEx.getRequestId())) {
                        stringBuffer.append("rid=");
                        stringBuffer.append(campaignEx.getRequestId());
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    if (!TextUtils.isEmpty(campaignEx.getCampaignUnitId())) {
                        stringBuffer.append("unit_id=");
                        stringBuffer.append(campaignEx.getCampaignUnitId());
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    if (!TextUtils.isEmpty(campaignEx.getId())) {
                        stringBuffer.append("cid=");
                        stringBuffer.append(campaignEx.getId());
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    stringBuffer.append("status=");
                    stringBuffer.append(str);
                    if (com.mbridge.msdk.foundation.same.report.a.a().c()) {
                        com.mbridge.msdk.foundation.same.report.a.a().a(stringBuffer.toString());
                        return;
                    }
                    try {
                        new com.mbridge.msdk.foundation.same.report.d.a(context).c(0, com.mbridge.msdk.foundation.same.net.g.d.c().a, com.mbridge.msdk.foundation.same.report.d.a(stringBuffer.toString(), context), null);
                    } catch (Exception e) {
                        e.printStackTrace();
                        s.d("MIMManager", e.getMessage());
                    }
                } catch (Throwable th) {
                    s.d("MIMManager", th.getMessage());
                }
            }
        });
    }
}
