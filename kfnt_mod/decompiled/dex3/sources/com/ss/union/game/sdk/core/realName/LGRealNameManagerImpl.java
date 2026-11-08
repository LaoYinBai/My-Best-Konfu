package com.ss.union.game.sdk.core.realName;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.antiAddiction.bean.LGAntiAddictionGlobalResult;
import com.ss.union.game.sdk.core.antiAddiction.callback.LGAntiAddictionGlobalCallback;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;
import com.ss.union.game.sdk.core.realName.b.a;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.d.a;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.core.router.account.RouterAccountManager;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGRealNameManagerImpl implements LGRealNameManager {
    private static volatile LGRealNameManagerImpl a;
    private LGAntiAddictionGlobalCallback b;
    private LGRealNameCallback c;

    private LGRealNameManagerImpl() {
    }

    public static LGRealNameManagerImpl getInstance() {
        if (a == null) {
            synchronized (LGRealNameManagerImpl.class) {
                if (a == null) {
                    a = new LGRealNameManagerImpl();
                }
            }
        }
        return a;
    }

    @Override // com.ss.union.game.sdk.core.realName.LGRealNameManager
    public void checkDeviceRealName(@NonNull final LGRealNameCallback lGRealNameCallback) {
        LogDevKitUtils.logRealName("start check Device RealName");
        if (LGSDKCore.isSdkInitSuccess()) {
            a.a(new LGRealNameCallback() { // from class: com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl.1
                @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                    LogDevKitUtils.logRealName("check Device RealName success isRealNameValid=" + z + " isAdult= " + z2);
                    lGRealNameCallback.onSuccess(z, z2, lGRealNameRewardInfo);
                }

                @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
                public void onFail(int i, String str) {
                    LogDevKitUtils.logRealName("check Device RealName fail code=" + i + " msg= " + str);
                    lGRealNameCallback.onFail(i, str);
                }
            });
        } else if (lGRealNameCallback != null) {
            lGRealNameCallback.onFail(-204, "SDK还未初始化完成");
        }
    }

    @Override // com.ss.union.game.sdk.core.realName.LGRealNameManager
    public void realNameAuth(Activity activity) {
        LogDevKitUtils.logRealName("start RealName from outer");
        if (!LGSDKCore.isSdkInitSuccess()) {
            globalRealNameFail(-204, "SDK还未初始化完成");
            return;
        }
        if (ConfigManager.LoginConfig.isNoUserLogin()) {
            if (a.C0087a.a()) {
                globalRealNameFail(-5001, com.ss.union.game.sdk.core.realName.c.a.d);
                return;
            } else {
                RealNameManager.showRealNameWindow(108, null);
                return;
            }
        }
        if (!LGAccountDataUtil.isLogined()) {
            globalRealNameFail(-4001, com.ss.union.game.sdk.core.realName.c.a.b);
            return;
        }
        if (LGAccountDataUtil.isIdentifyValidated()) {
            globalRealNameFail(-5001, com.ss.union.game.sdk.core.realName.c.a.d);
        } else if (RouterAccountManager.getAccountRouter().isShowLoginRealNameSelectWindow(108)) {
            RouterAccountManager.getAccountRouter().invokeLoginRealNameSelect(108, new StandardDialogListenerAdapter() { // from class: com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl.2
                @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
                public boolean onWeaknessBtnClick(BaseFragment baseFragment) {
                    baseFragment.navigation(RealNameManager.createRealNameFragment(108, true, null));
                    return true;
                }
            });
        } else {
            RealNameManager.showRealNameWindow(108, null);
        }
    }

    @Override // com.ss.union.game.sdk.core.realName.LGRealNameManager
    public void setAntiAddictionGlobalCallback(LGAntiAddictionGlobalCallback lGAntiAddictionGlobalCallback) {
        this.b = lGAntiAddictionGlobalCallback;
    }

    public void globalAntiAddiction(@NonNull LGAntiAddictionGlobalResult lGAntiAddictionGlobalResult) {
        if (this.b == null || lGAntiAddictionGlobalResult == null) {
            return;
        }
        this.b.onTriggerAntiAddiction(lGAntiAddictionGlobalResult);
    }

    @Override // com.ss.union.game.sdk.core.realName.LGRealNameManager
    public void setGlobalRealNameAuthCallback(LGRealNameCallback lGRealNameCallback) {
        this.c = lGRealNameCallback;
    }

    public void globalRealNameFail(int i, String str) {
        LogDevKitUtils.logRealName("RealName result error: code=" + i + " msg=" + str);
        if (this.c == null) {
            return;
        }
        this.c.onFail(i, str);
    }

    public void globalRealNameSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
        LogDevKitUtils.logRealName("RealName result success: isRealNameValid=" + z + " isAdult=" + z2);
        if (this.c == null) {
            return;
        }
        this.c.onSuccess(z, z2, lGRealNameRewardInfo);
    }
}
