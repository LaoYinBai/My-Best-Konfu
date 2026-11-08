package com.taobao.agoo.a;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.l;
import com.taobao.accs.utl.p;
import com.taobao.agoo.ICallback;
import com.taobao.agoo.IRegister;
import com.taobao.agoo.TaobaoRegister;
import com.taobao.agoo.a.a.d;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.Config;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements AccsDataListener {
    public static a b;
    public Map<String, ICallback> a = new HashMap();

    @Override // com.taobao.accs.base.AccsDataListener
    public void onAntiBrush(boolean z, TaoBaseService.ExtraInfo extraInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onBind(String str, int i, TaoBaseService.ExtraInfo extraInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onConnected(TaoBaseService.ConnectInfo connectInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onData(String str, String str2, String str3, byte[] bArr, TaoBaseService.ExtraInfo extraInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onDisconnected(TaoBaseService.ConnectInfo connectInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onSendData(String str, String str2, int i, TaoBaseService.ExtraInfo extraInfo) {
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onUnbind(String str, int i, TaoBaseService.ExtraInfo extraInfo) {
    }

    public b(Context context) {
        if (b == null) {
            b = new a(context.getApplicationContext());
        }
    }

    @Override // com.taobao.accs.base.AccsDataListener
    public void onResponse(String str, String str2, int i, byte[] bArr, TaoBaseService.ExtraInfo extraInfo) {
        try {
            try {
                if ("AgooDeviceCmd".equals(str)) {
                    ICallback iCallback = this.a.get(str2);
                    if (i == 200) {
                        String str3 = new String(bArr, "utf-8");
                        ALog.i("RequestListener", "RequestListener onResponse", Constants.KEY_DATA_ID, str2, "listener", iCallback, "json", str3);
                        JSONObject jSONObject = new JSONObject(str3);
                        String strA = p.a(jSONObject, com.taobao.agoo.a.a.b.JSON_ERRORCODE, null);
                        String strA2 = p.a(jSONObject, com.taobao.agoo.a.a.b.JSON_CMD, null);
                        if (!"success".equals(strA)) {
                            if (iCallback != null) {
                                iCallback.onFailure(String.valueOf(strA), "agoo server error");
                            }
                            if ("AgooDeviceCmd".equals(str)) {
                                this.a.remove(str2);
                                return;
                            }
                            return;
                        }
                        if ("register".equals(strA2)) {
                            String strA3 = p.a(jSONObject, com.taobao.agoo.a.a.b.JSON_DEVICE_ID, null);
                            if (!TextUtils.isEmpty(strA3)) {
                                TaobaoRegister.setIsRegisterSuccess(true);
                                l.a().b();
                                Config.a(GlobalClientInfo.getContext(), strA3);
                                b.a(GlobalClientInfo.getContext().getPackageName());
                                if (iCallback instanceof IRegister) {
                                    UtilityImpl.c("Agoo_AppStore", GlobalClientInfo.getContext());
                                    ((IRegister) iCallback).onSuccess(strA3);
                                }
                            } else if (iCallback != null) {
                                iCallback.onFailure("", "agoo server error deviceid null");
                            }
                            if ("AgooDeviceCmd".equals(str)) {
                                this.a.remove(str2);
                                return;
                            }
                            return;
                        }
                        if ("setAlias".equals(strA2)) {
                            a(jSONObject, iCallback);
                            if ("AgooDeviceCmd".equals(str)) {
                                this.a.remove(str2);
                                return;
                            }
                            return;
                        }
                        if (com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS.equals(strA2)) {
                            Config.b(GlobalClientInfo.getContext(), (String) null);
                            if (iCallback != null) {
                                iCallback.onSuccess();
                            }
                            b.a();
                            if ("AgooDeviceCmd".equals(str)) {
                                this.a.remove(str2);
                                return;
                            }
                            return;
                        }
                        if ((d.JSON_CMD_ENABLEPUSH.equals(strA2) || d.JSON_CMD_DISABLEPUSH.equals(strA2)) && iCallback != null) {
                            iCallback.onSuccess();
                        }
                    } else if (iCallback != null) {
                        iCallback.onFailure(String.valueOf(i), "accs channel error");
                    }
                }
                if (!"AgooDeviceCmd".equals(str)) {
                    return;
                }
            } catch (Throwable th) {
                ALog.e("RequestListener", "onResponse", th, new Object[0]);
                if (!"AgooDeviceCmd".equals(str)) {
                    return;
                }
            }
            this.a.remove(str2);
        } catch (Throwable th2) {
            if ("AgooDeviceCmd".equals(str)) {
                this.a.remove(str2);
            }
            throw th2;
        }
    }

    private void a(JSONObject jSONObject, ICallback iCallback) throws JSONException {
        String strA = p.a(jSONObject, com.taobao.agoo.a.a.a.JSON_PUSH_USER_TOKEN, null);
        if (TextUtils.isEmpty(strA)) {
            if (iCallback != null) {
                iCallback.onFailure("", "agoo server error-pushtoken null");
            }
        } else {
            Config.b(GlobalClientInfo.getContext(), strA);
            if (iCallback != null) {
                iCallback.onSuccess();
                b.c(iCallback.extra);
            }
        }
    }
}
