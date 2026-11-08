package com.taobao.agoo;

import android.content.Context;
import com.taobao.accs.utl.ALog;
import org.android.agoo.message.MessageReceiverService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TaobaoMessageIntentReceiverService extends MessageReceiverService {
    public String getIntentServiceClassName(Context context) {
        ALog.w("Taobao", "getPackage Name=" + context.getPackageName(), new Object[0]);
        return com.taobao.accs.client.a.a(context.getPackageName());
    }
}
