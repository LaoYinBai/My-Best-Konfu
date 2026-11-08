package com.appsflyer.share;

import android.content.Context;
import android.os.AsyncTask;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CrossPromotionHelper {
    public static void trackAndOpenStore(Context context, String str, String str2) {
        trackAndOpenStore(context, str, str2, null);
    }

    public static void trackAndOpenStore(Context context, String str, String str2, Map<String, String> map) {
        LinkGenerator linkGeneratorM231 = m231(context, str, str2, map, ServerConfigHandler.getUrl(Constants.BASE_URL_APP_APPSFLYER_COM));
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, track And Open Store is disabled", true);
            return;
        }
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        map2.put("af_campaign", str2);
        AppsFlyerLib.getInstance().trackEvent(context, "af_cross_promotion", map2);
        new e(new a(), context, AppsFlyerLib.getInstance().isTrackingStopped()).execute(linkGeneratorM231.generateLink());
    }

    public static void trackCrossPromoteImpression(Context context, String str, String str2) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, Promote Impression is disabled", true);
        } else {
            new e(null, null, AppsFlyerLib.getInstance().isTrackingStopped()).execute(m231(context, str, str2, null, ServerConfigHandler.getUrl("https://impression.%s")).generateLink());
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static LinkGenerator m231(Context context, String str, String str2, Map<String, String> map, String str3) {
        LinkGenerator linkGenerator = new LinkGenerator("af_cross_promotion");
        linkGenerator.m236(str3).m235(str).addParameter(Constants.URL_SITE_ID, context.getPackageName());
        if (str2 != null) {
            linkGenerator.setCampaign(str2);
        }
        if (map != null) {
            linkGenerator.addParameters(map);
        }
        String string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string != null) {
            linkGenerator.addParameter(Constants.URL_ADVERTISING_ID, string);
        }
        return linkGenerator;
    }

    static class e extends AsyncTask<String, Void, Void> {

        /* renamed from: ˊ, reason: contains not printable characters */
        private a f297;

        /* renamed from: ˏ, reason: contains not printable characters */
        private boolean f298;

        /* renamed from: ॱ, reason: contains not printable characters */
        private WeakReference<Context> f299;

        e(a aVar, Context context, boolean z) {
            this.f297 = aVar;
            this.f299 = new WeakReference<>(context);
            this.f298 = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:32:0x009a A[PHI: r2
  0x009a: PHI (r2v4 java.net.HttpURLConnection) = (r2v3 java.net.HttpURLConnection), (r2v7 java.net.HttpURLConnection) binds: [B:31:0x0098, B:22:0x0086] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
        @Override // android.os.AsyncTask
        /* renamed from: ˊ, reason: contains not printable characters and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(java.lang.String... r6) throws java.lang.Throwable {
            /*
                r5 = this;
                boolean r0 = r5.f298
                r1 = 0
                if (r0 == 0) goto L6
                return r1
            L6:
                r0 = 0
                r6 = r6[r0]     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L8e
                java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L8e
                r2.<init>(r6)     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L8e
                java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L8e
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L8b java.lang.Throwable -> L8e
                r3 = 10000(0x2710, float:1.4013E-41)
                r2.setConnectTimeout(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r2.setInstanceFollowRedirects(r0)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                int r3 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r4 = 200(0xc8, float:2.8E-43)
                if (r3 != r4) goto L32
                java.lang.String r3 = "Cross promotion impressions success: "
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.String r6 = r3.concat(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.AFLogger.afInfoLog(r6, r0)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                goto L86
            L32:
                r4 = 301(0x12d, float:4.22E-43)
                if (r3 == r4) goto L55
                r4 = 302(0x12e, float:4.23E-43)
                if (r3 != r4) goto L3b
                goto L55
            L3b:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.String r4 = "call to "
                r0.<init>(r4)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r0.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.String r6 = " failed: "
                r0.append(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r0.append(r3)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.String r6 = r0.toString()     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.AFLogger.afInfoLog(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                goto L86
            L55:
                java.lang.String r3 = "Cross promotion redirection success: "
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.String r6 = r3.concat(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.AFLogger.afInfoLog(r6, r0)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.share.a r6 = r5.f297     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                if (r6 == 0) goto L86
                java.lang.ref.WeakReference<android.content.Context> r6 = r5.f299     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.Object r6 = r6.get()     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                if (r6 == 0) goto L86
                java.lang.String r6 = "Location"
                java.lang.String r6 = r2.getHeaderField(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.share.a r0 = r5.f297     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r0.m238(r6)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                com.appsflyer.share.a r6 = r5.f297     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.ref.WeakReference<android.content.Context> r0 = r5.f299     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                android.content.Context r0 = (android.content.Context) r0     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
                r6.m237(r0)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L9e
            L86:
                if (r2 == 0) goto L9d
                goto L9a
            L89:
                r6 = move-exception
                goto L90
            L8b:
                r6 = move-exception
                r2 = r1
                goto L9f
            L8e:
                r6 = move-exception
                r2 = r1
            L90:
                java.lang.String r0 = r6.getMessage()     // Catch: java.lang.Throwable -> L9e
                r3 = 1
                com.appsflyer.AFLogger.afErrorLog(r0, r6, r3)     // Catch: java.lang.Throwable -> L9e
                if (r2 == 0) goto L9d
            L9a:
                r2.disconnect()
            L9d:
                return r1
            L9e:
                r6 = move-exception
            L9f:
                if (r2 == 0) goto La4
                r2.disconnect()
            La4:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.share.CrossPromotionHelper.e.doInBackground(java.lang.String[]):java.lang.Void");
        }
    }
}
