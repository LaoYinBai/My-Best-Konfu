package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.IAgooAppReceiver;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.l;
import java.util.Map;
import org.android.agoo.common.Config;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class h implements IAgooAppReceiver {
    final /* synthetic */ Context a;
    final /* synthetic */ Context b;
    final /* synthetic */ IRegister c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ com.taobao.accs.c f;

    @Override // com.taobao.accs.IAppReceiver
    public Map<String, String> getAllServices() {
        return null;
    }

    @Override // com.taobao.accs.IAppReceiver
    public String getService(String str) {
        return null;
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onBindApp(int i) {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onBindUser(String str, int i) {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onData(String str, String str2, byte[] bArr) {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onSendData(String str, int i) {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onUnbindApp(int i) {
    }

    @Override // com.taobao.accs.IAppReceiver
    public void onUnbindUser(int i) {
    }

    h(Context context, Context context2, IRegister iRegister, String str, String str2, com.taobao.accs.c cVar) {
        this.a = context;
        this.b = context2;
        this.c = iRegister;
        this.d = str;
        this.e = str2;
        this.f = cVar;
    }

    @Override // com.taobao.accs.IAppReceiverV1
    public void onBindApp(int i, String str) {
        try {
            ALog.i("TaobaoRegister", "onBindApp", Constants.KEY_ERROR_CODE, Integer.valueOf(i));
            if (i == 200) {
                if (TaobaoRegister.mRequestListener == null) {
                    com.taobao.agoo.a.b unused = TaobaoRegister.mRequestListener = new com.taobao.agoo.a.b(this.a);
                }
                GlobalClientInfo.getInstance(this.b).registerListener("AgooDeviceCmd", TaobaoRegister.mRequestListener);
                if (com.taobao.agoo.a.b.b.b(this.a.getPackageName()) && UtilityImpl.b(Constants.SP_CHANNEL_FILE_NAME, this.b)) {
                    ALog.i("TaobaoRegister", "agoo aready Registered return ", new Object[0]);
                    boolean unused2 = TaobaoRegister.isRegisterSuccess = true;
                    l.a().b();
                    if (this.c != null) {
                        this.c.onSuccess(Config.g(this.a));
                        return;
                    }
                    return;
                }
                byte[] bArrA = com.taobao.agoo.a.a.c.a(this.a, this.d, this.e);
                if (bArrA == null) {
                    if (this.c != null) {
                        this.c.onFailure("503.1", "req data null");
                        return;
                    }
                    return;
                }
                String strB = this.f.b(this.a, new ACCSManager.AccsRequest(null, "AgooDeviceCmd", bArrA, null));
                if (TextUtils.isEmpty(strB)) {
                    if (this.c != null) {
                        this.c.onFailure("503.1", "accs channel disabled!");
                        return;
                    }
                    return;
                } else {
                    if (this.c != null) {
                        TaobaoRegister.mRequestListener.a.put(strB, this.c);
                        return;
                    }
                    return;
                }
            }
            if (this.c != null) {
                this.c.onFailure(String.valueOf(i), "accs bindapp error!");
            }
        } catch (Throwable th) {
            ALog.e("TaobaoRegister", "register onBindApp", th, new Object[0]);
        }
    }

    @Override // com.taobao.accs.IAgooAppReceiver
    public String getAppkey() {
        return this.d;
    }
}
