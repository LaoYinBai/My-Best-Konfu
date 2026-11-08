package com.bytedance.push.a;

import android.content.Context;
import android.content.pm.PackageManager;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import com.ss.android.message.NotifyService;
import com.ss.android.message.log.LogService;
import com.ss.android.push.DefaultReceiver;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return b(context, str) & d(context, str) & c(context, str);
    }

    private static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.e(context, str, "Push", Arrays.asList(a.C0149a.d(PushMultiProcessSharedProvider.class.getName()).a(context.getPackageName()).c(context.getPackageName() + ".push.SHARE_PROVIDER_AUTHORITY").a()));
    }

    private static boolean c(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.c(context, str, "Push", (List<com.bytedance.push.utils.a>) Arrays.asList(a.C0149a.d(DefaultReceiver.class.getName()).a(context.getPackageName()).a()));
    }

    private static boolean d(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.b(context, str, "Push", (List<com.bytedance.push.utils.a>) Arrays.asList(a.C0149a.d(NotifyService.class.getName()).a(context.getPackageName() + ":push").a(new a.b(Arrays.asList("com.ss.android.message.action.PUSH_SERVICE"))).a(), a.C0149a.d(LogService.class.getName()).a(context.getPackageName() + ":push").a()));
    }
}
