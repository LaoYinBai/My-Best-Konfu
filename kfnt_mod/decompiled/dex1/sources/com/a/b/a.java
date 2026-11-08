package com.a.b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import com.ss.android.message.MessageReceiver;
import com.ss.android.message.NotifyIntentService;
import com.ss.android.message.PushJobService;
import com.ss.android.message.sswo.SswoActivity;
import com.ss.android.message.sswo.SswoReceiver;
import com.ss.android.push.daemon.PushReceiver;
import com.ss.android.push.daemon.PushService;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return b(context, str) & d(context, str) & c(context, str);
    }

    private static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.d(context, str, "Push", Arrays.asList(a.C0149a.d(SswoActivity.class.getName()).a(context.getPackageName() + ":push").a()));
    }

    private static boolean c(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.c(context, str, "Push", (List<com.bytedance.push.utils.a>) Arrays.asList(a.C0149a.d(MessageReceiver.class.getName()).a(context.getPackageName() + ":push").a(new a.b(Arrays.asList("android.intent.action.BOOT_COMPLETED", "android.net.conn.CONNECTIVITY_CHANGE", "android.intent.action.MEDIA_MOUNTED", "android.intent.action.MEDIA_UNMOUNTED", "android.intent.action.USER_PRESENT", "android.intent.action.SCREEN_OFF", "android.intent.action.SCREEN_ON", "android.intent.action.ACTION_POWER_CONNECTED", "android.intent.action.ACTION_POWER_DISCONNECTED"))).a(new a.b(Arrays.asList("android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REMOVED"), null, Uri.parse("package://" + context.getPackageName()))).a(), a.C0149a.d(PushReceiver.class.getName()).a(context.getPackageName() + ":pushservice").a(), a.C0149a.d(SswoReceiver.class.getName()).a(context.getPackageName() + ":push").a(new a.b(Arrays.asList("android.intent.action.USER_PRESENT", "android.intent.action.SCREEN_OFF", "android.intent.action.SCREEN_ON"))).a()));
    }

    private static boolean d(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.b(context, str, "Push", (List<com.bytedance.push.utils.a>) Arrays.asList(a.C0149a.d(NotifyIntentService.class.getName()).a(context.getPackageName() + ":push").a(), a.C0149a.d(PushJobService.class.getName()).a(context.getPackageName() + ":push").b("android.permission.BIND_JOB_SERVICE").a(), a.C0149a.d(PushService.class.getName()).a(context.getPackageName() + ":pushservice").a()));
    }
}
