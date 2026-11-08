package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.push.c;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UMessage;
import com.umeng.message.service.UMJobIntentService;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengIntentService extends UmengBaseIntentService {
    private static final String a = "com.umeng.message.UmengIntentService";

    public void onCreate() {
        try {
            super.onCreate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void onHandleIntent(Intent intent) throws JSONException {
        super.onHandleIntent(intent);
        if (intent != null) {
            try {
                if ("org.agoo.android.intent.action.RECEIVE".equals(intent.getAction())) {
                    String appkey = UMUtils.getAppkey(getApplicationContext());
                    String stringExtra = intent.getStringExtra("fromAppkey");
                    if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(appkey) || !stringExtra.contains(BaseConstants.CATEGORY_UMENG) || stringExtra.contains(appkey) || c.a()) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("fromAppkey", stringExtra);
                    c.b(getApplicationContext(), "", BaseConstants.CATEGORY_UMENG, jSONObject);
                    c.a(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.umeng.message.UmengBaseIntentService, com.taobao.agoo.TaobaoBaseIntentService
    protected void onMessage(final Context context, Intent intent) {
        try {
            MLog.i(a, "--->>> UmengIntentService onMessage");
            super.onMessage(context, intent);
            try {
                String stringExtra = intent.getStringExtra("body");
                String stringExtra2 = intent.getStringExtra(AdDownloadModel.JsonKey.ID);
                String stringExtra3 = intent.getStringExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID);
                final UMessage uMessage = new UMessage(new JSONObject(stringExtra));
                if (UMessage.DISPLAY_TYPE_PULLAPP.equals(uMessage.display_type)) {
                    if (uMessage.isAction) {
                        uMessage.pulled_service = UmengMessageDeviceConfig.getServiceName(this, uMessage.pulled_service, uMessage.pulled_package);
                    }
                    if (UmengMessageDeviceConfig.isServiceWork(context, uMessage.pulled_service, uMessage.pulled_package)) {
                        UTrack.getInstance(context).trackMsgPulled(uMessage, 52);
                        return;
                    }
                    if (!UmengMessageDeviceConfig.getDataData(uMessage.pulled_package)) {
                        UTrack.getInstance(context).trackMsgPulled(uMessage, 53);
                        return;
                    }
                    Intent intent2 = new Intent();
                    intent2.setClassName(uMessage.pulled_package, uMessage.pulled_service);
                    a(intent2, uMessage);
                    ThreadPoolExecutorFactory.execute(new Runnable() { // from class: com.umeng.message.UmengIntentService.1
                        @Override // java.lang.Runnable
                        public void run() throws InterruptedException {
                            try {
                                Thread.sleep(AppLinkMonitor.DELAY_TIME);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (UmengMessageDeviceConfig.isServiceWork(context, uMessage.pulled_service, uMessage.pulled_package)) {
                                UTrack.getInstance(context).trackMsgPulled(uMessage, 51);
                            } else {
                                UTrack.getInstance(context).trackMsgPulled(uMessage, 50);
                            }
                        }
                    });
                    return;
                }
                if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                    MLog.i(a, "--->>> UmengIntentSerivce display_type is DISPLAY_TYPE_NOTIFICATIONPULLAPP");
                    Intent intent3 = new Intent();
                    intent3.setPackage(context.getPackageName());
                    intent3.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                    intent3.putExtra("body", stringExtra);
                    intent3.putExtra(AdDownloadModel.JsonKey.ID, stringExtra2);
                    intent3.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, stringExtra3);
                    UMJobIntentService.enqueueWork(context, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent3);
                    return;
                }
                String pushIntentServiceClass = MessageSharedPrefs.getInstance(context).getPushIntentServiceClass();
                if (!pushIntentServiceClass.equalsIgnoreCase("")) {
                    MLog.i(a, "--->>> UmengIntentSerivce display_type is " + pushIntentServiceClass + " service");
                    Intent intent4 = new Intent();
                    intent4.setClassName(context, pushIntentServiceClass);
                    intent4.setPackage(context.getPackageName());
                    intent4.putExtra("body", stringExtra);
                    intent4.putExtra(AdDownloadModel.JsonKey.ID, stringExtra2);
                    intent4.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, stringExtra3);
                    UMJobIntentService.enqueueWork(context, pushIntentServiceClass, intent4);
                    return;
                }
                MLog.i(a, "--->>> UmengIntentSerivce display_type is else");
                Intent intent5 = new Intent();
                intent5.setPackage(context.getPackageName());
                intent5.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                intent5.putExtra("body", stringExtra);
                intent5.putExtra(AdDownloadModel.JsonKey.ID, stringExtra2);
                intent5.putExtra(MonitorConstants.EXTRA_DOWNLOAD_TASK_ID, stringExtra3);
                UMJobIntentService.enqueueWork(context, (Class<? extends UMJobIntentService>) UmengMessageCallbackHandlerService.class, intent5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable unused) {
        }
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (intent == null || uMessage == null || uMessage.extra == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null) {
                intent.putExtra(key, value);
            }
        }
        return intent;
    }
}
