package com.mbridge.msdk.video.module.a.a;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.video.js.factory.IJSFactory;

/* compiled from: ContainerViewJSListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b extends c {
    private IJSFactory j;

    public b(IJSFactory iJSFactory, CampaignEx campaignEx, com.mbridge.msdk.videocommon.b.d dVar, com.mbridge.msdk.videocommon.download.a aVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, int i, boolean z) {
        super(campaignEx, aVar, dVar, str, str2, aVar2, i, z);
        this.j = iJSFactory;
        if (iJSFactory == null) {
            this.a = false;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x001e. Please report as an issue. */
    @Override // com.mbridge.msdk.video.module.a.a.c, com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public final void a(int i, Object obj) {
        if (this.a) {
            if (i != 8) {
                if (i != 103) {
                    if (i == 105) {
                        String string = obj.toString();
                        s.d("=======", "pt:" + string);
                        this.j.getJSNotifyProxy().a(3, string);
                        i = -1;
                    } else if (i == 107) {
                        this.j.getJSContainerModule().showVideoClickView(-1);
                        this.j.getJSVideoModule().setCover(false);
                        this.j.getJSVideoModule().setMiniEndCardState(false);
                        this.j.getJSVideoModule().videoOperate(1);
                    } else if (i == 112) {
                        this.j.getJSVideoModule().setCover(true);
                        this.j.getJSVideoModule().setMiniEndCardState(true);
                        this.j.getJSVideoModule().videoOperate(2);
                    } else if (i != 115) {
                        switch (i) {
                        }
                    } else {
                        com.mbridge.msdk.video.js.i jSVideoModule = this.j.getJSVideoModule();
                        this.j.getJSContainerModule().resizeMiniCard(jSVideoModule.getBorderViewWidth(), jSVideoModule.getBorderViewHeight(), jSVideoModule.getBorderViewRadius());
                    }
                }
            } else if (!this.j.getJSContainerModule().showAlertWebView()) {
                this.j.getJSVideoModule().showAlertView();
            } else {
                this.j.getJSVideoModule().alertWebViewShowed();
            }
        }
        super.a(i, obj);
    }
}
