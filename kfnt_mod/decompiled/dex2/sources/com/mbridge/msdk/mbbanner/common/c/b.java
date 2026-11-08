package com.mbridge.msdk.mbbanner.common.c;

import android.content.Context;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.m;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.mbbanner.common.b.d;
import com.mbridge.msdk.mbbanner.common.b.f;
import com.mbridge.msdk.mbbanner.common.b.g;
import com.mbridge.msdk.mbbanner.common.util.BannerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: BannerLoader.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final String a = "b";
    private Context b;
    private com.mbridge.msdk.mbbanner.common.a.c d;
    private com.mbridge.msdk.mbbanner.common.util.a e;
    private com.mbridge.msdk.mbbanner.common.b.b f;
    private d g;
    private int c = 0;
    private volatile boolean h = false;
    private Timer i = new Timer();
    private volatile List<String> j = new ArrayList();
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean m = false;

    public b(Context context, com.mbridge.msdk.mbbanner.common.a.c cVar, com.mbridge.msdk.mbbanner.common.b.b bVar, com.mbridge.msdk.mbbanner.common.util.a aVar) {
        this.b = context.getApplicationContext();
        this.d = cVar;
        this.f = bVar;
        this.e = aVar;
    }

    private void a(String str) {
        if (this.m) {
            return;
        }
        if ((this.k || this.l) && this.j.size() == 0) {
            s.b(a, "在子线程处理业务逻辑 完成");
            this.h = true;
            this.m = true;
            this.i.cancel();
            this.e.a(this.f, str);
            this.g.a(str);
        }
    }

    public final void a(String str, int i, String str2, boolean z) {
        if (!z) {
            if (i == -1) {
                s.d(a, " unitId =" + str + " --> time out!");
            }
            this.i.cancel();
            s.b(a, "在子线程处理业务逻辑 完成");
            s.b(a, "downloadResource--> Fail");
            this.h = true;
            this.e.b(this.f, str);
            this.g.a(str);
            return;
        }
        if (i == 1) {
            s.b(a, "downloadResource--> Success Image");
            synchronized (this) {
                this.j.remove(str2);
                if (this.j.size() == 0) {
                    a(str);
                }
            }
            return;
        }
        if (i == 2) {
            s.b(a, "downloadResource--> Success banner_html");
            this.l = true;
            a(str);
        } else if (i == 3) {
            s.b(a, "downloadResource--> Success banner_url");
            this.k = true;
            a(str);
        }
    }

    public final void a(String str, String str2, com.mbridge.msdk.mbbanner.common.a.b bVar, d dVar) {
        boolean z;
        try {
            s.b(a, "requestCampaign--> started");
            this.g = dVar;
            com.mbridge.msdk.mbbanner.common.f.a aVar = new com.mbridge.msdk.mbbanner.common.f.a() { // from class: com.mbridge.msdk.mbbanner.common.c.b.2
                @Override // com.mbridge.msdk.mbbanner.common.f.a
                public final void a(CampaignUnit campaignUnit) throws Throwable {
                    try {
                        s.b(b.a, "requestCampaign--> Succeed");
                        b.this.e.a(b.this.f, campaignUnit, this.d);
                        b.a(b.this, this.d, campaignUnit);
                    } catch (Exception e) {
                        s.b(b.a, "requestCampaign--> Fail with exception = " + e.getMessage());
                        b.this.e.a(b.this.f, e.getMessage(), this.d);
                        b.this.g.a(this.d);
                    }
                }

                @Override // com.mbridge.msdk.mbbanner.common.f.a
                public final void a(int i, String str3) {
                    s.b(b.a, "requestCampaign--> Fail errorCode:" + i + " msg:" + str3);
                    b.this.e.a(b.this.f, str3, this.d);
                    b.this.g.a(this.d);
                }
            };
            aVar.d = str2;
            aVar.e = str;
            aVar.f = 296;
            com.mbridge.msdk.mbbanner.common.e.a aVar2 = new com.mbridge.msdk.mbbanner.common.e.a(this.b);
            this.c = b(str2);
            com.mbridge.msdk.foundation.same.net.h.d dVarA = com.mbridge.msdk.mbbanner.common.a.d.a(false, this.b, str2, this.d.a(), this.c, bVar);
            String strE = w.e(str2);
            if (!TextUtils.isEmpty(strE)) {
                dVarA.a("j", strE);
            }
            String strC = bVar.c();
            if (TextUtils.isEmpty(strC)) {
                z = false;
            } else {
                aVar.a(strC);
                z = true;
            }
            this.e.a(z);
            if (com.mbridge.msdk.foundation.same.net.g.d.c().b() && !z) {
                aVar2.b(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(strC), dVarA, aVar);
            } else {
                aVar2.a(1, com.mbridge.msdk.foundation.same.net.g.d.c().a(strC), dVarA, aVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.e.a(this.f, e.getMessage(), str2);
            this.g.a(str2);
        }
    }

    private List<CampaignEx> a(String str, CampaignUnit campaignUnit) {
        ArrayList arrayList = new ArrayList();
        if (campaignUnit != null) {
            try {
                if (campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                    ArrayList<CampaignEx> ads = campaignUnit.getAds();
                    s.b(a, "getNeedShowList 总共返回的campaign有：" + ads.size());
                    w.a((List<CampaignEx>) ads);
                    for (int i = 0; i < ads.size(); i++) {
                        CampaignEx campaignEx = ads.get(i);
                        if (campaignEx != null && campaignEx.getOfferType() != 99 && (!TextUtils.isEmpty(campaignEx.getBannerUrl()) || !TextUtils.isEmpty(campaignEx.getBannerHtml()) || !TextUtils.isEmpty(campaignEx.getImageUrl()))) {
                            if (w.b(campaignEx)) {
                                campaignEx.setRtinsType(w.c(this.b, campaignEx.getPackageName()) ? 1 : 2);
                            }
                            if (campaignEx.getWtick() == 1 || !w.c(this.b, campaignEx.getPackageName())) {
                                arrayList.add(campaignEx);
                            } else if (w.b(campaignEx)) {
                                arrayList.add(campaignEx);
                            } else {
                                w.a(str, campaignEx, com.mbridge.msdk.foundation.same.a.D);
                            }
                        }
                    }
                    s.b(a, "getNeedShowList 返回有以下带有视频素材的campaign：" + arrayList.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private void a(String str, List<CampaignEx> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (CampaignEx campaignEx : list) {
            if (!TextUtils.isEmpty(campaignEx.getImageUrl())) {
                this.j.add(campaignEx.getImageUrl());
                com.mbridge.msdk.foundation.same.c.b.a(this.b).a(campaignEx.getImageUrl(), new g(this, str));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 != 0) goto L9a
            r1 = 0
            com.mbridge.msdk.foundation.same.b.c r2 = com.mbridge.msdk.foundation.same.b.c.MBRIDGE_700_HTML     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r2 = com.mbridge.msdk.foundation.same.b.e.b(r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = com.mbridge.msdk.foundation.tools.ab.a(r8)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = com.mbridge.msdk.foundation.tools.SameMD5.getMD5(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r4 == 0) goto L25
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
        L25:
            java.lang.String r4 = ".html"
            java.lang.String r3 = r3.concat(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.<init>(r2, r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r2.getPath()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            byte[] r1 = r8.getBytes()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r2.write(r1)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r2.flush()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            java.lang.String r1 = r4.getAbsolutePath()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L57
            r2.close()     // Catch: java.lang.Exception -> L4e
            goto L52
        L4e:
            r0 = move-exception
            r0.printStackTrace()
        L52:
            r0 = r1
            goto L6c
        L54:
            r7 = move-exception
            r1 = r2
            goto L8f
        L57:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L5f
        L5c:
            r7 = move-exception
            goto L8f
        L5e:
            r2 = move-exception
        L5f:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L5c
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.lang.Exception -> L68
            goto L6c
        L68:
            r1 = move-exception
            r1.printStackTrace()
        L6c:
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r2 = r1.exists()
            r3 = 2
            if (r2 == 0) goto L8a
            boolean r2 = r1.isFile()
            if (r2 == 0) goto L8a
            boolean r1 = r1.canRead()
            if (r1 != 0) goto L85
            goto L8a
        L85:
            r1 = 1
            r6.a(r7, r3, r8, r1)
            goto L9a
        L8a:
            r1 = 0
            r6.a(r7, r3, r8, r1)
            goto L9a
        L8f:
            if (r1 == 0) goto L99
            r1.close()     // Catch: java.lang.Exception -> L95
            goto L99
        L95:
            r8 = move-exception
            r8.printStackTrace()
        L99:
            throw r7
        L9a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.mbbanner.common.c.b.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private int b(String str) {
        try {
            int iB = this.d.b();
            if (iB > this.d.c()) {
                return 0;
            }
            return iB;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    static /* synthetic */ void a(b bVar, final String str, final CampaignUnit campaignUnit) throws Throwable {
        if (campaignUnit == null) {
            bVar.e.a(bVar.f, "campaignUnit is NULL!", str);
            bVar.g.a(str);
            return;
        }
        List<CampaignEx> listA = bVar.a(str, campaignUnit);
        new Thread(new Runnable() { // from class: com.mbridge.msdk.mbbanner.common.c.b.3
            @Override // java.lang.Runnable
            public final void run() {
                s.b(b.a, "在单独子线程保存数据库 开始");
                m.a(i.a(b.this.b)).b();
                if (campaignUnit != null && campaignUnit.getAds() != null && campaignUnit.getAds().size() > 0) {
                    BannerUtils.updateInstallList(b.this.b, campaignUnit.getAds());
                }
                s.b(b.a, "在单独子线程保存数据库 完成");
            }
        }).start();
        if (listA == null || listA.size() == 0) {
            s.b(a, "tryDownloadOnLoadSuccess 返回的campaign 没有符合下载规则的");
            bVar.e.a(bVar.f, "APP ALREADY INSTALLED", str);
            bVar.g.a(str);
            return;
        }
        s.b(a, "在子线程处理业务逻辑 开始");
        bVar.i.schedule(new TimerTask() { // from class: com.mbridge.msdk.mbbanner.common.c.b.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                if (b.this.h) {
                    return;
                }
                b.this.h = true;
                b.this.a(str, -1, "", false);
            }
        }, 60000);
        bVar.d.a(campaignUnit.getSessionId());
        int size = bVar.c;
        int i = 0;
        if (listA != null) {
            try {
                if (listA.size() > 0) {
                    size += listA.size();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (size > bVar.d.c()) {
            s.b(a, "saveNextOffset 重置offset为0");
            size = 0;
        }
        s.b(a, "saveNextOffset 算出 下次的offset是:" + size);
        if (z.b(str)) {
            bVar.d.a(size);
        }
        CampaignEx campaignEx = listA.get(0);
        String strTrim = campaignEx.getBannerUrl().trim();
        if (TextUtils.isEmpty(strTrim)) {
            String strTrim2 = campaignEx.getBannerHtml().trim();
            if (!TextUtils.isEmpty(strTrim2)) {
                String strA = bVar.a(str, strTrim2);
                if (listA != null && listA.size() > 0) {
                    while (i < listA.size()) {
                        listA.get(i).setBannerHtml(strA);
                        listA.get(i).setHasMBTplMark(strTrim2.contains("<MBTPLMARK>"));
                        i++;
                    }
                }
            } else {
                bVar.l = true;
                bVar.k = true;
            }
        } else {
            if (!TextUtils.isEmpty(strTrim)) {
                H5DownLoadManager.getInstance().download(strTrim, new f(bVar, str));
            }
            if (listA != null && listA.size() > 0) {
                while (i < listA.size()) {
                    listA.get(i).setBannerUrl(campaignEx.getBannerUrl());
                    listA.get(i).setHasMBTplMark(true);
                    i++;
                }
            }
        }
        bVar.a(str, listA);
    }
}
