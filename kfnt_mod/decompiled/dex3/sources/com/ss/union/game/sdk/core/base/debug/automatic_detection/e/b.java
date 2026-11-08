package com.ss.union.game.sdk.core.base.debug.automatic_detection.e;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetectionParams;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements ILGDetectionParams {
    private List<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b> a;

    public static b a() {
        return a.a;
    }

    private static class a {
        static b a = new b();

        private a() {
        }
    }

    private b() {
        this.a = new ArrayList();
        b();
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetectionParams
    public List<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b> getAllParams() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetectionParams
    public void insertParams(String str, String str2) {
        if (c()) {
            com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar = new com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b();
            bVar.a = str;
            if (!this.a.contains(bVar)) {
                bVar.a(str2);
                this.a.add(bVar);
                return;
            }
            for (com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar2 : this.a) {
                if (bVar2.a.equals(str)) {
                    bVar2.a(str2);
                    return;
                }
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.in.ILGDetectionParams
    public com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b getParameterByKey(String str) {
        for (com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar : this.a) {
            if (bVar.a.equals(str)) {
                return bVar;
            }
        }
        return null;
    }

    private void b() {
        String str;
        if (c()) {
            insertParams("游戏名称", ConfigManager.AppConfig.appName());
            insertParams("游戏包名", GlobalApplicationUtils.getContext().getPackageName());
            insertParams("设备ID", AppLogManager.getInstance().getDid());
            insertParams("渠道", ConfigManager.AppConfig.appChannel());
            insertParams("归一化id", AppIdManager.secretAppId());
            insertParams("深度转化id", AppIdManager.apAppID());
            insertParams("深度转化URL Schema", ConfigManager.AppConfig.appLogSchema());
            insertParams("穿山甲appId", AppIdManager.adAppID());
            if (ConfigManager.LoginConfig.isSilentLogin()) {
                str = "有账号-静默";
            } else if (ConfigManager.LoginConfig.isDialogLogin()) {
                str = "有账号-弹窗";
            } else {
                str = ConfigManager.LoginConfig.isNoUserLogin() ? "无账号" : "无账号";
            }
            insertParams("当前登录类型", str);
            if (ConfigManager.LoginConfig.isAllowGuestLoginSwitch()) {
                insertParams("cp是否允许游客登录", "是");
            } else {
                insertParams("cp是否允许游客登录", "否");
            }
            if (TextUtils.isEmpty(ConfigManager.LoginConfig.oneKeyCMAppID())) {
                return;
            }
            insertParams("移动appId", ConfigManager.LoginConfig.oneKeyCMAppID());
            insertParams("移动appKey", ConfigManager.LoginConfig.getOneKeyCMAppKey());
            insertParams("联通appKey", ConfigManager.LoginConfig.oneKeyCUAppID());
            insertParams("联通appSecret", ConfigManager.LoginConfig.getOneKeyCUAppKey());
            insertParams("电信appKey", ConfigManager.LoginConfig.oneKeyCTAppID());
            insertParams("电信appSecret", ConfigManager.LoginConfig.getOneKeyCTAppKey());
        }
    }

    private boolean c() {
        return ConfigManager.AppConfig.isDebug();
    }
}
