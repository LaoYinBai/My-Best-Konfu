package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.IAppReceiverV1;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.vivo.push.PushClientConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.android.agoo.common.Config;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g {
    private static Set<String> a;
    private static volatile g b;

    protected String b() {
        return com.taobao.accs.utl.j.msgService;
    }

    public static g a() {
        if (b == null) {
            synchronized (g.class) {
                if (b == null) {
                    b = new g();
                }
            }
        }
        return b;
    }

    public static void a(Context context, Intent intent) {
        a(context, null, intent);
    }

    public static void a(Context context, com.taobao.accs.net.b bVar, Intent intent) {
        try {
            ThreadPoolExecutorFactory.getScheduledExecutor().execute(new h(context, bVar, intent));
        } catch (Throwable th) {
            if (bVar != null && intent != null) {
                String stringExtra = intent.getStringExtra(Constants.KEY_CONFIG_TAG);
                String stringExtra2 = intent.getStringExtra(Constants.KEY_DATA_ID);
                if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                    bVar.b(Message.buildErrorReportMessage(stringExtra2, intent.getStringExtra(Constants.KEY_SERVICE_ID), bVar.b((String) null), 3), true);
                }
            }
            ALog.e("MsgDistribute", "distribMessage", th, new Object[0]);
            UTMini.getInstance().commitEvent(66001, "MsgToBuss8", "distribMessage" + th.toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f2  */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.content.Context r26, com.taobao.accs.net.b r27, android.content.Intent r28) {
        /*
            Method dump skipped, instructions count: 657
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.g.b(android.content.Context, com.taobao.accs.net.b, android.content.Intent):void");
    }

    protected boolean a(int i, String str) {
        if (i != 100 && !GlobalClientInfo.AGOO_SERVICE_ID.equals(str)) {
            long jA = UtilityImpl.a();
            if (jA != -1 && jA <= com.ss.union.game.sdk.feedback.picture.select.d.f) {
                com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str, "1", "space low " + jA);
                ALog.e("MsgDistribute", "user space low, don't distribute", "size", Long.valueOf(jA), Constants.KEY_SERVICE_ID, str);
                return true;
            }
        }
        return false;
    }

    protected boolean a(Context context, String str, String str2, Intent intent, IAppReceiver iAppReceiver) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String service = GlobalClientInfo.getInstance(context).getService(intent.getStringExtra(Constants.KEY_CONFIG_TAG), str);
            if (TextUtils.isEmpty(service) && iAppReceiver != null) {
                service = iAppReceiver.getService(str);
            }
            if (TextUtils.isEmpty(service)) {
                service = GlobalClientInfo.getInstance(context).getService(str);
            }
            if (!TextUtils.isEmpty(service) || UtilityImpl.isMainProcess(context)) {
                return false;
            }
            if ("accs".equals(str)) {
                ALog.e("MsgDistribute", "start MsgDistributeService", Constants.KEY_DATA_ID, str2);
            } else {
                ALog.i("MsgDistribute", "start MsgDistributeService", Constants.KEY_DATA_ID, str2);
            }
            intent.setClassName(intent.getPackage(), b());
            com.taobao.accs.a.a.a(context, intent);
            return true;
        } catch (Throwable th) {
            ALog.e("MsgDistribute", "handleMsgInChannelProcess", th, new Object[0]);
            return false;
        }
    }

    private void a(Context context, com.taobao.accs.net.b bVar, Intent intent, String str, String str2, int i, String str3, String str4, String str5, IAppReceiver iAppReceiver, int i2) {
        if (ALog.isPrintLog(ALog.Level.D)) {
            Object[] objArr = new Object[12];
            objArr[0] = Constants.KEY_CONFIG_TAG;
            objArr[1] = str;
            objArr[2] = Constants.KEY_DATA_ID;
            objArr[3] = str5;
            objArr[4] = Constants.KEY_SERVICE_ID;
            objArr[5] = str4;
            objArr[6] = Constants.KEY_COMMAND;
            objArr[7] = Integer.valueOf(i);
            objArr[8] = Constants.KEY_ERROR_CODE;
            objArr[9] = Integer.valueOf(i2);
            objArr[10] = "appReceiver";
            objArr[11] = iAppReceiver == null ? null : iAppReceiver.getClass().getName();
            ALog.d("MsgDistribute", "handleControlMsg", objArr);
        }
        if (iAppReceiver != null) {
            switch (i) {
                case 1:
                    if (iAppReceiver instanceof IAppReceiverV1) {
                        ((IAppReceiverV1) iAppReceiver).onBindApp(i2, null);
                        break;
                    } else {
                        iAppReceiver.onBindApp(i2);
                        break;
                    }
                case 2:
                    if (i2 == 200) {
                        UtilityImpl.disableService(context);
                    }
                    iAppReceiver.onUnbindApp(i2);
                    break;
                case 3:
                    iAppReceiver.onBindUser(str3, i2);
                    break;
                case 4:
                    iAppReceiver.onUnbindUser(i2);
                    break;
                default:
                    switch (i) {
                        case 100:
                            if (TextUtils.isEmpty(str4)) {
                                iAppReceiver.onSendData(str5, i2);
                                break;
                            }
                            break;
                        case 101:
                            if (TextUtils.isEmpty(str4)) {
                                ALog.d("MsgDistribute", "handleControlMsg serviceId isEmpty", new Object[0]);
                                byte[] byteArrayExtra = intent.getByteArrayExtra("data");
                                if (byteArrayExtra != null) {
                                    iAppReceiver.onData(str3, str5, byteArrayExtra);
                                    break;
                                }
                            }
                            break;
                    }
            }
        }
        if (i == 1 && GlobalClientInfo.b != null && str2 != null && str2.equals(Config.a(context))) {
            ALog.d("MsgDistribute", "handleControlMsg agoo receiver onBindApp", new Object[0]);
            GlobalClientInfo.b.onBindApp(i2, null);
            return;
        }
        if (iAppReceiver != null || i == 104 || i == 103) {
            return;
        }
        if (bVar != null) {
            bVar.b(Message.buildErrorReportMessage(str5, str4, bVar.b((String) null), 0), true);
        }
        com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str4, "1", "appReceiver null return");
        UTMini.getInstance().commitEvent(66001, "MsgToBuss7", "commandId=" + i, "serviceId=" + str4 + " errorCode=" + i2 + " dataId=" + str5, Integer.valueOf(Constants.SDK_VERSION_CODE));
    }

    protected void a(Context context, com.taobao.accs.net.b bVar, IAppReceiver iAppReceiver, Intent intent, String str, String str2, int i, int i2) {
        ALog.i("MsgDistribute", "handleBusinessMsg start", Constants.KEY_DATA_ID, str2, Constants.KEY_SERVICE_ID, str, Constants.KEY_COMMAND, Integer.valueOf(i));
        String service = GlobalClientInfo.getInstance(context).getService(intent.getStringExtra(Constants.KEY_CONFIG_TAG), str);
        if (TextUtils.isEmpty(service) && iAppReceiver != null) {
            service = iAppReceiver.getService(str);
        }
        if (TextUtils.isEmpty(service)) {
            service = GlobalClientInfo.getInstance(context).getService(str);
        }
        if (!TextUtils.isEmpty(service)) {
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d("MsgDistribute", "handleBusinessMsg to start service", PushClientConstants.TAG_CLASS_NAME, service);
            }
            intent.setClassName(context, service);
            com.taobao.accs.a.a.a(context, intent);
        } else {
            AccsDataListener listener = GlobalClientInfo.getInstance(context).getListener(str);
            if (listener != null) {
                if (ALog.isPrintLog(ALog.Level.D)) {
                    ALog.d("MsgDistribute", "handleBusinessMsg getListener not null", new Object[0]);
                }
                com.taobao.accs.utl.a.a(context, intent, listener);
            } else {
                if (bVar != null) {
                    bVar.b(Message.buildErrorReportMessage(str2, str, bVar.b((String) null), 0), true);
                }
                ALog.e("MsgDistribute", "handleBusinessMsg getListener also null", new Object[0]);
                com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, str, "1", "service is null");
            }
        }
        UTMini.getInstance().commitEvent(66001, "MsgToBuss", "commandId=" + i, "serviceId=" + str + " errorCode=" + i2 + " dataId=" + str2, Integer.valueOf(Constants.SDK_VERSION_CODE));
        StringBuilder sb = new StringBuilder();
        sb.append("2commandId=");
        sb.append(i);
        sb.append("serviceId=");
        sb.append(str);
        com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_TO_BUSS, sb.toString(), 0.0d);
    }

    protected void a(Context context, Map<String, IAppReceiver> map, Intent intent, int i, int i2) {
        ALog.i("MsgDistribute", "handBroadCastMsg", Constants.KEY_COMMAND, Integer.valueOf(i));
        HashMap map2 = new HashMap();
        if (map != null) {
            for (Map.Entry<String, IAppReceiver> entry : map.entrySet()) {
                Map<String, String> allService = GlobalClientInfo.getInstance(context).getAllService(entry.getKey());
                if (allService == null) {
                    allService = entry.getValue().getAllServices();
                }
                if (allService != null) {
                    map2.putAll(allService);
                }
            }
        }
        if (i != 103) {
            if (i == 104) {
                for (String str : map2.keySet()) {
                    String service = (String) map2.get(str);
                    if (TextUtils.isEmpty(service)) {
                        service = GlobalClientInfo.getInstance(context).getService(str);
                    }
                    if (!TextUtils.isEmpty(service)) {
                        intent.setClassName(context, service);
                        com.taobao.accs.a.a.a(context, intent);
                    }
                }
                return;
            }
            ALog.w("MsgDistribute", "handBroadCastMsg not handled command", new Object[0]);
            return;
        }
        for (String str2 : map2.keySet()) {
            if ("accs".equals(str2) || "windvane".equals(str2) || "motu-remote".equals(str2)) {
                String service2 = (String) map2.get(str2);
                if (TextUtils.isEmpty(service2)) {
                    service2 = GlobalClientInfo.getInstance(context).getService(str2);
                }
                if (!TextUtils.isEmpty(service2)) {
                    intent.setClassName(context, service2);
                    com.taobao.accs.a.a.a(context, intent);
                }
            }
        }
        boolean booleanExtra = intent.getBooleanExtra(Constants.KEY_CONNECT_AVAILABLE, false);
        String stringExtra = intent.getStringExtra(Constants.KEY_HOST);
        String stringExtra2 = intent.getStringExtra(Constants.KEY_ERROR_DETAIL);
        boolean booleanExtra2 = intent.getBooleanExtra(Constants.KEY_TYPE_INAPP, false);
        boolean booleanExtra3 = intent.getBooleanExtra(Constants.KEY_CENTER_HOST, false);
        TaoBaseService.ConnectInfo connectInfo = null;
        if (!TextUtils.isEmpty(stringExtra)) {
            if (booleanExtra) {
                connectInfo = new TaoBaseService.ConnectInfo(stringExtra, booleanExtra2, booleanExtra3);
            } else {
                connectInfo = new TaoBaseService.ConnectInfo(stringExtra, booleanExtra2, booleanExtra3, i2, stringExtra2);
            }
            connectInfo.connected = booleanExtra;
        }
        if (connectInfo != null) {
            ALog.d("MsgDistribute", "handBroadCastMsg ACTION_CONNECT_INFO", connectInfo);
            Intent intent2 = new Intent(Constants.ACTION_CONNECT_INFO);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra(Constants.KEY_CONNECT_INFO, connectInfo);
            context.sendBroadcast(intent2);
            return;
        }
        ALog.e("MsgDistribute", "handBroadCastMsg connect info null, host empty", new Object[0]);
    }

    private boolean a(Context context, Intent intent, String str, String str2) {
        boolean z;
        boolean booleanExtra = intent.getBooleanExtra("routingAck", false);
        boolean booleanExtra2 = intent.getBooleanExtra("routingMsg", false);
        if (booleanExtra) {
            ALog.e("MsgDistribute", "recieve routiong ack", Constants.KEY_DATA_ID, str, Constants.KEY_SERVICE_ID, str2);
            if (a != null) {
                a.remove(str);
            }
            com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_MSG_ROUTING_RATE, "");
            z = true;
        } else {
            z = false;
        }
        if (booleanExtra2) {
            try {
                String stringExtra = intent.getStringExtra("packageName");
                ALog.e("MsgDistribute", "send routiong ack", Constants.KEY_DATA_ID, str, "to pkg", stringExtra, Constants.KEY_SERVICE_ID, str2);
                Intent intent2 = new Intent(Constants.ACTION_COMMAND);
                intent2.putExtra(Constants.KEY_COMMAND, 106);
                intent2.setClassName(stringExtra, com.taobao.accs.utl.j.channelService);
                intent2.putExtra("routingAck", true);
                intent2.putExtra("packageName", stringExtra);
                intent2.putExtra(Constants.KEY_DATA_ID, str);
                com.taobao.accs.a.a.a(context, intent);
            } catch (Throwable th) {
                ALog.e("MsgDistribute", "send routing ack", th, Constants.KEY_SERVICE_ID, str2);
            }
        }
        return z;
    }

    private boolean b(Context context, Intent intent, String str, String str2) {
        if (context.getPackageName().equals(intent.getPackage())) {
            return false;
        }
        try {
            ALog.e("MsgDistribute", "start MsgDistributeService", "receive pkg", context.getPackageName(), "target pkg", intent.getPackage(), Constants.KEY_SERVICE_ID, str2);
            intent.setClassName(intent.getPackage(), com.taobao.accs.utl.j.msgService);
            intent.putExtra("routingMsg", true);
            intent.putExtra("packageName", context.getPackageName());
            com.taobao.accs.a.a.a(context, intent);
            if (a == null) {
                a = new HashSet();
            }
            a.add(str);
            ThreadPoolExecutorFactory.schedule(new i(this, str, str2, intent), 10L, TimeUnit.SECONDS);
        } catch (Throwable th) {
            com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_MSG_ROUTING_RATE, "", com.umeng.commonsdk.framework.c.c, th.toString());
            ALog.e("MsgDistribute", "routing msg error, try election", th, Constants.KEY_SERVICE_ID, str2, Constants.KEY_DATA_ID, str);
        }
        return true;
    }
}
