package com.vv;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.bytedance.push.utils.h;
import com.sigmob.sdk.common.Constants;
import com.ss.android.message.a;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.util.VivoPushException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VvPushAdapter implements IPushAdapter {
    private static final String TAG = "VivoPush";
    private static int VV_PUSH = -1;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public boolean requestNotificationPermission(int i) {
        return false;
    }

    public void trackPush(Context context, int i, Object obj) {
    }

    public static int getVvPush() {
        if (VV_PUSH == -1) {
            VV_PUSH = e.a(a.a()).a(VvPushAdapter.class.getName());
        }
        return VV_PUSH;
    }

    public boolean isPushAvailable(Context context, int i) {
        return Build.VERSION.SDK_INT >= 23 && h.a() && PushClient.getInstance(context.getApplicationContext()).isSupport();
    }

    public void registerPush(final Context context, final int i) {
        try {
            i.l().c(TAG, "registerVivoPush");
            if (i.l().a()) {
                PushClient.getInstance(context).checkManifest();
            }
            PushClient.getInstance(context.getApplicationContext()).initialize();
            PushClient.getInstance(context).turnOnPush(new IPushActionListener() { // from class: com.vv.VvPushAdapter.1
                @Override // com.vivo.push.IPushActionListener
                public void onStateChanged(int i2) {
                    if (i2 != 0) {
                        i.o().a(i, 104, String.valueOf(i2), "vivo 通道注册失败");
                        i.l().b(VvPushAdapter.TAG, "打开push异常[" + i2 + "] ，请查看解决方案> https://dev.vivo.com.cn/documentCenter/doc/226");
                    } else {
                        i.l().c(VvPushAdapter.TAG, "打开push成功");
                    }
                    final String regId = PushClient.getInstance(context).getRegId();
                    i.l().a(VvPushAdapter.TAG, "token = " + regId);
                    if (TextUtils.isEmpty(regId)) {
                        return;
                    }
                    VvPushAdapter.this.mHandler.post(new Runnable() { // from class: com.vv.VvPushAdapter.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            VvPushAdapter.sendToken(context, regId);
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAlias(Context context, String str, int i) {
        try {
            i.l().c(TAG, "setAlias alias = " + str);
            PushClient.getInstance(context).bindAlias(str, new IPushActionListener() { // from class: com.vv.VvPushAdapter.2
                @Override // com.vivo.push.IPushActionListener
                public void onStateChanged(int i2) {
                    com.bytedance.common.push.utility.a.b(VvPushAdapter.TAG, "onStateChanged() called with: i = [" + i2 + "]");
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void unregisterPush(Context context, int i) {
        try {
            PushClient.getInstance(context).turnOffPush(new IPushActionListener() { // from class: com.vv.VvPushAdapter.3
                @Override // com.vivo.push.IPushActionListener
                public void onStateChanged(int i2) {
                    com.bytedance.common.push.utility.a.b(VvPushAdapter.TAG, "onStateChanged() called with: i = [" + i2 + "]");
                }
            });
            i.l().c(TAG, "unregisterPush");
        } catch (Throwable unused) {
        }
    }

    public static void sendToken(Context context, String str) {
        if (context == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            i.m().a(context, getVvPush(), str);
        } else if (i.m().a(context)) {
            i.o().a(getVvPush(), 102, Constants.FAIL, "token is empty");
        }
    }

    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        try {
            PushClient.getInstance(context).checkManifest();
            return true;
        } catch (VivoPushException e) {
            com.bytedance.common.push.utility.a.e(str, "VivoPush Errcode = " + e.getCode() + " " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
