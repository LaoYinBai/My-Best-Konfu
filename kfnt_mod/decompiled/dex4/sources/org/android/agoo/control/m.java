package org.android.agoo.control;

import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import java.net.URL;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.Config;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class m implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ boolean d;
    final /* synthetic */ NotifManager e;

    m(NotifManager notifManager, String str, String str2, String str3, boolean z) {
        this.e = notifManager;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            HashMap map = new HashMap();
            map.put("thirdTokenType", this.a);
            map.put("token", this.b);
            map.put("appkey", Config.a(NotifManager.mContext));
            map.put("utdid", com.taobao.accs.utl.j.b(NotifManager.mContext));
            map.put("vendorSdkVersion", this.c);
            ALog.d("NotifManager", "report,utdid=" + com.taobao.accs.utl.j.b(NotifManager.mContext) + ",regId=" + this.b + ",type=" + this.a, new Object[0]);
            ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest((String) null, "agooTokenReport", new JSONObject(map).toString().getBytes("UTF-8"), (String) null, (String) null, (URL) null, (String) null);
            com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(NotifManager.mContext, Config.a(NotifManager.mContext), Config.b(NotifManager.mContext));
            String strA = this.d ? accsInstance.a(NotifManager.mContext, accsRequest) : accsInstance.a(NotifManager.mContext, accsRequest, new TaoBaseService.ExtraInfo());
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.i("NotifManager", "reportThirdPushToken,dataId=" + strA + ",regId=" + this.b + ",type=" + this.a, new Object[0]);
            }
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "reportThirdPushToken", com.taobao.accs.utl.j.b(NotifManager.mContext), th.toString());
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e("NotifManager", "[report] is error", th, new Object[0]);
            }
        }
    }
}
