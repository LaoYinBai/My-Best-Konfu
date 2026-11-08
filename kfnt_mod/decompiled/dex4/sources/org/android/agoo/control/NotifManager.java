package org.android.agoo.control;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.Config;
import org.android.agoo.common.MsgDO;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class NotifManager {
    private static final String ACK_MESSAGE = "accs.ackMessage";
    private static final int EVENT_ID = 66001;
    private static final String TAG = "NotifManager";
    private static Context mContext;

    public void pingApp(String str, String str2, String str3, int i) {
    }

    public void init(Context context) {
        mContext = context;
    }

    public void handlerACKMessage(MsgDO msgDO, TaoBaseService.ExtraInfo extraInfo) {
        if (msgDO == null) {
            return;
        }
        if (TextUtils.isEmpty(msgDO.msgIds) && TextUtils.isEmpty(msgDO.removePacks) && TextUtils.isEmpty(msgDO.errorCode)) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, com.taobao.accs.utl.j.b(mContext), "handlerACKMessageRetuen", "msgids=" + msgDO.msgIds + ",removePacks=" + msgDO.removePacks + ",errorCode=" + msgDO.errorCode);
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("api", AgooConstants.AGOO_SERVICE_AGOOACK);
            map.put(AgooConstants.MESSAGE_ID, msgDO.msgIds + "@" + msgDO.messageSource);
            if (!TextUtils.isEmpty(msgDO.removePacks)) {
                map.put("del_pack", msgDO.removePacks);
            }
            if (!TextUtils.isEmpty(msgDO.errorCode)) {
                map.put("ec", msgDO.errorCode);
            }
            if (!TextUtils.isEmpty(msgDO.type)) {
                map.put(AgooConstants.MESSAGE_TYPE, msgDO.type);
            }
            if (!TextUtils.isEmpty(msgDO.extData)) {
                map.put("ext", msgDO.extData);
            }
            map.put("appkey", Config.a(mContext));
            map.put("utdid", com.taobao.accs.utl.j.b(mContext));
            byte[] bytes = new JSONObject(map).toString().getBytes("UTF-8");
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, com.taobao.accs.utl.j.b(mContext), "handlerACKMessageSendData", msgDO.msgIds);
            com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_ack", "handlerACKMessage", 0.0d);
            ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest((String) null, AgooConstants.AGOO_SERVICE_AGOOACK, bytes, (String) null, (String) null, (URL) null, (String) null);
            if (msgDO != null) {
                accsRequest.setTag(msgDO.msgIds);
            }
            ALog.i(TAG, "handlerACKMessage,endRequest,dataId=" + ACCSManager.getAccsInstance(mContext, Config.a(mContext), Config.b(mContext)).a(mContext, accsRequest, extraInfo), new Object[0]);
        } catch (Throwable th) {
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e(TAG, "handlerACKMessage Throwable,msgIds=" + msgDO.msgIds + ",type=" + msgDO.type + ",e=" + th.toString(), new Object[0]);
            }
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, ACK_MESSAGE, com.taobao.accs.utl.j.b(mContext), "handlerACKMessageExceptionFailed", th.toString());
        }
    }

    public void report(MsgDO msgDO, TaoBaseService.ExtraInfo extraInfo) {
        if (TextUtils.isEmpty(msgDO.reportStr)) {
            return;
        }
        try {
            if (Integer.parseInt(msgDO.reportStr) >= -1) {
                reportMethod(msgDO, extraInfo);
                if (msgDO.isFromCache) {
                    return;
                }
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_ack", msgDO.msgStatus, 0.0d);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "[report] is error", th, new Object[0]);
        }
    }

    public void reportNotifyMessage(MsgDO msgDO) {
        if (msgDO != null) {
            try {
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_report_id", msgDO.msgIds, 0.0d);
                ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest((String) null, AgooConstants.AGOO_SERVICE_AGOOACK, convertMsgToBytes(msgDO), (String) null, (String) null, (URL) null, (String) null);
                ACCSManager.getAccsInstance(mContext, Config.a(mContext), Config.b(mContext)).a(mContext, accsRequest, (TaoBaseService.ExtraInfo) null);
                if (ALog.isPrintLog(ALog.Level.E)) {
                    ALog.e(TAG, "reportNotifyMessage", new Object[]{"dataId", accsRequest.dataId, "status", msgDO.msgStatus});
                }
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_click", msgDO.msgStatus, 0.0d);
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_ack", msgDO.msgStatus, 0.0d);
            } catch (Throwable th) {
                ALog.e(TAG, "[reportNotifyMessage] is error", th, new Object[0]);
                com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "error", th.toString(), 0.0d);
            }
        }
    }

    private byte[] convertMsgToBytes(MsgDO msgDO) throws UnsupportedEncodingException {
        HashMap map = new HashMap();
        map.put("api", "agooReport");
        map.put(AgooConstants.MESSAGE_ID, msgDO.msgIds + "@" + msgDO.messageSource);
        map.put("ext", msgDO.extData);
        map.put("status", msgDO.msgStatus);
        if (!TextUtils.isEmpty(msgDO.errorCode)) {
            map.put("ec", msgDO.errorCode);
        }
        if (!TextUtils.isEmpty(msgDO.type)) {
            map.put(AgooConstants.MESSAGE_TYPE, msgDO.type);
        }
        if (!TextUtils.isEmpty(msgDO.fromPkg)) {
            map.put("fromPkg", msgDO.fromPkg);
        }
        if (!TextUtils.isEmpty(msgDO.fromAppkey)) {
            map.put(AgooConstants.MESSAGE_FROM_APPKEY, msgDO.fromAppkey);
        }
        if (!TextUtils.isEmpty(msgDO.notifyEnable)) {
            map.put("notifyEnable", msgDO.notifyEnable);
        }
        if (!TextUtils.isEmpty(msgDO.extData)) {
            map.put("ext", msgDO.extData);
        }
        map.put("isStartProc", Boolean.toString(msgDO.isStartProc));
        map.put("appkey", Config.a(mContext));
        map.put("utdid", com.taobao.accs.utl.j.b(mContext));
        map.put("evokeAppStatus", String.valueOf(msgDO.evokeAppStatus));
        map.put("lastActiveTime", String.valueOf(msgDO.lastActiveTime));
        map.put("isGlobalClick", String.valueOf(msgDO.isGlobalClick));
        return new JSONObject(map).toString().getBytes("UTF-8");
    }

    private void reportMethod(MsgDO msgDO, TaoBaseService.ExtraInfo extraInfo) {
        try {
            if (msgDO == null) {
                ALog.e(TAG, "reportMethod msg null", new Object[0]);
                return;
            }
            ACCSManager.AccsRequest accsRequest = new ACCSManager.AccsRequest((String) null, AgooConstants.AGOO_SERVICE_AGOOACK, convertMsgToBytes(msgDO), (String) null, (String) null, (URL) null, (String) null);
            accsRequest.setTag(msgDO.msgIds);
            String strA = ACCSManager.getAccsInstance(mContext, Config.a(mContext), Config.b(mContext)).a(mContext, accsRequest, extraInfo);
            if (ALog.isPrintLog(ALog.Level.E)) {
                ALog.e(TAG, AgooConstants.MESSAGE_REPORT, new Object[]{"dataId", strA, "status", msgDO.msgStatus, "errorcode", msgDO.errorCode});
            }
        } catch (Throwable th) {
            com.taobao.accs.utl.k.a(AgooConstants.MESSAGE_SOURCE_ACCS, "error", th.toString(), 0.0d);
        }
    }

    public void reportThirdPushToken(String str, String str2, boolean z) {
        ThreadPoolExecutorFactory.schedule(new l(this, str2, str, z), 10L, TimeUnit.SECONDS);
    }

    public void reportThirdPushToken(String str, String str2, String str3, boolean z) {
        ThreadPoolExecutorFactory.schedule(new m(this, str2, str, str3, z), 10L, TimeUnit.SECONDS);
    }

    public void reportThirdPushToken(String str, String str2) {
        reportThirdPushToken(str, str2, true);
    }

    public void doUninstall(String str, boolean z) {
        try {
            HashMap map = new HashMap();
            map.put("pack", str);
            map.put("appkey", Config.a(mContext));
            map.put("utdid", com.taobao.accs.utl.j.b(mContext));
            ACCSManager.getAccsInstance(mContext, Config.a(mContext), Config.b(mContext)).a(mContext, new ACCSManager.AccsRequest((String) null, "agooKick", new JSONObject(map).toString().getBytes("UTF-8"), (String) null, (String) null, (URL) null, (String) null), new TaoBaseService.ExtraInfo());
        } catch (Throwable th) {
            ALog.e(TAG, "[doUninstall] is error", th, new Object[0]);
        }
    }

    private boolean isAppInstalled(String str) {
        PackageInfo packageInfo;
        try {
        } catch (Throwable unused) {
            packageInfo = null;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        packageInfo = mContext.getPackageManager().getPackageInfo(str, 0);
        if (packageInfo == null) {
            return false;
        }
        ALog.i(TAG, "isAppInstalled true..", new Object[0]);
        return true;
    }

    private String getVersion(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "null";
            }
            String str2 = mContext.getPackageManager().getPackageInfo(str, 0).versionName;
            ALog.d(TAG, "getVersion###版本号为 : " + str2, new Object[0]);
            return str2;
        } catch (Throwable unused) {
            return "null";
        }
    }
}
