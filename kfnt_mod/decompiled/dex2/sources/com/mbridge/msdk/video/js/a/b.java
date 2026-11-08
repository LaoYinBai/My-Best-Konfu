package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultJSBTModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.mbridge.msdk.video.js.b {
    private static final String a = "b";

    @Override // com.mbridge.msdk.video.js.d
    public void click(int i, String str) {
        s.a(a, CampaignEx.JSON_NATIVE_VIDEO_CLICK);
    }

    @Override // com.mbridge.msdk.video.js.d
    public void handlerH5Exception(int i, String str) {
        s.a(a, "handlerH5Exception");
    }

    @Override // com.mbridge.msdk.video.js.b
    public void reactDeveloper(Object obj, String str) {
        s.a(a, "reactDeveloper");
    }
}
