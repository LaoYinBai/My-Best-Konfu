package com.taobao.accs.data;

import android.content.Intent;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class l implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ MsgDistributeService b;

    l(MsgDistributeService msgDistributeService, Intent intent) {
        this.b = msgDistributeService;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        ALog.i("MsgDistributeService", "onStartCommand send message", new Object[0]);
        ACCSManager.AccsRequest accsRequest = (ACCSManager.AccsRequest) this.a.getSerializableExtra(Constants.KEY_SEND_REQDATA);
        String stringExtra = this.a.getStringExtra("packageName");
        String stringExtra2 = this.a.getStringExtra(Constants.KEY_APP_KEY);
        String stringExtra3 = this.a.getStringExtra(Constants.KEY_CONFIG_TAG);
        if (TextUtils.isEmpty(stringExtra3)) {
            stringExtra3 = stringExtra2;
        }
        ACCSManager.getAccsInstance(this.b.getApplicationContext(), stringExtra2, stringExtra3).a(this.b.getApplicationContext(), accsRequest, stringExtra, false);
    }
}
