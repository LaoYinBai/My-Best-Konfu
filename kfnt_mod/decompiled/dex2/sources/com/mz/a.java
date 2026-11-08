package com.mz;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a {
    static boolean a(String str, Context context) throws PackageManager.NameNotFoundException {
        return b(context, str) & a(str) & a(context, str);
    }

    private static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.a(context, str, "MZPush", Arrays.asList("com.meizu.flyme.push.permission.RECEIVE", context.getPackageName() + ".push.permission.MESSAGE", "com.meizu.c2dm.permission.RECEIVE", context.getPackageName() + ".permission.C2D_MESSAGE"));
    }

    private static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.c(context, str, "MZPushPush 错误", Arrays.asList(a.a.d("com.meizu.cloud.pushsdk.SystemReceiver").a(context.getPackageName()).a(new a.b(Arrays.asList(PushConstants.MZ_SYSTEM_RECEIVER_ACTION), Arrays.asList("android.intent.category.DEFAULT"))).a(), a.a.d("com.meizu.message.MzMessageReceiver").a(context.getPackageName()).a(new a.b(Arrays.asList(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, PushConstants.MZ_PUSH_ON_REGISTER_ACTION, PushConstants.MZ_PUSH_ON_UNREGISTER_ACTION, PushConstants.REGISTRATION_CALLBACK_INTENT, PushConstants.C2DM_INTENT), Arrays.asList(context.getPackageName()))).a())) && f.b(context, str, "MZPushPush 错误", Arrays.asList(a.a.d("com.meizu.cloud.pushsdk.NotificationService").a(context.getPackageName()).a()));
    }

    private static boolean a(String str) {
        Pair pairA = i.m().a(MzPushAdapter.getMzPush());
        if (pairA != null && !TextUtils.isEmpty((CharSequence) pairA.first) && !TextUtils.isEmpty((CharSequence) pairA.second)) {
            return true;
        }
        i.l().b(str, "MZPush key 配置错误，缺少 key 配置");
        return false;
    }
}
