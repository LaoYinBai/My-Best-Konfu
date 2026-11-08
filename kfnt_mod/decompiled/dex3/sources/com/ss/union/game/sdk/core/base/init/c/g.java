package com.ss.union.game.sdk.core.base.init.c;

import com.bytedance.applog.AppLog;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestAppLogCallback;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g extends FlowItem {
    public static String a = "CoreInit";

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        if (com.ss.union.game.sdk.core.base.component.c.a(new LGSdkInitCallback() { // from class: com.ss.union.game.sdk.core.base.init.c.g.1
            @Override // com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback
            public void onInitSuccess(String str, String str2, String str3, String str4) {
                com.ss.union.game.sdk.core.base.init.provider.a.a(str, str2, str3, str4);
            }

            @Override // com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback
            public void onInitFailed(int i, String str) {
                com.ss.union.game.sdk.core.base.init.provider.a.a(i, str);
            }
        }, new LGRequestAppLogCallback() { // from class: com.ss.union.game.sdk.core.base.init.c.g.2
            @Override // com.ss.union.game.sdk.core.base.init.callback.LGRequestAppLogCallback
            public void onCall() {
                g.this.finish();
            }
        })) {
            return;
        }
        AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.base.init.c.g.3
            @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
            public void onIdChanged(String str, String str2) {
                com.ss.union.game.sdk.core.base.init.provider.a.a(AppLog.getDid(), AppLog.getIid(), AppLog.getSsid(), AppLog.getUserUniqueID());
            }
        });
        finish();
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return a;
    }
}
