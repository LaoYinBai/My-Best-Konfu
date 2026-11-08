package com.umeng.message;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Process;
import android.os.RemoteException;
import com.ss.android.b.a.b;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.pushmanager.a.a;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.proguard.o;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.onMessageSendListener;
import com.umeng.message.common.d;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.provider.a;
import com.umeng.message.service.UMJobIntentService;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengMessageCallbackHandlerService extends UMJobIntentService {
    public static final String TAG = "com.umeng.message.UmengMessageCallbackHandlerService";
    private Context a = this;

    @Override // com.umeng.message.service.JobIntentService, android.app.Service
    public void onCreate() {
        try {
            super.onCreate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    protected void a(Intent intent) throws Throwable {
        UHandler messageHandler;
        String strA = h.a(this.a, Process.myPid());
        UMLog uMLog = UMConfigure.umDebugLog;
        UMLog.mutlInfo(TAG, 2, "进程名：" + strA);
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION)) {
            MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService register callback");
            try {
                final String stringExtra = intent.getStringExtra(MsgConstant.KEY_REGISTRATION_ID);
                boolean booleanExtra = intent.getBooleanExtra("status", false);
                UMLog uMLog2 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TAG, 2, "注册：" + stringExtra + "，状态：" + booleanExtra);
                IUmengRegisterCallback registerCallback = PushAgent.getInstance(this.a).getRegisterCallback();
                if (booleanExtra) {
                    d.a(new Runnable() { // from class: com.umeng.message.UmengMessageCallbackHandlerService.1
                        @Override // java.lang.Runnable
                        public void run() throws Throwable {
                            try {
                                String deviceToken = MessageSharedPrefs.getInstance(UmengMessageCallbackHandlerService.this.a).getDeviceToken();
                                if (stringExtra != null && deviceToken != null && !stringExtra.equals(deviceToken)) {
                                    MessageSharedPrefs.getInstance(UmengMessageCallbackHandlerService.this.a).setHasResgister(false);
                                    MessageSharedPrefs.getInstance(UmengMessageCallbackHandlerService.this.a).setDeviceToken(stringExtra);
                                    UmengMessageCallbackHandlerService.this.a(UmengMessageCallbackHandlerService.this.a, stringExtra);
                                    ContentResolver contentResolver = UmengMessageCallbackHandlerService.this.a.getContentResolver();
                                    a.a(UmengMessageCallbackHandlerService.this.a);
                                    contentResolver.delete(a.e, (String) null, (String[]) null);
                                    MessageSharedPrefs.getInstance(UmengMessageCallbackHandlerService.this.a).resetTags();
                                }
                            } catch (Exception unused) {
                            }
                            UTrack.getInstance(UmengMessageCallbackHandlerService.this.a).a();
                            PushAgent.getInstance(UmengMessageCallbackHandlerService.this.a).onAppStart();
                        }
                    });
                    if (registerCallback != null) {
                        registerCallback.onSuccess(stringExtra);
                    }
                } else if (registerCallback != null) {
                    registerCallback.onFailure(intent.getStringExtra(o.at), intent.getStringExtra("s1"));
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (intent.getAction().equals(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION)) {
            MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService enable callback");
            try {
                boolean booleanExtra2 = intent.getBooleanExtra("status", false);
                IUmengCallback callback = PushAgent.getInstance(this.a).getCallback();
                UMLog uMLog3 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TAG, 2, "开启状态:" + booleanExtra2);
                if (booleanExtra2) {
                    if (callback != null) {
                        callback.onSuccess();
                    }
                } else if (callback != null) {
                    callback.onFailure(intent.getStringExtra(o.at), intent.getStringExtra("s1"));
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (intent.getAction().equals(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION)) {
            MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService disable callback");
            try {
                boolean booleanExtra3 = intent.getBooleanExtra("status", false);
                IUmengCallback callback2 = PushAgent.getInstance(this.a).getCallback();
                UMLog uMLog4 = UMConfigure.umDebugLog;
                UMLog.mutlInfo(TAG, 2, "关闭状态:" + booleanExtra3);
                if (booleanExtra3) {
                    if (callback2 != null) {
                        callback2.onSuccess();
                    }
                } else if (callback2 != null) {
                    callback2.onFailure(intent.getStringExtra(o.at), intent.getStringExtra("s1"));
                }
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION)) {
            MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService message callback");
            try {
                UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
                uMessage.message_id = intent.getStringExtra(AdDownloadModel.JsonKey.ID);
                uMessage.task_id = intent.getStringExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID);
                if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                    messageHandler = PushAgent.getInstance(this.a).getAdHandler();
                } else {
                    messageHandler = PushAgent.getInstance(this.a).getMessageHandler();
                }
                if (messageHandler != null) {
                    messageHandler.handleMessage(this.a, uMessage);
                    return;
                }
                return;
            } catch (Exception e4) {
                if (e4.getMessage() != null) {
                    UMLog uMLog5 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TAG, 2, "MESSAGE_HANDLER_ACTION:" + e4.getMessage());
                    return;
                }
                return;
            }
        }
        if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION)) {
            MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService send log callback");
            try {
                String stringExtra2 = intent.getStringExtra(MsgConstant.KEY_SENDMESSAGE);
                final String stringExtra3 = intent.getStringExtra(MsgConstant.KEY_UMPX_PATH);
                JSONObject jSONObject = new JSONObject(stringExtra2);
                final JSONObject jSONObject2 = jSONObject.getJSONObject("jsonHeader");
                final JSONObject jSONObject3 = jSONObject.getJSONObject("jsonBody");
                final UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                try {
                    UMConfigureImpl.registerMessageSendListener(new onMessageSendListener() { // from class: com.umeng.message.UmengMessageCallbackHandlerService.2
                        @Override // com.umeng.commonsdk.utils.onMessageSendListener
                        public void onMessageSend() {
                            try {
                                JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(UmengMessageCallbackHandlerService.this.a, jSONObject2, jSONObject3, stringExtra3);
                                if (jSONObjectBuildSLEnvelope != null && !jSONObjectBuildSLEnvelope.has(c.c)) {
                                    UmengMessageCallbackHandlerService.this.a(jSONObject3.getJSONArray("push"));
                                }
                                UMConfigureImpl.removeMessageSendListener(this);
                            } catch (Throwable unused) {
                            }
                        }
                    });
                } catch (Throwable unused) {
                }
            } catch (Exception e5) {
                if (e5.getMessage() != null) {
                    UMLog uMLog6 = UMConfigure.umDebugLog;
                    UMLog.mutlInfo(TAG, 2, "MESSAGE_SEND_ACTION:" + e5.getMessage());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) throws Exception {
        File file = new File(context.getExternalFilesDir((String) null).getPath() + "/deviceToken");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONArray jSONArray) throws RemoteException, OperationApplicationException {
        if (jSONArray == null) {
            return;
        }
        try {
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    String strOptString = jSONObject.optString("msg_id");
                    int iOptInt = jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE);
                    a.a(this.a);
                    arrayList.add(ContentProviderOperation.newDelete(a.f).withSelection("MsgId=? And ActionType=?", new String[]{strOptString, iOptInt + ""}).build());
                    if (iOptInt != 0) {
                        a.a(this.a);
                        arrayList.add(ContentProviderOperation.newDelete(a.g).withSelection("MsgId=?", new String[]{strOptString}).build());
                    }
                }
            }
            ContentResolver contentResolver = this.a.getContentResolver();
            a.a(this.a);
            contentResolver.applyBatch(a.a, arrayList);
        } catch (Exception e) {
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(TAG, 2, "remove cache log:" + e.getMessage());
        }
    }

    @Override // com.umeng.message.service.JobIntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i);
        if (((a.b) b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
