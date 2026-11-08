package com.hw;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.push.utils.a;
import com.bytedance.push.utils.e;
import com.bytedance.push.utils.f;
import com.huawei.hms.utils.Util;
import java.util.Collections;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a {
    static boolean a(String str, Context context) throws PackageManager.NameNotFoundException {
        return a(context, str) & b(str, context);
    }

    private static boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return f.b(context, str, HWPushAdapter.TAG, Collections.singletonList(a.a.d("com.huawei.MessageService").a(context.getPackageName()).a(new a.b(Collections.singletonList("com.huawei.push.action.MESSAGING_EVENT"))).a()));
    }

    private static boolean b(String str, Context context) {
        if (!TextUtils.isEmpty(Util.getAppId(context))) {
            return true;
        }
        e.a(str, "HWPush. You need to declare metadata \"com.huawei.hms.client.appid \" in AndroidManifest.xml");
        return false;
    }
}
