package com.xiaomi.push.service.receivers;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.push.i;
import com.sigmob.sdk.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.xm.MiPushAdapter;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MIPushMessageHandler extends PushMessageReceiver {
    private static final String TAG = "MiPush";

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        if (context == null || miPushCommandMessage == null) {
            return;
        }
        try {
            String command = miPushCommandMessage.getCommand();
            List<String> commandArguments = miPushCommandMessage.getCommandArguments();
            String str = null;
            String str2 = (commandArguments == null || commandArguments.size() <= 0) ? null : commandArguments.get(0);
            String str3 = (commandArguments == null || commandArguments.size() <= 1) ? null : commandArguments.get(1);
            if ("register".equals(command)) {
                if (miPushCommandMessage.getResultCode() == 0) {
                    i.l().c(TAG, "mRegId = " + str2);
                    if (!TextUtils.isEmpty(str2)) {
                        i.m().a(context, MiPushAdapter.getMiPush(), str2);
                    } else {
                        i.o().a(MiPushAdapter.getMiPush(), 102, Constants.FAIL, "token is empty");
                    }
                } else {
                    i.o().a(MiPushAdapter.getMiPush(), 104, String.valueOf(miPushCommandMessage.getResultCode()), miPushCommandMessage.getReason());
                    i.m().a(MiPushAdapter.getMiPush(), String.valueOf(miPushCommandMessage.getResultCode()), miPushCommandMessage.getReason());
                }
            } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
                if (miPushCommandMessage.getResultCode() == 0) {
                    i.l().c(TAG, "set alias success, mAlias = " + str2);
                }
            } else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
                if (miPushCommandMessage.getResultCode() == 0) {
                    i.l().c(TAG, "unSet alias success, mAlias = " + str2);
                }
            } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
                if (miPushCommandMessage.getResultCode() == 0) {
                    i.l().c(TAG, "subscribe topic success, mTopic = " + str2);
                }
            } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
                if (miPushCommandMessage.getResultCode() == 0) {
                    i.l().c(TAG, "unSubscribe topic success, mTopic = " + str2);
                }
            } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command) && miPushCommandMessage.getResultCode() == 0) {
                i.l().c(TAG, "mStartTime = " + str2 + " mEndTime = " + str3);
            }
            if (miPushCommandMessage.getResultCode() != 0) {
                if (70000002 == miPushCommandMessage.getResultCode()) {
                    str = "push连接的认证失败";
                } else if (70000001 == miPushCommandMessage.getResultCode()) {
                    str = "由于网络原因导致的push连接失败";
                } else if (70000004 == miPushCommandMessage.getResultCode()) {
                    str = "push内部状态错误，遇到此类错误请联系开发人员";
                } else if (70000003 == miPushCommandMessage.getResultCode()) {
                    str = "客户端的发给PUSH通道的消息格式不合法";
                }
                if (TextUtils.equals(command, "Registration")) {
                    i.o().a(MiPushAdapter.getMiPush(), 104, String.valueOf(miPushCommandMessage.getResultCode()), str);
                }
                i.l().c(TAG, command + " -> " + str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        JSONObject jSONObjectA;
        super.onReceivePassThroughMessage(context, miPushMessage);
        com.bytedance.push.d.a aVarL = i.l();
        StringBuilder sb = new StringBuilder();
        sb.append("Get MiPush Message ");
        sb.append(miPushMessage != null ? miPushMessage.toString() : String.valueOf((char[]) null));
        aVarL.a(TAG, sb.toString());
        if (context == null || miPushMessage == null) {
            return;
        }
        try {
            String content = miPushMessage.getContent();
            if (TextUtils.isEmpty(content) || (jSONObjectA = i.m().a(content.getBytes(), true)) == null) {
                return;
            }
            jSONObjectA.put("pass_through", miPushMessage.getPassThrough());
            i.n().a(jSONObjectA.toString(), MiPushAdapter.getMiPush(), (String) null);
        } catch (Throwable unused) {
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        JSONObject jSONObjectA;
        super.onNotificationMessageClicked(context, miPushMessage);
        com.bytedance.push.d.a aVarL = i.l();
        StringBuilder sb = new StringBuilder();
        sb.append("Get MiPush Message ");
        sb.append(miPushMessage != null ? miPushMessage.toString() : String.valueOf((char[]) null));
        aVarL.c(TAG, sb.toString());
        if (context == null || miPushMessage == null) {
            return;
        }
        try {
            String content = miPushMessage.getContent();
            if (TextUtils.isEmpty(content) || (jSONObjectA = i.m().a(content.getBytes(), true)) == null) {
                return;
            }
            jSONObjectA.put("pass_through", miPushMessage.getPassThrough());
            i.m().a(context, jSONObjectA.toString(), MiPushAdapter.getMiPush(), (String) null);
        } catch (Throwable unused) {
        }
    }

    @Override // com.xiaomi.mipush.sdk.PushMessageReceiver
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
        i.l().c(TAG, "onNotificationMessageArrived -> " + miPushMessage);
    }
}
