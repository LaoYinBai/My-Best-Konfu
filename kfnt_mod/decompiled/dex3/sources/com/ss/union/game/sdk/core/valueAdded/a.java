package com.ss.union.game.sdk.core.valueAdded;

import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.b.b;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.browser.BrowserFragment;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.ss.union.game.sdk.core.valueAdded.callback.IDeeplinkInnerCallback;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements IValueAddedService {
    private IDeeplinkInnerCallback a;

    private a() {
    }

    public static a a() {
        return C0092a.a;
    }

    /* renamed from: com.ss.union.game.sdk.core.valueAdded.a$a, reason: collision with other inner class name */
    private static class C0092a {
        private static final a a = new a();

        private C0092a() {
        }
    }

    @Override // com.ss.union.game.sdk.core.valueAdded.IValueAddedService
    public void startBrowser(String str) {
        if (!LGSDKCore.isSdkInitSuccess()) {
            LogDevKitUtils.logBrowser("SDK has not yet been initialized");
            return;
        }
        LogDevKitUtils.logBrowser("startBrowser: " + str);
        BrowserFragment.show("https://u.ohayoo.cn/uapp/front/validate?redirect_url=" + URLEncoder.encode(str));
    }

    @Override // com.ss.union.game.sdk.core.valueAdded.IValueAddedService
    public void registerDeeplinkInnerCallback(IDeeplinkInnerCallback iDeeplinkInnerCallback) {
        this.a = iDeeplinkInnerCallback;
    }

    public void a(String str) {
        LogDevKitUtils.logBrowser("publicDeeplinkFromInner: " + str);
        if (this.a != null) {
            this.a.deeplinkFromInner(str);
        }
    }

    @Override // com.ss.union.game.sdk.core.valueAdded.IValueAddedService
    public int verifySensitiveWords(String str) {
        if (!LGSDKCore.isSdkInitSuccess()) {
            LogDevKitUtils.logSensitiveWord("SDK has not yet been initialized");
            return -1;
        }
        int iB = b.b(str);
        HashMap map = new HashMap();
        map.put("blackword_result", iB == 1 ? "blackword_pass" : "blackword_fail");
        PageStater.onEvent("ohayoo_sdk_blackword", map);
        LogDevKitUtils.logSensitiveWord("verifySensitiveWords: " + str + " result = " + iB);
        return iB;
    }
}
