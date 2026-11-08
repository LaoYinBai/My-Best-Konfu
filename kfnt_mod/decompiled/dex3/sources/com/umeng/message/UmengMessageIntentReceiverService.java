package com.umeng.message;

import android.content.Context;
import org.android.agoo.message.MessageReceiverService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageIntentReceiverService extends MessageReceiverService {
    public String getIntentServiceClassName(Context context) {
        return MsgConstant.DEFAULT_INTENT_SERVICE_CLASS_NAME;
    }
}
