package com.taobao.accs.client;

import android.content.Context;
import com.taobao.accs.ChannelService;
import com.taobao.accs.IProcessName;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.t;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlobalConfig {
    public static AccsConfig.ACCS_GROUP mGroup = AccsConfig.ACCS_GROUP.TAOBAO;
    public static boolean enableCookie = true;

    public static void setControlFrameMaxRetry(int i) {
        Message.CONTROL_MAX_RETRY_TIMES = i;
    }

    public static void setMainProcessName(String str) {
        a.d = str;
    }

    public static void setChannelProcessName(String str) {
        a.e = str;
    }

    public static void setCurrProcessNameImpl(IProcessName iProcessName) {
        a.f = iProcessName;
    }

    public static void setChannelReuse(boolean z, AccsConfig.ACCS_GROUP accs_group) {
        GlobalClientInfo.d = z;
        mGroup = accs_group;
    }

    public static void setEnableForground(Context context, boolean z) {
        ALog.i("GlobalConfig", "setEnableForground", "enable", Boolean.valueOf(z));
        t.a(context, ChannelService.SUPPORT_FOREGROUND_VERSION_KEY, z ? 21 : 0);
    }
}
