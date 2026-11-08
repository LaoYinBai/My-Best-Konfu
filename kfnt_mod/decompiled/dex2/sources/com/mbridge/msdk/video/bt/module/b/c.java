package com.mbridge.msdk.video.bt.module.b;

import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultShowRewardListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements h {
    @Override // com.mbridge.msdk.video.bt.module.b.h
    public boolean b() {
        return false;
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a() {
        s.a("ShowRewardListener", "onAdShow");
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(boolean z, com.mbridge.msdk.videocommon.b.d dVar) {
        s.a("ShowRewardListener", "onAdClose:isCompleteView:" + z + ",reward:" + dVar);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(String str) {
        s.a("ShowRewardListener", "onShowFail:" + str);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(boolean z, String str, String str2) {
        s.a("ShowRewardListener", "onVideoAdClicked:" + str2);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(String str, String str2) {
        s.a("ShowRewardListener", "onVideoComplete: " + str2);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(boolean z, int i) {
        s.a("ShowRewardListener", "onAdCloseWithIVReward: " + z + "  " + i);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void b(String str, String str2) {
        s.a("ShowRewardListener", "onEndcardShow: " + str2);
    }

    @Override // com.mbridge.msdk.video.bt.module.b.h
    public void a(int i, String str, String str2) {
        s.a("ShowRewardListener", "onAutoLoad: " + str2);
    }
}
