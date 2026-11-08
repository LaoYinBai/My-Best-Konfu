package com.huawei.hms.aaid.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.aaid.init.b;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PluginUtil {
    private static final String ACTION_MASSAGING_EVENT = "com.huawei.push.action.MESSAGING_EVENT";
    private static final String MESSAGE_BODY = "message_body";
    private static final String MESSAGE_ERROR = "error";
    private static final String MESSAGE_ID = "message_id";
    private static final String MESSAGE_PROXY_TYPE = "message_proxy_type";
    private static final String MESSAGE_TYPE = "message_type";
    private static final String MSGTYPE_NEW_TOKEN = "new_token";
    private static final String MSGTYPE_RECEIVED = "received_message";
    private static final String MSGTYPE_SEVER_DELETED = "server_deleted_message";
    public static final int PROXY_TYPE_FCM = 1;
    private static final String PUSH_KEY_DEVICE_TOKEN = "device_token";
    private static final String TAG = "PluginUtil";

    private PluginUtil() {
    }

    public static boolean onNewToken(Context context, String str) {
        HMSLog.i(TAG, "onNewToken");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(ACTION_MASSAGING_EVENT);
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("message_type", MSGTYPE_NEW_TOKEN);
        bundle.putString("device_token", str);
        return new b().a(context, bundle, intent);
    }

    public static boolean onNewTokenError(Context context, int i) {
        HMSLog.i(TAG, "onNewTokenError");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(ACTION_MASSAGING_EVENT);
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("message_type", MSGTYPE_NEW_TOKEN);
        bundle.putInt("error", i);
        return new b().a(context, bundle, intent);
    }

    public static boolean onMessageReceived(Context context, String str, byte[] bArr, int i) {
        HMSLog.i(TAG, "onMessageReceived");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(ACTION_MASSAGING_EVENT);
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE_ID, str);
        bundle.putByteArray("message_body", bArr);
        bundle.putInt(MESSAGE_PROXY_TYPE, i);
        bundle.putString("message_type", MSGTYPE_RECEIVED);
        return new b().a(context, bundle, intent);
    }

    public static boolean onDeletedMessages(Context context, int i) {
        HMSLog.i(TAG, "onDeletedMessages");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(ACTION_MASSAGING_EVENT);
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putInt(MESSAGE_PROXY_TYPE, i);
        bundle.putString("message_type", MSGTYPE_SEVER_DELETED);
        return new b().a(context, bundle, intent);
    }
}
