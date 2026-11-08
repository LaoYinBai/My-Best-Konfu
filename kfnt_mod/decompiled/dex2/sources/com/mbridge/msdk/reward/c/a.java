package com.mbridge.msdk.reward.c;

import com.mbridge.msdk.foundation.entity.CampaignUnit;
import com.mbridge.msdk.out.Frame;
import java.util.List;

/* compiled from: RewarLoadVideoResponseHandler.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends c {
    public abstract void a(int i, String str);

    public abstract void a(CampaignUnit campaignUnit);

    @Override // com.mbridge.msdk.reward.c.c
    public final void a(List<Frame> list) {
    }

    @Override // com.mbridge.msdk.reward.c.c
    public final void a(List<com.mbridge.msdk.foundation.same.net.c.b> list, CampaignUnit campaignUnit) {
        a(campaignUnit);
    }

    @Override // com.mbridge.msdk.reward.c.c
    public final void b(int i, String str) {
        a(i, str);
    }
}
