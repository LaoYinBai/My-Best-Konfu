package com.bytedance.gameprotect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static Context f3875a;

    public static Map<String, String> a(boolean z) {
        HashMap map = new HashMap();
        PackageManager packageManager = f3875a.getPackageManager();
        if (packageManager == null) {
            return map;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (!z || !a(activityInfo)) {
                ApplicationInfo applicationInfo = activityInfo.applicationInfo;
                map.put(applicationInfo.packageName, applicationInfo.sourceDir);
            }
        }
        return map;
    }

    public static void a(Context context) {
        f3875a = context.getApplicationContext();
    }

    private static boolean a(ActivityInfo activityInfo) {
        int i = activityInfo.applicationInfo.flags;
        return ((i & 1) == 0 && (i & 128) == 0) ? false : true;
    }
}
