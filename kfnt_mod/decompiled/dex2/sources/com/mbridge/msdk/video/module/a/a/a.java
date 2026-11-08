package com.mbridge.msdk.video.module.a.a;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.video.module.MBridgeContainerView;
import com.mbridge.msdk.video.module.MBridgeVideoView;
import java.lang.reflect.InvocationTargetException;

/* compiled from: ContainerViewDefaultListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends c {
    private MBridgeVideoView j;
    private MBridgeContainerView k;

    public a(MBridgeVideoView mBridgeVideoView, MBridgeContainerView mBridgeContainerView, CampaignEx campaignEx, com.mbridge.msdk.videocommon.b.d dVar, com.mbridge.msdk.videocommon.download.a aVar, String str, String str2, com.mbridge.msdk.video.module.a.a aVar2, int i, boolean z) {
        super(campaignEx, aVar, dVar, str, str2, aVar2, i, z);
        this.j = mBridgeVideoView;
        this.k = mBridgeContainerView;
        if (mBridgeVideoView == null || mBridgeContainerView == null) {
            this.a = false;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x001d. Please report as an issue. */
    @Override // com.mbridge.msdk.video.module.a.a.c, com.mbridge.msdk.video.module.a.a.j, com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public final void a(int i, Object obj) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.a) {
            if (i != 8) {
                if (i != 103 && i != 105) {
                    if (i == 107) {
                        this.k.showVideoClickView(-1);
                        this.j.setCover(false);
                        this.j.setMiniEndCardState(false);
                        this.j.videoOperate(1);
                    } else if (i == 112) {
                        this.j.setCover(true);
                        this.j.setMiniEndCardState(true);
                        this.j.videoOperate(2);
                    } else if (i != 115) {
                        switch (i) {
                        }
                    } else {
                        this.k.resizeMiniCard(this.j.getBorderViewWidth(), this.j.getBorderViewHeight(), this.j.getBorderViewRadius());
                    }
                }
            } else if (this.k != null) {
                if (!this.k.showAlertWebView()) {
                    if (this.j != null) {
                        this.j.showAlertView();
                    }
                } else if (this.j != null) {
                    this.j.alertWebViewShowed();
                }
            } else if (this.j != null) {
                this.j.showAlertView();
            }
        }
        super.a(i, obj);
    }
}
