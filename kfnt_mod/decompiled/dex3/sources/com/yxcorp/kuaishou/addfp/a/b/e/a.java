package com.yxcorp.kuaishou.addfp.a.b.e;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.ss.android.downloadad.api.download.AdDownloadModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a b;
    private static final Uri c = Uri.parse("content://cn.nubia.identity/identity");
    private final Context a;

    private a(Context context) {
        this.a = context;
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    private String a(String str, String str2) {
        Bundle bundleCall;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = this.a.getContentResolver().acquireUnstableContentProviderClient(c);
                bundleCall = contentProviderClientAcquireUnstableContentProviderClient.call(str2, null, null);
                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        contentProviderClientAcquireUnstableContentProviderClient.close();
                    } else {
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                    }
                }
            } else {
                bundleCall = this.a.getContentResolver().call(c, str2, (String) null, (Bundle) null);
            }
            return (bundleCall == null || bundleCall.getInt("code", -1) != 0) ? "" : bundleCall.getString(AdDownloadModel.JsonKey.ID);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public final boolean a() {
        Bundle bundleCall;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = this.a.getContentResolver().acquireUnstableContentProviderClient(c);
                bundleCall = contentProviderClientAcquireUnstableContentProviderClient.call("isSupport", null, null);
                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        contentProviderClientAcquireUnstableContentProviderClient.close();
                    } else {
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                    }
                }
            } else {
                bundleCall = this.a.getContentResolver().call(c, "isSupport", (String) null, (Bundle) null);
            }
            if (bundleCall.getInt("code", -1) == 0) {
                return bundleCall.getBoolean("issupport", true);
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final String b() {
        return a(null, "getOAID");
    }
}
