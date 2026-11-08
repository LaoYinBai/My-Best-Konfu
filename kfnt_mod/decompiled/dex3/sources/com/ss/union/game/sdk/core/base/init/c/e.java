package com.ss.union.game.sdk.core.base.init.c;

import com.ss.union.game.sdk.common.download.GameDownloadManager;
import com.ss.union.game.sdk.common.net.dns.VDNSUtils;
import com.ss.union.game.sdk.common.net.tnc.VTncUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.LocalConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "CoreBaseAfterPermissionInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        LocalConfigManager.readLocalConfig();
        VDNSUtils.init(GlobalApplicationUtils.getContext(), AppIdManager.sdkDemoAid());
        VTncUtils.init(GlobalApplicationUtils.getContext(), AppIdManager.sdkDemoAid());
        GameDownloadManager.init(GlobalApplicationUtils.getApplication());
        finish();
    }
}
