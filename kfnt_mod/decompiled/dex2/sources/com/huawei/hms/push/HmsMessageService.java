package com.huawei.hms.push;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.push.c.a.a.c;
import com.huawei.hms.support.api.push.c.a.b;
import com.huawei.hms.support.log.HMSLog;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HmsMessageService extends Service {
    private final Messenger a = new Messenger(new a());

    public void onDeletedMessages() {
    }

    public void onMessageDelivered(String str, Exception exc) {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onSendError(String str, Exception exc) {
    }

    public void onTokenError(Exception exc) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        HMSLog.i("HmsMessageService", "start to bind");
        return this.a.getBinder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) throws JSONException {
        HMSLog.i("HmsMessageService", "start to command , startId = " + i2);
        a(intent);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        HMSLog.i("HmsMessageService", "start to destroy");
        super.onDestroy();
    }

    @SuppressLint({"HandlerLeak"})
    private class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws JSONException {
            if (message == null) {
                HMSLog.e("HmsMessageService", "receive message is null");
                return;
            }
            HMSLog.i("HmsMessageService", "handle message start ");
            Bundle data = message.getData();
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtra("message_id", data.getString("message_id"));
                intent.putExtra(RemoteMessageConst.MSGTYPE, data.getString(RemoteMessageConst.MSGTYPE));
                intent.putExtra(RemoteMessageConst.MSGBODY, data.getByteArray(RemoteMessageConst.MSGBODY));
                intent.putExtra(RemoteMessageConst.DEVICE_TOKEN, data.getString(RemoteMessageConst.DEVICE_TOKEN));
                intent.putExtra(CampaignEx.JSON_NATIVE_VIDEO_ERROR, data.getInt(CampaignEx.JSON_NATIVE_VIDEO_ERROR));
                intent.putExtra("transaction_id", data.getString("transaction_id"));
                intent.putExtra(RemoteMessageConst.INPUT_TYPE, data.getInt(RemoteMessageConst.INPUT_TYPE, -1));
                HmsMessageService.this.a(intent);
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) throws JSONException {
        if (intent == null) {
            HMSLog.e("HmsMessageService", "receive message is null");
            return;
        }
        String stringExtra = intent.getStringExtra("message_id");
        String stringExtra2 = intent.getStringExtra(RemoteMessageConst.MSGTYPE);
        String stringExtra3 = intent.getStringExtra("transaction_id");
        if ("new_token".equals(stringExtra2)) {
            HMSLog.i("HmsMessageService", "onNewToken");
            a(intent, stringExtra3);
            return;
        }
        if ("received_message".equals(stringExtra2)) {
            HMSLog.i("HmsMessageService", "onMessageReceived, message id:" + stringExtra);
            a("push.receiveMessage", stringExtra, com.huawei.hms.push.constant.a.SUCCESS.a());
            onMessageReceived(new RemoteMessage(b(intent)));
            return;
        }
        if ("sent_message".equals(stringExtra2)) {
            a(stringExtra3, stringExtra);
            return;
        }
        if ("send_error".equals(stringExtra2)) {
            b(stringExtra3, stringExtra, intent.getIntExtra(CampaignEx.JSON_NATIVE_VIDEO_ERROR, com.huawei.hms.push.constant.a.ERROR_UNKNOWN.a()));
            return;
        }
        if ("delivery".equals(stringExtra2)) {
            int intExtra = intent.getIntExtra(CampaignEx.JSON_NATIVE_VIDEO_ERROR, com.huawei.hms.push.constant.a.ERROR_APP_SERVER_NOT_ONLINE.a());
            HMSLog.i("HmsMessageService", "onMessageDelivery, message id:" + stringExtra + ", status:" + intExtra + ", transactionId: " + stringExtra3);
            a("push.deliveryMessage", stringExtra3, intExtra);
            onMessageDelivered(stringExtra, new SendException(intExtra));
            return;
        }
        if ("server_deleted_message".equals(stringExtra2)) {
            HMSLog.i("HmsMessageService", "delete message, message id:" + stringExtra);
            onDeletedMessages();
            return;
        }
        if ("batchSent".equals(stringExtra2)) {
            c(intent);
            return;
        }
        HMSLog.e("HmsMessageService", "Receive unknown message: " + stringExtra2);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Context applicationContext = getApplicationContext();
        c cVar = new c(applicationContext, AaidIdConstant.PushClientSelfInfo.FILE_NAME);
        cVar.a("reqTokenTime", Long.valueOf(System.currentTimeMillis()));
        if (!str.equals(b.a(applicationContext, AaidIdConstant.PushClientSelfInfo.FILE_NAME))) {
            HMSLog.i("HmsMessageService", "receive a token, refresh the local token");
            cVar.c("token_info");
        }
        b.a(applicationContext, AaidIdConstant.PushClientSelfInfo.FILE_NAME, str);
    }

    private Bundle b(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString("message_id", intent.getStringExtra("message_id"));
        bundle.putByteArray(RemoteMessageConst.MSGBODY, intent.getByteArrayExtra(RemoteMessageConst.MSGBODY));
        bundle.putString(RemoteMessageConst.DEVICE_TOKEN, intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN));
        if (1 == intent.getIntExtra(RemoteMessageConst.INPUT_TYPE, -1)) {
            bundle.putInt(RemoteMessageConst.INPUT_TYPE, 1);
        }
        return bundle;
    }

    private void a(Intent intent, String str) {
        int intExtra = intent.getIntExtra(CampaignEx.JSON_NATIVE_VIDEO_ERROR, com.huawei.hms.push.constant.a.SUCCESS.a());
        HMSLog.i("HmsMessageService", "doOnNewToken:transactionId = " + str + " , internalCode = " + intExtra);
        a("push.onNewToken", str, intExtra);
        if (intExtra == com.huawei.hms.push.constant.a.SUCCESS.a()) {
            a(intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN));
            onNewToken(intent.getStringExtra(RemoteMessageConst.DEVICE_TOKEN));
        } else {
            onTokenError(new BaseException(intExtra));
        }
    }

    private void a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "null";
        }
        com.huawei.hms.push.utils.b.a(getApplicationContext(), str, str2, i);
    }

    private void c(Intent intent) throws JSONException {
        HMSLog.i("HmsMessageService", "parse batch response.");
        String stringExtra = intent.getStringExtra("batchMsgbody");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(stringExtra);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String strOptString = jSONObject.optString("transactionId");
                String strOptString2 = jSONObject.optString(RemoteMessageConst.MSGID);
                int iOptInt = jSONObject.optInt("ret", com.huawei.hms.push.constant.a.ERROR_UNKNOWN.a());
                if (com.huawei.hms.push.constant.a.SUCCESS.a() == iOptInt) {
                    a(strOptString, strOptString2);
                } else {
                    b(strOptString, strOptString2, iOptInt);
                }
            }
        } catch (JSONException unused) {
            HMSLog.w("HmsMessageService", "parse batch response failed.");
        }
    }

    private void a(String str, String str2) {
        HMSLog.i("HmsMessageService", "onMessageSent, message id:" + str2 + ", transactionId: " + str);
        a("push.sendMessageRet", str, com.huawei.hms.push.constant.a.SUCCESS.a());
        onMessageSent(str2);
    }

    private void b(String str, String str2, int i) {
        HMSLog.i("HmsMessageService", "onSendError, message id:" + str2 + " error:" + i + ", transactionId: " + str);
        a("push.sendMessageRet", str, i);
        onSendError(str2, new SendException(i));
    }
}
