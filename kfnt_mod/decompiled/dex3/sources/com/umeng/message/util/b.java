package com.umeng.message.util;

import android.content.Context;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String a = "b";
    private static final int b = 100;

    public static void a(Context context) {
        HttpDns.getService(context, MsgConstant.ACCOUNT_ID).setExpiredIPEnabled(true);
    }

    public static String a(Context context, String str) {
        String ipByHostAsync;
        String str2 = null;
        try {
            HttpDnsService service = HttpDns.getService(context, MsgConstant.ACCOUNT_ID);
            while (true) {
                ipByHostAsync = service.getIpByHostAsync(str);
                try {
                    Thread.sleep(100L);
                    if (ipByHostAsync != null) {
                        break;
                    }
                    str2 = ipByHostAsync;
                } catch (InterruptedException unused) {
                    UMLog uMLog = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(a, 0, "Thread interrupted exception");
                    return ipByHostAsync;
                }
            }
        } catch (InterruptedException unused2) {
            ipByHostAsync = str2;
        }
        return ipByHostAsync;
    }
}
