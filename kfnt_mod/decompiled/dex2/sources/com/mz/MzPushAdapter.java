package com.mz;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.i;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.ss.android.message.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MzPushAdapter implements IPushAdapter {
    public static int MZ_PUSH = -1;

    public boolean isPushAvailable(Context context, int i) {
        return true;
    }

    public boolean requestNotificationPermission(int i) {
        return false;
    }

    public void trackPush(Context context, int i, Object obj) {
    }

    public static int getMzPush() {
        if (MZ_PUSH == -1) {
            MZ_PUSH = e.a(com.ss.android.message.a.a()).a(MzPushAdapter.class.getName());
        }
        return MZ_PUSH;
    }

    public void registerPush(Context context, int i) {
        if (context == null || i != getMzPush()) {
            String str = null;
            if (context == null) {
                str = "空 context";
            } else if (i != getMzPush()) {
                str = "通道注册错误";
            }
            i.o().a(i, 101, PushConstants.PUSH_TYPE_NOTIFY, str);
            return;
        }
        i.l().c("MzPush", "registerMzPush");
        Pair pairA = i.m().a(getMzPush());
        if (pairA == null) {
            i.o().a(i, 106, PushConstants.PUSH_TYPE_NOTIFY, "配置为空");
        } else {
            DebugLogger.switchDebug(i.l().a());
            PushManager.register(context, (String) pairA.first, (String) pairA.second);
        }
    }

    public void setAlias(Context context, String str, int i) {
        Pair pairA;
        if (context == null || i != getMzPush() || TextUtils.isEmpty(str)) {
            return;
        }
        i.l().c("MzPush", "set alias:" + str);
        String pushId = PushManager.getPushId(context);
        if (TextUtils.isEmpty(pushId) || (pairA = i.m().a(getMzPush())) == null) {
            return;
        }
        PushManager.subScribeAlias(context, (String) pairA.first, (String) pairA.second, pushId, str);
    }

    public void unregisterPush(Context context, int i) {
        if (context != null && i == getMzPush() && b.a()) {
            i.l().c("MzPush", "unregisterMzPush");
            Pair pairA = i.m().a(getMzPush());
            if (pairA != null) {
                PushManager.unRegister(context, (String) pairA.first, (String) pairA.second);
            }
        }
    }

    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        return a.a(str, context);
    }
}
