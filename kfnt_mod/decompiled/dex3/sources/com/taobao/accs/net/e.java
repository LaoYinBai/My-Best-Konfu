package com.taobao.accs.net;

import android.content.Intent;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import org.android.agoo.common.Config;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e implements Runnable {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ALog.d(this.a.d(), "startChannelService", new Object[0]);
        Intent intent = new Intent(Constants.ACTION_START_SERVICE);
        intent.putExtra(Constants.KEY_APP_KEY, this.a.i());
        intent.putExtra(Constants.KEY_TTID, this.a.a);
        intent.putExtra("packageName", GlobalClientInfo.getContext().getPackageName());
        intent.putExtra("app_sercet", this.a.i.getAppSecret());
        intent.putExtra(Constants.KEY_MODE, AccsClientConfig.mEnv);
        intent.putExtra("agoo_app_key", Config.a(GlobalClientInfo.getContext()));
        intent.putExtra(Constants.KEY_CONFIG_TAG, this.a.m);
        intent.setClassName(GlobalClientInfo.getContext().getPackageName(), com.taobao.accs.utl.j.channelService);
        com.taobao.accs.a.a.a(GlobalClientInfo.getContext(), intent);
        Intent intent2 = new Intent();
        intent2.setAction("org.agoo.android.intent.action.REPORT");
        intent2.setPackage(GlobalClientInfo.getContext().getPackageName());
        intent2.setClassName(GlobalClientInfo.getContext().getPackageName(), com.taobao.accs.client.a.a(GlobalClientInfo.getContext().getPackageName()));
        com.taobao.accs.a.a.a(GlobalClientInfo.getContext(), intent2);
    }
}
