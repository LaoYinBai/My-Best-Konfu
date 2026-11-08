package org.android.agoo.control;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.t;
import com.taobao.accs.utl.w;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.Config;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public abstract class BaseIntentService extends Service {
    private static final String TAG = "BaseIntentService";
    private static boolean isBinded = false;
    private static final String msgStatus = "4";
    private AgooFactory agooFactory;
    private MessageService messageService;
    private NotifManager notifyManager;
    private Context mContext = null;
    private Messenger messenger = new Messenger(new g(this));

    protected abstract void onError(Context context, String str);

    protected abstract void onMessage(Context context, Intent intent);

    protected abstract void onRegistered(Context context, String str);

    protected void onUserCommand(Context context, Intent intent) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (t.c() && w.a(this) && !isBinded) {
            isBinded = true;
            getApplicationContext().bindService(new Intent(getApplication(), getClass()), new i(this), 1);
        }
        return this.messenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ThreadPoolExecutorFactory.execute(new j(this));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        ThreadPoolExecutorFactory.execute(new k(this, intent));
        return 2;
    }

    protected void onHandleIntent(Intent intent) {
        this.mContext = getApplicationContext();
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        String agooCommand = IntentUtil.getAgooCommand(this.mContext);
        String thirdPushCommand = IntentUtil.getThirdPushCommand(this.mContext);
        ALog.i(TAG, "onHandleIntent,action=" + action + ",agooCommand=" + agooCommand + ",mipushCommand=" + thirdPushCommand, new Object[0]);
        try {
            try {
                if (TextUtils.equals(action, agooCommand)) {
                    String stringExtra = intent.getStringExtra("command");
                    ALog.d(TAG, "actionCommand --->[" + stringExtra + "]", new Object[0]);
                    if (TextUtils.equals(stringExtra, AgooConstants.AGOO_COMMAND_MESSAGE_READED) || TextUtils.equals(stringExtra, AgooConstants.AGOO_COMMAND_MESSAGE_DELETED)) {
                        onUserCommand(this.mContext, intent);
                    }
                } else if (TextUtils.equals(action, thirdPushCommand)) {
                    String stringExtra2 = intent.getStringExtra("command");
                    String stringExtra3 = intent.getStringExtra(AgooConstants.THIRD_PUSH_ID);
                    if (TextUtils.equals(stringExtra2, AgooConstants.AGOO_COMMAND_MIPUSHID_REPORT)) {
                        this.notifyManager.reportThirdPushToken(stringExtra3, "MI_TOKEN", false);
                    } else if (TextUtils.equals(stringExtra2, AgooConstants.AGOO_COMMAND_HUAWEIPUSHID_REPORT)) {
                        ALog.d(TAG, "HW_TOKEN report begin..regid=" + stringExtra3, new Object[0]);
                        this.notifyManager.reportThirdPushToken(stringExtra3, "HW_TOKEN", false);
                    } else if (TextUtils.equals(stringExtra2, AgooConstants.AGOO_COMMAND_GCMIPUSHID_REPORT)) {
                        ALog.i(TAG, "GCM_TOKEN report begin..regid=" + stringExtra3, new Object[0]);
                        this.notifyManager.reportThirdPushToken(stringExtra3, AgooConstants.MESSAGE_SYSTEM_SOURCE_GCM, false);
                    }
                } else if (action.equals(AgooConstants.INTENT_FROM_AGOO_MESSAGE)) {
                    handleRemoteMessage(this.mContext, intent);
                } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    handleRemovePackage(this.mContext, intent);
                } else if (TextUtils.equals(action, AgooConstants.INTENT_FROM_AGOO_REPORT) || TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE") || TextUtils.equals(action, "android.intent.action.BOOT_COMPLETED") || TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED") || TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED") || TextUtils.equals(action, "android.intent.action.USER_PRESENT") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_CONNECTED") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_DISCONNECTED")) {
                    try {
                        ALog.i(TAG, "is report cache msg,Config.isReportCacheMsg(mContext)=" + Config.c(this.mContext), new Object[0]);
                        if (Config.c(this.mContext) && com.taobao.accs.utl.j.c(this.mContext)) {
                            Config.d(this.mContext);
                            this.agooFactory.reportCacheMsg();
                            this.messageService.a();
                        }
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (ALog.isPrintLog(ALog.Level.I)) {
                            ALog.i(TAG, "is clear all msg=" + Config.b(this.mContext, jCurrentTimeMillis), new Object[0]);
                        }
                        if (Config.b(this.mContext, jCurrentTimeMillis)) {
                            Config.a(this.mContext, jCurrentTimeMillis);
                            this.messageService.a();
                        }
                    } catch (Throwable th) {
                        ALog.e(TAG, "reportCacheMsg", th, new Object[0]);
                    }
                }
            } catch (Throwable th2) {
                if (ALog.isPrintLog(ALog.Level.E)) {
                    ALog.e(TAG, "onHandleIntent deal error", th2, new Object[0]);
                }
            }
        } finally {
            com.taobao.accs.client.a.g.incrementAndGet();
        }
    }

    private final void handleRemovePackage(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        Uri data = intent.getData();
        String schemeSpecificPart = data != null ? data.getSchemeSpecificPart() : null;
        if (TextUtils.isEmpty(schemeSpecificPart)) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d(TAG, "handleRemovePackage---->[replacing:" + booleanExtra + "],uninstallPack=" + schemeSpecificPart, new Object[0]);
        }
        if (booleanExtra) {
            return;
        }
        this.notifyManager.doUninstall(schemeSpecificPart, booleanExtra);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5 A[Catch: Throwable -> 0x0358, TryCatch #11 {Throwable -> 0x0358, blocks: (B:3:0x0004, B:28:0x009b, B:30:0x00a5, B:31:0x00ee, B:33:0x0117, B:35:0x0122, B:37:0x0136, B:39:0x0140, B:42:0x0154, B:47:0x016e, B:52:0x01d8, B:54:0x01e0, B:56:0x01e8, B:57:0x020d, B:59:0x0219, B:61:0x0221, B:86:0x02d5, B:87:0x0323, B:89:0x033e, B:93:0x0354, B:92:0x034a, B:71:0x0274, B:73:0x027c, B:51:0x01bb, B:45:0x0163, B:27:0x007d, B:48:0x0173, B:62:0x0247, B:64:0x0253, B:66:0x025b, B:68:0x0267), top: B:123:0x0004, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0117 A[Catch: Throwable -> 0x0358, TryCatch #11 {Throwable -> 0x0358, blocks: (B:3:0x0004, B:28:0x009b, B:30:0x00a5, B:31:0x00ee, B:33:0x0117, B:35:0x0122, B:37:0x0136, B:39:0x0140, B:42:0x0154, B:47:0x016e, B:52:0x01d8, B:54:0x01e0, B:56:0x01e8, B:57:0x020d, B:59:0x0219, B:61:0x0221, B:86:0x02d5, B:87:0x0323, B:89:0x033e, B:93:0x0354, B:92:0x034a, B:71:0x0274, B:73:0x027c, B:51:0x01bb, B:45:0x0163, B:27:0x007d, B:48:0x0173, B:62:0x0247, B:64:0x0253, B:66:0x025b, B:68:0x0267), top: B:123:0x0004, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x016e A[Catch: Throwable -> 0x0358, TRY_LEAVE, TryCatch #11 {Throwable -> 0x0358, blocks: (B:3:0x0004, B:28:0x009b, B:30:0x00a5, B:31:0x00ee, B:33:0x0117, B:35:0x0122, B:37:0x0136, B:39:0x0140, B:42:0x0154, B:47:0x016e, B:52:0x01d8, B:54:0x01e0, B:56:0x01e8, B:57:0x020d, B:59:0x0219, B:61:0x0221, B:86:0x02d5, B:87:0x0323, B:89:0x033e, B:93:0x0354, B:92:0x034a, B:71:0x0274, B:73:0x027c, B:51:0x01bb, B:45:0x0163, B:27:0x007d, B:48:0x0173, B:62:0x0247, B:64:0x0253, B:66:0x025b, B:68:0x0267), top: B:123:0x0004, inners: #0, #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void handleRemoteMessage(android.content.Context r29, android.content.Intent r30) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.control.BaseIntentService.handleRemoteMessage(android.content.Context, android.content.Intent):void");
    }

    private final String getTrace(Context context, long j) {
        String str = TextUtils.isEmpty(null) ? "unknow" : null;
        String str2 = TextUtils.isEmpty(null) ? "unknow" : null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("appkey");
        stringBuffer.append("|");
        stringBuffer.append(j);
        stringBuffer.append("|");
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append("|");
        stringBuffer.append(str);
        stringBuffer.append("|");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static final void runIntentInService(Context context, Intent intent, String str) {
        try {
            intent.setClassName(context, str);
            context.startService(intent);
        } catch (Throwable th) {
            ALog.w(TAG, "runIntentInService", th, new Object[0]);
        }
    }
}
