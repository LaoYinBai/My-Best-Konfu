package com.taobao.accs.utl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.Constants;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.utl.ALog;
import com.vivo.push.PushClientConstants;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.accs.AgooService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String TAG = "a";
    private static Handler a = new Handler(Looper.getMainLooper());

    public static int a(Context context, Intent intent, AccsDataListener accsDataListener) {
        if (accsDataListener == null || context == null) {
            ALog.e(TAG, "onReceiveData listener or context null", new Object[0]);
            return 2;
        }
        if (intent != null) {
            try {
                int intExtra = intent.getIntExtra(Constants.KEY_COMMAND, -1);
                int intExtra2 = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                String stringExtra = intent.getStringExtra(Constants.KEY_USER_ID);
                String stringExtra2 = intent.getStringExtra(Constants.KEY_DATA_ID);
                String stringExtra3 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
                if (ALog.isPrintLog(ALog.Level.I)) {
                    ALog.i(TAG, "onReceiveData", Constants.KEY_DATA_ID, stringExtra2, Constants.KEY_SERVICE_ID, stringExtra3, Constants.KEY_COMMAND, Integer.valueOf(intExtra), PushClientConstants.TAG_CLASS_NAME, accsDataListener.getClass().getName());
                }
                if (intExtra > 0) {
                    UTMini.getInstance().commitEvent(66001, "MsgToBuss5", "commandId=" + intExtra, "serviceId=" + stringExtra3 + " dataId=" + stringExtra2, Integer.valueOf(Constants.SDK_VERSION_CODE));
                    k.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, "3commandId=" + intExtra + "serviceId=" + stringExtra3, 0.0d);
                    switch (intExtra) {
                        case 5:
                            a.post(new b(accsDataListener, stringExtra3, intExtra2, intent));
                            break;
                        case 6:
                            a.post(new c(accsDataListener, stringExtra3, intExtra2, intent));
                            break;
                        case 100:
                            if (TextUtils.equals(Constants.SEND_TYPE_RES, intent.getStringExtra(Constants.KEY_SEND_TYPE))) {
                                a.post(new e(accsDataListener, stringExtra3, stringExtra2, intExtra2, intent.getByteArrayExtra("data"), intent));
                                break;
                            } else {
                                a.post(new f(accsDataListener, stringExtra3, stringExtra2, intExtra2, intent));
                                break;
                            }
                        case 101:
                            byte[] byteArrayExtra = intent.getByteArrayExtra("data");
                            boolean booleanExtra = intent.getBooleanExtra(Constants.KEY_NEED_BUSINESS_ACK, false);
                            if (byteArrayExtra != null) {
                                if (ALog.isPrintLog(ALog.Level.D)) {
                                    ALog.d(TAG, "onReceiveData COMMAND_RECEIVE_DATA onData dataId:" + stringExtra2 + " serviceId:" + stringExtra3, new Object[0]);
                                }
                                TaoBaseService.ExtraInfo extraInfoB = b(intent);
                                if (booleanExtra) {
                                    ALog.i(TAG, "onReceiveData try to send biz ack dataId " + stringExtra2, new Object[0]);
                                    a(context, intent, stringExtra2, extraInfoB.oriExtHeader);
                                }
                                try {
                                    intent.getExtras().setClassLoader(NetPerformanceMonitor.class.getClassLoader());
                                    NetPerformanceMonitor netPerformanceMonitor = (NetPerformanceMonitor) intent.getExtras().getSerializable(Constants.KEY_MONIROT);
                                    if (netPerformanceMonitor != null) {
                                        netPerformanceMonitor.onToAccsTime();
                                        if (!(context instanceof AgooService)) {
                                            AppMonitor.getInstance().commitStat(netPerformanceMonitor);
                                        }
                                    }
                                } catch (Exception e) {
                                    ALog.e(TAG, "get NetPerformanceMonitor Error:", e, new Object[0]);
                                }
                                k.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS_SUCCESS, "1commandId=101serviceId=" + stringExtra3, 0.0d);
                                a.post(new d(accsDataListener, stringExtra3, stringExtra, stringExtra2, byteArrayExtra, extraInfoB));
                                break;
                            } else {
                                ALog.e(TAG, "onReceiveData COMMAND_RECEIVE_DATA msg null", new Object[0]);
                                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, stringExtra3, "1", "COMMAND_RECEIVE_DATA msg null");
                                break;
                            }
                        case 103:
                            boolean booleanExtra2 = intent.getBooleanExtra(Constants.KEY_CONNECT_AVAILABLE, false);
                            String stringExtra4 = intent.getStringExtra(Constants.KEY_HOST);
                            String stringExtra5 = intent.getStringExtra(Constants.KEY_ERROR_DETAIL);
                            boolean booleanExtra3 = intent.getBooleanExtra(Constants.KEY_TYPE_INAPP, false);
                            boolean booleanExtra4 = intent.getBooleanExtra(Constants.KEY_CENTER_HOST, false);
                            if (!TextUtils.isEmpty(stringExtra4)) {
                                if (booleanExtra2) {
                                    a.post(new h(accsDataListener, stringExtra4, booleanExtra3, booleanExtra4));
                                    break;
                                } else {
                                    a.post(new i(accsDataListener, stringExtra4, booleanExtra3, booleanExtra4, intExtra2, stringExtra5));
                                    break;
                                }
                            }
                            break;
                        case 104:
                            boolean booleanExtra5 = intent.getBooleanExtra(Constants.KEY_ANTI_BRUSH_RET, false);
                            ALog.e(TAG, "onReceiveData anti brush result:" + booleanExtra5, new Object[0]);
                            a.post(new g(accsDataListener, booleanExtra5));
                            break;
                        default:
                            ALog.w(TAG, "onReceiveData command not handled", new Object[0]);
                            break;
                    }
                } else {
                    ALog.w(TAG, "onReceiveData command not handled", new Object[0]);
                }
            } catch (Exception e2) {
                k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, "", "1", "callback error" + e2.toString());
                ALog.e(TAG, "onReceiveData", e2, new Object[0]);
            }
        }
        return 2;
    }

    private static Map<TaoBaseService.ExtHeaderType, String> a(Map<Integer, String> map) {
        HashMap map2;
        if (map == null) {
            return null;
        }
        try {
            map2 = new HashMap();
        } catch (Exception e) {
            e = e;
            map2 = null;
        }
        try {
            for (TaoBaseService.ExtHeaderType extHeaderType : TaoBaseService.ExtHeaderType.values()) {
                String str = map.get(Integer.valueOf(extHeaderType.ordinal()));
                if (!TextUtils.isEmpty(str)) {
                    map2.put(extHeaderType, str);
                }
            }
        } catch (Exception e2) {
            e = e2;
            ALog.e(TAG, "getExtHeader", e, new Object[0]);
            return map2;
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TaoBaseService.ExtraInfo b(Intent intent) {
        TaoBaseService.ExtraInfo extraInfo = new TaoBaseService.ExtraInfo();
        try {
            HashMap map = (HashMap) intent.getSerializableExtra(TaoBaseService.ExtraInfo.EXT_HEADER);
            Map<TaoBaseService.ExtHeaderType, String> mapA = a(map);
            String stringExtra = intent.getStringExtra("packageName");
            String stringExtra2 = intent.getStringExtra(Constants.KEY_HOST);
            extraInfo.connType = intent.getIntExtra(Constants.KEY_CONN_TYPE, 0);
            extraInfo.extHeader = mapA;
            extraInfo.oriExtHeader = map;
            extraInfo.fromPackage = stringExtra;
            extraInfo.fromHost = stringExtra2;
        } catch (Throwable th) {
            ALog.e(TAG, "getExtraInfo", th, new Object[0]);
        }
        return extraInfo;
    }

    private static void a(Context context, Intent intent, String str, Map<Integer, String> map) {
        try {
            ALog.i(TAG, "sendBusinessAck", Constants.KEY_DATA_ID, str);
            if (intent != null) {
                String stringExtra = intent.getStringExtra(Constants.KEY_HOST);
                String stringExtra2 = intent.getStringExtra("source");
                String stringExtra3 = intent.getStringExtra(Constants.KEY_TARGET);
                String stringExtra4 = intent.getStringExtra(Constants.KEY_APP_KEY);
                String stringExtra5 = intent.getStringExtra(Constants.KEY_CONFIG_TAG);
                short shortExtra = intent.getShortExtra(Constants.KEY_FLAGS, (short) 0);
                com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, stringExtra4, stringExtra5);
                if (accsInstance != null) {
                    accsInstance.a(stringExtra3, stringExtra2, str, shortExtra, stringExtra, map);
                    k.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_SUCC, "", 0.0d);
                } else {
                    k.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_FAIL, "no acsmgr", 0.0d);
                }
            }
        } catch (Throwable th) {
            ALog.e(TAG, "sendBusinessAck", th, new Object[0]);
            k.a("accs", BaseMonitor.COUNT_BUSINESS_ACK_FAIL, th.toString(), 0.0d);
        }
    }
}
