package org.android.agoo.control;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import javax.crypto.spec.SecretKeySpec;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.Config;
import org.android.agoo.common.MsgDO;
import org.android.agoo.message.MessageService;
import org.android.agoo.service.SendMessage;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class AgooFactory {
    private static final String DEAL_MESSAGE = "accs.msgRecevie";
    private static final String TAG = "AgooFactory";

    @SuppressLint({"CI_StaticFieldLeak"})
    private static Context mContext;
    protected NotifManager notifyManager = null;
    private MessageService messageService = null;

    public void init(Context context, NotifManager notifManager, MessageService messageService) {
        mContext = context.getApplicationContext();
        this.notifyManager = notifManager;
        if (this.notifyManager == null) {
            this.notifyManager = new NotifManager();
        }
        this.notifyManager.init(mContext);
        this.messageService = messageService;
        if (this.messageService == null) {
            this.messageService = new MessageService();
        }
        this.messageService.a(mContext);
    }

    public void saveMsg(byte[] bArr) {
        saveMsg(bArr, (String) null);
    }

    public void saveMsg(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        ThreadPoolExecutorFactory.execute(new org.android.agoo.control.a(this, bArr, str));
    }

    public void msgRecevie(byte[] bArr, String str) {
        msgRecevie(bArr, str, (TaoBaseService.ExtraInfo) null);
    }

    public void msgRecevie(byte[] bArr, String str, TaoBaseService.ExtraInfo extraInfo) {
        try {
            if (ALog.isPrintLog(ALog.Level.I)) {
                ALog.i(TAG, "into--[AgooFactory,msgRecevie]:messageSource=" + str, new Object[0]);
            }
            ThreadPoolExecutorFactory.execute(new org.android.agoo.control.b(this, bArr, str, extraInfo));
        } catch (Throwable th) {
            ALog.e(TAG, "serviceImpl init task fail:" + th.toString(), new Object[0]);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:42|(2:44|(3:46|62|84)(1:47))(1:48)|(1:50)|79|51|(1:53)|58|(1:60)(1:61)|62|84) */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0200, code lost:
    
        if (com.taobao.accs.utl.ALog.isPrintLog(com.taobao.accs.utl.ALog.Level.I) != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0202, code lost:
    
        com.taobao.accs.utl.ALog.i(org.android.agoo.control.AgooFactory.TAG, "agoo msg has no time", new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.Bundle msgReceiverPreHandler(byte[] r30, java.lang.String r31, com.taobao.accs.base.TaoBaseService.ExtraInfo r32, boolean r33) throws org.json.JSONException, java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instructions count: 762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.control.AgooFactory.msgReceiverPreHandler(byte[], java.lang.String, com.taobao.accs.base.TaoBaseService$ExtraInfo, boolean):android.os.Bundle");
    }

    public static String parseEncryptedMsg(String str) {
        byte[] bArrA;
        try {
            String strA = Config.a(mContext);
            String strD = UtilityImpl.a("ACCS_SDK", mContext) ? UtilityImpl.d("ACCS_SDK", mContext) : com.taobao.accs.utl.j.b(mContext);
            if (TextUtils.isEmpty(strD)) {
                strD = com.taobao.accs.utl.j.b(mContext);
            }
            if (com.taobao.accs.client.a.a != 2) {
                bArrA = null;
            } else if (!TextUtils.isEmpty(com.taobao.accs.utl.j.b)) {
                bArrA = org.android.agoo.common.a.a(com.taobao.accs.utl.j.b.getBytes("utf-8"), (strA + strD).getBytes("utf-8"));
            } else {
                ALog.e(TAG, "getAppsign secret null", new Object[0]);
                bArrA = null;
            }
            if (bArrA != null && bArrA.length > 0) {
                return new String(org.android.agoo.common.a.a(Base64.decode(str, 8), new SecretKeySpec(org.android.agoo.common.a.a(bArrA), "AES"), org.android.agoo.common.a.a(strA.getBytes("utf-8"))), "utf-8");
            }
            ALog.e(TAG, "aesDecrypt key is null!", new Object[0]);
            return null;
        } catch (Throwable th) {
            ALog.e(TAG, "parseEncryptedMsg failure: ", th, new Object[0]);
            return null;
        }
    }

    public void reportCacheMsg() {
        try {
            ThreadPoolExecutorFactory.execute(new c(this));
        } catch (Throwable th) {
            ALog.e(TAG, "reportCacheMsg fail:" + th.toString(), new Object[0]);
        }
    }

    public void updateMsg(byte[] bArr, boolean z) {
        ThreadPoolExecutorFactory.execute(new d(this, bArr, z));
    }

    public void updateNotifyMsg(String str, String str2) {
        ThreadPoolExecutorFactory.execute(new e(this, str, str2));
    }

    public void updateMsgStatus(String str, String str2) throws Throwable {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (ALog.isPrintLog(ALog.Level.I)) {
                    ALog.i(TAG, "updateNotifyMsg begin,messageId=" + str + ",status=" + str2 + ",reportTimes=" + Config.e(mContext), new Object[0]);
                }
                if (TextUtils.equals(str2, MessageService.MSG_ACCS_NOTIFY_CLICK)) {
                    this.messageService.a(str, MessageService.MSG_DB_NOTIFY_CLICK);
                } else if (TextUtils.equals(str2, MessageService.MSG_ACCS_NOTIFY_DISMISS)) {
                    this.messageService.a(str, MessageService.MSG_DB_NOTIFY_DISMISS);
                }
            }
        } catch (Throwable th) {
            ALog.e(TAG, "updateNotifyMsg e=" + th.toString(), new Object[0]);
        }
    }

    private static final boolean checkPackage(Context context, String str) {
        return context.getPackageManager().getApplicationInfo(str, 0) != null;
    }

    private static Bundle getFlag(long j, MsgDO msgDO) {
        Bundle bundle = new Bundle();
        try {
            char[] charArray = Long.toBinaryString(j).toCharArray();
            if (charArray != null && 8 <= charArray.length) {
                if (8 <= charArray.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(Integer.parseInt("" + charArray[1] + charArray[2] + charArray[3] + charArray[4], 2));
                    bundle.putString(AgooConstants.MESSAGE_ENCRYPTED, sb.toString());
                    if (charArray[6] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_REPORT, MessageService.MSG_DB_NOTIFY_REACHED);
                        msgDO.reportStr = MessageService.MSG_DB_NOTIFY_REACHED;
                    }
                    if (charArray[7] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_NOTIFICATION, MessageService.MSG_DB_NOTIFY_REACHED);
                    }
                }
                if (9 <= charArray.length && charArray[8] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_HAS_TEST, MessageService.MSG_DB_NOTIFY_REACHED);
                }
                if (10 <= charArray.length && charArray[9] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_DUPLICATE, MessageService.MSG_DB_NOTIFY_REACHED);
                }
                if (11 <= charArray.length && charArray[10] == '1') {
                    bundle.putInt(AgooConstants.MESSAGE_POPUP, 1);
                }
            }
        } catch (Throwable unused) {
        }
        return bundle;
    }

    private void sendMsgToBussiness(Context context, String str, Bundle bundle, boolean z, String str2, TaoBaseService.ExtraInfo extraInfo) {
        Intent intent = new Intent();
        intent.setAction(AgooConstants.INTENT_FROM_AGOO_MESSAGE);
        intent.setPackage(str);
        intent.putExtras(bundle);
        intent.putExtra(AgooConstants.MESSAGE_TYPE, "common-push");
        intent.putExtra(AgooConstants.MESSAGE_SOURCE, str2);
        intent.addFlags(32);
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable(AgooConstants.MESSAGE_ACCS_EXTRA, extraInfo);
            intent.putExtra(AgooConstants.MESSAGE_AGOO_BUNDLE, bundle2);
        } catch (Throwable th) {
            ALog.e(TAG, "sendMsgToBussiness", th, new Object[0]);
        }
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i(TAG, "sendMsgToBussiness intent:" + bundle.toString() + ",utdid=" + com.taobao.accs.utl.j.b(context) + ",pack=" + str + ",agooFlag=" + z, new Object[0]);
        }
        if (z) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, DEAL_MESSAGE, com.taobao.accs.utl.j.b(context), "agooMsg", AgooConstants.ACK_PACK_ERROR);
            sendMsgByBindService(str, intent);
        } else {
            intent.setClassName(str, com.taobao.accs.client.a.a(str));
            com.taobao.accs.a.a.a(context, intent);
        }
    }

    private void sendMsgByBindService(String str, Intent intent) {
        try {
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d(TAG, "onHandleMessage current tid:" + Thread.currentThread().getId(), new Object[0]);
            }
            ThreadPoolExecutorFactory.execute(new b(str, intent));
        } catch (Throwable th) {
            ALog.e(TAG, "sendMsgByBindService error >>", th, new Object[0]);
        }
    }

    class a implements ServiceConnection {
        private Intent b;
        private String c;
        private SendMessage d;
        private ServiceConnection e = this;

        public a(String str, Intent intent) {
            this.c = str;
            this.b = intent;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ALog.d(AgooFactory.TAG, "MessageConnection disConnected", new Object[0]);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ALog.d(AgooFactory.TAG, "MessageConnection conneted:" + componentName, new Object[0]);
            this.d = SendMessage.Stub.asInterface(iBinder);
            ALog.d(AgooFactory.TAG, "onConnected current tid:" + Thread.currentThread().getId(), new Object[0]);
            ALog.d(AgooFactory.TAG, "MessageConnection sent:" + this.b, new Object[0]);
            if (this.d != null) {
                ThreadPoolExecutorFactory.execute(new f(this));
            }
        }
    }

    class b implements Runnable {
        private String b;
        private Intent c;

        public b(String str, Intent intent) {
            this.b = str;
            this.c = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!com.bytedance.push.i.m().a(AgooFactory.mContext, this.b)) {
                    com.bytedance.push.i.l().a(AgooFactory.TAG, "run: prevent start : " + this.b);
                    com.ss.android.push.c.a(AgooFactory.mContext, this.b, "umeng", new JSONObject[0]);
                    return;
                }
                ALog.d(AgooFactory.TAG, "running tid:" + Thread.currentThread().getId() + ",pack=" + this.b, new Object[0]);
                AgooFactory.mContext.sendBroadcast(this.c);
                StringBuilder sb = new StringBuilder();
                sb.append("SendMessageRunnable for accs,pack=");
                sb.append(this.b);
                ALog.d(AgooFactory.TAG, sb.toString(), new Object[0]);
                try {
                    this.c.setPackage(this.b);
                    this.c.setAction(AgooConstants.INTENT_FROM_AGOO_MESSAGE);
                    com.taobao.accs.a.a.a(AgooFactory.mContext, this.c);
                } catch (Throwable unused) {
                }
                Intent intent = new Intent(AgooConstants.BINDER_MSGRECEIVER_ACTION);
                intent.setPackage(this.b);
                ALog.d(AgooFactory.TAG, "this message pack:" + this.b, new Object[0]);
                ALog.d(AgooFactory.TAG, "start to service...", new Object[0]);
                AgooFactory agooFactory = AgooFactory.this;
                agooFactory.getClass();
                boolean zBindService = AgooFactory.mContext.bindService(intent, agooFactory.new a(this.c.getStringExtra(AgooConstants.MESSAGE_ID), this.c), 17);
                ALog.d(AgooFactory.TAG, "start service ret:" + zBindService, new Object[0]);
                if (zBindService) {
                    return;
                }
                ALog.d(AgooFactory.TAG, "SendMessageRunnable is error", new Object[0]);
            } catch (Throwable th) {
                ALog.e(AgooFactory.TAG, "SendMessageRunnable is error,e=" + th.toString(), new Object[0]);
            }
        }
    }
}
