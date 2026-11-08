package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.accs.base.IBaseReceiver;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ReceiverImpl implements IBaseReceiver {
    @Override // com.taobao.accs.base.IBaseReceiver
    public void onReceive(Context context, Intent intent) {
        ALog.d("ReceiverImpl", "ReceiverImpl onReceive begin......", new Object[0]);
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            intent = new Intent();
        }
        try {
            if (UtilityImpl.e(context)) {
                intent.setClassName(context.getPackageName(), j.channelService);
                com.taobao.accs.a.a.a(context.getApplicationContext(), intent);
            } else {
                Process.killProcess(Process.myPid());
            }
            if (UtilityImpl.f(context)) {
                intent.setClassName(context, com.taobao.accs.client.a.a(context.getPackageName()));
                com.taobao.accs.a.a.a(context.getApplicationContext(), intent);
            }
        } catch (Throwable th) {
            ALog.e("ReceiverImpl", "ReceiverImpl onReceive,exception,e=" + th.getMessage(), new Object[0]);
        }
    }
}
