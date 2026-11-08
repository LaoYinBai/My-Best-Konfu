package com.taobao.accs.net;

import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ALog.d(this.a.d(), "sendAccsHeartbeatMessage", new Object[0]);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dataType", "pingreq");
        jSONObject.put("timeInterval", Long.valueOf(this.a.p));
        ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest(null, null, jSONObject.toJSONString().getBytes(), UUID.randomUUID().toString());
        accsRequest.setTarget("accs-iot");
        accsRequest.setTargetServiceName("sal");
        this.a.a(Message.buildRequest(this.a.d, this.a.b((String) null), this.a.d(), this.a.i.getStoreId(), this.a.d.getPackageName(), Constants.TARGET_SERVICE, accsRequest, true), true);
    }
}
