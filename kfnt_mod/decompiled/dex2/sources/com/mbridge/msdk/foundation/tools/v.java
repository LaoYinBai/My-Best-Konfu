package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* compiled from: SameSDKTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class v extends f {
    public static boolean b = false;
    public static char[] c = {'a', 'n', 'd', 'r', 'o', 'i', 'd', 'c', 'o', 'n', 't', 'e', 'n', 't', 'p', 'm', 'g', 'e', 't', 'C', 'o', 'n', 't', 'e', 'x', 't'};

    /* compiled from: SameSDKTool.java */
    public static class a {
        private static Intent a(Context context) {
            return new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.package.name"));
        }

        private static List<ResolveInfo> b(Context context) {
            try {
                return context.getPackageManager().queryIntentActivities(a(context), 0);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static boolean a(String str) {
            return b(str) || c(str);
        }

        public static boolean b(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                return Uri.parse(str).getScheme().equals("market");
            } catch (Throwable th) {
                s.d("SDKUtil", Log.getStackTraceString(th));
                return false;
            }
        }

        private static boolean c(String str) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    Uri uri = Uri.parse(str);
                    if (!uri.getHost().equals("play.google.com")) {
                        if (!uri.getHost().equals("market.android.com")) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Throwable th) {
                s.d("SDKUtil", Log.getStackTraceString(th));
            }
            return false;
        }

        public static boolean a(Context context, String str, NativeListener.NativeTrackingListener nativeTrackingListener) {
            try {
                List<ResolveInfo> listB = b(context);
                if (listB != null && listB.size() > 0) {
                    if (!b(str)) {
                        if (c(str)) {
                            str = "market://" + str.substring(str.indexOf("details?id="));
                        } else {
                            str = null;
                        }
                    }
                    if (TextUtils.isEmpty(str)) {
                        return false;
                    }
                    Intent intentA = a(context);
                    intentA.setData(Uri.parse(str));
                    intentA.addFlags(268435456);
                    Iterator<ResolveInfo> it = listB.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (it.next().activityInfo.packageName.equals("com.android.vending")) {
                            intentA.setPackage("com.android.vending");
                            break;
                        }
                    }
                    context.startActivity(intentA);
                    v.a(nativeTrackingListener);
                    return true;
                }
                return false;
            } catch (Throwable th) {
                s.d("SDKUtil", Log.getStackTraceString(th));
                return false;
            }
        }
    }

    public static void a(NativeListener.NativeTrackingListener nativeTrackingListener) {
        if (nativeTrackingListener instanceof NativeListener.TrackingExListener) {
            ((NativeListener.TrackingExListener) nativeTrackingListener).onLeaveApp();
        }
    }

    public static void a(Context context, String str, NativeListener.NativeTrackingListener nativeTrackingListener) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            boolean z = listQueryIntentActivities.size() > 0;
            if (!str.startsWith("market://")) {
                if (str.startsWith("https://play.google.com/")) {
                    a(context, "market://details?id=" + str.replace("https://play.google.com/store/apps/details?id=", ""), nativeTrackingListener);
                    return;
                }
                return;
            }
            if (!z) {
                b(context, "https://play.google.com/store/apps/details?id=" + str.replace("market://details?id=", ""), nativeTrackingListener);
                return;
            }
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().activityInfo.packageName.equals("com.android.vending")) {
                        intent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
                        break;
                    }
                }
            }
            try {
                context.startActivity(intent);
                a(nativeTrackingListener);
            } catch (Exception unused) {
                b(context, "https://play.google.com/store/apps/details?id=" + str.replace("market://details?id=", ""), nativeTrackingListener);
            }
        } catch (Exception e) {
            s.d("SDKUtil", e.getMessage());
        }
    }

    public static void a(Context context, String str, CampaignEx campaignEx, NativeListener.NativeTrackingListener nativeTrackingListener) {
        if (context == null) {
            return;
        }
        if (b) {
            b(context, str, nativeTrackingListener);
            return;
        }
        try {
            Class.forName("com.mbridge.msdk.activity.MBCommonActivity");
            Intent intent = new Intent(context, Class.forName("com.mbridge.msdk.activity.MBCommonActivity"));
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (a.b(str)) {
                String strReplace = str.replace("market://details?id=", "");
                try {
                    str = "https://play.google.com/store/apps/details?id=" + strReplace;
                } catch (Exception unused) {
                    str = strReplace;
                    b(context, str, nativeTrackingListener);
                    return;
                }
            }
            intent.putExtra("url", str);
            s.b("url", "webview url = " + str);
            intent.setFlags(268435456);
            intent.putExtra("mvcommon", campaignEx);
            context.startActivity(intent);
        } catch (Exception unused2) {
        }
    }

    public static void b(Context context, String str, NativeListener.NativeTrackingListener nativeTrackingListener) {
        if (str == null || context == null) {
            return;
        }
        try {
            if (a.b(str)) {
                String strReplace = str.replace("market://details?id=", "");
                try {
                    str = "https://play.google.com/store/apps/details?id=" + strReplace;
                } catch (Exception e) {
                    e = e;
                    str = strReplace;
                    e.printStackTrace();
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        intent.addFlags(268468224);
                        context.startActivity(intent);
                        a(nativeTrackingListener);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent2.addFlags(268435456);
            ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent2, C.DEFAULT_BUFFER_SEGMENT_SIZE);
            if (resolveInfoResolveActivity != null) {
                intent2.setClassName(resolveInfoResolveActivity.activityInfo.packageName, resolveInfoResolveActivity.activityInfo.name);
            }
            context.startActivity(intent2);
            a(nativeTrackingListener);
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File fileA = com.mbridge.msdk.foundation.same.b.e.a(com.mbridge.msdk.foundation.same.b.c.MBRIDGE_700_IMG);
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else if (str.lastIndexOf("/") == -1) {
            str2 = str.hashCode() + "";
        } else {
            str2 = (str.hashCode() + str.substring(str.lastIndexOf("/") + 1).hashCode()) + "";
        }
        return new File(fileA, str2).getAbsolutePath();
    }
}
