package com.mbridge.msdk.reward.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.db.u;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.download.download.ResDownloadCheckManager;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.q;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import com.mbridge.msdk.videocommon.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardCampaignsResourceManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static Map<String, a> c;
    private final h a;
    private ConcurrentHashMap<String, List<CampaignEx>> b;
    private boolean d;
    private volatile List<WindVaneWebView> e;

    /* compiled from: RewardCampaignsResourceManager.java */
    /* renamed from: com.mbridge.msdk.reward.adapter.c$c, reason: collision with other inner class name */
    public interface InterfaceC0138c {
        void a(String str, String str2, String str3, List<CampaignEx> list);

        void a(String str, String str2, String str3, List<CampaignEx> list, String str4);
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    public interface i {
        void a(String str, String str2, String str3, String str4);

        void a(String str, String str2, String str3, String str4, String str5);
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    public interface j {
        void a(String str, String str2, String str3, String str4, String str5, a.C0146a c0146a);

        void a(String str, String str2, String str3, String str4, String str5, a.C0146a c0146a, String str6);
    }

    private c() {
        this.e = new ArrayList(6);
        HandlerThread handlerThread = new HandlerThread("mb-reward-load-thread");
        c = new HashMap();
        handlerThread.start();
        this.a = new h(handlerThread.getLooper());
        this.b = new ConcurrentHashMap<>();
    }

    public static c a() {
        return m.a;
    }

    public final synchronized void a(final Context context, boolean z, int i2, boolean z2, final int i3, final String str, final String str2, final String str3, final List<CampaignEx> list, InterfaceC0138c interfaceC0138c, final i iVar) {
        String str4 = str2 + "_" + str3;
        c.put(str4, new a(z, z2, i2, list.size(), str2, str3, i3, list));
        this.a.a(str, str2, str3, interfaceC0138c);
        this.a.a(context);
        this.a.a(str4, list);
        this.a.post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.c.1
            /* JADX WARN: Removed duplicated region for block: B:155:0x0334 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:41:0x00fe  */
            /* JADX WARN: Removed duplicated region for block: B:59:0x016e  */
            /* JADX WARN: Removed duplicated region for block: B:68:0x01bd  */
            /* JADX WARN: Removed duplicated region for block: B:71:0x01d2  */
            /* JADX WARN: Removed duplicated region for block: B:80:0x020f  */
            /* JADX WARN: Removed duplicated region for block: B:88:0x0244  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() throws java.security.NoSuchAlgorithmException {
                /*
                    Method dump skipped, instructions count: 825
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.reward.adapter.c.AnonymousClass1.run():void");
            }
        });
    }

    public final synchronized void a(final Context context, final CampaignEx campaignEx, final String str, final String str2, final String str3, final i iVar) {
        this.a.a(context);
        if (campaignEx != null && !TextUtils.isEmpty(campaignEx.getMof_template_url())) {
            try {
                H5DownLoadManager.getInstance().downloadH5Res(campaignEx.getMof_template_url(), new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.reward.adapter.c.2
                    @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
                    public final void onSuccess(String str4) {
                        try {
                            s.a("RewardCampaignsResourceManager", "zip btl template download success");
                            ResDownloadCheckManager.getInstance().setZipDownloadDone(str4, true);
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 105;
                            Bundle bundle = new Bundle();
                            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                            bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                            bundle.putString("request_id", str3);
                            bundle.putString("url", str4);
                            messageObtain.setData(bundle);
                            c.this.a.sendMessage(messageObtain);
                            if (iVar != null) {
                                iVar.a(str, str2, str3, str4);
                            }
                            q qVar = new q();
                            qVar.o("2000045");
                            if (context != null) {
                                qVar.b(o.C(context.getApplicationContext()));
                            }
                            qVar.c(1);
                            if (campaignEx != null) {
                                qVar.n(campaignEx.getId());
                                qVar.k(campaignEx.getRequestId());
                                qVar.l(campaignEx.getRequestIdNotice());
                            }
                            qVar.i(str4);
                            qVar.p("");
                            qVar.m(str2);
                            u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(qVar);
                        } catch (Exception e2) {
                            ResDownloadCheckManager.getInstance().setZipDownloadDone(str4, false);
                            Message messageObtain2 = Message.obtain();
                            messageObtain2.what = 205;
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                            bundle2.putString(MBridgeConstans.PLACEMENT_ID, str);
                            bundle2.putString("request_id", str3);
                            bundle2.putString("url", str4);
                            messageObtain2.setData(bundle2);
                            c.this.a.sendMessage(messageObtain2);
                            if (iVar != null) {
                                iVar.a(str, str2, str3, str4, e2.getLocalizedMessage());
                            }
                            q qVar2 = new q();
                            qVar2.o("2000045");
                            if (context != null) {
                                qVar2.b(o.C(context.getApplicationContext()));
                            }
                            qVar2.c(3);
                            if (campaignEx != null) {
                                qVar2.n(campaignEx.getId());
                                qVar2.k(campaignEx.getRequestId());
                                qVar2.l(campaignEx.getRequestIdNotice());
                            }
                            qVar2.i(str4);
                            qVar2.p(e2.getLocalizedMessage());
                            qVar2.m(str2);
                            u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(qVar2);
                            if (MBridgeConstans.DEBUG) {
                                s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                            }
                        }
                    }

                    @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
                    public final void onFailed(String str4, String str5) {
                        s.a("RewardCampaignsResourceManager", "zip btl template download failed");
                        try {
                            ResDownloadCheckManager.getInstance().setZipDownloadDone(str5, false);
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 205;
                            Bundle bundle = new Bundle();
                            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                            bundle.putString(MBridgeConstans.PLACEMENT_ID, str);
                            bundle.putString("request_id", str3);
                            bundle.putString("url", str5);
                            bundle.putString("message", str4);
                            messageObtain.setData(bundle);
                            c.this.a.sendMessage(messageObtain);
                            if (iVar != null) {
                                iVar.a(str, str2, str3, str5, str4);
                            }
                            q qVar = new q();
                            qVar.o("2000045");
                            if (context != null) {
                                qVar.b(o.C(context.getApplicationContext()));
                            }
                            qVar.c(3);
                            if (campaignEx != null) {
                                qVar.n(campaignEx.getId());
                                qVar.k(campaignEx.getRequestId());
                                qVar.l(campaignEx.getRequestIdNotice());
                            }
                            qVar.i(str5);
                            qVar.p(str4);
                            qVar.m(str2);
                            u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(qVar);
                        } catch (Exception e2) {
                            ResDownloadCheckManager.getInstance().setZipDownloadDone(str5, false);
                            Message messageObtain2 = Message.obtain();
                            messageObtain2.what = 105;
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, str2);
                            bundle2.putString(MBridgeConstans.PLACEMENT_ID, str);
                            bundle2.putString("request_id", str3);
                            bundle2.putString("url", str5);
                            bundle2.putString("message", e2.getMessage());
                            messageObtain2.setData(bundle2);
                            c.this.a.sendMessage(messageObtain2);
                            if (iVar != null) {
                                iVar.a(str, str2, str3, str5, str4);
                            }
                            if (MBridgeConstans.DEBUG) {
                                s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                            }
                        }
                    }
                });
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                }
            }
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static final class e implements com.mbridge.msdk.foundation.same.c.c {
        private Handler a;
        private int b;
        private String c;
        private String d;
        private String e;
        private CampaignEx f;

        public e(Handler handler, int i, String str, String str2, String str3, CampaignEx campaignEx) {
            this.a = handler;
            this.b = i;
            this.d = str;
            this.c = str2;
            this.e = str3;
            this.f = campaignEx;
        }

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onSuccessLoad(Bitmap bitmap, String str) {
            ResDownloadCheckManager.getInstance().setImageDownloadDone(str, true);
            Message messageObtain = Message.obtain();
            messageObtain.what = this.b == 0 ? 102 : 104;
            Bundle bundle = new Bundle();
            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
            bundle.putString(MBridgeConstans.PLACEMENT_ID, this.d);
            bundle.putString("request_id", this.e);
            messageObtain.setData(bundle);
            this.a.sendMessage(messageObtain);
        }

        @Override // com.mbridge.msdk.foundation.same.c.c
        public final void onFailedLoad(String str, String str2) {
            Message messageObtain = Message.obtain();
            messageObtain.what = this.b == 0 ? 202 : 204;
            Bundle bundle = new Bundle();
            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
            bundle.putString(MBridgeConstans.PLACEMENT_ID, this.d);
            bundle.putString("request_id", this.e);
            messageObtain.setData(bundle);
            this.a.sendMessage(messageObtain);
            if (this.f != null) {
                try {
                    u uVarA = u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
                    s.a("RewardCampaignsResourceManager", "campaign is null");
                    q qVar = new q();
                    qVar.o("2000044");
                    qVar.b(o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
                    qVar.n(this.f.getId());
                    qVar.d(this.f.getImageUrl());
                    qVar.k(this.f.getRequestId());
                    qVar.l(this.f.getRequestIdNotice());
                    qVar.m(this.c);
                    qVar.p(str);
                    uVarA.a(qVar);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static final class f implements com.mbridge.msdk.videocommon.listener.a {
        private Handler a;
        private final String b;
        private final String c;
        private final String d;

        public f(Handler handler, String str, String str2, String str3) {
            this.a = handler;
            this.c = str;
            this.b = str2;
            this.d = str3;
        }

        @Override // com.mbridge.msdk.videocommon.listener.a
        public final void a(String str) {
            s.d("RewardCampaignsResourceManager", "Video 下载成功: " + this.d);
            ResDownloadCheckManager.getInstance().setVideoDownloadDone(str, true);
            Message messageObtain = Message.obtain();
            messageObtain.what = 100;
            Bundle bundle = new Bundle();
            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.b);
            bundle.putString(MBridgeConstans.PLACEMENT_ID, this.c);
            bundle.putString("request_id", this.d);
            bundle.putString("url", str);
            messageObtain.setData(bundle);
            this.a.sendMessage(messageObtain);
        }

        @Override // com.mbridge.msdk.videocommon.listener.a
        public final void a(String str, String str2) {
            s.d("RewardCampaignsResourceManager", "Video 下载失败： " + str + " " + this.d);
            ResDownloadCheckManager.getInstance().setVideoDownloadDone(str2, false);
            Message messageObtain = Message.obtain();
            messageObtain.what = 200;
            Bundle bundle = new Bundle();
            bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.b);
            bundle.putString(MBridgeConstans.PLACEMENT_ID, this.c);
            bundle.putString("request_id", this.d);
            bundle.putString("url", str2);
            bundle.putString("message", str);
            messageObtain.setData(bundle);
            this.a.sendMessage(messageObtain);
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static final class g implements H5DownLoadManager.ZipDownloadListener {
        private Context b;
        private String c;
        private String d;
        private String e;
        private CampaignEx f;
        private int g;
        private Handler h;
        private i i;
        private List<CampaignEx> j;
        private boolean a = false;
        private long k = System.currentTimeMillis();

        public g(Context context, String str, String str2, String str3, CampaignEx campaignEx, int i, Handler handler, i iVar, List<CampaignEx> list) {
            this.b = context;
            this.d = str;
            this.c = str2;
            this.e = str3;
            this.f = campaignEx;
            this.g = i;
            this.h = handler;
            this.i = iVar;
            this.j = list;
        }

        public final void a(boolean z) {
            this.a = z;
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onSuccess(String str) {
            ResDownloadCheckManager.getInstance().setZipDownloadDone(str, true);
            long jCurrentTimeMillis = System.currentTimeMillis() - this.k;
            int i = this.g;
            if (i == 313) {
                s.a("RewardCampaignsResourceManager", "zip pause download success");
                Message messageObtain = Message.obtain();
                messageObtain.what = 101;
                Bundle bundle = new Bundle();
                bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
                bundle.putString(MBridgeConstans.PLACEMENT_ID, this.d);
                bundle.putString("request_id", this.e);
                bundle.putString("url", str);
                messageObtain.setData(bundle);
                this.h.sendMessage(messageObtain);
                return;
            }
            if (i == 497) {
                s.a("RewardCampaignsResourceManager", "zip endcard download success");
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 101;
                Bundle bundle2 = new Bundle();
                bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
                bundle2.putString(MBridgeConstans.PLACEMENT_ID, this.d);
                bundle2.putString("request_id", this.e);
                bundle2.putString("url", str);
                messageObtain2.setData(bundle2);
                this.h.sendMessage(messageObtain2);
                if (this.a) {
                    try {
                        q qVar = new q("2000043", 14, jCurrentTimeMillis + "", str, this.f.getId(), this.c, "", "1");
                        qVar.k(this.f.getRequestId());
                        qVar.l(this.f.getRequestIdNotice());
                        qVar.n(this.f.getId());
                        if (this.f.getAdType() == 287) {
                            qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                        } else if (this.f.getAdType() == 94) {
                            qVar.h("1");
                        }
                        com.mbridge.msdk.foundation.same.report.b.a(qVar, this.c);
                        return;
                    } catch (Exception e) {
                        if (MBridgeConstans.DEBUG) {
                            s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            if (i != 859) {
                return;
            }
            s.a("RewardCampaignsResourceManager", "zip template download success");
            Message messageObtain3 = Message.obtain();
            messageObtain3.what = 103;
            Bundle bundle3 = new Bundle();
            bundle3.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
            bundle3.putString(MBridgeConstans.PLACEMENT_ID, this.d);
            bundle3.putString("request_id", this.e);
            messageObtain3.setData(bundle3);
            this.h.sendMessage(messageObtain3);
            if (this.i != null) {
                this.i.a(this.d, this.c, this.e, str);
            }
            try {
                q qVar2 = new q();
                qVar2.o("2000045");
                if (this.b != null) {
                    qVar2.b(o.C(this.b.getApplicationContext()));
                }
                qVar2.c(1);
                if (this.f != null) {
                    qVar2.n(this.f.getId());
                    qVar2.k(this.f.getRequestId());
                    qVar2.l(this.f.getRequestIdNotice());
                }
                qVar2.i(str);
                qVar2.p("");
                qVar2.m(this.c);
                u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(qVar2);
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                }
            }
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onFailed(String str, String str2) {
            ResDownloadCheckManager.getInstance().setZipDownloadDone(str2, false);
            long jCurrentTimeMillis = System.currentTimeMillis() - this.k;
            int i = this.g;
            if (i == 313) {
                s.a("RewardCampaignsResourceManager", "zip pause download failed");
                Message messageObtain = Message.obtain();
                messageObtain.what = 101;
                Bundle bundle = new Bundle();
                bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
                bundle.putString(MBridgeConstans.PLACEMENT_ID, this.d);
                bundle.putString("request_id", this.e);
                bundle.putString("url", str2);
                bundle.putString("message", str);
                messageObtain.setData(bundle);
                this.h.sendMessage(messageObtain);
                return;
            }
            if (i != 497) {
                if (i != 859) {
                    return;
                }
                s.a("RewardCampaignsResourceManager", "zip template download failed");
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 203;
                Bundle bundle2 = new Bundle();
                bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
                bundle2.putString(MBridgeConstans.PLACEMENT_ID, this.d);
                bundle2.putString("request_id", this.e);
                bundle2.putString("url", str2);
                bundle2.putString("message", str);
                messageObtain2.setData(bundle2);
                this.h.sendMessage(messageObtain2);
                if (this.i != null) {
                    this.i.a(this.d, this.c, this.e, str2, str);
                }
                try {
                    q qVar = new q();
                    qVar.o("2000045");
                    if (this.b != null) {
                        qVar.b(o.C(this.b.getApplicationContext()));
                    }
                    qVar.c(3);
                    if (this.f != null) {
                        qVar.n(this.f.getId());
                        qVar.k(this.f.getRequestId());
                        qVar.l(this.f.getRequestIdNotice());
                    }
                    qVar.i(str2);
                    qVar.p(str);
                    qVar.m(this.c);
                    u.a(com.mbridge.msdk.foundation.db.i.a(com.mbridge.msdk.foundation.controller.a.f().j())).a(qVar);
                    return;
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                        return;
                    }
                    return;
                }
            }
            s.a("RewardCampaignsResourceManager", "zip endcard download failed:  " + str);
            Message messageObtain3 = Message.obtain();
            messageObtain3.what = 201;
            Bundle bundle3 = new Bundle();
            bundle3.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.c);
            bundle3.putString(MBridgeConstans.PLACEMENT_ID, this.d);
            bundle3.putString("request_id", this.e);
            bundle3.putString("url", str2);
            bundle3.putString("message", str);
            messageObtain3.setData(bundle3);
            this.h.sendMessage(messageObtain3);
            if (this.a) {
                try {
                    q qVar2 = new q("2000043", 3, jCurrentTimeMillis + "", str2, this.f.getId(), this.c, "zip download failed", "1");
                    qVar2.k(this.f.getRequestId());
                    qVar2.l(this.f.getRequestIdNotice());
                    qVar2.n(this.f.getId());
                    if (this.f.getAdType() == 287) {
                        qVar2.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                    } else if (this.f.getAdType() == 94) {
                        qVar2.h("1");
                    }
                    com.mbridge.msdk.foundation.same.report.b.a(qVar2, this.c);
                } catch (Exception e2) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                    }
                }
            }
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static final class d implements H5DownLoadManager.IH5SourceDownloadListener {
        private int b;
        private final String c;
        private final String d;
        private final String e;
        private CampaignEx f;
        private i g;
        private Handler h;
        private List<CampaignEx> i;
        private boolean a = false;
        private final long j = System.currentTimeMillis();

        public d(int i, String str, String str2, String str3, CampaignEx campaignEx, i iVar, Handler handler, List<CampaignEx> list) {
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = campaignEx;
            this.g = iVar;
            this.h = handler;
            this.i = list;
        }

        public final void a(boolean z) {
            this.a = z;
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onSuccess(String str) {
            ResDownloadCheckManager.getInstance().setZipDownloadDone(str, true);
            int i = this.b;
            if (i == 313) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 101;
                Bundle bundle = new Bundle();
                bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
                bundle.putString(MBridgeConstans.PLACEMENT_ID, this.c);
                bundle.putString("request_id", this.e);
                bundle.putString("url", str);
                messageObtain.setData(bundle);
                this.h.sendMessage(messageObtain);
                return;
            }
            if (i != 497) {
                if (i != 859) {
                    return;
                }
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 105;
                Bundle bundle2 = new Bundle();
                bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
                bundle2.putString(MBridgeConstans.PLACEMENT_ID, this.c);
                bundle2.putString("request_id", this.e);
                messageObtain2.setData(bundle2);
                this.h.sendMessage(messageObtain2);
                if (this.g != null) {
                    this.g.a(this.c, this.d, this.e, str);
                    return;
                }
                return;
            }
            Message messageObtain3 = Message.obtain();
            messageObtain3.what = 101;
            Bundle bundle3 = new Bundle();
            bundle3.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
            bundle3.putString(MBridgeConstans.PLACEMENT_ID, this.c);
            bundle3.putString("request_id", this.e);
            bundle3.putString("url", str);
            messageObtain3.setData(bundle3);
            this.h.sendMessage(messageObtain3);
            if (this.a) {
                try {
                    q qVar = new q("2000043", 20, (System.currentTimeMillis() - this.j) + "", str, this.f.getId(), this.d, "", "2");
                    qVar.k(this.f.getRequestId());
                    qVar.l(this.f.getRequestIdNotice());
                    qVar.n(this.f.getId());
                    if (this.f.getAdType() == 287) {
                        qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                    } else if (this.f.getAdType() == 94) {
                        qVar.h("1");
                    }
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.d);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                    }
                }
            }
        }

        @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
        public final void onFailed(String str, String str2) {
            ResDownloadCheckManager.getInstance().setZipDownloadDone(str, false);
            int i = this.b;
            if (i == 313) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 201;
                Bundle bundle = new Bundle();
                bundle.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
                bundle.putString(MBridgeConstans.PLACEMENT_ID, this.c);
                bundle.putString("request_id", this.e);
                bundle.putString("url", str);
                bundle.putString("message", str2);
                messageObtain.setData(bundle);
                this.h.sendMessage(messageObtain);
                return;
            }
            if (i != 497) {
                if (i != 859) {
                    return;
                }
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 205;
                Bundle bundle2 = new Bundle();
                bundle2.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
                bundle2.putString(MBridgeConstans.PLACEMENT_ID, this.c);
                bundle2.putString("request_id", this.e);
                bundle2.putString("message", str2);
                messageObtain2.setData(bundle2);
                this.h.sendMessage(messageObtain2);
                if (this.g != null) {
                    this.g.a(this.c, this.d, this.e, str, str2);
                    return;
                }
                return;
            }
            Message messageObtain3 = Message.obtain();
            messageObtain3.what = 201;
            Bundle bundle3 = new Bundle();
            bundle3.putString(MBridgeConstans.PROPERTIES_UNIT_ID, this.d);
            bundle3.putString(MBridgeConstans.PLACEMENT_ID, this.c);
            bundle3.putString("request_id", this.e);
            bundle3.putString("url", str);
            bundle3.putString("message", str2);
            messageObtain3.setData(bundle3);
            this.h.sendMessage(messageObtain3);
            if (this.a) {
                try {
                    q qVar = new q("2000043", 21, (System.currentTimeMillis() - this.j) + "", str, this.f.getId(), this.d, "url download failed", "2");
                    qVar.k(this.f.getRequestId());
                    qVar.l(this.f.getRequestIdNotice());
                    qVar.n(this.f.getId());
                    if (this.f.getAdType() == 287) {
                        qVar.h(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                    } else if (this.f.getAdType() == 94) {
                        qVar.h("1");
                    }
                    com.mbridge.msdk.foundation.same.report.b.a(qVar, this.d);
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static final class h extends Handler {
        private Context a;
        private ConcurrentHashMap<String, InterfaceC0138c> b;
        private ConcurrentHashMap<String, List<CampaignEx>> c;

        public h(Looper looper) {
            super(looper);
            this.b = new ConcurrentHashMap<>();
            this.c = new ConcurrentHashMap<>();
        }

        public final void a(String str, String str2, String str3, InterfaceC0138c interfaceC0138c) {
            this.b.put(str2 + "_" + str3, interfaceC0138c);
        }

        public final void a(Context context) {
            this.a = context;
        }

        public final void a(String str, List<CampaignEx> list) {
            this.c.put(str, list);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            String string = data.getString(MBridgeConstans.PLACEMENT_ID);
            String string2 = data.getString(MBridgeConstans.PROPERTIES_UNIT_ID);
            String string3 = data.getString("request_id");
            String str = string2 + "_" + string3;
            a aVar = (a) c.c.get(str);
            InterfaceC0138c interfaceC0138c = this.b.get(str);
            List<CampaignEx> list = this.c.get(str);
            s.a("RewardCampaignsResourceManager", "收到 Message，开始判断");
            int i = message.what;
            switch (i) {
                case 0:
                case 1:
                    break;
                default:
                    int i2 = 0;
                    switch (i) {
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                            break;
                        default:
                            switch (i) {
                                case 200:
                                case 201:
                                case 203:
                                case 205:
                                    if (aVar != null && interfaceC0138c != null) {
                                        String string4 = data.getString("message");
                                        if (string4 == null) {
                                            string4 = "";
                                        }
                                        String str2 = "resource download failed " + c.b(message.what) + " " + string4;
                                        CampaignEx campaignEx = null;
                                        if (aVar.h != null && aVar.h.size() > 0) {
                                            campaignEx = aVar.h.get(0);
                                        }
                                        try {
                                            if (aVar.a && aVar.i != null) {
                                                if (aVar.c == 1) {
                                                    if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0) {
                                                        switch (message.what) {
                                                            case 200:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(0)) {
                                                                }
                                                                break;
                                                            case 201:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(2)) {
                                                                }
                                                                break;
                                                            case 203:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(1)) {
                                                                }
                                                                break;
                                                            case 205:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(3)) {
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    interfaceC0138c.a(string, string2, string3, aVar.h, str2);
                                                    this.b.remove(str);
                                                    c.c.remove(str);
                                                    this.c.remove(str);
                                                    break;
                                                } else {
                                                    String string5 = data.getString("url");
                                                    switch (message.what) {
                                                        case 200:
                                                            if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(0)) {
                                                                s.b("RewardCampaignsResourceManager", "Is TPL but  video download fail but hit ignoreCheckRule");
                                                                break;
                                                            } else {
                                                                while (i2 < aVar.i.size()) {
                                                                    if (aVar.i.get(i2).getVideoUrlEncode().equals(string5)) {
                                                                        aVar.i.remove(i2);
                                                                    }
                                                                    i2++;
                                                                }
                                                                c.c.remove(str);
                                                                c.c.put(str, aVar);
                                                                break;
                                                            }
                                                            break;
                                                        case 201:
                                                            if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(2)) {
                                                                s.b("RewardCampaignsResourceManager", "Is TPL but download endcard fail but hit ignoreCheckRule");
                                                                break;
                                                            } else {
                                                                while (i2 < aVar.i.size()) {
                                                                    CampaignEx campaignEx2 = aVar.i.get(i2);
                                                                    if (campaignEx2.getRewardTemplateMode() != null && campaignEx2.getRewardTemplateMode().c().equals(string5)) {
                                                                        aVar.i.remove(i2);
                                                                    }
                                                                    if (campaignEx2.getendcard_url().equals(string5)) {
                                                                        aVar.i.remove(i2);
                                                                    }
                                                                    i2++;
                                                                }
                                                                c.c.remove(str);
                                                                c.c.put(str, aVar);
                                                                break;
                                                            }
                                                            break;
                                                        case 203:
                                                            if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(1)) {
                                                                s.b("RewardCampaignsResourceManager", "Is TPL but download template fail but hit ignoreCheckRule");
                                                                break;
                                                            } else {
                                                                while (i2 < aVar.i.size()) {
                                                                    CampaignEx campaignEx3 = aVar.i.get(i2);
                                                                    if (campaignEx3.getRewardTemplateMode() != null && campaignEx3.getRewardTemplateMode().d().equals(string5)) {
                                                                        aVar.i.remove(i2);
                                                                    }
                                                                    i2++;
                                                                }
                                                                c.c.remove(str);
                                                                c.c.put(str, aVar);
                                                                break;
                                                            }
                                                            break;
                                                        case 205:
                                                            if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0 && campaignEx.getRsIgnoreCheckRule().contains(3)) {
                                                                s.b("RewardCampaignsResourceManager", "Is TPL but download BTL Template fail but hit ignoreCheckRule");
                                                                break;
                                                            } else {
                                                                aVar.i.clear();
                                                                c.c.remove(str);
                                                                c.c.put(str, aVar);
                                                                break;
                                                            }
                                                            break;
                                                    }
                                                    boolean zA = com.mbridge.msdk.videocommon.download.b.getInstance().a(94, aVar.e, aVar.b, aVar.d, aVar.a, aVar.c, list);
                                                    s.a("RewardCampaignsResourceManager", " failed Campaign是否下载成功：(回调) " + zA);
                                                    if (zA) {
                                                        interfaceC0138c.a(string, string2, string3, aVar.h);
                                                        this.b.remove(str);
                                                        c.c.remove(str);
                                                        this.c.remove(str);
                                                        break;
                                                    } else if (aVar.i.size() == 0) {
                                                        interfaceC0138c.a(string, string2, string3, aVar.h, str2);
                                                        this.b.remove(str);
                                                        c.c.remove(str);
                                                        this.c.remove(str);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                boolean zA2 = com.mbridge.msdk.videocommon.download.b.getInstance().a(94, aVar.e, aVar.b, aVar.d, aVar.a, aVar.c, list);
                                                s.a("RewardCampaignsResourceManager", " failed Campaign是否下载成功： （回调）" + zA2);
                                                if (zA2) {
                                                    interfaceC0138c.a(string, string2, string3, aVar.h);
                                                } else {
                                                    if (campaignEx != null && campaignEx.getRsIgnoreCheckRule() != null && campaignEx.getRsIgnoreCheckRule().size() > 0) {
                                                        switch (message.what) {
                                                            case 200:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(0)) {
                                                                }
                                                                break;
                                                            case 201:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(2)) {
                                                                }
                                                                break;
                                                            case 203:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(1)) {
                                                                }
                                                                break;
                                                            case 205:
                                                                if (campaignEx.getRsIgnoreCheckRule().contains(3)) {
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    interfaceC0138c.a(string, string2, string3, aVar.h, str2);
                                                }
                                                this.b.remove(str);
                                                c.c.remove(str);
                                                this.c.remove(str);
                                                break;
                                            }
                                        } catch (Exception e) {
                                            interfaceC0138c.a(string, string2, string3, aVar.h, "resource download failed " + e.getMessage());
                                            return;
                                        }
                                    }
                                    break;
                            }
                    }
                    boolean zA3 = false;
                    if (aVar != null && interfaceC0138c != null) {
                        try {
                            zA3 = com.mbridge.msdk.videocommon.download.b.getInstance().a(94, aVar.e, aVar.b, aVar.d, aVar.a, aVar.c, list);
                        } catch (Exception e2) {
                            if (MBridgeConstans.DEBUG) {
                                s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                            }
                        }
                        s.a("RewardCampaignsResourceManager", " success Campaign是否下载成功：(回调) " + zA3);
                        if (zA3) {
                            interfaceC0138c.a(string, string2, string3, aVar.h);
                            this.b.remove(str);
                            c.c.remove(str);
                            this.c.remove(str);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public final synchronized void a(boolean z, Handler handler, boolean z2, boolean z3, WindVaneWebView windVaneWebView, String str, int i2, CampaignEx campaignEx, List<CampaignEx> list, String str2, String str3, String str4, String str5, String str6, com.mbridge.msdk.videocommon.d.c cVar, j jVar) {
        WindVaneWebView windVaneWebView2;
        com.mbridge.msdk.video.js.a.j jVar2;
        WindVaneWebView windVaneWebView3;
        String str7 = str4 + "_" + str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            com.mbridge.msdk.reward.b.a.a(campaignEx, com.mbridge.msdk.foundation.controller.a.f().j(), "start preload template ", str4, z3, str5, str6, System.currentTimeMillis() - jCurrentTimeMillis);
        } catch (Exception unused) {
        }
        try {
            a.C0146a c0146a = new a.C0146a();
            WindVaneWebView windVaneWebViewA = m.a.a(false);
            if (windVaneWebViewA == null) {
                try {
                    windVaneWebView2 = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
                } catch (Exception e2) {
                    if (jVar != null) {
                        c0146a.a(true);
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(str4 + "_" + str5 + "_" + str, true);
                        try {
                            com.mbridge.msdk.reward.b.a.a(campaignEx, com.mbridge.msdk.foundation.controller.a.f().j(), "WebView exception: " + e2.getMessage(), str4, z3, str5, str6, System.currentTimeMillis() - jCurrentTimeMillis);
                        } catch (Exception unused2) {
                        }
                        jVar.a(str4 + "_" + str, str3, str4, str5, str, c0146a);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (jVar != null) {
                        c0146a.a(true);
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(str4 + "_" + str5 + "_" + str, true);
                        try {
                            com.mbridge.msdk.reward.b.a.a(campaignEx, com.mbridge.msdk.foundation.controller.a.f().j(), "WebView exception: " + th.getMessage(), str4, z3, str5, str6, System.currentTimeMillis() - jCurrentTimeMillis);
                        } catch (Exception unused3) {
                        }
                        jVar.a(str4 + "_" + str, str3, str4, str5, str, c0146a);
                        return;
                    }
                    return;
                }
            } else {
                windVaneWebView2 = windVaneWebViewA;
            }
            c0146a.a(windVaneWebView2);
            if (list != null && list.size() > 0) {
                jVar2 = new com.mbridge.msdk.video.js.a.j(null, campaignEx, list);
            } else {
                jVar2 = new com.mbridge.msdk.video.js.a.j(null, campaignEx);
            }
            com.mbridge.msdk.video.js.a.j jVar3 = jVar2;
            jVar3.a(i2);
            jVar3.a(str4);
            jVar3.a(cVar);
            jVar3.e(z);
            windVaneWebView3 = windVaneWebView2;
            try {
                windVaneWebView3.setWebViewListener(new k(handler, null, z2, z3, str5, jVar, windVaneWebView, str, str3, str4, c0146a, campaignEx, list, jCurrentTimeMillis));
                windVaneWebView3.setObject(jVar3);
                windVaneWebView3.loadUrl(str2);
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
        try {
            windVaneWebView3.setRid(str5);
        } catch (Exception e5) {
            e = e5;
            if (MBridgeConstans.DEBUG) {
                s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
            }
            if (jVar != null) {
                jVar.a(str7, str3, str4, str5, str, null, e.getLocalizedMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized String b(int i2) {
        switch (i2) {
            case 200:
                return MimeTypes.BASE_TYPE_VIDEO;
            case 201:
                return "zip/html";
            case 202:
            case 204:
            default:
                return Platform.UNKNOWN;
            case 203:
                return "temp";
            case 205:
                return "tpl";
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static class k extends com.mbridge.msdk.mbjscommon.c.b {
        private Handler a;
        private Runnable b;
        private final boolean c;
        private final boolean d;
        private String e;
        private final j f;
        private final WindVaneWebView g;
        private final String h;
        private final String i;
        private final String j;
        private final a.C0146a k;
        private final CampaignEx l;
        private List<CampaignEx> m;
        private long n;
        private boolean o;
        private boolean p;
        private final Runnable q;
        private final Runnable r;

        public k(Handler handler, Runnable runnable, boolean z, final boolean z2, final String str, final j jVar, WindVaneWebView windVaneWebView, final String str2, final String str3, final String str4, final a.C0146a c0146a, final CampaignEx campaignEx, List<CampaignEx> list, final long j) {
            this.a = handler;
            this.b = runnable;
            this.c = z;
            this.d = z2;
            this.e = str;
            this.f = jVar;
            this.g = windVaneWebView;
            this.h = str2;
            this.i = str4;
            this.j = str3;
            this.k = c0146a;
            this.l = campaignEx;
            this.m = list;
            this.n = j;
            this.r = new Runnable() { // from class: com.mbridge.msdk.reward.adapter.c.k.1
                @Override // java.lang.Runnable
                public final void run() {
                    WindVaneWebView windVaneWebViewA;
                    s.a("WindVaneWebView", "WebView onPageFinish timeout exception after 5s");
                    if (jVar != null && c0146a != null) {
                        c0146a.a(true);
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(str4 + "_" + str + "_" + str2, true);
                        try {
                            com.mbridge.msdk.reward.b.a.a(campaignEx, com.mbridge.msdk.foundation.controller.a.f().j(), "WebView onPageFinish timeout exception after 5s", str4, z2, str, campaignEx.getRequestIdNotice(), System.currentTimeMillis() - j);
                        } catch (Exception unused) {
                        }
                        jVar.a(str4 + "_" + str2, str3, str4, str, str2, c0146a);
                    }
                    if (c0146a == null || (windVaneWebViewA = c0146a.a()) == null) {
                        return;
                    }
                    try {
                        windVaneWebViewA.release();
                    } catch (Exception unused2) {
                    }
                }
            };
            this.q = new Runnable() { // from class: com.mbridge.msdk.reward.adapter.c.k.2
                @Override // java.lang.Runnable
                public final void run() {
                    WindVaneWebView windVaneWebViewA;
                    s.a("WindVaneWebView", "WebView readyState timeout exception after 5s");
                    if (jVar != null && c0146a != null) {
                        c0146a.a(true);
                        ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(str4 + "_" + str + "_" + str2, true);
                        try {
                            com.mbridge.msdk.reward.b.a.a(campaignEx, com.mbridge.msdk.foundation.controller.a.f().j(), "WebView readyState timeout exception after 5s", str4, z2, str, campaignEx.getRequestIdNotice(), System.currentTimeMillis() - j);
                        } catch (Exception unused) {
                        }
                        jVar.a(str4 + "_" + str2, str3, str4, str, str2, c0146a);
                    }
                    if (c0146a == null || (windVaneWebViewA = c0146a.a()) == null) {
                        return;
                    }
                    try {
                        windVaneWebViewA.release();
                    } catch (Exception unused2) {
                    }
                }
            };
            if (handler != null) {
                handler.postDelayed(this.r, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i) {
            super.a(webView, i);
            if (this.a != null && this.q != null) {
                this.a.removeCallbacks(this.q);
            }
            if (this.a != null && this.r != null) {
                this.a.removeCallbacks(this.r);
            }
            if (this.p) {
                return;
            }
            try {
                com.mbridge.msdk.reward.b.a.a(this.l, com.mbridge.msdk.foundation.controller.a.f().j(), "preload temp readState: " + i, this.i, this.d, this.e, this.l.getRequestIdNotice(), System.currentTimeMillis() - this.n);
            } catch (Exception unused) {
            }
            String str = this.i + "_" + this.h;
            if (i == 1) {
                s.a("WindVaneWebView", "TempalteWindVaneWebviewClient template 预加载成功 state ：" + i);
                if (this.b != null && this.a != null) {
                    this.a.removeCallbacks(this.b);
                }
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.i + "_" + this.e + "_" + this.h, true);
                if (this.k != null) {
                    this.k.a(true);
                }
                if (this.c) {
                    if (this.d) {
                        s.a("WindVaneWebView", "put templeteCache in bidIVCache ");
                        com.mbridge.msdk.videocommon.a.a(287, this.l.getRequestIdNotice(), this.k);
                    } else {
                        s.a("WindVaneWebView", "put templeteCache in iVCache ");
                        com.mbridge.msdk.videocommon.a.b(287, this.l.getRequestIdNotice(), this.k);
                    }
                } else if (this.d) {
                    s.a("WindVaneWebView", "put templeteCache in bidRVCache ");
                    com.mbridge.msdk.videocommon.a.a(94, this.l.getRequestIdNotice(), this.k);
                } else {
                    s.a("WindVaneWebView", "put templeteCache in rVCache ");
                    com.mbridge.msdk.videocommon.a.b(94, this.l.getRequestIdNotice(), this.k);
                }
                if (this.f != null) {
                    this.f.a(str, this.j, this.i, this.e, this.h, this.k);
                }
            } else if (this.f != null) {
                this.f.a(str, this.j, this.i, this.e, this.h, this.k, "state " + i);
            }
            this.p = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) {
            super.a(webView, i, str, str2);
            s.a("WindVaneWebView", "TempalteWindVaneWebviewClient preLoadTemplate onReceivedError: " + str);
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.i + "_" + this.e + "_" + this.h, false);
            try {
                com.mbridge.msdk.reward.b.a.a(this.l, com.mbridge.msdk.foundation.controller.a.f().j(), "WebView onReceivedError exception: " + str, this.i, this.d, this.e, this.l.getRequestIdNotice(), System.currentTimeMillis() - this.n);
            } catch (Exception unused) {
            }
            if (this.a != null) {
                if (this.q != null) {
                    this.a.removeCallbacks(this.r);
                }
                if (this.q != null) {
                    this.a.removeCallbacks(this.q);
                }
            }
            try {
                String str3 = this.i + "_" + this.h;
                if (this.k != null) {
                    this.k.a(false);
                }
                if (this.f != null) {
                    this.f.a(str3, this.j, this.i, this.e, str2, this.k, str);
                }
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    s.d("WindVaneWebView", e.getLocalizedMessage());
                }
            }
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.a(webView, sslErrorHandler, sslError);
            StringBuilder sb = new StringBuilder();
            sb.append("TempalteWindVaneWebviewClient preLoadTemplate onReceivedSslError: ");
            sb.append(sslError == null ? "" : Integer.valueOf(sslError.getPrimaryError()));
            s.a("WindVaneWebView", sb.toString());
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.i + "_" + this.e + "_" + this.h, false);
            try {
                CampaignEx campaignEx = this.l;
                Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("WebView onReceivedSslError exception: ");
                sb2.append(sslError == null ? "SSLError" : Integer.toString(sslError.getPrimaryError()));
                com.mbridge.msdk.reward.b.a.a(campaignEx, contextJ, sb2.toString(), this.i, this.d, this.e, this.l.getRequestIdNotice(), System.currentTimeMillis() - this.n);
            } catch (Exception unused) {
            }
            if (this.a != null) {
                if (this.q != null) {
                    this.a.removeCallbacks(this.r);
                }
                if (this.q != null) {
                    this.a.removeCallbacks(this.q);
                }
            }
            try {
                String str = this.i + "_" + this.h;
                if (this.k != null) {
                    this.k.a(false);
                }
                if (this.f != null) {
                    this.f.a(str, this.j, this.i, this.e, this.h, this.k, sslError == null ? "" : Integer.toString(sslError.getPrimaryError()));
                }
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    s.d("WindVaneWebView", e.getLocalizedMessage());
                }
            }
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) {
            super.a(webView, str);
            s.a("WindVaneWebView", "TempalteWindVaneWebviewClient preLoadTemplate onPageFinished: ");
            if (this.a != null && this.r != null) {
                this.a.removeCallbacks(this.r);
            }
            if (this.o) {
                return;
            }
            try {
                com.mbridge.msdk.reward.b.a.a(this.l, com.mbridge.msdk.foundation.controller.a.f().j(), "preload temp onPageFinish", this.i, this.d, this.e, this.l.getRequestIdNotice(), System.currentTimeMillis() - this.n);
            } catch (Exception unused) {
            }
            if (!str.contains("wfr=1")) {
                String str2 = this.i + "_" + this.h;
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.i + "_" + this.e + "_" + this.h, true);
                if (this.b != null && this.a != null) {
                    this.a.removeCallbacks(this.b);
                }
                if (this.k != null) {
                    this.k.a(true);
                }
                if (this.c) {
                    if (this.l.isBidCampaign()) {
                        s.a("WindVaneWebView", "TempalteWindVaneWebviewClient Tempalte put templeteCache in bidIVCache ");
                        com.mbridge.msdk.videocommon.a.a(287, this.l.getRequestIdNotice(), this.k);
                    } else {
                        s.a("WindVaneWebView", "TempalteWindVaneWebviewClient Tempalte put templeteCache in iVCache ");
                        com.mbridge.msdk.videocommon.a.b(287, this.l.getRequestIdNotice(), this.k);
                    }
                } else if (this.l.isBidCampaign()) {
                    s.a("WindVaneWebView", "TempalteWindVaneWebviewClient Tempalte put templeteCache in bidRVCache ");
                    com.mbridge.msdk.videocommon.a.a(94, this.l.getRequestIdNotice(), this.k);
                } else {
                    s.a("WindVaneWebView", "TempalteWindVaneWebviewClient Tempalte put templeteCache in rVCache ");
                    com.mbridge.msdk.videocommon.a.b(94, this.l.getRequestIdNotice(), this.k);
                }
                if (this.f != null) {
                    this.f.a(str2, this.j, this.i, this.e, this.h, this.k);
                }
            } else if (this.a != null && this.q != null) {
                this.a.postDelayed(this.q, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(webView);
            this.o = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01f6 A[Catch: all -> 0x0201, TRY_LEAVE, TryCatch #1 {, blocks: (B:5:0x000d, B:6:0x0029, B:7:0x0047, B:9:0x005e, B:10:0x006b, B:12:0x009a, B:14:0x00a0, B:16:0x00aa, B:19:0x00b1, B:21:0x00b7, B:22:0x00c1, B:24:0x00c7, B:26:0x00db, B:28:0x00e9, B:30:0x00f9, B:31:0x00fd, B:33:0x010a, B:35:0x015b, B:37:0x0171, B:39:0x0191, B:52:0x01db, B:53:0x01f2, B:55:0x01f6, B:32:0x0104), top: B:64:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void a(boolean r28, android.os.Handler r29, boolean r30, boolean r31, java.lang.String r32, java.lang.String r33, java.lang.String r34, java.lang.String r35, java.lang.String r36, int r37, com.mbridge.msdk.foundation.entity.CampaignEx r38, java.util.List<com.mbridge.msdk.foundation.entity.CampaignEx> r39, java.lang.String r40, java.lang.String r41, com.mbridge.msdk.videocommon.d.c r42, com.mbridge.msdk.reward.adapter.c.j r43, boolean r44) {
        /*
            Method dump skipped, instructions count: 516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.reward.adapter.c.a(boolean, android.os.Handler, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, com.mbridge.msdk.foundation.entity.CampaignEx, java.util.List, java.lang.String, java.lang.String, com.mbridge.msdk.videocommon.d.c, com.mbridge.msdk.reward.adapter.c$j, boolean):void");
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static class b extends com.mbridge.msdk.mbjscommon.c.a {
        private final Handler a;
        private final Runnable b;
        private final boolean c;
        private final boolean d;
        private int e;
        private String f;
        private String g;
        private String h;
        private String i;
        private a.C0146a j;
        private CampaignEx k;
        private List<CampaignEx> l;
        private com.mbridge.msdk.videocommon.d.c m;
        private final j n;
        private boolean o;
        private boolean p;
        private boolean q;
        private int r = 0;
        private boolean s;
        private long t;

        public b(boolean z, Handler handler, Runnable runnable, boolean z2, boolean z3, int i, String str, String str2, String str3, String str4, a.C0146a c0146a, CampaignEx campaignEx, List<CampaignEx> list, com.mbridge.msdk.videocommon.d.c cVar, j jVar, boolean z4, long j) {
            this.a = handler;
            this.b = runnable;
            this.c = z2;
            this.d = z3;
            this.e = i;
            this.f = str;
            this.h = str2;
            this.g = str3;
            this.i = str4;
            this.j = c0146a;
            this.k = campaignEx;
            this.l = list;
            this.m = cVar;
            this.n = jVar;
            this.o = z4;
            this.s = z;
            this.t = j;
            s.a("RewardCampaignsResourceManager_test", "开始预加载大模板");
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i) {
            s.a("RewardCampaignsResourceManager_test", "收到大模板 readyState 回调: " + i);
            if (this.q) {
                return;
            }
            try {
                com.mbridge.msdk.reward.b.a.a(this.k, com.mbridge.msdk.foundation.controller.a.f().j(), "preload tpl readyState: " + i, this.g, this.d, this.i, this.k.getRequestIdNotice(), System.currentTimeMillis() - this.t);
            } catch (Exception unused) {
            }
            String str = this.g + "_" + this.f;
            s.a("test_pre_load_tpl", "CampaignTPLWindVaneWebviewClient 开始预加载大模板资源 readyState： " + i + " isCache: " + this.o);
            if (i == 1) {
                s.a("RVWindVaneWebView", "CampaignTPLWindVaneWebviewClient templete preload readyState state = " + i);
                if (this.o) {
                    com.mbridge.msdk.videocommon.a.d(this.g + "_" + this.i);
                } else {
                    com.mbridge.msdk.videocommon.a.c(this.g + "_" + this.i);
                }
                s.a("test_pre_load_tpl", "添加大模板： " + this.g + "_" + this.i + "_" + this.f);
                StringBuilder sb = new StringBuilder();
                sb.append(this.g);
                sb.append("_");
                sb.append(this.i);
                sb.append("_");
                sb.append(this.f);
                com.mbridge.msdk.videocommon.a.a(sb.toString(), this.j, true, this.o);
                if (this.a != null && this.b != null) {
                    this.a.removeCallbacks(this.b);
                }
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.g + "_" + this.i + "_" + this.f, true);
                if (this.j != null) {
                    this.j.a(true);
                }
                if (this.n != null) {
                    s.a("RVWindVaneWebView", "CampaignTPLWindVaneWebviewClient rewardTemplatePreLoadListener.onPreLoadSuccess ");
                    this.n.a(str, this.h, this.g, this.i, this.f, this.j);
                }
            } else {
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.g + "_" + this.i + "_" + this.f, false);
                if (this.j != null) {
                    this.j.a(false);
                }
                if (this.n != null) {
                    this.n.a(str, this.h, this.g, this.i, this.f, this.j, "state 2");
                }
            }
            this.q = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a
        public final void a(WebView webView, String str, String str2, int i, int i2) {
            s.a("RVWindVaneWebView", "loadAds: unitID " + str2 + " type " + i + " adType " + i2);
            boolean z = true;
            if (i == 1) {
                try {
                    com.mbridge.msdk.reward.a.a aVar = new com.mbridge.msdk.reward.a.a();
                    aVar.b(false);
                    if (i2 != 2) {
                        z = false;
                    }
                    aVar.a(z);
                    aVar.b(str, str2);
                    aVar.a(new com.mbridge.msdk.video.bt.module.b.a(null));
                    aVar.d(false);
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a
        public final void a(Object obj, String str) {
            s.a("RewardCampaignsResourceManager_test", "====开始预加载播放模板====");
            if (obj != null) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        final String strOptString = new JSONObject(str).optString("id");
                        com.mbridge.msdk.video.bt.a.c.a().a(obj, strOptString);
                        final String strC = com.mbridge.msdk.video.bt.a.c.a().c(strOptString);
                        final CampaignEx campaignExA = com.mbridge.msdk.video.bt.a.c.a().a(strOptString);
                        final com.mbridge.msdk.videocommon.d.c cVarB = com.mbridge.msdk.video.bt.a.c.a().b(strOptString);
                        s.a("RewardCampaignsResourceManager_test", "preloadSubPlayTemplateView id = " + strOptString);
                        final ArrayList arrayList = new ArrayList();
                        arrayList.add(campaignExA);
                        com.mbridge.msdk.mbjscommon.windvane.a aVar = (com.mbridge.msdk.mbjscommon.windvane.a) obj;
                        if (aVar.a instanceof WindVaneWebView) {
                            s.a("RewardCampaignsResourceManager_test", "开始预加载播放模板 00 ");
                            final WindVaneWebView windVaneWebView = aVar.a;
                            final c cVar = m.a;
                            final boolean z = this.s;
                            int i = this.r == 0 ? 3 : 6;
                            if (windVaneWebView != null) {
                                if (campaignExA == null || cVarB == null || campaignExA.getRewardTemplateMode() == null || TextUtils.isEmpty(strC)) {
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put("id", strOptString);
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put("result", 2);
                                        jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, "data is null");
                                        jSONObject.put("data", jSONObject2);
                                        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) windVaneWebView, "onSubPlayTemplateViewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                                    } catch (Exception e) {
                                        if (MBridgeConstans.DEBUG) {
                                            s.d("RewardCampaignsResourceManager", e.getLocalizedMessage());
                                        }
                                    }
                                } else if (TextUtils.isEmpty(campaignExA.getRewardTemplateMode().d())) {
                                    JSONObject jSONObject3 = new JSONObject();
                                    try {
                                        jSONObject3.put("id", strOptString);
                                        JSONObject jSONObject4 = new JSONObject();
                                        jSONObject4.put("result", 1);
                                        jSONObject4.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, "data is null");
                                        jSONObject3.put("data", jSONObject4);
                                        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) windVaneWebView, "onSubPlayTemplateViewLoad", Base64.encodeToString(jSONObject3.toString().getBytes(), 2));
                                    } catch (Exception e2) {
                                        if (MBridgeConstans.DEBUG) {
                                            s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
                                        }
                                    }
                                } else {
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.c.3
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            s.a("RewardCampaignsResourceManager_test", "开始预加载播放模板 55");
                                            c.a(c.this, z, windVaneWebView, campaignExA.getRewardTemplateMode().d(), 0, campaignExA, arrayList, H5DownLoadManager.getInstance().getH5ResAddress(campaignExA.getRewardTemplateMode().d()), strC, cVarB, strOptString, c.this.d);
                                        }
                                    }, i * 1000);
                                }
                            }
                            this.r++;
                            return;
                        }
                        s.a("RewardCampaignsResourceManager_test", "开始预加载播放模板 11 ");
                        return;
                    }
                } catch (Throwable th) {
                    s.d("RVWindVaneWebView", th.getMessage());
                    return;
                }
            }
            s.a("RewardCampaignsResourceManager_test", "preloadSubPlayTemplateView: RewardCampaignsResourceManager  object or params null");
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) {
            super.a(webView, str);
            s.a("RewardCampaignsResourceManager_test", "onPageFinished");
            if (this.p) {
                return;
            }
            try {
                com.mbridge.msdk.reward.b.a.a(this.k, com.mbridge.msdk.foundation.controller.a.f().j(), "preload tpl onPageFinish", this.g, this.d, this.i, this.k.getRequestIdNotice(), System.currentTimeMillis() - this.t);
            } catch (Exception unused) {
            }
            String str2 = this.g + "_" + this.f;
            if (!str.contains("wfr=1")) {
                s.a("RVWindVaneWebView", "CampaignTPLWindVaneWebviewClient templete preload wfr=1 不包含 ");
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.g + "_" + this.i + "_" + this.f, true);
                if (this.a != null && this.b != null) {
                    this.a.removeCallbacks(this.b);
                }
                if (this.j != null) {
                    this.j.a(true);
                }
                if (this.n != null) {
                    s.a("RewardCampaignsResourceManager", "CampaignTPLWindVaneWebviewClient rewardTemplatePreLoadListener.onPreLoadSuccess ");
                    this.n.a(str2, this.h, this.g, this.i, this.f, this.j);
                }
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(webView);
            this.p = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.a, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) {
            super.a(webView, i, str, str2);
            s.a("RewardCampaignsResourceManager_test", "onReceivedError： " + i + "  " + str);
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.g + "_" + this.i + "_" + this.f, false);
            if (this.n != null) {
                String str3 = this.g + "_" + this.f;
                if (this.j != null) {
                    this.j.a(false);
                }
                this.n.a(str3, this.h, this.g, this.i, this.f, this.j, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RewardCampaignsResourceManager.java */
    static final class m {
        private static final c a = new c();
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static class l extends com.mbridge.msdk.mbjscommon.c.b {
        private String a;
        private final boolean b;
        private final WindVaneWebView c;
        private final String d;
        private final String e;
        private final a.C0146a f;
        private final CampaignEx g;
        private boolean h;
        private String i;
        private boolean j;
        private boolean k;

        public l(String str, boolean z, WindVaneWebView windVaneWebView, String str2, String str3, a.C0146a c0146a, CampaignEx campaignEx, boolean z2, String str4) {
            this.b = z;
            this.c = windVaneWebView;
            this.d = str2;
            this.e = str3;
            this.f = c0146a;
            this.g = campaignEx;
            this.a = str;
            this.h = z2;
            this.i = str4;
            s.a("WindVaneWebView", "TempalteWindVaneWebviewClientForTPL init");
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i) throws JSONException {
            s.a("RewardCampaignsResourceManager_test", "收到大模板 播放模板 readyState 回调: " + i);
            if (this.k) {
                return;
            }
            if (this.c != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.a);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("result", i);
                    jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, "");
                    jSONObject.put("data", jSONObject2);
                    com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.c, "onSubPlayTemplateViewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("WindVaneWebView", e.getLocalizedMessage());
                    }
                }
            }
            String str = this.e + "_" + this.g.getId() + "_" + this.g.getRequestId() + "_" + this.d;
            if (i == 1) {
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.e + "_" + this.i + "_" + this.d, true);
                if (this.f != null) {
                    this.f.a(true);
                }
                if (this.b) {
                    if (this.g.isBidCampaign()) {
                        s.a("WindVaneWebView", "Tempalte put templeteCache in bidIVCache ");
                        com.mbridge.msdk.videocommon.a.a(str, this.f, false, this.h);
                    } else {
                        s.a("WindVaneWebView", "Tempalte put templeteCache in iVCache ");
                        com.mbridge.msdk.videocommon.a.a(str, this.f, false, this.h);
                    }
                } else if (this.g.isBidCampaign()) {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in bidRVCache ");
                    com.mbridge.msdk.videocommon.a.a(str, this.f, false, this.h);
                } else {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in rVCache ");
                    com.mbridge.msdk.videocommon.a.a(str, this.f, false, this.h);
                }
            } else {
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.e + "_" + this.i + "_" + this.d, false);
                if (this.f != null) {
                    this.f.a(false);
                }
            }
            this.k = true;
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, int i, String str, String str2) throws JSONException {
            s.a("WindVaneWebView", "TempalteWindVaneWebviewClientForTPL preLoadTemplate onReceivedError: " + str);
            ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.e + "_" + this.i + "_" + this.d, false);
            if (this.f != null) {
                this.f.a(false);
            }
            if (this.c != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.a);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("result", 2);
                    jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, str);
                    jSONObject.put("data", jSONObject2);
                    com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.c, "onSubPlayTemplateViewLoad", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                } catch (Exception e) {
                    if (MBridgeConstans.DEBUG) {
                        s.d("WindVaneWebView", e.getLocalizedMessage());
                    }
                }
            }
        }

        @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
        public final void a(WebView webView, String str) throws JSONException {
            s.a("WindVaneWebView", "TempalteWindVaneWebviewClientForTPL preLoadTemplate onPageFinished: ");
            if (this.j) {
                return;
            }
            if (!str.contains("wfr=1")) {
                if (this.c != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("id", this.a);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("result", 1);
                        jSONObject2.put(CampaignEx.JSON_NATIVE_VIDEO_ERROR, "");
                        jSONObject.put("data", jSONObject2);
                        com.mbridge.msdk.mbjscommon.windvane.h.a().a((WebView) this.c, "componentReact", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                    } catch (Exception e) {
                        if (MBridgeConstans.DEBUG) {
                            s.d("WindVaneWebView", e.getLocalizedMessage());
                        }
                    }
                }
                ResDownloadCheckManager.getInstance().setTemplatePreLoadDone(this.e + "_" + this.i + "_" + this.d, true);
                if (this.f != null) {
                    this.f.a(true);
                }
                String str2 = this.e + "_" + this.g.getId() + "_" + this.g.getRequestId() + "_" + this.d;
                if (this.b) {
                    if (this.g.isBidCampaign()) {
                        s.a("WindVaneWebView", "Tempalte put templeteCache in bidIVCache ");
                        com.mbridge.msdk.videocommon.a.a(287, this.g.getRequestIdNotice(), this.f);
                    } else {
                        s.a("WindVaneWebView", "Tempalte put templeteCache in iVCache ");
                        com.mbridge.msdk.videocommon.a.a(str2, this.f, false, this.h);
                    }
                } else if (this.g.isBidCampaign()) {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in bidRVCache ");
                    com.mbridge.msdk.videocommon.a.a(94, this.g.getRequestIdNotice(), this.f);
                } else {
                    s.a("WindVaneWebView", "Tempalte put templeteCache in rVCache ");
                    com.mbridge.msdk.videocommon.a.a(str2, this.f, false, this.h);
                }
            }
            com.mbridge.msdk.mbjscommon.windvane.h.a().a(webView);
            this.j = true;
        }
    }

    /* compiled from: RewardCampaignsResourceManager.java */
    private static class a {
        boolean a;
        boolean b;
        int c;
        int d;
        String e;
        String f;
        int g;
        List<CampaignEx> h;
        CopyOnWriteArrayList<CampaignEx> i;

        public a(boolean z, boolean z2, int i, int i2, String str, String str2, int i3, List<CampaignEx> list) {
            this.a = z;
            this.b = z2;
            this.c = i;
            this.d = i2;
            this.e = str;
            this.f = str2;
            this.g = i3;
            this.h = list;
            this.i = new CopyOnWriteArrayList<>(list);
        }
    }

    public final synchronized void b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.mbridge.msdk.reward.adapter.c.4
                @Override // java.lang.Runnable
                public final void run() {
                    c.this.d();
                }
            });
        } else {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void d() {
        try {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ != null) {
                this.e.add(new WindVaneWebView(contextJ));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private synchronized WindVaneWebView a(boolean z) {
        if (this.e != null && this.e.size() > 0) {
            return this.e.remove(0);
        }
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ != null) {
            this.e.add(new WindVaneWebView(contextJ));
            if (z) {
                this.e.add(new WindVaneWebView(contextJ));
            }
        }
        return null;
    }

    static /* synthetic */ void a(c cVar, boolean z, WindVaneWebView windVaneWebView, String str, int i2, CampaignEx campaignEx, List list, String str2, String str3, com.mbridge.msdk.videocommon.d.c cVar2, String str4, boolean z2) {
        com.mbridge.msdk.video.js.a.j jVar;
        String requestId;
        try {
            a.C0146a c0146a = new a.C0146a();
            WindVaneWebView windVaneWebViewA = m.a.a(false);
            if (windVaneWebViewA == null) {
                windVaneWebViewA = new WindVaneWebView(com.mbridge.msdk.foundation.controller.a.f().j());
            }
            WindVaneWebView windVaneWebView2 = windVaneWebViewA;
            c0146a.a(windVaneWebView2);
            if (list != null && list.size() > 0) {
                List<CampaignEx> listA = com.mbridge.msdk.videocommon.download.b.getInstance().a(str3);
                if (listA != null && listA.size() > 0) {
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        CampaignEx campaignEx2 = (CampaignEx) list.get(i3);
                        for (CampaignEx campaignEx3 : listA) {
                            if (campaignEx3.getId().equals(campaignEx2.getId()) && campaignEx3.getRequestId().equals(campaignEx2.getRequestId())) {
                                campaignEx2.setReady(true);
                                list.set(i3, campaignEx2);
                            }
                        }
                    }
                }
                jVar = new com.mbridge.msdk.video.js.a.j(null, campaignEx, list);
                requestId = ((CampaignEx) list.get(0)).getRequestId();
            } else {
                jVar = new com.mbridge.msdk.video.js.a.j(null, campaignEx);
                requestId = campaignEx.getRequestId();
            }
            String str5 = requestId;
            com.mbridge.msdk.video.js.a.j jVar2 = jVar;
            jVar2.a(i2);
            jVar2.a(str3);
            jVar2.c(str4);
            jVar2.a(cVar2);
            jVar2.e(z);
            windVaneWebView2.setWebViewListener(new l(str4, false, windVaneWebView, str, str3, c0146a, campaignEx, z2, str5));
            windVaneWebView2.setObject(jVar2);
            windVaneWebView2.loadUrl(str2);
            windVaneWebView2.setRid(str5);
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                s.d("RewardCampaignsResourceManager", e2.getLocalizedMessage());
            }
        }
    }
}
