package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestAppLogCallback;
import com.ss.union.game.sdk.core.realName.b.a;
import com.ss.union.game.sdk.core.vapp.VUserInfoManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e extends FlowItem {
    LGRequestAppLogCallback a;

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AppLogInit";
    }

    public e(LGRequestAppLogCallback lGRequestAppLogCallback) {
        this.a = lGRequestAppLogCallback;
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        this.a.onCall();
        AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.init.b.e.1
            @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
            public void onIdChanged(String str, String str2) {
                e.this.a(str, str2);
                e.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        a();
        VUserInfoManager.refreshDeviceVUserInfo();
    }

    private void a() {
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal != null) {
            com.ss.union.game.sdk.core.realName.a.a.a(currentUserLocal);
        } else {
            com.ss.union.game.sdk.core.realName.a.a.a(a.C0087a.b());
        }
    }
}
