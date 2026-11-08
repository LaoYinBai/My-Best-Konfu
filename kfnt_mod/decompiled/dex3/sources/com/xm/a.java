package com.xm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import com.umeng.message.MsgConstant;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    static boolean a(String str, Context context) throws PackageManager.NameNotFoundException {
        return b(context, str) & a(str) & a(context, str);
    }

    private static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.a(context, str, "MiPush", Arrays.asList(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_GET_TASKS, "android.permission.VIBRATE", context.getPackageName() + ".permission.MIPUSH_RECEIVE"));
    }

    private static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        boolean zB = f.b(context, str, "MiPush 错误", Arrays.asList(a.a.d("com.xiaomi.push.service.XMPushService").a(context.getPackageName() + ":pushservice").a(), a.a.d("com.xiaomi.push.service.XMJobService").a(context.getPackageName() + ":pushservice").b("android.permission.BIND_JOB_SERVICE").a(), a.a.d("com.xiaomi.mipush.sdk.PushMessageHandler").a(context.getPackageName()).a(), a.a.d("com.xiaomi.mipush.sdk.MessageHandleService").a(context.getPackageName()).a()));
        a.a aVarD = a.a.d("com.xiaomi.push.service.receivers.PingReceiver");
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append(":pushservice");
        return f.c(context, str, "MiPush 错误", Arrays.asList(a.a.d("com.xiaomi.push.service.receivers.NetworkStatusReceiver").a(context.getPackageName()).a(new a.b(Arrays.asList("android.net.conn.CONNECTIVITY_CHANGE"), Arrays.asList("android.intent.category.DEFAULT"))).a(), aVarD.a(sb.toString()).a(new a.b(Arrays.asList("com.xiaomi.push.PING_TIMER"))).a(), a.a.d("com.xiaomi.push.service.receivers.MIPushMessageHandler").a(context.getPackageName()).a(new a.b(Arrays.asList("com.xiaomi.mipush.RECEIVE_MESSAGE"))).a(new a.b(Arrays.asList("com.xiaomi.mipush.MESSAGE_ARRIVED"))).a(new a.b(Arrays.asList("com.xiaomi.mipush.ERROR"))).a())) && zB;
    }

    private static boolean a(String str) {
        Pair pairA = i.m().a(MiPushAdapter.getMiPush());
        if (pairA != null && !TextUtils.isEmpty((CharSequence) pairA.first) && !TextUtils.isEmpty((CharSequence) pairA.second)) {
            return true;
        }
        i.l().b(str, "MiPush key 配置错误，缺少 key 配置");
        return false;
    }
}
