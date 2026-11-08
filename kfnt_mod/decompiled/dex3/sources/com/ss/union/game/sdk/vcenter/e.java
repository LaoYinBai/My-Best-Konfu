package com.ss.union.game.sdk.vcenter;

import com.ss.union.game.sdk.vcenter.account.callback.IGameCenterAccountChangeListener;
import com.ss.union.game.sdk.vcenter.service.IGameCenterService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements IGameCenterService {
    @Override // com.ss.union.game.sdk.vcenter.service.IGameCenterService
    public void setGameCenterAccountChangeListener(IGameCenterAccountChangeListener iGameCenterAccountChangeListener) {
        c.d().a(iGameCenterAccountChangeListener);
    }
}
