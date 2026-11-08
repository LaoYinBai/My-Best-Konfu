package com.kwad.sdk.ec.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.e;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.core.network.d;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.network.j;
import com.kwad.sdk.core.report.f;
import com.kwad.sdk.core.report.m;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.ec.kwai.b;
import com.kwad.sdk.ec.model.EcKwaiInfo;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.u;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum EcKwaiInfoManager {
    INSTAANCE;

    private static final int MESSAGE_DOWNLOAD_DOWN = 3;
    private static final int MESSAGE_RELEASE = 2;
    private static final int STATUS_DONE = 2;
    private static final int STATUS_INIT = 0;
    private static final int STATUS_RUNNING = 1;
    private String mAdLogStr;
    private int mAdPos;
    private AdTemplate mAdTemplate;
    private com.kwad.sdk.ec.download.a mApkDownloadHelper;
    private Context mAppContext;
    private int mContentType;
    private EcKwaiInfo mEcKwaiInfo;
    private long mPosId;
    private final Handler mHandler = new a();
    private int status = 0;
    private int mDownloadSource = 0;

    @SuppressLint({"HandlerLeak"})
    private class a extends Handler {
        public a() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 2:
                    EcKwaiInfoManager.this.release();
                    break;
                case 3:
                    EcKwaiInfoManager.this.onDownloadEnd((EcKwaiInfo) message.obj);
                    break;
            }
        }
    }

    EcKwaiInfoManager() {
    }

    private i<d, BaseResultData> createPullNewRequest(final String str) {
        return new i<d, BaseResultData>() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public d b() {
                return new d() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.5.2
                    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
                    public String a() {
                        return str;
                    }
                };
            }

            @Override // com.kwad.sdk.core.network.i
            @NonNull
            protected BaseResultData b(String str2) {
                return new BaseResultData() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.5.1
                };
            }

            @Override // com.kwad.sdk.core.network.i
            protected boolean g() {
                return false;
            }
        };
    }

    private i<b, EcKwaiInfo> createRequest() {
        return new i<b, EcKwaiInfo>() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public b b() {
                return new b();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public EcKwaiInfo b(String str) {
                JSONObject jSONObject = new JSONObject(str);
                EcKwaiInfo ecKwaiInfo = new EcKwaiInfo();
                ecKwaiInfo.parseJson(jSONObject);
                return ecKwaiInfo;
            }
        };
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c5 A[PHI: r4
  0x00c5: PHI (r4v5 java.lang.String) = (r4v4 java.lang.String), (r4v8 java.lang.String) binds: [B:36:0x00c3, B:33:0x00b6] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String generatePullNewUrl(java.lang.String r8) {
        /*
            r7 = this;
            com.kwad.sdk.ec.model.EcKwaiInfo r0 = r7.mEcKwaiInfo
            java.lang.String r0 = com.kwad.sdk.ec.model.EcKwaiInfo.getPullNewUrl(r0)
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r2 = r0.getScheme()
            android.net.Uri$Builder r2 = r1.scheme(r2)
            java.lang.String r3 = r0.getAuthority()
            android.net.Uri$Builder r2 = r2.authority(r3)
            java.lang.String r3 = r0.getPath()
            r2.path(r3)
            java.util.Set r2 = r0.getQueryParameterNames()
            java.util.Iterator r2 = r2.iterator()
        L2e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lce
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = -1
            int r5 = r3.hashCode()
            r6 = 3367(0xd27, float:4.718E-42)
            if (r5 == r6) goto L71
            r6 = 3236040(0x3160c8, float:4.534658E-39)
            if (r5 == r6) goto L67
            r6 = 1131700202(0x43745fea, float:244.37466)
            if (r5 == r6) goto L5d
            r6 = 2099989403(0x7d2b4b9b, float:1.423066E37)
            if (r5 == r6) goto L53
            goto L7a
        L53:
            java.lang.String r5 = "siteSet"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L7a
            r4 = 2
            goto L7a
        L5d:
            java.lang.String r5 = "androidId"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L7a
            r4 = 1
            goto L7a
        L67:
            java.lang.String r5 = "imei"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L7a
            r4 = 0
            goto L7a
        L71:
            java.lang.String r5 = "ip"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L7a
            r4 = 3
        L7a:
            switch(r4) {
                case 0: goto Lb9;
                case 1: goto Lac;
                case 2: goto L8f;
                case 3: goto L82;
                default: goto L7d;
            }
        L7d:
            java.lang.String r4 = r0.getQueryParameter(r3)
            goto Lc9
        L82:
            android.content.Context r4 = r7.mAppContext
            java.lang.String r4 = com.kwad.sdk.utils.av.s(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L7d
            goto Lc9
        L8f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.kwad.sdk.KsAdSDKImpl r5 = com.kwad.sdk.KsAdSDKImpl.get()
            java.lang.String r5 = r5.getAppId()
            r4.append(r5)
            java.lang.String r5 = "_"
            r4.append(r5)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            goto Lc9
        Lac:
            android.content.Context r4 = r7.mAppContext
            java.lang.String r4 = com.kwad.sdk.utils.av.t(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L7d
            goto Lc5
        Lb9:
            android.content.Context r4 = r7.mAppContext
            java.lang.String r4 = com.kwad.sdk.utils.av.d(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L7d
        Lc5:
            java.lang.String r4 = com.kwad.sdk.utils.aa.a(r4)
        Lc9:
            r1.appendQueryParameter(r3, r4)
            goto L2e
        Lce:
            android.net.Uri r8 = r1.build()
            java.lang.String r8 = r8.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.ec.download.EcKwaiInfoManager.generatePullNewUrl(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadEnd(EcKwaiInfo ecKwaiInfo) {
        if (ecKwaiInfo != null) {
            this.mEcKwaiInfo = ecKwaiInfo;
            this.status = 2;
            saveInfo();
        } else if (this.mEcKwaiInfo != null) {
            this.status = 2;
        } else {
            this.status = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadReport(int i) {
        HashMap map = new HashMap();
        map.put("referElementType", Integer.valueOf(this.mDownloadSource));
        map.put("adLogStr", this.mAdLogStr);
        map.put("deeplinkAppName", "kuaishou");
        map.put("contentStyle", Integer.valueOf(this.mContentType));
        map.put("position", Integer.valueOf(this.mAdPos + 1));
        map.put("posId", Long.valueOf(this.mPosId));
        if (i == 32) {
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
            map.put("isPackageChanged", Integer.valueOf(ag.b(adInfoJ.downloadId, com.kwad.sdk.core.response.a.a.v(adInfoJ))));
        }
        if (i == 40) {
            map.put("downloadFailedReason", this.mApkDownloadHelper.a);
        }
        f.a(new m(i, map));
    }

    private void performDownload(Context context, String str, final String str2) {
        if (this.mAdTemplate == null) {
            this.mAdTemplate = this.mEcKwaiInfo.generateAdTemplate(str);
        } else {
            com.kwad.sdk.core.response.a.d.j(this.mAdTemplate).adConversionInfo.deeplinkUrl = str;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
        if (adInfoJ.status == 12) {
            adInfoJ.status = 0;
        }
        if (this.mApkDownloadHelper == null) {
            this.mApkDownloadHelper = new com.kwad.sdk.ec.download.a(this.mAdTemplate, new e() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.3
                @Override // com.kwad.sdk.core.download.a.e, com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    EcKwaiInfoManager.this.mHandler.sendEmptyMessage(2);
                    EcKwaiInfoManager.this.onDownloadReport(40);
                }

                @Override // com.kwad.sdk.core.download.a.e, com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    EcKwaiInfoManager.this.onDownloadReport(32);
                }

                @Override // com.kwad.sdk.core.download.a.e, com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadStarted() {
                    EcKwaiInfoManager.this.onDownloadReport(30);
                    EcKwaiInfoManager.this.reportPullNew(str2);
                }

                @Override // com.kwad.sdk.core.download.a.e, com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    EcKwaiInfoManager.this.mHandler.sendEmptyMessage(2);
                }
            });
        }
        this.mApkDownloadHelper.a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postDownloadEndMessage(EcKwaiInfo ecKwaiInfo) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 3;
        messageObtain.arg1 = this.status;
        messageObtain.obj = ecKwaiInfo;
        this.mHandler.sendMessage(messageObtain);
    }

    private void readCache(Context context) {
        String string = context.getSharedPreferences("ksadsdk_pref", 0).getString("ksadsdk_kwai_download", "");
        if (string.isEmpty()) {
            return;
        }
        try {
            this.mEcKwaiInfo = EcKwaiInfo.fromJson(new JSONObject(string));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        this.mApkDownloadHelper.e();
        this.mApkDownloadHelper = null;
        this.mAdTemplate = null;
        this.mDownloadSource = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportPullNew(String str) {
        createPullNewRequest(generatePullNewUrl(str)).a(new j<d, BaseResultData>() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.4
        });
    }

    private void saveInfo() {
        if (this.mAppContext != null) {
            this.mAppContext.getSharedPreferences("ksadsdk_pref", 0).edit().putString("ksadsdk_kwai_download", this.mEcKwaiInfo.toString()).apply();
        }
    }

    public String getAppName() {
        return this.mEcKwaiInfo == null ? "" : this.mEcKwaiInfo.getAppName();
    }

    public void init(Context context) {
        if (this.status > 0) {
            return;
        }
        this.status = 1;
        this.mAppContext = context.getApplicationContext();
        readCache(context);
        createRequest().a(new j<b, EcKwaiInfo>() { // from class: com.kwad.sdk.ec.download.EcKwaiInfoManager.1
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull b bVar, int i, String str) {
                EcKwaiInfoManager.this.postDownloadEndMessage(null);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull b bVar, @NonNull EcKwaiInfo ecKwaiInfo) {
                EcKwaiInfoManager.this.postDownloadEndMessage(ecKwaiInfo);
            }
        });
    }

    public void startDownload(Context context, AdTemplate adTemplate, String str, int i, int i2, String str2) {
        if (this.mDownloadSource == 0) {
            this.mDownloadSource = i;
        }
        if (!TextUtils.isEmpty(adTemplate.photoInfo.productInfo.shennongjiaLog)) {
            this.mAdLogStr = adTemplate.photoInfo.productInfo.shennongjiaLog;
            this.mAdPos = adTemplate.getShowPosition();
            this.mContentType = 1;
            if (adTemplate.mAdScene != null) {
                this.mPosId = adTemplate.mAdScene.getPosId();
            }
        }
        if (this.mAdLogStr == null) {
            this.mAdLogStr = adTemplate.mLiveInfo.shennongjiaLog;
            this.mAdPos = adTemplate.getShowPosition();
            this.mContentType = 2;
            if (adTemplate.mAdScene != null) {
                this.mPosId = adTemplate.mAdScene.getPosId();
            }
        }
        if (this.status == 2) {
            performDownload(context, str, str2);
        } else {
            u.a(context, context.getString(R.string.ksad_download_kwai_waiting), 0);
            init(context);
        }
    }
}
