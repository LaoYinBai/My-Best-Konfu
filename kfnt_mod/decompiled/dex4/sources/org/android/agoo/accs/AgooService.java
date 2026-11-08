package org.android.agoo.accs;

import android.text.TextUtils;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.j;
import com.taobao.accs.utl.k;
import java.nio.charset.Charset;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.CallBack;
import org.android.agoo.common.Config;
import org.android.agoo.control.AgooFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class AgooService extends TaoBaseService {
    public static CallBack a;
    public static CallBack b;
    private AgooFactory c;

    public void onCreate() {
        super.onCreate();
        ALog.d("AgooService", "into--[onCreate]", new Object[0]);
        this.c = new AgooFactory();
        this.c.init(getApplicationContext(), null, null);
    }

    public void onData(String str, String str2, String str3, byte[] bArr, TaoBaseService.ExtraInfo extraInfo) {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("AgooService", "into--[onData]:serviceId:" + str + ",dataId=" + str3, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("push data:");
            sb.append(new String(bArr, Charset.forName("UTF-8")));
            ALog.d("AgooService", sb.toString(), new Object[0]);
        }
        UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", j.b(getApplicationContext()), str3);
        k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_total_arrive", "total_arrive", 0.0d);
        try {
            this.c.saveMsg(bArr);
            this.c.msgRecevie(bArr, AgooConstants.MESSAGE_SOURCE_ACCS, extraInfo);
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", "onDataError", th);
            ALog.e("AgooService", "into--[onData,dealMessage]:error:" + th, new Object[0]);
        }
    }

    public void onBind(String str, int i, TaoBaseService.ExtraInfo extraInfo) {
        if (ALog.isPrintLog(ALog.Level.E)) {
            ALog.e("AgooService", "into--[onBind]:serviceId:" + str + ",errorCode=" + i, new Object[0]);
        }
        if (a != null && "agooSend".equals(str)) {
            if (i == 200) {
                a.onSuccess();
            } else {
                a.onFailure(String.valueOf(i), "bind Agoo service fail");
            }
        }
        a = null;
    }

    public void onUnbind(String str, int i, TaoBaseService.ExtraInfo extraInfo) {
        if (ALog.isPrintLog(ALog.Level.E)) {
            ALog.e("AgooService", "into--[onUnbind]:serviceId:" + str + ",errorCode=" + i, new Object[0]);
        }
        if (b != null && "agooSend".equals(str)) {
            if (i == 200) {
                b.onSuccess();
            } else {
                b.onFailure(String.valueOf(i), "unbind Agoo service fail");
            }
        }
        b = null;
    }

    public void onSendData(String str, String str2, int i, TaoBaseService.ExtraInfo extraInfo) {
        try {
            if (ALog.isPrintLog(ALog.Level.I)) {
                ALog.i("AgooService", "onSendData,dataId=" + str2 + ",errorCode=" + i + ",serviceId=" + str, new Object[0]);
            }
            if (i == 200) {
                if (TextUtils.equals(AgooConstants.AGOO_SERVICE_AGOOACK, str)) {
                    k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_success_ack", "8/9", 0.0d);
                }
                if (!TextUtils.isEmpty(str) && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK) && Long.parseLong(str2) > 300000000 && Long.parseLong(str2) < 600000000) {
                    if (ALog.isPrintLog(ALog.Level.I)) {
                        ALog.i("AgooService", "onSendData,AckData=" + str2 + ",serviceId=" + str, new Object[0]);
                        return;
                    }
                    return;
                }
                if (TextUtils.isEmpty(str) || !TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK) || Long.parseLong(str2) <= 600000000 || !ALog.isPrintLog(ALog.Level.I)) {
                    return;
                }
                ALog.i("AgooService", "onSendData,reportData=" + str2 + ",serviceId=" + str, new Object[0]);
                return;
            }
            if (TextUtils.equals(AgooConstants.AGOO_SERVICE_AGOOACK, str)) {
                Config.a(getApplicationContext(), 1);
                k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", String.valueOf(i), 0.0d);
            }
            if (ALog.isPrintLog(ALog.Level.I)) {
                ALog.i("AgooService", "onSendData error,dataId=" + str2 + ",serviceId=" + str, new Object[0]);
                ALog.e("AgooService", "into--[parseError]", new Object[0]);
            }
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", j.b(getApplicationContext()), "errorCode", str2 + ",serviceId=" + str + ",errorCode=" + i);
        } catch (Throwable th) {
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e("AgooService", "onSendData exception,e=" + th.getMessage() + ",e.getStackMsg=" + a(th), new Object[0]);
            }
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.agooService", j.b(getApplicationContext()), "onSendDataException", a(th));
        }
    }

    public void onResponse(String str, String str2, int i, byte[] bArr, TaoBaseService.ExtraInfo extraInfo) {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i("AgooService", "onResponse,dataId=" + str2 + ",errorCode=" + i + ",data=" + bArr + ",serviceId=" + str, new Object[0]);
        }
        String str3 = null;
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    str3 = new String(bArr, "utf-8");
                }
            } catch (Throwable th) {
                ALog.e("AgooService", "onResponse get data error,e=" + th, new Object[0]);
            }
        }
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("AgooService", "onResponse,message=" + str3, new Object[0]);
        }
        if (i == 200 && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK)) {
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e("AgooService", "request is success", new Object[]{"dataId", str2});
            }
            this.c.updateMsg(bArr, true);
        } else {
            if (i != 200 && TextUtils.equals(str, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                if (ALog.isPrintLog(ALog.Level.E)) {
                    ALog.e("AgooService", "request is error", new Object[]{"dataId", str2, "errorid", Integer.valueOf(i)});
                }
                Config.a(getApplicationContext(), 1);
                k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", String.valueOf(i), 0.0d);
                return;
            }
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e("AgooService", "business request is error,message=" + str3, new Object[0]);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement.toString());
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }
}
