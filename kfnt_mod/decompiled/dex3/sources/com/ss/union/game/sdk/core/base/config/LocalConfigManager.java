package com.ss.union.game.sdk.core.base.config;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.LGExecutor;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LocalConfigManager {
    public static boolean canTriggerCrash;

    public static void readLocalConfig() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || context.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) != 0) {
            return;
        }
        LGExecutor.CACHED_EXECUTOR.execute(new Runnable() { // from class: com.ss.union.game.sdk.core.base.config.LocalConfigManager.1
            @Override // java.lang.Runnable
            public void run() {
                LocalConfigManager.startReadDebugSwitch();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startReadDebugSwitch() {
        if (ConfigManager.AppConfig.isDebug()) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(), "ohayooSdkDebugSwitch");
                if (file.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    final String str = new String(bArr);
                    fileInputStream.close();
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.base.config.LocalConfigManager.2
                        @Override // java.lang.Runnable
                        public void run() {
                            LocalConfigManager.parseConfigJson(str);
                        }
                    });
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parseConfigJson(String str) {
        try {
            canTriggerCrash = new JSONObject(str).optBoolean("switch_trigger_crash", canTriggerCrash);
        } catch (Throwable unused) {
        }
    }
}
