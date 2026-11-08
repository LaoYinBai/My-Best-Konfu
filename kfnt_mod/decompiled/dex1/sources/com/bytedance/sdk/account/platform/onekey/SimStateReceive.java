package com.bytedance.sdk.account.platform.onekey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.common.utility.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SimStateReceive extends BroadcastReceiver {
    private static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Logger.d("SimStateReceive", "SIM_STATE_CHANGED");
        if (ACTION_SIM_STATE_CHANGED.equals(intent.getAction())) {
            CommonUtils.cleanSimCache();
        }
    }
}
