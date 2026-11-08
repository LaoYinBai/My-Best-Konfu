package com.bytedance.gameprotect;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.ss.sys.ces.out.StcSDKFactory;
import java.util.HashMap;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class GameProtect {
    private static final int OPTION_SET_PRIORITY = 1;
    public static final int PRIORITY_ABOVE_NORMAL = 4;
    public static final int PRIORITY_BELOW_NORMAL = 2;
    public static final int PRIORITY_HIGHEST = 5;
    public static final int PRIORITY_LOWEST = 1;
    public static final int PRIORITY_NORMAL = 3;
    private static g mDriverThread;

    public static synchronized void addExtraInfo(@NonNull Map<String, Object> map) {
        StcSDKFactory.getInstance().setCustomInfo(new HashMap(map));
    }

    public static synchronized void init(@NonNull UserConfig userConfig) {
        if (mDriverThread != null) {
            return;
        }
        Context applicationContext = userConfig.appContext.getApplicationContext();
        String str = userConfig.appId;
        j.e().a(applicationContext);
        j.e().g();
        StcSDKFactory.getSDK(applicationContext, Long.parseLong(str), 0);
        g gVar = new g(applicationContext, userConfig);
        mDriverThread = gVar;
        gVar.start();
    }

    public static void isEmulator(@NonNull IEmulatorCallback iEmulatorCallback) {
        h.a(iEmulatorCallback, 0L);
    }

    public static void isEmulator(@NonNull IEmulatorCallback iEmulatorCallback, long j) {
        h.a(iEmulatorCallback, j);
    }

    public static synchronized void setDeviceInfo(@NonNull String str, @NonNull String str2) {
        if (!str.isEmpty()) {
            StcSDKFactory.getInstance().setParams(str, str2);
            IlIlllIiI.a(str, str2);
            ContentVerifier.getInstance().setDeviceID(str);
        }
    }

    public static void setPriority(int i) {
        p.a(1, String.valueOf(i));
    }

    public static synchronized void setUserInfo(int i, @NonNull String str) {
        setUserInfo(i, str, -1L, "");
    }

    public static synchronized void setUserInfo(int i, @NonNull String str, long j, @NonNull String str2) {
        IlIlllIiI.a(i, str, j, str2);
    }
}
