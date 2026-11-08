package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationProxyBroadcastReceiver extends BroadcastReceiver {
    public static final int EXTRA_ACTION_CLICK = 10;
    public static final int EXTRA_ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MESSAGE_ID = "MESSAGE_ID";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final String EXTRA_KEY_NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String EXTRA_KEY_TASK_ID = "TASK_ID";
    public static final int LOCAL_ACTION_CLICK = 12;
    private static final String a = "com.umeng.message.NotificationProxyBroadcastReceiver";
    private UHandler b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_KEY_MSG);
        try {
            int intExtra = intent.getIntExtra(EXTRA_KEY_ACTION, -1);
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, String.format("onReceive[msg=%s, action=%d]", stringExtra, Integer.valueOf(intExtra)));
            if (intExtra == 12) {
                a(context);
                return;
            }
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            int intExtra2 = intent.getIntExtra(EXTRA_KEY_NOTIFICATION_ID, -1);
            uMessage.message_id = intent.getStringExtra(EXTRA_KEY_MESSAGE_ID);
            uMessage.task_id = intent.getStringExtra(EXTRA_KEY_TASK_ID);
            switch (intExtra) {
                case 10:
                    UMLog uMLog2 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(a, 2, "点击通知");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgClick(uMessage);
                    this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                    if (this.b != null) {
                        uMessage.clickOrDismiss = true;
                        this.b.handleMessage(context, uMessage);
                        break;
                    }
                    break;
                case 11:
                    UMLog uMLog3 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(a, 2, "忽略通知");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgDismissed(uMessage);
                    this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                    if (this.b != null) {
                        uMessage.clickOrDismiss = false;
                        this.b.handleMessage(context, uMessage);
                        break;
                    }
                    break;
            }
            MessageNotificationQueue.getInstance().remove(new UNotificationItem(intExtra2, uMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "找不到应用: " + context.getPackageName());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        context.startActivity(launchIntentForPackage);
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 2, "打开应用: " + context.getPackageName());
    }
}
