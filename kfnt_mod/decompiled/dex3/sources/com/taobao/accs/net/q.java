package com.taobao.accs.net;

import android.text.TextUtils;
import anet.channel.IAuth;
import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import com.taobao.accs.net.j;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class q implements RequestCb {
    final /* synthetic */ IAuth.AuthCallback a;
    final /* synthetic */ j.a b;

    public void onDataReceive(ByteArray byteArray, boolean z) {
    }

    q(j.a aVar, IAuth.AuthCallback authCallback) {
        this.b = aVar;
        this.a = authCallback;
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        ALog.e(this.b.c, BaseMonitor.ALARM_POINT_AUTH, "httpStatusCode", Integer.valueOf(i));
        if (i == 200) {
            this.a.onAuthSuccess();
            if (this.b.d instanceof j) {
                ((j) this.b.d).n();
            }
        } else {
            this.a.onAuthFail(i, "auth fail");
        }
        Map<String, String> mapA = UtilityImpl.a(map);
        ALog.d(this.b.c, BaseMonitor.ALARM_POINT_AUTH, "header", mapA);
        String str = mapA.get("x-at");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b.d.k = str;
    }

    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        if (i < 0) {
            ALog.e(this.b.c, "auth onFinish", "statusCode", Integer.valueOf(i));
            this.a.onAuthFail(i, "onFinish auth fail");
        }
    }
}
