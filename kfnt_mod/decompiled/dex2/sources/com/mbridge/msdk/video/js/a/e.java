package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.video.module.MBridgeVideoView;

/* compiled from: DefaultJSNotifyProxy.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements com.mbridge.msdk.video.js.f {
    @Override // com.mbridge.msdk.video.js.f
    public void a(int i) {
        s.a("DefaultJSNotifyProxy", "onVideoStatusNotify:" + i);
    }

    @Override // com.mbridge.msdk.video.js.f
    public void a(int i, String str) {
        s.a("DefaultJSNotifyProxy", "onClick:" + i + ",pt:" + str);
    }

    @Override // com.mbridge.msdk.video.js.f
    public void a(MBridgeVideoView.a aVar) {
        s.a("DefaultJSNotifyProxy", "onProgressNotify:" + aVar.toString());
    }

    @Override // com.mbridge.msdk.video.js.f
    public void a(Object obj) {
        s.a("DefaultJSNotifyProxy", "onWebviewShow:" + obj);
    }

    @Override // com.mbridge.msdk.video.js.f
    public void a(int i, int i2, int i3, int i4) {
        s.a("DefaultJSNotifyProxy", "showDataInfo");
    }
}
