package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSClient;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.j;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.android.agoo.common.CallBack;
import org.android.agoo.common.Config;
import org.android.agoo.common.MsgDO;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;
import org.android.agoo.message.MessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class TaobaoRegister {
    private static final int EVENT_ID = 66001;
    static final String PREFERENCES = "Agoo_AppStore";
    static final String PROPERTY_APP_NOTIFICATION_CUSTOM_SOUND = "app_notification_custom_sound";
    static final String PROPERTY_APP_NOTIFICATION_ICON = "app_notification_icon";
    static final String PROPERTY_APP_NOTIFICATION_SOUND = "app_notification_sound";
    static final String PROPERTY_APP_NOTIFICATION_VIBRATE = "app_notification_vibrate";
    private static final String SERVICEID = "agooSend";
    protected static final String TAG = "TaobaoRegister";
    private static boolean isRegisterSuccess;
    private static com.taobao.agoo.a.b mRequestListener;

    @Deprecated
    public static void setBuilderSound(Context context, String str) {
    }

    @Deprecated
    public static void setNotificationIcon(Context context, int i) {
    }

    @Deprecated
    public static void setNotificationSound(Context context, boolean z) {
    }

    @Deprecated
    public static void setNotificationVibrate(Context context, boolean z) {
    }

    private TaobaoRegister() {
        throw new UnsupportedOperationException();
    }

    public static synchronized void setAccsConfigTag(Context context, String str) {
        Config.a = str;
        AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
        if (configByTag == null) {
            throw new RuntimeException("accs config not exist!! please set accs config first!!");
        }
        ALog.i(TAG, "setAccsConfigTag", "config", configByTag.toString());
        com.taobao.accs.client.a.c = configByTag.getAuthCode();
        Config.setAgooAppKey(context, configByTag.getAppKey());
        j.b = configByTag.getAppSecret();
        if (!TextUtils.isEmpty(j.b)) {
            com.taobao.accs.client.a.a = 2;
        }
    }

    @Deprecated
    public static synchronized void register(Context context, String str, String str2, String str3, IRegister iRegister) throws AccsException {
        register(context, "default", str, str2, str3, iRegister);
    }

    public static synchronized void register(Context context, String str, String str2, String str3, String str4, IRegister iRegister) throws AccsException {
        if (context != null) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                if ((context.getApplicationInfo().flags & 2) != 0) {
                    ALog.isUseTlog = false;
                    anet.channel.util.ALog.setUseTlog(false);
                }
                ALog.i(TAG, "register", Constants.KEY_APP_KEY, str2, Constants.KEY_CONFIG_TAG, str);
                Context applicationContext = context.getApplicationContext();
                Config.a = str;
                Config.setAgooAppKey(context, str2);
                j.b = str3;
                if (!TextUtils.isEmpty(str3)) {
                    com.taobao.accs.client.a.a = 2;
                }
                AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
                if (configByTag == null) {
                    new AccsClientConfig.Builder().setAppKey(str2).setAppSecret(str3).setTag(str).build();
                } else {
                    com.taobao.accs.client.a.c = configByTag.getAuthCode();
                }
                com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, str2, str);
                accsInstance.a(applicationContext, str2, str3, str4, new h(applicationContext, context, iRegister, str2, str4, accsInstance));
                return;
            }
        }
        ALog.e(TAG, "register params null", "appkey", str2, Constants.KEY_CONFIG_TAG, str);
    }

    public static synchronized void setAlias(Context context, String str, ICallback iCallback) {
        ALog.i(TAG, "setAlias", "alias", str);
        String strG = Config.g(context);
        String strA = Config.a(context);
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strG) || context == null || TextUtils.isEmpty(str)) {
            if (iCallback != null) {
                iCallback.onFailure("504.1", "input params null!!");
            }
            ALog.e(TAG, "setAlias param null", "appkey", strA, com.taobao.agoo.a.a.b.JSON_DEVICE_ID, strG, "alias", str, "context", context);
            return;
        }
        try {
            if (mRequestListener == null) {
                mRequestListener = new com.taobao.agoo.a.b(context.getApplicationContext());
            }
        } catch (Throwable th) {
            ALog.e(TAG, "setAlias", th, new Object[0]);
        }
        if (com.taobao.agoo.a.b.b.d(str)) {
            ALog.i(TAG, "setAlias already set", "alias", str);
            if (iCallback != null) {
                iCallback.onSuccess();
            }
            return;
        }
        com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, strA, Config.b(context));
        if (com.taobao.agoo.a.b.b.b(context.getPackageName())) {
            GlobalClientInfo.getInstance(context).registerListener("AgooDeviceCmd", mRequestListener);
            String strB = accsInstance.b(context, new ACCSManager.AccsRequest(null, "AgooDeviceCmd", com.taobao.agoo.a.a.a.a(strA, strG, str), null));
            if (TextUtils.isEmpty(strB)) {
                if (iCallback != null) {
                    iCallback.onFailure("504.1", "accs channel disabled!");
                }
            } else if (iCallback != null) {
                iCallback.extra = str;
                mRequestListener.a.put(strB, iCallback);
            }
        } else if (iCallback != null) {
            iCallback.onFailure("504.1", "bindApp first!!");
        }
    }

    public static synchronized void removeAlias(Context context, String str, ICallback iCallback) {
        String strG;
        String strA;
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, new Object[0]);
        try {
            strG = Config.g(context);
            strA = Config.a(context);
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, th, new Object[0]);
        }
        if (!TextUtils.isEmpty(strA) && !TextUtils.isEmpty(strG) && context != null && !TextUtils.isEmpty(str)) {
            com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, strA, Config.b(context));
            if (mRequestListener == null) {
                mRequestListener = new com.taobao.agoo.a.b(context.getApplicationContext());
            }
            GlobalClientInfo.getInstance(context).registerListener("AgooDeviceCmd", mRequestListener);
            String strB = accsInstance.b(context, new ACCSManager.AccsRequest(null, "AgooDeviceCmd", com.taobao.agoo.a.a.a.c(strA, strG, str), null));
            if (TextUtils.isEmpty(strB)) {
                if (iCallback != null) {
                    iCallback.onFailure("504.1", "accs channel disabled!");
                }
            } else if (iCallback != null) {
                mRequestListener.a.put(strB, iCallback);
            }
            return;
        }
        if (iCallback != null) {
            iCallback.onFailure("504.1", "input params null!!");
        }
        ALog.e(TAG, "setAlias param null", "appkey", strA, com.taobao.agoo.a.a.b.JSON_DEVICE_ID, strG, "alias", str, "context", context);
    }

    public static synchronized void removeAlias(Context context, ICallback iCallback) {
        String strG;
        String strH;
        String strA;
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, new Object[0]);
        try {
            strG = Config.g(context);
            strH = Config.h(context);
            strA = Config.a(context);
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, th, new Object[0]);
        }
        if (!TextUtils.isEmpty(strA) && !TextUtils.isEmpty(strG) && context != null && !TextUtils.isEmpty(strH)) {
            com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, strA, Config.b(context));
            if (mRequestListener == null) {
                mRequestListener = new com.taobao.agoo.a.b(context.getApplicationContext());
            }
            GlobalClientInfo.getInstance(context).registerListener("AgooDeviceCmd", mRequestListener);
            String strB = accsInstance.b(context, new ACCSManager.AccsRequest(null, "AgooDeviceCmd", com.taobao.agoo.a.a.a.b(strA, strG, strH), null));
            if (TextUtils.isEmpty(strB)) {
                if (iCallback != null) {
                    iCallback.onFailure("504.1", "accs channel disabled!");
                }
            } else if (iCallback != null) {
                mRequestListener.a.put(strB, iCallback);
            }
            return;
        }
        if (iCallback != null) {
            iCallback.onFailure("504.1", "input params null!!");
        }
        ALog.e(TAG, "setAlias param null", "appkey", strA, com.taobao.agoo.a.a.b.JSON_DEVICE_ID, strG, com.taobao.agoo.a.a.a.JSON_PUSH_USER_TOKEN, strH, "context", context);
    }

    @Deprecated
    public static void bindAgoo(Context context, String str, String str2, CallBack callBack) {
        bindAgoo(context, null);
    }

    @Deprecated
    public static void unBindAgoo(Context context, String str, String str2, CallBack callBack) {
        unbindAgoo(context, null);
    }

    private static synchronized void sendSwitch(Context context, ICallback iCallback, boolean z) {
        String strG;
        String strA;
        String strL;
        try {
            strG = Config.g(context);
            strA = Config.a(context);
            strL = UtilityImpl.l(context);
        } catch (Throwable th) {
            ALog.e(TAG, "sendSwitch", th, new Object[0]);
        }
        if (!TextUtils.isEmpty(strA) && context != null && (!TextUtils.isEmpty(strG) || !TextUtils.isEmpty(strL))) {
            com.taobao.accs.c accsInstance = ACCSManager.getAccsInstance(context, strA, Config.b(context));
            if (mRequestListener == null) {
                mRequestListener = new com.taobao.agoo.a.b(context.getApplicationContext());
            }
            GlobalClientInfo.getInstance(context).registerListener("AgooDeviceCmd", mRequestListener);
            String strB = accsInstance.b(context, new ACCSManager.AccsRequest(null, "AgooDeviceCmd", com.taobao.agoo.a.a.d.a(strA, strG, strL, z), null));
            if (TextUtils.isEmpty(strB)) {
                if (iCallback != null) {
                    iCallback.onFailure("503.2", "accs channel disabled!");
                }
            } else if (iCallback != null) {
                mRequestListener.a.put(strB, iCallback);
            }
            return;
        }
        if (iCallback != null) {
            iCallback.onFailure("503.3", "input params null!!");
        }
        ALog.e(TAG, "sendSwitch param null", "appkey", strA, com.taobao.agoo.a.a.b.JSON_DEVICE_ID, strG, "context", context, com.taobao.agoo.a.a.d.JSON_CMD_ENABLEPUSH, Boolean.valueOf(z));
    }

    public static void bindAgoo(Context context, ICallback iCallback) {
        sendSwitch(context, iCallback, true);
        UTMini.getInstance().commitEvent(EVENT_ID, "bindAgoo", UtilityImpl.l(context));
    }

    public static void unbindAgoo(Context context, ICallback iCallback) {
        sendSwitch(context, iCallback, false);
        UTMini.getInstance().commitEvent(EVENT_ID, MiPushClient.COMMAND_UNREGISTER, UtilityImpl.l(context));
    }

    public static void clickMessage(Context context, String str, String str2) {
        clickMessage(context, str, str2, 0, 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void clickMessage(android.content.Context r21, java.lang.String r22, java.lang.String r23, int r24, long r25) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.agoo.TaobaoRegister.clickMessage(android.content.Context, java.lang.String, java.lang.String, int, long):void");
    }

    public static void dismissMessage(Context context, String str, String str2) throws Throwable {
        NotifManager notifManager = new NotifManager();
        MsgDO msgDO = null;
        try {
            try {
                if (ALog.isPrintLog(ALog.Level.I)) {
                    ALog.i(TAG, "dismissMessage", "msgid", str, "extData", str2);
                }
                if (TextUtils.isEmpty(str)) {
                    ALog.d(TAG, "messageId == null", new Object[0]);
                    return;
                }
                notifManager.init(context);
                MsgDO msgDO2 = new MsgDO();
                try {
                    msgDO2.msgIds = str;
                    msgDO2.extData = str2;
                    msgDO2.messageSource = "accs";
                    msgDO2.msgStatus = "9";
                    AgooFactory agooFactory = new AgooFactory();
                    agooFactory.init(context, notifManager, (MessageService) null);
                    agooFactory.updateMsgStatus(str, "9");
                    notifManager.reportNotifyMessage(msgDO2);
                } catch (Throwable th) {
                    th = th;
                    msgDO = msgDO2;
                    if (msgDO != null) {
                        notifManager.reportNotifyMessage(msgDO);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static void pingApp(Context context, String str, String str2, String str3, int i) {
        NotifManager notifManager = new NotifManager();
        notifManager.init(context);
        notifManager.pingApp(str, str2, str3, i);
    }

    public static void isEnableDaemonServer(Context context, boolean z) {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i(TAG, "isEnableDaemonServer begin,enable=" + z, new Object[0]);
        }
        Config.a(context, z);
    }

    public static void setAgooMsgReceiveService(String str) {
        com.taobao.accs.client.a.b = str;
    }

    public static void setEnv(Context context, @AccsClientConfig.ENV int i) {
        ACCSClient.setEnvironment(context, i);
    }

    @Deprecated
    public static void unregister(Context context, CallBack callBack) {
        unbindAgoo(context, null);
    }

    public static boolean isRegisterSuccess() {
        return isRegisterSuccess;
    }

    public static void setIsRegisterSuccess(boolean z) {
        isRegisterSuccess = z;
    }
}
