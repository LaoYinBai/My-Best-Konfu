package com.ss.union.game.sdk.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.gameprotect.ContentVerifier;
import com.bytedance.gameprotect.GameProtect;
import com.bytedance.gameprotect.UserConfig;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String a = "Core_GameProtect";
    private static boolean b;

    public static void a(String str, boolean z, boolean z2) {
        try {
            Context context = GlobalApplicationUtils.getContext();
            if (context == null) {
                LogUtils.log(a, "initGpSDk context is null");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                LogUtils.log(a, "initGpSDk ap_appID is null");
            } else {
                if (b) {
                    return;
                }
                GameProtect.init(new UserConfig.Builder(context, str).setRegion(1).setSandbox(z).setAccountless(z2).build());
                ContentVerifier.getInstance().init();
                b = true;
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2) {
        if (!b || TextUtils.isEmpty(str)) {
            return;
        }
        GameProtect.setDeviceInfo(str, str2);
    }

    public static void a(String str) {
        if (b) {
            GameProtect.setUserInfo(0, str);
        }
    }

    public static int b(String str) {
        if (b) {
            return ContentVerifier.getInstance().verifyChatContent(str);
        }
        return -1;
    }
}
