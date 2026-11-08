package com.huawei;

import com.huawei.hms.push.HmsMessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MessageService extends HmsMessageService {
    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(String str) {
        super.onNewToken(str);
        com.bytedance.common.push.utility.b.b.a(new a(getApplicationContext(), str));
    }
}
