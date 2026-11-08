package com.ss.android.newmedia.redbadge.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static boolean a(Context context, Intent intent) {
        PackageManager packageManager;
        List<ResolveInfo> listQueryBroadcastReceivers;
        return (context == null || intent == null || (packageManager = context.getPackageManager()) == null || (listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0)) == null || listQueryBroadcastReceivers.size() <= 0) ? false : true;
    }
}
