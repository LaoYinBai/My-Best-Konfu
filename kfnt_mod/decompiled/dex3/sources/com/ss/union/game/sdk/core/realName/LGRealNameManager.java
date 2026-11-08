package com.ss.union.game.sdk.core.realName;

import android.app.Activity;
import com.ss.union.game.sdk.core.antiAddiction.callback.LGAntiAddictionGlobalCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGRealNameManager {
    void checkDeviceRealName(LGRealNameCallback lGRealNameCallback);

    void realNameAuth(Activity activity);

    void setAntiAddictionGlobalCallback(LGAntiAddictionGlobalCallback lGAntiAddictionGlobalCallback);

    void setGlobalRealNameAuthCallback(LGRealNameCallback lGRealNameCallback);
}
