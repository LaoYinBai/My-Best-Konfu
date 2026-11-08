package com.hw;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.push.utility.c;
import com.bytedance.push.i;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.huawei.b;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.utils.Util;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HWPushAdapter implements IPushAdapter {
    private static int HW_PUSH = -1;
    public static final String TAG = "HWPush";

    public boolean requestNotificationPermission(int i) {
        return false;
    }

    public void trackPush(Context context, int i, Object obj) {
    }

    public static int getHwPush() {
        if (HW_PUSH == -1) {
            HW_PUSH = e.a(com.ss.android.message.a.a()).a(HWPushAdapter.class.getName());
        }
        return HW_PUSH;
    }

    public boolean isPushAvailable(Context context, int i) {
        return b.a(context);
    }

    public void registerPush(Context context, int i) {
        if (context == null || i != getHwPush()) {
            i.o().a(i, 101, PushConstants.PUSH_TYPE_NOTIFY, context == null ? "空 context" : "通道注册错误");
            return;
        }
        if (i.l().a()) {
            i.l().a(TAG, "registerHWPush");
        }
        com.bytedance.common.push.utility.b.b.a(new com.huawei.a(context));
    }

    public void setAlias(Context context, String str, int i) {
        if (context != null && i == getHwPush() && i.l().a()) {
            i.l().a(TAG, "setAlias");
        }
    }

    public void unregisterPush(Context context, int i) {
        if (i.l().a()) {
            i.l().a(TAG, "unregisterPush");
        }
        if (c.a()) {
            try {
                if (TextUtils.isEmpty(i.m().a(context, i))) {
                    return;
                }
                HmsInstanceId.getInstance(context).deleteToken(Util.getAppId(context), "HCM");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        return a.a(str, context);
    }
}
