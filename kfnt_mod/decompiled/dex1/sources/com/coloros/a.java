package com.coloros;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a {
    static boolean a(String str, Context context) throws PackageManager.NameNotFoundException {
        return a(context, str) & a(str) & b(context, str);
    }

    private static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        ArrayList arrayList = new ArrayList();
        com.bytedance.push.utils.a aVarA = a.C0149a.d("com.heytap.mcssdk.PushService").a(context.getPackageName()).b("com.coloros.mcs.permission.SEND_MCS_MESSAGE").a(new a.b(Collections.singletonList("com.coloros.mcs.action.RECEIVE_MCS_MESSAGE"))).a();
        com.bytedance.push.utils.a aVarA2 = a.C0149a.d("com.heytap.mcssdk.AppPushService").a(context.getPackageName()).b("com.heytap.mcs.permission.SEND_MCS_MESSAGE").a(new a.b(Collections.singletonList("com.heytap.mcs.action.RECEIVE_MCS_MESSAGE"))).a();
        arrayList.add(aVarA);
        arrayList.add(aVarA2);
        return f.b(context, str, "OPPOPush", arrayList);
    }

    private static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.a(context, str, "OPPOPush 错误,", (List<String>) Arrays.asList("com.coloros.mcs.permission.RECIEVE_MCS_MESSAGE", "com.heytap.mcs.permission.RECIEVE_MCS_MESSAGE"));
    }

    private static boolean a(String str) {
        Pair<String, String> pairA = i.m().a(OpPushAdapter.getOpPush());
        if (pairA != null && !TextUtils.isEmpty((CharSequence) pairA.first) && !TextUtils.isEmpty((CharSequence) pairA.second)) {
            return true;
        }
        i.l().b(str, "OPPOPush错误，OPPO Key 配置有误");
        return false;
    }
}
