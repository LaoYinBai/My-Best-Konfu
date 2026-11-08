package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.appsflyer.b;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class y {
    y() {
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static boolean m246(Context context) {
        return m244(context) | m247(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004b A[Catch: Throwable -> 0x0093, ClassNotFoundException -> 0x009a, TryCatch #2 {ClassNotFoundException -> 0x009a, Throwable -> 0x0093, blocks: (B:5:0x000c, B:10:0x0038, B:15:0x004b, B:20:0x006b, B:23:0x0087, B:24:0x008d), top: B:32:0x000c }] */
    /* renamed from: ˏ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean m247(android.content.Context r6) {
        /*
            com.appsflyer.AppsFlyerLib r0 = com.appsflyer.AppsFlyerLib.getInstance()
            boolean r0 = r0.isTrackingStopped()
            r1 = 0
            if (r0 == 0) goto Lc
            return r1
        Lc:
            java.lang.String r0 = "com.google.android.gms.iid.InstanceIDListenerService"
            java.lang.Class.forName(r0)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.String r2 = "com.google.android.gms.iid.InstanceID"
            java.lang.Class<com.appsflyer.GcmInstanceIdListener> r3 = com.appsflyer.GcmInstanceIdListener.class
            r4 = 0
            r0.<init>(r2, r4, r6, r3)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.String r3 = "com.google.android.gms.iid.InstanceID"
            java.lang.Class<com.google.android.gms.iid.InstanceIDListenerService> r5 = com.google.android.gms.iid.InstanceIDListenerService.class
            r2.<init>(r3, r4, r6, r5)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.util.List r0 = r3.queryIntentServices(r0, r1)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            r3 = 1
            if (r0 <= 0) goto L35
            r0 = 1
            goto L36
        L35:
            r0 = 0
        L36:
            if (r0 != 0) goto L4b
            android.content.pm.PackageManager r0 = r6.getPackageManager()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.util.List r0 = r0.queryIntentServices(r2, r1)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            if (r0 <= 0) goto L48
            r0 = 1
            goto L49
        L48:
            r0 = 0
        L49:
            if (r0 == 0) goto La2
        L4b:
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.String r2 = "com.google.android.c2dm.intent.RECEIVE"
            java.lang.String r5 = "com.google.android.gms.gcm.GcmReceiver"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            r0.<init>(r2, r4, r6, r5)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            android.content.pm.PackageManager r2 = r6.getPackageManager()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.util.List r0 = r2.queryBroadcastReceivers(r0, r1)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            if (r0 <= 0) goto L68
            r0 = 1
            goto L69
        L68:
            r0 = 0
        L69:
            if (r0 == 0) goto L8d
            java.lang.String r0 = r6.getPackageName()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            r2.<init>()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            r2.append(r0)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.String r0 = ".permission.C2D_MESSAGE"
            r2.append(r0)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            boolean r6 = com.appsflyer.j.AnonymousClass1.m181(r6, r0)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            if (r6 == 0) goto L87
            return r3
        L87:
            java.lang.String r6 = "Cannot verify existence of the app's \"permission.C2D_MESSAGE\" permission in the manifest. Please refer to documentation."
            com.appsflyer.AFLogger.afWarnLog(r6)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            goto La2
        L8d:
            java.lang.String r6 = "Cannot verify existence of GcmReceiver receiver in the manifest. Please refer to documentation."
            com.appsflyer.AFLogger.afWarnLog(r6)     // Catch: java.lang.Throwable -> L93 java.lang.ClassNotFoundException -> L9a
            goto La2
        L93:
            r6 = move-exception
            java.lang.String r0 = "An error occurred while trying to verify manifest declarations: "
            com.appsflyer.AFLogger.afErrorLog(r0, r6)
            goto La2
        L9a:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.appsflyer.AFLogger.afRDLog(r6)
        La2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.y.m247(android.content.Context):boolean");
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private static boolean m244(Context context) {
        Intent intent;
        Intent intent2;
        if (AppsFlyerLib.getInstance().isTrackingStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.iid.FirebaseInstanceIdService");
            intent = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, FirebaseInstanceIdListener.class);
            intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, FirebaseInstanceIdService.class);
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest declarations: ", th);
        }
        if (!(context.getPackageManager().queryIntentServices(intent, 0).size() > 0)) {
            if (!(context.getPackageManager().queryIntentServices(intent2, 0).size() > 0)) {
                AFLogger.afWarnLog("Cannot verify existence of our InstanceID Listener Service in the manifest. Please refer to documentation.");
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˊ, reason: contains not printable characters */
    public static String m243(WeakReference<Context> weakReference, String str) {
        try {
            Class<?> cls = Class.forName("com.google.android.gms.iid.InstanceID");
            Class.forName("com.google.android.gms.gcm.GcmReceiver");
            String str2 = (String) cls.getDeclaredMethod("getToken", String.class, String.class).invoke(cls.getDeclaredMethod("getInstance", Context.class).invoke(cls, weakReference.get()), str, "GCM");
            if (str2 != null) {
                return str2;
            }
            AFLogger.afWarnLog("Couldn't get token using reflection.");
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't get token using GoogleCloudMessaging. ", th);
            return null;
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    static void m248(Context context, b.e.C0088b c0088b) {
        StringBuilder sb = new StringBuilder("updateServerUninstallToken called with: ");
        sb.append(c0088b.toString());
        AFLogger.afInfoLog(sb.toString());
        b.e.C0088b c0088bM141 = b.e.C0088b.m141(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
        if (context.getSharedPreferences("appsflyer-data", 0).getBoolean("sentRegisterRequestToAF", false) && c0088bM141.m142() != null && c0088bM141.m142().equals(c0088b.m142())) {
            return;
        }
        AppsFlyerProperties.getInstance().set("afUninstallToken", c0088b.toString());
        AppsFlyerLib.getInstance().m112(context, c0088b.m142());
    }

    static class b extends AsyncTask<Void, Void, String> {

        /* renamed from: ˏ, reason: contains not printable characters */
        private final WeakReference<Context> f314;

        /* renamed from: ॱ, reason: contains not printable characters */
        private String f315;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ String doInBackground(Void[] voidArr) {
            return m249();
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(String str) {
            String str2 = str;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String string = AppsFlyerProperties.getInstance().getString("afUninstallToken");
            b.e.C0088b c0088b = new b.e.C0088b(str2);
            if (string == null) {
                y.m248(this.f314.get(), c0088b);
                return;
            }
            b.e.C0088b c0088bM141 = b.e.C0088b.m141(string);
            if (c0088bM141.m143(c0088b)) {
                y.m248(this.f314.get(), c0088bM141);
            }
        }

        b(WeakReference<Context> weakReference) {
            this.f314 = weakReference;
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            super.onPreExecute();
            this.f315 = AppsFlyerProperties.getInstance().getString("gcmProjectNumber");
        }

        /* renamed from: ˏ, reason: contains not printable characters */
        private String m249() {
            try {
                if (this.f315 != null) {
                    return y.m243(this.f314, this.f315);
                }
                return null;
            } catch (Throwable th) {
                AFLogger.afErrorLog("Error registering for uninstall feature", th);
                return null;
            }
        }
    }
}
