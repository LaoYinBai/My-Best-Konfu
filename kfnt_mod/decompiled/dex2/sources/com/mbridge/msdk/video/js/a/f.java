package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultJSRewardVideoV1.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements com.mbridge.msdk.video.js.h {
    @Override // com.mbridge.msdk.video.js.h
    public String a() {
        s.a("DefaultJSRewardVideoV1", "getEndScreenInfo");
        return "{}";
    }

    @Override // com.mbridge.msdk.video.js.g
    public void notifyCloseBtn(int i) {
        s.a("DefaultJSRewardVideoV1", "notifyCloseBtn,state=" + i);
    }

    @Override // com.mbridge.msdk.video.js.g
    public void toggleCloseBtn(int i) {
        s.a("DefaultJSRewardVideoV1", "toggleCloseBtn,state=" + i);
    }

    @Override // com.mbridge.msdk.video.js.h
    public void a(String str) {
        s.a("DefaultJSRewardVideoV1", "triggerCloseBtn,state=" + str);
    }

    @Override // com.mbridge.msdk.video.js.h
    public void b(String str) {
        s.a("DefaultJSRewardVideoV1", "setOrientation,landscape=" + str);
    }

    @Override // com.mbridge.msdk.video.js.h
    public void c(String str) {
        s.a("DefaultJSRewardVideoV1", "handlerPlayableException，msg=" + str);
    }
}
